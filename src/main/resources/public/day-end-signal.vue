<template>
    <div class="day-end-signal">
        <div class="container">
            <h1>Day End Signal List</h1>
            <input type="button" class="btn btn-primary" @click="list" value="search">
            <input type="button" class="btn btn-primary" @click="showAdd" value="add">
            <hr>
            <div class="row">
	            <div class="col-3 border p-3" v-for="item in dayEndSignalList" v-bind:key="item.dayEndSignalSeqNo">
		            <form>
		                <h3>{{item.entityCode}}</h3>
		                <div class="form-group" v-if="mode == 'add'">
                          <label for="prevAcctDate">entityCode</label>
                          <input v-model="item.entityCode" name="entityCode" id="entityCode" required class="form-control">
                        </div>
		                <div class="form-group">
		                  <label for="prevAcctDate">prevAcctDate</label>
		                  <input v-model="item.prevAcctDate" name="prevAcctDate" id="prevAcctDate" required class="form-control" placeholder="yyyy-mm-dd">
		                </div>
		                <div class="form-group">
                          <label for="newAcctDate">newAcctDate</label>
                          <input v-model="item.newAcctDate" name="newAcctDate" id="newAcctDate" required class="form-control" placeholder="yyyy-mm-dd">
                        </div>
                        
                        <div v-if="mode=='add'">
                            <input type="button" class="btn btn-primary float-right" @click="save(item)" value="save">
                        </div>
                        <div v-else>
                            <input type="button" class="btn btn-primary float-right" @click="deleteItem(item)" value="delete">
                            <input type="button" class="btn btn-primary float-right mr-2" @click="update(item)" value="update">
                        </div>
                    </form>
	            </div>
            </div>
        </div>
    </div>
</template>


<script>
    
    module.exports = {
        data : function() {
            return {
                dayEndSignalList : [],
                mode:''
            }
        },
        methods : {
        	showAdd : function() {
        		this.mode = 'add';
        		
        		this.dayEndSignalList = [];
        		this.dayEndSignalList.push({});
        	},
        	save : function(item) {
        		
        		var that = this;
        		this.$http.put(api_url + "/api/dayEndSignal/", item).then(function(data) {
                    that.dayEndSignalList = [];
                    that.list();
                    that.mode = '';
                    that.$router.app.$emit('pushMessage', "update done");
                }, function(err) {
                    this.$root.$emit('pushMessage', err);
                });
        		
        		
        	},
        	list : function() {
        		this.mode = '';
        		this.dayEndSignalList = [];
        		
        		var that = this;
        		$.get(api_url + "/api/dayEndSignal/list", function(data) {
        	    	that.dayEndSignalList = data;
        	    })
        	},
        	deleteItem : function(item) {
        	    var that = this;
                
                this.$http.delete(api_url + "/api/dayEndSignal/" + item.dayEndSignalSeqNo, item).then(function(data) {
                    that.dayEndSignalList = [];
                    that.list();
                    
                    that.$router.app.$emit('pushMessage', "update done");
                }, function(err) {
                    this.$root.$emit('pushMessage', err);
                });
        	},
        	update : function(item) {
        		var that = this;
        		
        		this.$http.put(api_url + "/api/dayEndSignal/" + item.dayEndSignalSeqNo, item).then(function(data) {
        			that.dayEndSignalList = [];
        		    that.list();
        		    
        		    that.$router.app.$emit('pushMessage', "update done");
        		}, function(err) {
        			this.$root.$emit('pushMessage', err);
        		});
        	}
        }
    }
    
    
</script>
