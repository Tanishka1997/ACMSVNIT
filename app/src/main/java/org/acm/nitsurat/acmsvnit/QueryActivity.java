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
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;

public class QueryActivity extends AppCompatActivity {
    private Button postQur;
    private EditText QueryTB, mailTB, addmNoTB;
    private final String serverLink = "http://192.168.1.7:80/query.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        QueryTB = (EditText) findViewById(R.id.message);
        mailTB = (EditText) findViewById(R.id.mail);
        addmNoTB = (EditText) findViewById(R.id.addNo);
        postQur = (Button) findViewById(R.id.send);
        postQur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addmNo = addmNoTB.getText().toString();
                String mailID = mailTB.getText().toString();
                String query = QueryTB.getText().toString();
                if(addmNo == "" || mailID == "" || query == "")
                    Toast.makeText(getApplicationContext(),"Invaild Details. Try again",Toast.LENGTH_SHORT).show();
                else{
                    if(android.util.Patterns.EMAIL_ADDRESS.matcher(mailID).matches() && addmNo.length()==8)
                    {
                        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("uid",addmNo));
                        params.add(new BasicNameValuePair("mail",mailID));
                        params.add(new BasicNameValuePair("query",query));
                        new PostQuery().execute(params);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Invalid Mail-ID/Admission Number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private class PostQuery extends AsyncTask<ArrayList<NameValuePair> , Integer , String> {

        protected String doInBackground(ArrayList<NameValuePair>... nameValuePairs) {
            if(MainActivity.checkConnection(getApplicationContext()))
                postData(nameValuePairs[0]);
            return null;
        }

        protected void onPostExecute(String result) {
            String msg = "";
            if(MainActivity.checkConnection(getApplicationContext()))
                msg = msg.concat("Query recorded! We will get back to you soon.");
            else
                msg = msg.concat("No internet connection.Try again later");
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            finish();
        }

    }
    public void postData(ArrayList<NameValuePair> nameValuePairs) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(serverLink);

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
