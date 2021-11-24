package com.codewithankit.myfarm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
  TextView farmername,sex,age,village,taluka,district,state,land_Ownership,farm_land_Address,farm_land_area,mobile_number,email,password;
  FirebaseDatabase db;
  DatabaseReference myref;
  Button editprofile;

  public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1=inflater.inflate(R.layout.fragment_profile, container, false);

        farmername=view1.findViewById(R.id.farmername);
        sex=view1.findViewById(R.id.sex);
        age=view1.findViewById(R.id.age);
        village=view1.findViewById(R.id.village);
        taluka=view1.findViewById(R.id.taluka);
        district=view1.findViewById(R.id.district);
        state=view1.findViewById(R.id.state);
        land_Ownership=view1.findViewById(R.id.land_Ownership);
        farm_land_Address=view1.findViewById(R.id.farm_land_Address);
        farm_land_area=view1.findViewById(R.id.farm_land_area);
        mobile_number=view1.findViewById(R.id.mobile_number);
        email=view1.findViewById(R.id.email);
        password=view1.findViewById(R.id.password);
        editprofile=view1.findViewById(R.id.editprofile);

        db=FirebaseDatabase.getInstance();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getUid();
        myref=db.getReference().child("Users").child(uid);
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name=snapshot.child("Farmer_Name").getValue(String.class);
                String Age=snapshot.child("Age").getValue(String.class);
                String District=snapshot.child("District").getValue(String.class);
                String Email=snapshot.child("Email").getValue(String.class);
                String Farm_Land_Address=snapshot.child("Farm_Land_Address").getValue(String.class);
                String Farm_Land_Area=snapshot.child("Farm_Land_Area").getValue(String.class);
                String Land_Ownership=snapshot.child("Land_Ownership").getValue(String.class);
                String Mobile_Number=snapshot.child("Mobile_Number").getValue(String.class);
                String Password=snapshot.child("Password").getValue(String.class);
                String Sex=snapshot.child("Sex").getValue(String.class);
                String State=snapshot.child("State").getValue(String.class);
                String Taluka=snapshot.child("Taluka").getValue(String.class);
                String Village=snapshot.child("Village").getValue(String.class);

                farmername.setText(Name);
                age.setText(Age);
                district.setText(District);
                email.setText(Email);
                farm_land_Address.setText(Farm_Land_Address);
                farm_land_area.setText(Farm_Land_Area);
                land_Ownership.setText(Land_Ownership);
                mobile_number.setText(Mobile_Number);
                password.setText(Password);
                sex.setText(Sex);
                state.setText(State);
                taluka.setText(Taluka);
                village.setText(Village);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toasty.error(getContext(),error.toString(),Toasty.LENGTH_LONG,true).show();
            }
        });

      editprofile.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(getContext(),editprofile.class);
              startActivity(intent);
          }
      });
    return view1;
    }
}
