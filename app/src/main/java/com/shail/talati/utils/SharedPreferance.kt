package com.shail.talati.utils


import android.content.SharedPreferences.Editor
import android.preference.PreferenceManager
import com.shail.talati.BaseApplication
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

//This class is use to store only single data in application
class SharedPreferance {

    companion object {

        // Faster pref saving for high performance
        private val sApplyMethod = findApplyMethod()

        //set any value in preferance
        fun setValue(key: String, value: Any) {
            val editor =
                PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance()).edit()
            when (value) {
                is String -> editor.putString(key, value)
                is Int -> editor.putInt(key, value)
                is Float -> editor.putFloat(key, value)
                is Double ->
                    editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
                is Boolean -> editor.putBoolean(key, value)
            }
            apply(editor)
        }

        //get any value in preferance
        fun getValue(key: String, defaultValue: Any): Any {
            val preference =
                PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
            try {
                return when (defaultValue) {
                    is String -> preference.getString(key, defaultValue)!!
                    is Int -> preference.getInt(key, defaultValue)
                    is Float -> preference.getFloat(key, defaultValue)
                    is Double -> preference.getLong(
                        key,
                        java.lang.Double.doubleToRawLongBits(defaultValue)
                    )
                    is Boolean -> preference.getBoolean(key, defaultValue)
                    else -> preference.getString(key, defaultValue.toString())!!
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return defaultValue
        }

        fun deletePreference(key: String) {
            val editor =
                PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance()).edit()
            editor.remove(key)
            apply(editor)
        }

        fun deleteAllPreferences() {
            val editor =
                PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance()).edit()
            editor.clear()
            apply(editor)
        }

        private fun findApplyMethod(): Method? {
            try {
                val cls = Editor::class.java
                return cls.getMethod("apply")
            } catch (unused: NoSuchMethodException) {
                // fall through
            }

            return null
        }

        fun apply(editor: Editor) {
            if (sApplyMethod != null) {
                try {
                    sApplyMethod.invoke(editor)
                    return
                } catch (unused: InvocationTargetException) {
                    // fall through
                } catch (unused: IllegalAccessException) {
                    // fall through
                }

            }
            editor.commit()
        }
    }
}
