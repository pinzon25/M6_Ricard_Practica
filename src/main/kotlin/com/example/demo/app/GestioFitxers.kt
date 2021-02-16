package com.example.demo.app

import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.media.AudioClip
import javafx.scene.media.MediaPlayer
import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import tornadofx.View
import tornadofx.*
import java.io.File
import java.io.FileInputStream

class GestioFitxers: View() {

    private val botoDesa: Button by fxid("Bt_BotoDesa")
    private val botoObre:Button by fxid("Bt_BotoObre")
    private val campDeText: TextArea by fxid("Ta_TextFitxer")
    private val campFitxerObrir: TextField?=null
    private val botoNetejaText: ImageView by fxid("Iv_NetejaCampText")
    override val root : AnchorPane by fxml()

    private val ef = arrayOf(FileChooser.ExtensionFilter("Document files (*.txt)", "*.txt"))

    init{
        botoDesa.action {
            reprodueixAudio()
            guardarfitxer()
        }

        botoObre.action {
            obreFitxer()
        }

        botoNetejaText.setOnMouseClicked {
            esborraText()
        }

    }

    private fun guardarfitxer() {

        val filechooser = FileChooser()
        filechooser.extensionFilters.addAll(FileChooser.ExtensionFilter("Texto", "*.txt"),
                                            FileChooser.ExtensionFilter("All Files", "*.*"))

        var file = filechooser.showSaveDialog(null)
        var contingutfitxer:String = campDeText.text.toString()
        try {
            file.printWriter().use { out ->
                out.println(contingutfitxer)
            }
        }catch(ex:IllegalStateException){
            println("No has desat el fitxer.")
        }

    }

   private fun obreFitxer(){
        var s:String=""
        val fn: List<File> = chooseFile("Escull el fitxer",ef, FileChooserMode.Single)

        if (fn.isNotEmpty()) {
            s = "${fn.first()}"
            campDeText.text= ""
            val file = File(s).inputStream().readBytes().toString(Charsets.UTF_8)
            campDeText.text = file
        }
    }

    private fun esborraText(){
        campDeText.text = ""
    }

    private fun reprodueixAudio(){
        val myAudioClip = AudioClip(GestioFitxers::class.java.getResource("/Audio/FF7save.mp3").toExternalForm())
        myAudioClip.play(1000.0)
    }


}




