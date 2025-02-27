package com.example.cadastro

class Formulario(
    private val Nome: String,
    private val telefone: String,
    private val email: String,
    private val ingressarEmail: Boolean,
    private val sexo: String,
    private val cidade: String,
    private val estado: String
) {
    override fun toString(): String {
        return "Formulario: " +
                "Nome = $Nome, " +
                "telefone = $telefone, " +
                "email = $email, " +
                "ingressarEmail = $ingressarEmail, " +
                "sexo = $sexo, " +
                "cidade = $cidade, " +
                "estado = $estado"
    }
}