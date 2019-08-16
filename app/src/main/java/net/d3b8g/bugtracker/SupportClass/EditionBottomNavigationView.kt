package net.d3b8g.bugtracker.SupportClass

import android.content.Context
import android.graphics.*
import android.support.design.widget.BottomNavigationView
import android.util.AttributeSet

class EditionBottomNavigationView : BottomNavigationView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    lateinit var mPaint: Paint
    lateinit var mPath: Path

    override fun setOnFocusChangeListener(l: OnFocusChangeListener?) {
        super.setOnFocusChangeListener(l)

    }


    val CURVE_CIRCLE_RADIUS = 252/2

    var  mFirstStartPoint: Point = Point()
    var  mFirstEndPoint: Point = Point()
    var  mFirstCurveContorlPoint1: Point = Point()
    var  mFirstCurveContorPoint2: Point = Point()

    var  mSecondStartPoint: Point = Point()
    var  mSecondEndPoint: Point = Point()
    var  mSecondCurveContorlPoint1: Point = Point()
    var  mSecondCurveContorPoint2: Point = Point()

    var mNavWidth:Int=0
    var mNavHeight:Int=0


    private fun init() {
        mPath = Path()
        mPaint = Paint()
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.color = Color.parseColor("#5181B8")
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawPath(mPath,mPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mNavWidth = width
        mNavHeight = height

        mFirstStartPoint.set((mNavWidth / 2) - (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 5), 0)
        mFirstEndPoint.set(mNavWidth / 2, 110)
        mSecondStartPoint = mFirstEndPoint
        mSecondEndPoint.set((mNavWidth / 2) + (CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 5), 0)

        mFirstCurveContorlPoint1.set(mFirstStartPoint.x + CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 2), mFirstStartPoint.y)
        mFirstCurveContorPoint2.set(mFirstEndPoint.x - (CURVE_CIRCLE_RADIUS * 2) + CURVE_CIRCLE_RADIUS, mFirstEndPoint.y)

        mSecondCurveContorlPoint1.set(mSecondStartPoint.x + (CURVE_CIRCLE_RADIUS * 2) - CURVE_CIRCLE_RADIUS, mSecondStartPoint.y)
        mSecondCurveContorPoint2.set(mSecondEndPoint.x - (CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 2)), mSecondEndPoint.y)

        mPath.reset()
        mPath.moveTo(0f,0f)
        mPath.lineTo(mFirstStartPoint.x.toFloat(),mFirstStartPoint.y.toFloat())

        mPath.cubicTo(mFirstCurveContorlPoint1.x.toFloat(), mFirstCurveContorlPoint1.y.toFloat(),
                mFirstCurveContorPoint2.x.toFloat(),mFirstCurveContorPoint2.y.toFloat(),
                mFirstEndPoint.x.toFloat(),mFirstEndPoint.y.toFloat())

        mPath.cubicTo(mSecondCurveContorlPoint1.x.toFloat(), mSecondCurveContorlPoint1.y.toFloat(),
                mSecondCurveContorPoint2.x.toFloat(),mSecondCurveContorPoint2.y.toFloat(),
                mSecondEndPoint.x.toFloat(),mSecondEndPoint.y.toFloat())

        mPath.lineTo(mNavWidth.toFloat(),0f)
        mPath.lineTo(mNavWidth.toFloat(),mNavHeight.toFloat())
        mPath.lineTo(0f,mNavHeight.toFloat())
        mPath.close()
    }
}