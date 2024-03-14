package juan.acosta.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var emailRegistro_Act: EditText
    lateinit var passwordRegistro_Act: EditText
    lateinit var passwordRegistro2_Act: EditText
    lateinit var btn_registrar_Act: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        emailRegistro_Act = findViewById(R.id.emailRegistro)
        passwordRegistro_Act = findViewById(R.id.passwordRegistro)
        passwordRegistro2_Act = findViewById(R.id.passwordRegistro2)
        btn_registrar_Act = findViewById(R.id.btn_registrar)

        auth = Firebase.auth

        btn_registrar_Act.setOnClickListener{
            var correo: String = emailRegistro_Act.text.toString()
            var contra: String = passwordRegistro_Act.text.toString()
            var contra2: String = passwordRegistro2_Act.text.toString()

            if(!correo.isNullOrEmpty() && !contra.isNullOrEmpty() && !contra2.isNullOrEmpty()){

                if(contra == contra2){

                    auth.createUserWithEmailAndPassword(correo, contra)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("exito", "createUserWithEmail:success")
                                Toast.makeText(
                                    baseContext,
                                    "Se ha registrado correctamente al usuario ${user?.email}.",
                                    Toast.LENGTH_SHORT,
                                ).show()

                                //updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("fallo", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext,
                                    "No se pudo registrar al usuario.",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                //updateUI(null)
                            }
                        }

                }else{
                    Toast.makeText(this, "ILas contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "Ingresa los datos faltantes", Toast.LENGTH_SHORT).show()
            }

        }
    }
}