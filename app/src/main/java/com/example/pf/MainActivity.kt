package com.example.juegoparejas
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.pf.R


class MainActivity : AppCompatActivity() {
    lateinit var iv_1_1: ImageView
    lateinit var iv_1_2: ImageView
    lateinit var iv_1_3: ImageView
    lateinit var iv_1_4: ImageView
    lateinit var iv_2_1: ImageView
    lateinit var iv_2_2: ImageView
    lateinit var iv_2_3: ImageView
    lateinit var iv_2_4: ImageView
    lateinit var iv_3_1: ImageView
    lateinit var iv_3_2: ImageView
    lateinit var iv_3_3: ImageView
    lateinit var iv_3_4: ImageView
    lateinit var tv_j1: TextView
    lateinit var tv_j2: TextView
    lateinit var ib_sonido: ImageButton
    lateinit var mp: MediaPlayer
    lateinit var mpFondo: MediaPlayer
    lateinit var imagen1: ImageView
    lateinit var imagen2: ImageView
    var imagenesArray = arrayOf(1_1, 1_2, 1_3, 1_4, 1_5, 1_6, 2_1, 2_2, 2_3, 2_4, 2_5, 2_6)
    var bulls = 0
    var dallas = 0
    var lakers = 0
    var milwauke = 0
    var portland = 0
    var timber = 0
    var turno = 1
    var puntosj1 = 0
    var puntosj2 = 0
    var numeroImagen = 1
    var escuchar = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enlazarGUI()
    }
    private fun enlazarGUI() {
        iv_1_1 = findViewById(R.id.iv_11)
        iv_1_2 = findViewById(R.id.iv_12)
        iv_1_3 = findViewById(R.id.iv_13)
        iv_1_4 = findViewById(R.id.iv_14)
        iv_2_1 = findViewById(R.id.iv_21)
        iv_2_2 = findViewById(R.id.iv_22)
        iv_2_3 = findViewById(R.id.iv_23)
        iv_2_4 = findViewById(R.id.iv_24)
        iv_3_1 = findViewById(R.id.iv_31)
        iv_3_2 = findViewById(R.id.iv_32)
        iv_3_3 = findViewById(R.id.iv_33)
        iv_3_4 = findViewById(R.id.iv_34)
        ib_sonido = findViewById(R.id.ib_sonido)
        ib_sonido.setColorFilter(Color.DKGRAY)
        sonido("background", true)
        iv_1_1.tag = "0"
        iv_1_2.tag = "1"
        iv_1_3.tag = "2"
        iv_1_4.tag = "3"
        iv_2_1.tag = "4"
        iv_2_2.tag = "5"
        iv_2_3.tag = "6"
        iv_2_4.tag = "7"
        iv_3_1.tag = "8"
        iv_3_2.tag = "9"
        iv_3_3.tag = "10"
        iv_3_4.tag = "11"
        bulls = R.drawable.bulls
        dallas = R.drawable.dallas
        lakers = R.drawable.lakers
        milwauke = R.drawable.milwauke
        portland = R.drawable.portland
        timber = R.drawable.timber
        imagenesArray.shuffle()
        tv_j1 = findViewById(R.id.tv_j1)
        tv_j2 = findViewById(R.id.tv_j2)
        tv_j1.setTextColor(Color.GRAY)
        tv_j2.setTextColor(Color.WHITE)
    }
    private fun sonido(sonidoN: String, repetir: Boolean = false) {
        val resID = resources.getIdentifier(sonidoN, "raw", packageName)
        if (sonidoN == "background") {
            mpFondo = MediaPlayer.create(this, resID)
            mpFondo.isLooping = repetir
            mpFondo.setVolume(0.5F, 0.5F)
            if (!mpFondo.isPlaying) {
                mpFondo.start()
            }
        } else {
            mp = MediaPlayer.create(this, resID)
            mp.setOnCompletionListener(MediaPlayer.OnCompletionListener { mediaPlayer ->
                mediaPlayer.stop()
                mediaPlayer.release()
            })
            if (!mp.isPlaying) {
                mp.start()
            }
        }
    }
    fun musicaFondo(v: View) {
        if (escuchar) {
            mpFondo.pause()
            ib_sonido.setImageResource(R.drawable.ic_volume_off)
            ib_sonido.setColorFilter((Color.DKGRAY))
        } else {
            mpFondo.start()
            ib_sonido.setImageResource(R.drawable.ic_volumen_on)
            ib_sonido.setColorFilter((Color.DKGRAY))
        }
        escuchar = !escuchar
    }
    fun seleccionar(imagen: View) {
        sonido("touch")
        verificar(imagen)
    }
    private fun verificar(imagen: View) {
        val tag = imagen.tag.toString().toInt()
        val iv = (imagen as ImageView)
        if (imagenesArray[tag] == 1_1) {
            iv.setImageResource(bulls)
        } else if (imagenesArray[tag] == 1_2) {
            iv.setImageResource(dallas)
        } else if (imagenesArray[tag] == 1_3) {
            iv.setImageResource(lakers)
        } else if (imagenesArray[tag] == 1_4) {
            iv.setImageResource(milwauke)
        } else if (imagenesArray[tag] == 1_5) {
            iv.setImageResource(portland)
        } else if (imagenesArray[tag] == 1_6) {
            iv.setImageResource(timber)
        } else if (imagenesArray[tag] == 2_1) {
            iv.setImageResource(bulls)
        } else if (imagenesArray[tag] == 2_2) {
            iv.setImageResource(dallas)
        } else if (imagenesArray[tag] == 2_3) {
            iv.setImageResource(lakers)
        } else if (imagenesArray[tag] == 2_4) {
            iv.setImageResource(milwauke)
        } else if (imagenesArray[tag] == 2_5) {
            iv.setImageResource(portland)
        } else if (imagenesArray[tag] == 2_6) {
            iv.setImageResource(timber)
        }
// guardar img
        if (numeroImagen == 1) {
            imagen1 = iv
            numeroImagen = 2
            iv.isEnabled = false
        } else if (numeroImagen == 2) {
            imagen2 = iv
            numeroImagen = 1
            iv.isEnabled = false
            deshabilitarImagenes()
            val h = Handler(Looper.getMainLooper())
            h.postDelayed({ iguales() }, 1000)
        }
    }
    private fun iguales() {
        if (imagen1.drawable.constantState == imagen2.drawable.constantState) {
            sonido("success")
            if (turno == 1) {
                puntosj1++
                tv_j1.text = "J1: $puntosj1"
            } else if (turno == 2) {
                puntosj2++
                tv_j2.text = "J2: $puntosj2"
            }
            imagen1.isEnabled = false
            imagen2.isEnabled = false
            imagen1.tag = ""
            imagen2.tag = ""
        } else {
            sonido("no")
            imagen1.setImageResource(R.drawable.oculta)
            imagen2.setImageResource(R.drawable.oculta)
            if (turno == 1) {
                turno = 2
                tv_j1.setTextColor(Color.WHITE)
                tv_j2.setTextColor(Color.GRAY)
            } else if (turno == 2) {
                turno = 1
                tv_j1.setTextColor(Color.GRAY)
                tv_j2.setTextColor(Color.WHITE)
            }
        }
        iv_1_1.isEnabled = !iv_1_1.tag.toString().isEmpty()
        iv_1_2.isEnabled = !iv_1_2.tag.toString().isEmpty()
        iv_1_3.isEnabled = !iv_1_3.tag.toString().isEmpty()
        iv_1_4.isEnabled = !iv_1_4.tag.toString().isEmpty()
        iv_2_1.isEnabled = !iv_2_1.tag.toString().isEmpty()
        iv_2_2.isEnabled = !iv_2_2.tag.toString().isEmpty()
        iv_2_3.isEnabled = !iv_2_3.tag.toString().isEmpty()
        iv_2_4.isEnabled = !iv_2_4.tag.toString().isEmpty()
        iv_3_1.isEnabled = !iv_3_1.tag.toString().isEmpty()
        iv_3_2.isEnabled = !iv_3_2.tag.toString().isEmpty()
        iv_3_3.isEnabled = !iv_3_3.tag.toString().isEmpty()
        iv_3_4.isEnabled = !iv_3_4.tag.toString().isEmpty()
        verificarFinJuego()
    }
    private fun liberarAudio(){
        if(::mp.isInitialized && mp.isPlaying){
            mp.stop()
            mp.release()
        }
        if(::mpFondo.isInitialized && mpFondo.isPlaying){
            mpFondo.stop()
            mpFondo.release()
        }
    }
    private fun reiniciarJuego(){
        liberarAudio()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun verificarFinJuego() {
        if (iv_1_1.tag.toString().isEmpty() && iv_1_2.tag.toString()
                .isEmpty() && iv_1_3.tag.toString().isEmpty() && iv_1_4.tag.toString().isEmpty() &&
            iv_2_1.tag.toString().isEmpty() && iv_2_2.tag.toString()
                .isEmpty() && iv_2_3.tag.toString().isEmpty() && iv_2_4.tag.toString().isEmpty() &&
            iv_3_1.tag.toString().isEmpty() && iv_3_2.tag.toString()
                .isEmpty() && iv_3_3.tag.toString().isEmpty() && iv_3_4.tag.toString().isEmpty()
        ) {
            mp.stop()
            mp.release()
            sonido("win")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Fin del juego")
                .setMessage("Puntos \nJ1: " + puntosj1 + "\nJ2: " + puntosj2).setCancelable(false)
                .setPositiveButton("Jugar de nuevo",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        reiniciarJuego()
                    })
                .setNegativeButton("Salir", DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                })
            val ad = builder.create()
            ad.show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        liberarAudio()
    }
    private fun deshabilitarImagenes() {
        iv_1_1.isEnabled = false
        iv_1_2.isEnabled = false
        iv_1_3.isEnabled = false
        iv_1_4.isEnabled = false
        iv_2_1.isEnabled = false
        iv_2_2.isEnabled = false
        iv_2_3.isEnabled = false
        iv_2_4.isEnabled = false
        iv_3_1.isEnabled = false
        iv_3_2.isEnabled = false
        iv_3_3.isEnabled = false
        iv_3_4.isEnabled = false
    }
}