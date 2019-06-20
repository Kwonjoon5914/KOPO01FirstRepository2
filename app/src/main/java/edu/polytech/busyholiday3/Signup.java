package edu.polytech.busyholiday3;

import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Signup extends Activity {

    private static String IP_ADDRESS = "192.168.23.107";
    private static String TAG = "phptest";

    private EditText mEditID;
    private EditText mEditPassword;
    private EditText mEditName;
    private EditText mEditEmail;
    private EditText mEditPhone;


    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView textView = (TextView)findViewById(R.id.alertDialogTextView);
        this.showMultipleCheckboxInAlertDialog(textView);

        mEditID = (EditText)findViewById(R.id.SUEditText1);
        mEditPassword = (EditText)findViewById(R.id.SUEditText2);
        mEditName = (EditText)findViewById(R.id.SUEditText4);
        mEditEmail = (EditText)findViewById(R.id.SUEditText5);
        mEditPhone = (EditText)findViewById(R.id.SUEditText5);
        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        Button buttonInsert = (Button)findViewById(R.id.SUButton1);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UserID = mEditID.getText().toString();
                String Password = mEditPassword.getText().toString();
                String Name = mEditName.getText().toString();
                String Email = mEditEmail.getText().toString();
                String Phone = mEditPhone.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/insert.php", UserID, Password, Name, Email, Phone);


                mEditID.setText("");
                mEditPassword.setText("");
                mEditName.setText("");
                mEditEmail.setText("");
                mEditPhone.setText("");

                Toast.makeText(Signup.this,"로그인 되었습니다" ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),
                        members.class);
                startActivity(intent);
            }
        });

    }

    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Signup.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }

        @Override
        protected String doInBackground(String... params) {

            String ID = (String)params[1];
            String Password = (String)params[2];
            String Name = (String)params[3];
            String Email = (String)params[4];
            String Phone = (String)params[5];

            String serverURL = (String)params[0];
            String postParameters = "UserID=" + ID + "&Password=" + Password + "&Name=" + Name + "&Email=" + Email + "&Phone=" + Phone;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString();

            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }
    }


    // Show multiple choice checkbox in alert dialog.

    private void showMultipleCheckboxInAlertDialog(TextView textView)
    {
        final TextView textViewTmp = textView;
        Button alertDialogButton = (Button)findViewById(R.id.alertDialogMultipleCheckboxButton);
        alertDialogButton.setOnClickListener(new View.OnClickListener() {

            // Store user checked checkbox values.
            private Map<String, String> chooseDataMap = new HashMap<String, String>();
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("관심 분야");

                final String listItemArr[] = {"음악", "미술", "공예", "요리", "생활", "운동"
                        ,"피아노","보컬","기타","드럼","믹싱","수채화","유화","동양화","스케치","사진"
                        ,"영상","캘리그라피","한식","일식","양식","베이킹","비누","캔들","도예"
                        ,"플라워","반려동물","인테리어","조경","DIY","스쿠버다이빙","글램핑"};
                builder.setMultiChoiceItems(listItemArr, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false
                                , false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
                                , false, false, false, false},
                        new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int itemIndex, boolean checked) {
                        String checkboxString = listItemArr[itemIndex];

                        if(checked)
                        {
                            if(chooseDataMap.get(checkboxString)==null) {
                                chooseDataMap.put(checkboxString, checkboxString);
                            }
                        }else
                        {
                            if(chooseDataMap.get(checkboxString)!=null) {
                                chooseDataMap.remove(checkboxString);
                            }
                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textViewTmp.setText("당신의 관심 분야는" + chooseDataMap.keySet().toString() + "이군요!");

                        // Empty the data map.
                        Set<String> keySet = chooseDataMap.keySet();
                        Iterator keySetIt = keySet.iterator();
                        while(keySetIt.hasNext())
                        {
                            // Remove one element.
                            String key = (String)keySetIt.next();
                            chooseDataMap.remove(key);

                            // Recalculate keyset.
                            keySet = chooseDataMap.keySet();
                            keySetIt = keySet.iterator();
                        }
                    }
                });
                builder.show();
            }
        });
    }
}