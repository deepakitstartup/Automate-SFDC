({
    onInit: function(component, event, helper) {
        helper.onInit(component, event, helper);
    },
    /**
     * Handles refresh when AccountContactRelation, AccountAccountRelation__c, ContactContactRelation__c changes or account/contact record id change.
     **/
    onChange: function(component, event, helper) {
        var recordId = component.get("v.recordId");
        var eRecord = event.getParam("record");
        // Matches markup://<namespace> and extracts namespace
        var prefix = component.toString().match(/markup:\/\/(\w{1,15}):/)[1];
        prefix = (prefix == "c" ? "" : prefix + "__").toLowerCase();

        var objectTypesToWatch = [
            "accountcontactrelation",
            prefix + "accountaccountrelation__c",
            prefix + "contactcontactrelation__c"
        ];
        var additionalWatchObjects = component.get('v.additionalWatchObjects');
        if (!$A.util.isEmpty(additionalWatchObjects)){
            objectTypesToWatch = objectTypesToWatch.concat(additionalWatchObjects.toLowerCase().split(','));
        }

        var eRecordObjectType = (!$A.util.isEmpty(eRecord)) ? eRecord.sobjectType : null;
        var relationshipObjectFlag = objectTypesToWatch.indexOf(eRecordObjectType.toLowerCase()) > -1;
        if (!$A.util.isEmpty(eRecord) && (eRecord.Id === recordId || relationshipObjectFlag)) {
            helper.onInit(component, event, helper);
        }
    },

    showData: function(component, event, helper){
        var eventData = event.getParam('data');
        helper.setTreeData(component, eventData.treeRoot);
    },

    showError: function(component, event, helper) {
        var errorMessage = event.getParam('error');

        component.set("v.showErrors", true);
        component.set("v.errors", errorMessage);
    }
})