package sagarb.arduinocontroller;


import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class ConnectThread extends Thread {
    Context ct;
    public ConnectThread(Context ct,BluetoothDevice device) {
        BluetoothSocket tmp = null;
        try {
            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
        } catch (IOException e) {
            Toast.makeText(ct,"Bluetooth Socket not created",Toast.LENGTH_SHORT).show();}
        GlobalVar.mmSocket = tmp;
    }
    @Override
    public void run() {
        try {
            GlobalVar.mmSocket.connect();
        } catch (IOException connectException) {
            Toast.makeText(ct, "Unable to connect Socket", Toast.LENGTH_SHORT).show();
            return;
        }
        try
        {
            GlobalVar.outstream = GlobalVar.mmSocket.getOutputStream();
        }catch(IOException e){Toast.makeText(ct,"Unable to create Output Stream",Toast.LENGTH_SHORT).show();}
        if(GlobalVar.outstream!=null){
            GlobalVar.error=0;
        }
    }
}
