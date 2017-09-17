package sagarb.arduinocontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;

import java.io.OutputStream;

/**
 * Created by Dell on 22-07-2016.
 */
public class GlobalVar {
    static BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
    static public BluetoothSocket mmSocket=null;
    static public OutputStream outstream=null;
    static int error=1;
}
