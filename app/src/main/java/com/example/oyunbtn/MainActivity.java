package com.example.oyunbtn;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Integer> images = new ArrayList<>();
    private Button[] buttons = new Button[16];
    private int cardBack = R.drawable.ark;
    private int numClicks = 0;
    private TextView numtxt;
    private final int[] clicked = {0};
    private final boolean[] turnOver = {false};
    private final int[] lastClicked = {-1};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numtxt = findViewById(R.id.numcli);

        images.add(R.drawable.image1);
        images.add(R.drawable.image2);
        images.add(R.drawable.image3);
        images.add(R.drawable.image4);
        images.add(R.drawable.image5);
        images.add(R.drawable.image6);
        images.add(R.drawable.image7);
        images.add(R.drawable.image8);
        images.add(R.drawable.image1);
        images.add(R.drawable.image2);
        images.add(R.drawable.image3);
        images.add(R.drawable.image4);
        images.add(R.drawable.image5);
        images.add(R.drawable.image6);
        images.add(R.drawable.image7);
        images.add(R.drawable.image8);

        buttons[0] = findViewById(R.id.btn1);
        buttons[1] = findViewById(R.id.btn2);
        buttons[2] = findViewById(R.id.btn3);
        buttons[3] = findViewById(R.id.btn4);
        buttons[4] = findViewById(R.id.btn11);
        buttons[5] = findViewById(R.id.btn12);
        buttons[6] = findViewById(R.id.btn13);
        buttons[7] = findViewById(R.id.btn14);
        buttons[8] = findViewById(R.id.btn21);
        buttons[9] = findViewById(R.id.btn22);
        buttons[10] = findViewById(R.id.btn23);
        buttons[11] = findViewById(R.id.btn24);
        buttons[12] = findViewById(R.id.btn31);
        buttons[13] = findViewById(R.id.btn32);
        buttons[14] = findViewById(R.id.btn33);
        buttons[15] = findViewById(R.id.btn34);

        Collections.shuffle(images);
        for (int i = 0; i < 16; i++) {
            final int finalI = i;
            buttons[i].setBackgroundResource(cardBack);
            buttons[i].setText("cardBack");
            buttons[i].setTextSize(0.0F);
            buttons[i].setOnClickListener(view -> {
                numClicks++;
                numtxt.setText("Tıklama : " + numClicks);
                if (buttons[finalI].getText().equals("cardBack") && !turnOver[0]) {
                    buttons[finalI].setBackgroundResource(images.get(finalI));
                    buttons[finalI].setText(String.valueOf(images.get(finalI)));
                    if (clicked[0] == 0) {
                        lastClicked[0] = finalI;
                    }
                    clicked[0]++;
                } else if (!buttons[finalI].getText().equals("cardBack")) {
                    buttons[finalI].setBackgroundResource(cardBack);
                    buttons[finalI].setText("cardBack");
                    clicked[0]--;
                }

                if (clicked[0] == 2) {
                    turnOver[0] = true;
                    if (buttons[finalI].getText().equals(buttons[lastClicked[0]].getText())) {
                        buttons[finalI].setClickable(false);
                        buttons[lastClicked[0]].setClickable(false);
                        turnOver[0] = false;
                        clicked[0] = 0;
                    } else {
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            buttons[finalI].setBackgroundResource(cardBack);
                            buttons[finalI].setText("cardBack");
                            buttons[lastClicked[0]].setBackgroundResource(cardBack);
                            buttons[lastClicked[0]].setText("cardBack");
                            clicked[0] = 0;
                            turnOver[0] = false;
                        }, 1000);
                    }
                } else if (clicked[0] == 0) {
                    turnOver[0] = false;
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    public void resetCards(View view) {
        Collections.shuffle(images);
        for (int i = 0; i < 16; i++) {
            numClicks = 0;
            numtxt.setText("Tıklama : " + numClicks);
            buttons[i].setBackgroundResource(cardBack);
            buttons[i].setText("cardBack");
            buttons[i].setClickable(true);
        }
    }
}
