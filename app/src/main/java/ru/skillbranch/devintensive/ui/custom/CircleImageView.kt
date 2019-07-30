package ru.skillbranch.devintensive.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
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
        val DEFAULT_BORDER_WIDTH = 201
    }

    private val paintBorder: Paint = Paint().apply { isAntiAlias = true }
    private var defaultBorderColor = DEFAULT_COLOR
    private var defaultBorderWidth = DEFAULT_BORDER_WIDTH * resources.displayMetrics.density

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)

            defaultBorderColor = a.getColor(R.styleable.CircleImageView_cv_bordercolor, defaultBorderColor)
            defaultBorderWidth = a.getDimension(R.styleable.CircleImageView_cv_borderWidth, defaultBorderWidth)
            a.recycle()


        }
    }

    var mStrokePaint = Paint().apply {
        color = defaultBorderColor
        style = Paint.Style.STROKE
        strokeWidth = defaultBorderWidth
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        val halfWidth: Float = width / 2f
        val halfHeight: Float = height / 2f
        val radius = max(halfWidth, halfHeight)
        val path = Path().apply {
            addCircle(halfWidth, halfHeight, radius, Path.Direction.CCW)
        }

        canvas?.clipPath(path)
        super.onDraw(canvas)
        //      val circleCenter = (112 - defaultBorderWidth * 2).toInt() / 2

        //  circleCenter = (heightCircle - borderWidth * 2).toInt() / 2

        //paintBorder.color = defaultBorderColor

        canvas?.drawOval(RectF(), mStrokePaint)

        //   canvas?.drawCircle(0f, 0f, defaultBorderWidth, paintBorder)

    }

    @Dimension
    fun getBorderWidth(): Int {
        return defaultBorderWidth.toInt()
    }

    fun setBorderWidth(@Dimension dp: Int) {
        defaultBorderWidth = dp.toFloat()
    }

    fun getBorderColor(): Int {
        return defaultBorderColor
    }

    fun setBorderColor(hex: String) {
        defaultBorderColor = Color.parseColor(hex)
    }

    fun setBorderColor(@ColorRes colorId: Int) {
        defaultBorderColor = colorId
    }
}