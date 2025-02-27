package com.example.cadastro

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editViewNome: EditText
    private lateinit var editViewTelefone: EditText
    private lateinit var editViewEmail: EditText
    private lateinit var checkBox: CheckBox
    private lateinit var radioGroupSexo: RadioGroup
    private lateinit var editViewCidade: EditText
    private lateinit var spinnerUf: Spinner
    private lateinit var buttonSalvar: Button
    private lateinit var buttonLimpar: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editViewNome = findViewById(R.id.inputNome)
        editViewTelefone = findViewById(R.id.inputTelefone)
        editViewEmail = findViewById(R.id.inputEmail)
        checkBox = findViewById(R.id.chckListaEmails)
        radioGroupSexo = findViewById(R.id.rdGroupSexo)
        editViewCidade = findViewById(R.id.inputCidade)
        spinnerUf = findViewById(R.id.spinnerUf)
        buttonSalvar = findViewById(R.id.btnSalvar)
        buttonLimpar = findViewById(R.id.btnLimpar)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.estados,
            R.layout.spinner_item
        )

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        spinnerUf.adapter = adapter

        buttonSalvar.setOnClickListener {
            if(formularioValido()) {
                salvarFormulario()
            }
            else {
                Toast.makeText(this, "Preencha todos os campos antes de enviar o formulário", Toast.LENGTH_SHORT).show()
            }
        }

        buttonLimpar.setOnClickListener {
            limparFormulario()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }

    private fun formularioValido(): Boolean {
        return !editViewNome.text.isNullOrEmpty() &&
                !editViewEmail.text.isNullOrEmpty() &&
                !editViewTelefone.text.isNullOrEmpty() &&
                !editViewCidade.text.isNullOrEmpty() &&
                radioGroupSexo.checkedRadioButtonId != -1

    }

    private fun limparFormulario() {
        editViewNome.text.clear()
        editViewTelefone.text.clear()
        editViewEmail.text.clear()
        checkBox.isChecked = false
        radioGroupSexo.clearCheck()
        editViewCidade.text.clear()
        spinnerUf.setSelection(0)
    }

    private fun salvarFormulario() {

        val receberEmail = checkBox.isChecked
        val sexo = if (radioGroupSexo.checkedRadioButtonId == R.id.radioFem) "feminino" else "masculino"

        val formulario = Formulario(
            editViewNome.text.toString(),
            editViewTelefone.text.toString(),
            editViewEmail.text.toString(),
            receberEmail,
            sexo,
            editViewCidade.text.toString(),
            spinnerUf.selectedItem.toString()
        )

        Toast.makeText(this, formulario.toString(), Toast.LENGTH_SHORT).show()
    }
}