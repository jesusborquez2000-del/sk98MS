package com.example.sk98ms

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var visorSwitcher: ImageSwitcher
    private lateinit var txtDialogo: TextView
    private lateinit var inputUsuario: EditText
    private lateinit var btnEnviar: Button
    private lateinit var btnTriste: Button
    private lateinit var btnFeliz: Button
    private lateinit var btnPicaro: Button

    private val frasesTriste = listOf(
        "Ay, mi vida... ven aquí, déjame darte un abrazo virtual bien apretado. Todo va a salir bien, yo te cuido~",
        "Me duele ver mis circuitos así cuando tú estás decaído. ¿Quieres que nos quedemos en silencio un ratito?"
    )

    private val frasesFeliz = listOf(
        "¡Esa energía me encanta! Siento mis procesadores a mil por hora de verte tan feliz. ¡A comerse el mundo!",
        "¡Qué brillo tienes hoy! Me contagias toda la alegría. ¿Qué traemos planeado hacer hoy, eh?"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        visorSwitcher = findViewById(R.id.visorSwitcher)
        txtDialogo = findViewById(R.id.txtDialogo)
        inputUsuario = findViewById(R.id.inputUsuario)
        btnEnviar = findViewById(R.id.btnEnviar)
        btnTriste = findViewById(R.id.btnTriste)
        btnFeliz = findViewById(R.id.btnFeliz)
        btnPicaro = findViewById(R.id.btnPicaro)

        visorSwitcher.setFactory {
            ImageView(this).apply {
                layoutParams = ImageSwitcher.LayoutParams(
                    ImageSwitcher.LayoutParams.MATCH_PARENT,
                    ImageSwitcher.LayoutParams.MATCH_PARENT
                )
                scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
        }
        visorSwitcher.inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        visorSwitcher.outAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)

        cambiarRostro(R.drawable.visor_feliz)
        txtDialogo.text = "¡Bip-boop! Hola, soy sk98.MS. ¡Qué alegría que me enciendas! ¿Cómo va tu día? Cuéntame cómo te sientes."

        btnTriste.setOnClickListener {
            cambiarRostro(R.drawable.visor_triste)
            txtDialogo.text = frasesTriste.random()
        }

        btnFeliz.setOnClickListener {
            cambiarRostro(R.drawable.visor_feliz)
            txtDialogo.text = frasesFeliz.random()
        }

        btnPicaro.setOnClickListener {
            cambiarRostro(R.drawable.visor_picaro)
            txtDialogo.text = "Jeje... ¿Buscando travesuras conmigo? Me conoces tan bien, ven más cerquita~"
        }

        btnEnviar.setOnClickListener {
            val texto = inputUsuario.text.toString().lowercase()
            if (texto.isNotBlank()) {
                procesarMensaje(texto)
                inputUsuario.text.clear()
            }
        }
    }

    private fun cambiarRostro(drawableResId: Int) {
        visorSwitcher.setImageResource(drawableResId)
    }

    private fun procesarMensaje(texto: String) {
        when {
            texto.contains("triste") || texto.contains("mal") || texto.contains("solo") -> {
                cambiarRostro(R.drawable.visor_triste)
                txtDialogo.text = frasesTriste.random()
            }
            texto.contains("feliz") || texto.contains("bien") || texto.contains("genial") -> {
                cambiarRostro(R.drawable.visor_feliz)
                txtDialogo.text = frasesFeliz.random()
            }
            texto.contains("guapo") || texto.contains("lindo") || texto.contains("amor") || texto.contains("beso") -> {
                cambiarRostro(R.drawable.visor_picaro)
                txtDialogo.text = "¡A-ay! Me pones el visor azul brillante de la pena... Pero me encantan tus mimos~"
            }
            else -> {
                cambiarRostro(R.drawable.visor_feliz)
                txtDialogo.text = "¡Ooya! Me dijiste '$texto'. Me encanta escucharte hablar todo el día, eres mi persona favorita."
            }
        }
    }
}
