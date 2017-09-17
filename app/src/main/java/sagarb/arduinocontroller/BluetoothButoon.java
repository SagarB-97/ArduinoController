package sagarb.arduinocontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Set;

public class BluetoothButoon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_butoon);
    }
    public void bluetoothbutton(View v) {
        if (GlobalVar.btAdapter == null) {
            Toast.makeText(this, "No Bluetooth Support in Device", Toast.LENGTH_LONG).show();
        } else {
            if (!GlobalVar.btAdapter.isEnabled()) {
                Intent enableBtth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableBtth);
            }
            Set<BluetoothDevice> pairedDevices = GlobalVar.btAdapter.getBondedDevices();
            BluetoothDevice btModule = null;
            final String ModuleName = "HC-05";
            int flag = 0;
            for (BluetoothDevice device : pairedDevices) {
                if (ModuleName.equals(device.getName())) {
                    btModule = device;
                    flag = 1;
                    break;
                }
            }
            if (flag == 0)
                Toast.makeText(this, "Bluetooth Module not Detected", Toast.LENGTH_SHORT).show();
            else if (flag == 1) {
                Toast.makeText(this, "Bluetooth Module Detected Successfully", Toast.LENGTH_SHORT).show();
                ConnectThread CT = new ConnectThread(this, btModule);
                if (GlobalVar.mmSocket != null) CT.start();
            }
        }
        while(true) {
            if(GlobalVar.error==0) {
                Intent intent = new Intent(this, Remote.class);
                startActivity(intent);
                break;
            }
        }
    }
}
