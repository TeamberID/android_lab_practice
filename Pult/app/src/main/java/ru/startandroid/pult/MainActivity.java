package ru.startandroid.pult;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String NOTH = "";
        super.onCreate(savedInstanceState);
        final String[] txt = {NOTH};
        LinearLayout ll = new LinearLayout(this);
        TableLayout tableLayout = new TableLayout(this);
        final TextView tv = new TextView(this);
        tv.setTextSize(20);

        ll.addView(tableLayout);
        ll.addView(tv);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TableRow tr0 = new TableRow(this);
        TableRow tr1 = new TableRow(this);
        TableRow tr2 = new TableRow(this);
        TableRow tr3 = new TableRow(this);
        TableRow tr4 = new TableRow(this);
        tableLayout.addView(tr0);
        tableLayout.addView(tr1);
        tableLayout.addView(tr2);
        tableLayout.addView(tr3);
        tableLayout.addView(tr4);

        tv.setText("hello");

        Button b1 = new Button(this);
        b1.setText("1");
        tr1.addView(b1);
        Button b2 = new Button(this);
        b2.setText("2");
        tr1.addView(b2);
        Button b3 = new Button(this);
        b3.setText("3");
        tr1.addView(b3);

        Button b4 = new Button(this);
        b4.setText("4");
        tr2.addView(b4);
        Button b5 = new Button(this);
        b5.setText("5");
        tr2.addView(b5);
        Button b6 = new Button(this);
        b6.setText("6");
        tr2.addView(b6);


        Button b7 = new Button(this);
        b7.setText("7");
        tr3.addView(b7);
        Button b8 = new Button(this);
        b8.setText("8");
        tr3.addView(b8);
        Button b9 = new Button(this);
        b9.setText("9");
        tr3.addView(b9);
        setContentView(ll, layoutParams);

        Button enter = new Button(this);
        enter.setText("ENTER");
        Button clear = new Button(this);
        clear.setText("CLEAR");
        tr4.addView(enter);
        tr4.addView(clear);

        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                txt[0] = txt[0] + b.getText();
                tv.setText(txt[0]);
            }
        };

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), txt[0], Toast.LENGTH_LONG).show();
                txt[0] = NOTH;
                tv.setText(txt[0]);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt[0] = NOTH;
                tv.setText(txt[0]);
            }
        });
        b1.setOnClickListener(cl);
        b2.setOnClickListener(cl);
        b3.setOnClickListener(cl);
        b4.setOnClickListener(cl);
        b5.setOnClickListener(cl);
        b6.setOnClickListener(cl);
        b7.setOnClickListener(cl);
        b8.setOnClickListener(cl);
        b9.setOnClickListener(cl);

    }
}
