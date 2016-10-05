package nkeerthy.touch.com.touchcoordinates;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by naveen.keerthy on 10/5/16.
 */

public class TouchEventView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();
    private float xPos;
    private float yPos;
    public TouchEventView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String cords = String.format("(%.2f,%.2f)",xPos,yPos);
        canvas.drawText(cords, xPos, yPos, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        xPos = motionEvent.getX();
        yPos = motionEvent.getY();

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos,yPos);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos,yPos);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
}
