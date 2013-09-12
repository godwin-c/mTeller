/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import java.util.Hashtable;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("MultiList", com.codename1.ui.list.MultiList.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("List", com.codename1.ui.List.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "Main";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("MultiList", com.codename1.ui.list.MultiList.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("List", com.codename1.ui.List.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "Main");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.TextField findOfflineTellerNumberTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("offlineTellerNumberTextField", root);
    }

    public com.codename1.ui.TextField findOfflineTellerNumberTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("offlineTellerNumberTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("offlineTellerNumberTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findChangeOfflineTeller(Component root) {
        return (com.codename1.ui.Button)findByName("changeOfflineTeller", root);
    }

    public com.codename1.ui.Button findChangeOfflineTeller() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("changeOfflineTeller", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("changeOfflineTeller", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findWithdrawalVerify(Component root) {
        return (com.codename1.ui.Button)findByName("withdrawalVerify", root);
    }

    public com.codename1.ui.Button findWithdrawalVerify() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("withdrawalVerify", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("withdrawalVerify", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findCustAcctNo(Component root) {
        return (com.codename1.ui.Label)findByName("custAcctNo", root);
    }

    public com.codename1.ui.Label findCustAcctNo() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("custAcctNo", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("custAcctNo", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findOfflineUsernameTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("offlineUsernameTextField", root);
    }

    public com.codename1.ui.TextField findOfflineUsernameTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("offlineUsernameTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("offlineUsernameTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBackToMain(Component root) {
        return (com.codename1.ui.Button)findByName("backToMain", root);
    }

    public com.codename1.ui.Button findBackToMain() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("backToMain", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("backToMain", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findUserLogin(Component root) {
        return (com.codename1.ui.Button)findByName("userLogin", root);
    }

    public com.codename1.ui.Button findUserLogin() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("userLogin", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("userLogin", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelDeposit(Component root) {
        return (com.codename1.ui.Button)findByName("cancelDeposit", root);
    }

    public com.codename1.ui.Button findCancelDeposit() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelDeposit", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelDeposit", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findPasswordTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("passwordTextField", root);
    }

    public com.codename1.ui.TextField findPasswordTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("passwordTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("passwordTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatRetMessage(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatRetMessage", root);
    }

    public com.codename1.ui.Label findMiniStatRetMessage() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatRetMessage", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatRetMessage", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.List findAccountsWithErrorStatusList(Component root) {
        return (com.codename1.ui.List)findByName("accountsWithErrorStatusList", root);
    }

    public com.codename1.ui.List findAccountsWithErrorStatusList() {
        com.codename1.ui.List cmp = (com.codename1.ui.List)findByName("accountsWithErrorStatusList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.List)findByName("accountsWithErrorStatusList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNewOfflineTellerPWDTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("newOfflineTellerPWDTextField", root);
    }

    public com.codename1.ui.TextField findNewOfflineTellerPWDTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("newOfflineTellerPWDTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("newOfflineTellerPWDTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNewOfflineTellerTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("newOfflineTellerTextField", root);
    }

    public com.codename1.ui.TextField findNewOfflineTellerTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("newOfflineTellerTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("newOfflineTellerTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findAcctNumberTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("acctNumberTextField", root);
    }

    public com.codename1.ui.TextField findAcctNumberTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("acctNumberTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("acctNumberTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelFetch(Component root) {
        return (com.codename1.ui.Button)findByName("cancelFetch", root);
    }

    public com.codename1.ui.Button findCancelFetch() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelFetch", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelFetch", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findTranURLTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("tranURLTextArea", root);
    }

    public com.codename1.ui.TextArea findTranURLTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("tranURLTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("tranURLTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSignature(Component root) {
        return (com.codename1.ui.Button)findByName("signature", root);
    }

    public com.codename1.ui.Button findSignature() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("signature", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("signature", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findOkToDeposit(Component root) {
        return (com.codename1.ui.Button)findByName("okToDeposit", root);
    }

    public com.codename1.ui.Button findOkToDeposit() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("okToDeposit", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("okToDeposit", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findTellerTilTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("tellerTilTextField", root);
    }

    public com.codename1.ui.TextField findTellerTilTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("tellerTilTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("tellerTilTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAdminSettings(Component root) {
        return (com.codename1.ui.Button)findByName("adminSettings", root);
    }

    public com.codename1.ui.Button findAdminSettings() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("adminSettings", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("adminSettings", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findWithdrawaBankMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("withdrawaBankMultiList", root);
    }

    public com.codename1.ui.list.MultiList findWithdrawaBankMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("withdrawaBankMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("withdrawaBankMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findVerifyBalInquiry(Component root) {
        return (com.codename1.ui.Button)findByName("verifyBalInquiry", root);
    }

    public com.codename1.ui.Button findVerifyBalInquiry() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("verifyBalInquiry", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("verifyBalInquiry", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNewAdminPasswordTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("newAdminPasswordTextField", root);
    }

    public com.codename1.ui.TextField findNewAdminPasswordTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("newAdminPasswordTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("newAdminPasswordTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findLoginOffline(Component root) {
        return (com.codename1.ui.Button)findByName("loginOffline", root);
    }

    public com.codename1.ui.Button findLoginOffline() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("loginOffline", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("loginOffline", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSignUpUser(Component root) {
        return (com.codename1.ui.Button)findByName("signUpUser", root);
    }

    public com.codename1.ui.Button findSignUpUser() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("signUpUser", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("signUpUser", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findAmountToWithdrawTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("amountToWithdrawTextField", root);
    }

    public com.codename1.ui.TextField findAmountToWithdrawTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("amountToWithdrawTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("amountToWithdrawTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findOfflineTranTellerNumber(Component root) {
        return (com.codename1.ui.Label)findByName("offlineTranTellerNumber", root);
    }

    public com.codename1.ui.Label findOfflineTranTellerNumber() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("offlineTranTellerNumber", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("offlineTranTellerNumber", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCustomerSignature(Component root) {
        return (com.codename1.ui.Button)findByName("customerSignature", root);
    }

    public com.codename1.ui.Button findCustomerSignature() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("customerSignature", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("customerSignature", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatValueDate(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatValueDate", root);
    }

    public com.codename1.ui.Label findMiniStatValueDate() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatValueDate", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatValueDate", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findTranPasswordTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("tranPasswordTextField", root);
    }

    public com.codename1.ui.TextField findTranPasswordTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("tranPasswordTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("tranPasswordTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findWithdrawalTranPWDTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("withdrawalTranPWDTextField", root);
    }

    public com.codename1.ui.TextField findWithdrawalTranPWDTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("withdrawalTranPWDTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("withdrawalTranPWDTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatOpenBalance(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatOpenBalance", root);
    }

    public com.codename1.ui.Label findMiniStatOpenBalance() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatOpenBalance", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatOpenBalance", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findMiniStatMFBsMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("MiniStatMFBsMultiList", root);
    }

    public com.codename1.ui.list.MultiList findMiniStatMFBsMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("MiniStatMFBsMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("MiniStatMFBsMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelBalInquiry(Component root) {
        return (com.codename1.ui.Button)findByName("cancelBalInquiry", root);
    }

    public com.codename1.ui.Button findCancelBalInquiry() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelBalInquiry", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelBalInquiry", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatNarration(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatNarration", root);
    }

    public com.codename1.ui.Label findMiniStatNarration() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatNarration", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatNarration", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelWithdraw(Component root) {
        return (com.codename1.ui.Button)findByName("cancelWithdraw", root);
    }

    public com.codename1.ui.Button findCancelWithdraw() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelWithdraw", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelWithdraw", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatTellerNumber(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatTellerNumber", root);
    }

    public com.codename1.ui.Label findMiniStatTellerNumber() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatTellerNumber", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatTellerNumber", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCustomerPhoto(Component root) {
        return (com.codename1.ui.Button)findByName("customerPhoto", root);
    }

    public com.codename1.ui.Button findCustomerPhoto() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("customerPhoto", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("customerPhoto", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findOfflineTranTranCode(Component root) {
        return (com.codename1.ui.Label)findByName("offlineTranTranCode", root);
    }

    public com.codename1.ui.Label findOfflineTranTranCode() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("offlineTranTranCode", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("offlineTranTranCode", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.List findAccountsWithErrorReturnCodeList(Component root) {
        return (com.codename1.ui.List)findByName("accountsWithErrorReturnCodeList", root);
    }

    public com.codename1.ui.List findAccountsWithErrorReturnCodeList() {
        com.codename1.ui.List cmp = (com.codename1.ui.List)findByName("accountsWithErrorReturnCodeList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.List)findByName("accountsWithErrorReturnCodeList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findOfflineTranAcctNumber(Component root) {
        return (com.codename1.ui.Label)findByName("offlineTranAcctNumber", root);
    }

    public com.codename1.ui.Label findOfflineTranAcctNumber() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("offlineTranAcctNumber", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("offlineTranAcctNumber", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findMfbCodeTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("mfbCodeTextField", root);
    }

    public com.codename1.ui.TextField findMfbCodeTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("mfbCodeTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("mfbCodeTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findStoredCustomersMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("storedCustomersMultiList", root);
    }

    public com.codename1.ui.list.MultiList findStoredCustomersMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("storedCustomersMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("storedCustomersMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findOfflineNarrationTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("offlineNarrationTextArea", root);
    }

    public com.codename1.ui.TextArea findOfflineNarrationTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("offlineNarrationTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("offlineNarrationTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSendWithdraw(Component root) {
        return (com.codename1.ui.Button)findByName("sendWithdraw", root);
    }

    public com.codename1.ui.Button findSendWithdraw() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("sendWithdraw", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("sendWithdraw", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelWithdrawaToken(Component root) {
        return (com.codename1.ui.Button)findByName("cancelWithdrawaToken", root);
    }

    public com.codename1.ui.Button findCancelWithdrawaToken() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelWithdrawaToken", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelWithdrawaToken", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findViewOfflineTrans(Component root) {
        return (com.codename1.ui.Button)findByName("viewOfflineTrans", root);
    }

    public com.codename1.ui.Button findViewOfflineTrans() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("viewOfflineTrans", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("viewOfflineTrans", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findAppURLTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("appURLTextArea", root);
    }

    public com.codename1.ui.TextArea findAppURLTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("appURLTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("appURLTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.List findAccountsStoredList(Component root) {
        return (com.codename1.ui.List)findByName("accountsStoredList", root);
    }

    public com.codename1.ui.List findAccountsStoredList() {
        com.codename1.ui.List cmp = (com.codename1.ui.List)findByName("accountsStoredList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.List)findByName("accountsStoredList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findPullAccounts(Component root) {
        return (com.codename1.ui.Button)findByName("pullAccounts", root);
    }

    public com.codename1.ui.Button findPullAccounts() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("pullAccounts", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("pullAccounts", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findShowDepositOffline(Component root) {
        return (com.codename1.ui.Button)findByName("showDepositOffline", root);
    }

    public com.codename1.ui.Button findShowDepositOffline() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("showDepositOffline", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("showDepositOffline", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findWithdraw(Component root) {
        return (com.codename1.ui.Button)findByName("withdraw", root);
    }

    public com.codename1.ui.Button findWithdraw() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("withdraw", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("withdraw", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findAllOfflineTransactionsMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("allOfflineTransactionsMultiList", root);
    }

    public com.codename1.ui.list.MultiList findAllOfflineTransactionsMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("allOfflineTransactionsMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("allOfflineTransactionsMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findBearerKeyTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("bearerKeyTextArea", root);
    }

    public com.codename1.ui.TextArea findBearerKeyTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("bearerKeyTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("bearerKeyTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findAcctNumberField(Component root) {
        return (com.codename1.ui.TextField)findByName("acctNumberField", root);
    }

    public com.codename1.ui.TextField findAcctNumberField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("acctNumberField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("acctNumberField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findOfflineTranAcctName(Component root) {
        return (com.codename1.ui.Label)findByName("offlineTranAcctName", root);
    }

    public com.codename1.ui.Label findOfflineTranAcctName() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("offlineTranAcctName", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("offlineTranAcctName", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findMiniStatement(Component root) {
        return (com.codename1.ui.Button)findByName("miniStatement", root);
    }

    public com.codename1.ui.Button findMiniStatement() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("miniStatement", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("miniStatement", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelCheckToken(Component root) {
        return (com.codename1.ui.Button)findByName("cancelCheckToken", root);
    }

    public com.codename1.ui.Button findCancelCheckToken() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelCheckToken", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelCheckToken", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAddOfflineTeller(Component root) {
        return (com.codename1.ui.Button)findByName("addOfflineTeller", root);
    }

    public com.codename1.ui.Button findAddOfflineTeller() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("addOfflineTeller", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("addOfflineTeller", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findTransactionsNotUploadedMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("transactionsNotUploadedMultiList", root);
    }

    public com.codename1.ui.list.MultiList findTransactionsNotUploadedMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("transactionsNotUploadedMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("transactionsNotUploadedMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findOfflinePasswordTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("offlinePasswordTextField", root);
    }

    public com.codename1.ui.TextField findOfflinePasswordTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("offlinePasswordTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("offlinePasswordTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findVerifyDeposit(Component root) {
        return (com.codename1.ui.Button)findByName("verifyDeposit", root);
    }

    public com.codename1.ui.Button findVerifyDeposit() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("verifyDeposit", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("verifyDeposit", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findBalInquiryMFBMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("balInquiryMFBMultiList", root);
    }

    public com.codename1.ui.list.MultiList findBalInquiryMFBMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("balInquiryMFBMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("balInquiryMFBMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAdminLogin(Component root) {
        return (com.codename1.ui.Button)findByName("adminLogin", root);
    }

    public com.codename1.ui.Button findAdminLogin() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("adminLogin", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("adminLogin", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatBookBalance(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatBookBalance", root);
    }

    public com.codename1.ui.Label findMiniStatBookBalance() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatBookBalance", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatBookBalance", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findAmountToDepositField(Component root) {
        return (com.codename1.ui.TextField)findByName("amountToDepositField", root);
    }

    public com.codename1.ui.TextField findAmountToDepositField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("amountToDepositField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("amountToDepositField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findMiniStatAcctNumberTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("miniStatAcctNumberTextField", root);
    }

    public com.codename1.ui.TextField findMiniStatAcctNumberTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("miniStatAcctNumberTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("miniStatAcctNumberTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findTranPWDDepositTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("tranPWDDepositTextField", root);
    }

    public com.codename1.ui.TextField findTranPWDDepositTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("tranPWDDepositTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("tranPWDDepositTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findTellerNum(Component root) {
        return (com.codename1.ui.Label)findByName("tellerNum", root);
    }

    public com.codename1.ui.Label findTellerNum() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("tellerNum", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("tellerNum", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatEndDate(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatEndDate", root);
    }

    public com.codename1.ui.Label findMiniStatEndDate() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatEndDate", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatEndDate", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCreateAdmin(Component root) {
        return (com.codename1.ui.Button)findByName("createAdmin", root);
    }

    public com.codename1.ui.Button findCreateAdmin() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("createAdmin", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("createAdmin", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findAppSettingsContainer(Component root) {
        return (com.codename1.ui.Container)findByName("appSettingsContainer", root);
    }

    public com.codename1.ui.Container findAppSettingsContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("appSettingsContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("appSettingsContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatTranDate(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatTranDate", root);
    }

    public com.codename1.ui.Label findMiniStatTranDate() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatTranDate", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatTranDate", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findUploadSingleTran(Component root) {
        return (com.codename1.ui.Button)findByName("uploadSingleTran", root);
    }

    public com.codename1.ui.Button findUploadSingleTran() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("uploadSingleTran", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("uploadSingleTran", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel(Component root) {
        return (com.codename1.ui.Label)findByName("Label", root);
    }

    public com.codename1.ui.Label findLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findMiniStatMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("miniStatMultiList", root);
    }

    public com.codename1.ui.list.MultiList findMiniStatMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("miniStatMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("miniStatMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findTellerNumberTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("tellerNumberTextField", root);
    }

    public com.codename1.ui.TextField findTellerNumberTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("tellerNumberTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("tellerNumberTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAdminCancel(Component root) {
        return (com.codename1.ui.Button)findByName("adminCancel", root);
    }

    public com.codename1.ui.Button findAdminCancel() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("adminCancel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("adminCancel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer4(Component root) {
        return (com.codename1.ui.Container)findByName("Container4", root);
    }

    public com.codename1.ui.Container findContainer4() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container4", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container4", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer3(Component root) {
        return (com.codename1.ui.Container)findByName("Container3", root);
    }

    public com.codename1.ui.Container findContainer3() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container3", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container3", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer2(Component root) {
        return (com.codename1.ui.Container)findByName("Container2", root);
    }

    public com.codename1.ui.Container findContainer2() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelAddAdmin(Component root) {
        return (com.codename1.ui.Button)findByName("cancelAddAdmin", root);
    }

    public com.codename1.ui.Button findCancelAddAdmin() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelAddAdmin", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelAddAdmin", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatChargeAmount(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatChargeAmount", root);
    }

    public com.codename1.ui.Label findMiniStatChargeAmount() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatChargeAmount", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatChargeAmount", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer1(Component root) {
        return (com.codename1.ui.Container)findByName("Container1", root);
    }

    public com.codename1.ui.Container findContainer1() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSaveAdmin(Component root) {
        return (com.codename1.ui.Button)findByName("saveAdmin", root);
    }

    public com.codename1.ui.Button findSaveAdmin() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("saveAdmin", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("saveAdmin", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findDepositMFBsMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("depositMFBsMultiList", root);
    }

    public com.codename1.ui.list.MultiList findDepositMFBsMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("depositMFBsMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("depositMFBsMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer6(Component root) {
        return (com.codename1.ui.Container)findByName("Container6", root);
    }

    public com.codename1.ui.Container findContainer6() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container6", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container6", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer5(Component root) {
        return (com.codename1.ui.Container)findByName("Container5", root);
    }

    public com.codename1.ui.Container findContainer5() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container5", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container5", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findDepositOnline(Component root) {
        return (com.codename1.ui.Button)findByName("depositOnline", root);
    }

    public com.codename1.ui.Button findDepositOnline() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("depositOnline", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("depositOnline", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCheckToken(Component root) {
        return (com.codename1.ui.Button)findByName("checkToken", root);
    }

    public com.codename1.ui.Button findCheckToken() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("checkToken", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("checkToken", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findOfflineDeposit(Component root) {
        return (com.codename1.ui.Button)findByName("offlineDeposit", root);
    }

    public com.codename1.ui.Button findOfflineDeposit() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("offlineDeposit", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("offlineDeposit", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelOfflineTeller(Component root) {
        return (com.codename1.ui.Button)findByName("cancelOfflineTeller", root);
    }

    public com.codename1.ui.Button findCancelOfflineTeller() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelOfflineTeller", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelOfflineTeller", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findConfigureOfflineTeller(Component root) {
        return (com.codename1.ui.Button)findByName("configureOfflineTeller", root);
    }

    public com.codename1.ui.Button findConfigureOfflineTeller() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("configureOfflineTeller", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("configureOfflineTeller", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findAccountNumber(Component root) {
        return (com.codename1.ui.Label)findByName("accountNumber", root);
    }

    public com.codename1.ui.Label findAccountNumber() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("accountNumber", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("accountNumber", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel1(Component root) {
        return (com.codename1.ui.Label)findByName("Label1", root);
    }

    public com.codename1.ui.Label findLabel1() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel3(Component root) {
        return (com.codename1.ui.Label)findByName("Label3", root);
    }

    public com.codename1.ui.Label findLabel3() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label3", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label3", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel2(Component root) {
        return (com.codename1.ui.Label)findByName("Label2", root);
    }

    public com.codename1.ui.Label findLabel2() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findOfflineTranNarration(Component root) {
        return (com.codename1.ui.Label)findByName("offlineTranNarration", root);
    }

    public com.codename1.ui.Label findOfflineTranNarration() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("offlineTranNarration", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("offlineTranNarration", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatFullName(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatFullName", root);
    }

    public com.codename1.ui.Label findMiniStatFullName() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatFullName", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatFullName", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findBalInquiryAcctNumberTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("balInquiryAcctNumberTextField", root);
    }

    public com.codename1.ui.TextField findBalInquiryAcctNumberTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("balInquiryAcctNumberTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("balInquiryAcctNumberTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findOfflineTranAmount(Component root) {
        return (com.codename1.ui.Label)findByName("offlineTranAmount", root);
    }

    public com.codename1.ui.Label findOfflineTranAmount() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("offlineTranAmount", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("offlineTranAmount", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSendAmountToDeposit(Component root) {
        return (com.codename1.ui.Button)findByName("sendAmountToDeposit", root);
    }

    public com.codename1.ui.Button findSendAmountToDeposit() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("sendAmountToDeposit", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("sendAmountToDeposit", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findInstrumentNumberField(Component root) {
        return (com.codename1.ui.TextField)findByName("instrumentNumberField", root);
    }

    public com.codename1.ui.TextField findInstrumentNumberField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("instrumentNumberField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("instrumentNumberField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNewUserEmailTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("newUserEmailTextField", root);
    }

    public com.codename1.ui.TextField findNewUserEmailTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("newUserEmailTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("newUserEmailTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAppSettings(Component root) {
        return (com.codename1.ui.Button)findByName("appSettings", root);
    }

    public com.codename1.ui.Button findAppSettings() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("appSettings", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("appSettings", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSaveAppSettings(Component root) {
        return (com.codename1.ui.Button)findByName("saveAppSettings", root);
    }

    public com.codename1.ui.Button findSaveAppSettings() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("saveAppSettings", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("saveAppSettings", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel4(Component root) {
        return (com.codename1.ui.Label)findByName("Label4", root);
    }

    public com.codename1.ui.Label findLabel4() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label4", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label4", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findDepositToPhone(Component root) {
        return (com.codename1.ui.Button)findByName("depositToPhone", root);
    }

    public com.codename1.ui.Button findDepositToPhone() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("depositToPhone", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("depositToPhone", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findTellerNumberField(Component root) {
        return (com.codename1.ui.TextField)findByName("tellerNumberField", root);
    }

    public com.codename1.ui.TextField findTellerNumberField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("tellerNumberField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("tellerNumberField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findAdminSettingsContainer(Component root) {
        return (com.codename1.ui.Container)findByName("adminSettingsContainer", root);
    }

    public com.codename1.ui.Container findAdminSettingsContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("adminSettingsContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("adminSettingsContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatPostingSeq(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatPostingSeq", root);
    }

    public com.codename1.ui.Label findMiniStatPostingSeq() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatPostingSeq", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatPostingSeq", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelAppSettings(Component root) {
        return (com.codename1.ui.Button)findByName("cancelAppSettings", root);
    }

    public com.codename1.ui.Button findCancelAppSettings() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelAppSettings", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelAppSettings", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNewUserPasswordTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("newUserPasswordTextField", root);
    }

    public com.codename1.ui.TextField findNewUserPasswordTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("newUserPasswordTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("newUserPasswordTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelSignUp(Component root) {
        return (com.codename1.ui.Button)findByName("cancelSignUp", root);
    }

    public com.codename1.ui.Button findCancelSignUp() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelSignUp", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelSignUp", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findTransactionsUploadedMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("transactionsUploadedMultiList", root);
    }

    public com.codename1.ui.list.MultiList findTransactionsUploadedMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("transactionsUploadedMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("transactionsUploadedMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findWithdrawalNarrationTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("withdrawalNarrationTextArea", root);
    }

    public com.codename1.ui.TextArea findWithdrawalNarrationTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("withdrawalNarrationTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("withdrawalNarrationTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNewAdminNameTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("newAdminNameTextField", root);
    }

    public com.codename1.ui.TextField findNewAdminNameTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("newAdminNameTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("newAdminNameTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findMiniStatOK(Component root) {
        return (com.codename1.ui.Button)findByName("miniStatOK", root);
    }

    public com.codename1.ui.Button findMiniStatOK() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("miniStatOK", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("miniStatOK", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findMiniStatBranchAddress(Component root) {
        return (com.codename1.ui.TextArea)findByName("miniStatBranchAddress", root);
    }

    public com.codename1.ui.TextArea findMiniStatBranchAddress() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("miniStatBranchAddress", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("miniStatBranchAddress", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findUserGoOffline(Component root) {
        return (com.codename1.ui.Button)findByName("userGoOffline", root);
    }

    public com.codename1.ui.Button findUserGoOffline() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("userGoOffline", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("userGoOffline", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findUsernameTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("usernameTextField", root);
    }

    public com.codename1.ui.TextField findUsernameTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("usernameTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("usernameTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNewUserTellerIDTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("newUserTellerIDTextField", root);
    }

    public com.codename1.ui.TextField findNewUserTellerIDTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("newUserTellerIDTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("newUserTellerIDTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findCurrentBalance(Component root) {
        return (com.codename1.ui.Label)findByName("currentBalance", root);
    }

    public com.codename1.ui.Label findCurrentBalance() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("currentBalance", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("currentBalance", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findWithdrawalAcctNumberTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("withdrawalAcctNumberTextField", root);
    }

    public com.codename1.ui.TextField findWithdrawalAcctNumberTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("withdrawalAcctNumberTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("withdrawalAcctNumberTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findTranDescArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("tranDescArea", root);
    }

    public com.codename1.ui.TextArea findTranDescArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("tranDescArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("tranDescArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("TextArea", root);
    }

    public com.codename1.ui.TextArea findTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("TextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("TextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findUploadAllTransactions(Component root) {
        return (com.codename1.ui.Button)findByName("uploadAllTransactions", root);
    }

    public com.codename1.ui.Button findUploadAllTransactions() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("uploadAllTransactions", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("uploadAllTransactions", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findOfflineAmountTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("offlineAmountTextField", root);
    }

    public com.codename1.ui.TextField findOfflineAmountTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("offlineAmountTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("offlineAmountTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAddAccount(Component root) {
        return (com.codename1.ui.Button)findByName("addAccount", root);
    }

    public com.codename1.ui.Button findAddAccount() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("addAccount", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("addAccount", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancel(Component root) {
        return (com.codename1.ui.Button)findByName("Cancel", root);
    }

    public com.codename1.ui.Button findCancel() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("Cancel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("Cancel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCheckTokenToWithdraw(Component root) {
        return (com.codename1.ui.Button)findByName("checkTokenToWithdraw", root);
    }

    public com.codename1.ui.Button findCheckTokenToWithdraw() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("checkTokenToWithdraw", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("checkTokenToWithdraw", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBalInquiryOK(Component root) {
        return (com.codename1.ui.Button)findByName("balInquiryOK", root);
    }

    public com.codename1.ui.Button findBalInquiryOK() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("balInquiryOK", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("balInquiryOK", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findCustAcctName(Component root) {
        return (com.codename1.ui.Label)findByName("custAcctName", root);
    }

    public com.codename1.ui.Label findCustAcctName() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("custAcctName", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("custAcctName", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer(Component root) {
        return (com.codename1.ui.Container)findByName("Container", root);
    }

    public com.codename1.ui.Container findContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findLoginUser(Component root) {
        return (com.codename1.ui.Button)findByName("loginUser", root);
    }

    public com.codename1.ui.Button findLoginUser() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("loginUser", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("loginUser", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findWithdrawOnline(Component root) {
        return (com.codename1.ui.Button)findByName("withdrawOnline", root);
    }

    public com.codename1.ui.Button findWithdrawOnline() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("withdrawOnline", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("withdrawOnline", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCreateNewUser(Component root) {
        return (com.codename1.ui.Button)findByName("createNewUser", root);
    }

    public com.codename1.ui.Button findCreateNewUser() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("createNewUser", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("createNewUser", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatStartDate(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatStartDate", root);
    }

    public com.codename1.ui.Label findMiniStatStartDate() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatStartDate", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatStartDate", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.List findAccountList(Component root) {
        return (com.codename1.ui.List)findByName("accountList", root);
    }

    public com.codename1.ui.List findAccountList() {
        com.codename1.ui.List cmp = (com.codename1.ui.List)findByName("accountList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.List)findByName("accountList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findFetchInfo(Component root) {
        return (com.codename1.ui.Button)findByName("fetchInfo", root);
    }

    public com.codename1.ui.Button findFetchInfo() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("fetchInfo", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("fetchInfo", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatCreditOrDebit(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatCreditOrDebit", root);
    }

    public com.codename1.ui.Label findMiniStatCreditOrDebit() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatCreditOrDebit", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatCreditOrDebit", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findMiniStatBranchName(Component root) {
        return (com.codename1.ui.Label)findByName("miniStatBranchName", root);
    }

    public com.codename1.ui.Label findMiniStatBranchName() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("miniStatBranchName", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("miniStatBranchName", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findAdminPasswordTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("adminPasswordTextField", root);
    }

    public com.codename1.ui.TextField findAdminPasswordTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("adminPasswordTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("adminPasswordTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelWithdrawal(Component root) {
        return (com.codename1.ui.Button)findByName("cancelWithdrawal", root);
    }

    public com.codename1.ui.Button findCancelWithdrawal() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelWithdrawal", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelWithdrawal", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findAccountName(Component root) {
        return (com.codename1.ui.Label)findByName("accountName", root);
    }

    public com.codename1.ui.Label findAccountName() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("accountName", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("accountName", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findContinue(Component root) {
        return (com.codename1.ui.Button)findByName("continue", root);
    }

    public com.codename1.ui.Button findContinue() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("continue", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("continue", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findAdminNameTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("adminNameTextField", root);
    }

    public com.codename1.ui.TextField findAdminNameTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("adminNameTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("adminNameTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBalanceInquiry(Component root) {
        return (com.codename1.ui.Button)findByName("balanceInquiry", root);
    }

    public com.codename1.ui.Button findBalanceInquiry() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("balanceInquiry", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("balanceInquiry", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelAmount(Component root) {
        return (com.codename1.ui.Button)findByName("cancelAmount", root);
    }

    public com.codename1.ui.Button findCancelAmount() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelAmount", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelAmount", aboutToShowThisContainer);
        }
        return cmp;
    }

    protected void exitForm(Form f) {
        if("AccountToPullInfo".equals(f.getName())) {
            exitAccountToPullInfo(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryMFBs".equals(f.getName())) {
            exitBalInquiryMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToDeposit".equals(f.getName())) {
            exitAmountToDeposit(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositAcctNumber".equals(f.getName())) {
            exitDepositAcctNumber(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToWithdraw".equals(f.getName())) {
            exitAmountToWithdraw(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfUpload".equals(f.getName())) {
            exitSummaryOfUpload(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewAppUser".equals(f.getName())) {
            exitNewAppUser(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalAcctNumber".equals(f.getName())) {
            exitWithdrawalAcctNumber(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTransactionsDisplay".equals(f.getName())) {
            exitOfflineTransactionsDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineLogin".equals(f.getName())) {
            exitOfflineLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTellerCreate".equals(f.getName())) {
            exitOfflineTellerCreate(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OnlineLogin".equals(f.getName())) {
            exitOnlineLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenWithdrawal".equals(f.getName())) {
            exitDailyTokenWithdrawal(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToDeposit".equals(f.getName())) {
            exitCustomerToDeposit(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(f.getName())) {
            exitSettings(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalMFBs".equals(f.getName())) {
            exitWithdrawalMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachMiniStat".equals(f.getName())) {
            exitEachMiniStat(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatDisplay".equals(f.getName())) {
            exitMiniStatDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineDepositForm".equals(f.getName())) {
            exitOfflineDepositForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatForm".equals(f.getName())) {
            exitMiniStatForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositMFBs".equals(f.getName())) {
            exitDepositMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomers".equals(f.getName())) {
            exitStoredCustomers(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenDeposit".equals(f.getName())) {
            exitDailyTokenDeposit(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("TransactionMenu".equals(f.getName())) {
            exitTransactionMenu(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachOfflineTransaction".equals(f.getName())) {
            exitEachOfflineTransaction(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryAcctNumber".equals(f.getName())) {
            exitBalInquiryAcctNumber(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SettingsLogin".equals(f.getName())) {
            exitSettingsLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AdminCreateName".equals(f.getName())) {
            exitAdminCreateName(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToWithdraw".equals(f.getName())) {
            exitCustomerToWithdraw(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatMFBs".equals(f.getName())) {
            exitMiniStatMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineMenu".equals(f.getName())) {
            exitOfflineMenu(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("InstrumentForm".equals(f.getName())) {
            exitInstrumentForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfDownload".equals(f.getName())) {
            exitSummaryOfDownload(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryDisplay".equals(f.getName())) {
            exitBalInquiryDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomer".equals(f.getName())) {
            exitStoredCustomer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            exitMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void exitAccountToPullInfo(Form f) {
    }


    protected void exitBalInquiryMFBs(Form f) {
    }


    protected void exitAmountToDeposit(Form f) {
    }


    protected void exitDepositAcctNumber(Form f) {
    }


    protected void exitAmountToWithdraw(Form f) {
    }


    protected void exitSummaryOfUpload(Form f) {
    }


    protected void exitNewAppUser(Form f) {
    }


    protected void exitWithdrawalAcctNumber(Form f) {
    }


    protected void exitOfflineTransactionsDisplay(Form f) {
    }


    protected void exitOfflineLogin(Form f) {
    }


    protected void exitOfflineTellerCreate(Form f) {
    }


    protected void exitOnlineLogin(Form f) {
    }


    protected void exitDailyTokenWithdrawal(Form f) {
    }


    protected void exitCustomerToDeposit(Form f) {
    }


    protected void exitSettings(Form f) {
    }


    protected void exitWithdrawalMFBs(Form f) {
    }


    protected void exitEachMiniStat(Form f) {
    }


    protected void exitMiniStatDisplay(Form f) {
    }


    protected void exitOfflineDepositForm(Form f) {
    }


    protected void exitMiniStatForm(Form f) {
    }


    protected void exitDepositMFBs(Form f) {
    }


    protected void exitStoredCustomers(Form f) {
    }


    protected void exitDailyTokenDeposit(Form f) {
    }


    protected void exitTransactionMenu(Form f) {
    }


    protected void exitEachOfflineTransaction(Form f) {
    }


    protected void exitBalInquiryAcctNumber(Form f) {
    }


    protected void exitSettingsLogin(Form f) {
    }


    protected void exitAdminCreateName(Form f) {
    }


    protected void exitCustomerToWithdraw(Form f) {
    }


    protected void exitMiniStatMFBs(Form f) {
    }


    protected void exitOfflineMenu(Form f) {
    }


    protected void exitInstrumentForm(Form f) {
    }


    protected void exitSummaryOfDownload(Form f) {
    }


    protected void exitBalInquiryDisplay(Form f) {
    }


    protected void exitStoredCustomer(Form f) {
    }


    protected void exitMain(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("AccountToPullInfo".equals(f.getName())) {
            beforeAccountToPullInfo(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryMFBs".equals(f.getName())) {
            beforeBalInquiryMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToDeposit".equals(f.getName())) {
            beforeAmountToDeposit(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositAcctNumber".equals(f.getName())) {
            beforeDepositAcctNumber(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToWithdraw".equals(f.getName())) {
            beforeAmountToWithdraw(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfUpload".equals(f.getName())) {
            beforeSummaryOfUpload(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewAppUser".equals(f.getName())) {
            beforeNewAppUser(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalAcctNumber".equals(f.getName())) {
            beforeWithdrawalAcctNumber(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTransactionsDisplay".equals(f.getName())) {
            beforeOfflineTransactionsDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineLogin".equals(f.getName())) {
            beforeOfflineLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTellerCreate".equals(f.getName())) {
            beforeOfflineTellerCreate(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OnlineLogin".equals(f.getName())) {
            beforeOnlineLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenWithdrawal".equals(f.getName())) {
            beforeDailyTokenWithdrawal(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToDeposit".equals(f.getName())) {
            beforeCustomerToDeposit(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(f.getName())) {
            beforeSettings(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalMFBs".equals(f.getName())) {
            beforeWithdrawalMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachMiniStat".equals(f.getName())) {
            beforeEachMiniStat(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatDisplay".equals(f.getName())) {
            beforeMiniStatDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineDepositForm".equals(f.getName())) {
            beforeOfflineDepositForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatForm".equals(f.getName())) {
            beforeMiniStatForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositMFBs".equals(f.getName())) {
            beforeDepositMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomers".equals(f.getName())) {
            beforeStoredCustomers(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenDeposit".equals(f.getName())) {
            beforeDailyTokenDeposit(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("TransactionMenu".equals(f.getName())) {
            beforeTransactionMenu(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachOfflineTransaction".equals(f.getName())) {
            beforeEachOfflineTransaction(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryAcctNumber".equals(f.getName())) {
            beforeBalInquiryAcctNumber(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SettingsLogin".equals(f.getName())) {
            beforeSettingsLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AdminCreateName".equals(f.getName())) {
            beforeAdminCreateName(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToWithdraw".equals(f.getName())) {
            beforeCustomerToWithdraw(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatMFBs".equals(f.getName())) {
            beforeMiniStatMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineMenu".equals(f.getName())) {
            beforeOfflineMenu(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("InstrumentForm".equals(f.getName())) {
            beforeInstrumentForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfDownload".equals(f.getName())) {
            beforeSummaryOfDownload(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryDisplay".equals(f.getName())) {
            beforeBalInquiryDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomer".equals(f.getName())) {
            beforeStoredCustomer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            beforeMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeAccountToPullInfo(Form f) {
    }


    protected void beforeBalInquiryMFBs(Form f) {
    }


    protected void beforeAmountToDeposit(Form f) {
    }


    protected void beforeDepositAcctNumber(Form f) {
    }


    protected void beforeAmountToWithdraw(Form f) {
    }


    protected void beforeSummaryOfUpload(Form f) {
    }


    protected void beforeNewAppUser(Form f) {
    }


    protected void beforeWithdrawalAcctNumber(Form f) {
    }


    protected void beforeOfflineTransactionsDisplay(Form f) {
    }


    protected void beforeOfflineLogin(Form f) {
    }


    protected void beforeOfflineTellerCreate(Form f) {
    }


    protected void beforeOnlineLogin(Form f) {
    }


    protected void beforeDailyTokenWithdrawal(Form f) {
    }


    protected void beforeCustomerToDeposit(Form f) {
    }


    protected void beforeSettings(Form f) {
    }


    protected void beforeWithdrawalMFBs(Form f) {
    }


    protected void beforeEachMiniStat(Form f) {
    }


    protected void beforeMiniStatDisplay(Form f) {
    }


    protected void beforeOfflineDepositForm(Form f) {
    }


    protected void beforeMiniStatForm(Form f) {
    }


    protected void beforeDepositMFBs(Form f) {
    }


    protected void beforeStoredCustomers(Form f) {
    }


    protected void beforeDailyTokenDeposit(Form f) {
    }


    protected void beforeTransactionMenu(Form f) {
    }


    protected void beforeEachOfflineTransaction(Form f) {
    }


    protected void beforeBalInquiryAcctNumber(Form f) {
    }


    protected void beforeSettingsLogin(Form f) {
    }


    protected void beforeAdminCreateName(Form f) {
    }


    protected void beforeCustomerToWithdraw(Form f) {
    }


    protected void beforeMiniStatMFBs(Form f) {
    }


    protected void beforeOfflineMenu(Form f) {
    }


    protected void beforeInstrumentForm(Form f) {
    }


    protected void beforeSummaryOfDownload(Form f) {
    }


    protected void beforeBalInquiryDisplay(Form f) {
    }


    protected void beforeStoredCustomer(Form f) {
    }


    protected void beforeMain(Form f) {
    }

    protected void beforeShowContainer(Container c) {
        aboutToShowThisContainer = c;
        if("AccountToPullInfo".equals(c.getName())) {
            beforeContainerAccountToPullInfo(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryMFBs".equals(c.getName())) {
            beforeContainerBalInquiryMFBs(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToDeposit".equals(c.getName())) {
            beforeContainerAmountToDeposit(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositAcctNumber".equals(c.getName())) {
            beforeContainerDepositAcctNumber(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToWithdraw".equals(c.getName())) {
            beforeContainerAmountToWithdraw(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfUpload".equals(c.getName())) {
            beforeContainerSummaryOfUpload(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewAppUser".equals(c.getName())) {
            beforeContainerNewAppUser(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalAcctNumber".equals(c.getName())) {
            beforeContainerWithdrawalAcctNumber(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTransactionsDisplay".equals(c.getName())) {
            beforeContainerOfflineTransactionsDisplay(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineLogin".equals(c.getName())) {
            beforeContainerOfflineLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTellerCreate".equals(c.getName())) {
            beforeContainerOfflineTellerCreate(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OnlineLogin".equals(c.getName())) {
            beforeContainerOnlineLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenWithdrawal".equals(c.getName())) {
            beforeContainerDailyTokenWithdrawal(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToDeposit".equals(c.getName())) {
            beforeContainerCustomerToDeposit(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(c.getName())) {
            beforeContainerSettings(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalMFBs".equals(c.getName())) {
            beforeContainerWithdrawalMFBs(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachMiniStat".equals(c.getName())) {
            beforeContainerEachMiniStat(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatDisplay".equals(c.getName())) {
            beforeContainerMiniStatDisplay(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineDepositForm".equals(c.getName())) {
            beforeContainerOfflineDepositForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatForm".equals(c.getName())) {
            beforeContainerMiniStatForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositMFBs".equals(c.getName())) {
            beforeContainerDepositMFBs(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomers".equals(c.getName())) {
            beforeContainerStoredCustomers(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenDeposit".equals(c.getName())) {
            beforeContainerDailyTokenDeposit(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("TransactionMenu".equals(c.getName())) {
            beforeContainerTransactionMenu(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachOfflineTransaction".equals(c.getName())) {
            beforeContainerEachOfflineTransaction(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryAcctNumber".equals(c.getName())) {
            beforeContainerBalInquiryAcctNumber(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SettingsLogin".equals(c.getName())) {
            beforeContainerSettingsLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("AdminCreateName".equals(c.getName())) {
            beforeContainerAdminCreateName(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToWithdraw".equals(c.getName())) {
            beforeContainerCustomerToWithdraw(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatMFBs".equals(c.getName())) {
            beforeContainerMiniStatMFBs(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineMenu".equals(c.getName())) {
            beforeContainerOfflineMenu(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("InstrumentForm".equals(c.getName())) {
            beforeContainerInstrumentForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfDownload".equals(c.getName())) {
            beforeContainerSummaryOfDownload(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryDisplay".equals(c.getName())) {
            beforeContainerBalInquiryDisplay(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomer".equals(c.getName())) {
            beforeContainerStoredCustomer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            beforeContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeContainerAccountToPullInfo(Container c) {
    }


    protected void beforeContainerBalInquiryMFBs(Container c) {
    }


    protected void beforeContainerAmountToDeposit(Container c) {
    }


    protected void beforeContainerDepositAcctNumber(Container c) {
    }


    protected void beforeContainerAmountToWithdraw(Container c) {
    }


    protected void beforeContainerSummaryOfUpload(Container c) {
    }


    protected void beforeContainerNewAppUser(Container c) {
    }


    protected void beforeContainerWithdrawalAcctNumber(Container c) {
    }


    protected void beforeContainerOfflineTransactionsDisplay(Container c) {
    }


    protected void beforeContainerOfflineLogin(Container c) {
    }


    protected void beforeContainerOfflineTellerCreate(Container c) {
    }


    protected void beforeContainerOnlineLogin(Container c) {
    }


    protected void beforeContainerDailyTokenWithdrawal(Container c) {
    }


    protected void beforeContainerCustomerToDeposit(Container c) {
    }


    protected void beforeContainerSettings(Container c) {
    }


    protected void beforeContainerWithdrawalMFBs(Container c) {
    }


    protected void beforeContainerEachMiniStat(Container c) {
    }


    protected void beforeContainerMiniStatDisplay(Container c) {
    }


    protected void beforeContainerOfflineDepositForm(Container c) {
    }


    protected void beforeContainerMiniStatForm(Container c) {
    }


    protected void beforeContainerDepositMFBs(Container c) {
    }


    protected void beforeContainerStoredCustomers(Container c) {
    }


    protected void beforeContainerDailyTokenDeposit(Container c) {
    }


    protected void beforeContainerTransactionMenu(Container c) {
    }


    protected void beforeContainerEachOfflineTransaction(Container c) {
    }


    protected void beforeContainerBalInquiryAcctNumber(Container c) {
    }


    protected void beforeContainerSettingsLogin(Container c) {
    }


    protected void beforeContainerAdminCreateName(Container c) {
    }


    protected void beforeContainerCustomerToWithdraw(Container c) {
    }


    protected void beforeContainerMiniStatMFBs(Container c) {
    }


    protected void beforeContainerOfflineMenu(Container c) {
    }


    protected void beforeContainerInstrumentForm(Container c) {
    }


    protected void beforeContainerSummaryOfDownload(Container c) {
    }


    protected void beforeContainerBalInquiryDisplay(Container c) {
    }


    protected void beforeContainerStoredCustomer(Container c) {
    }


    protected void beforeContainerMain(Container c) {
    }

    protected void postShow(Form f) {
        if("AccountToPullInfo".equals(f.getName())) {
            postAccountToPullInfo(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryMFBs".equals(f.getName())) {
            postBalInquiryMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToDeposit".equals(f.getName())) {
            postAmountToDeposit(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositAcctNumber".equals(f.getName())) {
            postDepositAcctNumber(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToWithdraw".equals(f.getName())) {
            postAmountToWithdraw(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfUpload".equals(f.getName())) {
            postSummaryOfUpload(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewAppUser".equals(f.getName())) {
            postNewAppUser(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalAcctNumber".equals(f.getName())) {
            postWithdrawalAcctNumber(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTransactionsDisplay".equals(f.getName())) {
            postOfflineTransactionsDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineLogin".equals(f.getName())) {
            postOfflineLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTellerCreate".equals(f.getName())) {
            postOfflineTellerCreate(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OnlineLogin".equals(f.getName())) {
            postOnlineLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenWithdrawal".equals(f.getName())) {
            postDailyTokenWithdrawal(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToDeposit".equals(f.getName())) {
            postCustomerToDeposit(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(f.getName())) {
            postSettings(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalMFBs".equals(f.getName())) {
            postWithdrawalMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachMiniStat".equals(f.getName())) {
            postEachMiniStat(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatDisplay".equals(f.getName())) {
            postMiniStatDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineDepositForm".equals(f.getName())) {
            postOfflineDepositForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatForm".equals(f.getName())) {
            postMiniStatForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositMFBs".equals(f.getName())) {
            postDepositMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomers".equals(f.getName())) {
            postStoredCustomers(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenDeposit".equals(f.getName())) {
            postDailyTokenDeposit(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("TransactionMenu".equals(f.getName())) {
            postTransactionMenu(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachOfflineTransaction".equals(f.getName())) {
            postEachOfflineTransaction(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryAcctNumber".equals(f.getName())) {
            postBalInquiryAcctNumber(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SettingsLogin".equals(f.getName())) {
            postSettingsLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AdminCreateName".equals(f.getName())) {
            postAdminCreateName(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToWithdraw".equals(f.getName())) {
            postCustomerToWithdraw(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatMFBs".equals(f.getName())) {
            postMiniStatMFBs(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineMenu".equals(f.getName())) {
            postOfflineMenu(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("InstrumentForm".equals(f.getName())) {
            postInstrumentForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfDownload".equals(f.getName())) {
            postSummaryOfDownload(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryDisplay".equals(f.getName())) {
            postBalInquiryDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomer".equals(f.getName())) {
            postStoredCustomer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            postMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postAccountToPullInfo(Form f) {
    }


    protected void postBalInquiryMFBs(Form f) {
    }


    protected void postAmountToDeposit(Form f) {
    }


    protected void postDepositAcctNumber(Form f) {
    }


    protected void postAmountToWithdraw(Form f) {
    }


    protected void postSummaryOfUpload(Form f) {
    }


    protected void postNewAppUser(Form f) {
    }


    protected void postWithdrawalAcctNumber(Form f) {
    }


    protected void postOfflineTransactionsDisplay(Form f) {
    }


    protected void postOfflineLogin(Form f) {
    }


    protected void postOfflineTellerCreate(Form f) {
    }


    protected void postOnlineLogin(Form f) {
    }


    protected void postDailyTokenWithdrawal(Form f) {
    }


    protected void postCustomerToDeposit(Form f) {
    }


    protected void postSettings(Form f) {
    }


    protected void postWithdrawalMFBs(Form f) {
    }


    protected void postEachMiniStat(Form f) {
    }


    protected void postMiniStatDisplay(Form f) {
    }


    protected void postOfflineDepositForm(Form f) {
    }


    protected void postMiniStatForm(Form f) {
    }


    protected void postDepositMFBs(Form f) {
    }


    protected void postStoredCustomers(Form f) {
    }


    protected void postDailyTokenDeposit(Form f) {
    }


    protected void postTransactionMenu(Form f) {
    }


    protected void postEachOfflineTransaction(Form f) {
    }


    protected void postBalInquiryAcctNumber(Form f) {
    }


    protected void postSettingsLogin(Form f) {
    }


    protected void postAdminCreateName(Form f) {
    }


    protected void postCustomerToWithdraw(Form f) {
    }


    protected void postMiniStatMFBs(Form f) {
    }


    protected void postOfflineMenu(Form f) {
    }


    protected void postInstrumentForm(Form f) {
    }


    protected void postSummaryOfDownload(Form f) {
    }


    protected void postBalInquiryDisplay(Form f) {
    }


    protected void postStoredCustomer(Form f) {
    }


    protected void postMain(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("AccountToPullInfo".equals(c.getName())) {
            postContainerAccountToPullInfo(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryMFBs".equals(c.getName())) {
            postContainerBalInquiryMFBs(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToDeposit".equals(c.getName())) {
            postContainerAmountToDeposit(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositAcctNumber".equals(c.getName())) {
            postContainerDepositAcctNumber(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToWithdraw".equals(c.getName())) {
            postContainerAmountToWithdraw(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfUpload".equals(c.getName())) {
            postContainerSummaryOfUpload(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewAppUser".equals(c.getName())) {
            postContainerNewAppUser(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalAcctNumber".equals(c.getName())) {
            postContainerWithdrawalAcctNumber(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTransactionsDisplay".equals(c.getName())) {
            postContainerOfflineTransactionsDisplay(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineLogin".equals(c.getName())) {
            postContainerOfflineLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTellerCreate".equals(c.getName())) {
            postContainerOfflineTellerCreate(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OnlineLogin".equals(c.getName())) {
            postContainerOnlineLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenWithdrawal".equals(c.getName())) {
            postContainerDailyTokenWithdrawal(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToDeposit".equals(c.getName())) {
            postContainerCustomerToDeposit(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(c.getName())) {
            postContainerSettings(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalMFBs".equals(c.getName())) {
            postContainerWithdrawalMFBs(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachMiniStat".equals(c.getName())) {
            postContainerEachMiniStat(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatDisplay".equals(c.getName())) {
            postContainerMiniStatDisplay(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineDepositForm".equals(c.getName())) {
            postContainerOfflineDepositForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatForm".equals(c.getName())) {
            postContainerMiniStatForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositMFBs".equals(c.getName())) {
            postContainerDepositMFBs(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomers".equals(c.getName())) {
            postContainerStoredCustomers(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenDeposit".equals(c.getName())) {
            postContainerDailyTokenDeposit(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("TransactionMenu".equals(c.getName())) {
            postContainerTransactionMenu(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachOfflineTransaction".equals(c.getName())) {
            postContainerEachOfflineTransaction(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryAcctNumber".equals(c.getName())) {
            postContainerBalInquiryAcctNumber(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SettingsLogin".equals(c.getName())) {
            postContainerSettingsLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("AdminCreateName".equals(c.getName())) {
            postContainerAdminCreateName(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToWithdraw".equals(c.getName())) {
            postContainerCustomerToWithdraw(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatMFBs".equals(c.getName())) {
            postContainerMiniStatMFBs(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineMenu".equals(c.getName())) {
            postContainerOfflineMenu(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("InstrumentForm".equals(c.getName())) {
            postContainerInstrumentForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfDownload".equals(c.getName())) {
            postContainerSummaryOfDownload(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryDisplay".equals(c.getName())) {
            postContainerBalInquiryDisplay(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomer".equals(c.getName())) {
            postContainerStoredCustomer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            postContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postContainerAccountToPullInfo(Container c) {
    }


    protected void postContainerBalInquiryMFBs(Container c) {
    }


    protected void postContainerAmountToDeposit(Container c) {
    }


    protected void postContainerDepositAcctNumber(Container c) {
    }


    protected void postContainerAmountToWithdraw(Container c) {
    }


    protected void postContainerSummaryOfUpload(Container c) {
    }


    protected void postContainerNewAppUser(Container c) {
    }


    protected void postContainerWithdrawalAcctNumber(Container c) {
    }


    protected void postContainerOfflineTransactionsDisplay(Container c) {
    }


    protected void postContainerOfflineLogin(Container c) {
    }


    protected void postContainerOfflineTellerCreate(Container c) {
    }


    protected void postContainerOnlineLogin(Container c) {
    }


    protected void postContainerDailyTokenWithdrawal(Container c) {
    }


    protected void postContainerCustomerToDeposit(Container c) {
    }


    protected void postContainerSettings(Container c) {
    }


    protected void postContainerWithdrawalMFBs(Container c) {
    }


    protected void postContainerEachMiniStat(Container c) {
    }


    protected void postContainerMiniStatDisplay(Container c) {
    }


    protected void postContainerOfflineDepositForm(Container c) {
    }


    protected void postContainerMiniStatForm(Container c) {
    }


    protected void postContainerDepositMFBs(Container c) {
    }


    protected void postContainerStoredCustomers(Container c) {
    }


    protected void postContainerDailyTokenDeposit(Container c) {
    }


    protected void postContainerTransactionMenu(Container c) {
    }


    protected void postContainerEachOfflineTransaction(Container c) {
    }


    protected void postContainerBalInquiryAcctNumber(Container c) {
    }


    protected void postContainerSettingsLogin(Container c) {
    }


    protected void postContainerAdminCreateName(Container c) {
    }


    protected void postContainerCustomerToWithdraw(Container c) {
    }


    protected void postContainerMiniStatMFBs(Container c) {
    }


    protected void postContainerOfflineMenu(Container c) {
    }


    protected void postContainerInstrumentForm(Container c) {
    }


    protected void postContainerSummaryOfDownload(Container c) {
    }


    protected void postContainerBalInquiryDisplay(Container c) {
    }


    protected void postContainerStoredCustomer(Container c) {
    }


    protected void postContainerMain(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("AccountToPullInfo".equals(rootName)) {
            onCreateAccountToPullInfo();
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryMFBs".equals(rootName)) {
            onCreateBalInquiryMFBs();
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToDeposit".equals(rootName)) {
            onCreateAmountToDeposit();
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositAcctNumber".equals(rootName)) {
            onCreateDepositAcctNumber();
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToWithdraw".equals(rootName)) {
            onCreateAmountToWithdraw();
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfUpload".equals(rootName)) {
            onCreateSummaryOfUpload();
            aboutToShowThisContainer = null;
            return;
        }

        if("NewAppUser".equals(rootName)) {
            onCreateNewAppUser();
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalAcctNumber".equals(rootName)) {
            onCreateWithdrawalAcctNumber();
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTransactionsDisplay".equals(rootName)) {
            onCreateOfflineTransactionsDisplay();
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineLogin".equals(rootName)) {
            onCreateOfflineLogin();
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTellerCreate".equals(rootName)) {
            onCreateOfflineTellerCreate();
            aboutToShowThisContainer = null;
            return;
        }

        if("OnlineLogin".equals(rootName)) {
            onCreateOnlineLogin();
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenWithdrawal".equals(rootName)) {
            onCreateDailyTokenWithdrawal();
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToDeposit".equals(rootName)) {
            onCreateCustomerToDeposit();
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(rootName)) {
            onCreateSettings();
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalMFBs".equals(rootName)) {
            onCreateWithdrawalMFBs();
            aboutToShowThisContainer = null;
            return;
        }

        if("EachMiniStat".equals(rootName)) {
            onCreateEachMiniStat();
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatDisplay".equals(rootName)) {
            onCreateMiniStatDisplay();
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineDepositForm".equals(rootName)) {
            onCreateOfflineDepositForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatForm".equals(rootName)) {
            onCreateMiniStatForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositMFBs".equals(rootName)) {
            onCreateDepositMFBs();
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomers".equals(rootName)) {
            onCreateStoredCustomers();
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenDeposit".equals(rootName)) {
            onCreateDailyTokenDeposit();
            aboutToShowThisContainer = null;
            return;
        }

        if("TransactionMenu".equals(rootName)) {
            onCreateTransactionMenu();
            aboutToShowThisContainer = null;
            return;
        }

        if("EachOfflineTransaction".equals(rootName)) {
            onCreateEachOfflineTransaction();
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryAcctNumber".equals(rootName)) {
            onCreateBalInquiryAcctNumber();
            aboutToShowThisContainer = null;
            return;
        }

        if("SettingsLogin".equals(rootName)) {
            onCreateSettingsLogin();
            aboutToShowThisContainer = null;
            return;
        }

        if("AdminCreateName".equals(rootName)) {
            onCreateAdminCreateName();
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToWithdraw".equals(rootName)) {
            onCreateCustomerToWithdraw();
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatMFBs".equals(rootName)) {
            onCreateMiniStatMFBs();
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineMenu".equals(rootName)) {
            onCreateOfflineMenu();
            aboutToShowThisContainer = null;
            return;
        }

        if("InstrumentForm".equals(rootName)) {
            onCreateInstrumentForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfDownload".equals(rootName)) {
            onCreateSummaryOfDownload();
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryDisplay".equals(rootName)) {
            onCreateBalInquiryDisplay();
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomer".equals(rootName)) {
            onCreateStoredCustomer();
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(rootName)) {
            onCreateMain();
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void onCreateAccountToPullInfo() {
    }


    protected void onCreateBalInquiryMFBs() {
    }


    protected void onCreateAmountToDeposit() {
    }


    protected void onCreateDepositAcctNumber() {
    }


    protected void onCreateAmountToWithdraw() {
    }


    protected void onCreateSummaryOfUpload() {
    }


    protected void onCreateNewAppUser() {
    }


    protected void onCreateWithdrawalAcctNumber() {
    }


    protected void onCreateOfflineTransactionsDisplay() {
    }


    protected void onCreateOfflineLogin() {
    }


    protected void onCreateOfflineTellerCreate() {
    }


    protected void onCreateOnlineLogin() {
    }


    protected void onCreateDailyTokenWithdrawal() {
    }


    protected void onCreateCustomerToDeposit() {
    }


    protected void onCreateSettings() {
    }


    protected void onCreateWithdrawalMFBs() {
    }


    protected void onCreateEachMiniStat() {
    }


    protected void onCreateMiniStatDisplay() {
    }


    protected void onCreateOfflineDepositForm() {
    }


    protected void onCreateMiniStatForm() {
    }


    protected void onCreateDepositMFBs() {
    }


    protected void onCreateStoredCustomers() {
    }


    protected void onCreateDailyTokenDeposit() {
    }


    protected void onCreateTransactionMenu() {
    }


    protected void onCreateEachOfflineTransaction() {
    }


    protected void onCreateBalInquiryAcctNumber() {
    }


    protected void onCreateSettingsLogin() {
    }


    protected void onCreateAdminCreateName() {
    }


    protected void onCreateCustomerToWithdraw() {
    }


    protected void onCreateMiniStatMFBs() {
    }


    protected void onCreateOfflineMenu() {
    }


    protected void onCreateInstrumentForm() {
    }


    protected void onCreateSummaryOfDownload() {
    }


    protected void onCreateBalInquiryDisplay() {
    }


    protected void onCreateStoredCustomer() {
    }


    protected void onCreateMain() {
    }

    protected Hashtable getFormState(Form f) {
        Hashtable h = super.getFormState(f);
        if("AccountToPullInfo".equals(f.getName())) {
            getStateAccountToPullInfo(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("BalInquiryMFBs".equals(f.getName())) {
            getStateBalInquiryMFBs(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("AmountToDeposit".equals(f.getName())) {
            getStateAmountToDeposit(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("DepositAcctNumber".equals(f.getName())) {
            getStateDepositAcctNumber(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("AmountToWithdraw".equals(f.getName())) {
            getStateAmountToWithdraw(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("SummaryOfUpload".equals(f.getName())) {
            getStateSummaryOfUpload(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("NewAppUser".equals(f.getName())) {
            getStateNewAppUser(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("WithdrawalAcctNumber".equals(f.getName())) {
            getStateWithdrawalAcctNumber(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("OfflineTransactionsDisplay".equals(f.getName())) {
            getStateOfflineTransactionsDisplay(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("OfflineLogin".equals(f.getName())) {
            getStateOfflineLogin(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("OfflineTellerCreate".equals(f.getName())) {
            getStateOfflineTellerCreate(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("OnlineLogin".equals(f.getName())) {
            getStateOnlineLogin(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("DailyTokenWithdrawal".equals(f.getName())) {
            getStateDailyTokenWithdrawal(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("CustomerToDeposit".equals(f.getName())) {
            getStateCustomerToDeposit(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Settings".equals(f.getName())) {
            getStateSettings(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("WithdrawalMFBs".equals(f.getName())) {
            getStateWithdrawalMFBs(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("EachMiniStat".equals(f.getName())) {
            getStateEachMiniStat(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("MiniStatDisplay".equals(f.getName())) {
            getStateMiniStatDisplay(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("OfflineDepositForm".equals(f.getName())) {
            getStateOfflineDepositForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("MiniStatForm".equals(f.getName())) {
            getStateMiniStatForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("DepositMFBs".equals(f.getName())) {
            getStateDepositMFBs(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("StoredCustomers".equals(f.getName())) {
            getStateStoredCustomers(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("DailyTokenDeposit".equals(f.getName())) {
            getStateDailyTokenDeposit(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("TransactionMenu".equals(f.getName())) {
            getStateTransactionMenu(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("EachOfflineTransaction".equals(f.getName())) {
            getStateEachOfflineTransaction(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("BalInquiryAcctNumber".equals(f.getName())) {
            getStateBalInquiryAcctNumber(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("SettingsLogin".equals(f.getName())) {
            getStateSettingsLogin(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("AdminCreateName".equals(f.getName())) {
            getStateAdminCreateName(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("CustomerToWithdraw".equals(f.getName())) {
            getStateCustomerToWithdraw(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("MiniStatMFBs".equals(f.getName())) {
            getStateMiniStatMFBs(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("OfflineMenu".equals(f.getName())) {
            getStateOfflineMenu(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("InstrumentForm".equals(f.getName())) {
            getStateInstrumentForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("SummaryOfDownload".equals(f.getName())) {
            getStateSummaryOfDownload(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("BalInquiryDisplay".equals(f.getName())) {
            getStateBalInquiryDisplay(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("StoredCustomer".equals(f.getName())) {
            getStateStoredCustomer(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Main".equals(f.getName())) {
            getStateMain(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

            return h;
    }


    protected void getStateAccountToPullInfo(Form f, Hashtable h) {
    }


    protected void getStateBalInquiryMFBs(Form f, Hashtable h) {
    }


    protected void getStateAmountToDeposit(Form f, Hashtable h) {
    }


    protected void getStateDepositAcctNumber(Form f, Hashtable h) {
    }


    protected void getStateAmountToWithdraw(Form f, Hashtable h) {
    }


    protected void getStateSummaryOfUpload(Form f, Hashtable h) {
    }


    protected void getStateNewAppUser(Form f, Hashtable h) {
    }


    protected void getStateWithdrawalAcctNumber(Form f, Hashtable h) {
    }


    protected void getStateOfflineTransactionsDisplay(Form f, Hashtable h) {
    }


    protected void getStateOfflineLogin(Form f, Hashtable h) {
    }


    protected void getStateOfflineTellerCreate(Form f, Hashtable h) {
    }


    protected void getStateOnlineLogin(Form f, Hashtable h) {
    }


    protected void getStateDailyTokenWithdrawal(Form f, Hashtable h) {
    }


    protected void getStateCustomerToDeposit(Form f, Hashtable h) {
    }


    protected void getStateSettings(Form f, Hashtable h) {
    }


    protected void getStateWithdrawalMFBs(Form f, Hashtable h) {
    }


    protected void getStateEachMiniStat(Form f, Hashtable h) {
    }


    protected void getStateMiniStatDisplay(Form f, Hashtable h) {
    }


    protected void getStateOfflineDepositForm(Form f, Hashtable h) {
    }


    protected void getStateMiniStatForm(Form f, Hashtable h) {
    }


    protected void getStateDepositMFBs(Form f, Hashtable h) {
    }


    protected void getStateStoredCustomers(Form f, Hashtable h) {
    }


    protected void getStateDailyTokenDeposit(Form f, Hashtable h) {
    }


    protected void getStateTransactionMenu(Form f, Hashtable h) {
    }


    protected void getStateEachOfflineTransaction(Form f, Hashtable h) {
    }


    protected void getStateBalInquiryAcctNumber(Form f, Hashtable h) {
    }


    protected void getStateSettingsLogin(Form f, Hashtable h) {
    }


    protected void getStateAdminCreateName(Form f, Hashtable h) {
    }


    protected void getStateCustomerToWithdraw(Form f, Hashtable h) {
    }


    protected void getStateMiniStatMFBs(Form f, Hashtable h) {
    }


    protected void getStateOfflineMenu(Form f, Hashtable h) {
    }


    protected void getStateInstrumentForm(Form f, Hashtable h) {
    }


    protected void getStateSummaryOfDownload(Form f, Hashtable h) {
    }


    protected void getStateBalInquiryDisplay(Form f, Hashtable h) {
    }


    protected void getStateStoredCustomer(Form f, Hashtable h) {
    }


    protected void getStateMain(Form f, Hashtable h) {
    }

    protected void setFormState(Form f, Hashtable state) {
        super.setFormState(f, state);
        if("AccountToPullInfo".equals(f.getName())) {
            setStateAccountToPullInfo(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryMFBs".equals(f.getName())) {
            setStateBalInquiryMFBs(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToDeposit".equals(f.getName())) {
            setStateAmountToDeposit(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositAcctNumber".equals(f.getName())) {
            setStateDepositAcctNumber(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("AmountToWithdraw".equals(f.getName())) {
            setStateAmountToWithdraw(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfUpload".equals(f.getName())) {
            setStateSummaryOfUpload(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewAppUser".equals(f.getName())) {
            setStateNewAppUser(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalAcctNumber".equals(f.getName())) {
            setStateWithdrawalAcctNumber(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTransactionsDisplay".equals(f.getName())) {
            setStateOfflineTransactionsDisplay(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineLogin".equals(f.getName())) {
            setStateOfflineLogin(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineTellerCreate".equals(f.getName())) {
            setStateOfflineTellerCreate(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("OnlineLogin".equals(f.getName())) {
            setStateOnlineLogin(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenWithdrawal".equals(f.getName())) {
            setStateDailyTokenWithdrawal(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToDeposit".equals(f.getName())) {
            setStateCustomerToDeposit(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(f.getName())) {
            setStateSettings(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("WithdrawalMFBs".equals(f.getName())) {
            setStateWithdrawalMFBs(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachMiniStat".equals(f.getName())) {
            setStateEachMiniStat(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatDisplay".equals(f.getName())) {
            setStateMiniStatDisplay(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineDepositForm".equals(f.getName())) {
            setStateOfflineDepositForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatForm".equals(f.getName())) {
            setStateMiniStatForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("DepositMFBs".equals(f.getName())) {
            setStateDepositMFBs(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomers".equals(f.getName())) {
            setStateStoredCustomers(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("DailyTokenDeposit".equals(f.getName())) {
            setStateDailyTokenDeposit(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("TransactionMenu".equals(f.getName())) {
            setStateTransactionMenu(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachOfflineTransaction".equals(f.getName())) {
            setStateEachOfflineTransaction(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryAcctNumber".equals(f.getName())) {
            setStateBalInquiryAcctNumber(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("SettingsLogin".equals(f.getName())) {
            setStateSettingsLogin(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("AdminCreateName".equals(f.getName())) {
            setStateAdminCreateName(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("CustomerToWithdraw".equals(f.getName())) {
            setStateCustomerToWithdraw(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("MiniStatMFBs".equals(f.getName())) {
            setStateMiniStatMFBs(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("OfflineMenu".equals(f.getName())) {
            setStateOfflineMenu(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("InstrumentForm".equals(f.getName())) {
            setStateInstrumentForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("SummaryOfDownload".equals(f.getName())) {
            setStateSummaryOfDownload(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("BalInquiryDisplay".equals(f.getName())) {
            setStateBalInquiryDisplay(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("StoredCustomer".equals(f.getName())) {
            setStateStoredCustomer(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            setStateMain(f, state);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void setStateAccountToPullInfo(Form f, Hashtable state) {
    }


    protected void setStateBalInquiryMFBs(Form f, Hashtable state) {
    }


    protected void setStateAmountToDeposit(Form f, Hashtable state) {
    }


    protected void setStateDepositAcctNumber(Form f, Hashtable state) {
    }


    protected void setStateAmountToWithdraw(Form f, Hashtable state) {
    }


    protected void setStateSummaryOfUpload(Form f, Hashtable state) {
    }


    protected void setStateNewAppUser(Form f, Hashtable state) {
    }


    protected void setStateWithdrawalAcctNumber(Form f, Hashtable state) {
    }


    protected void setStateOfflineTransactionsDisplay(Form f, Hashtable state) {
    }


    protected void setStateOfflineLogin(Form f, Hashtable state) {
    }


    protected void setStateOfflineTellerCreate(Form f, Hashtable state) {
    }


    protected void setStateOnlineLogin(Form f, Hashtable state) {
    }


    protected void setStateDailyTokenWithdrawal(Form f, Hashtable state) {
    }


    protected void setStateCustomerToDeposit(Form f, Hashtable state) {
    }


    protected void setStateSettings(Form f, Hashtable state) {
    }


    protected void setStateWithdrawalMFBs(Form f, Hashtable state) {
    }


    protected void setStateEachMiniStat(Form f, Hashtable state) {
    }


    protected void setStateMiniStatDisplay(Form f, Hashtable state) {
    }


    protected void setStateOfflineDepositForm(Form f, Hashtable state) {
    }


    protected void setStateMiniStatForm(Form f, Hashtable state) {
    }


    protected void setStateDepositMFBs(Form f, Hashtable state) {
    }


    protected void setStateStoredCustomers(Form f, Hashtable state) {
    }


    protected void setStateDailyTokenDeposit(Form f, Hashtable state) {
    }


    protected void setStateTransactionMenu(Form f, Hashtable state) {
    }


    protected void setStateEachOfflineTransaction(Form f, Hashtable state) {
    }


    protected void setStateBalInquiryAcctNumber(Form f, Hashtable state) {
    }


    protected void setStateSettingsLogin(Form f, Hashtable state) {
    }


    protected void setStateAdminCreateName(Form f, Hashtable state) {
    }


    protected void setStateCustomerToWithdraw(Form f, Hashtable state) {
    }


    protected void setStateMiniStatMFBs(Form f, Hashtable state) {
    }


    protected void setStateOfflineMenu(Form f, Hashtable state) {
    }


    protected void setStateInstrumentForm(Form f, Hashtable state) {
    }


    protected void setStateSummaryOfDownload(Form f, Hashtable state) {
    }


    protected void setStateBalInquiryDisplay(Form f, Hashtable state) {
    }


    protected void setStateStoredCustomer(Form f, Hashtable state) {
    }


    protected void setStateMain(Form f, Hashtable state) {
    }

    protected boolean setListModel(List cmp) {
        String listName = cmp.getName();
        if("accountsWithErrorStatusList".equals(listName)) {
            return initListModelAccountsWithErrorStatusList(cmp);
        }
        if("withdrawaBankMultiList".equals(listName)) {
            return initListModelWithdrawaBankMultiList(cmp);
        }
        if("MiniStatMFBsMultiList".equals(listName)) {
            return initListModelMiniStatMFBsMultiList(cmp);
        }
        if("accountsWithErrorReturnCodeList".equals(listName)) {
            return initListModelAccountsWithErrorReturnCodeList(cmp);
        }
        if("storedCustomersMultiList".equals(listName)) {
            return initListModelStoredCustomersMultiList(cmp);
        }
        if("accountsStoredList".equals(listName)) {
            return initListModelAccountsStoredList(cmp);
        }
        if("allOfflineTransactionsMultiList".equals(listName)) {
            return initListModelAllOfflineTransactionsMultiList(cmp);
        }
        if("transactionsNotUploadedMultiList".equals(listName)) {
            return initListModelTransactionsNotUploadedMultiList(cmp);
        }
        if("balInquiryMFBMultiList".equals(listName)) {
            return initListModelBalInquiryMFBMultiList(cmp);
        }
        if("miniStatMultiList".equals(listName)) {
            return initListModelMiniStatMultiList(cmp);
        }
        if("depositMFBsMultiList".equals(listName)) {
            return initListModelDepositMFBsMultiList(cmp);
        }
        if("transactionsUploadedMultiList".equals(listName)) {
            return initListModelTransactionsUploadedMultiList(cmp);
        }
        if("accountList".equals(listName)) {
            return initListModelAccountList(cmp);
        }
        return super.setListModel(cmp);
    }

    protected boolean initListModelAccountsWithErrorStatusList(List cmp) {
        return false;
    }

    protected boolean initListModelWithdrawaBankMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelMiniStatMFBsMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelAccountsWithErrorReturnCodeList(List cmp) {
        return false;
    }

    protected boolean initListModelStoredCustomersMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelAccountsStoredList(List cmp) {
        return false;
    }

    protected boolean initListModelAllOfflineTransactionsMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelTransactionsNotUploadedMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelBalInquiryMFBMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelMiniStatMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelDepositMFBsMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelTransactionsUploadedMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelAccountList(List cmp) {
        return false;
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("AccountToPullInfo")) {
            if("fetchInfo".equals(c.getName())) {
                onAccountToPullInfo_FetchInfoAction(c, event);
                return;
            }
            if("cancelFetch".equals(c.getName())) {
                onAccountToPullInfo_CancelFetchAction(c, event);
                return;
            }
            if("acctNumberField".equals(c.getName())) {
                onAccountToPullInfo_AcctNumberFieldAction(c, event);
                return;
            }
            if("addAccount".equals(c.getName())) {
                onAccountToPullInfo_AddAccountAction(c, event);
                return;
            }
            if("accountList".equals(c.getName())) {
                onAccountToPullInfo_AccountListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("BalInquiryMFBs")) {
            if("balInquiryMFBMultiList".equals(c.getName())) {
                onBalInquiryMFBs_BalInquiryMFBMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("AmountToDeposit")) {
            if("amountToDepositField".equals(c.getName())) {
                onAmountToDeposit_AmountToDepositFieldAction(c, event);
                return;
            }
            if("tranDescArea".equals(c.getName())) {
                onAmountToDeposit_TranDescAreaAction(c, event);
                return;
            }
            if("tellerNumberField".equals(c.getName())) {
                onAmountToDeposit_TellerNumberFieldAction(c, event);
                return;
            }
            if("sendAmountToDeposit".equals(c.getName())) {
                onAmountToDeposit_SendAmountToDepositAction(c, event);
                return;
            }
            if("cancelAmount".equals(c.getName())) {
                onAmountToDeposit_CancelAmountAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("DepositAcctNumber")) {
            if("acctNumberTextField".equals(c.getName())) {
                onDepositAcctNumber_AcctNumberTextFieldAction(c, event);
                return;
            }
            if("verifyDeposit".equals(c.getName())) {
                onDepositAcctNumber_VerifyDepositAction(c, event);
                return;
            }
            if("cancelDeposit".equals(c.getName())) {
                onDepositAcctNumber_CancelDepositAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("AmountToWithdraw")) {
            if("amountToWithdrawTextField".equals(c.getName())) {
                onAmountToWithdraw_AmountToWithdrawTextFieldAction(c, event);
                return;
            }
            if("withdrawalNarrationTextArea".equals(c.getName())) {
                onAmountToWithdraw_WithdrawalNarrationTextAreaAction(c, event);
                return;
            }
            if("sendWithdraw".equals(c.getName())) {
                onAmountToWithdraw_SendWithdrawAction(c, event);
                return;
            }
            if("cancelWithdraw".equals(c.getName())) {
                onAmountToWithdraw_CancelWithdrawAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("SummaryOfUpload")) {
            if("transactionsUploadedMultiList".equals(c.getName())) {
                onSummaryOfUpload_TransactionsUploadedMultiListAction(c, event);
                return;
            }
            if("transactionsNotUploadedMultiList".equals(c.getName())) {
                onSummaryOfUpload_TransactionsNotUploadedMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("NewAppUser")) {
            if("newUserTellerIDTextField".equals(c.getName())) {
                onNewAppUser_NewUserTellerIDTextFieldAction(c, event);
                return;
            }
            if("newUserPasswordTextField".equals(c.getName())) {
                onNewAppUser_NewUserPasswordTextFieldAction(c, event);
                return;
            }
            if("newUserEmailTextField".equals(c.getName())) {
                onNewAppUser_NewUserEmailTextFieldAction(c, event);
                return;
            }
            if("tranPasswordTextField".equals(c.getName())) {
                onNewAppUser_TranPasswordTextFieldAction(c, event);
                return;
            }
            if("tellerTilTextField".equals(c.getName())) {
                onNewAppUser_TellerTilTextFieldAction(c, event);
                return;
            }
            if("signUpUser".equals(c.getName())) {
                onNewAppUser_SignUpUserAction(c, event);
                return;
            }
            if("cancelSignUp".equals(c.getName())) {
                onNewAppUser_CancelSignUpAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("WithdrawalAcctNumber")) {
            if("withdrawalAcctNumberTextField".equals(c.getName())) {
                onWithdrawalAcctNumber_WithdrawalAcctNumberTextFieldAction(c, event);
                return;
            }
            if("withdrawalVerify".equals(c.getName())) {
                onWithdrawalAcctNumber_WithdrawalVerifyAction(c, event);
                return;
            }
            if("cancelWithdrawal".equals(c.getName())) {
                onWithdrawalAcctNumber_CancelWithdrawalAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("OfflineTransactionsDisplay")) {
            if("allOfflineTransactionsMultiList".equals(c.getName())) {
                onOfflineTransactionsDisplay_AllOfflineTransactionsMultiListAction(c, event);
                return;
            }
            if("uploadAllTransactions".equals(c.getName())) {
                onOfflineTransactionsDisplay_UploadAllTransactionsAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("OfflineLogin")) {
            if("offlineUsernameTextField".equals(c.getName())) {
                onOfflineLogin_OfflineUsernameTextFieldAction(c, event);
                return;
            }
            if("offlinePasswordTextField".equals(c.getName())) {
                onOfflineLogin_OfflinePasswordTextFieldAction(c, event);
                return;
            }
            if("loginOffline".equals(c.getName())) {
                onOfflineLogin_LoginOfflineAction(c, event);
                return;
            }
            if("Cancel".equals(c.getName())) {
                onOfflineLogin_CancelAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("OfflineTellerCreate")) {
            if("newOfflineTellerTextField".equals(c.getName())) {
                onOfflineTellerCreate_NewOfflineTellerTextFieldAction(c, event);
                return;
            }
            if("newOfflineTellerPWDTextField".equals(c.getName())) {
                onOfflineTellerCreate_NewOfflineTellerPWDTextFieldAction(c, event);
                return;
            }
            if("mfbCodeTextField".equals(c.getName())) {
                onOfflineTellerCreate_MfbCodeTextFieldAction(c, event);
                return;
            }
            if("addOfflineTeller".equals(c.getName())) {
                onOfflineTellerCreate_AddOfflineTellerAction(c, event);
                return;
            }
            if("cancelOfflineTeller".equals(c.getName())) {
                onOfflineTellerCreate_CancelOfflineTellerAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("OnlineLogin")) {
            if("usernameTextField".equals(c.getName())) {
                onOnlineLogin_UsernameTextFieldAction(c, event);
                return;
            }
            if("passwordTextField".equals(c.getName())) {
                onOnlineLogin_PasswordTextFieldAction(c, event);
                return;
            }
            if("loginUser".equals(c.getName())) {
                onOnlineLogin_LoginUserAction(c, event);
                return;
            }
            if("cancel".equals(c.getName())) {
                onOnlineLogin_CancelAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("DailyTokenWithdrawal")) {
            if("TextArea".equals(c.getName())) {
                onDailyTokenWithdrawal_TextAreaAction(c, event);
                return;
            }
            if("withdrawalTranPWDTextField".equals(c.getName())) {
                onDailyTokenWithdrawal_WithdrawalTranPWDTextFieldAction(c, event);
                return;
            }
            if("checkTokenToWithdraw".equals(c.getName())) {
                onDailyTokenWithdrawal_CheckTokenToWithdrawAction(c, event);
                return;
            }
            if("cancelWithdrawaToken".equals(c.getName())) {
                onDailyTokenWithdrawal_CancelWithdrawaTokenAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("CustomerToDeposit")) {
            if("customerPhoto".equals(c.getName())) {
                onCustomerToDeposit_CustomerPhotoAction(c, event);
                return;
            }
            if("customerSignature".equals(c.getName())) {
                onCustomerToDeposit_CustomerSignatureAction(c, event);
                return;
            }
            if("okToDeposit".equals(c.getName())) {
                onCustomerToDeposit_OkToDepositAction(c, event);
                return;
            }
            if("cancelDeposit".equals(c.getName())) {
                onCustomerToDeposit_CancelDepositAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Settings")) {
            if("appSettings".equals(c.getName())) {
                onSettings_AppSettingsAction(c, event);
                return;
            }
            if("adminSettings".equals(c.getName())) {
                onSettings_AdminSettingsAction(c, event);
                return;
            }
            if("tranURLTextArea".equals(c.getName())) {
                onSettings_TranURLTextAreaAction(c, event);
                return;
            }
            if("appURLTextArea".equals(c.getName())) {
                onSettings_AppURLTextAreaAction(c, event);
                return;
            }
            if("bearerKeyTextArea".equals(c.getName())) {
                onSettings_BearerKeyTextAreaAction(c, event);
                return;
            }
            if("saveAppSettings".equals(c.getName())) {
                onSettings_SaveAppSettingsAction(c, event);
                return;
            }
            if("cancelAppSettings".equals(c.getName())) {
                onSettings_CancelAppSettingsAction(c, event);
                return;
            }
            if("createAdmin".equals(c.getName())) {
                onSettings_CreateAdminAction(c, event);
                return;
            }
            if("configureOfflineTeller".equals(c.getName())) {
                onSettings_ConfigureOfflineTellerAction(c, event);
                return;
            }
            if("changeOfflineTeller".equals(c.getName())) {
                onSettings_ChangeOfflineTellerAction(c, event);
                return;
            }
            if("backToMain".equals(c.getName())) {
                onSettings_BackToMainAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("WithdrawalMFBs")) {
            if("withdrawaBankMultiList".equals(c.getName())) {
                onWithdrawalMFBs_WithdrawaBankMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("EachMiniStat")) {
            if("miniStatBranchAddress".equals(c.getName())) {
                onEachMiniStat_MiniStatBranchAddressAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("MiniStatDisplay")) {
            if("miniStatMultiList".equals(c.getName())) {
                onMiniStatDisplay_MiniStatMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("OfflineDepositForm")) {
            if("offlineAmountTextField".equals(c.getName())) {
                onOfflineDepositForm_OfflineAmountTextFieldAction(c, event);
                return;
            }
            if("offlineNarrationTextArea".equals(c.getName())) {
                onOfflineDepositForm_OfflineNarrationTextAreaAction(c, event);
                return;
            }
            if("offlineTellerNumberTextField".equals(c.getName())) {
                onOfflineDepositForm_OfflineTellerNumberTextFieldAction(c, event);
                return;
            }
            if("depositToPhone".equals(c.getName())) {
                onOfflineDepositForm_DepositToPhoneAction(c, event);
                return;
            }
            if("cancel".equals(c.getName())) {
                onOfflineDepositForm_CancelAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("MiniStatForm")) {
            if("miniStatAcctNumberTextField".equals(c.getName())) {
                onMiniStatForm_MiniStatAcctNumberTextFieldAction(c, event);
                return;
            }
            if("miniStatOK".equals(c.getName())) {
                onMiniStatForm_MiniStatOKAction(c, event);
                return;
            }
            if("cancel".equals(c.getName())) {
                onMiniStatForm_CancelAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("DepositMFBs")) {
            if("depositMFBsMultiList".equals(c.getName())) {
                onDepositMFBs_DepositMFBsMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("StoredCustomers")) {
            if("storedCustomersMultiList".equals(c.getName())) {
                onStoredCustomers_StoredCustomersMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("DailyTokenDeposit")) {
            if("TextArea".equals(c.getName())) {
                onDailyTokenDeposit_TextAreaAction(c, event);
                return;
            }
            if("tranPWDDepositTextField".equals(c.getName())) {
                onDailyTokenDeposit_TranPWDDepositTextFieldAction(c, event);
                return;
            }
            if("checkToken".equals(c.getName())) {
                onDailyTokenDeposit_CheckTokenAction(c, event);
                return;
            }
            if("cancelCheckToken".equals(c.getName())) {
                onDailyTokenDeposit_CancelCheckTokenAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("TransactionMenu")) {
            if("depositOnline".equals(c.getName())) {
                onTransactionMenu_DepositOnlineAction(c, event);
                return;
            }
            if("withdrawOnline".equals(c.getName())) {
                onTransactionMenu_WithdrawOnlineAction(c, event);
                return;
            }
            if("balanceInquiry".equals(c.getName())) {
                onTransactionMenu_BalanceInquiryAction(c, event);
                return;
            }
            if("miniStatement".equals(c.getName())) {
                onTransactionMenu_MiniStatementAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("EachOfflineTransaction")) {
            if("uploadSingleTran".equals(c.getName())) {
                onEachOfflineTransaction_UploadSingleTranAction(c, event);
                return;
            }
            if("cancel".equals(c.getName())) {
                onEachOfflineTransaction_CancelAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("BalInquiryAcctNumber")) {
            if("balInquiryAcctNumberTextField".equals(c.getName())) {
                onBalInquiryAcctNumber_BalInquiryAcctNumberTextFieldAction(c, event);
                return;
            }
            if("verifyBalInquiry".equals(c.getName())) {
                onBalInquiryAcctNumber_VerifyBalInquiryAction(c, event);
                return;
            }
            if("cancelBalInquiry".equals(c.getName())) {
                onBalInquiryAcctNumber_CancelBalInquiryAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("SettingsLogin")) {
            if("adminNameTextField".equals(c.getName())) {
                onSettingsLogin_AdminNameTextFieldAction(c, event);
                return;
            }
            if("adminPasswordTextField".equals(c.getName())) {
                onSettingsLogin_AdminPasswordTextFieldAction(c, event);
                return;
            }
            if("adminLogin".equals(c.getName())) {
                onSettingsLogin_AdminLoginAction(c, event);
                return;
            }
            if("adminCancel".equals(c.getName())) {
                onSettingsLogin_AdminCancelAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("AdminCreateName")) {
            if("newAdminNameTextField".equals(c.getName())) {
                onAdminCreateName_NewAdminNameTextFieldAction(c, event);
                return;
            }
            if("newAdminPasswordTextField".equals(c.getName())) {
                onAdminCreateName_NewAdminPasswordTextFieldAction(c, event);
                return;
            }
            if("saveAdmin".equals(c.getName())) {
                onAdminCreateName_SaveAdminAction(c, event);
                return;
            }
            if("cancelAddAdmin".equals(c.getName())) {
                onAdminCreateName_CancelAddAdminAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("CustomerToWithdraw")) {
            if("customerPhoto".equals(c.getName())) {
                onCustomerToWithdraw_CustomerPhotoAction(c, event);
                return;
            }
            if("signature".equals(c.getName())) {
                onCustomerToWithdraw_SignatureAction(c, event);
                return;
            }
            if("continue".equals(c.getName())) {
                onCustomerToWithdraw_ContinueAction(c, event);
                return;
            }
            if("cancel".equals(c.getName())) {
                onCustomerToWithdraw_CancelAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("MiniStatMFBs")) {
            if("MiniStatMFBsMultiList".equals(c.getName())) {
                onMiniStatMFBs_MiniStatMFBsMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("OfflineMenu")) {
            if("pullAccounts".equals(c.getName())) {
                onOfflineMenu_PullAccountsAction(c, event);
                return;
            }
            if("viewOfflineTrans".equals(c.getName())) {
                onOfflineMenu_ViewOfflineTransAction(c, event);
                return;
            }
            if("offlineDeposit".equals(c.getName())) {
                onOfflineMenu_OfflineDepositAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("InstrumentForm")) {
            if("instrumentNumberField".equals(c.getName())) {
                onInstrumentForm_InstrumentNumberFieldAction(c, event);
                return;
            }
            if("tellerNumberTextField".equals(c.getName())) {
                onInstrumentForm_TellerNumberTextFieldAction(c, event);
                return;
            }
            if("withdraw".equals(c.getName())) {
                onInstrumentForm_WithdrawAction(c, event);
                return;
            }
            if("cancel".equals(c.getName())) {
                onInstrumentForm_CancelAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("SummaryOfDownload")) {
            if("accountsStoredList".equals(c.getName())) {
                onSummaryOfDownload_AccountsStoredListAction(c, event);
                return;
            }
            if("accountsWithErrorStatusList".equals(c.getName())) {
                onSummaryOfDownload_AccountsWithErrorStatusListAction(c, event);
                return;
            }
            if("accountsWithErrorReturnCodeList".equals(c.getName())) {
                onSummaryOfDownload_AccountsWithErrorReturnCodeListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("BalInquiryDisplay")) {
            if("customerPhoto".equals(c.getName())) {
                onBalInquiryDisplay_CustomerPhotoAction(c, event);
                return;
            }
            if("customerSignature".equals(c.getName())) {
                onBalInquiryDisplay_CustomerSignatureAction(c, event);
                return;
            }
            if("balInquiryOK".equals(c.getName())) {
                onBalInquiryDisplay_BalInquiryOKAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("StoredCustomer")) {
            if("customerPhoto".equals(c.getName())) {
                onStoredCustomer_CustomerPhotoAction(c, event);
                return;
            }
            if("customerSignature".equals(c.getName())) {
                onStoredCustomer_CustomerSignatureAction(c, event);
                return;
            }
            if("showDepositOffline".equals(c.getName())) {
                onStoredCustomer_ShowDepositOfflineAction(c, event);
                return;
            }
            if("cancel".equals(c.getName())) {
                onStoredCustomer_CancelAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Main")) {
            if("userLogin".equals(c.getName())) {
                onMain_UserLoginAction(c, event);
                return;
            }
            if("userGoOffline".equals(c.getName())) {
                onMain_UserGoOfflineAction(c, event);
                return;
            }
            if("createNewUser".equals(c.getName())) {
                onMain_CreateNewUserAction(c, event);
                return;
            }
            if("settings".equals(c.getName())) {
                onMain_SettingsAction(c, event);
                return;
            }
        }
    }

      protected void onAccountToPullInfo_FetchInfoAction(Component c, ActionEvent event) {
      }

      protected void onAccountToPullInfo_CancelFetchAction(Component c, ActionEvent event) {
      }

      protected void onAccountToPullInfo_AcctNumberFieldAction(Component c, ActionEvent event) {
      }

      protected void onAccountToPullInfo_AddAccountAction(Component c, ActionEvent event) {
      }

      protected void onAccountToPullInfo_AccountListAction(Component c, ActionEvent event) {
      }

      protected void onBalInquiryMFBs_BalInquiryMFBMultiListAction(Component c, ActionEvent event) {
      }

      protected void onAmountToDeposit_AmountToDepositFieldAction(Component c, ActionEvent event) {
      }

      protected void onAmountToDeposit_TranDescAreaAction(Component c, ActionEvent event) {
      }

      protected void onAmountToDeposit_TellerNumberFieldAction(Component c, ActionEvent event) {
      }

      protected void onAmountToDeposit_SendAmountToDepositAction(Component c, ActionEvent event) {
      }

      protected void onAmountToDeposit_CancelAmountAction(Component c, ActionEvent event) {
      }

      protected void onDepositAcctNumber_AcctNumberTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onDepositAcctNumber_VerifyDepositAction(Component c, ActionEvent event) {
      }

      protected void onDepositAcctNumber_CancelDepositAction(Component c, ActionEvent event) {
      }

      protected void onAmountToWithdraw_AmountToWithdrawTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onAmountToWithdraw_WithdrawalNarrationTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onAmountToWithdraw_SendWithdrawAction(Component c, ActionEvent event) {
      }

      protected void onAmountToWithdraw_CancelWithdrawAction(Component c, ActionEvent event) {
      }

      protected void onSummaryOfUpload_TransactionsUploadedMultiListAction(Component c, ActionEvent event) {
      }

      protected void onSummaryOfUpload_TransactionsNotUploadedMultiListAction(Component c, ActionEvent event) {
      }

      protected void onNewAppUser_NewUserTellerIDTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onNewAppUser_NewUserPasswordTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onNewAppUser_NewUserEmailTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onNewAppUser_TranPasswordTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onNewAppUser_TellerTilTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onNewAppUser_SignUpUserAction(Component c, ActionEvent event) {
      }

      protected void onNewAppUser_CancelSignUpAction(Component c, ActionEvent event) {
      }

      protected void onWithdrawalAcctNumber_WithdrawalAcctNumberTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onWithdrawalAcctNumber_WithdrawalVerifyAction(Component c, ActionEvent event) {
      }

      protected void onWithdrawalAcctNumber_CancelWithdrawalAction(Component c, ActionEvent event) {
      }

      protected void onOfflineTransactionsDisplay_AllOfflineTransactionsMultiListAction(Component c, ActionEvent event) {
      }

      protected void onOfflineTransactionsDisplay_UploadAllTransactionsAction(Component c, ActionEvent event) {
      }

      protected void onOfflineLogin_OfflineUsernameTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onOfflineLogin_OfflinePasswordTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onOfflineLogin_LoginOfflineAction(Component c, ActionEvent event) {
      }

      protected void onOfflineLogin_CancelAction(Component c, ActionEvent event) {
      }

      protected void onOfflineTellerCreate_NewOfflineTellerTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onOfflineTellerCreate_NewOfflineTellerPWDTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onOfflineTellerCreate_MfbCodeTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onOfflineTellerCreate_AddOfflineTellerAction(Component c, ActionEvent event) {
      }

      protected void onOfflineTellerCreate_CancelOfflineTellerAction(Component c, ActionEvent event) {
      }

      protected void onOnlineLogin_UsernameTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onOnlineLogin_PasswordTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onOnlineLogin_LoginUserAction(Component c, ActionEvent event) {
      }

      protected void onOnlineLogin_CancelAction(Component c, ActionEvent event) {
      }

      protected void onDailyTokenWithdrawal_TextAreaAction(Component c, ActionEvent event) {
      }

      protected void onDailyTokenWithdrawal_WithdrawalTranPWDTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onDailyTokenWithdrawal_CheckTokenToWithdrawAction(Component c, ActionEvent event) {
      }

      protected void onDailyTokenWithdrawal_CancelWithdrawaTokenAction(Component c, ActionEvent event) {
      }

      protected void onCustomerToDeposit_CustomerPhotoAction(Component c, ActionEvent event) {
      }

      protected void onCustomerToDeposit_CustomerSignatureAction(Component c, ActionEvent event) {
      }

      protected void onCustomerToDeposit_OkToDepositAction(Component c, ActionEvent event) {
      }

      protected void onCustomerToDeposit_CancelDepositAction(Component c, ActionEvent event) {
      }

      protected void onSettings_AppSettingsAction(Component c, ActionEvent event) {
      }

      protected void onSettings_AdminSettingsAction(Component c, ActionEvent event) {
      }

      protected void onSettings_TranURLTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onSettings_AppURLTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onSettings_BearerKeyTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onSettings_SaveAppSettingsAction(Component c, ActionEvent event) {
      }

      protected void onSettings_CancelAppSettingsAction(Component c, ActionEvent event) {
      }

      protected void onSettings_CreateAdminAction(Component c, ActionEvent event) {
      }

      protected void onSettings_ConfigureOfflineTellerAction(Component c, ActionEvent event) {
      }

      protected void onSettings_ChangeOfflineTellerAction(Component c, ActionEvent event) {
      }

      protected void onSettings_BackToMainAction(Component c, ActionEvent event) {
      }

      protected void onWithdrawalMFBs_WithdrawaBankMultiListAction(Component c, ActionEvent event) {
      }

      protected void onEachMiniStat_MiniStatBranchAddressAction(Component c, ActionEvent event) {
      }

      protected void onMiniStatDisplay_MiniStatMultiListAction(Component c, ActionEvent event) {
      }

      protected void onOfflineDepositForm_OfflineAmountTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onOfflineDepositForm_OfflineNarrationTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onOfflineDepositForm_OfflineTellerNumberTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onOfflineDepositForm_DepositToPhoneAction(Component c, ActionEvent event) {
      }

      protected void onOfflineDepositForm_CancelAction(Component c, ActionEvent event) {
      }

      protected void onMiniStatForm_MiniStatAcctNumberTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onMiniStatForm_MiniStatOKAction(Component c, ActionEvent event) {
      }

      protected void onMiniStatForm_CancelAction(Component c, ActionEvent event) {
      }

      protected void onDepositMFBs_DepositMFBsMultiListAction(Component c, ActionEvent event) {
      }

      protected void onStoredCustomers_StoredCustomersMultiListAction(Component c, ActionEvent event) {
      }

      protected void onDailyTokenDeposit_TextAreaAction(Component c, ActionEvent event) {
      }

      protected void onDailyTokenDeposit_TranPWDDepositTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onDailyTokenDeposit_CheckTokenAction(Component c, ActionEvent event) {
      }

      protected void onDailyTokenDeposit_CancelCheckTokenAction(Component c, ActionEvent event) {
      }

      protected void onTransactionMenu_DepositOnlineAction(Component c, ActionEvent event) {
      }

      protected void onTransactionMenu_WithdrawOnlineAction(Component c, ActionEvent event) {
      }

      protected void onTransactionMenu_BalanceInquiryAction(Component c, ActionEvent event) {
      }

      protected void onTransactionMenu_MiniStatementAction(Component c, ActionEvent event) {
      }

      protected void onEachOfflineTransaction_UploadSingleTranAction(Component c, ActionEvent event) {
      }

      protected void onEachOfflineTransaction_CancelAction(Component c, ActionEvent event) {
      }

      protected void onBalInquiryAcctNumber_BalInquiryAcctNumberTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onBalInquiryAcctNumber_VerifyBalInquiryAction(Component c, ActionEvent event) {
      }

      protected void onBalInquiryAcctNumber_CancelBalInquiryAction(Component c, ActionEvent event) {
      }

      protected void onSettingsLogin_AdminNameTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onSettingsLogin_AdminPasswordTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onSettingsLogin_AdminLoginAction(Component c, ActionEvent event) {
      }

      protected void onSettingsLogin_AdminCancelAction(Component c, ActionEvent event) {
      }

      protected void onAdminCreateName_NewAdminNameTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onAdminCreateName_NewAdminPasswordTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onAdminCreateName_SaveAdminAction(Component c, ActionEvent event) {
      }

      protected void onAdminCreateName_CancelAddAdminAction(Component c, ActionEvent event) {
      }

      protected void onCustomerToWithdraw_CustomerPhotoAction(Component c, ActionEvent event) {
      }

      protected void onCustomerToWithdraw_SignatureAction(Component c, ActionEvent event) {
      }

      protected void onCustomerToWithdraw_ContinueAction(Component c, ActionEvent event) {
      }

      protected void onCustomerToWithdraw_CancelAction(Component c, ActionEvent event) {
      }

      protected void onMiniStatMFBs_MiniStatMFBsMultiListAction(Component c, ActionEvent event) {
      }

      protected void onOfflineMenu_PullAccountsAction(Component c, ActionEvent event) {
      }

      protected void onOfflineMenu_ViewOfflineTransAction(Component c, ActionEvent event) {
      }

      protected void onOfflineMenu_OfflineDepositAction(Component c, ActionEvent event) {
      }

      protected void onInstrumentForm_InstrumentNumberFieldAction(Component c, ActionEvent event) {
      }

      protected void onInstrumentForm_TellerNumberTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onInstrumentForm_WithdrawAction(Component c, ActionEvent event) {
      }

      protected void onInstrumentForm_CancelAction(Component c, ActionEvent event) {
      }

      protected void onSummaryOfDownload_AccountsStoredListAction(Component c, ActionEvent event) {
      }

      protected void onSummaryOfDownload_AccountsWithErrorStatusListAction(Component c, ActionEvent event) {
      }

      protected void onSummaryOfDownload_AccountsWithErrorReturnCodeListAction(Component c, ActionEvent event) {
      }

      protected void onBalInquiryDisplay_CustomerPhotoAction(Component c, ActionEvent event) {
      }

      protected void onBalInquiryDisplay_CustomerSignatureAction(Component c, ActionEvent event) {
      }

      protected void onBalInquiryDisplay_BalInquiryOKAction(Component c, ActionEvent event) {
      }

      protected void onStoredCustomer_CustomerPhotoAction(Component c, ActionEvent event) {
      }

      protected void onStoredCustomer_CustomerSignatureAction(Component c, ActionEvent event) {
      }

      protected void onStoredCustomer_ShowDepositOfflineAction(Component c, ActionEvent event) {
      }

      protected void onStoredCustomer_CancelAction(Component c, ActionEvent event) {
      }

      protected void onMain_UserLoginAction(Component c, ActionEvent event) {
      }

      protected void onMain_UserGoOfflineAction(Component c, ActionEvent event) {
      }

      protected void onMain_CreateNewUserAction(Component c, ActionEvent event) {
      }

      protected void onMain_SettingsAction(Component c, ActionEvent event) {
      }

}
