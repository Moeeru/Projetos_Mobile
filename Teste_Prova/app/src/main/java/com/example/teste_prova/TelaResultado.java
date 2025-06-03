package com.example.teste_prova;

import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class TelaResultado extends AppCompatActivity {
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.tela_resultado);
        inicializaTelaResultado();
    }

    private void inicializaTelaResultado() {
        resultado = findViewById(R.id.textResultado);
        int notaFinal = calculaNota();


        if (notaFinal == 10) {
           resultado.setText("Parabéns, você tirou nota 10!");
        } else if (notaFinal == 6) {
            resultado.setText("Parabéns, você tirou nota 6!");
        }else if (notaFinal == 3) {
            resultado.setText("Parabéns, você tirou nota 3!");
        }else {
            resultado.setText("Você é a vergonha da profissão.");
        }
    }

    private int calculaNota() {
        String resposta1 = getIntent().getStringExtra("resposta1");
        String resposta2 = getIntent().getStringExtra("resposta2");
        String resposta3 = getIntent().getStringExtra("resposta3");
        if (resposta1 != null && resposta2 != null) {
            if (resposta1.equals("Falso") && resposta2.equals("Verdadeiro") && resposta3.equals("Falso"))
                return 10;
            else if ((resposta1.equals("Falso") && resposta2.equals("Verdadeiro"))||(resposta1.equals("Falso") && resposta3.equals("Falso")) || (resposta3.equals("Falso") && resposta2.equals("Verdadeiro")))
                return 6;
            else if (resposta1.equals("Falso") || resposta2.equals("Verdadeiro") || resposta3.equals("Falso"))
                return 3;
        }
        return 0;
    }
}
