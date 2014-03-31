/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zipit;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import static android.content.ContentValues.TAG;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 *
 * @author root
 */
public class SubActivity extends Activity {
    
//     private ConnectThread mConnectThread = null;
//     private BluetoothAdapter mBluetoothAdapter;
//     private BluetoothDevice device;
//     private  String  address;
    /**
     * Called when the activity is first created.
     */
             
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.sub);
        Button btn = (Button) findViewById(R.id.btn_allarme);
//        Button button = new Button(this);
//        button.setText("ALLARME");
//        button.setTextSize(35);
//  
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                            finish();
               
            }
        });
           //setContentView(button);
    }  

@Override
protected void onRestart() {
super.onRestart();
Log.i("ActivityDemo", "Richiamato onRestart()");
}
@Override
protected void onStart() {
super.onStart();
Log.i("ActivityDemo", "Richiamato onStart()");
}
@Override
protected void onResume() {
super.onResume();
Log.i("ActivityDemo", "Richiamato onResume()");

}
@Override
protected void onPause() {
super.onPause();
Log.i("ActivityDemo", "Richiamato onPause()");
}
@Override
protected void onStop() {
super.onStop();
Log.i("ActivityDemo", "Richiamato onStop()");
}
@Override
protected void onDestroy() {
super.onDestroy();
Log.i("ActivityDemo", "Richiamato onDestroy()");
}



        
        
        
//         try {
//                        Bundle extras = getIntent().getExtras();
//                         address = extras.getString("address");
//                        device = mBluetoothAdapter.getRemoteDevice(address);
//                        // Attempt to connect to the device
//                        mConnectThread = new ConnectThread(device, mHandler);
//                        mConnectThread.start();
//
//                    } catch (Exception e) {
//
//                        Toast.makeText(this,"Connection Falied", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(this,address, Toast.LENGTH_SHORT).show();
//                       // Toast.makeText(this, device.getName(), Toast.LENGTH_SHORT).show();
//                    }
     

        // ToDo add your GUI initialization code here        
    }
    
//        private void Sound() {
//            
//        SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
//        int iTmp = sp.load(this, R.raw.allarmenucleare, 1); // in 2nd param u have to pass your desire ringtone
//        sp.play(iTmp, 1, 1, 0, 0, 1);
//        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.allarmenucleare);// in 2nd param u have to pass your desire ringtone
//        //mPlayer.prepare();
//        
//            mPlayer.start();
//     
//
//    }
//    
//    private final Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            String readMessage = (String) msg.obj;
//            switch (msg.what) {
//
//                case 2:
//                    Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.toString() + readMessage);
//                    Sound();
//                    break;
//
//            }
//
//        }
//
//    };


