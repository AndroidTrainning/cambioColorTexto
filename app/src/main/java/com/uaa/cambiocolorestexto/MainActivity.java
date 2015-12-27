package com.uaa.cambiocolorestexto;

import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //main_activity.xml
    private Button btnMain;
    private TextView txtViewSalida;
    private EditText inputMain;

    //radio_btns.xml
    private TextView txtViewLengFav;
    private RadioGroup rg;
    private RadioButton rb_netRed;
    private RadioButton rb_javaGreen;
    private RadioButton rb_cBlue;
    private Button btnAceptarRbtns;
    private int seleccion = -1;
    String mensaje = "Error en la selección";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.radio_btns);

        btnMain = (Button)findViewById(R.id.btnAceptarMain);
        txtViewSalida = (TextView) findViewById(R.id.txtViewSalida);
        inputMain = (EditText) findViewById(R.id.editTxtInput);

        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rb_netRed = (RadioButton) findViewById(R.id.rb_netRed);
        rb_javaGreen = (RadioButton) findViewById(R.id.rb_javaGreen);
        rb_cBlue = (RadioButton) findViewById(R.id.rb_cBlue);
        txtViewLengFav = (TextView) findViewById(R.id.textView);
        btnAceptarRbtns = (Button) findViewById(R.id.btnAceptarRadioBtns);

        /*btnMain.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String color;
                        color = inputMain.getText().toString();
                        coloreaTexto(color);
                    }
                }
        );*/

        rg.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        seleccion = checkedId;
                        switch (seleccion)
                        {
                            case R.id.rb_netRed:
                                txtViewLengFav.setTextColor(Color.RED);
                                mensaje = ".Net no es un lenguaje! ¬¬";
                                break;
                            case R.id.rb_javaGreen:
                                txtViewLengFav.setTextColor(Color.GREEN);
                                mensaje = "Java...the hut";
                                break;
                            case R.id.rb_cBlue:
                                txtViewLengFav.setTextColor(Color.BLUE);
                                mensaje = "C# rifa";
                                break;
                            default:
                                mensaje = "Error en la selección";
                                break;
                        }


                    }
                }
        );

        btnAceptarRbtns.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    private void coloreaTexto(String colorHex)
    {
        boolean ok;
        ok= validaTexto(colorHex);
        if(ok)
        {
            txtViewSalida.setText(colorHex);
            txtViewSalida.setTextColor(Color.parseColor(colorHex));
        }
        else
        {
            Toast.makeText(this,"Debe ser color hexadecimal. #123456",Toast.LENGTH_LONG).show();
        }
    }

    private boolean validaTexto(String colorHex)
    {
        boolean resp = false;
        if(colorHex != null && colorHex.length()==7)
        {
            Pattern pat = Pattern.compile("#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})");
            Matcher mat = pat.matcher(colorHex);
            if (mat.matches())
                resp = true;
            else
                resp = false;
        }
        return  resp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
