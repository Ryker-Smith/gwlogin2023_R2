package com.example.gaswrld;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.Label;


public class GameScreen extends Form implements HandlesEventDispatching {
    private
    Button up, down, left, right, eat, berserk, bag, sleep, wake, explode, muv;
    HorizontalArrangement padh;
    VerticalArrangement Main, tableback;
    GrassViewer GrassViewer;
    TableArrangement Table;
    String doitagain_key;
    Clock doitagain;
    Label sleepwakeCheck;
    Notifier PopUpAd;


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

        tableback = new VerticalArrangement(Main);
        tableback.Image("controlsbkgnd.png");
        tableback.WidthPercent(100);
        tableback.HeightPercent(Component.LENGTH_FILL_PARENT);

        Table = new TableArrangement(tableback);
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

        sleep = new Button(Table);
        sleep.Shape(BUTTON_SHAPE_OVAL);
        sleep.Column(2);
        sleep.Row(1);
        sleep.Width(60);
        sleep.Height(60);
        sleep.Text("\uD83D\uDE2A");
        sleep.TextAlignment(ALIGNMENT_CENTER);
        sleep.FontSize(30);

        wake = new Button(Table);
        wake.Shape(BUTTON_SHAPE_OVAL);
        wake.Column(2);
        wake.Row(3);
        wake.Width(60);
        wake.Height(60);
        wake.Text("\uD83D\uDE2B");
        wake.TextAlignment(ALIGNMENT_CENTER);
        wake.FontSize(30);

        explode = new Button(Table);
        explode.Shape(BUTTON_SHAPE_OVAL);
        explode.Column(1);
        explode.Row(3);
        explode.Width(60);
        explode.Height(60);
        explode.Text("\uD83E\uDDE8");
        explode.TextAlignment(ALIGNMENT_CENTER);
        explode.FontSize(30);

        muv = new Button(Table);
        muv.Shape(BUTTON_SHAPE_OVAL);
        muv.Column(3);
        muv.Row(1);
        muv.Width(60);
        muv.Height(60);
        muv.Text("\uD83C\uDFC3");
        muv.TextAlignment(ALIGNMENT_CENTER);
        muv.FontSize(30);

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

        PopUpAd = new Notifier(this);
        PopUpAd.BackgroundColor(Component.COLOR_DKGRAY);
        PopUpAd.TextColor(COLOR_LTGRAY);

        EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "WebViewStringChange");
        EventDispatcher.registerEventForDelegation(this, formName, "thingUpdate");
        EventDispatcher.registerEventForDelegation(this, formName, "Timer");
        EventDispatcher.registerEventForDelegation(this, formName, "wvq_fromGame");
        EventDispatcher.registerEventForDelegation(this, formName, "wvq_fromGame_clear");
        /// for the movement keys
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
                System.err.print("key_omnomnom");
                doitagain_key="key_E";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(berserk)) {
                System.err.print("key_ANGY");
                doitagain_key="key_G";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(muv)) {
                System.err.print("key_muv");
                doitagain_key="key_M";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(sleep)) {
                System.err.print("key_sleep");
                doitagain_key="key_S";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(wake)) {
                System.err.print("key_wake");
                doitagain_key="key_W";
                GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                doitagain.TimerEnabled(true);
                return true;
            }
            else if (component.equals(bag)) {
                PopUpAd.ShowAlert("dis don't work yet, be patient!");
                System.err.print("key_bag");
                //doitagain_key="key_M";
                //GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                //doitagain.TimerEnabled(true);
                //return true;
            }
            else if (component.equals(explode)) {
                PopUpAd.ShowAlert("KHAAAAA-BEEEEWM");
                System.err.print("key_BOOMBA");
                //doitagain_key="key_M";
                //GrassViewer.toGame(GrassViewer.as_JSON(new String[] {"type","key","keyCode",doitagain_key}));
                //doitagain.TimerEnabled(true);
                //return true;
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
//hellu :>