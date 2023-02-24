package com.example.gaswrld;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TableArrangement;

public class RegisterScreen extends Form implements HandlesEventDispatching {
    private
    Button buttonr;
    HorizontalArrangement padh, padh2;
    VerticalArrangement Main, Vert, padv, padv2, padv3;
    Label Label;
    TextBox email, pass;
    TableArrangement Table;

    protected void $define() {
        this.Sizing("Responsive");
        this.ShowStatusBar(false);

        Main = new VerticalArrangement(this);
        Main.BackgroundColor(COLOR_PINK);
        Main.HeightPercent(100);
        Main.WidthPercent(100);
        Main.Image("background.png");

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "ScreenStart");
    }
}