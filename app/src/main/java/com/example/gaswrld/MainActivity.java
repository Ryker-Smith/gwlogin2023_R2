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
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.PasswordTextBox;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Form implements HandlesEventDispatching {
    private
    Button buttonl, buttonr;
    HorizontalArrangement padh, padh2;
    VerticalArrangement Main, Vert, padv, padv2, padv3;
    Label Label, statsus;
    TextBox email;
    PasswordTextBox pass;
    TableArrangement Table, table2;
    Web authweb;
    JSONObject sndstff = new JSONObject();
    Notifier PopUpAd;

    protected void $define() {
        this.Sizing("Responsive");
        this.ShowStatusBar(false);

        Main = new VerticalArrangement(this);
        Main.BackgroundColor(COLOR_PINK);
        Main.HeightPercent(100);
        Main.WidthPercent(100);
        Main.Image("background.png");

        Table = new TableArrangement(Main);
        Table.Columns(3);
        Table.Rows(4);

        padh = new HorizontalArrangement(Table);
        padh.Column(0);
        padh.Row(2);
        padh.WidthPercent(12);
        padh.HeightPercent(15);
        padh.BackgroundColor(7);

        padv = new VerticalArrangement(Table);
        padv.Column(1);
        padv.Row(1);
        padv.WidthPercent(76);
        padv.HeightPercent(12);
        padv.BackgroundColor(7);

        Vert = new VerticalArrangement(Table);
        Vert.Column(1);
        Vert.Row(2);
        Vert.BackgroundColor(7);
        Vert.HeightPercent(75);

        table2 = new TableArrangement(Vert);
        table2.Columns(3);
        table2.Rows(10);

        padv2 = new VerticalArrangement(table2);
        padv2.Column(1);
        padv2.Row(0);
        padv2.WidthPercent(2);
        padv2.HeightPercent(10);
        padv2.BackgroundColor(7);

        padh2 = new HorizontalArrangement(table2);
        padh2.Column(0);
        padh2.Row(2);
        padh2.WidthPercent(18);
        padh2.HeightPercent(5);
        padh2.BackgroundColor(7);

        Label = new Label(table2);
        Label.Text("Login");
        Label.TextColor(COLOR_LTGRAY);
        Label.FontSize(60);
        Label.Column(1);
        Label.Row(1);
        Label.TextAlignment(ALIGNMENT_CENTER);

        email = new TextBox(table2);
        email.Column(1);
        email.Row(3);
        email.Hint("Email here");
        email.TextColor(COLOR_LTGRAY);

        pass = new PasswordTextBox(table2);
        pass.Column(1);
        pass.Row(4);
        pass.Hint("Password here");
        pass.TextColor(COLOR_LTGRAY);

        buttonl = new Button(table2);
        buttonl.Column(1);
        buttonl.Row(5);
        buttonl.Text("log in");
        buttonl.BackgroundColor(Component.COLOR_DKGRAY);

        padv3 = new VerticalArrangement(table2);
        padv3.Column(1);
        padv3.Row(6);
        padv3.BackgroundColor(7);
        padv3.HeightPercent(14);
        padv3.WidthPercent(2);

        statsus = new Label(padv3);
        statsus.TextAlignment(ALIGNMENT_CENTER);
        statsus.TextColor(COLOR_LTGRAY);
        statsus.FontSize(26);

        Label = new Label(table2);
        Label.Column(1);
        Label.Row(7);
        Label.Text("Don't currently\nhave an account?");
        Label.TextAlignment(ALIGNMENT_CENTER);
        Label.TextColor(COLOR_LTGRAY);
        Label.FontSize(22);

        buttonr = new Button(table2);
        buttonr.Column(1);
        buttonr.Row(8);
        buttonr.Text("Register");
        buttonr.Shape(BUTTON_SHAPE_ROUNDED);
        buttonr.BackgroundColor(Component.COLOR_DKGRAY);

        authweb = new Web(this);
        authweb.Url(AppOptions.authenticationURL);

        PopUpAd = new Notifier(this);
        PopUpAd.BackgroundColor(Component.COLOR_DKGRAY);
        PopUpAd.TextColor(COLOR_LTGRAY);

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "ScreenStart");
        EventDispatcher.registerEventForDelegation(this, formName, "GotText");

    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        System.err.print("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
        if (eventName.equals("BackPressed")) {
            // this would be a great place to do something useful
            return true;
        }
        else if (eventName.equals("Click")) {
            if (component.equals(buttonl)) {
                if (email.Text().length() < 4 ) {
                    PopUpAd.ShowChooseDialog("Enter valid email", "Alert", "Ok", "", false);
                    return true;
                }
                if (pass.Text().length() < 5 ) {
                    PopUpAd.ShowChooseDialog("Enter correct password", "Alert", "Ok", "", false);
                }
                else {
                    statsus.Text(UI_Responses.CONNECTION_SENDING);
                    //buttonl.Enabled(false);
                    System.err.print("You pressed a button");
                    try {
                        sndstff.put("action", "login");
                        sndstff.put("user", email.Text());
                        sndstff.put("password", pass.Text());
                        String msg = sndstff.toString();
                        authweb.PostText(msg);
                        email.HideKeyboard();
                    } catch (Exception e) {
                        return false;
                    }
                }
                return true;
            }
        }
        if (component.equals(buttonr)) {
            switchForm("RegisterScreen");
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
                        if (parser.getString("status").equals("error")) {
                            statsus.Text(parser.getString("detail"));
                            buttonl.Enabled(true);
                            if (parser.getString("detail").equals("bad login")) {
                                statsus.Text("Incorrect Password Entered");
                            }
                            if (parser.getString("detail").equals("unknown")) {
                                statsus.Text("Incorrect Email Entered");
                            }
                        }
                        else {
                            //String token = parser.getString("token");
                            statsus.Text("Login Successful!");
                            switchForm("GameScreen");
                        }
                    }
                    catch (JSONException e) {
                        statsus.Text("error e177 processing response");
                        buttonl.Enabled(true);
                    }
                }
                else {
                    statsus.Text("This account dosent exit!");
                    System.err.println("error e182 processing response" + status);
                    buttonl.Enabled(true);
                }
            }
        }
        return false;
    }
}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//