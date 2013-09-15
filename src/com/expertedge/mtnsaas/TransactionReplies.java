/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expertedge.mtnsaas;

/**
 *
 * @author imedia-2
 */
public class TransactionReplies implements Runnable{

    private String acctNumber;
    private String amount;
    private String tranType;
    private String postSeq;

    /**
     * @return the acctNumber
     */
    public String getAcctNumber() {
        return acctNumber;
    }

    /**
     * @param acctNumber the acctNumber to set
     */
    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
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

    /**
     * @return the postSeq
     */
    public String getPostSeq() {
        return postSeq;
    }

    /**
     * @param postSeq the postSeq to set
     */
    public void setPostSeq(String postSeq) {
        this.postSeq = postSeq;
    }

    public TransactionReplies(String acct, String amount, String tranType, String postSeq) {
        this.acctNumber = acct;
        this.amount = amount;
        this.tranType = tranType;
        this.postSeq = postSeq;
    }

    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
