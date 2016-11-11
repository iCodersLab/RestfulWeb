package icoderslab.com.restfulspring;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    //creating variables
    EditText Password,UserName;
    Button LoginButton,Register;
    ProgressBar pBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //taking inputs
        setContentView(R.layout.activity_main);
        UserName=(EditText) findViewById(R.id.eText1);
        Password=(EditText) findViewById(R.id.eText2);
        LoginButton=(Button) findViewById(R.id.b1);
        Register=(Button) findViewById(R.id.b2);
        pBar =(ProgressBar) findViewById(R.id.pBar1);
        pBar.setVisibility(View.GONE);
//when register button is clicked
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent I=new Intent(MainActivity.this, UserRegister.class);

            }
        });
    }
    //creatinng executing class
    class TaskExecute extends AsyncTask<String, Integer, String>{


        @Override
        protected String doInBackground(String... s) {
          String r= PostData(s);
            return r;
        }
        protected void onPostExecute(String result) {
            pBar.setVisibility(View.GONE);
            //progess_msz.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG ).show();
        }
    }
    //this method will gonna help us to post dataa on servelet
   public String PostData(String[] valuse) {
        String s="";
        try
        {

            HttpClient httpClient=new DefaultHttpClient();
            //host Address
            HttpPost httpPost=new HttpPost("www.example.com");

            List<NameValuePair> list=new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("name", valuse[0]));
            list.add(new BasicNameValuePair("pass",valuse[1]));
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse=  httpClient.execute(httpPost);

            HttpEntity httpEntity=httpResponse.getEntity();
            s= readResponse(httpResponse);


        }//throw exception
        catch(Exception exception)  {}
        return s;


    }

    public String readResponse(HttpResponse res) {
        InputStream is=null;
        String return_text="";
        try {
            is=res.getEntity().getContent();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
            String line="";
            StringBuffer sb=new StringBuffer();
            while ((line=bufferedReader.readLine())!=null)
            {
                sb.append(line);
            }
            return_text=sb.toString();
        } catch (Exception e)
        {
//throw exception
        }
        return return_text;

    }


}
