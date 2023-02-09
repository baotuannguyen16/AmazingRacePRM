package com.example.amazingrace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtMoney;
    EditText edtBetMoney;
    EditText etShip1;
    EditText etShip2;
    EditText etShip3;



    ImageButton btnStart, btnReset;
    CheckBox cb1, cb2, cb3;
    SeekBar sb1, sb2, sb3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        sb1.setEnabled(false);
        sb2.setEnabled(false);
        sb3.setEnabled(false);


        TextView txtMoney = (TextView) findViewById(R.id.txtMoney);
        txtMoney.setText(String.valueOf(100));
        EditText etShip1 = (EditText) findViewById(R.id.etShip1);
        EditText etShip2 = (EditText) findViewById(R.id.etShip2);
        EditText etShip3 = (EditText) findViewById(R.id.etShip3);

        ArrayList<Integer> result = new ArrayList<Integer>();
        CountDownTimer countDownTimer;
        countDownTimer = new CountDownTimer(10000, 100) {

            @Override
            public void onTick(long l) {
                btnStart.setEnabled(false);
                Random rand = new Random();
                sb1.setProgress((int) (sb1.getProgress() + rand.nextInt(5)));
                if (sb1.getProgress() >= 100) {
                    if(!result.contains(1)) {
                        result.add(1);
                    }
                }
                sb2.setProgress((int) (sb2.getProgress() + rand.nextInt(5)));
                if (sb2.getProgress() >= 100) {
                    if(!result.contains(2)) {
                        result.add(2);
                    }
                }
                sb3.setProgress((int) (sb3.getProgress() + rand.nextInt(5)));
                if (sb3.getProgress() >= 100) {
                    if(!result.contains(3)) {
                        result.add(3);
                    }
                }

                if (sb1.getProgress() >= 100 && sb2.getProgress() >= 100 && sb3.getProgress() >= 100) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Hoàn thành, thuyền về nhất là thuyền số " + result.get(0), Toast.LENGTH_SHORT).show();

                    if (!result.isEmpty() && txtMoney != null) {
                        try {
                            int winningMoney = 0;
                            int betShip1 = 0;
                            int betShip2 = 0;
                            int betShip3 = 0;
                            if (cb1.isChecked()) {
                                String et1 = etShip1.getText().toString().trim();
                                betShip1 = Integer.parseInt(et1);
                            }
                            if (cb2.isChecked()) {
                                String et2 = etShip2.getText().toString().trim();
                                betShip2 = Integer.parseInt(et2);
                            }
                            if (cb3.isChecked()) {
                                String et3 = etShip3.getText().toString().trim();
                                betShip3 = Integer.parseInt(et3);
                            }
                            String moneyString = txtMoney.getText().toString().trim();
                            int money = Integer.parseInt(moneyString);

                            if (result.get(0) == 1) {
                                winningMoney += betShip1 * 3;
                            } else if (result.get(0) == 2) {
                                winningMoney += betShip2 * 3;
                            } else if (result.get(0) == 3) {
                                winningMoney += betShip3 * 3;
                            }
                            money = money - betShip1 - betShip2 - betShip3;
                            money += winningMoney;
                            txtMoney.setText(String.valueOf(money));
                            btnStart.setEnabled(true);

                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        };


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb1.isChecked() || cb2.isChecked() || cb3.isChecked()){
                    sb1.setProgress(0);
                    sb2.setProgress(0);
                    sb3.setProgress(0);

                    countDownTimer.start();

                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng đặt cược trước khi chơi !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMoney.setText("100");
            }
        });
    }

    private void AnhXa(){
        txtMoney = (TextView) findViewById(R.id.txtMoney);
        edtBetMoney = (EditText) findViewById(R.id.edtBetMoney);
        btnStart = (ImageButton) findViewById(R.id.btnStart);
        btnReset = (ImageButton) findViewById(R.id.btnReset);
        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        sb1 = (SeekBar) findViewById(R.id.seekBarShip1);
        sb2 = (SeekBar) findViewById(R.id.seekBarShip2);
        sb3 = (SeekBar) findViewById(R.id.seekBarShip3);
    }
}