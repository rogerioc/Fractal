package rogerio.com.fractal

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View

class Screen(context: Context?) : View(context) {
    var mPaint: Paint
    var sizeH = 0
    var sizeW = 0
    var tam: Int
    var quant = 0
    var mov = 0
    var rect: RectF? = null
    var sW = 0f
    var sH = 0f
    var size = 0f
    var movX = 0f
    var movY = 0f
    var mColor: Int
    private var valor = 0f
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        valor = size * quant
        fractal(canvas, sW - valor / 2, sH - valor / 2, valor)
    }

    /*
	 * Fractal algorithm - 
	 */
    private fun fractal(canvas: Canvas, x: Float, y: Float, size: Float) {
        var size = size
        if (size < 4) return
        drawQuad(canvas, x, y, size)
        size /= 2f
        fractal(canvas, x - size, y - size, size)
        fractal(canvas, x - size, y + (size + size), size)
        fractal(canvas, x + (size + size), y - size, size)
        fractal(canvas, x + (size + size), y + (size + size), size)
    }

    private fun drawQuad(canvas: Canvas, x: Float, y: Float, size: Float) {
        rect = RectF(x, y, x + size, y + size)
        mPaint.color = mColor
        canvas.drawRect(rect, mPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        sizeH = h
        sizeW = w
        sW = (sizeW / 2).toFloat()
        sH = (sizeH / 2).toFloat()
        size = tam.toFloat()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            movY = event.x
        }
        if (event.action == MotionEvent.ACTION_MOVE) {
            mov++
            if (mov >= 10) {
                if (movY > event.y) {
                    if (quant < 10) quant += 1
                } else if (movY < event.y) {
                    if (quant > 0) quant -= 1
                }
                mov = 0
            }
        }
        if (event.action == MotionEvent.ACTION_UP) {
            movY = 0f
        }
        invalidate()
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_DPAD_UP -> quant += 1
                KeyEvent.KEYCODE_DPAD_DOWN -> quant -= 1
                KeyEvent.KEYCODE_DPAD_LEFT -> mColor -= 15
                KeyEvent.KEYCODE_DPAD_RIGHT -> mColor += 15
            }
        }
        invalidate()
        return super.onKeyDown(keyCode, event)
    }

    init {
        isFocusable = true
        mPaint = Paint()
        mPaint.style = Paint.Style.STROKE
        mColor = -0x1
        mPaint.color = mColor
        tam = 20
    }
}