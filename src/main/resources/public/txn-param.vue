<template>
    <div class="txn-param">
        <div class="container">
            <h1>txn param</h1>
            <h3 class="text-danger">no chinese file name</h3>
            <h3 class="text-danger">unique file name</h3>
            
            <form id="txn-param-form" enctype="multipart/form-data" method="post">
                <div class="form-group">
                    <label for="command">Command</label>
                    <input v-model="command" class="form-control" id="command" name="command">
                    <div>
                        {{getCommand}}
                    </div>
                </div>
                <div class="form-check pb-2">
                    <input type="checkbox" v-model="autoFahGuid" class="form-check-input" id="autoFahGuid" name="autoFahGuid">
                    <label class="form-check-label" for="autoFahGuid">autoFahGuid(fahGuid should have at least 4 chars)</label>
                </div>
                
                <div class="form-check">
				  <input class="form-check-input" type="radio" value="putKafka" id="putKafka"
				    v-model="opType" name="opType" @change="changeOpType">
				  <label class="form-check-label" for="putKafka">putKafka</label>
				</div>
				<div class="form-check">
				  <input class="form-check-input" type="radio" value="uploadFile" id="uploadFile"
				     v-model="opType" name="opType" @change="changeOpType">
				  <label class="form-check-label" for="uploadFile">uploadFile</label>
				</div>
				<div class="form-check">
                  <input class="form-check-input" type="radio" value="printJson" id="printJson"
                     v-model="opType" name="opType" @change="changeOpType">
                  <label class="form-check-label" for="printJson">printJson</label>
                </div>
				<div class="form-group" v-show="opType == 'uploadFile'">
				    <label for="uploadPath" >Upload path</label>
                    <select v-model="uploadPath" class="form-control" id="uploadPath">
                        <option v-for="(option,index) in uploadPathList" v-bind:value="option">
                             {{ option }}
                           </option>
                    </select> 
                  </div>
				
                <div class="btn-group pt-2">
	                <input type="button" class="btn btn-primary" @click="downloadFormat" value="downloadFormat">
	                <input type="button" class="btn btn-primary" @click="downloadJar" value="downloadJar">
	                <input type="file" class="btn btn-secondary" id="file" name="file" ref="file" required>
                    <input type="button" class="btn btn-primary" @click="upload" value="upload">
	            </div>
	        </form>
        </div>
        <div class="container-fluid">
	         <div class="output">
	             <ul class="list-group">
	                 <li v-for="item in items" class="list-group-item">{{item}}</li>
	             </ul>
	         </div>
        </div>
        <div class="container">
            <input type="button" class="btn btn-primary" @click="rest" value="reset" v-show="done">
        </div>
    </div>
</template>


<script>
    
	var evtSource = false;
	
    module.exports = {
        data : function() {
            return {
            	items:[],
            	uploadPathList : [],
            	uploadPath:'',
            	opType : 'putKafka',
            	fileName :'',
            	autoFahGuid : false,
            	done:false,
            	command : 'bash,-c,java -jar $scriptPath/efah_test_program-0.0.1-SNAPSHOT.jar --input=$scriptPath/upload/$filename'
            }
        },
        beforeMount : function() {
            var that = this;
            
            $.get(api_url + "/api/upload/list", function(data) {
                data.forEach(function(d) {
                    that.uploadPathList.push(d);
                })
                that.uploadPath = that.uploadPathList[0];
            })
        },
        computed : {
        	getCommand : function() {
        		var c = this.command;
        		
        		if (this.opType == 'putKafka') {
        			c += ' --putKafka=true';
        		} else if (this.opType == 'uploadFile'){
        			c += ' --output=$scriptPath/out/$filename';
        		} else if (this.opType == 'printJson') {
        			c += ' --printJson=true ';
        		}
        		if (this.autoFahGuid) {
                    c += " --autoFahGuid=true "; 
                }
        	    return c;
        	}
        },
        methods : {
        	changeOpType : function() {
        	    
        	},
        	downloadFormat : function() {
        		window.open(api_url + "/api/txnParam/downloadFormat");
        	},
        	downloadJar : function() {
        		window.open(api_url + "/api/txnParam/downloadJar");
            },
            upload : function() {
        		
            	this.rest();
        		
            	if (!validate()) return false;
            	
        		var formData = new FormData();
        		formData.append("file", this.$refs.file.files[0]);
        		
        		var that = this;
        		
        		this.$http.post(api_url + "/api/txnParam/upload", formData).then(
        	       function(data) {
                    
        	    	   that.fileName = data.data;
        	    	   
        	    	   evtSource = new EventSource(api_url + "/ping?script=" + that.getCommand.replace(/\$filename/g, that.fileName));
                       
                       evtSource.onmessage = function(e) {
                           that.items.push(e.data);
                           window.scrollTo(0,document.body.scrollHeight);
                       }
                       evtSource.onerror = function(e) {
                           // Connection was closed.
                           console.log('close connection');
                           console.log(e);
                           that.done =true;
                           evtSource.close();
                           window.scrollTo(0,document.body.scrollHeight);
                           
                           
                           if (that.opType == 'uploadFile') {
                        	   that.$http.post(api_url + "/api/txnParam/uploadTxnInfoBatch", {
                        		   fileName : that.fileName,
                        		   uploadPath : that.uploadPath
                        	   }).then(function(data) {
                        		   that.items.push("--------");
                        		   that.items.push(data.data);
                        		   that.$router.app.$emit('pushMessage', data.data);
                        		   that.items.push("--------");
                        	   })
                           }
                       }
        	    }, function(err) {
                    console.log(err);
                });
        		
        	},
        	rest : function() {
                
                if (evtSource !== false) {
                  evtSource.close();
                }
                this.done = false;
                this.items = [];
              }
        }
    }
    
    
</script>