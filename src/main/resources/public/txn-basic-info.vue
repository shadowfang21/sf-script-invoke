<template>
    <div class="txn-basic-info">
        <div class="container">
            <h1>Txn Basic Info List</h1>
            <form>
                <div class="form-group">
                    <label for="acctDate">AcctDate</label>
                    <input v-model="acctDate" name="acctDate" id="acctDate" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="fahGuid">FahGuid</label>
                    <input v-model="fahGuid" name="fahGuid" id="fahGuid" class="form-control">
                </div>
                <div class="form-group">
                    <label for="subTxnNo">SubTxnNo</label>
                    <input v-model="subTxnNo" name="subTxnNo" id="subTxnNo" class="form-control">
                </div>
                <input type="button" class="btn btn-primary" @click="list" value="search">
                <input type="button" class="btn btn-primary" @click="changeSubTxnNo" value="changeSubTxnNo">
            </form>
            <hr>
        </div>
        <div class="container-fluid">
            <table class="table table-striped table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">SeqNo</th>
                        <th scope="col">AcctDate</th>
                        <th scope="col">FahGuid</th>
                        <th scope="col">SubTxnNo</th>
                        <th scope="col">entityCode</th>
                        <th scope="col">batchFileCtrlSeqNo</th>
                        <th scope="col">processStatus</th>
                        <th scope="col">dataValidationErrorCode</th>
                        
                    </tr>
                </thead>
                <tbody>
                    <tr class="" v-for="item in txnBasicInfoList" v-bind:key="item.txnBasicInfoSeqNo">
                        <td>{{item.txnBasicInfoSeqNo}}</td>
                        <td>{{item.acctDate}}</td>
                        <td>{{item.fahGuid}}</td>
                        <td>{{item.subTxnNo}}</td>
                        <td>{{item.entityCode}}</td>
                        <td>{{item.batchFileCtrlSeqNo}}</td>
                        <td>{{item.processStatus}}</td>
                        <td>{{item.dataValidationErrorCode}}</td>
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
            	acctDate:null,
            	fahGuid : null,
            	subTxnNo : null,
                txnBasicInfoList : []
            }
        },
        methods : {
            list : function() {
                this.txnBasicInfoList = [];
                var that = this;
                
                if (!validate()) return false;
                
                this.$http.get(api_url + "/api/txnBasicInfo/list", {
                	params : {
                	    'acctDate' : that.acctDate,
                	    'fahGuid' : that.fahGuid,
                	    'subTxnNo' : that.subTxnNo
                	}
                }).then(function(data) {
                	var json = data.json();
                	if (json.length == 0) {
                        that.$router.app.$emit('pushMessage', "no data found");
                        that.txnBasicInfoList = [];
                    } else {
                    	that.txnBasicInfoList = json;		
                    }
                	
                });
            },
            changeSubTxnNo : function() {
            	
            	if (!validate()) return false;
            	
            	if (!confirm("are you sure?")) {
            		return false;
            	}
            	
            	var that = this;
            	
            	this.$http.put(api_url + "/api/txnBasicInfo/changeSubTxnNo", {
            	  'acctDate' : that.acctDate,
                  'fahGuid' : that.fahGuid,
                  'subTxnNo' : that.subTxnNo
                }).then(function(data) {
                    that.list();
                    that.$router.app.$emit('pushMessage', "change sub txn no done");
                });
            	
            }
        }
    }
    
    
</script>
