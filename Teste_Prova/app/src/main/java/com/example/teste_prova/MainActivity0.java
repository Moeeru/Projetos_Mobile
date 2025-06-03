package com.example.teste_prova;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity0 extends AppCompatActivity {
    private RadioGroup grupoPergunta1;
    private RadioGroup grupoPergunta2;
    private RadioGroup grupoPergunta3;
    private Button botaoEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inicializaTela();
    }

    private void inicializaTela(){
        grupoPergunta1 = findViewById(R.id.Grupo_Pergunta1);
        grupoPergunta2 = findViewById(R.id.Grupo_Pergunta2);
        grupoPergunta3 = findViewById(R.id.Grupo_Pergunta3);
        botaoEnviar = findViewById(R.id.botaoEnviar);

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarResposta();
            }
        });
    }

    private String verificaRespostas(RadioGroup grupoPergunta) {
        int idOpcaoSelecionada = grupoPergunta.getCheckedRadioButtonId();
        RadioButton radioButtonSelecionado = findViewById(idOpcaoSelecionada);
        return radioButtonSelecionado.getText().toString();
    }

    private void enviarResposta() {
        String resposta1 = verificaRespostas(grupoPergunta1);
        String resposta2 = verificaRespostas(grupoPergunta2);
        String resposta3 = verificaRespostas(grupoPergunta3);

        Intent telaResultado = new Intent(this, TelaResultado.class);
        telaResultado.putExtra("resposta1", resposta1);
        telaResultado.putExtra("resposta2", resposta2);
        telaResultado.putExtra("resposta3", resposta3);
        startActivity(telaResultado);
    }
}
