package juan.acosta.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var btn_ingresar_Act: Button
    lateinit var btn_registro_Act: Button
    lateinit var tv_recuperacion_Act: TextView
    lateinit var emailLogin_Act: TextView
    lateinit var passwordLogin_Act: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_ingresar_Act = findViewById(R.id.btn_ingresar)
        btn_registro_Act = findViewById(R.id.btn_registro)
        tv_recuperacion_Act = findViewById(R.id.tv_recuperacion)
        emailLogin_Act = findViewById(R.id.emailLogin)
        passwordLogin_Act = findViewById(R.id.passwordLogin)

        auth = Firebase.auth

        btn_registro_Act.setOnClickListener{
            var correo: String = emailLogin_Act.text.toString()
            var contra: String = passwordLogin_Act.text.toString()

            if(!correo.isNullOrEmpty() && !contra.isNullOrEmpty()){

                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                auth.signInWithEmailAndPassword(correo, contra)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d("exito", "signInWithEmail:success")
                            val user = auth.currentUser
                            var intent = Intent(this, RegistroActivity::class.java)
                            startActivity(intent)

                            //updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w("fallo", "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                this,
                                "Datos incorrectos.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            //updateUI(null)
                        }
                    }

            }else{
                Toast.makeText(this, "Ingresa los datos faltantes", Toast.LENGTH_SHORT).show()
            }



        }

        btn_ingresar_Act.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        tv_recuperacion_Act.setOnClickListener{
            var intent = Intent(this, RecuperacionActivity::class.java)
            startActivity(intent)
        }
    }
}