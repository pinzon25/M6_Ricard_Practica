package com.example.demo.app

import com.example.demo.view.MainView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.*

class MyApp: App(GestioFitxers::class, Styles::class)

val primaryView = GestioFitxers::class

fun start(stage: Stage) {
    stage.minHeight = 400.0
    stage.minWidth = 600.0
    start(stage)
}

fun main(args: Array<String>) {
    launch<MyApp>(args)
}