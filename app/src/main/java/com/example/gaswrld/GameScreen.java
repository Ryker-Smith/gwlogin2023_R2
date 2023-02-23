package com.example.gaswrld;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.WebViewer;

public class GameScreen extends Form implements HandlesEventDispatching {
    private
    Button up, down, left, right, eat, berserk, bag;
    HorizontalArrangement padh, padh2;
    VerticalArrangement Main;
    TableArrangement Table;
    WebViewer Gamescreen;

    protected void $define() {
        this.Sizing("Responsive");
        this.ShowStatusBar(false);

        Main = new VerticalArrangement(this);
        Main.BackgroundColor(COLOR_PINK);
        Main.HeightPercent(100);
        Main.WidthPercent(100);

        Gamescreen = new WebViewer(Main);
        Gamescreen.WidthPercent(100);
        Gamescreen.HeightPercent(75);
        Gamescreen.GoToUrl("https://grassworld.fachtnaroe.net");

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

        padh = new HorizontalArrangement(Table);
        padh.Column(0);
        padh.Row(2);
        padh.Width(10);

    }
}