package com.example.atividade2

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        val etNome = findViewById<EditText>(R.id.editTextNome)
        val etSenha = findViewById<EditText>(R.id.editTextSenha)
        val btnOk = findViewById<Button>(R.id.buttonOk)

        btnOk.setOnClickListener {
            val nome = etNome.text.toString()
            val senha = etSenha.text.toString()

            if (nome == "admin" && senha == "admin") {
                Toast.makeText(this@LoginActivity, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@LoginActivity, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
