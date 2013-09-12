package com.expertedge.mtnsaas;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.animations.CommonTransitions;
import userclasses.StateMachine;

public class MobileTeller {

    private Form current;

    public void init(Object context) {
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }
        new StateMachine("/theme");
    }

    public void stop() {
        Form f = Display.getInstance().getCurrent();
        if(f instanceof Dialog){
            f.setTransitionOutAnimator(CommonTransitions.createEmpty());
            ((Dialog)f).dispose();
        }
        
        current = Display.getInstance().getCurrent();
        if (current instanceof Dialog) {
            //((Dialog) current).dispose();
            current = null;
        }

    }

    public void destroy() {
    }
}
