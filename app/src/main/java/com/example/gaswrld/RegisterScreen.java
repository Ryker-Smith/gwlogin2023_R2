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
import com.google.appinventor.components.runtime.DatePicker;
//import com.google.appinventor.components.runtime.Slider;

public class RegisterScreen extends Form implements HandlesEventDispatching {
    private
    Button buttonr;
    HorizontalArrangement padh, padh2;
    VerticalArrangement Main, padv, padv2, padv3;
    Label Label, datedata;
    TextBox email, pass, datey, datem, dated;
    TableArrangement Table;
    Slider date;

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
        Table.Rows(10);

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
        date.ThumbPosition(2023);
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

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "ScreenStart");
        EventDispatcher.registerEventForDelegation(this, formName, "PositionChanged");
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
        return false;
    }
}