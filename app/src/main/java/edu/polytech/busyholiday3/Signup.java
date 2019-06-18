package edu.polytech.busyholiday3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button SUButton1 = (Button) findViewById(R.id.SUButton1);
        SUButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        members.class);
                startActivity(intent);

                final String resultText;

                Toast.makeText(Signup.this,"로그인 되었습니다" ,Toast.LENGTH_LONG).show();
                    startActivityForResult(intent, 1);

            }
        });
    }
}
