/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expertedge.mtnsaas;

/**
 *
 * @author imedia-2
 */
public class Customers {

    private String name;
    private String acctNumber;
    private String photo;
    private String signature;
    private String currentBal;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Customers(String name, String acctNumber, String photo, String signature, String currentBal) {
        this.name = name;
        this.acctNumber = acctNumber;
        this.photo = photo;
        this.signature = signature;
        this.currentBal = currentBal;

    }

    /**
     * @return the currentBal
     */
    public String getCurrentBal() {
        return currentBal;
    }

    /**
     * @param currentBal the currentBal to set
     */
    public void setCurrentBal(String currentBal) {
        this.currentBal = currentBal;
    }
}
