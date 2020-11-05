package com.loopin.calltracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CallReceiver extends BroadcastReceiver {
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    File txtFile;
    @Override
    public void onReceive(Context context, Intent intent) {
        String outNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String outGoingNumber = outNumber.concat("----->"+date);
        Toast.makeText(context, "OutGoingNumber: "+outGoingNumber, Toast.LENGTH_SHORT).show();
        SmsManager smsManager = SmsManager.getDefault();
        File folder = new File(Environment.getExternalStorageDirectory(), "OutCall");
        txtFile = new File(folder, "out.txt");
        if (!folder.exists()){
            folder.mkdir();
            try {
                txtFile = new File(folder, "out.txt");
                fileOutputStream = context.openFileOutput("OutCall", Context.MODE_PRIVATE);
                fileOutputStream = new FileOutputStream(txtFile, true);
                fileOutputStream.write("\n".getBytes());
                fileOutputStream.write(outGoingNumber.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (folder.exists() && txtFile.exists()==false){
            txtFile = new File(folder, "out.txt");
            try {
                fileOutputStream = context.openFileOutput("OutCall", Context.MODE_PRIVATE);
                fileOutputStream = new FileOutputStream(txtFile, true);
                fileOutputStream.write(outGoingNumber.getBytes());
                fileOutputStream.write("\n".getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
                Toast.makeText(context, "New number added", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else if (folder.getName().equals("OutCall")&& txtFile.getName().equals("out.txt")){
            try {
                fileOutputStream = context.openFileOutput("OutCall", Context.MODE_PRIVATE);
                fileOutputStream = new FileOutputStream(txtFile, true);
                fileOutputStream.write(outGoingNumber.getBytes());
                fileOutputStream.write("\n".getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
                Toast.makeText(context, "New number added", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
             catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
