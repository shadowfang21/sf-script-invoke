<template>
    <div class="script-invoke">
     <div class="container">
         <h1>Input</h1>
         <div class="input">
             <form>
                 <div class="form-group">
                     <label for="script">script</label>
                     <select id="script" name="script" class="form-control" v-model="script" ref="script" required>
                          <option v-for="option in scriptList" v-bind:value="option">
                             {{ option }}
                           </option>
                     </select>
                 </div>
                 <div class="form-group">
                     <label for="acctDate">acct_date</label>
                     <input type="text" id="acctDate" name="acctDate" class="form-control" placeholder="yyyyMMdd" v-model="acctDate">
                 </div>
                 <div class="form-group">
                     <label for="entityCode">entity_code</label>
                     <input type="text" id="entityCode" name="entityCode" class="form-control" placeholder="TW000" v-model="entityCode">
                 </div>
                 <div class="form-group">
                     <label for="params">params</label>
                     <input type="text" id="params" name="params" class="form-control" placeholder="--xx=yy" v-model="params">
                 </div>
                 
                 <input type="button" class="btn btn-primary" @click="invokeScript" value="submit" :disabled="loading">
             </form>
             
         </div>
         
     </div>
     <div class="container">
          <h1>Output</h1>
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
                script:'',
                acctDate:'',
                loading:false,
                done:false,
                items:[],
                entityCode:'',
                params:'',
                scriptList : [
                	'bash,-c,ping www.google.com.tw -c 3',
                	'cmd.exe,/c,ping www.google.com.tw'
                ]
            }
        },
        beforeMount : function() {
            var that = this;
        	
            $.get(api_url + "/api/script/list", function(data) {
        		data.forEach(function(d) {
        			that.scriptList.push("bash,-c,sh " + d);
        		})
        	})
        },
        computed : {
        	bizDate : function() {
        		if (this.acctDate != '') {
        			return ' --bizDate=' + this.acctDate;
        		}
        		return '';
        	},
        	entityCodes : function() {
        		if (this.entityCode != '') {
        			return ' --entityCodes=' + this.entityCode;
        		}
        		return ''
        	}
        },
        methods: {
             invokeScript : function() {
               
               this.rest();
               
               if (!validate()) return false;
               
               this.loading = true;
               
               evtSource = new EventSource(api_url + "/ping?script=" + this.script + this.bizDate + this.entityCodes + " " + this.params);
               
               var that = this;
               
               evtSource.onmessage = function(e) {
                   that.items.push(e.data);
                   window.scrollTo(0,document.body.scrollHeight);
               }
               evtSource.onerror = function(e) {
                   // Connection was closed.
                   console.log('close connection');
                   that.loading = false;
                   that.done = true;
                   evtSource.close();
                   window.scrollTo(0,document.body.scrollHeight);
               }
             },
             rest : function() {
                 
               if (evtSource !== false) {
                 evtSource.close();
               }
               this.loading = false;
               this.done = false;
               this.items = [];
             }
         }
    }
    
    
</script>


<style>

</style>