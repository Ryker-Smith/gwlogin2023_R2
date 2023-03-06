package com.example.gaswrld;

import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Image;

public class SplashScreen extends Form implements HandlesEventDispatching {

    Clock timerNextScreen;
    VerticalArrangement SplashScreen;
    int thisIsA_Bad_Idea_SettingTheLogoSizeLikeThis = 320;
    int another_Bad_Idea_SettingTheTimerThisWay = 500;

    protected void $define() {
        this.Sizing("Responsive");
        this.ShowStatusBar(false);

        SplashScreen = new VerticalArrangement(this);
        SplashScreen.HeightPercent(100);
        SplashScreen.WidthPercent(100);
        SplashScreen.Image("spacesplash.png");

        timerNextScreen = new Clock(SplashScreen);
        timerNextScreen.TimerEnabled(false);
        timerNextScreen.TimerInterval(another_Bad_Idea_SettingTheTimerThisWay);
        timerNextScreen.TimerEnabled(true);

        EventDispatcher.registerEventForDelegation(this, formName, "Timer");
        EventDispatcher.registerEventForDelegation(this, formName, "ScreenStart");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (eventName.equals("ScreenStart")) {
            timerNextScreen.TimerEnabled(true);
            timerNextScreen.TimerInterval(500);
            return true;
        } else if (eventName.equals("Timer")) {
            if (component.equals(timerNextScreen)) {
                timerNextScreen.TimerEnabled(false);
                switchForm("MainActivity");

            }

        }
        return false;
    }
}