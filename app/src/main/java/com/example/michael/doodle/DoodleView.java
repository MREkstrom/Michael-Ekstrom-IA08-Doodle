package com.example.michael.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.util.AttributeSet;

import java.util.ArrayList;


/**
 * Created by Michael on 11/2/16.
 */

public class DoodleView extends View {


    private ArrayList<PathPaint> paths;
    private int currPath;

    private int _hue; //0 to 360
    private int _alpha; //0 to 255
    private float _width; //0 to anything, cap here is 100

    private class PathPaint{
        public PathPaint(){
            path = new Path();
            paint = new Paint();
        }

        public Path path;
        public Paint paint;
    }

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle){
        paths = new ArrayList<PathPaint>();
        currPath = 0;
        paths.add(new PathPaint());

        _hue = 0;
        _alpha = 255;
        _width = 0f;
        initPaint();
    }

    private void initPaint()
    {
        setColor(_hue);
        setOpacity(_alpha);
        setWidth(_width);
        paths.get(currPath).paint.setAntiAlias(true);
        paths.get(currPath).paint.setStyle(Paint.Style.STROKE);
        paths.get(currPath).paint.setStrokeCap(Paint.Cap.ROUND);
        paths.get(currPath).paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        System.out.println("Printing up to " + currPath);
        for (int i = 0; i <= currPath; i++)
            canvas.drawPath(paths.get(i).path, paths.get(i).paint);
    }

    public void setWidth(float width){
        _width = width;
        paths.get(currPath).paint.setStrokeWidth(width);
    }

    public void setColor(int hue) {
        _hue = hue;
        paths.get(currPath).paint.setColor(Color.HSVToColor(_alpha, new float[] {_hue, 1f, 1f}));
    }

    public void setOpacity(int alpha) {
        _alpha = alpha;
        paths.get(currPath).paint.setColor(Color.HSVToColor(_alpha, new float[] {_hue, 1f, 1f}));
    }

    public int getHue(){
        return _hue;
    }

    public void clear(){
        paths = new ArrayList<PathPaint>();
        currPath = 0;
        paths.add(new PathPaint());
        initPaint();
        invalidate();
    }

    public void undo(){
        if (currPath > 0)
            currPath--;

        invalidate();
    }

    public void redo(){
        if (currPath < paths.size()-1)
            currPath++;

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                //Move new path to index
                currPath++;
                paths.add(new PathPaint());
                initPaint(); //TODO THIS BREAKS IT

                paths.get(currPath).path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                //Update only one path
                paths.get(currPath).path.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                //Make new path, update currpath, ready for next one, clear redo options
                while (currPath+1 < paths.size() && paths.get(currPath+1) != null)
                    paths.remove(currPath+1);

                break;
        }

        invalidate();
        return true;
    }

}
