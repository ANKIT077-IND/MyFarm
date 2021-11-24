package com.codewithankit.myfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
  TextView register,resetpassword;
  Toolbar logintoolbar;
    private FirebaseAuth mAuth;
  TextInputEditText email,password;
  AppCompatButton login;
  BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver=new ConnectionReceiver();
        Registernetwork();
        mAuth = FirebaseAuth.getInstance();
        logintoolbar=findViewById(R.id.logintoolbar);
        setSupportActionBar(logintoolbar);
        getSupportActionBar().setTitle("Login");

        register=findViewById(R.id.register);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        resetpassword=findViewById(R.id.resetpassword);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etemail=email.getText().toString();
                String etpassword=password.getText().toString();

                if (etemail.isEmpty()){
                    Toasty.error(getApplicationContext(),"Please enter the Email",Toasty.LENGTH_LONG,true).show();
                }
                else if(etemail.indexOf('@')<=0){
                    Toasty.error(getApplicationContext()," @ Invalid  Positions ",Toasty.LENGTH_LONG,true).show();
                }
                else if (etemail.charAt(etemail.length()-3)!='.' && etemail.charAt(etemail.length()-4)!='.'){
                    Toasty.error(getApplicationContext()," . Invalid Position",Toasty.LENGTH_LONG,true).show();
                }
                else if (etpassword.isEmpty()){
                    Toasty.error(getApplicationContext(),"Please enter the Password",Toasty.LENGTH_LONG,true).show();
                }
                else if (etpassword.length()<5 && etpassword.length()>15){
                    Toasty.error(getApplicationContext(),"Password length must we 5 to 15 character",Toasty.LENGTH_LONG,true).show();
                }
                else {
                    ProgressDialog dialog=new ProgressDialog(MainActivity.this);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setMessage("Please Wait....");
                    dialog.show();

                    mAuth.signInWithEmailAndPassword(etemail,etpassword)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        dialog.dismiss();
                                      Toasty.success(getApplicationContext(),"Login Successfully",Toasty.LENGTH_LONG,true).show();
                                      email.setText("");
                                      password.setText("");
                                      Intent intent=new Intent(MainActivity.this,homepage.class);
                                      startActivity(intent);
                                      finish();

                                    } else {
                                        dialog.hide();
                                        Toasty.error(getApplicationContext(),"Login failed try again",Toasty.LENGTH_LONG,true).show();
                                        email.setText("");
                                        password.setText("");

                                    }
                                }
                            });

                }




            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        });

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ResetPassword.class);
                startActivity(intent);
            }
        });

    }

    public void Registernetwork(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
    public void unRegisternetwork(){
        try {
         unregisterReceiver(broadcastReceiver);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            Intent intent=new Intent(MainActivity.this,homepage.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisternetwork();
    }
}
