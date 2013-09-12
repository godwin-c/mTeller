/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expertedge.mtnsaas;

/**
 *
 * @author imedia-2
 */
public class AppUsers {
    private String username;
    private String email;
    private String obJectId;
    private String sessionToken;
    private String emailVerified;
    private String offlineStatus;
    private String mfb_code;
    private String tranPWD;
    private String til_acct;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the obJectId
     */
    public String getObJectId() {
        return obJectId;
    }

    /**
     * @param obJectId the obJectId to set
     */
    public void setObJectId(String obJectId) {
        this.obJectId = obJectId;
    }

    /**
     * @return the sessionToken
     */
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     * @param sessionToken the sessionToken to set
     */
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    /**
     * @return the emailVerified
     */
    public String getEmailVerified() {
        return emailVerified;
    }

    /**
     * @param emailVerified the emailVerified to set
     */
    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }
//AppUsers(username, email, objectID, offlineStatus, mfbCode, tranPassword, tillAcct);
    public AppUsers(String username, String email, String objectId,
                    String offlineStatus, String mfb_code, String tranPWD, String til_acct){
        this.username = username;
        this.email = email;
        this.obJectId = objectId;      
        this.offlineStatus = offlineStatus;
        this.mfb_code = mfb_code;
        this.tranPWD = tranPWD;
        this.til_acct = til_acct;
    }

    /**
     * @return the offlineStatus
     */
    public String getOfflineStatus() {
        return offlineStatus;
    }

    /**
     * @param offlineStatus the offlineStatus to set
     */
    public void setOfflineStatus(String offlineStatus) {
        this.offlineStatus = offlineStatus;
    }

    /**
     * @return the mfb_code
     */
    public String getMfb_code() {
        return mfb_code;
    }

    /**
     * @param mfb_code the mfb_code to set
     */
    public void setMfb_code(String mfb_code) {
        this.mfb_code = mfb_code;
    }

    /**
     * @return the tranPWD
     */
    public String getTranPWD() {
        return tranPWD;
    }

    /**
     * @param tranPWD the tranPWD to set
     */
    public void setTranPWD(String tranPWD) {
        this.tranPWD = tranPWD;
    }

    /**
     * @return the til_acct
     */
    public String getTil_acct() {
        return til_acct;
    }

    /**
     * @param til_acct the til_acct to set
     */
    public void setTil_acct(String til_acct) {
        this.til_acct = til_acct;
    }
}
