package ru.base.dataprovider.sharPref

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Observable

class SharedPreferencesHelperImpl(context: Context) : SharedPreferencesHelper {

    val PREF_NAME_SHARED_PREF_M_POS = "PREF_NAME_SHARED_PREF_M_POS"
    val context: Context
    val mSettings: SharedPreferences = context.getSharedPreferences(PREF_NAME_SHARED_PREF_M_POS, Context.MODE_PRIVATE)

    init {
        this.context = context
    }

    //body
    override fun putSPString(name: String, value: String): Observable<String> {
        return Observable.create {
            //            val mSettings: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = mSettings.edit()
            editor.putString(name, value)
            editor.apply()
            it.onNext(value)
            it.onComplete()
        }
    }

    override fun putSPInt(name: String, value: Int): Observable<Int> {
        return Observable.create {
            //            val mSettings: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = mSettings.edit()
            editor.putInt(name, value)
            editor.apply()

            it.onNext(value)
            it.onComplete()
        }
    }

    override fun putSPLong(name: String, value: Long): Observable<Long> {
        return Observable.create {
            //            val mSettings: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = mSettings.edit()
            editor.putLong(name, value)
            editor.apply()

            it.onNext(value)
            it.onComplete()
        }
    }

    override fun putSPBoolean(name: String, value: Boolean): Observable<Boolean> {
        return Observable.create {
            //            val mSettings: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = mSettings.edit()
            editor.putBoolean(name, value)
            editor.apply()

            it.onNext(value)
            it.onComplete()
        }
    }

    override fun getSPString(name: String): Observable<String> {
        return Observable.create {
            //            val mSettings: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            it.onNext(mSettings.getString(name, "")?:"")
            it.onComplete()
        }
    }

    override fun getSPInt(name: String): Observable<Int> {
        return Observable.create {
            //            val mSettings: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            it.onNext(mSettings.getInt(name, 0))
            it.onComplete()
        }
    }

    override fun getSPInt(name: String, defValue: Int): Observable<Int> = Observable.create {
        it.onNext(mSettings.getInt(name, defValue))
        it.onComplete()
    }

    override fun getSPLong(name: String): Observable<Long> {
        return Observable.create {
            //            val mSettings: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            it.onNext(mSettings.getLong(name, 0))
            it.onComplete()
        }
    }

    override fun getSPBoolean(name: String) = getSPBoolean(name, false)

    override fun getSPBoolean(name: String, defaultValue: Boolean): Observable<Boolean> =
        Observable.create {
            it.onNext(mSettings.getBoolean(name, defaultValue))
            it.onComplete()
        }
}