package com.example.daniel.owi535;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Region;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button btnConeccion, btnMotor1A, btnMotor1B, btnMotor2A, btnMotor2B, btnMotor3A, btnMotor3B, btnMotor4A, btnMotor4B;
    ImageButton btnMotor5A, btnMotor5B, btnMotor6A, btnMotor6B;
    boolean estaPresionado = false;


    private static final int SOLICITAR_ACTIVACION_BLUETOOTH =1;
    private static final int SOLICITAR_CONEXION =2;

    ConnectedThread connectedThread;

    BluetoothAdapter mBluetoothAdapter= null;
    BluetoothDevice mDevice=null;
    BluetoothSocket mSocket= null;

    boolean coneccion=false;

    private static  String MAC= null;

    UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConeccion=(Button)findViewById(R.id.coneccion);

        btnMotor1A=(Button)findViewById(R.id.btnMotor1A);
        btnMotor1B=(Button)findViewById(R.id.btnMotor1B);

        btnMotor2A=(Button)findViewById(R.id.btnMotor2A);
        btnMotor2B=(Button)findViewById(R.id.btnMotor2B);

        btnMotor3A=(Button)findViewById(R.id.btnMotor3A);
        btnMotor3B=(Button)findViewById(R.id.btnMotor3B);

        btnMotor4A=(Button)findViewById(R.id.btnMotor4A);
        btnMotor4B=(Button)findViewById(R.id.btnMotor4B);

        btnMotor5A = (ImageButton) findViewById(R.id.btnMotor5A);
        btnMotor5B = (ImageButton) findViewById(R.id.btnMotor5B);

        btnMotor6A = (ImageButton) findViewById(R.id.btnMotor6A);
        btnMotor6B = (ImageButton) findViewById(R.id.btnMotor6B);



        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter==null)
        {
            Toast.makeText(getApplicationContext(),"Su dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        }
        else
        {
            if (!mBluetoothAdapter.isEnabled())
            {
                Intent activaBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(activaBluetooth, SOLICITAR_ACTIVACION_BLUETOOTH);
            }

            btnConeccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (coneccion)
                    {
                        //desconectar
                        try {
                            mSocket.close();
                            coneccion = false;
                            btnConeccion.setText("Conectar");
                            Toast.makeText(getApplicationContext(),"Bluetooth desconectado", Toast.LENGTH_LONG).show();

                        } catch (IOException e)
                        {
                            Toast.makeText(getApplicationContext(),"Error: "+e, Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        //conectar
                        Intent abreLista= new Intent(MainActivity.this, ListaDispositivos.class);
                        startActivityForResult(abreLista,SOLICITAR_CONEXION);

                    }

                }
            });

           btnMotor1A.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("A");
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("B");

                    }
                    return true;
                }

            });



            btnMotor1B.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("C");
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("B");

                    }
                    return true;
                }

            });

            //////////////////////////////////////////////////////////////////////////////////////////////////

            btnMotor2A.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("D");
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("E");

                    }
                    return true;
                }

            });



            btnMotor2B.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("F");
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("E");

                    }
                    return true;
                }

            });

            ////////////////////////////////////////////////////////////////////////////////////

            btnMotor3A.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("G");
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("H");

                    }
                    return true;
                }

            });



            btnMotor3B.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("I");
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("H");

                    }
                    return true;
                }

            });

            ////////////////////////////////////////////////////////////

            btnMotor4A.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("J");
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("K");

                    }
                    return true;
                }

            });



            btnMotor4B.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("L");
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("K");

                    }
                    return true;
                }

            });


            ////////////////////////////////////////////////////////////////////////////////

            btnMotor5A.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("M");
                                btnMotor5B.setImageResource(R.drawable.volante2izquierda);
                                btnMotor5A.setImageResource(R.drawable.volante1izquierda);



                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("N");
                            btnMotor5B.setImageResource(R.drawable.prueba2);
                            btnMotor5A.setImageResource(R.drawable.prueba);

                    }
                    return true;
                }

            });



            btnMotor5B.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("O");
                                btnMotor5B.setImageResource(R.drawable.volante2derecha);
                                btnMotor5A.setImageResource(R.drawable.volante1derecha);

                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("N");
                            btnMotor5B.setImageResource(R.drawable.prueba2);
                            btnMotor5A.setImageResource(R.drawable.prueba);


                    }
                    return true;
                }

            });


            /////////////////////////////////////////////////////////////////////////////////

            btnMotor6A.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("P");




                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("Q");


                    }
                    return true;
                }

            });



            btnMotor6B.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                connectedThread.Enviar("R");


                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            estaPresionado = false;
                            connectedThread.Enviar("Q");


                    }
                    return true;
                }

            });




        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case SOLICITAR_ACTIVACION_BLUETOOTH:
                if (resultCode== Activity.RESULT_OK)
                    {
                        Toast.makeText(getApplicationContext(),"Bluetooth activado", Toast.LENGTH_LONG).show();
                    }
                else
                    {
                        Toast.makeText(getApplicationContext(),"Bluetooth no fue activado", Toast.LENGTH_LONG).show();
                        finish();
                    }
            break;

            case SOLICITAR_CONEXION:
                if (resultCode == Activity.RESULT_OK)
                {
                    MAC = data.getExtras().getString(ListaDispositivos.DireccionMac);
                    mDevice = mBluetoothAdapter.getRemoteDevice(MAC);
                    try
                    {
                       mSocket = mDevice.createRfcommSocketToServiceRecord(mUUID);
                        mSocket.connect();
                        coneccion = true;
                        connectedThread = new ConnectedThread(mSocket);
                        connectedThread.start();
                        btnConeccion.setText("Desconectar");
                        Toast.makeText(getApplicationContext(),"Conectado", Toast.LENGTH_LONG).show();

                    }   catch (Exception e)
                    {
                        coneccion = false;
                        Toast.makeText(getApplicationContext(),"Error: "+ e, Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext()," Fallo al obtener dirección Mac",Toast.LENGTH_LONG).show();
                }



        }

    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {

            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

          /*  // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                   // mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                     //       .sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }*/
        }
        public void Enviar(String DtsEnviar) {
            byte[] magBuffer = DtsEnviar.getBytes();
            try {
                mmOutStream.write(magBuffer);
            } catch (IOException e) { }
        }
    }
}
