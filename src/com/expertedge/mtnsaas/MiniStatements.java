/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expertedge.mtnsaas;

/**
 *
 * @author Imedia-2
 */
public class MiniStatements {
    
    private String accountNumber;
    private String tranDate; 
    private String branchname;
    private String branchAddress;
    private String valueDate;
    private String narration;
    private String fullName; 
    private String debitOrCredit;
    private String openBalance;
    private String bookBalance;
    private String startDate;
    private String endDate; 
    private String chargeAmount;
    private String tellerNumber;
    private String postingSeq;
    private String retMessage;

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
     * @return the tranDate
     */
    public String getTranDate() {
        return tranDate;
    }

    /**
     * @param tranDate the tranDate to set
     */
    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    /**
     * @return the branchname
     */
    public String getBranchname() {
        return branchname;
    }

    /**
     * @param branchname the branchname to set
     */
    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    /**
     * @return the branchAddress
     */
    public String getBranchAddress() {
        return branchAddress;
    }

    /**
     * @param branchAddress the branchAddress to set
     */
    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    /**
     * @return the valueDate
     */
    public String getValueDate() {
        return valueDate;
    }

    /**
     * @param valueDate the valueDate to set
     */
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * @return the narration
     */
    public String getNarration() {
        return narration;
    }

    /**
     * @param narration the narration to set
     */
    public void setNarration(String narration) {
        this.narration = narration;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the debitOrCredit
     */
    public String getDebitOrCredit() {
        return debitOrCredit;
    }

    /**
     * @param debitOrCredit the debitOrCredit to set
     */
    public void setDebitOrCredit(String debitOrCredit) {
        this.debitOrCredit = debitOrCredit;
    }

    /**
     * @return the openBalance
     */
    public String getOpenBalance() {
        return openBalance;
    }

    /**
     * @param openBalance the openBalance to set
     */
    public void setOpenBalance(String openBalance) {
        this.openBalance = openBalance;
    }

    /**
     * @return the bookBalance
     */
    public String getBookBalance() {
        return bookBalance;
    }

    /**
     * @param bookBalance the bookBalance to set
     */
    public void setBookBalance(String bookBalance) {
        this.bookBalance = bookBalance;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the chargeAmount
     */
    public String getChargeAmount() {
        return chargeAmount;
    }

    /**
     * @param chargeAmount the chargeAmount to set
     */
    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
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
     * @return the postingSeq
     */
    public String getPostingSeq() {
        return postingSeq;
    }

    /**
     * @param postingSeq the postingSeq to set
     */
    public void setPostingSeq(String postingSeq) {
        this.postingSeq = postingSeq;
    }

    /**
     * @return the retMessage
     */
    public String getRetMessage() {
        return retMessage;
    }

    /**
     * @param retMessage the retMessage to set
     */
    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }
    
    public MiniStatements(String accountNumber, String tranDate, String branchname, String branchAddress, String valueDate, String narration,
                          String fullName, String debitOrCredit, String openBalance, String bookBalance, String startDate,
                          String endDate, String chargeAmount, String tellerNumber, String postingSeq, String retMessage){
        this.accountNumber = accountNumber;
        this.tranDate = tranDate;
        this.branchname = branchname;
        this.branchAddress = branchAddress;
        this.valueDate = valueDate;
        this.narration = narration;
        this.fullName = fullName;
        this.debitOrCredit = debitOrCredit;
        this.openBalance = openBalance;
        this.bookBalance = bookBalance;
        this.startDate = startDate;
        this.endDate = endDate;
        this.chargeAmount = chargeAmount;
        this.tellerNumber = tellerNumber;
        this.postingSeq = postingSeq;
        this.retMessage = retMessage;
        
    }
}
