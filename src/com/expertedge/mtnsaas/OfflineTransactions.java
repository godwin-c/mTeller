/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expertedge.mtnsaas;

/**
 *
 * @author imedia-2
 */
public class OfflineTransactions {
    private String accountNumber;
    private String accountName;
    private String amount;
    private String mfbCode;
    private String tellerNumber;
    private String tranDescription;
    private String tranCode;
    private String tranType;

    /**
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the mfbCode
     */
    public String getMfbCode() {
        return mfbCode;
    }

    /**
     * @param mfbCode the mfbCode to set
     */
    public void setMfbCode(String mfbCode) {
        this.mfbCode = mfbCode;
    }

    /**
     * @return the tellerNumber
     */
    public String getTellerNumber() {
        return tellerNumber;
    }

    /**
     * @param tellerNumber the tellerNumber to set
     */
    public void setTellerNumber(String tellerNumber) {
        this.tellerNumber = tellerNumber;
    }

    /**
     * @return the tranDescription
     */
    public String getTranDescription() {
        return tranDescription;
    }

    /**
     * @param tranDescription the tranDescription to set
     */
    public void setTranDescription(String tranDescription) {
        this.tranDescription = tranDescription;
    }

    /**
     * @return the tranCode
     */
    public String getTranCode() {
        return tranCode;
    }

    /**
     * @param tranCode the tranCode to set
     */
    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }
    
    public OfflineTransactions(String accountNumber,String name, String amount, String mfbCode, String tellerNumber, 
                           String tranDescription, String tranCode, String tranType){
        
        this.accountNumber = accountNumber;
        this.accountName = name;
        this.amount = amount;
        this.mfbCode = mfbCode;
        this.tellerNumber = tellerNumber;
        this.tranDescription = tranDescription;
        this.tranCode = tranCode;
        this.tranType = tranType;
        
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the tranType
     */
    public String getTranType() {
        return tranType;
    }

    /**
     * @param tranType the tranType to set
     */
    public void setTranType(String tranType) {
        this.tranType = tranType;
    }
    
}
