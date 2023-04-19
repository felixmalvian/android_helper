import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import android.app.Activity
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


object Helper {
    private val param: MutableMap<String, Any> = HashMap()

    /*Passing Data*/
    fun getItemParam(key: String): Any? {
        return param[key]
    }

    fun setItemParam(key: String, `object`: Any) {
        param[key] = `object`
    }

    fun removeItemParam(key: String) {
        param.remove(key)
    }

    fun generatePictureFromBase64String(base64String: String): Bitmap{
        val decodedString: ByteArray = Base64.decode(base64String, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        return decodedByte
    }

    //    fun setDropdownList(view: EditText?, data: ArrayList<String>?){
    //        if (data != null){
    //            val actView = (view as? AutoCompleteTextView)
    //            if (context != null){
    //                val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_list, data)
    //                actView?.setAdapter(arrayAdapter)
    //            }
    //        }
    //    }

    fun View.setMargins(
        startMarginDp: Int? = null,
        topMarginDp: Int? = null,
        endMarginDp: Int? = null,
        bottomMarginDp: Int? = null
    ) {
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val params = layoutParams as ViewGroup.MarginLayoutParams
            startMarginDp?.run { params.leftMargin = this.dpToPx(context) }
            topMarginDp?.run { params.topMargin = this.dpToPx(context) }
            endMarginDp?.run { params.rightMargin = this.dpToPx(context) }
            bottomMarginDp?.run { params.bottomMargin = this.dpToPx(context) }
            requestLayout()
        }
    }

    private fun Int.dpToPx(context: Context): Int {
        val metrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
    }

    fun String.capitalize(): String{
        return this.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            AppCompatActivity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus?.windowToken,
                0
            )
        }
        activity.currentFocus?.clearFocus()
    }

    fun showErrorDialog(ctx: Context, message: String){
        AlertDialog.Builder(ctx)
            .setTitle("Error")
            .setMessage(message)
            .show()
    }


}