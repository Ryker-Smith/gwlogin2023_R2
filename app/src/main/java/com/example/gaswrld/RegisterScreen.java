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
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.CheckBox;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.Notifier;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterScreen extends Form implements HandlesEventDispatching {
    private
    Button buttonr;
    HorizontalArrangement padh;
    VerticalArrangement Main, padv, padv2, padv3;
    Label Label, datedata, errormsg, datenr;
    TextBox email, nme;
    TableArrangement Table;
    Slider date;
    PasswordTextBox pass;
    CheckBox box, boxl;
    Clock tim, tim2;
    Web authweb, authwebjr;
    JSONObject jsonCredentials = new JSONObject();
    Notifier PopUpAd;

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
        Table.Rows(17);

        padh = new HorizontalArrangement(Table);
        padh.Column(1);
        padh.Row(0);
        padh.HeightPercent(15);
        padh.WidthPercent(60);
        padh.BackgroundColor(7);

        padv = new VerticalArrangement(Table);
        padv.Column(0);
        padv.Row(5);
        padv.HeightPercent(5);
        padv.WidthPercent(20);
        padv.BackgroundColor(7);

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
        padv2.BackgroundColor(7);

        date = new Slider(Table);
        date.WidthPercent(50);
        date.ThumbEnabled(true);
        date.Column(1);
        date.Row(5);
        date.ColorLeft(COLOR_BLUE);
        date.ColorRight(COLOR_RED);
        date.MinValue(1943);
        date.MaxValue(2050);

        datenr = new Label(this);
        datenr.Visible(false);

        datedata = new Label(Table);
        datedata.Column(1);
        datedata.Row(6);
        datedata.TextColor(COLOR_LTGRAY);
        datedata.Text("I were born in:"+" ....");
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

        nme = new TextBox(Table);
        nme.Column(1);
        nme.Row(9);
        nme.FontSize(20);
        nme.TextColor(COLOR_LTGRAY);
        nme.Hint("My Name Here!");
        nme.TextAlignment(ALIGNMENT_CENTER);

        box = new CheckBox(Table);
        box.Column(1);
        box.Row(10);
        box.Text("I promise that the \ninfo provided is 100% \ntrue and accurate.");
        box.TextColor(COLOR_LTGRAY);
        box.FontSize(15);

        boxl = new CheckBox(Table);
        boxl.Column(1);
        boxl.Row(11);
        boxl.Text("I allow for marketting and update\n news to be sent to my email\n every day for the next 50 years");
        boxl.TextColor(COLOR_LTGRAY);
        boxl.FontSize(15);

        padv3 = new VerticalArrangement(Table);
        padv3.Column(1);
        padv3.Row(12);
        padv3.WidthPercent(10);
        padv3.HeightPercent(10);
        padv3.BackgroundColor(7);

        buttonr = new Button(Table);
        buttonr.Column(1);
        buttonr.Row(14);
        buttonr.Shape(BUTTON_SHAPE_ROUNDED);
        buttonr.BackgroundColor(COLOR_LTGRAY);
        buttonr.Text("Register!");
        buttonr.TextAlignment(ALIGNMENT_CENTER);
        buttonr.FontSize(25);

        errormsg = new Label(padv3);
        errormsg.Text("");
        errormsg.TextColor(COLOR_LTGRAY);

        tim = new Clock(this);
        tim.TimerEnabled(false);
        tim.TimerInterval(2000);

        tim2 = new Clock(this);
        tim2.TimerEnabled(false);
        tim2.TimerInterval(3000);

        authweb = new Web(this);
        authweb.Url(AppOptions.authenticationURL);
        authwebjr = new Web(this);
        authwebjr.Url(AppOptions.authenticationURL);

        PopUpAd = new Notifier(this);
        PopUpAd.BackgroundColor(Component.COLOR_DKGRAY);
        PopUpAd.TextColor(COLOR_LTGRAY);

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "ScreenStart");
        EventDispatcher.registerEventForDelegation(this, formName, "PositionChanged");
        EventDispatcher.registerEventForDelegation(this, formName, "Timer");
        EventDispatcher.registerEventForDelegation(this, formName, "GotText");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        System.err.print("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
        if (eventName.equals("BackPressed")) {
            //this would be a great place to do something useful
            return true;
        }
        else if (eventName.equals("PositionChanged")) {
            float x = date.ThumbPosition();
            int y = (int) x;
            datedata.Text(("I were born in:") + y);
            datenr.Text(String.valueOf(y));
        }
        else if (eventName.equals("Click")) {
            if (component.equals(buttonr)) {
                if (date.ThumbPosition() > 2004) {
                    tim.TimerEnabled(true);
                    errormsg.Text("Sorry, you are too young for this game!");
                }
                if (date.ThumbPosition() < 2004) {
                    if (email.Text().contains("@")) {
                        if (email.Text().length() > 8) {
                            if (pass.Text().length() > 6) {
                                errormsg.Text(UI_Responses.CHECKING);
                                if (BAP.isValidEmailAddress(email.Text())) {
                                    try {
                                        jsonCredentials.put("action", "validate");
                                        jsonCredentials.put("user", email.Text());
                                        System.err.print("Sending: " + jsonCredentials.toString());
                                        String msg = jsonCredentials.toString();
                                        authweb.PostText(msg);
                                        errormsg.Text("Details have been sent!\nPlease wait a moment...");
                                    }
                                    catch (Exception e) {
                                        return false;
                                    }
                                }
                                else {
                                    PopUpAd.ShowAlert(UI_Responses.REGISTER_INVALID_EMAIL);
                                }
                            }
                            else {
                                tim.TimerEnabled(true);
                                errormsg.Text("Enter a more secure password!");
                            }
                        }
                        else {
                            tim.TimerEnabled(true);
                            errormsg.Text("Your email address is too short!");
                        }
                    }
                    else {
                        tim.TimerEnabled(true);
                        errormsg.Text("Please enter a valid email!");
                    }
                }
                else {
                    tim.TimerEnabled(true);
                    errormsg.Text("Please enter a valid year of birth!");
                }
            }
        }
        else if (eventName.equals("GotText")) {
            if (component.equals(authweb)) {
                String status = params[1].toString();
                String textOfResponse = (String) params[3];
                if (textOfResponse.equals("")) {
                    textOfResponse = status;
                }
                if (status.equals("200")) {
                    try {
                        JSONObject parser = new JSONObject(textOfResponse);
                        if (parser.getString("status").equals("OK")) {
                            String result = parser.getString("user");
                            if (result.contentEquals("exists")) {
                                PopUpAd.ShowAlert(UI_Responses.REGISTER_USER_EXISTS);
                                errormsg.Text(UI_Responses.REGISTER);
                            }
                            else {
                                //can create user
                                try {
                                    jsonCredentials.put("action", "register");
                                    jsonCredentials.put("user", email.Text());
                                    jsonCredentials.put("password", pass.Text());
                                    jsonCredentials.put("fullname", nme.Text());
                                    jsonCredentials.put("yob", datenr.Text());
                                    System.err.print("Registering: " + jsonCredentials.toString());
                                    String msg = jsonCredentials.toString();
                                    errormsg.Text(UI_Responses.WAITING);
                                    authweb.PostText(msg);
                                }
                                catch (Exception e) {
                                    return false;
                                }
                            }
                        }
                        else {
                            errormsg.Text(parser.getString("status"));
                        }
                    }
                    catch (JSONException e) {
                        errormsg.Text("error connecting1 " + status);
                    }
                }
                else {
                    errormsg.Text("error connecting2 " + status);
                }
                return true;
            }
            else if (component.equals(authwebjr)) {
                String status = params[1].toString();
                String textOfResponse = (String) params[3];
                if (status.equals("200")) {
                    try {
                        JSONObject parser = new JSONObject(textOfResponse);
                        if (parser.getString("status").equals("OK")) {
                            String result = parser.getString("userid");
                            if (Integer.parseInt(result) > 0) {
                                PopUpAd.ShowAlert(UI_Responses.HAPPY_WOOHOO);
                                errormsg.Text(UI_Responses.SUCCESS);
                                tim2.TimerEnabled(true);
                                return true;
                            }
                        }
                    }
                    catch (JSONException e) {
                        return true;
                    }
                }
            }
        }
        else if (eventName.equals("Timer")) {
            if (component.equals(tim)) {
                tim.TimerEnabled(false);
                //finish();
                //startActivity(getIntent());
            }
            if (component.equals(tim2)) {
                tim2.TimerEnabled(false);
                switchForm("MainActivity");
            }
        }
        return false;
    }
}