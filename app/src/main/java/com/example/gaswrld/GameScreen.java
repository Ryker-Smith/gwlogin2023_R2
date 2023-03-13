package com.example.gaswrld;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.WebViewer;
import com.google.appinventor.components.runtime.Label;

public class GameScreen extends Form implements HandlesEventDispatching {
    private
    Button up, down, left, right, eat, berserk, bag, sleepwake;
    HorizontalArrangement padh;
    VerticalArrangement Main;
    GrassViewer GrassViewer;
    TableArrangement Table;
    WebViewer Gamescreen;
    String token, doitagain_key;
    Clock doitagain;
    Label sleepwakeCheck;


    protected void $define() {
        this.Sizing("Responsive");
        this.ShowStatusBar(false);

        Main = new VerticalArrangement(this);
        Main.BackgroundColor(COLOR_PINK);
        Main.HeightPercent(100);
        Main.WidthPercent(100);

        GrassViewer = new GrassViewer(Main);
        GrassViewer.WidthPercent(100);
        GrassViewer.HeightPercent(75);
        GrassViewer.GoToUrl("https://grassworld.fachtnaroe.net");

        Table = new TableArrangement(Main);
        Table.Rows(5);
        Table.Columns(8);
        Table.WidthPercent(100);
        Table.HeightPercent(Component.LENGTH_FILL_PARENT);
        //here be controls
        padh = new HorizontalArrangement(Table);
        padh.Row(0);
        padh.Column(4);
        padh.Height(10);
        padh.Width(1);

        up = new Button(Table);
        up.Shape(BUTTON_SHAPE_OVAL);
        up.Column(6);
        up.Row(1);
        up.Width(60);
        up.Height(60);
        up.Text("↑");
        up.TextColor(COLOR_BLACK);
        up.TextAlignment(ALIGNMENT_CENTER);
        up.FontSize(30);

        down = new Button(Table);
        down.Shape(BUTTON_SHAPE_OVAL);
        down.Column(6);
        down.Row(3);
        down.Width(60);
        down.Height(60);
        down.Text("↓");
        down.TextColor(COLOR_BLACK);
        down.TextAlignment(ALIGNMENT_CENTER);
        down.FontSize(30);

        left = new Button(Table);
        left.Shape(BUTTON_SHAPE_OVAL);
        left.Column(5);
        left.Row(2);
        left.Width(60);
        left.Height(60);
        left.Text("←");
        left.TextColor(COLOR_BLACK);
        left.TextAlignment(ALIGNMENT_CENTER);
        left.FontSize(30);

        right = new Button(Table);
        right.Shape(BUTTON_SHAPE_OVAL);
        right.Column(7);
        right.Row(2);
        right.Width(60);
        right.Height(60);
        right.Text("→");
        right.TextColor(COLOR_BLACK);
        right.TextAlignment(ALIGNMENT_CENTER);
        right.FontSize(30);

        padh = new HorizontalArrangement(Table);
        padh.Row(4);
        padh.Column(4);
        padh.Height(10);
        padh.Width(1);

        padh = new HorizontalArrangement(Table);
        padh.Column(4);
        padh.Row(2);
        padh.Width(35);
        padh.Height(60);

        eat = new Button(Table);
        eat.Shape(BUTTON_SHAPE_OVAL);
        eat.Column(1);
        eat.Row(1);
        eat.Width(60);
        eat.Height(60);
        eat.Text("\uD83C\uDF74");
        eat.TextAlignment(ALIGNMENT_CENTER);
        eat.FontSize(30);

        berserk = new Button(Table);
        berserk.Shape(BUTTON_SHAPE_OVAL);
        berserk.Column(2);
        berserk.Row(2);
        berserk.Width(60);
        berserk.Height(60);
        berserk.Text("\uD83D\uDCA2");
        berserk.TextAlignment(ALIGNMENT_CENTER);
        berserk.FontSize(30);

        bag = new Button(Table);
        bag.Shape(BUTTON_SHAPE_OVAL);
        bag.Column(3);
        bag.Row(3);
        bag.Width(60);
        bag.Height(60);
        bag.Text("\uD83D\uDCBC");
        bag.TextAlignment(ALIGNMENT_CENTER);
        bag.FontSize(30);

        sleepwake = new Button(Table);
        sleepwake.Shape(BUTTON_SHAPE_OVAL);
        sleepwake.Column(2);
        sleepwake.Row(1);
        sleepwake.Width(60);
        sleepwake.Height(60);
        sleepwake.Text("\uD83D\uDE2A");
        sleepwake.TextAlignment(ALIGNMENT_CENTER);
        sleepwake.FontSize(30);

        sleepwakeCheck = new Label(Main);
        sleepwakeCheck.Visible(false);
        sleepwakeCheck.Text("\uD83D\uDE2A");

        padh = new HorizontalArrangement(Table);
        padh.Column(0);
        padh.Row(2);
        padh.Width(10);

        GrassViewer.ClearCaches();

        doitagain = new Clock(Main);
        doitagain.TimerInterval(AppOptions.doitagainTicker_interval);
        doitagain.TimerEnabled(false);

        EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "WebViewStringChange");
        EventDispatcher.registerEventForDelegation(this, formName, "thingUpdate");
        EventDispatcher.registerEventForDelegation(this, formName, "Timer");
        EventDispatcher.registerEventForDelegation(this, formName, "wvq_fromGame");
        EventDispatcher.registerEventForDelegation(this, formName, "wvq_fromGame_clear");
        // for the movement keys
        EventDispatcher.registerEventForDelegation(this, formName, "TouchDown");
        EventDispatcher.registerEventForDelegation(this, formName, "TouchUp");

    }
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

        System.err.print("dispatchEvent: [" + formName + "] [" + component.toString() + "] [" + componentName + "] " + eventName);
        if (eventName.equals("BackPressed")) {
            GrassViewer.GoBack();
            return true;
        }
        else if (eventName.equals("WebViewStringChange")) {
            String msg = GrassViewer.WebViewString();
            System.err.print("WVS: "+msg);
            return true;
        }
        else if (eventName.equals("thingUpdate")) {
            String s=GrassViewer.get_thingUpdates();
            System.err.print("Updates: "+s);
            return true;
        }
        else if (eventName.equals("wvq_fromGame")) {
            String s=GrassViewer.fromGame();
            System.err.print("From game: "+s);
            return true;
        }
        else if (eventName.equals("wvq_fromGame_clear")) {
            System.err.print("Clear from game: ");
            return true;
        }
        else if (eventName.equals("TouchDown")) {
            if (component.equals(left)) {
                System.err.print("key_left");
                doitagain_key="key_left";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(down)) {
                System.err.print("key_down");
                doitagain_key="key_down";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(up)) {
                System.err.print("key_up");
                doitagain_key="key_up";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(right)) {
                System.err.print("key_right");
                doitagain_key="key_right";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(eat)) {
                System.err.print("key_right");
                doitagain_key="key_right";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(berserk)) {
                System.err.print("key_right");
                doitagain_key="key_M";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(bag)) {
                System.err.print("key_right");
                doitagain_key="key_right";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(sleepwake)) {
                System.err.print("key_sleepwake");
                doitagain_key="key_S";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
        }
        else if (eventName.equals("TouchUp")) {
            doitagain.TimerEnabled(false);
            return true;
        }
        else if (eventName.equals("Timer")) {
            if (component.equals(doitagain)) {
                doitagain.TimerEnabled(false);
                String msg=GrassViewer.as_JSON(new String[] {"type","key","keyCode", doitagain_key});
                System.err.print(msg);
                GrassViewer.toGame(msg);
                doitagain.TimerEnabled(true);
                return true;
            }
        }
        return false;
    }
}
//        else if (eventName.equals("Click")) {
//            if (component.equals(eat)) {
//                System.err.print("om nom nom");
//                //switchFormWithStartValue("AccountAdminScreen",token);
//                //return true;
//            }
//            else if (component.equals(berserk)) {
//                System.err.print("*heckin angy noises*");
//                //GrassViewer.GoHome();
//                // invert te timer status
//                //return true;
//            }
//            else if (component.equals(bag)) {
//                System.err.print("bag");
//                //GrassViewer.toGame(
//                        //GrassViewer.as_JSON(new String[] {"type","key","keyCode","key_M"})
//                //);
//                //return true;
//            }
//
//        }