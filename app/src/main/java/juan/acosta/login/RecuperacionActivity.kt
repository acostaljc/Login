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

class RecuperacionActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var emailRecuperacion_Act: EditText
    lateinit var btn_Recuperacion_Act: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)

        emailRecuperacion_Act = findViewById(R.id.emailRecuperacion)
        btn_Recuperacion_Act = findViewById(R.id.btn_Recuperacion)

        auth = Firebase.auth

        btn_Recuperacion_Act.setOnClickListener{
            var correo: String = emailRecuperacion_Act.text.toString()

            if(!correo.isNullOrEmpty()){
                auth.sendPasswordResetEmail(correo)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //Log.d("exito", "Email sent.")
                            Toast.makeText(this, "Se ha enviado un mensaje a su correo", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this, "No se pudo enviar el mensaje", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Ingresa un correo valido", Toast.LENGTH_SHORT).show()
            }
        }


    }
}