package com.ipn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.curso.R;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		SpecialView miVista = new SpecialView(this);
		setContentView(miVista);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int action=item.getItemId();
		switch(action)
		{
			case R.id.borrar:
				this.recreate();
				break;
		}
		return super.onOptionsItemSelected(item);
	}


	
	class SpecialView extends View {
		float x = 50;
		float y = 50;
		String accion = "Accion";
		String texto = "Evento";
		Path path = new Path();
		float radio=10;

		public SpecialView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			//canvas.drawColor(Color.rgb(255, 255, 150));
			canvas.drawColor(Color.WHITE); // color de fondo
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(6);
			paint.setColor(Color.MAGENTA);

			if (accion == "down") {
				path.moveTo(x, y);
				//path.addCircle(x,y,radio,Direction.CCW);

			}
			if (accion == "move") {
				//path.lineTo(x, y);
				path.addCircle(x,y,radio,Direction.CCW);
			}
			if(accion == "Dibujar"){
				path.addCircle(x,y,radio,Direction.CCW);

			}
			canvas.drawPath(path, paint);
			
			paint.setColor(Color.BLACK); 
			paint.setTextSize(20);
			paint.setStrokeWidth(2);
			canvas.drawText(texto, 100, 130, paint); canvas.drawText("x = " + x +
			 "  y = " + y, 100, 50, paint);
			//canvas.drawc
		}




		@Override
		public boolean onTouchEvent(MotionEvent evento){

			x = evento.getX();
			y = evento.getY();

			if (evento.getAction() == MotionEvent.ACTION_DOWN) {
				accion = "down";
				texto = "Action Down";
			}
			
			if (evento.getAction() == MotionEvent.ACTION_UP) { 
				texto = "Action Up";
				accion="Dibujar";

			 }

			if (evento.getAction() == MotionEvent.ACTION_MOVE) {
				accion = "move";
				texto = "Action Move"; 
			}
			invalidate();
			return true;
		}
	}
}
