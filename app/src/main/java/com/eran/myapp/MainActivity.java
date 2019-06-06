package com.eran.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{


    private ImageButton log,Reg;
    CallbackManager callbackManager;
    TextView txtEmail,txtname;
    ProgressDialog mDialog;
    ImageView imgAvatar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Reg = (ImageButton) findViewById(R.id.register_btn);
        log = (ImageButton) findViewById(R.id.log_btn);
        Reg.setOnClickListener(this);
        log.setOnClickListener(this);

        imgAvatar = (ImageView) findViewById(R.id.imageView3);

        txtEmail = (TextView) findViewById(R.id.txtemail);
        txtname = (TextView) findViewById(R.id.txtname);

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setPermissions(Arrays.asList("email", "public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode,resultCode,data);

    }

    AccessTokenTracker tokenTracker= new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken==null)
            {
                txtname.setText("");
                txtEmail.setText("");
                imgAvatar.setImageResource(0);
                Toast.makeText(MainActivity.this,"User Logged out",Toast.LENGTH_LONG).show();
            }
            else{
                loadUserProfile(currentAccessToken);
                Intent intent = new Intent(MainActivity.this , menu.class);
                startActivity(intent);
            }

        }
    };

    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest request=GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try{
                    String first_name=object.getString("first_name");
                    String last_name=object.getString("last_name");
                    String email=object.getString("email");
                    String id=object.getString("id");
                    String image_url="https://graph.facebook.com/"+id+ "/picture?type=normal";

                    txtEmail.setText(email);
                    txtname.setText(first_name+" "+last_name);

                    RequestOptions requestOptions=new RequestOptions();
                    Glide.with(MainActivity.this).load(image_url).into(imgAvatar);
                    Intent intent = new Intent(MainActivity.this , menu.class);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Bundle parameters=new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }





    @Override
    public void onClick(View v) {
        if (v==Reg)
        {
            startActivity(new Intent(MainActivity.this,register.class));

        }
        if (v==log)
        {
            startActivity(new Intent(MainActivity.this,Login.class));

        }

    }
}

