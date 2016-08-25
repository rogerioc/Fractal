package rogerio.com.fractal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class Screen extends View {
	Paint mPaint;
	int sizeH,sizeW,tam,quant=0,mov=0;
	RectF rect;
	float sW;
	float sH;
	float size;	
	float movX=0,movY=0;
	int mColor;
	private float valor;
	public Screen(Context context) {
		super(context);
		setFocusable(true);
		mPaint = new  Paint();
		mPaint.setStyle(Style.STROKE);
		mColor=0xFFFFFFFF;
		mPaint.setColor(mColor);
		tam=20;


	}

	@Override
	public void draw(Canvas canvas) {		
		super.draw(canvas);
		valor = size * quant;
		fractal(canvas,sW-(valor/2),sH-(valor/2),valor);

	}
	/*
	 * Fractal algorithm - 
	 */
	private void fractal(Canvas canvas,float  x, float y,float size){

		if(size<4) return;		
		
		drawQuad(canvas,x,y,size);	

		size/=2;	
		fractal(canvas,x-(size),y-(size),size);		
		fractal(canvas,x-(size),y+(size+size),size);
		fractal(canvas,x+(size+size),y-(size),size);
		fractal(canvas,x+(size+size),y+(size+size),size);


	}

	private void drawQuad(Canvas canvas,float x, float y,float size){
		rect = new RectF(x,y,x+size, y+size);
		mPaint.setColor(mColor);		
		canvas.drawRect(rect, mPaint);
	}

	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);	       
		sizeH=h;
		sizeW=w;
		sW=sizeW/2;
		sH=sizeH/2;
		size=tam;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if(event.getAction() == MotionEvent.ACTION_DOWN){
			movY=event.getX();
			
		}

		if(event.getAction()  == MotionEvent.ACTION_MOVE){
			mov++;
			if(mov>=10){

				if(movY>event.getY()){
					if(quant<10)
						quant+=1;
				}
				else if(movY<event.getY()) {
					if(quant>0)
						quant-=1;
				}
				

				mov=0;
			}
		}

		if(event.getAction() == MotionEvent.ACTION_UP){
			movY=0;
			
		}

		invalidate();
		return true;
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN)
		{
			switch(keyCode)
			{
			case KeyEvent.KEYCODE_DPAD_UP:
				quant+=1;
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				quant-=1;  	
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				mColor-=15; 
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				mColor+=15;
				break;
				
			}
		}
		invalidate();

		return super.onKeyDown(keyCode, event);


	}








}
