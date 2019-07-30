package ru.skillbranch.devintensive.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.R
import kotlin.math.max

//Реализуй CustomView с названием класса CircleImageView и кастомными xml атрибутами
//cv_borderColor (цвет границы (format="color") по умолчанию white) и
//cv_borderWidth (ширина границы (format="dimension") по умолчанию 2dp).
//CircleImageView должна превращать установленное изображение в круглое изображение с цветной рамкой, у CircleImageView должны быть реализованы методы
//@Dimension getBorderWidth():Int,
//setBorderWidth(@Dimension dp:Int),
//getBorderColor():Int,
//setBorderColor(hex:String),
//setBorderColor(@ColorRes colorId: Int).
//Используй CircleImageView как ImageView для аватара пользователя (@id/iv_avatar)
// */
//
//-------------------------
//Канву под круг обрезаешь, отрисовываешь с картинкой, чтоб centerCrop применился
// и потом накладываешь бордер сверху. Толкьо учти, что 2dp  и просто 2 Int - это разные вещи. надо преобразовывать 2 в demension
//2*resources.displayMetrics.density - вот так в коде
//-------------------------

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    companion object {
        val DEFAULT_COLOR = Color.WHITE
        val DEFAULT_BORDER_WIDTH = 2
    }

    private var defaultBorderColor = DEFAULT_COLOR
    private var defaultBorderWidth = DEFAULT_BORDER_WIDTH * resources.displayMetrics.density

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)

            defaultBorderColor =
                a.getColor(R.styleable.CircleImageView_cv_borderColor, defaultBorderColor)

            defaultBorderWidth = a.getDimension(R.styleable.CircleImageView_cv_borderWidth, defaultBorderWidth)
            a.recycle()


        }
    }




    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {

        val halfWidth: Float = (width / 2).toFloat()
        val halfHeight: Float = (height / 2).toFloat()
        val radius = max(halfWidth, halfHeight)
        val path = Path().apply {
            addCircle(halfWidth, halfHeight, radius, Path.Direction.CCW)
        }

        canvas?.clipPath(path)
        super.onDraw(canvas)
        var paint = Paint()
        paint.color = defaultBorderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = defaultBorderWidth
        canvas?.drawCircle(halfWidth, halfHeight, radius, paint)





    }


    @Dimension
    fun getBorderWidth(): Int {
        return defaultBorderWidth.toInt()
    }

    fun setBorderWidth(@Dimension dp: Int) {
        defaultBorderWidth = dptoPx(dp).toFloat()
    }

    fun getBorderColor(): Int {
        return defaultBorderColor
    }

    fun setBorderColor(hex: String) {
        defaultBorderColor = Color.parseColor(hex)
    }

    fun setBorderColor(@ColorRes colorId: Int) {

        defaultBorderColor = ContextCompat.getColor(App.applicationContext(), colorId)
    }


    fun dptoPx(dp: Int): Int {
        var scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

//    fun pxtoDp(): Int {
//        var scale = resources.displayMetrics.density
//        return (px/scale + 0.5f).toInt()
//
//    }
}