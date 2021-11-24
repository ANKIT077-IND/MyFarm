package com.codewithankit.myfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class editprofile extends AppCompatActivity {
    Toolbar edittoolbar;
    Spinner sex,land_ownership,farm_Land_Area;
    String Sex,Land_ownership,farm_land_Area;
    String SEX[]={"Male","Female","Others"};
    String Land_Ownership[]={"Rented","Owned"};
    String Farm_Land_Area[]={"Acres","Hectares"};
    EditText etemail,etpassword,name,age,village,taluka,district,state,Farm_Land_Address,mobile;
    FirebaseDatabase database,db;
    DatabaseReference reference,myref;
    Button editprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        edittoolbar=findViewById(R.id.edittoolbar);
        setSupportActionBar(edittoolbar);
        getSupportActionBar().setTitle("Edit Profile");
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
        editprofile=findViewById(R.id.edit);
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

        database=FirebaseDatabase.getInstance();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getUid();
        reference=database.getReference().child("Users").child(uid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.getValue()!=null){

                String Email=snapshot.child("Email").getValue(String.class);
                String Name=snapshot.child("Farmer_Name").getValue(String.class);
                String Age=snapshot.child("Age").getValue(String.class);
                String District=snapshot.child("District").getValue(String.class);
                String Address=snapshot.child("Farm_Land_Address").getValue(String.class);
                String Mobile_Number=snapshot.child("Mobile_Number").getValue(String.class);
                String Password=snapshot.child("Password").getValue(String.class);
                String State=snapshot.child("State").getValue(String.class);
                String Taluka=snapshot.child("Taluka").getValue(String.class);
                String Village=snapshot.child("Village").getValue(String.class);

                etemail.setText(Email);
                etpassword.setText(Password);
                mobile.setText(Mobile_Number);
                village.setText(Village);
                taluka.setText(Taluka);
                state.setText(State);
                name.setText(Name);
                Farm_Land_Address.setText(Address);
                district.setText(District);
                age.setText(Age);
            }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toasty.error(getApplicationContext(),error.toString(),Toasty.LENGTH_LONG,true).show();

            }
        });

     editprofile.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             ProgressDialog dialog=new ProgressDialog(editprofile.this);
                    dialog.setMessage("Please wait...");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
             String Gmail=etemail.getText().toString().trim();
             String Pass=etpassword.getText().toString().trim();
             String etname=name.getText().toString().trim();
             String etage=age.getText().toString().trim();
             String etVillage=village.getText().toString().trim();
             String ettaluka=taluka.getText().toString().trim();
             String etdistrict=district.getText().toString().trim();
             String etstate=state.getText().toString().trim();
             String etaddress=Farm_Land_Address.getText().toString().trim();
             String etmobile=mobile.getText().toString().trim();



             db=FirebaseDatabase.getInstance();
             FirebaseUser user1=FirebaseAuth.getInstance().getCurrentUser();
             String id=user1.getUid();

             myref=db.getReference().child("Users").child(id);


             HashMap<String,Object> map=new HashMap<>();
             map.put("Email",Gmail);
             map.put("Password",Pass);
             map.put("Farmer_Name",etname);
             map.put("Sex",Sex);
             map.put("Age",etage);
             map.put("Village",etVillage);
             map.put("Taluka",ettaluka);
             map.put("District",etdistrict);
             map.put("State",etstate);
             map.put("Land_Ownership",Land_ownership);
             map.put("Farm_Land_Address",etaddress);
             map.put("Farm_Land_Area",farm_land_Area);
             map.put("Mobile_Number",etmobile);

          myref.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
          if (task.isSuccessful()){
              dialog.hide();
              Toasty.success(getApplicationContext(),"Profile updated successfully",Toasty.LENGTH_LONG,true).show();
          }
          else{
              dialog.dismiss();
              Toasty.error(getApplicationContext(),"Profile updated failed",Toasty.LENGTH_LONG,true).show();

          }

              }
          });

         }
     });
    }
}
