package com.example.treino;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText descricao;
    private EditText valor;
    private Button salvar;
    private TextView informacoes;
    private MyDatabase db;

    // Acumula o somatório de todos os valores
    private int valorTotal = 0;

    // Histórico em memória de “nome + valor” de cada gasto
    private List<String> historicoItens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        descricao = findViewById(R.id.editTextDescricao);
        valor = findViewById(R.id.editTextValor);
        salvar = findViewById(R.id.buttonSalvar);
        informacoes = findViewById(R.id.textViewInformacoes);

        // Instância do Room — não remova este bloco, apenas ajuste conforme necessário
        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "meusgastos.db")
                // remova .allowMainThreadQueries() se você já estiver usando consultas assíncronas no DAO
                .allowMainThreadQueries()
                .build();

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 1) Captura os textos digitados
                final String nomeProduto = descricao.getText().toString().trim().toUpperCase();
                final String valorStr   = valor.getText().toString().trim();

                // 2) Validação básica
                if (nomeProduto.isEmpty() || valorStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencha nome e valor", Toast.LENGTH_SHORT).show();
                    return;
                }

                final int valorInt;
                try {
                    valorInt = Integer.parseInt(valorStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Valor inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 3) Atualiza o histórico em memória (nome + “R$ ” + valor)
                historicoItens.add(nomeProduto + " - R$ " + valorInt);

                // 4) Soma ao valorTotal
                valorTotal += valorInt;

                // 5) Insere no banco (Room) em thread de background
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Aqui usamos o construtor Gasto(int id, String descricao, float valor).
                        // Passamos id = 0 para que o Room gere automaticamente um novo PK.
                        Gasto novoGasto = new Gasto(
                                0,                             // id = 0 => Room autogerará
                                nomeProduto,                   // descrição
                                (float) valorInt               // valor (float)
                        );
                        db.gastoDAO().insert(novoGasto);

                        // Depois de inserir, voltamos para a UI thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 6) Limpa os campos de entrada
                                descricao.setText("");
                                valor.setText("");

                                // 7) Monta a String que exibe todo o histórico + total de itens + somatório
                                StringBuilder sb = new StringBuilder();
                                sb.append("Histórico de gastos:\n");
                                for (String item : historicoItens) {
                                    sb.append("• ").append(item).append("\n");
                                }
                                sb.append("\nTotal de itens: ").append(historicoItens.size()).append("\n");
                                sb.append("Somatório dos valores: R$ ").append(valorTotal);

                                // 8) Exibe tudo no TextView informacoes
                                informacoes.setText(sb.toString());

                                // 9) Toast de confirmação
                                Toast.makeText(MainActivity.this, "Gasto cadastrado", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
