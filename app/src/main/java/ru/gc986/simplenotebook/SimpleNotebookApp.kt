package ru.gc986.simplenotebook

import android.app.Application
import ru.gc986.simplenotebook.di.v.DIPres
import ru.gc986.simplenotebook.di.v.DIPresModule
import ru.gc986.simplenotebook.di.p.DIData
import ru.gc986.simplenotebook.di.p.DIDataModule
import ru.gc986.simplenotebook.di.p.DaggerDIData
import ru.gc986.simplenotebook.di.v.DaggerDIPres

class SimpleNotebookApp: Application() {

    companion object{
        lateinit var instance: SimpleNotebookApp
        internal lateinit var diData: DIData
        internal lateinit var diPres: DIPres

        var showDebugInfo = true
    }

    init {
        instance = this
        diData = buildData()
        diPres = buildPres()
    }

    private fun buildData(): DIData {
        return DaggerDIData.builder()
            .dIDataModule(DIDataModule(this)).build()
    }

    private fun buildPres(): DIPres {
        return DaggerDIPres.builder()
            .dIPresModule(DIPresModule()).build()
    }

}