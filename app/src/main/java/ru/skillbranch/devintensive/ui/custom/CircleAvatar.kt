//package ru.skillbranch.devintensive.ui.custom
//
//import android.content.Context
//import android.content.res.Resources
//import android.graphics.*
//import android.text.TextPaint
//import android.util.AttributeSet
//import android.util.TypedValue
//import android.widget.ImageView
//import kotlinx.android.synthetic.main.activity_profile.view.*
//import ru.skillbranch.devintensive.R
//import ru.skillbranch.devintensive.utils.Utils
//import kotlin.math.max
//
//
// var backColor: Int = 0
//class CircleAvatar @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int = 0
//) : ImageView(context, attrs, defStyleAttr) {
//
//    lateinit var text: String
//    lateinit var rectF: RectF
//    lateinit var clipPath: Path
//    lateinit var paint: Paint
//    lateinit var textPaint: TextPaint
//    lateinit var borderPaint: Paint
//    lateinit var textBoundRect: Rect
//
//
//    companion object {
//        val DEFAULT_COLOR = Color.YELLOW
//        val DEFAULT_BORDER_WIDTH = 6
//
//    }
//    private var defaultBorderColor = DEFAULT_COLOR
//    private var defaultBorderWidth = DEFAULT_BORDER_WIDTH * resources.displayMetrics.density
//
//
//    init {
//        if (attrs != null) {
//            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleAvatar)
//
//            defaultBorderColor =a.getColor(R.styleable.CircleAvatar_cv_borderColor, defaultBorderColor)
//
//            defaultBorderWidth = a.getDimension(R.styleable.CircleAvatar_cv_borderWidth, defaultBorderWidth)
//
//            text = Utils.toInitials(et_first_name?.text.toString(), et_last_name?.text.toString())
//                .toString()
//            a.recycle()
//
//
//            paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//                color = backColor //color of Circle
//            }
//
//            textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
//                setColor(Color.WHITE)
//                textSize = 100f
//            }
//
//
//
//            borderPaint = Paint().apply {
//                color = defaultBorderColor
//                strokeWidth = defaultBorderWidth
//                style = Paint.Style.STROKE
//            }
//
//
//
//            rectF = RectF()
//            clipPath = Path()
//            textBoundRect = Rect()
//        }
//    }
//    fun updateAvatar(initials: String?, theme: Resources.Theme) {
//        var color = TypedValue()
//        theme.resolveAttribute(R.attr.colorAccent, color, true)
//        backColor = color.data
//
//        if (initials !=null) {
//            text = initials
//        } else {
//            text =""
//        }
//
//    }
//
//
//    override fun draw(canvas: Canvas?) {
//        val textWidth = textPaint.measureText(text) * 0.5f
//        val textBaseLineHeight = textPaint.fontMetrics.ascent * -0.4f
//        val halfWidth: Float = (width / 2).toFloat()
//        val halfHeight: Float = (height / 2).toFloat()
//        val radius = max(halfWidth, halfHeight)
//        val path = Path().apply {
//            addCircle(halfWidth, halfHeight, radius, Path.Direction.CCW)
//        }
//
//        canvas?.drawCircle(halfWidth, halfHeight, radius-defaultBorderWidth, paint)
//        canvas?.drawText(text, halfWidth - textWidth, halfHeight + textBaseLineHeight, textPaint)
//        canvas?.drawCircle(halfWidth, halfHeight, radius-defaultBorderWidth, borderPaint)
//        this.invalidate()
//        this.requestLayout()
//
//
//
////
//        super.draw(canvas)
//    }
//
//
//}
////
////    fun dptoPx(dp: Int): Int {
////        var scale = resources.displayMetrics.density
////        return (dp * scale + 0.5f).toInt()
////    }
////
////    fun pxtoDp(px: Int): Int {
////        var scale = resources.displayMetrics.density
////        return (px / scale + 0.5f).toInt()
////
////    }
//
//
////canvas.drawText(text, centerX - textWidth, centerY + textBaseLineHeight, textPaint);
//
////        paint.getTextBounds(text, 0, text.length, textBoundRect )
////        var mTextWidth = paint.measureText(text)
////        var mTextHeight = textBoundRect.height()
////        canvas?.drawText(text, halfWidth - (mTextWidth/2f),
////            halfHeight+(mTextHeight/2f), paint)
///*
//// Подсчитаем размер текста
//    mPaint.getTextBounds(text, 0, text.length(), mTextBoundRect);
//    //mTextWidth = textBounds.width();
//    // Используем measureText для измерения ширины
//    mTextWidth = mPaint.measureText(text);
//    mTextHeight = mTextBoundRect.height();
//
//    canvas.drawText(text,
//            centerX - (mTextWidth / 2f),
//            centerY + (mTextHeight /2f),
//            mPaint
//    );
// */
//
////    fun convertSpToPx(context: Context, sp: Int): Int {
////        return sp * context.resources.displayMetrics.scaledDensity.toInt()
////    }
//
//
//
//
//
//
//
//
//
