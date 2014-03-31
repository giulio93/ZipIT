package com.zipit;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import android.R.raw;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    private final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter mBluetoothAdapter;
    private final BluetoothSerialService mChatService = null;
    private ConnectThread mConnectThread = null;
    private BluetoothDevice device = null;
    public static final String TOAST = "toast";
    private boolean sound_stop = false;
    private String address;
    private MediaPlayer mPlayer;
    private boolean armed_alarm = false;
    private ToggleButton tgbutton;
       //   private ToggleButton toggle = (ToggleButton) findViewById(R.id.togglebutton);
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn = (Button) findViewById(R.id.btn_start);
        tgbutton = (ToggleButton) findViewById(R.id.togglebutton);
        // Button btn_stop = (Button) findViewById(R.id.send)
       
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            Toast.makeText(this, R.string.error_bluetooth_not_supported, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        }

        btn.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                // startSubActivity();
                startDeviceListActivity();
            }
        });

        
        tgbutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (tgbutton.isChecked()) {

                    armed_alarm = true;
                } else {

                    armed_alarm = false;
                }
            }
        });

//        btn_stop.setOnClickListener(new OnClickListener() {
//
//            public void onClick(View view) {
//
//                if (armed_alarm) {
//                    armed_alarm = false;
//                } else {
//                    armed_alarm = true;
//                }
////                try {
////                    mPlayer.release();
////                } catch (Exception e) {
////                    
////                }
//                //Sound(false);
//            }
//        });
    }

//    public void onToggleClicked(View view) {
//        // Is the toggle on?
//        boolean on = ((ToggleButton) view).isChecked();
//
//        if (on) {
//            armed_alarm = true;
//        } else {
//            armed_alarm = false;
//        }
//    }
    private void startSubActivity() {
        Intent intent = new Intent(this, SubActivity.class);
        intent.putExtra("address", address);
        startActivity(intent);
    }

    private void startDeviceListActivity() {
        Intent intent = new Intent(this, DeviceListActivity.class);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult " + resultCode);

        switch (requestCode) {
            case 1:

                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    // Get the device MAC address
                    address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
                    // Get the BLuetoothDevice object
                    device = mBluetoothAdapter.getRemoteDevice(address);

                    Toast.makeText(this, device.getName(), Toast.LENGTH_SHORT).show();

                    try {
                        // Attempt to connect to the device
                        mConnectThread = new ConnectThread(device, mHandler);
                        mConnectThread.start();
                        

                    } catch (Exception e) {

                        Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

    private void Sound(boolean play) {
        SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
//
        int iTmp = sp.load(this, R.raw.allarmenucleare, 1); // in 2nd param u have to pass your desire ringtone
//
        sp.play(iTmp, 1, 1, 0, 0, 1);

        mPlayer = MediaPlayer.create(this, R.raw.allarmenucleare);

        // in 2nd param u have to pass your desire ringtone
        //mPlayer.prepare();
        if (play) {
            mPlayer.start();
        } else {
            mPlayer.release();
        }

    }

    private final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            String readMessage = (String) msg.obj;
            switch (msg.what) {

                case 2:
                    Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.toString() + readMessage);
                    if (armed_alarm) {
                        tgbutton.setClickable(true);
                        tgbutton.setChecked(false);
                        armed_alarm = false;
                        Sound(true);
                        startSubActivity();
                    }
                    break;
                    
                case 1: tgbutton.setClickable(true);
                        tgbutton.setChecked(false);
                        armed_alarm = false;
                        Sound(true);
                        startSubActivity();

            }

        }

    };

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ActivityDemo", "Richiamato onRestart1()");

        try {
            mPlayer.release();
        } catch (Exception e) {
            Log.d(TAG, "Exception Crash" + e.getMessage());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ActivityDemo", "Richiamato onStart1()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityDemo", "Richiamato onResume1()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityDemo", "Richiamato onPause1()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityDemo", "Richiamato onStop1()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityDemo", "Richiamato onDestroy1()");
    }
}
