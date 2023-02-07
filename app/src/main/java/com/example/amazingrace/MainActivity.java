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

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtMoney;
    EditText edtBetMoney;
    ImageButton btnStart, btnReset;
    CheckBox cb1, cb2, cb3;
    SeekBar sb1, sb2, sb3;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        sb1.setEnabled(false);
        sb2.setEnabled(false);
        sb3.setEnabled(false);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                }
                countDownTimer = new CountDownTimer(10000, 200) {
                    @Override
                    public void onTick(long l) {
                        sb1.setProgress(sb1.getProgress() + randomSpeed());
                        sb2.setProgress(sb2.getProgress() + randomSpeed());
                        sb3.setProgress(sb3.getProgress() + randomSpeed());
                    }

                    @Override
                    public void onFinish() {

                    }
                };

                if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {
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

    private void AnhXa() {
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

    private int randomSpeed() {
        return (new Random().nextInt(5)) + 3;
    }
}