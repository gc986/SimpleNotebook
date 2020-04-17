package ru.gc986.simplenotebook.p.common

interface CommonPres<T : CommonView>{

    fun setup(view: T)
    fun onDestroy()
    fun onPause()
    fun onStart()

}