package sagarb.arduinocontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;


public class Remote extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    Sensor accelerometer;
    TextView display;
    long lastUpdate=0;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        display=(TextView)findViewById(R.id.TextData);
        img = (ImageView)findViewById(R.id.bord);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curtime = System.currentTimeMillis();
            if ((curtime - lastUpdate) > 100) {
                lastUpdate = curtime;
                int value = (int) Math.floor((event.values[1] + 8) / 2);
                display.setText("" + value);
                if (value < 4) {
                    img.setImageResource(R.drawable.bordinf);
                }
                if (value > 5) {
                    img.setImageResource(R.drawable.bordinr);
                }
                if(value==4)
                {
                    img.setImageResource(R.drawable.bordin);
                }
                if (GlobalVar.mmSocket != null && GlobalVar.outstream != null) {
                    String front = Integer.toString(value);
                    try {
                        GlobalVar.outstream.write(front.getBytes());
                    } catch (IOException io) {
                        Toast.makeText(this, "Unable to write", Toast.LENGTH_LONG).show();
                    }
                } else
                    Toast.makeText(this, "Signal not sent", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    /*public void frontSignal(View v)
    {
        if(GlobalVar.mmSocket!=null && GlobalVar.outstream!=null) {
            String front = "1";
            try {
                GlobalVar.outstream.write(front.getBytes());
            } catch (IOException io) {
                Toast.makeText(this, "Unable to write", Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(this,"Signal not sent",Toast.LENGTH_SHORT).show();
    }*/
    public void leftSignal(View v)
    {
        if(GlobalVar.mmSocket!=null && GlobalVar.outstream!=null) {
            String left = "a";
            try {
                GlobalVar.outstream.write(left.getBytes());
            } catch (IOException io) {
                Toast.makeText(this, "Unable to write", Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(this,"Signal not sent",Toast.LENGTH_LONG).show();
    }
    public void rightSignal(View v)
    {
        if(GlobalVar.mmSocket!=null && GlobalVar.outstream!=null) {
            String right = "b";
            try {
                GlobalVar.outstream.write(right.getBytes());
            } catch (IOException io) {
                Toast.makeText(this, "Unable to write", Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(this,"Signal not sent",Toast.LENGTH_SHORT).show();
    }
    /*public void stopSignal(View v)
    {
        if(GlobalVar.mmSocket!=null && GlobalVar.outstream!=null) {
            String stop = "0";
            try {
                GlobalVar.outstream.write(stop.getBytes());
            } catch (IOException io) {
                Toast.makeText(this, "Unable to write", Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(this,"Signal not sent",Toast.LENGTH_SHORT).show();
    }
    public void revSignal(View v)
    {
        if(GlobalVar.mmSocket!=null && GlobalVar.outstream!=null) {
            String rev = "4";
            try {
                GlobalVar.outstream.write(rev.getBytes());
            } catch (IOException io) {
                Toast.makeText(this, "Unable to write", Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(this,"Signal not sent",Toast.LENGTH_SHORT).show();
    }*/

}
