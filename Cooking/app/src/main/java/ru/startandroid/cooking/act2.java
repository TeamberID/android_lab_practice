package ru.startandroid.cooking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class act2 extends AppCompatActivity {
    public static final String EXTRA_TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish);

        TextView name = (TextView) findViewById(R.id.name_tv);
        TextView recepie = (TextView) findViewById(R.id.recepie);
        TextView time = (TextView) findViewById(R.id.time);
        TextView dif = (TextView) findViewById(R.id.difficult);

        String text = "";
        if (getIntent().getExtras() != null) {
            text = (String) getIntent().getExtras().get(EXTRA_TEXT);
            name.setText(text);
            switch (text) {
                case "SHAURMA":
                    recepie.setText("go and by");
                    time.setText("2  hours");
                    dif.setText("hard");
                    break;
                case "chop":
                    recepie.setText("go and by");
                    time.setText("1  hours");
                    dif.setText("hard");
                    break;
                case "pahlava":
                    recepie.setText("go and by");
                    time.setText("0.4  hours");
                    dif.setText("easy");
                    break;
                case "halva":
                    recepie.setText("go and by");
                    time.setText("5 min");
                    dif.setText("easy");
                    break;
                case "vegetables":
                    recepie.setText("go and by");
                    time.setText("2  hours");
                    dif.setText("medium");
                    break;
                case "mcduck":
                    recepie.setText("go and by");
                    time.setText("0  hours");
                    dif.setText("medium");
                    break;

            }
        }

    }
}
