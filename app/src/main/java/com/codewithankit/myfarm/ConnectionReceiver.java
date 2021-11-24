package com.codewithankit.myfarm;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import es.dmoral.toasty.Toasty;

public class ConnectionReceiver extends BroadcastReceiver {
    Context mcontext;
    @Override
    public void onReceive(Context context, Intent intent) {
     mcontext=context;
        if (isconnect(context)){
         Toasty.info(context,"Internet Connected",Toasty.LENGTH_LONG,true).show();
     }
     else{
      AlertDialog.Builder builder=new AlertDialog.Builder(mcontext);
      builder.setCancelable(false);
      builder.setIcon(R.drawable.wifi);
      builder.setMessage("Please check your internet connection");
      builder.setTitle("No internet connection");
            builder.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
          }
      });
      AlertDialog dialog=builder.create();
      dialog.show();

     }

    }


    public boolean isconnect(Context context){
      try{
          ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
          NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
          return (networkInfo !=null && networkInfo.isConnected());

      }catch (NullPointerException e){
          e.printStackTrace();
          return false;
      }

   }
}
