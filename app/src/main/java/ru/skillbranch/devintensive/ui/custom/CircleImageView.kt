//package ru.skillbranch.devintensive.ui.custom
//
//import android.content.Context
//import android.graphics.*
//import android.graphics.drawable.BitmapDrawable
//import android.graphics.drawable.Drawable
//import android.util.AttributeSet
//import android.widget.ImageView
//import androidx.annotation.ColorRes
//import androidx.annotation.Dimension
//import ru.skillbranch.devintensive.R
//
//
///*
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
//class CircleImageView @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int = 0
//) : ImageView(context, attrs, defStyleAttr) {
//
//    var mBitmapShader: Shader? = null
//
//    var mShaderMatrix: Matrix? = null
//
//    val mBitmapDrawBounds: RectF? = null
//    val mStrokeBounds: RectF? = null
//
//    var mBitmap: Bitmap? = null
//
//    val mBitmapPaint: Paint? = Paint(Paint.ANTI_ALIAS_FLAG)
//    val mStrokePaint: Paint? = Paint(Paint.ANTI_ALIAS_FLAG)
//
//    val mPressedPaint: Paint? = null
//    val mInitialized: Boolean = false
//    val mPressed: Boolean = false
//    val mHighlightEnable: Boolean = false
//
//
//    var strokeColor = Color.TRANSPARENT
//    var strokeWidth: Float = 0.0f
//    val highlightEnable = false
//
//
//    init {
//        if (attrs != null) {
//            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
//            strokeColor = a.getColor(R.styleable.CircleImageView_cv_bordercolor, Color.TRANSPARENT)
//            strokeWidth = a.getDimension(R.styleable.CircleImageView_cv_borderWidth, 0.0f)
//            a.recycle()
//
//            mStrokePaint?.apply {
//                color = strokeColor
//                style = Paint.Style.STROKE
//                strokeWidth = strokeWidth
//            }
//            setUpBitmap()
//
//
//        }
//    }
//
//
//    fun getBitmapFromDrawable(drawable: Drawable): Bitmap? {
//        if (drawable is BitmapDrawable) {
//            return drawable.bitmap
//        }
//
//        var bitmap = Bitmap.createBitmap(
//            drawable.intrinsicWidth,
//            drawable.intrinsicHeight,
//            Bitmap.Config.ARGB_8888
//        )
//        var canvas = Canvas(bitmap)
//        drawable.setBounds(0, 0, canvas.width, canvas.height)
//        drawable.draw(canvas)
//        return bitmap
//
//    }
//
//    fun setUpBitmap() {
////        if (!mInitialized) {
////            return
////        }
//
//        mBitmap = getBitmapFromDrawable(drawable)
//        if (mBitmap == null) {
//            return
//        }
//
//        mBitmapShader = BitmapShader(mBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
//        mBitmapPaint?.shader = mBitmapShader
//
//    }
//
//    override fun onDraw(canvas: Canvas?) {
//        drawBitmap(canvas)
//        drawStroke(canvas)
//        super.onDraw(canvas)
//    }
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        setMeasuredDimension(measuredWidth, measuredHeight)
//    }
//
//
//
//    fun drawStroke(canvas: Canvas?) {
//        if (mStrokePaint?.strokeWidth!! >0f) {
//            canvas?.drawOval(RectF(), mStrokePaint)
//        }
//
//    }
//
//    fun drawBitmap(canvas: Canvas?) {
//
//        canvas?.drawOval(RectF(), mBitmapPaint!!)
//
//    }
//
//
//
//
//    fun setBorderWidth(@Dimension dp: Int) {}
//
//
//    @Dimension
//    fun getBorderWidth(): Int {
//        return 1
//    }
//
//    fun setBorderColor(hex: String) {}
//
//    fun setBorderColor(@ColorRes colorId: Int) {
//
//    }
//
//}
//
//
