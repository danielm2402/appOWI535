package com.example.daniel.owi535;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by Daniel on 29/05/2017.
 */
public class ListaDispositivos extends ListActivity {

    private BluetoothAdapter mBluetoothAdapter2 = null;

    static String DireccionMac =null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluettoth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        mBluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosVinculados = mBluetoothAdapter2.getBondedDevices();

        if (dispositivosVinculados.size()>0)
        {
            for (BluetoothDevice dispositivo : dispositivosVinculados)
            {
                String nombreDispositivo = dispositivo.getName();
                String Mac = dispositivo.getAddress();
                ArrayBluettoth.add(nombreDispositivo +"\n"+Mac);
            }
        }
        setListAdapter(ArrayBluettoth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String informacionGeneral =((TextView) v).getText().toString();

        //Toast.makeText(getApplicationContext(),"Información general: "+informacionGeneral,Toast.LENGTH_LONG).show();
        String direccionMac = informacionGeneral.substring(informacionGeneral.length()-17);
        Intent retornarMac = new Intent();
        retornarMac.putExtra(DireccionMac, direccionMac);
        setResult(RESULT_OK, retornarMac);
        finish();
    }
}
