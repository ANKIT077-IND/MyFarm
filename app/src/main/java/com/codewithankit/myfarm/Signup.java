package com.codewithankit.myfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class Signup extends AppCompatActivity {
    Spinner sex,land_ownership,farm_Land_Area;
    String Sex,Land_ownership,farm_land_Area;
    String SEX[]={"Male","Female","Others"};
    String Land_Ownership[]={"Rented","Owned"};
    String Farm_Land_Area[]={"Acres","Hectares"};
    Toolbar registertoolbar;
    Button createaccount;
    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference reference;
    EditText etemail,etpassword,name,age,village,taluka,district,state,Farm_Land_Address,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        registertoolbar=findViewById(R.id.registertoolbar);
        setSupportActionBar(registertoolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sex=findViewById(R.id.sex);
        land_ownership=findViewById(R.id.land_ownership);
        farm_Land_Area=findViewById(R.id.Farm_Land_Area);
        etemail=findViewById(R.id.etemail);
        etpassword=findViewById(R.id.etpassword);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        village=findViewById(R.id.village);
        taluka=findViewById(R.id.taluka);
        district=findViewById(R.id.district);
        state=findViewById(R.id.state);
        Farm_Land_Address=findViewById(R.id.Farm_Land_Address);
        mobile=findViewById(R.id.mobile);
        createaccount=findViewById(R.id.createaccount);
        ArrayAdapter<String> mysex=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,SEX);
        sex.setAdapter(mysex);
        ArrayAdapter<String> myland=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,Land_Ownership);
        land_ownership.setAdapter(myland);
        ArrayAdapter<String> myfarm=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,Farm_Land_Area);
        farm_Land_Area.setAdapter(myfarm);


        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sex=sex.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        land_ownership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Land_ownership=land_ownership.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        farm_Land_Area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                farm_land_Area=farm_Land_Area.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etemail.getText().toString();
                String password=etpassword.getText().toString();
                String Name=name.getText().toString();
                String Age=age.getText().toString();
                String Village=village.getText().toString();
                String Taluka=taluka.getText().toString();
                String District=district.getText().toString();
                String State=state.getText().toString();
                String FarmlandAddress=Farm_Land_Address.getText().toString();
                String Mobile=mobile.getText().toString();


                db=FirebaseDatabase.getInstance();
                reference=db.getReference().child("Users");

                reference.orderByChild("Mobile_Number").equalTo(Mobile).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue()!=null){
                           Toasty.warning(getApplicationContext(),"Mobile Number Already Register",Toasty.LENGTH_LONG,true).show();
                        }
                        else{

                            if (email.isEmpty()){
                                Toasty.error(getApplicationContext(),"Please enter the Email",Toasty.LENGTH_LONG,true).show();
                            }
                            else if(email.indexOf('@')<=0){
                                Toasty.error(getApplicationContext()," @ Invalid  Positions ",Toasty.LENGTH_LONG,true).show();
                            }
                            else if (email.charAt(email.length()-3)!='.' && email.charAt(email.length()-4)!='.'){
                                Toasty.error(getApplicationContext()," . Invalid Position",Toasty.LENGTH_LONG,true).show();
                            }
                            else if (password.isEmpty()){
                                Toasty.error(getApplicationContext(),"Please enter the Password",Toasty.LENGTH_LONG,true).show();
                            }
                            else if (password.length()<5 && password.length()>15){
                                Toasty.error(getApplicationContext(),"Password length must we 5 to 15 character",Toasty.LENGTH_LONG,true).show();
                            }
                            else if (Name.isEmpty()){
                                Toasty.error(getApplicationContext(),"Please enter the farmer name",Toasty.LENGTH_LONG,true).show();
                            }
                            else if(Name.length()<3 && Name.length()>15){
                                Toasty.error(getApplicationContext(),"Name length must we 3 to 15 character",Toasty.LENGTH_LONG,true).show();

                            }
                            else if (Age.isEmpty()){
                                Toasty.error(getApplicationContext(),"Please enter the age",Toasty.LENGTH_LONG,true).show();
                            }
                            else if (Village.isEmpty()){
                                Toasty.error(getApplicationContext(),"Please enter the village name",Toasty.LENGTH_LONG,true).show();

                            }
                            else if (Taluka.isEmpty()){
                                Toasty.error(getApplicationContext(),"Please enter the Taluka name",Toasty.LENGTH_LONG,true).show();

                            }
                            else if (District.isEmpty()){
                                Toasty.error(getApplicationContext(),"Please enter the District name",Toasty.LENGTH_LONG,true).show();
                            }
                            else if (State.isEmpty()){
                                Toasty.error(getApplicationContext(),"Please enter the State name",Toasty.LENGTH_LONG,true).show();
                            }
                            else if (FarmlandAddress.isEmpty()){
                                Toasty.error(getApplicationContext(),"Please enter the Farm land Address name",Toasty.LENGTH_LONG,true).show();
                            }
                            else if (Mobile.isEmpty()){
                                Toasty.error(getApplicationContext(),"Please enter the Mobile Number",Toasty.LENGTH_LONG,true).show();
                            }
                            else if (Mobile.length()!=10){
                                Toasty.error(getApplicationContext(),"Please enter the 10 Digit Mobile number",Toasty.LENGTH_LONG,true).show();
                            }

                            else {

                                mAuth.createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                ProgressDialog dialog=new ProgressDialog(Signup.this);
                                                dialog.setCanceledOnTouchOutside(false);
                                                dialog.setMessage("Please Wait...");
                                                dialog.show();

                                                if (task.isSuccessful()) {

                                                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                                                    String id=user.getUid();
                                                    db=FirebaseDatabase.getInstance();
                                                    reference=db.getReference().child("Users").child(id);

                                                    HashMap<String,Object>map=new HashMap<>();
                                                    map.put("Email",email);
                                                    map.put("Password",password);
                                                    map.put("Farmer_Name",Name);
                                                    map.put("Sex",Sex);
                                                    map.put("Age",Age);
                                                    map.put("Village",Village);
                                                    map.put("Taluka",Taluka);
                                                    map.put("District",District);
                                                    map.put("State",State);
                                                    map.put("Land_Ownership",Land_ownership);
                                                    map.put("Farm_Land_Address",FarmlandAddress);
                                                    map.put("Farm_Land_Area",farm_land_Area);
                                                    map.put("Mobile_Number",Mobile);

                                                    reference.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            dialog.dismiss();
                                                            etemail.setText("");
                                                            etpassword.setText("");
                                                            name.setText("");
                                                            age.setText("");
                                                            village.setText("");
                                                            taluka.setText("");
                                                            district.setText("");
                                                            state.setText("");
                                                            Farm_Land_Address.setText("");
                                                            mobile.setText("");

                                                            Toasty.success(getApplicationContext(),"Register Successfully",Toasty.LENGTH_LONG,true).show();
                                                        }
                                                    });


                                                } else {
                                                    dialog.hide();
                                                    etemail.setText("");
                                                    etpassword.setText("");
                                                    name.setText("");
                                                    age.setText("");
                                                    village.setText("");
                                                    taluka.setText("");
                                                    district.setText("");
                                                    state.setText("");
                                                    Farm_Land_Address.setText("");
                                                    mobile.setText("");
                                                    Toasty.error(getApplicationContext(),"Register failed,try again",Toasty.LENGTH_LONG,true).show();
                                                }
                                            }
                                        });
                            }

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                      Toasty.info(getApplicationContext(),error.toString(),Toasty.LENGTH_LONG,true).show();
                    }
                });

            }
        });
    }
}
