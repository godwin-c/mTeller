/**
 * Your application code goes here
 */
package userclasses;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.processing.Result;
import generated.StateMachineBase;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import com.codename1.xml.Element;
import com.expertedge.mtnsaas.AppUsers;
import com.expertedge.mtnsaas.Customers;
import com.expertedge.mtnsaas.GodwinEncrypt;
import com.expertedge.mtnsaas.MFBs;
import com.expertedge.mtnsaas.MiniStatements;
import com.expertedge.mtnsaas.OfflineTransactions;
import com.expertedge.mtnsaas.TransactionReplies;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Agada Godwin C.
 */
public class StateMachine extends StateMachineBase {

    String defaultAdmin;
    String defaultAdminPwd;
    String status;
    boolean answer;
    Hashtable<String, String> app_Settings;
    Hashtable<String, String> phoneAdmin;
    private Vector<Hashtable> MFBsList;
    Vector addAccount = new Vector();
    Hashtable<String, String> OfflineTeller;
    AppUsers appUser;
    MFBs mfb;
    Customers customer;
    private Vector<Hashtable> customersToStore;
    private Vector accountsStored;
    private Vector accountWithErrorReturnCode;
    private Vector accountWithErrorStatus;
    private Result result;
    private Result signUpResponse;
    private Result loginResponse;
    private Result mfbResult;
    private Result logResponse;
    private Result offlineTellerResponse;
    private Hashtable<String, String> customerToStore;
    private String InputParameter;
    private Vector<Hashtable> offlineTransactionsStored;
    OfflineTransactions offlineTransaction;
    private Vector<Hashtable> storedTransactionsNotUploaded;
    private Vector<Hashtable> storedTransactionsUploaded;
    private Vector<Hashtable> storedReplies;
    private EncodedImage photo;
    private EncodedImage signature;
    private Hashtable<String, String> offlineDeposit;
    private String tranAmount;
    private String narration;
    private String tellerNumber;
    private String tranCharge;
    private String instrumentNo;
    private String tellerNo;
    private Vector<Hashtable> allReturns;
    private Hashtable<String, String> returns;
    private MiniStatements miniStatement;
    private boolean credit;
    TransactionReplies transactionReply;

    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }

    /**
     * this method should be used to initialize variables instead of the
     * constructor/class scope to avoid race conditions
     */
    @Override
    protected void initVars(Resources res) {
        defaultAdmin = "mtellerAdmin";
        defaultAdminPwd = "mteller123de";
//        appId = "QLZEMHBvKdSUxtsKIHu1h4mOJG1yv2fLyvQRgLGa";
//        restApiKey = "LQkUG6qCVm72POwSeZJIB3kmZXO5dFoOTElJVnqy";
    }

    /**
     * this method is used to cancel back commands on the forms mentioned The
     * back command was canceled because I didn't want the user to go back to
     * previous form
     */
    @Override
    protected boolean allowBackTo(String formName) {
        if ("Main".equals(formName) || ("OfflineLogin".equals(formName)) || ("OfflineMenu".equals(formName)) || ("OnlineLogin".equals(formName))) {
            return false;

        }

        return true;

    }

//    private String encodePWD(String password) {
//        String text = com.codename1.util.Base64.encode(password.getBytes());
//        //String text = "Q" + password + "4";
//        String pwd = com.codename1.util.Base64.encode(text.getBytes());
//
//        return pwd;
//    }
    /**
     * this method queries the database for all registered MFBs
     *
     */
    public void fetchAllMFBs() {
        InputParameter = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:tem='http://tempuri.org/'>"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<tem:FetchMFBs/>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";

        final ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {
                os.write(InputParameter.getBytes("UTF-8"));
            }
            // **************** Get the status of the connection        

            @Override
            protected void readHeaders(Object connection) throws IOException {
                //status = getHeader(connection, "status");
                //System.out.println("The status of the connection: " + status);
            }
            //*****************

            @Override
            protected void readResponse(InputStream input) throws IOException {

                status = String.valueOf(getResponseCode());

                mfbResult = Result.fromContent(input, Result.XML);

                // System.out.println("All MFBs : " + MFBsList.toString());


            }
        };

        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);

        //String url = "https://api.parse.com/1/classes/MFBs";
        request.setUrl(app_Settings.get("App_URL").toString());
        request.setContentType("text/xml;charset=UTF-8");

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(dlg);

        manager.start();

        manager.addToQueueAndWait(request);
    }

    /**
     * this method is called to register a new Application user
     *
     */
    public void signUp(String tellerID, String password, String email, String tranPWD, String tillAcct, String mfbCode) {

        InputParameter = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:tem='http://tempuri.org/'>"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<tem:MTellerCreateUser>"
                + "<!--Optional:-->"
                + "<tem:MTransaction>"
                + "<!--Optional:-->"
                + "<tem:Username>" + tellerID + "</tem:Username>"
                + "<!--Optional:-->"
                + "<tem:Password>" + password + "</tem:Password>"
                + "<!--Optional:-->"
                + "<tem:Email>" + email + "</tem:Email>"
                + "<!--Optional:-->"
                + "<tem:Mfbcode>" + mfbCode + "</tem:Mfbcode>"
                + "<!--Optional:-->"
                + "<tem:OffLineTeller>" + "0" + "</tem:OffLineTeller>"
                + "<!--Optional:-->"
                + "<tem:TillAcct>" + tillAcct + "</tem:TillAcct>"
                + "<!--Optional:-->"
                + "<tem:TranPwd>" + tranPWD + "</tem:TranPwd>"
                + "</tem:MTransaction>"
                + "</tem:MTellerCreateUser>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";

        //final String json = Result.fromContent(data).toString();

        final ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {
                os.write(InputParameter.getBytes("UTF-8"));
            }

            // **************** Get the status of the connection        
            @Override
            protected void readHeaders(Object connection) throws IOException {
                //status = getHeader(connection, "status");
                // System.out.println("The status of the connection: " + status);
            }
            //*****************

            @Override
            protected void readResponse(InputStream input) throws IOException {

                status = String.valueOf(getResponseCode());

                //status = String.valueOf(getResposeCode());

                signUpResponse = Result.fromContent(input, Result.XML);

            }
        };

        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);


        request.setUrl(app_Settings.get("App_URL").toString());
        //request.setUrl(app_Settings.get("App_URL").toString());
        request.setContentType("text/xml;charset=UTF-8");
        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        //request.setTimeout(1234);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(dlg);

        manager.start();

        manager.addToQueueAndWait(request);
    }

    /**
     * this method is called to login a registered user of the Application
     *
     */
    public void login(String tellerID, String password) {

        InputParameter = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:tem='http://tempuri.org/'>"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<tem:MTellerLoginQuery>"
                + "<!--Optional:-->"
                + "<tem:MLoginQry>"
                + "<!--Optional:-->"
                + "<tem:Username>" + tellerID + "</tem:Username>"
                + "<!--Optional:-->"
                + "<tem:Password>" + password + "</tem:Password>"
                + "</tem:MLoginQry>"
                + "</tem:MTellerLoginQuery>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";

        final ConnectionRequest request = new ConnectionRequest() {
            // ******* Build the request body 
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {
                os.write(InputParameter.getBytes("UTF-8"));
            }

            // **************** Get the status of the connection        
            @Override
            protected void readHeaders(Object connection) throws IOException {
                //status = getHeader(connection, "status");
                // System.out.println("The status of the connection: " + status);
            }
            //*****************

            @Override
            protected void readResponse(InputStream input) throws IOException {
                status = String.valueOf(getResponseCode());
                System.out.println("Status of Login : " + status);

                loginResponse = Result.fromContent(input, Result.XML);


            }
        };

        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };



        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);


        request.setUrl(app_Settings.get("App_URL").toString());
        request.setContentType("text/xml;charset=UTF-8");

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);

        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(dlg);


        manager.start();

        manager.addToQueueAndWait(request);
        //manager.setTimeout(3000);
    }

    /**
     * this method is called to configure a registered user of the Application
     * to perform offline transactions
     *
     */
    private void configureOfflineTeller(String tellerID, String objectID, String mfbCode) {

        InputParameter = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:tem='http://tempuri.org/'>"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<tem:MTellerUpdateUser>"
                + "<!--Optional:-->"
                + "<tem:MTransaction>"
                + "<!--Optional:-->"
                + "<tem:Username>" + tellerID + "</tem:Username>"
                + "<!--Optional:-->"
                + "<tem:OffLineTeller>" + "1" + "</tem:OffLineTeller>"
                + "<!--Optional:-->"
                + "<tem:ObjectID>" + objectID + "</tem:ObjectID>"
                + "<!--Optional:-->"
                + "<tem:MFB_Code>" + mfbCode + "</tem:MFB_Code>"
                + "</tem:MTransaction>"
                + "</tem:MTellerUpdateUser>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";



        final ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {
                os.write(InputParameter.getBytes("UTF-8"));
            }

            // **************** Get the status of the connection        
            @Override
            protected void readHeaders(Object connection) throws IOException {
//                status = getHeader(connection, "status");
//                System.out.println("The status of the connection: " + status);
//                System.out.println(connection.toString());
            }
            //*****************

            @Override
            protected void readResponse(InputStream input) throws IOException {
                status = String.valueOf(getResponseCode());

                offlineTellerResponse = Result.fromContent(input, Result.XML);


            }
        };



        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);

        request.setUrl(app_Settings.get("App_URL").toString());
        request.setContentType("text/xml;charset=UTF-8");

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        //request.setHttpMethod("PUT");

        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(dlg);

        // NetworkManager manager = NetworkManager.getInstance();
        manager.start();
        manager.addToQueueAndWait(request);

    }

    /**
     * this method is called to view a customer's Account details
     *
     */
    private void verifyCustomer(String mfbCode, String accountNumber) {

        InputParameter = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:prim='http://prime.ee.org/' xmlns:tem='http://tempuri.org/'>"
                + " <soapenv:Header/>"
                + "<soapenv:Body>"
                + "<prim:CustomerInfo>"
                + " <!--Optional:-->"
                + "<account_no>" + accountNumber + "</account_no>"
                + "<!--Optional:-->"
                + "<mfb_id>" + mfbCode + "</mfb_id>"
                + "</prim:CustomerInfo>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";



        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Buid Request Body **************************************
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {

                os.write(InputParameter.getBytes("UTF-8"));
            }

            // ************** End Buid Request Body **********************************
//            
            @Override
            protected void readResponse(InputStream input) throws IOException {

                status = String.valueOf(getResponseCode());

                result = Result.fromContent(input, Result.XML);
                //System.out.println("The result "+result.toString());

            }
        };



        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);

        request.setUrl(app_Settings.get("Tran_URL").toString());
        request.setContentType("text/xml;charset=UTF-8");
        //request.addRequestHeader("SOAPAction", "");
        request.addRequestHeader("Authorization", "Bearer " + app_Settings.get("Bearer_Key").toString());
        request.setDuplicateSupported(true);
        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        request.setHttpMethod("POST");


        request.setDisposeOnCompletion(dlg);


        // NetworkManager manager = NetworkManager.getInstance();
        manager.start();
        manager.addToQueueAndWait(request);

    }

    /**
     * this method is called to perform deposit transaction into a customer's
     * Account
     *
     */
    private void performDeposit(String acctNumber, String amount, String mfbCode,
            String tranDesc, String tellerNumber, String tranCode) {


        InputParameter = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:prim='http://prime.ee.org/' xmlns:tem='http://tempuri.org/'>"
                + " <soapenv:Header/>"
                + "<soapenv:Body>"
                + "<prim:CashDeposit>"
                + "<!--Optional:-->"
                + "<cashdep>"
                + " <!--Optional:-->"
                + "<tem:AcctNo>" + acctNumber + "</tem:AcctNo>"
                + "<!--Optional:-->"
                + "<tem:SuspAcctNo>" + appUser.getTil_acct() + "</tem:SuspAcctNo>"
                + "<!--Optional:-->"
                + "<mfb_id>" + mfbCode + "</mfb_id>"
                + "<!--Optional:-->"
                + "<tem:TranAmt>" + amount + "</tem:TranAmt>"
                + "<!--Optional:-->"
                + "<tem:ChargeAcctNo>" + "" + "</tem:ChargeAcctNo>"
                + "<!--Optional:-->"
                + "<tem:TellerNo>" + tellerNumber + "</tem:TellerNo>"
                + "<!--Optional:-->"
                + "<tem:Narration>" + tranDesc + "</tem:Narration>"
                + "<!--Optional:-->"
                + "<tem:RequestID>" + tranCode + "</tem:RequestID>"
                + "<!--Optional:-->"
                + "<tem:TranDateDDMMYYYY>" + this.getCurrentDate() + "</tem:TranDateDDMMYYYY>"
                + "</cashdep>"
                + "</prim:CashDeposit>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";


        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Buid Request Body **************************************
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {

                os.write(InputParameter.getBytes("UTF-8"));
            }

//            // ************** End Buid Request Body **********************************
            @Override
            protected void readResponse(InputStream input) throws IOException {

                status = String.valueOf(getResponseCode());

                result = Result.fromContent(input, Result.XML);
            }
        };



        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);


        request.setUrl(app_Settings.get("Tran_URL").toString());
        request.setContentType("text/xml;charset=UTF-8");
        //request.addRequestHeader("SOAPAction", "");
        request.addRequestHeader("Authorization", "Bearer " + app_Settings.get("Bearer_Key").toString());
        request.setDuplicateSupported(true);
        //request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        request.setDisposeOnCompletion(dlg);


        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();
        manager.addToQueueAndWait(request);
        manager.setTimeout(15000);

    }

    /**
     * this method(though not in use) is called to perform a cheque withdrawal
     * transaction from a customer's Account
     *
     */
    private void doChequeWithdrawal(String acctNum, String mfbId, String amount, String tranDesc,
            String chequeNumber, String tranCharge, String tranCode) {


        InputParameter = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:prim='http://prime.ee.org/' xmlns:tem='http://tempuri.org/'>"
                + " <soapenv:Header/>"
                + "<soapenv:Body>"
                + "<prim:ChequeWithdrawal>"
                + " <!--Optional:-->"
                + "<chqWith>"
                + "<!--Optional:-->"
                + "<tem:AcctNo>" + acctNum + "</tem:AcctNo>"
                + "<!--Optional:-->"
                + "<tem:SuspAcctNo>" + "" + "</tem:SuspAcctNo>"
                + "<!--Optional:-->"
                + "<tem:TranAmt>" + amount + "</tem:TranAmt>"
                + "<!--Optional:-->"
                + "<tem:mfb_id>" + mfbId + "</tem:mfb_id>"
                + "<!--Optional:-->"
                + "<tem:ChargeAcctNo>" + "" + "</tem:ChargeAcctNo>"
                + "<!--Optional:-->"
                + "<tem:Narration>" + tranDesc + "</tem:Narration>"
                + "<!--Optional:-->"
                + "<tem:RequestID>" + tranCode + "</tem:RequestID>"
                + "<!--Optional:-->"
                + "<tem:ChequeNo>" + chequeNumber + "</tem:ChequeNo>"
                + "<!--Optional:-->"
                + "<tem:ChargeAmt>" + tranCharge + "</tem:ChargeAmt>"
                + "<!--Optional:-->"
                + "<tem:TranDateDDMMYYYY>" + this.getCurrentDate() + "</tem:TranDateDDMMYYYY>"
                + "</chqWith>"
                + "</prim:ChequeWithdrawal>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";




        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Buid Request Body **************************************
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {

                os.write(InputParameter.getBytes("UTF-8"));
            }

            // ************** End Buid Request Body **********************************
            @Override
            protected void readResponse(InputStream input) throws IOException {

                status = String.valueOf(getResponseCode());
                result = Result.fromContent(input, Result.XML);



            }
        };


        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);


        request.setUrl(app_Settings.get("Tran_URL").toString());
        request.setContentType("text/xml;charset=UTF-8");
        //request.addRequestHeader("SOAPAction", "");
        request.addRequestHeader("Authorization", "Bearer " + app_Settings.get("Bearer_Key").toString());
        request.setDuplicateSupported(true);
        //request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        request.setDisposeOnCompletion(dlg);


        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();
        manager.addToQueueAndWait(request);


    }

    /**
     * this method is called to assign a back command to the forms that their
     * back commands were not disabled by default
     */
    @Override
    public void back() {
        super.back(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * this method is called to perform withdrawal transaction on customers's
     * account
     *
     */
    private void doWithdrawal(String acctNum, String mfbId, String amount, String tranDesc,
            String tellerNumber, String tranCharge, String tranCode) {


        InputParameter = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:prim='http://prime.ee.org/' xmlns:tem='http://tempuri.org/'>"
                + " <soapenv:Header/>"
                + "<soapenv:Body>"
                + "<prim:CashWithdrawal>"
                + " <!--Optional:-->"
                + "<cashwith>"
                + "<!--Optional:-->"
                + "<tem:AcctNo>" + acctNum + "</tem:AcctNo>"
                + "<!--Optional:-->"
                + "<tem:mfb_id>" + mfbId + "</tem:mfb_id>"
                + "<!--Optional:-->"
                + "<tem:SuspAcctNo>" + "" + "</tem:SuspAcctNo>"
                + "<!--Optional:-->"
                + "<tem:TranAmt>" + amount + "</tem:TranAmt>"
                + "<!--Optional:-->"
                + "<tem:ChargeAcctNo>" + "" + "</tem:ChargeAcctNo>"
                + "<!--Optional:-->"
                + "<tem:TellerNo>" + tellerNumber + "</tem:TellerNo>"
                + "<!--Optional:-->"
                + "<tem:Narration>" + tranDesc + "</tem:Narration>"
                + "<!--Optional:-->"
                + "<tem:RequestID>" + tranCode + "</tem:RequestID>"
                + "<!--Optional:-->"
                + "<tem:ChargeAmt>" + tranCharge + "</tem:ChargeAmt>"
                + "<!--Optional:-->"
                + "<tem:TranDateDDMMYYYY>" + this.getCurrentDate() + "</tem:TranDateDDMMYYYY>"
                + "</cashwith>"
                + "</prim:CashWithdrawal>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";




        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Buid Request Body **************************************
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {

                os.write(InputParameter.getBytes("UTF-8"));
            }

            // ************** End Buid Request Body **********************************
            @Override
            protected void readResponse(InputStream input) throws IOException {

                status = String.valueOf(getResponseCode());
                result = Result.fromContent(input, Result.XML);

            }
        };

        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);


        request.setUrl(app_Settings.get("Tran_URL").toString());
        request.setContentType("text/xml;charset=UTF-8");
        //request.addRequestHeader("SOAPAction", "");
        request.addRequestHeader("Authorization", "Bearer " + app_Settings.get("Bearer_Key").toString());
        request.setDuplicateSupported(true);
        //request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        request.setDisposeOnCompletion(dlg);



        manager.start();
        manager.addToQueueAndWait(request);

    }

    /**
     * this method is called to fetch a customer's mini-statements
     *
     */
    private void fetchMiniStatementFor(String accountNumber, String mfbCode) {


        InputParameter = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:prim='http://prime.ee.org/' xmlns:tem='http://tempuri.org/'>"
                + " <soapenv:Header/>"
                + "<soapenv:Body>"
                + "<prim:MiniStatements>"
                + " <!--Optional:-->"
                + "<input>"
                + "<!--Optional:-->"
                + "<tem:AcctNo>" + accountNumber + "</tem:AcctNo>"
                + "<!--Optional:-->"
                + "<tem:LastMonthValue>" + "" + "</tem:LastMonthValue>"
                + "<!--Optional:-->"
                //+ "<tem:mfb_id>" + mfbCode + "</tem:mfb_id>"
                + "</input>"
                + "</prim:MiniStatements>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";




        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Buid Request Body **************************************
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {

                os.write(InputParameter.getBytes("UTF-8"));
            }

            // ************** End Buid Request Body **********************************
            @Override
            protected void readResponse(InputStream input) throws IOException {

                status = String.valueOf(getResponseCode());
                result = Result.fromContent(input, Result.XML);

            }
        };


        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);

        request.setUrl(app_Settings.get("Tran_URL").toString());
        request.setContentType("text/xml;charset=UTF-8");
        //request.addRequestHeader("SOAPAction", "");
        request.addRequestHeader("Authorization", "Bearer " + app_Settings.get("Bearer_Key").toString());
        request.setDuplicateSupported(true);
        //request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        request.setDisposeOnCompletion(dlg);

        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();
        manager.addToQueueAndWait(request);


    }

    /**
     * After performing any transaction, this method logs the transaction
     * details to the database for records
     *
     */
    private void uploadTranReplies(String acctNumber, String amount, String tranType,
            String post_seq) {


        InputParameter = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:tem='http://tempuri.org/'>"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<tem:MTellerTransResponse>"
                + "<!--Optional:-->"
                + "<tem:MCreateMFB>"
                + "<!--Optional:-->"
                + "<tem:AcctNo>" + acctNumber + "</tem:AcctNo>"
                + "<!--Optional:-->"
                + "<tem:TranAmt>" + amount + "</tem:TranAmt>"
                + "<!--Optional:-->"
                + "<tem:PostSeq>" + post_seq + "</tem:PostSeq>"
                + "<!--Optional:-->"
                + "<tem:TranType>" + tranType + "</tem:TranType>"
                + "</tem:MCreateMFB>"
                + "</tem:MTellerTransResponse>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";

        //final String json = Result.fromContent(data).toString();

        final ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {
                os.write(InputParameter.getBytes("UTF-8"));
            }

            // **************** Get the status of the connection        
            @Override
            protected void readHeaders(Object connection) throws IOException {
                //status = getHeader(connection, "status");
                //System.out.println("The status of the connection for upload: " + status);
            }
            //*****************

            @Override
            protected void readResponse(InputStream input) throws IOException {

                status = String.valueOf(getResponseCode());

                logResponse = Result.fromContent(input, Result.XML);


            }
        };

        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);

        //String url = "https://api.parse.com/1/classes/Transaction_Replies";
        request.setUrl(app_Settings.get("App_URL").toString());
        request.setContentType("text/xml;charset=UTF-8");

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        //request.setTimeout(1234);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(dlg);


        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();

        manager.addToQueueAndWait(request);

    }

    /**
     * This is where I generate the request ID I send along with other details
     * while performing deposit and withdrawal transactions
     */
    private String getRequestID() {

        java.util.Calendar cal = java.util.Calendar.getInstance();
        //plus 1 for month cos java starts January at 0 for Gregorian calendar...see doc for Calendar.MONTH
        String currentDate = String.valueOf(cal.get(java.util.Calendar.DAY_OF_MONTH)) + "" + String.valueOf(cal.get(java.util.Calendar.MONTH) + 1) + "" + String.valueOf(cal.get(java.util.Calendar.YEAR));

        int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
        int min = cal.get(java.util.Calendar.MINUTE);
        int sec = cal.get(java.util.Calendar.SECOND);
        int millsec = cal.get(java.util.Calendar.MILLISECOND);

        String currentTime = String.valueOf(hour) + "" + String.valueOf(min) + "" + String.valueOf(sec) + "" + String.valueOf(millsec);


        return currentDate + "" + currentTime;


    }

    /**
     * This is the method I use in getting the system current date and time
     */
    private String getCurrentDate() {


        java.util.Calendar cal = java.util.Calendar.getInstance();
        //plus 1 for month cos java starts January at 0 for Gregorian calendar...see doc for Calendar.MONTH
        //String currentDate = String.valueOf(cal.get(java.util.Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(cal.get(java.util.Calendar.MONTH) + 1) + "/" + String.valueOf(cal.get(java.util.Calendar.YEAR));
        String currentDate = String.valueOf(cal.get(java.util.Calendar.DAY_OF_MONTH)) + String.valueOf(cal.get(java.util.Calendar.MONTH) + 1) + String.valueOf(cal.get(java.util.Calendar.YEAR));

        int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
        int min = cal.get(java.util.Calendar.MINUTE);
        int sec = cal.get(java.util.Calendar.SECOND);
        int millsec = cal.get(java.util.Calendar.MILLISECOND);

        // String currentTime = String.valueOf(hour) + ":" + String.valueOf(min) + ":" + String.valueOf(sec) + "." + String.valueOf(millsec);


        return currentDate; //+ " " + currentTime;

    }

    /**
     * When the user clicks on "Login" button on the "Main" form
     */
    @Override
    protected void onMain_UserLoginAction(Component c, ActionEvent event) {

        //("AppUserLogin", null);
        if (Storage.getInstance().exists("Application_Settings")) {
            app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
            //showForm("OnlineLogin", null);
            showForm("AppUserLogin", null);
        } else {
            Dialog.show("Sorry", "You need to provide Application Settings before Login", "OK", null);
        }

    }

    /**
     * When the user clicks on "GoOffline" button on the "Main" form
     */
    @Override
    protected void onMain_UserGoOfflineAction(Component c, ActionEvent event) {


        if (Storage.getInstance().exists("Application_Settings")) {
            app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
            //showForm("OnlineLogin", null);
            showForm("AppUserLoginOffline", null);
        } else {
            Dialog.show("Sorry", "You need to provide Application Settings before Login", "OK", null);
        }
    }

    /**
     * After the user must have provided login details in the login form, this
     * method is used to collet the information and login the user
     */
    @Override
    protected void onOnlineLogin_LoginUserAction(Component c, ActionEvent event) {

        String username = findUsernameTextField(c.getComponentForm()).getText();
        String password = findPasswordTextField(c.getComponentForm()).getText();

        if (("".equals(username)) || ("".equals(password))) {
            Dialog.show("Missing", "Please provide Username and Password", "OK", null);
        } else {
            GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
            String myEncryptedPassword = encryptMyPassword.asHex();
            login(username, myEncryptedPassword);

            if (status == null || !(status.equals("200"))) {

                Dialog.show("", "unable to login", "OK", null);


            } else {
                status = "";

                String respCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retval");
                String respMsg = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retmsg");

                if ("1".equals(respCode)) {
                    String email = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Email");
                    String mfbCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Mfbcode");
                    String tranPassword = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TranPwd");
                    String tillAcct = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TillAcct");
                    String objectID = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/ObjectID");
                    String offlineStatus = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/OfflineTeller");

                    appUser = new AppUsers(username, email, objectID, offlineStatus, mfbCode, tranPassword, tillAcct);
                    //

                    Dialog.show("Logged In", appUser.getUsername().toUpperCase() + "  welcome", "OK", null);
                    //status = "";
                    showForm("TransactionMenu", null);

                } else {
                    Dialog.show("", respMsg, "OK", null);
                }
            }

        }
    }

    /**
     * In the Transaction menu, when the user clicks on "Deposit" it calls this
     * action
     */
    @Override
    protected void onTransactionMenu_DepositOnlineAction(Component c, ActionEvent event) {

        showForm("DepositAcctNumber", null);

    }

    /**
     * When there are more MFBs, this is a List model for all MFBs fetched
     */
    @Override
    protected boolean initListModelWithdrawaBankMultiList(List cmp) {
        cmp.setModel(new DefaultListModel(MFBsList));
        return true;
    }

    /**
     * When the user clicks on the Withdrawal button on the Transaction menu
     */
    @Override
    protected void onTransactionMenu_WithdrawOnlineAction(Component c, ActionEvent event) {

        showForm("WithdrawalAcctNumber", null);

//        if (MFBsList == null || (MFBsList.isEmpty())) {
//            fetchAllMFBs();
//            if ("200".equals(status)) {
//                status = "";
//
//                if (mfbResult == null || mfbResult.toString().equals("")) {
//                    Dialog.show("Oh dear", "no MFBs were fetched", "OK", null);
//                } else {
//
//                    MFBsList = new Vector<Hashtable>();
//
//                    for (Object o : mfbResult.getAsArray("/soap:Envelope/soap:Body/FetchMFBsResponse/FetchMFBsResult/FetchMFBs")) {
//                        Result lineitem = Result.fromContent(((Element) o).getChildAt(0));
//
//                        returns = new Hashtable<String, String>();
//
//                        returns.put("mfb_id", lineitem.getAsString("MFBID"));
//                        returns.put("mfb_name", lineitem.getAsString("MFBName"));
//
//                        MFBsList.add(returns);
//
//                    }
//
//                    showForm("WithdrawalMFBs", null);
//                }
//
//
//            } else {
//                Dialog.show("", "Please check your internet", "OK", null);
//            }
//        } else {
//            showForm("WithdrawalMFBs", null);
//        }
    }

    @Override
    protected boolean initListModelMiniStatMFBsMultiList(List cmp) {
        cmp.setModel(new DefaultListModel(MFBsList));
        return true;
    }

    /**
     * When the user clicks on the Mini-statement button on the transaction menu
     */
    @Override
    protected void onTransactionMenu_MiniStatementAction(Component c, ActionEvent event) {

        showForm("MiniStatForm", null);

//        if (MFBsList == null || MFBsList.isEmpty()) {
//            fetchAllMFBs();
//            if ("200".equals(status)) {
//                status = "";
//
//                if (mfbResult == null || mfbResult.toString().equals("")) {
//                    Dialog.show("Oh dear", "no MFBs were fetched", "OK", null);
//                } else {
//
//                    MFBsList = new Vector<Hashtable>();
//
//                    for (Object o : mfbResult.getAsArray("/soap:Envelope/soap:Body/FetchMFBsResponse/FetchMFBsResult/FetchMFBs")) {
//                        Result lineitem = Result.fromContent(((Element) o).getChildAt(0));
//
//                        returns = new Hashtable<String, String>();
//
//                        returns.put("mfb_id", lineitem.getAsString("MFBID"));
//                        returns.put("mfb_name", lineitem.getAsString("MFBName"));
//
//                        MFBsList.add(returns);
//
//                    }
//
//                    showForm("MiniStatMFBs", null);
//                }
//
//            } else {
//                Dialog.show("", "Please check your internet", "OK", null);
//            }
//        } else {
//            showForm("MiniStatMFBs", null);
//        }
    }

    /**
     * When the user clicks on the balance inquiry Button on the transaction
     * menu
     */
    @Override
    protected void onTransactionMenu_BalanceInquiryAction(Component c, ActionEvent event) {

        showForm("BalInquiryAcctNumber", null);

//        if (MFBsList == null || MFBsList.isEmpty()) {
//            fetchAllMFBs();
//            if ("200".equals(status)) {
//                status = "";
//
//                if (mfbResult == null || mfbResult.toString().equals("")) {
//                    Dialog.show("Oh dear", "no MFBs were fetched", "OK", null);
//                } else {
//
//                    MFBsList = new Vector<Hashtable>();
//
//                    for (Object o : mfbResult.getAsArray("/soap:envelope/soap:body/FetchMFBsResponse/FetchMFBsResult/FetchMFBs")) {
//                        Result lineitem = Result.fromContent(((Element) o).getChildAt(0));
//
//                        returns = new Hashtable<String, String>();
//
//                        returns.put("mfb_id", lineitem.getAsString("MFBID"));
//                        returns.put("mfb_name", lineitem.getAsString("MFBName"));
//
//                        MFBsList.add(returns);
//
//                    }
//
//                    showForm("BalInquiryMFBs", null);
//                }
//
//            } else {
//                Dialog.show("", "Please check your internet", "OK", null);
//            }
//        } else {
//            showForm("BalInquiryMFBs", null);
//        }
    }

    @Override
    protected boolean initListModelBalInquiryMFBMultiList(List cmp) {
        cmp.setModel(new DefaultListModel(MFBsList));
        return true;
    }

    /**
     * When the user clicks on the "Create User" button on the "Main" form.
     */
    @Override
    protected void onMain_CreateNewUserAction(Component c, ActionEvent event) {

        showForm("NewAppUser", null);
    }

    /**
     * After the user must have provided all details in the "New App User" form,
     * this method is used to collect the information and Create the user
     */
    @Override
    protected void onNewAppUser_SignUpUserAction(Component c, ActionEvent event) {

        String username = findNewUserTellerIDTextField(c.getComponentForm()).getText();
        String password = findNewUserPasswordTextField(c.getComponentForm()).getText();
        String email = findNewUserEmailTextField(c.getComponentForm()).getText();
        String tranPWD = findTranPasswordTextField(c.getComponentForm()).getText();
        String tillAcct = findTellerTilTextField(c.getComponentForm()).getText();
        String bankCode = findBankCodeTextField(c.getComponentForm()).getText();

        if (("".equals(bankCode)) || ("".equals(username)) || ("".equals(password)) || ("".equals(tillAcct)) || ("".equals(email)) || ("".equals(tranPWD))) {
            Dialog.show("", "all fields are required", "OK", null);
        } else {

            if ((password.length() <= 6) || (tranPWD.length() <= 6)) {
                Dialog.show("", "passwords should be more than six(6) characters", "OK", null);
            } else {

                GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                String myEncryptedPassword = encryptMyPassword.asHex();

                GodwinEncrypt encryptMyTranPassword = new GodwinEncrypt(tranPWD);
                String myEncryptedTranPassword = encryptMyTranPassword.asHex();

                signUp(username, myEncryptedPassword, email, myEncryptedTranPassword, tillAcct, bankCode);

                if ((status == null) || !("200".equals(status))) {
                    Dialog.show("Oh Dear", "Could not create Teller, please check your internet", "OK", null);
                } else {

                    status = "";
                    //String returncode = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retval");
                    String respCode = signUpResponse.getAsString("/soap:Envelope/soap:Body/MTellerCreateUserResponse/MTellerCreateUserResult/Retval");
                    String respMsg = signUpResponse.getAsString("/soap:Envelope/soap:Body/MTellerCreateUserResponse/MTellerCreateUserResult/Retmsg");
                    System.out.println(respCode);
                    System.out.println(respMsg);

                    if ("1".equals(respCode)) {
                        Dialog.show("Success", "Teller has been Created, please try and LOGIN", "OK", null);
                        showForm("Main", null);
                    } else {
                        Dialog.show("Oh Dear!!!", respMsg, "OK", null);
                        //showForm("Main", null);
                    }
//                    try {
//                        
//                    } catch (Exception e) {
//                        Dialog.show("OOPS!!!", "request may have been cancelled", "OK", null);
//                    }
                }
            }
        }
    }

    /**
     * Cancel action on sign up form
     */
    @Override
    protected void onNewAppUser_CancelSignUpAction(Component c, ActionEvent event) {

        showForm("Main", null);
    }

    /**
     * cancel action on "Login"form
     */
    @Override
    protected void onOnlineLogin_CancelAction(Component c, ActionEvent event) {

        showForm("Main", null);
    }

    /**
     * Not in use
     */
    @Override
    protected void onBalInquiryMFBs_BalInquiryMFBMultiListAction(Component c, ActionEvent event) {

        List l = (List) c;
        Hashtable h = (Hashtable) l.getSelectedItem();

        mfb = new MFBs((String) h.get("mfb_name"), (String) h.get("mfb_id"));

        showForm("BalInquiryAcctNumber", null);
    }

    /**
     * This method is called before "Settings" form is displayed, to set the
     * things to display
     */
    @Override
    protected void beforeSettings(Form f) {

        f.setScrollable(false);

        Command bac = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("Main", null);
            }
        };

        f.getMenuBar().addCommand(bac);
        f.setBackCommand(bac);

        if (Storage.getInstance().exists("Application_Settings")) {
            try {
                app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");

                findTranURLTextArea(f).setText(app_Settings.get("Tran_URL").toString());
                findAppURLTextArea(f).setText(app_Settings.get("App_URL").toString());
                findBearerKeyTextArea(f).setText(app_Settings.get("Bearer_Key").toString());
            } catch (Exception e) {
                Dialog.show("Error occured", "'" + e.getMessage() + "'", "OK", null);
                //back();
            }
        }
        Container c = findContainer1(f);
        c.removeComponent(findAdminSettingsContainer(c));
    }

    /**
     * this method is called when the "Admin Settings" button is clicked on the
     * settings form user
     */
    @Override
    protected void onSettings_AdminSettingsAction(Component c, ActionEvent event) {


        Container c1 = findContainer1(c.getComponentForm());
        c1.removeAll();
        //c1.removeComponent(findAppSettingsContainer(c1));
        c1.addComponent(findAdminSettingsContainer(c1));
        c.getComponentForm().revalidate();//animateLayout(10000);//ateLayoutFade(10000, 50);//revalidate();
    }

    /**
     * This method is called when "App settings" is clicked on the "settings"
     * form
     */
    @Override
    protected void onSettings_AppSettingsAction(Component c, ActionEvent event) {


        Container c1 = findContainer1(c.getComponentForm());
        c1.removeAll();
        // c1.removeComponent(findAdminSettingsContainer(c1));
        c1.addComponent(findAppSettingsContainer(c1));
        c.getComponentForm().animateLayout(10000);//imateLayoutFadeAndWait(10000, 50);//revalidate();
    }
//mtellerAdmin mteller123de

    /**
     * This is called when the "Settings" button is clicked on the "Main" form
     */
    @Override
    protected void onMain_SettingsAction(Component c, ActionEvent event) {

        showForm("SettingLogin", null);
    }

    /**
     * When the Admin provides the Admin name and password, this is the action
     * of the Login Button
     */
    @Override
    protected void onSettingsLogin_AdminLoginAction(Component c, ActionEvent event) {

        String name = findAdminNameTextField(c.getComponentForm()).getText();
        String password = findAdminPasswordTextField(c.getComponentForm()).getText();

        if ((name.equals(defaultAdmin)) && (password.equals(defaultAdminPwd))) {
            showForm("Settings", null);
        } else {
            if (Storage.getInstance().exists("phone_admin")) {
                phoneAdmin = (Hashtable<String, String>) Storage.getInstance().readObject("phone_admin");

                if (((phoneAdmin.get("admin_name").toString()).equals(name)) && ((phoneAdmin.get("admin_password").toString()).equals(password))) {
                    showForm("Settings", null);
                } else {
                    Dialog.show("", "Wrong Admin name and password combination", "OK", null);
                }
            } else {
                Dialog.show("", "Wrong Admin name and password combination", "OK", null);
            }
        }
    }

    /**
     * Cancel action on Admin Login
     */
    @Override
    protected void onSettingsLogin_AdminCancelAction(Component c, ActionEvent event) {
        showForm("Main", null);
    }

    /**
     * this method is called to save the application settings provided by the
     * admin
     */
    @Override
    protected void onSettings_SaveAppSettingsAction(Component c, ActionEvent event) {

        final String appURL = findTranURLTextArea(c.getComponentForm()).getText();
        final String appMainURL = findAppURLTextArea(c.getComponentForm()).getText();
        final String bearer = findBearerKeyTextArea(c.getComponentForm()).getText();

        if (("".equals(appURL)) || ("".equals(appMainURL)) || ("".equals(bearer))) {
            Dialog.show("", "please enter all URLs", "OK", null);
        } else {
            Hashtable h = new Hashtable();
            h.put("Tran_URL", appURL);
            h.put("App_URL", appMainURL);
            h.put("Bearer_Key", bearer);
            try {
                Storage.getInstance().writeObject("Application_Settings", h);
                Dialog.show("", "Successfully Saved", "OK", null);

                app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
            } catch (Exception e) {
                Dialog.show("Failed", e.getMessage(), "OK", null);
            }
        }

    }

    /**
     * cancel action on the settings page
     */
    @Override
    protected void onSettings_CancelAppSettingsAction(Component c, ActionEvent event) {
        showForm("Main", null);

    }

    /**
     * Action on "Create Admin" button on the "settings" form
     */
    @Override
    protected void onSettings_CreateAdminAction(Component c, ActionEvent event) {
        showForm("AdminCreateName", null);
    }

    /**
     * action on "Configure Offline teller" on the settings form
     */
    @Override
    protected void onSettings_ConfigureOfflineTellerAction(Component c, ActionEvent event) {
        showForm("OfflineTellerCreate", null);
    }

    /**
     * Back button action on Settings form
     */
    @Override
    protected void onSettings_BackToMainAction(Component c, ActionEvent event) {
        showForm("Main", null);
    }

    /**
     * This is the action on "Save Admin" on the settings form
     */
    @Override
    protected void onAdminCreateName_SaveAdminAction(Component c, ActionEvent event) {

        String name = findNewAdminNameTextField(c.getComponentForm()).getText();
        String password = findNewAdminPasswordTextField(c.getComponentForm()).getText();
        if (("".equals(name)) || ("".equals(password))) {
            Dialog.show("", "both fields are required", "OK", null);
        } else {
            if ((password.length()) <= 6) {
                Dialog.show("", "password shoulb be more than \n'6' characters", "OK", null);
            } else {
                phoneAdmin = new Hashtable<String, String>();
                phoneAdmin.put("admin_name", name);
                phoneAdmin.put("admin_password", password);

                //phoneAdmin = (Hashtable<String, String>) Storage.getInstance().readObject("phone_admin");
                try {
                    Storage.getInstance().writeObject("phone_admin", phoneAdmin);//readObject("phone_admin");
                    Dialog.show("Success", "Admin Created", "OK", null);

                } catch (Exception e) {
                    Dialog.show("OOPS!!", e.getMessage(), "OK", null);
                }
            }
        }
    }

    /**
     * A method called before the form to create An Admin
     */
    @Override
    protected void beforeAdminCreateName(final Form f) {

//        Command home = new Command("Home") {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//
//                showForm("TransactionMenu", null);
//            }
//        };

        Command create = new Command("Create") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                String name = findNewAdminNameTextField(f).getText();
                String password = findNewAdminPasswordTextField(f).getText();
                if (("".equals(name)) || ("".equals(password))) {
                    Dialog.show("", "both fields are required", "OK", null);
                } else {
                    if ((password.length()) <= 6) {
                        Dialog.show("", "password shoulb be more than \n'6' characters", "OK", null);
                    } else {
                        phoneAdmin = new Hashtable<String, String>();
                        phoneAdmin.put("admin_name", name);
                        phoneAdmin.put("admin_password", password);

                        //phoneAdmin = (Hashtable<String, String>) Storage.getInstance().readObject("phone_admin");
                        try {
                            Storage.getInstance().writeObject("phone_admin", phoneAdmin);//readObject("phone_admin");
                            Dialog.show("Success", "Admin Created", "OK", null);

                        } catch (Exception e) {
                            Dialog.show("OOPS!!", e.getMessage(), "OK", null);
                        }
                    }
                }
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("Settings", null);
            }
        };

        //f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(create);
        f.getMenuBar().addCommand(cancel);

        if (Storage.getInstance().exists("phone_admin")) {
            phoneAdmin = (Hashtable<String, String>) Storage.getInstance().readObject("phone_admin");
            findNewAdminNameTextField(f).setText(phoneAdmin.get("admin_name"));
            findNewAdminPasswordTextField(f).setText(phoneAdmin.get("admin_password"));
        }
    }

    /**
     * cancel action on the "create admin" form
     */
    @Override
    protected void onAdminCreateName_CancelAddAdminAction(Component c, ActionEvent event) {
        showForm("Settings", null);
        //back();
    }

    /**
     * This is a method that gets all information needed to configure an offline
     * teller and call a method to first check if the user exists before
     * configuring the user for offline transactions.
     */
    @Override
    protected void onOfflineTellerCreate_AddOfflineTellerAction(Component c, ActionEvent event) {

        String name = findNewOfflineTellerTextField(c.getComponentForm()).getText();
        String password = findNewOfflineTellerPWDTextField(c.getComponentForm()).getText();
        String mfbCode = findMfbCodeTextField(c.getComponentForm()).getText();
        if (("".equals(name)) || ("".equals(password)) || ("".equals(mfbCode))) {
            Dialog.show("", "all fields are required", "OK", null);
        } else {
//            if ((password.length()) <= 6) {
//                Dialog.show("", "password shoulb be more than \n'6' characters", "OK", null);
//            } else {

            GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
            String myEncryptedPassword = encryptMyPassword.asHex();

            login(name, myEncryptedPassword);

            if (status == null || !(status.equals("200"))) {

                Dialog.show("Oh dear", "we could not check the user. please ensure the following: \n"
                        + "1. are you a registered mTeller user? \n"
                        + "2. have you typed your username and password correctly?\n"
                        + "3. is your phone connected to the internet?", ""
                        + "OK", null);
            } else {
                status = "";

                String respCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retval");
                String respMsg = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retmsg");

                if ("1".equals(respCode)) {
                    String email = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Email");
                    String mfbCode2 = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Mfbcode");
                    String tranPassword = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TranPwd");
                    String tillAcct = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TillAcct");
                    String objectID = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/ObjectID");
                    String offlineStatus = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/OfflineTeller");

                    appUser = new AppUsers(name, email, objectID, offlineStatus, mfbCode2, tranPassword, tillAcct);
                    //

//                            Dialog.show("Logged In", appUser.getUsername().toUpperCase() + "  welcome", "OK", null);
//                            //status = "";
//                            showForm("TransactionMenu", null);
                    configureOfflineTeller(appUser.getUsername(), appUser.getObJectId(), mfbCode);

                    if ((status == null) || (!status.equals("200"))) {
                        Dialog.show("Oh dear", "you may not be connected to the internet", "OK", null);
                    } else {
                        //try{
                        //;
                        status = "";
                        String returncode = offlineTellerResponse.getAsString("/soap:Envelope/soap:Body/MTellerUpdateUserResponse/MTellerUpdateUserResult/Retval");
                        String returncodemsg = offlineTellerResponse.getAsString("/soap:Envelope/soap:Body/MTellerUpdateUserResponse/MTellerUpdateUserResult/Retmsg");

                        if ("1".equals(returncode)) {
                            //repliesUploaded.add(hashtable);
                            Dialog dlg = new Dialog("Offline teller");
                            dlg.addComponent(new Label(returncodemsg));
                            dlg.setTimeout(1000);
                            dlg.show();
                            back();
                        } else {
                            Dialog.show("", returncodemsg, "OK", null);
                        }


                        // back();

                    }

                } else {
                    Dialog.show("", respMsg, "OK", null);
                }


                //status = "";

            }
            //}

//            login(name, encodePWD(password));
//
//            if (status == null || !(status.equals("200"))) {
//
//                Dialog.show("Oh dear", "we could not check the user. please ensure the following: \n"
//                        + "1. are you a registered mTeller user? \n"
//                        + "2. have you typed your username and password correctly?\n"
//                        + "3. is your phone connected to the internet?", ""
//                        + "OK", null);
//            } else {
//                status = "";
//                try {
//                    configureOfflineTeller(appUser.getUsername(), appUser.getObJectId(), mfbCode);
//
//                    if ((status == null) || (!status.equals("200"))) {
//                        Dialog.show("Oh dear", "you may not be connected to the internet", "OK", null);
//                    } else {
//                        //try{
//                        Dialog.show("", "Succesfully created", "OK", null);
//                        back();
//
//                    }
//
//                } catch (Exception e) {
//                    Dialog.show("OOPS!!!", "request may have been cancelled", "OK", null);
//                }
//            }
            //}
        }
    }

    /**
     * cancel action on Create offline teller form
     */
    @Override
    protected void onOfflineTellerCreate_CancelOfflineTellerAction(Component c, ActionEvent event) {
        back();
    }

    /**
     * Method called to delete an existing offline teller on the device
     */
    @Override
    protected void onSettings_ChangeOfflineTellerAction(Component c, ActionEvent event) {

        Command[] cmds = new Command[2];
        cmds[0] = new Command("yes") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Storage.getInstance().deleteStorageFile("OfflineTeller");
                    Dialog.show("Deleted", "You can now create a new Teller", "OK", null);
                    back();
                } catch (Exception e) {
                    Dialog.show("Failed", e.getMessage(), "OK", null);
                    //back();
                }
            }
        };
        cmds[1] = new Command("no") {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        };


        TextArea area = new TextArea();
        area.setUIID("VKBtooltip");
        area.setEditable(false);
        area.setText("Delete Offline Teller?");
        Dialog.show("Confirm", area, cmds);
    }

    /**
     * cancel action on "Offline login" form
     */
    @Override
    protected void onOfflineLogin_CancelAction(Component c, ActionEvent event) {

        showForm("Main", null);
    }

    /**
     * The method that collects all the credentials for the user to go offline,
     * it first checks if the user has gone offline before and then downloads
     * the user's credentials if not
     */
    @Override
    protected void onOfflineLogin_LoginOfflineAction(Component c, ActionEvent event) {
        app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
        String name = findOfflineUsernameTextField(c.getComponentForm()).getText();
        String password = findOfflinePasswordTextField(c.getComponentForm()).getText();
        if (("".equals(name)) || ("".equals(password))) {
            Dialog.show("Sorry", "both fields are required to continue", "OK", null);
        } else {
            if (Storage.getInstance().exists("OfflineTeller")) {
                //Storage.getInstance().readObject(name)
                try {
                    OfflineTeller = (Hashtable<String, String>) Storage.getInstance().readObject("OfflineTeller");
                } catch (Exception e) {
                    Dialog.show("Error", e.getMessage(), "OK", null);

                }
                GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                String myEncryptedPassword = encryptMyPassword.asHex();

                if ((name.equals(OfflineTeller.get("teller_ID").toString())) && ((myEncryptedPassword).equals(OfflineTeller.get("password").toString()))) {
                    appUser = new AppUsers(OfflineTeller.get("teller_ID").toString(), OfflineTeller.get("email").toString(), OfflineTeller.get("object_id").toString(), OfflineTeller.get("offline_status").toString(), OfflineTeller.get("mfb_code").toString(), OfflineTeller.get("password").toString(), OfflineTeller.get("til_account").toString());
                    showForm("OfflineMenu", null);
                } else {
                    Dialog.show("Oh dear!", "invalid login credentials", "OK", null);
                }
            } else {
                Dialog.show("First time Login", "about to perform a check online", "OK", null);

                GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                String myEncryptedPassword = encryptMyPassword.asHex();

                login(name, myEncryptedPassword);

                if (status == null || !(status.equals("200"))) {

                    Dialog.show("Oh dear", "could not perform check. please ensure: \n"
                            + "1. are you a registered mTeller user? \n"
                            + "2. have you typed your username and password correctly?\n"
                            + "3. do you have internet on your device?", "OK", null);


                } else {
                    status = "";

                    String respCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retval");
                    String respMsg = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retmsg");

                    if ("1".equals(respCode)) {
                        String email = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Email");
                        String mfbCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Mfbcode");
                        String tranPassword = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TranPwd");
                        String tillAcct = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TillAcct");
                        String objectID = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/ObjectID");
                        String offlineStatus = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/OfflineTeller");

                        if (offlineStatus.equals("0")) {
                            Dialog.show("Sorry", "You are not configured to perform offline Transactions, "
                                    + "please contact your Administrator", "OK", null);
                        } else {

                            appUser = new AppUsers(name, email, objectID, offlineStatus, mfbCode, tranPassword, tillAcct);

                            OfflineTeller = new Hashtable<String, String>();
                            OfflineTeller.put("teller_ID", appUser.getUsername());
                            OfflineTeller.put("mfb_code", appUser.getMfb_code());
                            OfflineTeller.put("til_account", appUser.getTil_acct());
                            OfflineTeller.put("email", appUser.getEmail());
                            OfflineTeller.put("password", myEncryptedPassword);
                            //OfflineTeller.put("email_verified", appUser.getEmailVerified());
                            //OfflineTeller.put("session_string", appUser.getSessionToken());
                            OfflineTeller.put("mfb_code", appUser.getMfb_code());
                            OfflineTeller.put("offline_status", appUser.getOfflineStatus());
                            OfflineTeller.put("object_id", appUser.getObJectId());

                            try {
                                Storage.getInstance().writeObject("OfflineTeller", OfflineTeller);
                                Dialog.show("", "Login success", "OK", null);
                                OfflineTeller = (Hashtable<String, String>) Storage.getInstance().readObject("OfflineTeller");
                                showForm("OfflineMenu", null);
                            } catch (Exception e) {
                                Dialog.show("Error", e.getMessage(), "OK", null);
                                back();
                            }

                        }

                        //


                    } else {
                        Dialog.show("", respMsg, "OK", null);
                    }
                }


//                if ((status == null) || !(status.equals("200 OK")) || (appUser == null)) {
//
//                    Dialog.show("Oh dear", "could not perform check. please ensure: \n"
//                            + "1. are you a registered mTeller user? \n"
//                            + "2. have you typed your username and password correctly?\n"
//                            + "3. do you have internet on your device?", "OK", null);
//                } else {
//                    status = "";
                //try {
//                    if (appUser.getOfflineStatus().equals("false")) {
//                        Dialog.show("Sorry", "You are not configured to perform offline Transactions, "
//                                + "please contact your Administrator", "OK", null);
//                    } else {
//                        OfflineTeller = new Hashtable<String, String>();
//                        OfflineTeller.put("teller_ID", appUser.getUsername());
//                        OfflineTeller.put("mfb_code", appUser.getMfb_code());
//                        OfflineTeller.put("til_account", appUser.getTil_acct());
//                        OfflineTeller.put("email", appUser.getEmail());
//                        OfflineTeller.put("password", this.encodePWD(password));
//                        OfflineTeller.put("email_verified", appUser.getEmailVerified());
//                        OfflineTeller.put("session_string", appUser.getSessionToken());
//                        OfflineTeller.put("mfb_code", appUser.getMfb_code());
//                        OfflineTeller.put("offline_status", appUser.getOfflineStatus());
//                        OfflineTeller.put("object_id", appUser.getObJectId());
////appUser = new AppUsers(username, email, objectId, sessionString, emailVerified, offlineStatus, code, tranPWD, til_acct);
//                        try {
//                            Storage.getInstance().writeObject("OfflineTeller", OfflineTeller);
//                            Dialog.show("", "Login success", "OK", null);
//                            OfflineTeller = (Hashtable<String, String>) Storage.getInstance().readObject("OfflineTeller");
//                            showForm("OfflineMenu", null);
//                        } catch (Exception e) {
//                            Dialog.show("Error", e.getMessage(), "OK", null);
//                            back();
//                        }
//                    }

//                    } catch (Exception e) {
//                        Dialog.show("OOPS!!!", "request may have been cancelled", "OK", null);
//                    }
                //}
            }
        }
    }

    /**
     * the action on the "plus image" or rather, "add account" in the fetch
     * customers for offline transaction
     */
    @Override
    protected void onAccountToPullInfo_AddAccountAction(Component c, ActionEvent event) {

        String acctnumber = findAcctNumberField(c.getComponentForm()).getText();

        if ("".equals(acctnumber)) {
            Dialog.show("", "you haven't entered an account number", "OK", null);
        } else {
            addAccount.add(acctnumber);

            findAcctNumberField(c.getComponentForm()).clear();

            Form f = c.getComponentForm();
            List l = findAccountList(c.getComponentForm()); //findContactList();
            l.setModel(new DefaultListModel(addAccount));
            f.revalidate();
        }
    }

    /**
     * action to perform before displaying the "Account to pull " form
     */
    @Override
    protected void beforeAccountToPullInfo(final Form f) {

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("OfflineMenu", null);
            }
        };

        Command add = new Command("Add") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                String acctnumber = findAcctNumberField(f).getText();

                if ("".equals(acctnumber)) {
                    Dialog.show("", "you haven't entered an account number", "OK", null);
                } else {
                    addAccount.add(acctnumber);

                    findAcctNumberField(f).clear();

//                    Form f = c.getComponentForm();
                    List l = findAccountList(f); //findContactList();
                    l.setModel(new DefaultListModel(addAccount));
                    f.revalidate();
                }

            }
        };

        Command pull = new Command("Pull") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                if (Storage.getInstance().exists("storedCustomers")) {
                    customersToStore = (Vector<Hashtable>) Storage.getInstance().readObject("storedCustomers");
                } else {
                    customersToStore = new Vector<Hashtable>();
                }


                accountsStored = new Vector();
                accountWithErrorReturnCode = new Vector();
                accountWithErrorStatus = new Vector();

                // int count = 0;

                //String acctNumber = findAcctNumberField(f).getText();
                //System.out.println(addAccount);

                if ((addAccount.isEmpty())) {
                    Dialog.show("", "please add account number to the list", "OK", null);
                } else {

                    for (int i = 0; i < addAccount.size(); i++) {

                        verifyCustomer(OfflineTeller.get("mfb_code"), addAccount.elementAt(i).toString());

                        if (!("200".equals(status))) {

                            Dialog.show(addAccount.elementAt(i).toString(), "could not download this customer", "OK", null);

                            accountWithErrorStatus.add(addAccount.elementAt(i).toString());

                            // break;

                        } else {

                            status = "";

                            String returncode = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retval");
                            String returncodemsg = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retmsg");

                            if ("0".equals(returncode)) {


                                String accountname = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:signatoryname");

                                String accountnumber = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:accountnumber");

                                String imagephoto = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:customerimage");

                                String imagesignature = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:signatureimage");

                                String customerBalance = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:bookbalance");


                                customer = new Customers(accountname, accountnumber, imagephoto, imagesignature, customerBalance);

                                customerToStore = new Hashtable<String, String>();
                                customerToStore.put("accountName", customer.getName());
                                customerToStore.put("accountNumber", customer.getAcctNumber());
                                customerToStore.put("imagePhoto", customer.getPhoto());
                                customerToStore.put("signature", customer.getSignature());
                                customerToStore.put("balance", customer.getCurrentBal());

                                customersToStore.add(customerToStore);

                                accountsStored.add(addAccount.elementAt(i).toString());

                                //showForm("CustomerToSave", null);
                            } else {

                                Dialog.show(addAccount.elementAt(i).toString(), returncodemsg, "ok", null);
                                accountWithErrorReturnCode.add(addAccount.elementAt(i).toString());
                            }
                        }
                    }

                    if (!(accountsStored.isEmpty())) {
                        try {

                            Storage.getInstance().writeObject("storedCustomers", customersToStore);
                            Dialog.show("", "Customers stored", "ok", null);

                            addAccount.clear();

                            showForm("SummaryOfDownload", null);

                        } catch (Exception e) {
                            Dialog.show("error", "trying to store in media card " + "'" + e.getMessage() + "'", "ok", null);
                        }
                    } else {
                        Dialog.show("", "could not pull all customers", "ok", null);
                        showForm("SummaryOfDownload", null);
                    }


                }
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("OfflineMenu", null);
            }
        };

        f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(add);
        f.getMenuBar().addCommand(pull);
        f.getMenuBar().addCommand(cancel);
    }

    /**
     * After providing all the accounts to pull, this action actually calles a
     * method to pull all the information about the accounts
     */
    @Override
    protected void onAccountToPullInfo_FetchInfoAction(Component c, ActionEvent event) {

        if (Storage.getInstance().exists("storedCustomers")) {
            customersToStore = (Vector<Hashtable>) Storage.getInstance().readObject("storedCustomers");
        } else {
            customersToStore = new Vector<Hashtable>();
        }


        accountsStored = new Vector();
        accountWithErrorReturnCode = new Vector();
        accountWithErrorStatus = new Vector();

        // int count = 0;

        String acctNumber = findAcctNumberField(c.getComponentForm()).getText();
        //System.out.println(addAccount);

        if ("".equals(acctNumber) && (addAccount.isEmpty())) {
            Dialog.show("", "please enter account number", "OK", null);
        } else {

            for (int i = 0; i < addAccount.size(); i++) {

                this.verifyCustomer(OfflineTeller.get("mfb_code"), addAccount.elementAt(i).toString());

                if (!("200".equals(status))) {

                    Dialog.show(addAccount.elementAt(i).toString(), "could not download this customer", "OK", null);

                    accountWithErrorStatus.add(addAccount.elementAt(i).toString());

                    // break;

                } else {

                    status = "";

                    String returncode = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retval");
                    String returncodemsg = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retmsg");

                    if ("0".equals(returncode)) {


                        String accountname = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:signatoryname");

                        String accountnumber = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:accountnumber");

                        String imagephoto = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:customerimage");

                        String imagesignature = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:signatureimage");

                        String customerBalance = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:bookbalance");


                        customer = new Customers(accountname, accountnumber, imagephoto, imagesignature, customerBalance);

                        customerToStore = new Hashtable<String, String>();
                        customerToStore.put("accountName", customer.getName());
                        customerToStore.put("accountNumber", customer.getAcctNumber());
                        customerToStore.put("imagePhoto", customer.getPhoto());
                        customerToStore.put("signature", customer.getSignature());
                        customerToStore.put("balance", customer.getCurrentBal());

                        customersToStore.add(customerToStore);

                        accountsStored.add(addAccount.elementAt(i).toString());

                        //showForm("CustomerToSave", null);
                    } else {

                        Dialog.show(addAccount.elementAt(i).toString(), returncodemsg, "ok", null);
                        accountWithErrorReturnCode.add(addAccount.elementAt(i).toString());
                    }
                }
            }

            if (!(accountsStored.isEmpty())) {
                try {

                    Storage.getInstance().writeObject("storedCustomers", customersToStore);
                    Dialog.show("", "Customers stored", "ok", null);

                    addAccount.clear();

                    showForm("SummaryOfDownload", null);

                } catch (Exception e) {
                    Dialog.show("error", "trying to store in media card " + "'" + e.getMessage() + "'", "ok", null);
                }
            } else {
                Dialog.show("", "could not pull all customers", "ok", null);
                showForm("SummaryOfDownload", null);
            }


        }
    }

    /**
     * List model of accounts to pull
     */
    @Override
    protected boolean initListModelAccountList(List cmp) {
        cmp.setModel(new DefaultListModel(addAccount));
        return true;
    }

    /**
     * Action on the accounts to pull list
     */
    @Override
    protected void onAccountToPullInfo_AccountListAction(final Component c, ActionEvent event) {

        final List l = (List) c;

        // System.out.println("You Selected number : " + l.getSelectedIndex());

        Command[] cmds = new Command[2];
        cmds[0] = new Command("Delete") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                for (int i = 0; i < addAccount.size(); i++) {

                    if (i == l.getSelectedIndex()) {
                        addAccount.removeElementAt(i);
                    }
                }

                Form f = c.getComponentForm();
                List l = findAccountList(c.getComponentForm()); //findContactList();
                l.setModel(new DefaultListModel(addAccount));
                f.revalidate();

            }
        };
        cmds[1] = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //do Option2
            }
        };

        // Dialog.show("Account", l.getSelectedItem().toString(), cmds, BACK_COMMAND_ID, null, 0);
        TextArea area = new TextArea();
        area.setUIID("VKBtooltip");
        area.setEditable(false);
        area.setText(l.getSelectedItem().toString());
        Dialog.show("Account", area, cmds);
    }

    /**
     * cancel action on the "pull accounts" form
     */
    @Override
    protected void onAccountToPullInfo_CancelFetchAction(Component c, ActionEvent event) {
        showForm("OfflineMenu", null);
    }

    /**
     * Action onn the "Pull accounts" button on the Offline Menu
     */
    @Override
    protected void onOfflineMenu_PullAccountsAction(Component c, ActionEvent event) {

        if (Storage.getInstance().exists("Application_Settings")) {
            app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
            showForm("AccountToPullInfo", null);
        } else {
            Dialog.show("Sorry", "You need to provide Application Settings before you can pull Customer Accounts", "OK", null);
        }

    }

    /**
     * An action on the "View Offline Transactions" button on the "offline Menu"
     * form
     */
    @Override
    protected void onOfflineMenu_ViewOfflineTransAction(Component c, ActionEvent event) {

        if (Storage.getInstance().exists("storedTransactions")) {
            try {
                offlineTransactionsStored = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");

                showForm("OfflineTransactionsDisplay", null);

            } catch (Exception e) {
                Dialog.show("Error!!", e.getMessage(), "OK", null);
            }
        } else {
            Dialog.show("", "you may have not performed any offline transactions to display", "OK", null);
        }

    }

    /**
     * Action on the 'Offline deposit" button on the offline menu
     */
    @Override
    protected void onOfflineMenu_OfflineDepositAction(Component c, ActionEvent event) {
        if (Storage.getInstance().exists("storedCustomers")) {
            customersToStore = new Vector<Hashtable>();
            try {
                customersToStore = (Vector<Hashtable>) Storage.getInstance().readObject("storedCustomers");
                showForm("StoredCustomers", null);
            } catch (Exception exception) {
                Dialog.show("", "error reading storage media " + "'" + exception.getMessage() + "'", "OK", null);
            }

        } else {
            Dialog.show("", "you may not have stored customers for offline transactions", "OK", null);
        }
    }

    /**
     * this is a method that is called before "offline transaction display" form
     * is shown the method sets up the page for display
     */
    @Override
    protected void beforeOfflineTransactionsDisplay(Form f) {


        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("OfflineMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(home);

        Command uploadTran = new Command("Upload All") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                storedTransactionsNotUploaded = new Vector<Hashtable>();
                storedTransactionsUploaded = new Vector<Hashtable>();

                if ((app_Settings == null) || (app_Settings.isEmpty())) {
                    Dialog.show("Settings", "please go to SETTINGS and provide both Transaction URL and Bearer Key", "OK", null);
                } else {
                    try {
                        //Storage.getInstance().exists("Application_Settings")
                        app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
                    } catch (Exception e) {
                        Dialog.show("Failed", "Could not read Application Settings" + "'" + e.getMessage() + "'", "OK", null);
                        back();
                    }
                    if (Storage.getInstance().exists("storedTransactions")) {
//            try {
                        offlineTransactionsStored = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");

                        //offlineTransactionsStored.size();
                        for (int i = 0; i < offlineTransactionsStored.size(); i++) {
                            Hashtable h = offlineTransactionsStored.get(i);

                            performDeposit(h.get("customer_account").toString(),
                                    h.get("amount").toString(),
                                    h.get("mfbID").toString(),
                                    h.get("narration").toString(),
                                    h.get("teller_number").toString(),
                                    h.get("request_id").toString());

                            if (!("200".equals(status))) {
                                Dialog.show("", "something may have gone wrong", "OK", null);
                                storedTransactionsNotUploaded.add(h);
                            } else {

                                status = "";

                                String returncode = result.getAsString("/S:Envelope/s:Body/ns3:CashDepositResponse/return/ns2:Retval");
                                String returncodemsg = result.getAsString("/S:Envelope/s:Body/ns3:CashDepositResponse/return/ns2:Retmsg");


                                if ("0".equals(returncode)) {


                                    String postSeq = result.getAsString("/S:Envelope/s:Body/ns3:CashDepositResponse/return/ns2:postseq");

                                    Hashtable storeReply = new Hashtable();
                                    storeReply.put("account_number", h.get("customer_account").toString());
                                    storeReply.put("post_sequence", postSeq);
                                    storeReply.put("transaction_type", h.get("tran_type").toString());
                                    storeReply.put("amount", h.get("amount").toString());

                                    Dialog.show("Success", "posted with Sequence number : " + postSeq, "OK", null);

                                    storedTransactionsUploaded.add(h);

                                    // System.out.println("Strored Uploaded : " + storedTransactionsUploaded);

                                    if ((Storage.getInstance().exists("storedReplies"))) {

                                        storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");
                                        storedReplies.add(storeReply);
                                        try {
                                            Storage.getInstance().writeObject("storedReplies", storedReplies);


                                        } catch (Exception e) {
                                            Dialog.show("", "error saving info, contacting server", "OK", null);
                                        }
                                    } else {
                                        storedReplies = new Vector<Hashtable>();
                                        storedReplies.add(storeReply);
                                        try {
                                            Storage.getInstance().writeObject("storedReplies", storedReplies);

                                        } catch (Exception e) {
                                            Dialog.show("", "error saving info, contacting server", "OK", null);
                                        }
                                    }

//offlineTransactionsStored.removeElementAt(i);

                                } else {

                                    Dialog.show("", returncodemsg, "ok", null);
                                }

                            }
                        }

                        for (int i = 0; i < storedTransactionsUploaded.size(); i++) {

                            Hashtable hashtable = storedTransactionsUploaded.get(i);

                            for (int j = 0; j < offlineTransactionsStored.size(); j++) {

                                Hashtable hashtable1 = offlineTransactionsStored.elementAt(j);

                                if (hashtable.equals(hashtable1)) {
//                        System.out.println("Hashtable...." + hashtable.toString());
//                        System.out.println("Hashtable1...." + hashtable1.toString());
                                    offlineTransactionsStored.removeElementAt(j);
                                }
                            }
                            //  if(hashtable.equals(storedTransactionsUploaded))       
                        }

                        try {

                            Storage.getInstance().writeObject("storedTransactions", offlineTransactionsStored);
                            //Dialog.show("", "Transaction stored", "ok", null);

                            offlineTransactionsStored = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
                            //  System.out.println("Offline Transactions : " + offlineTransactionsStored.toString());
                            //back();

                        } catch (Exception e) {
                            Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
                        }

                        Dialog.show("", "about to run a check", "OK", null);

                        if ((Storage.getInstance().exists("storedReplies"))) {

                            //storedReplies.add(storeReply);
                            try {
                                storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");


                            } catch (Exception e) {
                                Dialog.show("", "error saving info, contacting server", "OK", null);
                                back();
                            }

                            Vector<Hashtable> repliesUploaded = new Vector<Hashtable>();

                            for (int i = 0; i < storedReplies.size(); i++) {
                                Hashtable hashtable = storedReplies.get(i);

                                uploadTranReplies(hashtable.get("account_number").toString(),
                                        hashtable.get("amount").toString(),
                                        hashtable.get("transaction_type").toString(),
                                        hashtable.get("post_sequence").toString());

                                if ("200".equals(status)) {
                                    //Dialog.show("", "something may have gone wrong", "OK", null);

                                    status = "";

                                    String returncode = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retval");
                                    String returncodemsg = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retmsg");
                                    if (("1".equals(returncode)) && ("Data Logged".equals(returncodemsg))) {
                                        repliesUploaded.add(hashtable);
                                    }

                                }
                            }

                            if (!(repliesUploaded.isEmpty())) {

                                for (int i = 0; i < repliesUploaded.size(); i++) {

                                    Hashtable hashtable2 = repliesUploaded.get(i);

                                    for (int j = 0; j < storedReplies.size(); j++) {
                                        Hashtable hashtable3 = storedReplies.get(j);

                                        if (hashtable2.equals(hashtable3)) {
                                            storedReplies.removeElementAt(j);
                                        }
                                    }

                                }

                                if (!(repliesUploaded.isEmpty())) {
                                    try {

                                        Storage.getInstance().writeObject("storedReplies", storedReplies);
                                        //Dialog.show("", "Transaction stored", "ok", null);

                                        storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
                                        //  System.out.println("Stored Replies : " + storedReplies.toString());
                                        //back();

                                    } catch (Exception e) {
                                        Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
                                    }



                                } else {
                                    Storage.getInstance().deleteStorageFile("storedReplies");
                                }

                            }
                        }

                        showForm("SummaryOfUpload", null);

                    } else {

                        Dialog.show("", "Transactions not found", "OK", null);
                        //showForm("OfflineMenu", null);

                    }

                }
            }
        };

        f.getMenuBar().addCommand(uploadTran);
    }

    /**
     * Offline transactions List model
     */
    @Override
    protected boolean initListModelAllOfflineTransactionsMultiList(List cmp) {
        cmp.setModel(new DefaultListModel(offlineTransactionsStored));
        return true;
    }

    /**
     * Action on the offline transactions list, to show the details of the
     * selected transaction
     */
    @Override
    protected void onOfflineTransactionsDisplay_AllOfflineTransactionsMultiListAction(Component c, ActionEvent event) {

        List l = (List) c;
        Hashtable h = (Hashtable) l.getSelectedItem();

        // System.out.println("---- Selected Transaction ------: " + h);
        offlineTransaction = new OfflineTransactions(h.get("customer_account").toString(), h.get("customer_name").toString(), h.get("amount").toString(), h.get("mfbID").toString(), h.get("teller_number").toString(), h.get("narration").toString(), h.get("request_id").toString(), h.get("tran_type").toString());

        showForm("EachOfflineTransaction", null);
    }

    /**
     * Before each offline transaction is displayed, this method sets up the
     * form for display
     */
    @Override
    protected void beforeEachOfflineTransaction(Form f) {
        f.setScrollable(false);
        findLabel(f).setText(offlineTransaction.getAccountNumber());

        findOfflineTranAcctName(f).setText(offlineTransaction.getAccountName());
        findOfflineTranAcctNumber(f).setText(offlineTransaction.getAccountNumber());
        findOfflineTranAmount(f).setText(offlineTransaction.getTranType() + " : " + offlineTransaction.getAmount());
        findOfflineTranTellerNumber(f).setText("Teller Number : " + offlineTransaction.getTellerNumber());
        findOfflineTranNarration(f).setText(offlineTransaction.getTranDescription());
        findOfflineTranTranCode(f).setText("Tran Code : " + offlineTransaction.getTranCode());

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("OfflineMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(home);
    }

    /**
     * The action on "Upload Transaction" button on the "Each offline
     * transaction" form
     */
    @Override
    protected void onEachOfflineTransaction_UploadSingleTranAction(Component c, ActionEvent event) {

        if (Storage.getInstance().exists("Application_Settings")) {
            try {
                app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
            } catch (Exception e) {
                Dialog.show("", "Error reading APP SETTINGS '" + e.getMessage() + "'", "OK", null);
                back();
            }

            storedTransactionsNotUploaded = new Vector<Hashtable>();
            storedTransactionsUploaded = new Vector<Hashtable>();

            Hashtable h = new Hashtable();
            h.put("customer_account", offlineTransaction.getAccountNumber());
            h.put("customer_name", offlineTransaction.getAccountName());
            h.put("amount", offlineTransaction.getAmount());
            h.put("mfbID", offlineTransaction.getMfbCode());
            h.put("teller_number", offlineTransaction.getTellerNumber());
            h.put("narration", offlineTransaction.getTranDescription());
            h.put("request_id", offlineTransaction.getTranCode());
            h.put("tran_type", offlineTransaction.getTranType());

            this.performDeposit(offlineTransaction.getAccountNumber(),
                    offlineTransaction.getAmount(),
                    offlineTransaction.getMfbCode(),
                    offlineTransaction.getTranDescription(),
                    offlineTransaction.getTellerNumber(),
                    offlineTransaction.getTranCode());

            if (!("200".equals(status))) {
                Dialog.show("", "something may have gone wrong", "OK", null);
                storedTransactionsNotUploaded.add(h);
            } else {

                status = "";

                String returncode = result.getAsString("/S:Envelope/s:Body/ns3:CashDepositResponse/return/ns2:Retval");
                String returncodemsg = result.getAsString("/S:Envelope/s:Body/ns3:CashDepositResponse/return/ns2:Retmsg");

                if ("0".equals(returncode)) {


                    String postSeq = result.getAsString("/S:Envelope/s:Body/ns3:CashDepositResponse/return/ns2:postseq");

                    Hashtable storeReply = new Hashtable();
                    storeReply.put("account_number", h.get("customer_account").toString());
                    storeReply.put("post_sequence", postSeq);
                    storeReply.put("transaction_type", h.get("tran_type").toString());
                    storeReply.put("amount", h.get("amount").toString());

                    Dialog.show("Success", "posted with Sequence number : " + postSeq, "OK", null);

                    //   System.out.println("Tracing the error on delete : " + h.toString());

                    storedTransactionsUploaded.add(h);

                    // System.out.println("Strored Uploaded : " + storedTransactionsUploaded);

                    if ((Storage.getInstance().exists("storedReplies"))) {

                        storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");
                        storedReplies.add(storeReply);
                        try {
                            Storage.getInstance().writeObject("storedReplies", storedReplies);


                        } catch (Exception e) {
                            Dialog.show("", "error saving info, contacting server", "OK", null);
                        }
                    } else {
                        storedReplies = new Vector<Hashtable>();
                        storedReplies.add(storeReply);
                        try {
                            Storage.getInstance().writeObject("storedReplies", storedReplies);

                        } catch (Exception e) {
                            Dialog.show("", "error saving info, contacting server", "OK", null);
                        }
                    }

//offlineTransactionsStored.removeElementAt(i);

                } else {

                    Dialog.show("", returncodemsg, "ok", null);
                }

                for (int i = 0; i < storedTransactionsUploaded.size(); i++) {

                    Hashtable hashtable = storedTransactionsUploaded.get(i);

                    for (int j = 0; j < offlineTransactionsStored.size(); j++) {

                        Hashtable hashtable1 = offlineTransactionsStored.elementAt(j);

                        if (hashtable.equals(hashtable1)) {
//                        System.out.println("storedTransactionsUploaded...." + storedTransactionsUploaded.toString());
//                        System.out.println("Hashtable1...." + hashtable1.toString());
                            offlineTransactionsStored.removeElementAt(j);
                        }
                    }

                }

                try {

                    Storage.getInstance().writeObject("storedTransactions", offlineTransactionsStored);
                    //Dialog.show("", "Transaction stored", "ok", null);

                    offlineTransactionsStored = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
                    //   System.out.println("Offline Transactions : " + offlineTransactionsStored.toString());
                    //back();

                } catch (Exception e) {
                    Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
                }

                Dialog.show("", "about to run a check", "OK", null);

                if ((Storage.getInstance().exists("storedReplies"))) {

                    //storedReplies.add(storeReply);
                    try {
                        storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");


                    } catch (Exception e) {
                        Dialog.show("", "error saving info, contacting server", "OK", null);
                        back();
                    }

                    Vector<Hashtable> repliesUploaded = new Vector<Hashtable>();

                    for (int i = 0; i < storedReplies.size(); i++) {
                        Hashtable hashtable = storedReplies.get(i);
//System.out.println();
                        this.uploadTranReplies(hashtable.get("account_number").toString(),
                                hashtable.get("amount").toString(),
                                hashtable.get("transaction_type").toString(),
                                hashtable.get("post_sequence").toString());

                        if ("200".equals(status)) {
                            //Dialog.show("", "something may have gone wrong", "OK", null);

                            status = "";

                            String returncode2 = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retval");
                            String returncodemsg2 = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retmsg");
                            if (("1".equals(returncode2)) && ("Data Logged".equals(returncodemsg2))) {
                                repliesUploaded.add(hashtable);
                            }
                        }
                    }

                    if (!(repliesUploaded.isEmpty())) {

                        for (int i = 0; i < repliesUploaded.size(); i++) {

                            Hashtable hashtable2 = repliesUploaded.get(i);

                            for (int j = 0; j < storedReplies.size(); j++) {
                                Hashtable hashtable3 = storedReplies.get(j);

                                if (hashtable2.equals(hashtable3)) {
                                    storedReplies.removeElementAt(j);
                                }
                            }

                        }

                        if (!(repliesUploaded.isEmpty())) {
                            try {

                                Storage.getInstance().writeObject("storedReplies", storedReplies);
                                //Dialog.show("", "Transaction stored", "ok", null);

                                storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
                                //    System.out.println("Stored Replies : " + storedReplies.toString());
                                //back();

                            } catch (Exception e) {
                                Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
                            }



                        } else {
                            Storage.getInstance().deleteStorageFile("storedReplies");
                        }

                    }
                }


                back();
                //showForm("SummaryOfUpload", null);
            }
            //}
        } else {
            Dialog.show("", "Please go to the Settings and provide the APP SETTINGS", "OK", null);
        }
    }

    /**
     * cancel action on "Each offline transaction" form
     */
    @Override
    protected void onEachOfflineTransaction_CancelAction(Component c, ActionEvent event) {

        back();
    }

    /**
     * action on the "Upload all Transaction" button in "Offline transaction"
     * form it checks if there are transactions stored on the phone, pulls it up
     * and then upload all transactions to the server
     */
    @Override
    protected void onOfflineTransactionsDisplay_UploadAllTransactionsAction(Component c, ActionEvent event) {

        storedTransactionsNotUploaded = new Vector<Hashtable>();
        storedTransactionsUploaded = new Vector<Hashtable>();

        if ((app_Settings == null) || (app_Settings.isEmpty())) {
            Dialog.show("Settings", "please go to SETTINGS and provide both Transaction URL and Bearer Key", "OK", null);
        } else {
            try {
                //Storage.getInstance().exists("Application_Settings")
                app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
            } catch (Exception e) {
                Dialog.show("Failed", "Could not read Application Settings" + "'" + e.getMessage() + "'", "OK", null);
                back();
            }
            if (Storage.getInstance().exists("storedTransactions")) {
//            try {
                offlineTransactionsStored = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");

                //offlineTransactionsStored.size();
                for (int i = 0; i < offlineTransactionsStored.size(); i++) {
                    Hashtable h = offlineTransactionsStored.get(i);

                    this.performDeposit(h.get("customer_account").toString(),
                            h.get("amount").toString(),
                            h.get("mfbID").toString(),
                            h.get("narration").toString(),
                            h.get("teller_number").toString(),
                            h.get("request_id").toString());

                    if (!("200".equals(status))) {
                        Dialog.show("", "something may have gone wrong", "OK", null);
                        storedTransactionsNotUploaded.add(h);
                    } else {

                        status = "";

                        String returncode = result.getAsString("/S:Envelope/s:Body/ns3:CashDepositResponse/return/ns2:Retval");
                        String returncodemsg = result.getAsString("/S:Envelope/s:Body/ns3:CashDepositResponse/return/ns2:Retmsg");


                        if ("0".equals(returncode)) {


                            String postSeq = result.getAsString("/S:Envelope/s:Body/ns3:CashDepositResponse/return/ns2:postseq");

                            Hashtable storeReply = new Hashtable();
                            storeReply.put("account_number", h.get("customer_account").toString());
                            storeReply.put("post_sequence", postSeq);
                            storeReply.put("transaction_type", h.get("tran_type").toString());
                            storeReply.put("amount", h.get("amount").toString());

                            Dialog.show("Success", "posted with Sequence number : " + postSeq, "OK", null);

                            storedTransactionsUploaded.add(h);

                            // System.out.println("Strored Uploaded : " + storedTransactionsUploaded);

                            if ((Storage.getInstance().exists("storedReplies"))) {

                                storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");
                                storedReplies.add(storeReply);
                                try {
                                    Storage.getInstance().writeObject("storedReplies", storedReplies);


                                } catch (Exception e) {
                                    Dialog.show("", "error saving info, contacting server", "OK", null);
                                }
                            } else {
                                storedReplies = new Vector<Hashtable>();
                                storedReplies.add(storeReply);
                                try {
                                    Storage.getInstance().writeObject("storedReplies", storedReplies);

                                } catch (Exception e) {
                                    Dialog.show("", "error saving info, contacting server", "OK", null);
                                }
                            }

//offlineTransactionsStored.removeElementAt(i);

                        } else {

                            Dialog.show("", returncodemsg, "ok", null);
                        }

                    }
                }

                for (int i = 0; i < storedTransactionsUploaded.size(); i++) {

                    Hashtable hashtable = storedTransactionsUploaded.get(i);

                    for (int j = 0; j < offlineTransactionsStored.size(); j++) {

                        Hashtable hashtable1 = offlineTransactionsStored.elementAt(j);

                        if (hashtable.equals(hashtable1)) {

                            offlineTransactionsStored.removeElementAt(j);
                        }
                    }

                }

                if (offlineTransactionsStored.isEmpty()) {
                    try {

                        Storage.getInstance().deleteStorageFile("storedTransactions");


                    } catch (Exception e) {
                        Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
                    }

                } else {
                    try {

                        Storage.getInstance().writeObject("storedTransactions", offlineTransactionsStored);
                        //Dialog.show("", "Transaction stored", "ok", null);

                        offlineTransactionsStored = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
                        //  System.out.println("Offline Transactions : " + offlineTransactionsStored.toString());
                        //back();

                    } catch (Exception e) {
                        Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
                    }
                }

                // Log the transaction details to the Server
                Dialog.show("", "about to run a check", "OK", null);

                if ((Storage.getInstance().exists("storedReplies"))) {

                    //storedReplies.add(storeReply);
                    try {
                        storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");


                    } catch (Exception e) {
                        Dialog.show("", "error accessing storage media " + "'" + e.getMessage() + "'", "OK", null);
                        back();
                    }

                    Vector<Hashtable> repliesUploaded = new Vector<Hashtable>();

                    for (int i = 0; i < storedReplies.size(); i++) {
                        Hashtable hashtable = storedReplies.get(i);

                        this.uploadTranReplies(hashtable.get("account_number").toString(),
                                hashtable.get("amount").toString(),
                                hashtable.get("transaction_type").toString(),
                                hashtable.get("post_sequence").toString());

                        if ("200".equals(status)) {
                            //Dialog.show("", "something may have gone wrong", "OK", null);

                            status = "";

                            String returncode2 = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retval");
                            String returncodemsg2 = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retmsg");
                            if (("1".equals(returncode2)) && ("Data Logged".equals(returncodemsg2))) {
                                repliesUploaded.add(hashtable);
                            }
                        }
                    }

                    if (!(repliesUploaded.isEmpty())) {

                        for (int i = 0; i < repliesUploaded.size(); i++) {

                            Hashtable hashtable2 = repliesUploaded.get(i);

                            for (int j = 0; j < storedReplies.size(); j++) {
                                Hashtable hashtable3 = storedReplies.get(j);

                                if (hashtable2.equals(hashtable3)) {
                                    storedReplies.removeElementAt(j);
                                }
                            }

                        }

                        if (!(repliesUploaded.isEmpty())) {
                            try {

                                Storage.getInstance().writeObject("storedReplies", storedReplies);
                                //Dialog.show("", "Transaction stored", "ok", null);

                                storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
                                //  System.out.println("Stored Replies : " + storedReplies.toString());
                                //back();

                            } catch (Exception e) {
                                Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
                            }



                        } else {
                            Storage.getInstance().deleteStorageFile("storedReplies");
                        }

                    }
                }

                showForm("SummaryOfUpload", null);

            } else {

                Dialog.show("", "Transactions not found", "OK", null);
                //showForm("OfflineMenu", null);

            }

        }
    }

    /**
     * method called to set up the display for transaction upload summary
     */
    @Override
    protected void beforeSummaryOfUpload(Form f) {

        List l = findTransactionsNotUploadedMultiList(f); //findContactList();
        l.setModel(new DefaultListModel(storedTransactionsNotUploaded));


        List l2 = findTransactionsUploadedMultiList(f);
        l2.setModel(new DefaultListModel(storedTransactionsUploaded));

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("OfflineMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(home);
    }

    /**
     * List model for Transactions that were uploaded when the Upload was run
     */
    @Override
    protected boolean initListModelTransactionsUploadedMultiList(List cmp) {
        cmp.setModel(new DefaultListModel(storedTransactionsUploaded));
        return true;
    }

    /**
     * List model for transactions that were not uploaded when the upload was
     * run
     */
    @Override
    protected boolean initListModelTransactionsNotUploadedMultiList(List cmp) {
        cmp.setModel(new DefaultListModel(storedTransactionsNotUploaded));
        return true;
    }

    /**
     * The action to be performed before displaying a summary of the accounts
     * that were pulled for offline transaction form
     */
    @Override
    protected void beforeSummaryOfDownload(Form f) {

        // Form f = c.getComponentForm();
        List l = findAccountsStoredList(f); //findContactList();
        l.setModel(new DefaultListModel(accountsStored));

        List l1 = findAccountsWithErrorReturnCodeList(f);
        l1.setModel(new DefaultListModel(accountWithErrorReturnCode));

        List l2 = findAccountsWithErrorStatusList(f);
        l2.setModel(new DefaultListModel(accountWithErrorStatus));
        //f.revalidate();

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("OfflineMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(home);
    }

    /**
     * List model for accounts stored after the download for offline transaction
     */
    @Override
    protected boolean initListModelAccountsStoredList(List cmp) {
        cmp.setModel(new DefaultListModel(accountsStored));
        return true;
    }

    /**
     * List model for accounts that returned error internet status while being
     * downloaded for offline transaction
     */
    @Override
    protected boolean initListModelAccountsWithErrorStatusList(List cmp) {
        cmp.setModel(new DefaultListModel(accountWithErrorStatus));
        return true;
    }

    /**
     * List model for account that returned error return code from the database
     * server while being downloaded from the server
     */
    @Override
    protected boolean initListModelAccountsWithErrorReturnCodeList(List cmp) {
        cmp.setModel(new DefaultListModel(accountWithErrorReturnCode));
        return true;
    }

    /**
     * List model for customers that were successfully downloaded and stored for
     * offline transactions
     */
    @Override
    protected boolean initListModelStoredCustomersMultiList(List cmp) {
        cmp.setModel(new DefaultListModel(customersToStore));
        return true;
    }

    /**
     * The action to be performed when a customer is selected from the list of
     * customers stored
     */
    @Override
    protected void onStoredCustomers_StoredCustomersMultiListAction(final Component c, ActionEvent event) {

        final List l = (List) c;

        Hashtable h = (Hashtable) l.getSelectedItem();

        customer = new Customers(h.get("accountName").toString(), h.get("accountNumber").toString(), h.get("imagePhoto").toString(), h.get("signature").toString(), h.get("balance").toString());

        //  System.out.println("You Selected number : " + l.getSelectedIndex());

        Command[] cmds = new Command[2];
        cmds[0] = new Command("Delete") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                for (int i = 0; i < customersToStore.size(); i++) {

                    if (i == l.getSelectedIndex()) {
                        customersToStore.removeElementAt(i);
                    }
                }

                Form f = c.getComponentForm();
                List l = findStoredCustomersMultiList(c.getComponentForm()); //findContactList();
                l.setModel(new DefaultListModel(customersToStore));
                f.revalidate();

            }
        };
        cmds[1] = new Command("Continue") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //do Option2
                showForm("StoredCustomer", null);
            }
        };

        TextArea area = new TextArea();
        area.setUIID("VKBtooltip");
        area.setEditable(false);
        area.setText(h.get("accountNumber").toString());
        Dialog.show("Account", area, cmds);

        // Dialog.show("Account", h.get("accountNumber").toString(), cmds, BACK_COMMAND_ID, null, 0);
        // Hashtable h = (Hashtable)l.getSelectedItem();


    }

    /**
     * This is method is called to convert the Base64 Image strings recieved
     * into images for display on the Stored customers form.
     */
    @Override
    protected void beforeStoredCustomer(Form f) {
        f.setScrollable(false);
        byte[] photoByte = com.codename1.util.Base64.decode((customer.getPhoto()).getBytes());
        byte[] signatureByte = com.codename1.util.Base64.decode((customer.getSignature()).getBytes());

        try {
            photo = EncodedImage.create(photoByte);
            signature = EncodedImage.create(signatureByte);


            findCustomerPhoto(f).setIcon(photo);
            findCustomerSignature(f).setIcon(signature);
            findAccountNumber(f).setText(customer.getAcctNumber());
            findAccountName(f).setText(customer.getName());
            //findCurrentBalance(f).setText("Balance : " + customer.getCurrentBal());

        } catch (Exception io) {
            Dialog.show("", "error occured trying to display info " + "'" + io.getMessage() + "'", "ok", null);
            // System.out.println("error: " + io);
            back();
        }

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("OfflineMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        });
    }

    /**
     * When "Deposit" is clicked on the offline menu, this method handles the
     * deposit action
     */
    @Override
    protected void onOfflineDepositForm_DepositToPhoneAction(Component c, ActionEvent event) {

        if (Storage.getInstance().exists("storedTransactions")) {
            offlineTransactionsStored = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
        } else {
            offlineTransactionsStored = new Vector<Hashtable>();
        }

        String amount = findOfflineAmountTextField(c.getComponentForm()).getText();
        String OfflineNarration = findOfflineNarrationTextArea(c.getComponentForm()).getText();
        String depositTellerNo = findOfflineTellerNumberTextField(c.getComponentForm()).getText();

        if (("".equals(amount)) || ("".equals(OfflineNarration)) || ("".equals(depositTellerNo))) {
            Dialog.show("", "respect yourself and enter a value in all fields", "ok", null);
        } else {
            //offlineDeposit = new OfflineDeposits(customer.getAcctNumber(), amount, profile.get("mfbID"), tellerNo, OfflineNarration, tellerNo);
            offlineDeposit = new Hashtable<String, String>();
            offlineDeposit.put("customer_account", customer.getAcctNumber());
            offlineDeposit.put("customer_name", customer.getName());
            offlineDeposit.put("amount", amount);
            offlineDeposit.put("mfbID", OfflineTeller.get("mfb_code"));
            offlineDeposit.put("teller_number", depositTellerNo);
            offlineDeposit.put("narration", OfflineNarration);
            offlineDeposit.put("request_id", OfflineTeller.get("teller_ID") + this.getRequestID());
            offlineDeposit.put("tran_type", "Credit");

            offlineTransactionsStored.add(offlineDeposit);

            try {

                Storage.getInstance().writeObject("storedTransactions", offlineTransactionsStored);
                Dialog.show("", "Transaction stored", "ok", null);

                offlineTransactionsStored = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
                //  System.out.println("Offline Transactions : " + offlineTransactionsStored.toString());
                back();

            } catch (Exception e) {

                Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
            }

        }
    }

    /**
     * Cancel action on the offline deposit form
     */
    @Override
    protected void onOfflineDepositForm_CancelAction(Component c, ActionEvent event) {

        back();
    }

    /**
     * List model for all MFBs (not in use anymore)
     */
    @Override
    protected boolean initListModelDepositMFBsMultiList(List cmp) {
        cmp.setModel(new DefaultListModel(MFBsList));
        return true;
    }

    /**
     * The action on each MFB on the List for deposit(not in use )
     */
    @Override
    protected void onDepositMFBs_DepositMFBsMultiListAction(Component c, ActionEvent event) {

        List l = (List) c;
        Hashtable h = (Hashtable) l.getSelectedItem();

        mfb = new MFBs((String) h.get("mfb_name"), (String) h.get("mfb_id"));

        showForm("DepositAcctNumber", null);
    }

    /**
     * This method calls the "verifyCustomer()" method to verify the customer
     * before performing a deposit transaction
     */
    @Override
    protected void onDepositAcctNumber_VerifyDepositAction(Component c, ActionEvent event) {

        String acctnum = findAcctNumberTextField(c.getComponentForm()).getText();
        if ("".equals(acctnum)) {
            Dialog.show("", "you haven't entered an account number yet", "OK", null);
        } else {
            verifyCustomer(appUser.getMfb_code(), acctnum);

            if (!("200".equals(status))) {
                Dialog.show("", "something may have gone wrong", "OK", null);
            } else {

                status = "";

                String returncode = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:Retval");
                String returncodemsg = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retmsg");

                if ("0".equals(returncode)) {


                    String accountname = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:SignatoryName");

                    String accountnumber = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:Accountnumber");

                    String imagephoto = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:CustomerImage");

                    String imagesignature = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:SignatureImage");

                    String customerBalance = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:BookBalance");


                    customer = new Customers(accountname, accountnumber, imagephoto, imagesignature, customerBalance);

                    showForm("CustomerToDeposit", null);
                    //showForm("CustomerToDeposit1", null);
                    //showForm("DepositingCustomer", null);
                } else {

                    Dialog.show("", returncodemsg, "ok", null);
                }

            }

        }
    }

    /**
     * The cancel action on the Deposit form
     */
    @Override
    protected void onDepositAcctNumber_CancelDepositAction(Component c, ActionEvent event) {

        back();
    }

    /**
     * The action to be performed before displaying the customer to perform
     * deposit transaction
     */
    @Override
    protected void beforeCustomerToDeposit(Form f) {

        f.setScrollable(false);

        byte[] photoByte = com.codename1.util.Base64.decode((customer.getPhoto()).getBytes());
        byte[] signatureByte = com.codename1.util.Base64.decode((customer.getSignature()).getBytes());

        try {
            photo = EncodedImage.create(photoByte);
            signature = EncodedImage.create(signatureByte);


            findCustomerPhoto(f).setIcon(photo);
            findCustomerSignature(f).setIcon(signature);
            findAccountNumber(f).setText(customer.getAcctNumber());
            findAccountName(f).setText(customer.getName());

        } catch (Exception io) {
            //System.out.println("error: " + io);
            Dialog.show("Fail!!", "'" + io.getMessage() + "'", "OK", null);
        }

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("TransactionMenu", null);
            }
        };

        Command cont = new Command("Continue") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("AmountToDeposit", null);
            }
        };

        Command cancel = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };

        f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(cont);
        f.getMenuBar().addCommand(cancel);
    }

    /**
     * The cancel action on the form that displays customer to perform deposit
     * transaction
     */
    @Override
    protected void onCustomerToDeposit_CancelDepositAction(Component c, ActionEvent event) {
        back();
    }

    /**
     * This method receives the amount, description and teller number from the
     * "Amount to Deposit" form and calls another form, telling the user to
     * enter transaction password
     */
    @Override
    protected void onAmountToDeposit_SendAmountToDepositAction(Component c, ActionEvent event) {
        tranAmount = findAmountToDepositField(c.getComponentForm()).getText();
        narration = findTranDescArea(c.getComponentForm()).getText();
        tellerNumber = findTellerNumberField(c.getComponentForm()).getText();

        if (("".equals(tranAmount)) || ("".equals(narration)) || ("".equals(tellerNumber))) {
            Dialog.show("", "all fields are required", "OK", null);
        } else {
            showForm("DailyTokenDeposit", null);
        }

    }

    /**
     * The cancel action on "Amount to Deposit" form
     */
    @Override
    protected void onAmountToDeposit_CancelAmountAction(Component c, ActionEvent event) {
        back();
    }

    /**
     * This is the action on "OK" button on the screen displaying the customer
     * to perform deposit action, it opens up another form telling the user to
     * enter Amount to deposit
     */
    @Override
    protected void onCustomerToDeposit_OkToDepositAction(Component c, ActionEvent event) {
        showForm("AmountToDeposit", null);
    }

    /**
     * This action checks the transaction password if it is valid, then performs
     * the deposit transaction
     */
    @Override
    protected void onDailyTokenDeposit_CheckTokenAction(Component c, ActionEvent event) {
        String tranPWD = findTranPWDDepositTextField(c.getComponentForm()).getText();

        GodwinEncrypt encryptMyPassword = new GodwinEncrypt(tranPWD);
        String myEncryptedPassword = encryptMyPassword.asHex();

        if (appUser.getTranPWD().equals(myEncryptedPassword)) {
            //Hashtable storedToken = new Hashtable();
//            try {
//                storedToken = (Hashtable) Storage.getInstance().readObject("TodayToken");
//            } catch (Exception e) {
//                Dialog.show("Error Encountered", e.getMessage(), "OK", null);
//                back();
//            }
//
//            if ((storedToken.get("date").toString()).equals(this.getCurrentDate())) {
//                if ((storedToken.get("token").toString()).equals(token)) {



            performDeposit(customer.getAcctNumber(), tranAmount, appUser.getMfb_code(),
                    narration, tellerNumber, appUser.getUsername() + "" + getRequestID());


            if (!("200".equals(status))) {
                Dialog.show("", "something may have gone wrong", "OK", null);
            } else {

                status = "";

                String returncode = result.getAsString("/s:envelope/S:Body/ns3:CashDepositResponse/return/ns2:Retval");
                String returncodemsg = result.getAsString("/s:envelope/S:Body/ns3:CashDepositResponse/return/ns2:retmsg");

                if ("0".equals(returncode)) {


                    String postSeq = result.getAsString("/s:envelope/S:Body/ns3:CashDepositResponse/return/ns2:postseq");

                    String crdt = "credit";

                    Hashtable storeReply = new Hashtable();
                    storeReply.put("account_number", customer.getAcctNumber());
                    storeReply.put("post_sequence", postSeq);
                    storeReply.put("transaction_type", crdt);
                    storeReply.put("amount", tranAmount);

                    Dialog.show("Transaction Successful", "posted with Sequence number : " + postSeq, "OK", null);

                    // Vector<Hashtable> storedReplies = new Vector<Hashtable>();

                    if ((Storage.getInstance().exists("storedReplies"))) {

                        storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");
                        storedReplies.add(storeReply);
                        try {
                            Storage.getInstance().writeObject("storedReplies", storedReplies);


                        } catch (Exception e) {
                            Dialog.show("", "error saving info " + e.getMessage(), "OK", null);
                        }
                    } else {
                        storedReplies = new Vector<Hashtable>();
                        storedReplies.add(storeReply);
                        try {
                            Storage.getInstance().writeObject("storedReplies", storedReplies);

                        } catch (Exception e) {
                            Dialog.show("", "error saving info, contacting server", "OK", null);
                        }
                    }

                    Dialog.show("", "about to run a check", "OK", null);

                    if ((Storage.getInstance().exists("storedReplies"))) {

                        //storedReplies.add(storeReply);
                        try {
                            storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");


                        } catch (Exception e) {
                            Dialog.show("", "error reading from the storage media", "OK", null);
                            back();
                        }

                        Vector<Hashtable> repliesUploaded = new Vector<Hashtable>();

                        for (int i = 0; i < storedReplies.size(); i++) {
                            Hashtable hashtable = storedReplies.get(i);

                            // System.out.println("Stored Replies to upload ******   " + hashtable.toString());
                            this.uploadTranReplies(hashtable.get("account_number").toString(),
                                    hashtable.get("amount").toString(),
                                    hashtable.get("transaction_type").toString(),
                                    hashtable.get("post_sequence").toString());

                            if ("200".equals(status)) {
                                //Dialog.show("", "something may have gone wrong", "OK", null);

                                status = "";

                                String returncode2 = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retval");
                                String returncodemsg2 = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retmsg");
                                if (("1".equals(returncode2)) && ("Data Logged".equals(returncodemsg2))) {
                                    repliesUploaded.add(hashtable);
                                }
                            }
                        }

                        if (!(repliesUploaded.isEmpty())) {

                            for (int i = 0; i < repliesUploaded.size(); i++) {

                                Hashtable hashtable2 = repliesUploaded.get(i);

                                for (int j = 0; j < storedReplies.size(); j++) {
                                    Hashtable hashtable3 = storedReplies.get(j);

                                    if (hashtable2.equals(hashtable3)) {
                                        storedReplies.removeElementAt(j);
                                    }
                                }

                            }

                            if (!(repliesUploaded.isEmpty())) {
                                try {

                                    Storage.getInstance().writeObject("storedReplies", storedReplies);
                                    //Dialog.show("", "Transaction stored", "ok", null);

                                    storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
                                    // System.out.println("Stored Replies : " + storedReplies.toString());
                                    //back();

                                } catch (Exception e) {
                                    Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
                                }



                            } else {
                                Storage.getInstance().deleteStorageFile("storedReplies");
                            }

                        }
                    }

                    back();

                } else {

                    Dialog.show("", returncodemsg, "ok", null);
                }
            }
//                } else {
//                    Dialog.show("Sorry", "Token does not match", "OK", null);
//                    //showForm("Main", null);
////                }
//            } else {
//                Dialog.show("Sorry", "Token has expired, please re-login", "OK", null);
//                showForm("Main", null);
            // }
        } else {
            Dialog.show("Sorry", "password does not match", "OK", null);
//            showForm("Main", null);
        }
    }

    /**
     * This is the cancel action on the form that asks for transaction password
     */
    @Override
    protected void onDailyTokenDeposit_CancelCheckTokenAction(Component c, ActionEvent event) {
        back();
    }

    /**
     * This is the action on the list of mfbs (no longer in use)
     */
    @Override
    protected void onWithdrawalMFBs_WithdrawaBankMultiListAction(Component c, ActionEvent event) {
        List l = (List) c;
        Hashtable h = (Hashtable) l.getSelectedItem();

        mfb = new MFBs((String) h.get("mfb_name"), (String) h.get("mfb_id"));

        showForm("WithdrawalAcctNumber", null);
    }

    /**
     * This method gets the account number to perform withdrawal transaction and
     * sends it for verification
     */
    @Override
    protected void onWithdrawalAcctNumber_WithdrawalVerifyAction(Component c, ActionEvent event) {


        String accountNumber = findWithdrawalAcctNumberTextField(c.getComponentForm()).getText();

        if (("".equals(accountNumber))) {

            Dialog.show("", " please provide account number", "OK", null);
        } else {

            this.verifyCustomer(appUser.getMfb_code(), accountNumber);

            if (!("200".equals(status))) {
                Dialog.show("", "something may have gone wrong", "OK", null);
            } else {
                status = "";

                String returncode = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retval");
                String returncodemsg = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retmsg");

                if ("0".equals(returncode)) {


                    String accountname = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:SignatoryName");

                    String accountnumber = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:Accountnumber");

                    String imagephoto = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:CustomerImage");

                    String imagesignature = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:SignatureImage");

                    String customerBalance = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:BookBalance");


                    customer = new Customers(accountname, accountnumber, imagephoto, imagesignature, customerBalance);

                    showForm("CustomerToWithdraw", null);
                } else {

                    Dialog.show("", returncodemsg, "ok", null);
                }

            }
        }
    }

    /**
     * This method displays the customer gotten from the verification of
     * customer to perform withdrawal transaction
     */
    @Override
    protected void beforeCustomerToWithdraw(Form f) {

        f.setScrollable(false);

        byte[] photoByte = com.codename1.util.Base64.decode((customer.getPhoto()).getBytes());
        byte[] signatureByte = com.codename1.util.Base64.decode((customer.getSignature()).getBytes());

        try {
            photo = EncodedImage.create(photoByte);
            signature = EncodedImage.create(signatureByte);


            findCustomerPhoto(f).setIcon(photo);
            findSignature(f).setIcon(signature);
            findAccountNumber(f).setText(customer.getAcctNumber());
            findAccountName(f).setText(customer.getName());

        } catch (Exception io) {
            // System.out.println("error: " + io);
            Dialog.show("Failed!!", "'" + io.getMessage() + "'", "OK", null);
        }

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("TransactionMenu", null);
            }
        };


        Command cont = new Command("OK") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("AmountToWithdraw", null);
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };

        f.setBackCommand(cancel);
        f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(cont);
        //f.getMenuBar().addCommand(cancel);

    }

    /**
     * The action of the "OK" button on The form that displays a customer to be
     * verified(The Form shown above)
     */
    @Override
    protected void onCustomerToWithdraw_ContinueAction(Component c, ActionEvent event) {
        showForm("AmountToWithdraw", null);
    }

    /**
     * The action on the Cancel button on the form "Customer To Withdraw"
     */
    @Override
    protected void onCustomerToWithdraw_CancelAction(Component c, ActionEvent event) {
        back();
    }

    /**
     * This method gets the amount and narration of the withdrawal transaction
     * and then calls the form where you will asked to state the teller number
     */
    @Override
    protected void onAmountToWithdraw_SendWithdrawAction(Component c, ActionEvent event) {

        tranAmount = findAmountToWithdrawTextField(c.getComponentForm()).getText();
        narration = findWithdrawalNarrationTextArea(c.getComponentForm()).getText();

        if (("".equals(tranAmount)) || ("".equals(narration))) {
            Dialog.show("", "both fields are required", "OK", null);
        } else {
            try {
                int intAmount = Integer.parseInt(tranAmount);
                double charge = (0.001 * intAmount);
                tranCharge = String.valueOf(charge);

                Dialog.show("", "charge on Transaction : " + tranCharge, 0, null, "OK", null);
                showForm("InstrumentForm", null);
            } catch (Exception e) {
                // System.out.println(e.getMessage());
                Dialog.show("", "please check the 'Amount' field for non-numeric character ", "OK", null);
            }
        }
    }

    /**
     * The action on "Cancel" button of the Form that asks for Amount and
     * Narration
     */
    @Override
    protected void onAmountToWithdraw_CancelWithdrawAction(Component c, ActionEvent event) {
        back();
    }

    /**
     * This method gets the account number to perform withdrawal transaction and
     * sends it for verification
     */
    @Override
    protected void onInstrumentForm_WithdrawAction(Component c, ActionEvent event) {

        instrumentNo = findInstrumentNumberField(c.getComponentForm()).getText();
        tellerNo = findTellerNumberTextField(c.getComponentForm()).getText();

        if (((!("".equals(instrumentNo))) && (!("".equals(tellerNo)))) || ((("".equals(tellerNo))) && ("".equals(instrumentNo)))) {
            Dialog.show("", "either  of the fields is required", "OK", null);
        } else {

            showForm("DailyTokenWithdrawal", null);
        }
    }

    /**
     * action on the "Cancel" button of the form that asks for Teller Number
     */
    @Override
    protected void onInstrumentForm_CancelAction(Component c, ActionEvent event) {
        back();
    }

    /**
     * This method gets the transaction password of the Teller, checks if
     * correct and then performs the withdrawal
     */
    @Override
    protected void onDailyTokenWithdrawal_CheckTokenToWithdrawAction(Component c, ActionEvent event) {
        String tranPWD = findWithdrawalTranPWDTextField(c.getComponentForm()).getText();

        GodwinEncrypt encryptMyPassword = new GodwinEncrypt(tranPWD);
        String myEncryptedPassword = encryptMyPassword.asHex();

        if (appUser.getTranPWD().equals(myEncryptedPassword)) {
//            Hashtable storedToken = new Hashtable();
//            try {
//                storedToken = (Hashtable) Storage.getInstance().readObject("TodayToken");
//            } catch (Exception e) {
//                Dialog.show("Error Encountered", e.getMessage(), "OK", null);
//                back();
//            }

//            //if ((storedToken.get("date").toString()).equals(this.getCurrentDate())) {
//            if (((!("".equals(instrumentNo))) && (!("".equals(tellerNo)))) || ((("".equals(tellerNo))) && ("".equals(instrumentNo)))) {
//                Dialog.show("", "either  of the fields is required", "OK", null);
//            } else {

            if ((instrumentNo == null) || ("".equals(instrumentNo))) {
                //  System.out.println("Transaction on non-cheque");

                this.doWithdrawal(customer.getAcctNumber(), appUser.getMfb_code(), tranAmount, narration, tellerNo, tranCharge, appUser.getUsername() + "" + getRequestID());

                if (!("200".equals(status))) {
                    Dialog.show("", "something may have gone wrong", "OK", null);
                } else {

                    status = "";

                    String returncode = result.getAsString("/s:envelope/S:Body/ns3:CashWithdrawalResponse/return/ns2:Retval");
                    String returncodemsg = result.getAsString("/s:envelope/S:Body/ns3:CashWithdrawalResponse/return/ns2:Retmsg");

                    if ("0".equals(returncode)) {


                        String postSeq = result.getAsString("/s:envelope/S:Body/ns3:CashWithdrawalResponse/return/ns2:postseq");

                        String crdt = "Debit";
                        Hashtable storeReply = new Hashtable();
                        storeReply.put("account_number", customer.getAcctNumber());
                        storeReply.put("post_sequence", postSeq);
                        storeReply.put("transaction_type", crdt);
                        storeReply.put("amount", tranAmount);

                        Dialog.show("Transaction Successful", "Sequence number : " + postSeq, "ok", null);
                        // back();

                        if ((Storage.getInstance().exists("storedReplies"))) {

                            storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");
                            storedReplies.add(storeReply);
                            try {
                                Storage.getInstance().writeObject("storedReplies", storedReplies);


                            } catch (Exception e) {
                                Dialog.show("", "error saving info " + e.getMessage(), "OK", null);
                            }
                        } else {
                            storedReplies = new Vector<Hashtable>();
                            storedReplies.add(storeReply);
                            try {
                                Storage.getInstance().writeObject("storedReplies", storedReplies);

                            } catch (Exception e) {
                                Dialog.show("", "error saving info, contacting server", "OK", null);
                            }
                        }

                        Dialog.show("", "about to run a check", "OK", null);

                        if ((Storage.getInstance().exists("storedReplies"))) {

                            //storedReplies.add(storeReply);
                            try {
                                storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");


                            } catch (Exception e) {
                                Dialog.show("", "error saving info, contacting server", "OK", null);
                                back();
                            }

                            Vector<Hashtable> repliesUploaded = new Vector<Hashtable>();

                            for (int i = 0; i < storedReplies.size(); i++) {
                                Hashtable hashtable = storedReplies.get(i);

                                // System.out.println("Stored Replies to upload ******   " + hashtable.toString());
                                this.uploadTranReplies(hashtable.get("account_number").toString(),
                                        hashtable.get("amount").toString(),
                                        hashtable.get("transaction_type").toString(),
                                        hashtable.get("post_sequence").toString());

                                if ("200".equals(status)) {
                                    //Dialog.show("", "something may have gone wrong", "OK", null);

                                    status = "";

                                    String returncode2 = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retval");
                                    String returncodemsg2 = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retmsg");
                                    if (("1".equals(returncode2)) && ("Data Logged".equals(returncodemsg2))) {
                                        repliesUploaded.add(hashtable);
                                    }
                                }
                            }

                            if (!(repliesUploaded.isEmpty())) {

                                for (int i = 0; i < repliesUploaded.size(); i++) {

                                    Hashtable hashtable2 = repliesUploaded.get(i);

                                    for (int j = 0; j < storedReplies.size(); j++) {
                                        Hashtable hashtable3 = storedReplies.get(j);

                                        if (hashtable2.equals(hashtable3)) {
                                            storedReplies.removeElementAt(j);
                                        }
                                    }

                                }

                                if (!(repliesUploaded.isEmpty())) {
                                    try {

                                        Storage.getInstance().writeObject("storedReplies", storedReplies);
                                        //Dialog.show("", "Transaction stored", "ok", null);

                                        storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
                                        // System.out.println("Stored Replies : " + storedReplies.toString());
                                        //back();

                                    } catch (Exception e) {
                                        Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
                                    }



                                } else {
                                    Storage.getInstance().deleteStorageFile("storedReplies");
                                }

                            }
                        }

                        back();


                    } else {

                        Dialog.show("", returncodemsg, "ok", null);
                    }

                }

            } else {
                // System.out.println("Transaction on cheque");
                doChequeWithdrawal(customer.getAcctNumber(), appUser.getMfb_code(), tranAmount,
                        narration, instrumentNo, tranCharge, appUser.getUsername() + "" + getRequestID());

                if (!("200".equals(status))) {
                    Dialog.show("", "something may have gone wrong", "OK", null);
                } else {

                    status = "";
                    String returncode = result.getAsString("/s:envelope/s:body/ns2:dochequewithdrawalresponse/return/ns2:retval");
                    String returncodemsg = result.getAsString("/s:envelope/s:body/ns2:dochequewithdrawalresponse/return/ns2:retmsg");

                    if ("0".equals(returncode)) {


                        String postSeq = result.getAsString("/s:envelope/s:body/ns2:dochequewithdrawalresponse/return/ns3:postseq");

                        //String accountnumber = result.getAsString("/s:envelope/s:body/ns2:dowithdrawalresponse/return/accountnumber");

                        //              Dialog.show("Transaction Successful", "Sequence number : " + postSeq, "ok", null);

                        String crdt = "Debit";
                        Hashtable storeReply = new Hashtable();
                        storeReply.put("account_number", customer.getAcctNumber());
                        storeReply.put("post_sequence", postSeq);
                        storeReply.put("transaction_type", crdt);
                        storeReply.put("amount", tranAmount);

                        Dialog.show("Transaction Successful", "Sequence number : " + postSeq, "ok", null);
                        // back();

                        if ((Storage.getInstance().exists("storedReplies"))) {

                            storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");
                            storedReplies.add(storeReply);
                            try {
                                Storage.getInstance().writeObject("storedReplies", storedReplies);


                            } catch (Exception e) {
                                Dialog.show("", "error saving info " + e.getMessage(), "OK", null);
                            }
                        } else {
                            storedReplies = new Vector<Hashtable>();
                            storedReplies.add(storeReply);
                            try {
                                Storage.getInstance().writeObject("storedReplies", storedReplies);

                            } catch (Exception e) {
                                Dialog.show("", "error saving info, contacting server", "OK", null);
                            }
                        }

                        Dialog.show("", "about to run a check", "OK", null);

                        if ((Storage.getInstance().exists("storedReplies"))) {

                            //storedReplies.add(storeReply);
                            try {
                                storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedReplies");


                            } catch (Exception e) {
                                Dialog.show("", "error saving info, contacting server", "OK", null);
                                back();
                            }

                            Vector<Hashtable> repliesUploaded = new Vector<Hashtable>();

                            for (int i = 0; i < storedReplies.size(); i++) {
                                Hashtable hashtable = storedReplies.get(i);

                                // System.out.println("Stored Replies to upload ******   " + hashtable.toString());
                                this.uploadTranReplies(hashtable.get("account_number").toString(),
                                        hashtable.get("amount").toString(),
                                        hashtable.get("transaction_type").toString(),
                                        hashtable.get("post_sequence").toString());

                                if ("200".equals(status)) {
                                    //Dialog.show("", "something may have gone wrong", "OK", null);

                                    status = "";

                                    String returncode2 = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retval");
                                    String returncodemsg2 = logResponse.getAsString("/soap:Envelope/soap:Body/MTellerTransResponseResponse/MTellerTransResponseResult/Retmsg");
                                    if (("1".equals(returncode2)) && ("Data Logged".equals(returncodemsg2))) {
                                        repliesUploaded.add(hashtable);
                                    }
                                }
                            }

                            if (!(repliesUploaded.isEmpty())) {

                                for (int i = 0; i < repliesUploaded.size(); i++) {

                                    Hashtable hashtable2 = repliesUploaded.get(i);

                                    for (int j = 0; j < storedReplies.size(); j++) {
                                        Hashtable hashtable3 = storedReplies.get(j);

                                        if (hashtable2.equals(hashtable3)) {
                                            storedReplies.removeElementAt(j);
                                        }
                                    }

                                }

                                if (!(repliesUploaded.isEmpty())) {
                                    try {

                                        Storage.getInstance().writeObject("storedReplies", storedReplies);
                                        //Dialog.show("", "Transaction stored", "ok", null);

                                        storedReplies = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");
                                        //      System.out.println("Stored Replies : " + storedReplies.toString());
                                        //back();

                                    } catch (Exception e) {
                                        Dialog.show("", "error reading storage media " + "'" + e.getMessage() + "'", "ok", null);
                                    }



                                } else {
                                    Storage.getInstance().deleteStorageFile("storedReplies");
                                }

                            }
                        }

                        back();



                        //  back();

                    } else {

                        Dialog.show("", returncodemsg, "ok", null);
                    }

                }
            }
            //}
//            } else {
//                Dialog.show("Sorry", "Token has expired, please re-login", "OK", null);
//                showForm("Main", null);
//            }
        } else {
            Dialog.show("Sorry", "wrong transaction password", "OK", null);
            //showForm("Main", null);
        }
    }

    /**
     * The action of the "cancel" button on the form that asks for transaction
     * password
     */
    @Override
    protected void onDailyTokenWithdrawal_CancelWithdrawaTokenAction(Component c, ActionEvent event) {
        back();
    }

    /**
     * When the Teller enters an account number for Balance Inquiry, This method
     * performs the Inquiry
     */
    @Override
    protected void onBalInquiryAcctNumber_VerifyBalInquiryAction(Component c, ActionEvent event) {

        String accountNumber = findBalInquiryAcctNumberTextField(c.getComponentForm()).getText();


        if (("".equals(accountNumber))) {

            Dialog.show("", " please provide account number", "OK", null);
        } else {

            this.verifyCustomer(appUser.getMfb_code(), accountNumber);

            if (!("200".equals(status))) {
                Dialog.show("", "something may have gone wrong", "OK", null);
            } else {

                status = "";

                String returncode = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retval");
                String returncodemsg = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retmsg");

                if ("0".equals(returncode)) {


                    String accountname = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:SignatoryName");

                    String accountnumber = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:Accountnumber");

                    String imagephoto = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:CustomerImage");

                    String imagesignature = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:SignatureImage");

                    String customerBalance = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:BookBalance");



                    customer = new Customers(accountname, accountnumber, imagephoto, imagesignature, customerBalance);

                    showForm("BalInquiryDisplay", null);
                } else {

                    Dialog.show("", returncodemsg, "ok", null);
                }

            }
        }
    }

    /**
     * action of the "Cancel" button on the Balance Inquiry form
     */
    @Override
    protected void onBalInquiryAcctNumber_CancelBalInquiryAction(Component c, ActionEvent event) {
        back();
    }

    /**
     * This method displays the details of the Balance Inquiry
     */
    @Override
    protected void beforeBalInquiryDisplay(Form f) {


        byte[] photoByte = com.codename1.util.Base64.decode((customer.getPhoto()).getBytes());
        byte[] signatureByte = com.codename1.util.Base64.decode((customer.getSignature()).getBytes());

        try {
            photo = EncodedImage.create(photoByte);
            signature = EncodedImage.create(signatureByte);


            findCustomerPhoto(f).setIcon(photo);
            findCustomerSignature(f).setIcon(signature);
            findCustAcctNo(f).setText(customer.getAcctNumber());
            findCustAcctName(f).setText(customer.getName());
            findCurrentBalance(f).setText("Balance : " + customer.getCurrentBal());

        } catch (Exception io) {
            Dialog.show("", "error occured trying to display image" + "'" + io.getMessage() + "'", "ok", null);
            //System.out.println("error: " + io);
        }

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("TransactionMenu", null);
            }
        };

        Command cancel = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };

        f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(cancel);
    }

    /**
     * action on the "OK" button on the Balance Inquiry Display form
     */
    @Override
    protected void onBalInquiryDisplay_BalInquiryOKAction(Component c, ActionEvent event) {

        back();
    }

    /**
     * action on each MFB for Mini Statement(Not in Use)
     */
    @Override
    protected void onMiniStatMFBs_MiniStatMFBsMultiListAction(Component c, ActionEvent event) {

        List l = (List) c;
        Hashtable h = (Hashtable) l.getSelectedItem();

        mfb = new MFBs((String) h.get("mfb_name"), (String) h.get("mfb_id"));

        showForm("MiniStatForm", null);
    }

    /**
     * Action on the "OK" button when the Teller enters account number for mini
     * statement
     */
    @Override
    protected void onMiniStatForm_MiniStatOKAction(Component c, ActionEvent event) {

        String acctNumber = findMiniStatAcctNumberTextField(c.getComponentForm()).getText();
//event.getComponent()
        if ("".equals(acctNumber)) {
            Dialog.show("", "please provide account number", "OK", null);
        } else {

            this.fetchMiniStatementFor(acctNumber, appUser.getMfb_code());

            if (!("200".equals(status))) {
                Dialog.show("", "something may have gone wrong", "OK", null);
            } else {

                status = "";

                try {

                    allReturns = new Vector<Hashtable>();

                    for (Object o : result.getAsArray("/s:envelope/s:body/ns3:MiniStatementsResponse/return/ns2:MiniStatement")) {
                        Result lineitem = Result.fromContent(((Element) o).getChildAt(0));

                        returns = new Hashtable<String, String>();

                        returns.put("AccountNumber", lineitem.getAsString("ns2:Accountnumber"));
                        returns.put("TransactionDate", lineitem.getAsString("ns2:Trandate"));
                        returns.put("BranchName", lineitem.getAsString("ns2:BranchName"));
                        returns.put("BranchAddress", lineitem.getAsString("ns2:BranchAddress"));
                        returns.put("ValueDate", lineitem.getAsString("ns2:ValueDate"));
                        returns.put("Narration", lineitem.getAsString("ns2:Narration"));
                        returns.put("FullName", lineitem.getAsString("ns2:Fullname"));
                        returns.put("Credit", lineitem.getAsString("ns2:Credit"));
                        returns.put("Debit", lineitem.getAsString("ns2:Debit"));
                        returns.put("OpenBalance", lineitem.getAsString("ns2:OpenBalance"));
                        returns.put("BookBallance", lineitem.getAsString("ns2:BkBalance"));
                        returns.put("StartDate", lineitem.getAsString("ns2:Startdate"));
                        returns.put("EndDate", lineitem.getAsString("ns2:EndDate"));
                        returns.put("Amount", lineitem.getAsString("ns2:Amount"));
                        returns.put("TellerNumber", lineitem.getAsString("ns2:TellerNo"));
                        returns.put("PostingSequence", lineitem.getAsString("ns2:PostingSeq"));
                        returns.put("RetVal", lineitem.getAsString("ns2:Retval"));
                        returns.put("RetMessage", lineitem.getAsString("ns2:retmsg"));

                        allReturns.add(returns);

                    }
                    //System.out.println(allReturns.toString());

                    showForm("MiniStatDisplay", null);
                } catch (Exception e) {
                    Dialog.show("", "something may have gone wrong " + "'" + e.getMessage() + "'", "OK", null);
                }


            }


        }
    }

    /**
     * cancel action on Mini statement Form
     */
    @Override
    protected void onMiniStatForm_CancelAction(Component c, ActionEvent event) {
        back();
    }

    /**
     * List model for Mini statement MFBs(Not in use)
     */
    @Override
    protected boolean initListModelMiniStatMultiList(List cmp) {
        cmp.setModel(new DefaultListModel(allReturns));
        return true;
    }

    /**
     * On the List of Mini statements, when each is selected, this method
     * displays the full details of Each mini statement
     */
    @Override
    protected void onMiniStatDisplay_MiniStatMultiListAction(Component c, ActionEvent event) {
        List l = (List) c;
        Hashtable h = (Hashtable) l.getSelectedItem();

        if ((h.get("Credit") == null) || ("".equals(h.get("Credit")))) {

            miniStatement = new MiniStatements(h.get("AccountNumber").toString(), h.get("TransactionDate").toString(), h.get("BranchName").toString(), h.get("BranchAddress").toString(), h.get("ValueDate").toString(), h.get("Narration").toString(), h.get("FullName").toString(), h.get("Debit").toString(), h.get("OpenBalance").toString(), h.get("BookBallance").toString(), h.get("StartDate").toString(), h.get("EndDate").toString(),
                    h.get("Amount").toString(), h.get("TellerNumber").toString(), h.get("PostingSequence").toString(), h.get("RetMessage").toString());
            credit = false;

        } else {
            credit = true;
            miniStatement = new MiniStatements(h.get("AccountNumber").toString(), h.get("TransactionDate").toString(), h.get("BranchName").toString(), h.get("BranchAddress").toString(), h.get("ValueDate").toString(), h.get("Narration").toString(), h.get("FullName").toString(), h.get("Credit").toString(), h.get("OpenBalance").toString(), h.get("BookBallance").toString(), h.get("StartDate").toString(), h.get("EndDate").toString(),
                    h.get("Amount").toString(), h.get("TellerNumber").toString(), h.get("PostingSequence").toString(), h.get("RetMessage").toString());

        }

        showForm("EachMiniStat", null);
    }

    /**
     * The Form that displays the Mini statements
     */
    @Override
    protected void beforeMiniStatDisplay(Form f) {

        findAccountName(f).setText(mfb.getName());

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("TransactionMenu", null);
            }
        };

        f.setBackCommand(home);
        f.getMenuBar().addCommand(home);
    }

    /**
     * the form that displays Each of the mini statements when selected
     */
    @Override
    protected void beforeEachMiniStat(Form f) {

        f.setScrollable(false);
        findLabel(f).setText(miniStatement.getAccountNumber());
        //findMiniStatAccount(f).setText(miniStatement.getAccountNumber());
        findMiniStatTranDate(f).setText("Transaction Date : " + miniStatement.getTranDate());
        findMiniStatBranchName(f).setText(miniStatement.getBranchname());
        findMiniStatBranchAddress(f).setText(miniStatement.getBranchAddress());
        findMiniStatBranchAddress(f).setEditable(false);
        findMiniStatOpenBalance(f).setText("Open Balance : " + miniStatement.getOpenBalance());
        findMiniStatBookBalance(f).setText("Book Balance : " + miniStatement.getBookBalance());
        findMiniStatValueDate(f).setText("Value Date : " + miniStatement.getValueDate());
        findMiniStatNarration(f).setText(miniStatement.getNarration());
        findMiniStatFullName(f).setText(miniStatement.getFullName());

        if (credit == false) {
            findMiniStatCreditOrDebit(f).setText("Debit : " + miniStatement.getDebitOrCredit());
        } else {
            findMiniStatCreditOrDebit(f).setText("Credit : " + miniStatement.getDebitOrCredit());
            //findMiniStatCreditOrDebit(f).setVisible(true);
        }

        findMiniStatStartDate(f).setText("Start Date : " + miniStatement.getStartDate());
        findMiniStatEndDate(f).setText("End Date : " + miniStatement.getEndDate());
        findMiniStatChargeAmount(f).setText("Charge Amount : " + miniStatement.getChargeAmount());
        findMiniStatTellerNumber(f).setText("Teller Number : " + miniStatement.getTellerNumber());
        findMiniStatPostingSeq(f).setText("Posting Sequence : " + miniStatement.getPostingSeq());
        findMiniStatRetMessage(f).setText(miniStatement.getRetMessage());

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("TransactionMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(null);
    }

    /**
     * The Form that displays Offline Menu
     */
    @Override
    protected void beforeOfflineMenu(Form f) {

        Command logout = new Command("Logout") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                // Dialog.show("", "about to logout", "OK", null);

                Command[] cmds = new Command[2];
                cmds[0] = new Command("OK") {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        showForm("Main", null);

                    }
                };
                cmds[1] = new Command("Cancel") {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        //do Option2
                    }
                };

                Dialog.show("Log Out", new Label("Please confirm"), cmds);

            }
        };

//        MenuBar mb = new MenuBar();
//        mb.addCommand(logout);
        f.getMenuBar().addCommand(logout);
        f.setBackCommand(logout);

        Command pullAcct = new Command("Pull Accounts") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                if (Storage.getInstance().exists("Application_Settings")) {
                    app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
                    showForm("AccountToPullInfo", null);
                } else {
                    Dialog.show("Sorry", "You need to provide Application Settings before you can pull Customer Accounts", "OK", null);
                }

            }
        };

        Command viewOff = new Command("View Offline Transactions") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                if (Storage.getInstance().exists("storedTransactions")) {
                    try {
                        offlineTransactionsStored = (Vector<Hashtable>) Storage.getInstance().readObject("storedTransactions");

                        showForm("OfflineTransactionsDisplay", null);

                    } catch (Exception e) {
                        Dialog.show("Error!!", e.getMessage(), "OK", null);
                    }
                } else {
                    Dialog.show("", "you may have not performed any offline transactions to display", "OK", null);
                }
            }
        };

        Command depOff = new Command("Deposit") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                if (Storage.getInstance().exists("storedCustomers")) {
                    customersToStore = new Vector<Hashtable>();
                    try {
                        customersToStore = (Vector<Hashtable>) Storage.getInstance().readObject("storedCustomers");
                        showForm("StoredCustomers", null);
                    } catch (Exception exception) {
                        Dialog.show("", "error reading storage media " + "'" + exception.getMessage() + "'", "OK", null);
                    }

                } else {
                    Dialog.show("", "you may not have stored customers for offline transactions", "OK", null);
                }
            }
        };

        f.getMenuBar().addCommand(depOff);
        f.getMenuBar().addCommand(viewOff);
        f.getMenuBar().addCommand(pullAcct);
    }

    /**
     * The Form that displays the Main Form(The Index form)
     */
    @Override
    protected void beforeMain(Form f) {
        //Storage.getInstance().deleteStorageFile("OfflineTeller");
//        OfflineTeller = (Hashtable<String, String>) Storage.getInstance().readObject("OfflineTeller");
//        System.out.println(OfflineTeller);
        //Storage.getInstance().exists("Application_Settings")
        // Storage.getInstance().deleteStorageFile("Application_Settings");
//Storage.getInstance().deleteStorageFile("OfflineTeller");
//        Command back = new Command("back") {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
////                showForm("TransactionMenu", null);
//            }
//        };
//        f.setBackCommand(back);

        f.setScrollable(false);

        Command logout = new Command("Exit") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //   try {
//                    if (evt == null) {
//                        System.out.println("user cancelled");
//                        return;
//                    }

                Command[] cmds = new Command[2];
                cmds[0] = new Command("OK") {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        // try {
//                                if (evt == null) {
//                                    System.out.println("user cancelled");
//                                    return;
//                                }
                        Display.getInstance().exitApplication();
//                            } catch (Exception e) {
//                                Dialog.show("", e.getMessage(), "OK", null);
//                            }
                    }
                };
                cmds[1] = new Command("Cancel") {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        //do Option2
                    }
                };

                // Dialog.show("Log Out", "Please confirm", cmds, BACK_COMMAND_ID, null, 0);
                Dialog.show("Exit Appllication", new Label("Please confirm"), cmds);

//                } catch (Exception e) {
//                    Dialog.show("", e.getMessage(), "OK", null);
//                }
            }
        };


        f.addCommand(logout);

    }

    /**
     * The Form that displays the transaction menu
     */
    @Override
    protected void beforeTransactionMenu(Form f) {

        f.setScrollable(false);
        Command back = new Command("back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                showForm("TransactionMenu", null);
            }
        };
        f.setBackCommand(back);

        Command logout = new Command("Logout") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //   try {
//                    if (evt == null) {
//                        System.out.println("user cancelled");
//                        return;
//                    }

                Command[] cmds = new Command[2];
                cmds[0] = new Command("OK") {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        // try {
//                                if (evt == null) {
//                                    System.out.println("user cancelled");
//                                    return;
//                                }
                        showForm("Main", null);
//                            } catch (Exception e) {
//                                Dialog.show("", e.getMessage(), "OK", null);
//                            }
                    }
                };
                cmds[1] = new Command("Cancel") {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        //do Option2
                    }
                };

                // Dialog.show("Log Out", "Please confirm", cmds, BACK_COMMAND_ID, null, 0);
                Dialog.show("Log Out", new Label("Please confirm"), cmds);

//                } catch (Exception e) {
//                    Dialog.show("", e.getMessage(), "OK", null);
//                }
            }
        };


        f.getMenuBar().addCommand(logout);


    }

    /**
     * Not in use
     */
    @Override
    protected void beforeBalInquiryMFBs(Form f) {


        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("TransactionMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
    }

    /**
     * Not in use
     */
    @Override
    protected void beforeDepositMFBs(Form f) {


        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("TransactionMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);

        Command cancel = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };


        f.getMenuBar().addCommand(cancel);
    }

    /**
     * Not in use
     */
    @Override
    protected void beforeMiniStatMFBs(Form f) {


        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("TransactionMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(home);
    }

    /**
     * Not in use
     */
    @Override
    protected void beforeWithdrawalMFBs(Form f) {


        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("TransactionMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
    }

    /**
     * The Form that displays the Customers stored for Offline Transactions
     */
    @Override
    protected void beforeStoredCustomers(Form f) {
//        List l = findStoredCustomersMultiList(f);
//        l.setModel(new DefaultListModel(customersToStore));
        f.setScrollable(false);

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("OfflineMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(home);
    }

    /**
     * The action on Each customer stored for offline transaction, taking the
     * Teller to the offline deposit form
     */
    @Override
    protected void onStoredCustomer_ShowDepositOfflineAction(Component c, ActionEvent event) {

        showForm("OfflineDepositForm", null);
    }

    /**
     * Cancel action on the Offline Transaction customers Form
     */
    @Override
    protected void onStoredCustomer_CancelAction(Component c, ActionEvent event) {

        back();
    }

    /**
     * The Form that displays Offline Deposit Form
     */
    @Override
    protected void beforeOfflineDepositForm(Form f) {

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("OfflineMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(null);
    }

    /**
     * Cancel action on the Online withdrawal Form
     */
    @Override
    protected void onWithdrawalAcctNumber_CancelWithdrawalAction(Component c, ActionEvent event) {

        back();
    }

    /**
     * This method enforces the User to enter password of more than 6 characters
     */
    @Override
    protected void onNewAppUser_NewUserPasswordTextFieldAction(Component c, ActionEvent event) {

        String pwd = findNewUserPasswordTextField(c.getComponentForm()).getText();
        if (pwd.length() <= 6) {
            Dialog.show("", "password should be more than six(6) characters", "OK", null);
            findNewUserPasswordTextField(c.getComponentForm()).requestFocus();
        }
    }

    /**
     * This method enforces the user to enter more than 6 characters as
     * Transaction password
     */
    @Override
    protected void onNewAppUser_TranPasswordTextFieldAction(Component c, ActionEvent event) {

        String pwd = findTranPasswordTextField(c.getComponentForm()).getText();
        if (pwd.length() <= 6) {
            Dialog.show("", "password should be more than six(6) characters", "OK", null);
            findTranPasswordTextField(c.getComponentForm()).requestFocus();
        }

    }

    /**
     * The Form that asks the Teller to enter Deposit amount(Online Deposit).
     */
    @Override
    protected void beforeAmountToDeposit(final Form f) {

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("TransactionMenu", null);
            }
        };

        Command deposit = new Command("Deposit") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                tranAmount = findAmountToDepositField(f).getText();
                narration = findTranDescArea(f).getText();
                tellerNumber = findTellerNumberField(f).getText();

                if (("".equals(tranAmount)) || ("".equals(narration)) || ("".equals(tellerNumber))) {
                    Dialog.show("", "all fields are required", "OK", null);
                } else {
                    showForm("DailyTokenDeposit", null);
                }
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };

        f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(deposit);
        f.getMenuBar().addCommand(cancel);

    }

    /**
     * The Form that asks the Teller to enter amount to withdraw
     */
    @Override
    protected void beforeAmountToWithdraw(final Form f) {


        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("TransactionMenu", null);
            }
        };

        Command ok = new Command("OK") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                tranAmount = findAmountToWithdrawTextField(f).getText();
                narration = findWithdrawalNarrationTextArea(f).getText();

                if (("".equals(tranAmount)) || ("".equals(narration))) {
                    Dialog.show("", "both fields are required", "OK", null);
                } else {
                    try {
                        int intAmount = Integer.parseInt(tranAmount);
                        double charge = (0.001 * intAmount);
                        tranCharge = String.valueOf(charge);

                        Dialog.show("", "charge on Transaction : " + tranCharge, 0, null, "OK", null);
                        showForm("InstrumentForm", null);
                    } catch (Exception e) {
                        // System.out.println(e.getMessage());
                        Dialog.show("", "please check the 'Amount' field for non-numeric character ", "OK", null);
                    }
                }
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };

        f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(ok);
        f.getMenuBar().addCommand(cancel);
    }

    /**
     * The Form that asks the teller to enter Account Number for Balance Inquiry
     */
    @Override
    protected void beforeBalInquiryAcctNumber(final Form f) {

        Command inquire = new Command("Inquire") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                String accountNumber = findBalInquiryAcctNumberTextField(f).getText();


                if (("".equals(accountNumber))) {

                    Dialog.show("", " please provide account number", "OK", null);
                } else {

                    verifyCustomer(appUser.getMfb_code(), accountNumber);

                    if (!("200".equals(status))) {
                        Dialog.show("", "something may have gone wrong", "OK", null);
                    } else {

                        status = "";

                        String returncode = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retval");
                        String returncodemsg = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retmsg");

                        if ("0".equals(returncode)) {


                            String accountname = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:SignatoryName");

                            String accountnumber = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:Accountnumber");

                            String imagephoto = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:CustomerImage");

                            String imagesignature = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:SignatureImage");

                            String customerBalance = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:BookBalance");



                            customer = new Customers(accountname, accountnumber, imagephoto, imagesignature, customerBalance);

                            showForm("BalInquiryDisplay", null);
                        } else {

                            Dialog.show("", returncodemsg, "ok", null);
                        }

                    }
                }
            }
        };

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("TransactionMenu", null);
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };

        f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(cancel);
        f.getMenuBar().addCommand(inquire);
    }

    /**
     * The Form that asks the Teller to enter Transaction password
     */
    @Override
    protected void beforeDailyTokenWithdrawal(Form f) {

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("TransactionMenu", null);
            }
        };

        Command cancel = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };

        f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(cancel);

    }

    /**
     * The Form that displays deposit form
     */
    @Override
    protected void beforeDepositAcctNumber(Form f) {

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("TransactionMenu", null);
            }
        };

        Command cancel = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };

        f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(cancel);
    }

    /**
     * The Form that asks the Teller to enter teller number
     */
    @Override
    protected void beforeInstrumentForm(final Form f) {

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("TransactionMenu", null);
            }
        };

        Command cancel = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };

        f.getMenuBar().addCommand(home);
        f.getMenuBar().addCommand(cancel);

        Command withdraw = new Command("Withdraw") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                instrumentNo = findInstrumentNumberField(f).getText();
                tellerNo = findTellerNumberTextField(f).getText();

                if (((!("".equals(instrumentNo))) && (!("".equals(tellerNo)))) || ((("".equals(tellerNo))) && ("".equals(instrumentNo)))) {
                    Dialog.show("", "either  of the fields is required", "OK", null);
                } else {

                    showForm("DailyTokenWithdrawal", null);
                }
            }
        };

        f.getMenuBar().addCommand(withdraw);
    }

    /**
     * The Form that asks the Teller to enter account number for mini statement
     */
    @Override
    protected void beforeMiniStatForm(Form f) {

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("TransactionMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(home);

    }

    /**
     * The Form that displays Registration Form for new Application user
     */
    @Override
    protected void beforeNewAppUser(final Form f) {

        Command home = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("Main", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(home);

        Command newUser = new Command("Sign Up") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                String username = findNewUserTellerIDTextField(f).getText();
                String password = findNewUserPasswordTextField(f).getText();
                String email = findNewUserEmailTextField(f).getText();
                String tranPWD = findTranPasswordTextField(f).getText();
                String tillAcct = findTellerTilTextField(f).getText();
                String bankCode = findBankCodeTextField(f).getText();

                if (("".equals(bankCode)) || ("".equals(username)) || ("".equals(password)) || ("".equals(tillAcct)) || ("".equals(email)) || ("".equals(tranPWD))) {
                    Dialog.show("", "all fields are required", "OK", null);
                } else {

                    if ((password.length() <= 6) || (tranPWD.length() <= 6)) {
                        Dialog.show("", "passwords should be more than six(6) characters", "OK", null);
                    } else {

                        GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                        String myEncryptedPassword = encryptMyPassword.asHex();

                        GodwinEncrypt encryptTranMyPassword = new GodwinEncrypt(tranPWD);
                        String myEncryptedTranPassword = encryptTranMyPassword.asHex();

                        signUp(username, myEncryptedPassword, email, myEncryptedTranPassword, tillAcct, bankCode);

                        if ((status == null) || !("200".equals(status))) {
                            Dialog.show("Oh Dear", "Could not create Teller, please check your internet", "OK", null);
                        } else {

                            status = "";
                            //String returncode = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retval");
                            String respCode = signUpResponse.getAsString("/soap:Envelope/soap:Body/MTellerCreateUserResponse/MTellerCreateUserResult/Retval");
                            String respMsg = signUpResponse.getAsString("/soap:Envelope/soap:Body/MTellerCreateUserResponse/MTellerCreateUserResult/Retmsg");
                            System.out.println(respCode);
                            System.out.println(respMsg);

                            if ("1".equals(respCode)) {
                                Dialog.show("Success", "Teller has been Created, please try and LOGIN", "OK", null);
                                showForm("Main", null);
                            } else {
                                Dialog.show("Oh Dear!!!", respMsg, "OK", null);
                                //showForm("Main", null);
                            }
//                    try {
//                        
//                    } catch (Exception e) {
//                        Dialog.show("OOPS!!!", "request may have been cancelled", "OK", null);
//                    }
                        }
                    }
                }
            }
        };

        f.getMenuBar().addCommand(newUser);

    }

    /**
     * The Offline Login Form(Not in use)
     */
    @Override
    protected void beforeOfflineLogin(final Form f) {

        Command home = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("Main", null);
            }
        };


        f.getMenuBar().addCommand(home);
        f.setBackCommand(home);

        Command logins = new Command("Login") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
                String name = findOfflineUsernameTextField(f).getText();
                String password = findOfflinePasswordTextField(f).getText();
                if (("".equals(name)) || ("".equals(password))) {
                    Dialog.show("Sorry", "both fields are required to continue", "OK", null);
                } else {
                    if (Storage.getInstance().exists("OfflineTeller")) {
                        //Storage.getInstance().readObject(name)
                        try {
                            OfflineTeller = (Hashtable<String, String>) Storage.getInstance().readObject("OfflineTeller");
                        } catch (Exception e) {
                            Dialog.show("Error", e.getMessage(), "OK", null);

                        }

                        GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                        String myEncryptedPassword = encryptMyPassword.asHex();

                        if ((name.equals(OfflineTeller.get("teller_ID").toString())) && ((myEncryptedPassword).equals(OfflineTeller.get("password").toString()))) {
                            appUser = new AppUsers(OfflineTeller.get("teller_ID").toString(), OfflineTeller.get("email").toString(), OfflineTeller.get("object_id").toString(), OfflineTeller.get("offline_status").toString(), OfflineTeller.get("mfb_code").toString(), OfflineTeller.get("password").toString(), OfflineTeller.get("til_account").toString());
                            showForm("OfflineMenu", null);
                        } else {
                            Dialog.show("Oh dear!", "wrong username and password combination", "OK", null);
                        }
                    } else {
                        Dialog.show("First time Login", "about to perform a check online", "OK", null);

                        GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                        String myEncryptedPassword = encryptMyPassword.asHex();

                        login(name, myEncryptedPassword);

                        if (status == null || !(status.equals("200"))) {

                            Dialog.show("Oh dear", "could not perform check. please ensure: \n"
                                    + "1. are you a registered mTeller user? \n"
                                    + "2. have you typed your username and password correctly?\n"
                                    + "3. do you have internet on your device?", "OK", null);


                        } else {
                            status = "";

                            String respCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retval");
                            String respMsg = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retmsg");

                            if ("1".equals(respCode)) {
                                String email = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Email");
                                String mfbCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Mfbcode");
                                String tranPassword = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TranPwd");
                                String tillAcct = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TillAcct");
                                String objectID = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/ObjectID");
                                String offlineStatus = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/OfflineTeller");

                                if (appUser.getOfflineStatus().equals("0")) {
                                    Dialog.show("Sorry", "You are not configured to perform offline Transactions, "
                                            + "please contact your Administrator", "OK", null);
                                } else {

                                    appUser = new AppUsers(name, email, objectID, offlineStatus, mfbCode, tranPassword, tillAcct);

                                    OfflineTeller = new Hashtable<String, String>();
                                    OfflineTeller.put("teller_ID", appUser.getUsername());
                                    OfflineTeller.put("mfb_code", appUser.getMfb_code());
                                    OfflineTeller.put("til_account", appUser.getTil_acct());
                                    OfflineTeller.put("email", appUser.getEmail());
                                    OfflineTeller.put("password", myEncryptedPassword);
                                    //OfflineTeller.put("email_verified", appUser.getEmailVerified());
                                    //OfflineTeller.put("session_string", appUser.getSessionToken());
                                    OfflineTeller.put("mfb_code", appUser.getMfb_code());
                                    OfflineTeller.put("offline_status", appUser.getOfflineStatus());
                                    OfflineTeller.put("object_id", appUser.getObJectId());

                                    try {
                                        Storage.getInstance().writeObject("OfflineTeller", OfflineTeller);
                                        Dialog.show("", "Login success", "OK", null);
                                        OfflineTeller = (Hashtable<String, String>) Storage.getInstance().readObject("OfflineTeller");
                                        showForm("OfflineMenu", null);
                                    } catch (Exception e) {
                                        Dialog.show("Error", e.getMessage(), "OK", null);
                                        back();
                                    }

                                }

                                //


                            } else {
                                Dialog.show("", respMsg, "OK", null);
                            }
                        }
                    }
                }

            }
        };

        f.getMenuBar().addCommand(logins);

    }

    /**
     * Offline Teller registration Form
     */
    @Override
    protected void beforeOfflineTellerCreate(final Form f) {

        Command home = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("Settings", null);
            }
        };
        f.getMenuBar().addCommand(home);
        f.setBackCommand(home);

        Command cre = new Command("Create") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.


                String name = findNewOfflineTellerTextField(f).getText();
                String password = findNewOfflineTellerPWDTextField(f).getText();
                String mfbCode = findMfbCodeTextField(f).getText();
                if (("".equals(name)) || ("".equals(password)) || ("".equals(mfbCode))) {
                    Dialog.show("", "all fields are required", "OK", null);
                } else {
//            if ((password.length()) <= 6) {
//                Dialog.show("", "password shoulb be more than \n'6' characters", "OK", null);
//            } else {

                    GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                    String myEncryptedPassword = encryptMyPassword.asHex();

                    login(name, myEncryptedPassword);

                    if (status == null || !(status.equals("200"))) {

                        Dialog.show("Oh dear", "we could not check the user. please ensure the following: \n"
                                + "1. are you a registered mTeller user? \n"
                                + "2. have you typed your username and password correctly?\n"
                                + "3. is your phone connected to the internet?", ""
                                + "OK", null);
                    } else {
                        status = "";

                        String respCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retval");
                        String respMsg = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retmsg");

                        if ("1".equals(respCode)) {
                            String email = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Email");
                            String mfbCode2 = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Mfbcode");
                            String tranPassword = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TranPwd");
                            String tillAcct = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TillAcct");
                            String objectID = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/ObjectID");
                            String offlineStatus = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/OfflineTeller");

                            appUser = new AppUsers(name, email, objectID, offlineStatus, mfbCode2, tranPassword, tillAcct);
                            //

//                            Dialog.show("Logged In", appUser.getUsername().toUpperCase() + "  welcome", "OK", null);
//                            //status = "";
//                            showForm("TransactionMenu", null);
                            configureOfflineTeller(appUser.getUsername(), appUser.getObJectId(), mfbCode);

                            if ((status == null) || (!status.equals("200"))) {
                                Dialog.show("Oh dear", "you may not be connected to the internet", "OK", null);
                            } else {
                                //try{
                                //;

                                String returncode = offlineTellerResponse.getAsString("/soap:Envelope/soap:Body/MTellerUpdateUserResponse/MTellerUpdateUserResult/Retval");
                                String returncodemsg = offlineTellerResponse.getAsString("/soap:Envelope/soap:Body/MTellerUpdateUserResponse/MTellerUpdateUserResult/Retmsg");

                                if ("1".equals(returncode)) {
                                    //repliesUploaded.add(hashtable);
                                    Dialog dlg = new Dialog("Offline teller");
                                    dlg.addComponent(new Label(returncodemsg));
                                    dlg.setTimeout(1000);
                                    dlg.show();
                                    back();
                                } else {
                                    Dialog.show("", returncodemsg, "OK", null);
                                }


                                // back();

                            }

                        } else {
                            Dialog.show("", respMsg, "OK", null);
                        }


                        //status = "";

                    }
                    //}
                }
            }
        };
        f.getMenuBar().addCommand(cre);
    }

    /**
     * Login Form (NOt in use)
     */
    @Override
    protected void beforeOnlineLogin(final Form f) {

        Command logUser = new Command("Login") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                String username = findUsernameTextField(f).getText();
                String password = findPasswordTextField(f).getText();

                if (("".equals(username)) || ("".equals(password))) {
                    Dialog.show("Missing", "Please provide Username and Password", "OK", null);
                } else {

                    GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                    String myEncryptedPassword = encryptMyPassword.asHex();

                    login(username, myEncryptedPassword);

                    if (status == null || !(status.equals("200"))) {

                        Dialog.show("", "unable to login", "OK", null);


                    } else {
                        status = "";
                        String respCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retval");
                        String respMsg = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retmsg");

                        if ("1".equals(respCode)) {
                            String email = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Email");
                            String mfbCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Mfbcode");
                            String tranPassword = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TranPwd");
                            String tillAcct = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TillAcct");
                            String objectID = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/ObjectID");
                            String offlineStatus = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/OfflineTeller");

                            appUser = new AppUsers(username, email, objectID, offlineStatus, mfbCode, tranPassword, tillAcct);
                            //

                            Dialog.show("Logged In", appUser.getUsername().toUpperCase() + "  welcome", "OK", null);
                            //status = "";
                            showForm("TransactionMenu", null);

                        } else {
                            Dialog.show("", respMsg, "OK", null);
                        }
                    }

                }
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                showForm("Main", null);
            }
        };

        f.setBackCommand(cancel);
        f.addCommand(logUser);
        f.addCommand(cancel);
    }

    /**
     * Admin Login form (Not in use)
     */
    @Override
    protected void beforeSettingsLogin(final Form f) {

        Command setLogin = new Command("Login") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.


                String name = findAdminNameTextField(f).getText();
                String password = findAdminPasswordTextField(f).getText();

                if ((name.equals(defaultAdmin)) && (password.equals(defaultAdminPwd))) {
                    showForm("Settings", null);
                } else {
                    if (Storage.getInstance().exists("phone_admin")) {
                        phoneAdmin = (Hashtable<String, String>) Storage.getInstance().readObject("phone_admin");

                        if (((phoneAdmin.get("admin_name").toString()).equals(name)) && ((phoneAdmin.get("admin_password").toString()).equals(password))) {
                            showForm("Settings", null);
                        } else {
                            Dialog.show("", "Wrong Admin name and password combination", "OK", null);
                        }
                    } else {
                        Dialog.show("", "Wrong Admin name and password combination", "OK", null);
                    }
                }
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("Main", null);
            }
        };

        f.addCommand(cancel);
        f.setBackCommand(cancel);
        f.addCommand(setLogin);
    }

    /**
     * Adding menu options to the Withdrawal form
     */
    @Override
    protected void beforeWithdrawalAcctNumber(final Form f) {

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("TransactionMenu", null);
            }
        };


        f.getMenuBar().addCommand(home);
        //f.setBackCommand(null);

        Command verify = new Command("Verify") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.


                String accountNumber = findWithdrawalAcctNumberTextField(f).getText();

                if (("".equals(accountNumber))) {

                    Dialog.show("", " please provide account number", "OK", null);
                } else {

                    verifyCustomer(appUser.getMfb_code(), accountNumber);

                    if (!("200".equals(status))) {
                        Dialog.show("", "something may have gone wrong", "OK", null);
                    } else {
                        status = "";

                        String returncode = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retval");
                        String returncodemsg = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:retmsg");

                        if ("0".equals(returncode)) {


                            String accountname = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:SignatoryName");

                            String accountnumber = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:Accountnumber");

                            String imagephoto = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:CustomerImage");

                            String imagesignature = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:SignatureImage");

                            String customerBalance = result.getAsString("/s:envelope/s:body/ns3:customerinforesponse/return/ns2:customerdata/ns2:BookBalance");


                            customer = new Customers(accountname, accountnumber, imagephoto, imagesignature, customerBalance);

                            showForm("CustomerToWithdraw", null);
                        } else {

                            Dialog.show("", returncodemsg, "ok", null);
                        }

                    }
                }
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                back();
            }
        };
        f.addCommand(verify);
        f.addCommand(cancel);
    }

     /**
     * the login action on the Login dialog after the user must have entered the credentials
     */
    
    @Override
    protected void onAppUserLogin_LoginAction(Component c, ActionEvent event) {
        String username = findTellerUsername(c.getComponentForm()).getText();
        String password = findPassword(c.getComponentForm()).getText();

        if (("".equals(username)) || ("".equals(password))) {

            Dialog.show("Missing", "Please provide Username and Password", "OK", null);
        } else {
            GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
            String myEncryptedPassword = encryptMyPassword.asHex();

            login(username, myEncryptedPassword);

            if (status == null || !(status.equals("200"))) {

                Dialog.show("", "unable to login", "OK", null);


            } else {
                status = "";

                String respCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retval");
                String respMsg = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retmsg");

                if ("1".equals(respCode)) {
                    String email = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Email");
                    String mfbCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Mfbcode");
                    String tranPassword = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TranPwd");
                    String tillAcct = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TillAcct");
                    String objectID = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/ObjectID");
                    String offlineStatus = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/OfflineTeller");

                    appUser = new AppUsers(username, email, objectID, offlineStatus, mfbCode, tranPassword, tillAcct);
                    //
                    ((Dialog) Display.getInstance().getCurrent()).dispose();

                    Dialog.show("Logged In", appUser.getUsername().toUpperCase() + "  welcome", "OK", null);
                    //status = "";
                    showForm("TransactionMenu", null);

                } else {
                    Dialog.show("", respMsg, "OK", null);
                }

            }

        }
    }

     /**
     * cancel action on the login dialog
     */
    @Override
    protected void onAppUserLogin_CancelAction(Component c, ActionEvent event) {
        ((Dialog) Display.getInstance().getCurrent()).dispose();
    }

     /**
     * adding menu option to the login dialog
     */
    @Override
    protected void beforeAppUserLogin(final Form f) {

        Command logUser = new Command("Login") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                String username = findTellerUsername(f).getText();
                String password = findPassword(f).getText();

                if (("".equals(username)) || ("".equals(password))) {
                    Dialog.show("Missing", "Please provide Username and Password", "OK", null);
                } else {

                    GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                    String myEncryptedPassword = encryptMyPassword.asHex();

                    login(username, myEncryptedPassword);

                    if (status == null || !(status.equals("200"))) {

                        Dialog.show("", "unable to login", "OK", null);


                    } else {
                        status = "";
                        String respCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retval");
                        String respMsg = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retmsg");

                        if ("1".equals(respCode)) {
                            String email = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Email");
                            String mfbCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Mfbcode");
                            String tranPassword = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TranPwd");
                            String tillAcct = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TillAcct");
                            String objectID = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/ObjectID");
                            String offlineStatus = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/OfflineTeller");

                            appUser = new AppUsers(username, email, objectID, offlineStatus, mfbCode, tranPassword, tillAcct);
                            //
                            ((Dialog) Display.getInstance().getCurrent()).dispose();
                            Dialog.show("Logged In", appUser.getUsername().toUpperCase() + "  welcome", "OK", null);
                            //status = "";
                            showForm("TransactionMenu", null);

                        } else {
                            Dialog.show("", respMsg, "OK", null);
                        }
                    }

                }
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                ((Dialog) Display.getInstance().getCurrent()).dispose();
                //showForm("Main", null);
            }
        };

        f.setBackCommand(cancel);
        f.addCommand(logUser);
        f.addCommand(cancel);
    }

     /**
     * login action for the offline login dialog
     */
    @Override
    protected void onAppUserLoginOffline_OfflineUserLoginAction(Component c, ActionEvent event) {
        if (Storage.getInstance().exists("Application_Settings")) {
            try {
                app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
            } catch (Exception e) {
                Dialog.show("Oh dear", "error has been encountered, reading application settings", "OK", null);
            }

            String name = findOfflineUsername(c.getComponentForm()).getText();
            String password = findOfflinePassword(c.getComponentForm()).getText();
            if (("".equals(name)) || ("".equals(password))) {
                Dialog.show("Sorry", "both fields are required to continue", "OK", null);
            } else {
                if (Storage.getInstance().exists("OfflineTeller")) {
                    //Storage.getInstance().readObject(name)
                    try {
                        OfflineTeller = (Hashtable<String, String>) Storage.getInstance().readObject("OfflineTeller");
                    } catch (Exception e) {
                        Dialog.show("Error", e.getMessage(), "OK", null);

                    }

                    GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                    String myEncryptedPassword = encryptMyPassword.asHex();

                    if ((name.equals(OfflineTeller.get("teller_ID").toString())) && ((myEncryptedPassword).equals(OfflineTeller.get("password").toString()))) {
                        appUser = new AppUsers(OfflineTeller.get("teller_ID").toString(), OfflineTeller.get("email").toString(), OfflineTeller.get("object_id").toString(), OfflineTeller.get("offline_status").toString(), OfflineTeller.get("mfb_code").toString(), OfflineTeller.get("password").toString(), OfflineTeller.get("til_account").toString());
                        ((Dialog) Display.getInstance().getCurrent()).dispose();
                        showForm("OfflineMenu", null);
                    } else {
                        Dialog.show("Oh dear!", "invalid login credentials", "OK", null);
                    }
                } else {
                    Dialog.show("First time Login", "about to perform a check online", "OK", null);

                    GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                    String myEncryptedPassword = encryptMyPassword.asHex();

                    login(name, myEncryptedPassword);

                    if (status == null || !(status.equals("200"))) {

                        Dialog.show("Oh dear", "could not perform check. please ensure: \n"
                                + "1. are you a registered mTeller user? \n"
                                + "2. have you typed your username and password correctly?\n"
                                + "3. do you have internet on your device?", "OK", null);


                    } else {
                        status = "";

                        String respCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retval");
                        String respMsg = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retmsg");

                        if ("1".equals(respCode)) {
                            String email = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Email");
                            String mfbCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Mfbcode");
                            String tranPassword = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TranPwd");
                            String tillAcct = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TillAcct");
                            String objectID = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/ObjectID");
                            String offlineStatus = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/OfflineTeller");

                            if (offlineStatus.equals("0")) {
                                Dialog.show("Sorry", "You are not configured to perform offline Transactions, "
                                        + "please contact your Administrator", "OK", null);
                            } else {
                                appUser = new AppUsers(name, email, objectID, offlineStatus, mfbCode, tranPassword, tillAcct);

                                OfflineTeller = new Hashtable<String, String>();
                                OfflineTeller.put("teller_ID", appUser.getUsername());
                                OfflineTeller.put("mfb_code", appUser.getMfb_code());
                                OfflineTeller.put("til_account", appUser.getTil_acct());
                                OfflineTeller.put("email", appUser.getEmail());
                                OfflineTeller.put("password", myEncryptedPassword);
                                //OfflineTeller.put("email_verified", appUser.getEmailVerified());
                                //OfflineTeller.put("session_string", appUser.getSessionToken());
                                OfflineTeller.put("mfb_code", appUser.getMfb_code());
                                OfflineTeller.put("offline_status", appUser.getOfflineStatus());
                                OfflineTeller.put("object_id", appUser.getObJectId());

                                try {
                                    Storage.getInstance().writeObject("OfflineTeller", OfflineTeller);
                                    Dialog.show("", "Login success", "OK", null);
                                    OfflineTeller = (Hashtable<String, String>) Storage.getInstance().readObject("OfflineTeller");
                                    ((Dialog) Display.getInstance().getCurrent()).dispose();
                                    showForm("OfflineMenu", null);
                                } catch (Exception e) {
                                    ((Dialog) Display.getInstance().getCurrent()).dispose();
                                    Dialog.show("Error", e.getMessage(), "OK", null);

                                }

                            }

                            //


                        } else {
                            Dialog.show("Oh dear", respMsg, "OK", null);
                        }
                    }
                }
            }
        } else {
            ((Dialog) Display.getInstance().getCurrent()).dispose();
            Dialog.show("Oh dear", "Application settings cannot be found, go to Settings and provide Application settings", "OK", null);
        }

    }

     /**
     * cancel action on the offline login dialog
     */
    @Override
    protected void onAppUserLoginOffline_CancelAction(Component c, ActionEvent event) {
        ((Dialog) Display.getInstance().getCurrent()).dispose();
    }

     /**
     * adding menu option to the offline login dialog
     */
    @Override
    protected void beforeAppUserLoginOffline(final Form f) {


        Command home = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                //showForm("Main", null);
                ((Dialog) Display.getInstance().getCurrent()).dispose();
            }
        };


        f.addCommand(home);
        f.setBackCommand(home);

        Command logins = new Command("Login") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                app_Settings = (Hashtable<String, String>) Storage.getInstance().readObject("Application_Settings");
                String name = findOfflineUsername(f).getText();
                String password = findOfflinePassword(f).getText();
                if (("".equals(name)) || ("".equals(password))) {
                    Dialog.show("Sorry", "both fields are required to continue", "OK", null);
                } else {
                    if (Storage.getInstance().exists("OfflineTeller")) {
                        //Storage.getInstance().readObject(name)
                        try {
                            OfflineTeller = (Hashtable<String, String>) Storage.getInstance().readObject("OfflineTeller");
                        } catch (Exception e) {
                            Dialog.show("Error", e.getMessage(), "OK", null);

                        }

                        GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                        String myEncryptedPassword = encryptMyPassword.asHex();

                        if ((name.equals(OfflineTeller.get("teller_ID").toString())) && ((myEncryptedPassword).equals(OfflineTeller.get("password").toString()))) {
                            appUser = new AppUsers(OfflineTeller.get("teller_ID").toString(), OfflineTeller.get("email").toString(), OfflineTeller.get("object_id").toString(), OfflineTeller.get("offline_status").toString(), OfflineTeller.get("mfb_code").toString(), OfflineTeller.get("password").toString(), OfflineTeller.get("til_account").toString());
                            ((Dialog) Display.getInstance().getCurrent()).dispose();
                            showForm("OfflineMenu", null);
                        } else {
                            Dialog.show("Oh dear!", "invalid login credentials", "OK", null);
                        }
                    } else {
                        Dialog.show("First time Login", "about to perform a check online", "OK", null);

                        GodwinEncrypt encryptMyPassword = new GodwinEncrypt(password);
                        String myEncryptedPassword = encryptMyPassword.asHex();

                        login(name, myEncryptedPassword);

                        if (status == null || !(status.equals("200"))) {

                            Dialog.show("Oh dear", "could not perform check. please ensure: \n"
                                    + "1. are you a registered mTeller user? \n"
                                    + "2. have you typed your username and password correctly?\n"
                                    + "3. do you have internet on your device?", "OK", null);


                        } else {
                            status = "";

                            String respCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retval");
                            String respMsg = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Retmsg");

                            if ("1".equals(respCode)) {
                                String email = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Email");
                                String mfbCode = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/Mfbcode");
                                String tranPassword = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TranPwd");
                                String tillAcct = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/TillAcct");
                                String objectID = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/ObjectID");
                                String offlineStatus = loginResponse.getAsString("/soap:Envelope/soap:Body/MTellerLoginQueryResponse/MTellerLoginQueryResult/OfflineTeller");

                                if (appUser.getOfflineStatus().equals("0")) {
                                    Dialog.show("Sorry", "You are not configured to perform offline Transactions, "
                                            + "please contact your Administrator", "OK", null);
                                } else {
                                    appUser = new AppUsers(name, email, objectID, offlineStatus, mfbCode, tranPassword, tillAcct);

                                    OfflineTeller = new Hashtable<String, String>();
                                    OfflineTeller.put("teller_ID", appUser.getUsername());
                                    OfflineTeller.put("mfb_code", appUser.getMfb_code());
                                    OfflineTeller.put("til_account", appUser.getTil_acct());
                                    OfflineTeller.put("email", appUser.getEmail());
                                    OfflineTeller.put("password", myEncryptedPassword);
                                    //OfflineTeller.put("email_verified", appUser.getEmailVerified());
                                    //OfflineTeller.put("session_string", appUser.getSessionToken());
                                    OfflineTeller.put("mfb_code", appUser.getMfb_code());
                                    OfflineTeller.put("offline_status", appUser.getOfflineStatus());
                                    OfflineTeller.put("object_id", appUser.getObJectId());

                                    try {
                                        Storage.getInstance().writeObject("OfflineTeller", OfflineTeller);
                                        Dialog.show("", "Login success", "OK", null);
                                        OfflineTeller = (Hashtable<String, String>) Storage.getInstance().readObject("OfflineTeller");
                                        ((Dialog) Display.getInstance().getCurrent()).dispose();
                                        showForm("OfflineMenu", null);
                                    } catch (Exception e) {
                                        Dialog.show("Error", e.getMessage(), "OK", null);
                                        //back();
                                    }

                                }

                                //


                            } else {
                                Dialog.show("", respMsg, "OK", null);
                            }
                        }
                    }
                }

            }
        };

        f.addCommand(logins);
    }

     /**
     * adding menu option to the settings(Admin) login dialog
     */
    @Override
    protected void beforeSettingLogin(final Form f) {

        Command setLogin = new Command("Login") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.


                String name = findAdminName(f).getText();
                String password = findAdminPassword(f).getText();

                if ((name.equals(defaultAdmin)) && (password.equals(defaultAdminPwd))) {
                    ((Dialog) Display.getInstance().getCurrent()).dispose();
                    showForm("Settings", null);
                } else {
                    if (Storage.getInstance().exists("phone_admin")) {
                        phoneAdmin = (Hashtable<String, String>) Storage.getInstance().readObject("phone_admin");

                        if (((phoneAdmin.get("admin_name").toString()).equals(name)) && ((phoneAdmin.get("admin_password").toString()).equals(password))) {
                            ((Dialog) Display.getInstance().getCurrent()).dispose();
                            showForm("Settings", null);
                        } else {
                            Dialog.show("", "Wrong Admin name and password combination", "OK", null);
                        }
                    } else {
                        Dialog.show("", "Wrong Admin name and password combination", "OK", null);
                    }
                }
            }
        };

        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                //showForm("Main", null);
                ((Dialog) Display.getInstance().getCurrent()).dispose();
            }
        };

        f.addCommand(cancel);
        f.setBackCommand(cancel);
        f.addCommand(setLogin);
    }

     /**
     * admin login action
     */
    @Override
    protected void onSettingLogin_AdminLoginAction(Component c, ActionEvent event) {
        String name = findAdminName(c.getComponentForm()).getText();
        String password = findAdminPassword(c.getComponentForm()).getText();

        if ((name.equals(defaultAdmin)) && (password.equals(defaultAdminPwd))) {
            showForm("Settings", null);
        } else {
            if (Storage.getInstance().exists("phone_admin")) {
                phoneAdmin = (Hashtable<String, String>) Storage.getInstance().readObject("phone_admin");

                if (((phoneAdmin.get("admin_name").toString()).equals(name)) && ((phoneAdmin.get("admin_password").toString()).equals(password))) {
                    showForm("Settings", null);
                } else {
                    Dialog.show("", "Wrong Admin name and password combination", "OK", null);
                }
            } else {
                Dialog.show("", "Wrong Admin name and password combination", "OK", null);
            }
        }
    }

     /**
     * cancel action on the Admin login dialog
     */
    @Override
    protected void onSettingLogin_CancelAction(Component c, ActionEvent event) {

        ((Dialog) Display.getInstance().getCurrent()).dispose();
    }
}
