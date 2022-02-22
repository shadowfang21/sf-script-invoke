<template>
    <div class="upload-file">
     <div class="container">
        <h1>Upload file</h1>
        <form>
            <div class="form-group">
               <label for="uploadPath" >Upload path</label>
               <select v-model="uploadPath" class="form-control" id="uploadPath" required="required">
                   <option v-for="option in uploadPathList" v-bind:value="option">
                        {{ option }}
                      </option>
               </select> 
            </div>
            <div class="form-group">
                <label for="fileType" >FileType</label>
               <select v-model="fileType" class="form-control" id="fileType">
                   <option v-for="option in fileTypeList" v-bind:value="option">
                        {{option.prefix}}
                   </option>
               </select> 
            </div>
            <div class="form-check" v-show="fileType.isZip">
                <input type="checkbox" class="form-check-input" id="isZip" v-model="isZip">
                <label class="form-check-label" for="isZip">Auto zip</label>
            </div>
            <div class="btn-group pt-2">
                <input type="file" class="btn btn-secondary" id="file" name="file" ref="file" required="required">
                <input type="button" class="btn btn-primary" @click="upload" value="upload">
            </div>
        </form>
         
     </div>
     
    </div>
</template>


<script>
    
	var evtSource = false;
    
    module.exports = {
        data : function() {
        	return {
        		uploadPathList : [],
        		uploadPath : '',
        		isZip: true,
        		fileTypeList : [
        		    {prefix:'', isZip:false},        	
        			{prefix:'dayEnd-', isZip:false}, 
        			{prefix:'trans-', isZip:true}, 
        			{prefix:'transEnd-', isZip:true}, 
        			{prefix:'acctSum-', isZip:true}
        		],
        		fileType : {}
            }
        },
        beforeMount : function() {
            var that = this;
        	
            $.get(api_url + "/api/download/listPath", function(data) {
        		data.forEach(function(d) {
        			that.uploadPathList.push(d);
        		})
        	})
        },
        computed : {
            isAutoZip : function() {
            	return this.fileType.isZip && this.isZip;
            }
        },
        methods: {
            upload : function() {
            	
            	if (!validate()) return false;
            	
            	var formData = new FormData();
                formData.append("file", this.$refs.file.files[0]);
                formData.append("prefix", this.fileType.prefix);
                formData.append("autoZip", this.isAutoZip);
                formData.append("uploadPath", this.uploadPath);
            	
                var that = this;
            	this.$http.post(api_url + "/api/upload/upload", formData)
            	   .then(function(data) {
                        	that.$router.app.$emit('pushMessage', data.body);
                });
            
            }
        }
    }
    
    
</script>


<style>

</style>