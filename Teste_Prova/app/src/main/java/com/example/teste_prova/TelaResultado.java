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
        // Utilize o ID definido no layout XML para o TextView de resultado
        resultado = findViewById(R.id.textResultado);
        int notaFinal = calculaNota();

        if (notaFinal == 10) {
            resultado.setText("Parabéns, você tirou nota 10!");
        } else if (notaFinal == 5) {
            resultado.setText("Parabéns, você tirou nota 5!");
        } else {
            resultado.setText("Você é a vergonha da profissão.");
        }
    }

    private int calculaNota() {
        String resposta1 = getIntent().getStringExtra("resposta1");
        String resposta2 = getIntent().getStringExtra("resposta2");
        if (resposta1 != null && resposta2 != null) {
            if (resposta1.equals("Brasília") && resposta2.equals("Marte"))
                return 10;
            else if (resposta1.equals("Brasília") || resposta2.equals("Marte"))
                return 5;
        }
        return 0;
    }
}
