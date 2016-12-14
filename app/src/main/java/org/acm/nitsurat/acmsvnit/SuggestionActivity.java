package org.acm.nitsurat.acmsvnit;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.NoopUserTokenHandler;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SuggestionActivity extends AppCompatActivity {
    private Button postSuggest;
    private EditText uidTB, mailTB, suggestTB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        postSuggest = (Button)findViewById(R.id.send);
        uidTB = (EditText)findViewById(R.id.addNo);
        mailTB = (EditText)findViewById(R.id.mail);
        suggestTB = (EditText)findViewById(R.id.message);
        postSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = uidTB.getText().toString();
                String mail = mailTB.getText().toString();
                String suggest = suggestTB.getText().toString();
                if(uid.equals("")||mail.equals("")||suggest.equals(""))
                    Toast.makeText(getApplicationContext(),"Inavlid details!Retry",Toast.LENGTH_SHORT).show();
                else{
                    if(android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches() && uid.length()==8)
                    {
                        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("uid",uid));
                        params.add(new BasicNameValuePair("mail",mail));
                        params.add(new BasicNameValuePair("suggestion",suggest));
                        new PostSuggestion().execute(params);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Invalid Mail-ID/Admission Number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private class PostSuggestion extends AsyncTask<ArrayList<NameValuePair> , Integer , String>{

        protected String doInBackground(ArrayList<NameValuePair>... nameValuePairs) {
            postData(nameValuePairs[0]);
            return null;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Suggestion recorded! we will get back to you soon.", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
    public void postData(ArrayList<NameValuePair> nameValuePairs) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://192.168.1.7:80/suggest.php");

        try {

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
