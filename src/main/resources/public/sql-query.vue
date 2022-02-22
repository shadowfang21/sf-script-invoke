<template>
    <div class="sql-query">
        <div class="container">
            <form class="form">
                <div class="form-group">
                    <label for="sql">Sql</label>
                    <textarea id="sql" v-model="sql" rows="14" class="form-control" required></textarea>
                </div>
            </form>
            
        </div>
        <div class="container">
            <input type="button" class="btn btn-primary" @click="query" value="query">
            <input type="button" class="btn btn-primary" @click="execute" value="execute">
            
            <select  @change="loadTemplate">
                <option selected></option>
                <option v-for="item in templates" v-bind:value="item.sql">
                    {{item.name}}
                </option>
            </select>
         </div>
         <div class="container-fluid pt-3" v-show="dataList.length > 0">
            {{keys}}
            <table class="table table-striped table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th scope="col"></th>
                        <th scope="col" v-for="item in keys" v-bind:key="item">
                            {{item}}
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="" v-for="(item,index) in dataList" v-bind:key="index">
                        <td>{{index+1}}</td>
                        <td scope="col" v-for="key in keys" v-bind:key="key">
                            {{item[key]}}
                        </td>
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
                sql : '',
                dataList : [],
                keys : [],
                templates : [
                	{"name":"insert edls compare data", "sql":`
delete from tb_accing_engine where tx_date = '20180731';
insert into tb_accing_engine (
accing_engine_seq_no,fahgu_id,sub_txn_no,tx_type,
accing_data,tx_date,accing_timestamp,
create_date,amend_date,status)
select txn_basic_info_seq_no,fah_guid,sub_txn_no, service_type,
'insert from txn_basic_info', acct_date, txn_time, sysdate, sysdate, 1
from tb_txn_basic_info
where acct_date = '20180731'
and system_no = 'TS0108'
and entity_code = 'TW000';
`},
                	{"name":"insert biz date", "sql":`
insert into tb_batch_business_date(curbizday, lastbizday, nextbizday)
select '20180604', '20180603', '20190605'
from dual where not exists (select 1 from tb_batch_business_date);
update tb_batch_business_date set lastbizday='20180603', curbizday='20180604', nextbizday='20180605';
`},
                ]
            }
        },
        methods : {
        	loadTemplate : function(event) {
        		this.sql = event.target.value;
        	},
        	query : function() {
        		
        		var that = this;
        		
        		if (!validate()) return false;
        		
        		this.$http.post(api_url + "/api/sql/query", this.sql).then(function(data) {
        			
        			var json = data.json();
        			
        			if (json.length == 0) {
        				that.$router.app.$emit('pushMessage', "no data found");
        				that.dataList = [];
        				that.keys = [];
        			} else {
        				that.keys = json[0]._keys_;
        				that.dataList = json;
        			}
        		}, response => {
        			that.dataList = [];
        			that.keys = [];
        			
        			that.$router.app.$emit('pushMessage', response);
        		});
        	},
            execute : function() {
                
                var that = this;
                
                if (!validate()) return false;
                
                this.$http.post(api_url + "/api/sql/execute", this.sql).then(function(data) {
                    
                    that.$router.app.$emit('pushMessage', "execute done");
                }, response => {
                	that.$router.app.$emit('pushMessage', response);
                });
            }
        }
    }
    
    
</script>
