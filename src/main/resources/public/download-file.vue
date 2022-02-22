<template>
    <div class="download-file">
     <div class="container">
        <h1>Download file</h1>
        <form>
            <div class="form-group">
               <label for="downloadPath" class="form-label">Download path</label>
               <select v-model="downloadPath" class="form-control" id="downloadPath" required="required">
                   <option v-for="option in downloadPathList" v-bind:value="option">
                        {{ option }}
                      </option>
               </select> 
            </div>
            <div class="btn-group pt-2">
                <input type="button" class="btn btn-primary" @click="search" value="search">
            </div>
            
            <div class="form-group">
                <label for="pattern">Delete pattern</label>
                <input type="text" v-model='pattern' id="pattern" class="form-control">
            </div>
            
            <div class="btn-group pt-2">
                <input type="button" class="btn btn-primary" @click="removeFile" value="removeFile">
            </div>
        </form>
        <hr>
     </div>
     <div class="container">
        
        <table class="table table-striped table-bordered">
            <thead class="">
                <th scope="col">#</th>
                <th scope="col">FileName</th>
                <th scope="col">ModifyTime</th>
                <th scope="col">FileSize</th>
                <th scope="col"></th>
            </thead>
            <tbody>
                <tr v-for="(file, index) in fileList">
                    <td><input type="checkbox" style="zoom:180%;" v-model="selectedFileList" v-bind:value="file.fileName"></td>
                    <td>{{file.fileName}}</td>
                    <td>{{file.createTime}}</td>
                    <td>{{file.fileSize}}</td>
                    <td><input type="button" class="btn btn-primary" value="download" @click="download(file)"></td>
                </tr>
            </tbody>
        </table>
        
     </div>
     
    </div>
</template>


<script>
    
	var evtSource = false;
    
    module.exports = {
        data : function() {
        	return {
        		downloadPathList : [],
        		downloadPath : '',
        		fileList : [],
        		pattern : null,
        		selectedFileList : []
            }
        },
        beforeMount : function() {
            var that = this;
        	
            $.get(api_url + "/api/download/listPath", function(data) {
        		data.forEach(function(d) {
        			that.downloadPathList.push(d);
        		})
        	})
        },
        methods: {
        	search : function() {
            	
            	if (!validate()) return false;
            	
                var that = this;
            	this.$http.get(api_url + "/api/download/listFile", {
            		params: {
            			downloadPath : that.downloadPath
            		}
            	}).then(function(data) {
            		
            		var json = data.json();
            		
            		if (json.length == 0) {
            			that.$router.app.$emit('pushMessage', "no data found");
            			that.fileList = [];
            		} else {
            			that.fileList = json;	
            		}
            	});
            },
            download : function(file) {
            	console.log(file.downloadPath);
            	console.log(file.fileName);
            	
            	window.open(api_url + "/api/download/download?downloadPath=" + file.downloadPath + "&fileName=" + file.fileName);
            },
            removeFile : function() {
            	
                var fileNames = this.selectedFileList.map(f=> {
                	return f
                });
            	
                this.selectedFileList = [];
                
                var that = this;
                this.$http.delete(api_url + "/api/download/deleteFile", {
                	body : {
                		pattern : that.pattern,
                		fileNames : fileNames,
                        downloadPath : that.downloadPath
                	}
                }).then(function(data) {
                	that.search();
                	that.$router.app.$emit('pushMessage', data.body);
                }, response => {
                	that.$router.app.$emit('pushMessage', response);
                });
            }
        
        }
    }
    
    
</script>


<style>

</style>