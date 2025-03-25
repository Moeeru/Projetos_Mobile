package com.example.aula5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela2);

        EditText nota1 = findViewById(R.id.editTextNota1);
        EditText nota2 = findViewById(R.id.editTextNota2);
        EditText Nome = findViewById(R.id.editTextNome);
        EditText Matricula = findViewById(R.id.editTextMatricula);
        Button botaoResultado = findViewById(R.id.buttonCalculo);
        TextView textoMedia = findViewById(R.id.textViewResultado);

        botaoResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nota1Informada = nota1.getText().toString();
                String nota2Informada = nota2.getText().toString();

                String NomeInformado = Nome.getText().toString();
                String MatriculaInformada = Matricula.getText().toString();

                float nota1Calc = Float.parseFloat(nota1Informada);
                float nota2Calc = Float.parseFloat(nota2Informada);

                double mediaFinal = (nota1Calc * 0.4) + (nota2Calc * 0.6);

                textoMedia.setText(String.format("A média do aluno(a) %s de matricula %s é : %.2f", NomeInformado, MatriculaInformada,mediaFinal));

            }
        });
    }
}
