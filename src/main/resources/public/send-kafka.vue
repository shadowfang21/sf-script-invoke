<template>
    <div class="send-kafka">
        <div class="container">
        
            <form class="form">
                <div class="form-group">    
                    <label for="topic">topic</label>
                    <select id="topic" v-model="topic" class="form-control">
                        <option value="TRANS_TS0085_00">interface</option>
                        <option value="TRANS_TS0085_01">engine</option>
                        <option value="TXBAT_TS0085_00">interface_batch</option>
                        <option value="TXBAT_TS0085_01">engine_batch</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="json">json</label>
                    <textarea id="json" v-model="json" rows="20" class="form-control" @change="prettyJson" required></textarea>
                </div>
            </form>
            
            <input type="button" class="btn btn-primary" @click="post" value="post">
            
            
            <p>
            {{message}}
            </p>
        </div>
    </div>
</template>


<script>
    
    module.exports = {
        data : function() {
            return {
                json : '',
                topic : 'TRANS_TS0085_00',
                message : ''
            }
        },
        methods : {
        	
        	prettyJson : function() {
        		var tempJson = this.json
        		try {
        			this.json = JSON.stringify(JSON.parse(this.json),null,2);	
        		} catch {
        			
        		}
        	},
        	post : function() {
        		
        		var that = this;
        		
        		if (!validate()) return false;
        		
        		this.$http.post(api_url + "/api/sendKafka/publichKafkaMessage", {
        			  json : this.json,
        			  topic : this.topic
        		}).then(function(data) {
        			that.message = data.body;
        			
        			that.$router.app.$emit('pushMessage', "put message success");
        		});
        	}
        }
    }
    
    
</script>
