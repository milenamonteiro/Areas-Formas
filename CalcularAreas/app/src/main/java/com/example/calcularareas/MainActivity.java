package com.example.calcularareas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView textView;
    private RelativeLayout relativeLayout;
    private EditText lado1, lado2;
    private Button button;
    private FormasGeometricas metodos = new FormasGeometricas();
    private int selec;
    private double valor1, valor2;
    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) this.findViewById(R.id.spinner);

        List<String> list = new ArrayList<>();
        list.add("Quadrado");
        list.add("Triângulo");
        list.add("Retângulo");
        list.add("Círculo");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                checarSelecionado(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void checarSelecionado(String selecionado){
        switch (selecionado){
            case "Quadrado":
                selec = 1;
                limparCriados();
                gerarCampo1();
                lado1.setHint("Lado");
                criarBotao();
                break;
            case "Triângulo":
                selec = 2;
                limparCriados();
                gerarCampo1();
                gerarCampo2();
                lado1.setHint("Base");
                lado2.setHint("Altura");
                criarBotao();
                break;
            case "Retângulo":
                selec = 3;
                limparCriados();
                gerarCampo1();
                gerarCampo2();
                lado1.setHint("Lado maior");
                lado2.setHint("Lado menor");
                criarBotao();
                break;
            case "Círculo":
                selec = 4;
                limparCriados();
                gerarCampo1();
                lado1.setHint("Raio");
                criarBotao();
                break;
        }
    }

    public void gerarCampo1(){
        relativeLayout = findViewById(R.id.layoute);

        lado1 = new EditText(this);
        lado1.setSingleLine(true);
        lado1.setId(R.id.lado1);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.spinner);
        params.setMargins(100, 20, 100, 0);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        lado1.setInputType(InputType.TYPE_CLASS_NUMBER);

        relativeLayout.addView(lado1, params);
    }

    public void gerarCampo2(){
        relativeLayout = findViewById(R.id.layoute);

        lado2 = new EditText(this);
        lado2.setSingleLine(true);
        lado2.setId(R.id.lado2);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.lado1);
        params.setMargins(100, 20, 100, 0);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        lado2.setInputType(InputType.TYPE_CLASS_NUMBER);

        relativeLayout.addView(lado2, params);
    }

    public void limparCriados(){
        relativeLayout = findViewById(R.id.layoute);
        relativeLayout.removeView(lado1);
        relativeLayout.removeView(lado2);
        relativeLayout.removeView(button);
        lado1 = null;
        lado2 = null;
    }

    public void criarBotao(){
        relativeLayout = findViewById(R.id.layoute);

        button = new Button(this);
        button.setId(R.id.botao);
        button.setText("Calcular");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        if(lado2 != null) {
            params.addRule(RelativeLayout.BELOW, R.id.lado2);
        }
        else {
            params.addRule(RelativeLayout.BELOW, R.id.lado1);
        }

        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        params.setMargins(0, 20, 0, 0);

        relativeLayout.addView(button, params);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                calcularArea();
            }
            });
    }

    public void calcularArea(){

        lado1 = findViewById(R.id.lado1);
        valor1 = Double.parseDouble(lado1.getText().toString());

        if(lado2!=null) {
            lado2 = findViewById(R.id.lado2);
            valor2 = Double.parseDouble(lado2.getText().toString());
        }

        switch(selec){
            case 1:
                resultado = ("Resultado: " + metodos.areaQuadrado(valor1));
                break;
            case 2:
                resultado = ("Resultado: " + metodos.areaTriangulo(valor1, valor2));
                break;
            case 3:
                resultado = ("Resultado: " + metodos.areaRetangulo(valor1, valor2));
                break;
            case 4:
                resultado = ("Resultado: " + metodos.areaCirculo(valor1));
                break;
        }

        Intent sendIntent = new Intent(MainActivity.this, ResultActivity.class);
        sendIntent.putExtra(Intent.EXTRA_TEXT, resultado);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
