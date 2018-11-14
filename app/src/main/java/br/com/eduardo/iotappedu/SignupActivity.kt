package br.com.eduardo.iotappedu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        botaoCriarMinhaConta.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                    inputEmail.text.toString(),
                    inputSenha.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    gravaNoBancoDeDados()
                } else {
                    Toast.makeText(this,
                            "Erro",
                            Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun gravaNoBancoDeDados() {
        val usuario = Usuario(
                inputNome.text.toString(),
                inputTelefone.text.toString(),
                inputEmail.text.toString())
        FirebaseDatabase
                .getInstance()
                .getReference("Usuarios")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .setValue(usuario)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this,
                                "Sucesso",
                                Toast.LENGTH_LONG).show()

                        finish()

                    }
                }
    }
}




