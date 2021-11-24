package com.codewithankit.myfarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.InputStream;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class homepage extends AppCompatActivity {
   DrawerLayout drawerlayout;
   BottomNavigationView bottomnavigation;
   NavigationView navigation;
   ActionBarDrawerToggle toggle;
   Toolbar hometoolbaar;
   FirebaseDatabase db,database;
   DatabaseReference myref,reference;
   Uri filepath;
   BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        broadcastReceiver=new ConnectionReceiver();
        Registernetwork();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Enter_Farm_dataFragment()).commit();

        hometoolbaar=findViewById(R.id.hometoolbar);
        setSupportActionBar(hometoolbaar);
        getSupportActionBar().setTitle("MyFarm");
        bottomnavigation=findViewById(R.id.bottomnavigation);
        drawerlayout=findViewById(R.id.drawerlayout);
        navigation=findViewById(R.id.navigation);
        toggle=new ActionBarDrawerToggle(this,drawerlayout,hometoolbaar,R.string.open,R.string.close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                switch (id){
                        case R.id.record:
                             Toasty.normal(getApplicationContext(),"Why to record Farm data?",Toasty.LENGTH_LONG).show();
                             break;
                        case R.id.inputcost:
                             Toasty.normal(getApplicationContext(),"Suggestions to reduce Input cost",Toasty.LENGTH_LONG).show();
                             break;
                        case R.id.about:
                             Toasty.normal(getApplicationContext(),"About MyFarm",Toasty.LENGTH_LONG).show();
                             break;
                        case R.id.faqs:
                             Toasty.normal(getApplicationContext(),"Faqs",Toasty.LENGTH_LONG).show();
                             break;
                        case R.id.privecy:
                             Toasty.normal(getApplicationContext(),"Privecy Policy",Toasty.LENGTH_LONG).show();
                             break;
                        case R.id.logout:
                            AlertDialog.Builder builder=new AlertDialog.Builder(homepage.this);
                            builder.setMessage("Are you sure want to logout");
                            builder.setTitle("Logout");
                            builder.setIcon(R.drawable.logout);
                            builder.setCancelable(false);
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                  dialog.dismiss();
                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    FirebaseAuth.getInstance().signOut();
                                    Toasty.success(getApplicationContext(),"Logout Successfully",Toasty.LENGTH_LONG,true).show();
                                    Intent intent=new Intent(homepage.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                            AlertDialog dialog=builder.create();
                            dialog.show();

                            break;
                }

                   drawerlayout.closeDrawer(GravityCompat.START);


                return false;
            }
        });

        View header=navigation.getHeaderView(0);
        TextView name=header.findViewById(R.id.name);
        TextView mobile=header.findViewById(R.id.mobile);
        CircleImageView camera=header.findViewById(R.id.camera);
        CircleImageView profile_image=header.findViewById(R.id.profile_image);
        db=FirebaseDatabase.getInstance();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getUid();
        myref=db.getReference().child("Users").child(uid);
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String Name=snapshot.child("Farmer_Name").getValue(String.class);
                String Mobile=snapshot.child("Mobile_Number").getValue(String.class);
                String image=snapshot.child("image").getValue(String.class);
                Glide.with(getApplicationContext()).load(image).placeholder(R.drawable.profile).into(profile_image);
                name.setText(Name);
                mobile.setText(Mobile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
             Toasty.error(getApplicationContext(),error.toString(),Toasty.LENGTH_LONG,true).show();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                               Intent intent=new Intent(Intent.ACTION_PICK);
                               intent.setType("image/*");
                               startActivityForResult(Intent.createChooser(intent,"Please select Image"),101);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                             permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        bottomnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment temp=null;

                int id=menuItem.getItemId();
                switch (id){
                        case R.id.farmdata:temp=new Enter_Farm_dataFragment();
                        break;
                        case R.id.reviewfarmdata:temp=new ReviewFragment();
                        break;
                        case R.id.Profile:temp=new ProfileFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,temp).commit();

                return true;
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101 && resultCode==RESULT_OK){

        filepath=data.getData();

            CropImage.activity(filepath)
                    .setAspectRatio(1,1)
                    .start(this);


        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                ProgressDialog dialog=new ProgressDialog(this);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setTitle("Image Uploading");
                dialog.show();

                FirebaseStorage storage=FirebaseStorage.getInstance();
                StorageReference storageReference=storage.getReference().child("Image").child(getfilename(filepath));
                storageReference.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                              database=FirebaseDatabase.getInstance();
                              FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                              String id=firebaseUser.getUid();
                              reference=database.getReference().child("Users").child(id);

                              reference.child("image").setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                  @Override
                                  public void onComplete(@NonNull Task<Void> task) {
                                      if (task.isSuccessful()){
                                          dialog.hide();
                                          Toasty.success(getApplicationContext(),"Image Uploading Successfully",Toasty.LENGTH_LONG,true).show();
                                      }
                                      else {
                                          dialog.dismiss();
                                          Toasty.error(getApplicationContext(),"Image not Uploading ",Toasty.LENGTH_LONG,true).show();
                                      }
                                  }
                              });
                            }
                        });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                     float percent=(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                     dialog.setMessage("Uploading:"+(int)percent+"%");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toasty.error(getApplicationContext(),e.toString(),Toasty.LENGTH_LONG,true).show();

                    }
                });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }



    }

    public String getfilename(Uri filepath) {
        String result = null;
        if (filepath.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(filepath, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = filepath.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
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
    protected void onDestroy() {
        super.onDestroy();
        unRegisternetwork();
    }
}
