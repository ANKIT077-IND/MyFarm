package com.codewithankit.myfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class ResetPassword extends AppCompatActivity {
    Toolbar resetoolbar;
    TextInputEditText email;
    AppCompatButton Reset;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mAuth=FirebaseAuth.getInstance();
        resetoolbar=findViewById(R.id.resetoolbar);
        setSupportActionBar(resetoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Reset Password");

        email=findViewById(R.id.resetemail);
        Reset=findViewById(R.id.Reset);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etmail=email.getText().toString();

                if (etmail.isEmpty()){
                    Toasty.error(getApplicationContext(),"Please enter the Email",Toasty.LENGTH_LONG,true).show();
                }
                else if (etmail.indexOf('@')<=0){
                    Toasty.error(getApplicationContext()," @ Invalid Position",Toasty.LENGTH_LONG,true).show();
                }
                else if(etmail.charAt(etmail.length()-3)!='.' && etmail.charAt(etmail.length()-4)!='.'){
                    Toasty.error(getApplicationContext()," . Invalid Position",Toasty.LENGTH_LONG,true).show();
                }
                else {
                    ProgressDialog dialog=new ProgressDialog(ResetPassword.this);
                    dialog.setMessage("Please wait....");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();


                    mAuth.sendPasswordResetEmail(etmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                          if (task.isSuccessful()){
                              dialog.dismiss();
                              Toasty.success(getApplicationContext(),"Password reset link send to email",Toasty.LENGTH_LONG,true).show();
                               email.setText("");

                          }
                          else{
                              dialog.hide();
                              Toasty.error(getApplicationContext(),"Password reset failed , try again",Toasty.LENGTH_LONG,true).show();
                              email.setText("");

                          }
                        }
                    });
                }


            }
        });

    }
}
