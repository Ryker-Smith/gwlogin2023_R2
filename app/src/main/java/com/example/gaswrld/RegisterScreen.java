package com.example.gaswrld;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.DatePicker;
//import com.google.appinventor.components.runtime.Slider;
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.CheckBox;

public class RegisterScreen extends Form implements HandlesEventDispatching {
    private
    Button buttonr;
    HorizontalArrangement padh, padh2;
    VerticalArrangement Main, padv, padv2, padv3;
    Label Label, datedata, errormsg;
    TextBox email;
    TableArrangement Table;
    Slider date;
    PasswordTextBox pass;
    CheckBox box, boxl;
    Clock tim;

    protected void $define() {
        this.Sizing("Responsive");
        this.ShowStatusBar(false);

        Main = new VerticalArrangement(this);
        Main.BackgroundColor(COLOR_PINK);
        Main.HeightPercent(100);
        Main.WidthPercent(100);
        Main.Image("register.png");

        Table = new TableArrangement(Main);
        Table.Columns(3);
        Table.Rows(15);

        padh = new HorizontalArrangement(Table);
        padh.Column(1);
        padh.Row(0);
        padh.HeightPercent(15);
        padh.WidthPercent(60);
        padh.BackgroundColor(COLOR_GREEN);

        padv = new VerticalArrangement(Table);
        padv.Column(0);
        padv.Row(5);
        padv.HeightPercent(5);
        padv.WidthPercent(20);
        padv.BackgroundColor(COLOR_ORANGE);

        Label = new Label(Table);
        Label.Column(1);
        Label.Row(1);
        Label.Text("Register");
        Label.TextAlignment(ALIGNMENT_CENTER);
        Label.FontSize(45);
        Label.TextColor(COLOR_LTGRAY);

        padv2 = new VerticalArrangement(Table);
        padv2.Column(1);
        padv2.Row(3);
        padv2.WidthPercent(10);
        padv2.HeightPercent(10);
        padv2.BackgroundColor(COLOR_MAGENTA);

        date = new Slider(Table);
        date.WidthPercent(50);
        date.ThumbEnabled(true);
        date.ThumbPosition(2000);
        date.Column(1);
        date.Row(5);
        date.ColorLeft(COLOR_BLUE);
        date.ColorRight(COLOR_RED);
        date.MinValue(1943);
        date.MaxValue(2050);

        datedata = new Label(Table);
        datedata.Column(1);
        datedata.Row(6);
        datedata.TextColor(COLOR_LTGRAY);
        datedata.Text("I were born in:....");
        datedata.FontSize(28);

        email = new TextBox(Table);
        email.Column(1);
        email.Row(7);
        email.FontSize(20);
        email.TextColor(COLOR_LTGRAY);
        email.Hint("My Email Here!");
        email.TextAlignment(ALIGNMENT_CENTER);

        pass = new PasswordTextBox(Table);
        pass.Column(1);
        pass.Row(8);
        pass.FontSize(20);
        pass.TextColor(COLOR_LTGRAY);
        pass.Hint("My Password Here!");
        pass.TextAlignment(ALIGNMENT_CENTER);


        box = new CheckBox(Table);
        box.Column(1);
        box.Row(9);
        box.Text("I promise that the \ninfo provided is 100% \ntrue and accurate.");
        box.TextColor(COLOR_LTGRAY);
        box.FontSize(15);

        boxl = new CheckBox(Table);
        boxl.Column(1);
        boxl.Row(10);
        boxl.Text("I allow for marketting and update\n news to be sent to my email\n every day for the next 50 years");
        boxl.TextColor(COLOR_LTGRAY);
        boxl.FontSize(15);

        padv3 = new VerticalArrangement(Table);
        padv3.Column(1);
        padv3.Row(12);
        padv3.WidthPercent(10);
        padv3.HeightPercent(10);
        padv3.BackgroundColor(Component.COLOR_CYAN);

        buttonr = new Button(Table);
        buttonr.Column(1);
        buttonr.Row(13);
        buttonr.Shape(BUTTON_SHAPE_ROUNDED);
        buttonr.BackgroundColor(COLOR_LTGRAY);
        buttonr.Text("Register!");
        buttonr.TextAlignment(ALIGNMENT_CENTER);
        buttonr.FontSize(25);

        errormsg = new Label(padv3);
        errormsg.Text("");

        tim = new Clock(this);
        tim.TimerEnabled(false);
        tim.TimerInterval(2000);

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "ScreenStart");
        EventDispatcher.registerEventForDelegation(this, formName, "PositionChanged");
        EventDispatcher.registerEventForDelegation(this, formName, "Timer");
    }
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        System.err.print("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
        if (eventName.equals("BackPressed")) {
            // this would be a great place to do something useful
            return true;
        }
        else if (eventName.equals("PositionChanged")) {
            if (component.equals(date));
            float x = date.ThumbPosition();
            int y = (int) x;
            datedata.Text(("I were born in:") + String.valueOf(y));
        }
        else if (eventName.equals("Click")) {
            if (component.equals(buttonr)) {
                 if (date.ThumbPosition() > 2004) {
                    tim.TimerEnabled(true);
                    errormsg.Text("Sorry, you are too young for this game!");
                 }
                else if (date.ThumbPosition() > 2023) {
                     tim.TimerEnabled(true);
                     errormsg.Text("You havent even been born yet!\n No way you can play this game.");
                }
                else if (date.ThumbPosition() < 2004) {
                    if (email.Text().equals("")) {
                        if (pass.Text().equals("")) {
                            if (component.equals(box.Checked(true))){
                                tim.TimerEnabled(true);
                                errormsg.Text("Your account has been registered,\n you will receive a \nconfirmation email shortly!.");

                            }

                        }

                    }

                 }

            }
        }
        else if (eventName.equals("Timer")) {
            if (component.equals(tim));
            tim.TimerEnabled(false);
            finish();
            startActivity(getIntent());
        }
        return false;
    }
}