package com.example.gaswrld;

import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.Clock;

public class ErrorScreen extends Form implements HandlesEventDispatching {
    VerticalArrangement Main;
    Label Label;
    TableArrangement Table;
    Clock Clock;

    protected void $define() {
        this.Sizing("Responsive");
        this.ShowStatusBar(false);

        Main = new VerticalArrangement(this);
        Main.WidthPercent(100);
        Main.HeightPercent(100);
        Main.Image("error.png");

        Table = new TableArrangement(Main);
        Table.Rows(5);
        Table.Columns(3);
        Table.HeightPercent(LENGTH_FILL_PARENT);
        Table.WidthPercent(LENGTH_FILL_PARENT);

        HorizontalArrangement padh = new HorizontalArrangement(Table);
        padh.Row(0);
        padh.Column(1);
        padh.HeightPercent(55);
        padh.WidthPercent(10);
        padh.BackgroundColor(COLOR_BLUE);

        HorizontalArrangement padh2 = new HorizontalArrangement(Table);
        padh2.Row(3);
        padh2.Column(0);
        padh2.HeightPercent(10);
        padh2.WidthPercent(28);
        padh2.BackgroundColor(Component.COLOR_RED);

        Label = new Label(Table);
        Label.Row(3);
        Label.Column(1);
        Label.TextAlignment(ALIGNMENT_CENTER);
        Label.FontSize(20);
        Label.TextColor(COLOR_LTGRAY);

        Clock = new Clock(this);
        Clock.TimerEnabled(false);
        Clock.TimerInterval(8000);
        Clock.TimerEnabled(true);

        EventDispatcher.registerEventForDelegation(this, formName, "Timer");
        EventDispatcher.registerEventForDelegation(this, formName, "ScreenStart");
        EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");

    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        System.err.print("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
        switch (eventName) {
            case "BackPressed":
                getStartValue();
                // this would be a great place to do something useful

                return true;
            case "ScreenStart":
                if (getStartValue().equals(19)) {
                    Label.Text("The register function does not yet work, sorry for the inconvenience!");
                }

                break;
            case "Timer":
                if (component.equals(Clock)) {

                    switchForm("MainActivity");
                    Clock.TimerEnabled(false);
                }
                break;
        }
        return false;
    }
}