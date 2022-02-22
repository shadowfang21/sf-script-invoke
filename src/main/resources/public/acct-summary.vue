<template>
    <div class="acct-summary">
        <div class="container">
            <h1>Acct Summary Builder</h1>
            
            <div class="border p-3 mt-3">
                <div class="form-row">
	                <div class="form-group col-md-4">
	                    <label for="acctDate">acctDate</label>
	                    <input v-model="acctSummary.acctDate" class="form-control" id="acctDate" placeholder="yyyyMMdd">
	                </div>
	                <div class="form-group col-md-4">
                        <label for="acctBranch">acctBranch</label>
                        <input v-model="acctSummary.acctBranch" class="form-control" id="acctBranch">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="balTypeCode">balTypeCode</label>
                        <input v-model="acctSummary.balTypeCode" class="form-control" id="balTypeCode">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="currCode">currCode</label>
                        <input v-model="acctSummary.currCode" class="form-control" id="currCode" value="TWD">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="systemNo">systemNo</label>
                        <input v-model="acctSummary.systemNo" class="form-control" id="systemNo" value="TS0108">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="entityCode">entityCode</label>
                        <input v-model="acctSummary.entityCode" class="form-control" id="entityCode" value="TW000">
                    </div>
                    
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="fahMappingVer">fahMappingVer</label>
                        <input v-model="acctSummary.fahMappingVer" class="form-control" id="fahMappingVer" value="01">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="fahObjectType">fahObjectType</label>
                        <input v-model="acctSummary.fahObjectType" class="form-control" id="fahObjectType">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="fahObjectCode">fahObjectCode</label>
                        <input v-model="acctSummary.fahObjectCode" class="form-control" id="fahObjectCode">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="totalAmt">totalAmt</label>
                        <input v-model="acctSummary.totalAmt" class="form-control" id="totalAmt">
                    </div>
                </div>
                <button class="btn-primary btn-sm rounded-circle btn mb-3" @click="addMapping">+</button>
                <div class="form-row" v-for="(mapping,index) in acctSummary.mappingInfos">
                    <div class="form-group col-md-4">
                        <label><a class="text-danger rounded-circle mr-2" @click="deleteMapping(mapping)" >x</a></label>
                        <label for="itemNo">itemNo</label>
                        <input v-model="mapping.itemNo" class="form-control" id="itemNo" disabled>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="mappingCode">mappingCode</label>
                        <input v-model="mapping.mappingCode" class="form-control" id="mappingCode">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="mappingVal">mappingVal</label>
                        <input v-model="mapping.mappingVal" class="form-control" id="mappingVal">
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid pt-3">
            <p>
                {{json}}
            </p>
        </div>
    </div>
</template>


<script>
    
    module.exports = {
        data : function() {
            return {
            	acctSummary : {
            		entityCode : 'TW000',
            		systemNo : "TS0108",
            		currCode : "TWD",
            		fahMappingVer : "01",
            		mappingInfos : []
            	}
            }
        },
        computed : {
        	json : function() {
        		
        		var obj = Object.assign({}, this.acctSummary);
        		
        		obj.mappingCount = obj.mappingInfos.length;
        		
        		return JSON.stringify(obj);
        	}
        },
        methods : {
           addMapping : function() {
        	   this.acctSummary.mappingInfos.push({
        		   itemNo : this.acctSummary.mappingInfos.length+1
        	   });
           },
           deleteMapping : function(mapping) {
        	   this.acctSummary.mappingInfos.splice(this.acctSummary.mappingInfos.indexOf(mapping), 1);
        	   
        	   this.acctSummary.mappingInfos.forEach((mapping, index) => {
        		   mapping.itemNo = index+1;
        	   })
           }
        }
    }
    
    
</script>
