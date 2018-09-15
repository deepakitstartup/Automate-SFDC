<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <alerts>
        <fullName>Approval_Alert_Email</fullName>
        <description>Approval Alert Email</description>
        <protected>false</protected>
        <recipients>
            <recipient>namita.shaha@accenture.com</recipient>
            <type>user</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>FSCEmailTemplates/FSCNewReferralNotification</template>
    </alerts>
    <alerts>
        <fullName>FSCNewReferralAssignmentNotification</fullName>
        <description>Email alert for a new referral assignment</description>
        <protected>false</protected>
        <recipients>
            <type>owner</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>FSCEmailTemplates/FSCNewReferralAssignmentNotification</template>
    </alerts>
    <alerts>
        <fullName>FSCNewReferralNotification</fullName>
        <description>Email alert for a new referral</description>
        <protected>false</protected>
        <recipients>
            <field>FinServ__ReferredByContact__c</field>
            <type>contactLookup</type>
        </recipients>
        <recipients>
            <field>FinServ__ReferredByUser__c</field>
            <type>userLookup</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>FSCEmailTemplates/FSCNewReferralNotification</template>
    </alerts>
    <alerts>
        <fullName>FSCUpdatedReferralNotification</fullName>
        <description>Email alert for an updated referral</description>
        <protected>false</protected>
        <recipients>
            <field>FinServ__ReferredByContact__c</field>
            <type>contactLookup</type>
        </recipients>
        <recipients>
            <field>FinServ__ReferredByUser__c</field>
            <type>userLookup</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>FSCEmailTemplates/FSCUpdatedReferralNotification</template>
    </alerts>
    <fieldUpdates>
        <fullName>Approved_Referral_Owner</fullName>
        <field>Start_Date__c</field>
        <formula>TODAY()</formula>
        <name>Approved Referral Owner</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Rejection_Action</fullName>
        <field>Status</field>
        <literalValue>Unqualified</literalValue>
        <name>Rejection Action</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
</Workflow>
