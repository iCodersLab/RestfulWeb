package icoderslab.com.restfulspring;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harris on 11/9/2016.
 */
public class UserRegister extends Activity {

      //creating variables
    EditText UserName,Password;
    Button Register,Login;
    ProgressBar pBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        //taking inputs a
        UserName=(EditText) findViewById(R.id.eText1);;
        Password=(EditText) findViewById(R.id.eText2);
        Register=(Button) findViewById(R.id.b1);

        pBar =(ProgressBar) findViewById(R.id.pBar1);
        pBar.setVisibility(View.GONE);
//when register button is clicked
        Register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pBar.setVisibility(View.VISIBLE);
//it will be visible
                String s1=UserName.getText().toString();
                String s2=Password.getText().toString();
                //and it will execute
                new ExecuteTask().execute(s1,s2);
            }
        });




    }
//creatinng executing class
    class ExecuteTask extends AsyncTask<String, Integer, String>
    {

        @Override
        protected String doInBackground(String... params) {

            PostData(params);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
//visibilitiy set off
            pBar.setVisibility(View.GONE);
        }

    }


//this method will gonna help us to post dataa on servelet
    public void PostData(String[] valuse) {
        try
        {
            HttpClient httpClient=new DefaultHttpClient();
            //host Address
            HttpPost httpPost=new HttpPost("www.example.com");
            //creating a LIst of arraylist
            List<NameValuePair> LIst=new ArrayList<NameValuePair>();
            
            LIst.add(new BasicNameValuePair("UserName", valuse[0]));
            LIst.add(new BasicNameValuePair("PassWord",valuse[1]));
            httpPost.setEntity(new UrlEncodedFormEntity(LIst));
            httpClient.execute(httpPost);
        }
        catch(Exception e)
        {//throw exception
            System.out.println(e);
        }

    }

}
