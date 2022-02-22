<template>
    <div class="txn-info-error">
        <custom-modal></custom-modal>
        <div class="container">
            <h1>Txn Info Error List</h1>
            <form>
                <div class="form-group">
                    <label for="createdTime">CreatedTime</label>
                    <input v-model="createdTime" name="createdTime" id="createdTime" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="message">Message</label>
                    <input v-model="message" name="message" id="message" class="form-control">
                </div>
                
                <input type="button" class="btn btn-primary" @click="list" value="search">
            </form>
            <hr>
        </div>
        <div class="container-fluid">
            <table class="table table-striped table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">SeqNo</th>
                        <th scope="col">batchFileCtrlSeqNo</th>
                        <th scope="col">traceId</th>
                        <th scope="col">spanId</th>
                        <th scope="col">message</th>
                        <th scope="col">errorMsg</th>
                        <th scope="col">createdTime</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="" v-for="item in txnInfoErrorList" v-bind:key="item.txnInfoErrorSeqNo">
                        <td>{{item.txnInfoErrorSeqNo}}</td>
                        <td>{{item.batchFileCtrlSeqNo}}</td>
                        <td>{{item.traceId}}</td>
                        <td>{{item.spanId}}</td>
                        <td><a href="javascript:;" @click='showDetail(item.txnInfoErrorSeqNo, item.message, true)'>{{item.message | substr }}</a></td>
                        <td><a href="javascript:;" @click="showDetail(item.txnInfoErrorSeqNo, item.errorMsg)">{{item.errorMsg | substr }}</a></td>
                        <td>{{item.createdTime}}</td>
                    </tr>    
                </tbody>
            </table>
        </div>
    </div>
</template>


<script>
    
    module.exports = {
        data : function() {
            return {
            	createdTime : moment().format('YYYYMMDD'),
            	message : null,
            	txnInfoErrorList : []
            }
        },
        beforeMount : function() {
        	var lastSearchCreatedTime = sessionStorage.getItem('txn_info_error_created_time');
        	
        	if (lastSearchCreatedTime) {
        	    this.createdTime = lastSearchCreatedTime;
        	} 
        },
        filters : {
        	substr : function(value) {
        		if (value) {
        		    return value.substr(0, 100);	   		
        		}
        	}
        },
        methods : {
            list : function() {
            	
            	sessionStorage.setItem('txn_info_error_created_time', this.createdTime);
            	
                this.txnInfoErrorList = [];
                var that = this;
                
                if (!validate()) return false;
                
                this.$http.get(api_url + "/api/txnInfoError/list", {
                	params : {
                	    'createdTime' : that.createdTime,
                	    'message' : that.message
                	}
                }).then(function(data) {
                	var json = data.json();
                	if (json.length == 0) {
                        that.$router.app.$emit('pushMessage', "no data found");
                        that.txnInfoErrorList = [];
                    } else {
                    	that.txnInfoErrorList = json;		
                    }
                	
                });
            },
            showDetail : function(title, body, json=false) {
            	
            	if (json) {
            		try {
            			body = JSON.stringify(JSON.parse(body),null,2);	
            		} catch (e) {
            			
            		}
            	}
            	
            	this.$router.app.$emit("showModal", {
            		"title" : `SeqNo : ${title}`,
            		"body" : body
            	});
            }
        }
    }
    
    
</script>
