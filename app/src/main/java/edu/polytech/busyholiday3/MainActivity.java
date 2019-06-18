package edu.polytech.busyholiday3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button CTWButton1 = (Button) findViewById(R.id.CTWButton1);
        CTWButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText idEdit, passEdit;

                idEdit = (EditText) findViewById(R.id.CTWEditText1);
                passEdit = (EditText) findViewById(R.id.CTWEditText2);

                String idStr, passStr;
                idStr = idEdit.getText().toString();
                passStr = passEdit.getText().toString();

                Intent intent = new Intent(getApplicationContext(),
                        members.class);

//                intent.putExtra("inputID", idStr);
//                intent.putExtra("inputPassword", passStr);//세컨드로 던짐(세컨드에선 받아야함)

                final String resultText;

                if(idStr.equals("abc") && passStr.equals("1111")) {
                    resultText = "로그인이 성공하였습니다.";
                } else {
                    resultText = "ID/PW 틀린것 같네요.";
                }

                Toast.makeText(MainActivity.this, resultText,Toast.LENGTH_LONG).show();
                if (resultText.equals("로그인이 성공하였습니다.")) {
                    startActivityForResult(intent, 1000);
                }
            }
        });

        Button CTWButton2 = (Button) findViewById(R.id.CTWButton2);
        CTWButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        nomembers.class);
                startActivity(intent);
                finish();
            }
        });

        TextView CTWTextView2 = (TextView) findViewById(R.id.CTWTextView2);
        CTWTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        Signup.class);
                startActivity(intent);
                finish();
            }
        });

        TextView CTWTextView4 = (TextView) findViewById(R.id.CTWTextView4);
        CTWTextView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        FindPW.class);
                startActivity(intent);
                finish();
            }
        });
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(resultCode == RESULT_OK) {
//            if(requestCode == 1000) {
//
//                TextView errText = (TextView)findViewById(R.id.errorText);
//                errText.setText(data.getStringExtra("errText"));
//            }
//        }
//    }
}
