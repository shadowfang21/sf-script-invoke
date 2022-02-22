<template>
    <div class="oms-message">
        <div class="container">
            <h1>Oms Message List</h1>
            <input type="button" class="btn btn-primary" @click="list" value="search">
            <input type="button" class="btn btn-primary" @click="deleteAll" value="deleteAll">
            <hr>
        </div>
        <div class="container-fluid">
            <table class="table table-striped table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">SeqNo</th>
                        <th scope="col">MsgId</th>
                        <th scope="col">Message</th>
                        <th scope="col">UpdatedTime</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="" v-for="item in omsMessageList" v-bind:key="item.seqNo">
                        <td>{{item.seqNo}}</td>
                        <td>{{item.msgId}}</td>
                        <td>{{item.message}}</td>
                        <td>{{item.updatedTime}}</td>
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
                omsMessageList : []
            }
        },
        methods : {
        	list : function() {
        		this.omsMessageList = [];
        		var that = this;
        	    $.get(api_url + "/api/omsMessage/list", function(data) {
        	    	if (data.length == 0) {
        	    		that.$router.app.$emit('pushMessage', "no data found");
        	    		that.omsMessageList = [];
        	    	} else {
        	    		that.omsMessageList = data;	
        	    	}
        	    })
        	},
        	deleteAll : function() {
        		
        		if (!confirm("are you sure?")) {
        			return false;
        		}
        		
        		var that = this;
        		this.$http.delete(api_url + "/api/omsMessage/").then(function(data) {
        			this.omsMessageList = [];
        			that.$router.app.$emit('pushMessage', "delete done");
        		});
        	}
        }
    }
    
    
</script>
