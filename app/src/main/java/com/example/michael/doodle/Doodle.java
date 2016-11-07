package com.example.michael.doodle;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Doodle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle);

        SeekBar hueBar = (SeekBar) findViewById(R.id.HueBar);
        SeekBar brushBar = (SeekBar) findViewById(R.id.BrushBar);
        SeekBar opacityBar = (SeekBar) findViewById(R.id.OpacityBar);

        Button clearButton = (Button) findViewById(R.id.ButtonClear);
        Button undoButton = (Button) findViewById(R.id.ButtonUndo);
        Button redoButton = (Button) findViewById(R.id.ButtonRedo);
        Button leftButton = (Button) findViewById(R.id.RotateLeftButton);
        Button rightButton = (Button) findViewById(R.id.RotateRightButton);


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DoodleView doodle = (DoodleView) findViewById(R.id.DoodleView);
                doodle.clear();
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DoodleView doodle = (DoodleView) findViewById(R.id.DoodleView);
                doodle.undo();
            }
        });

        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DoodleView doodle = (DoodleView) findViewById(R.id.DoodleView);
                doodle.redo();
            }
        });

        hueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float val = (float) 360 * progress/100f;
                Drawable test = seekBar.getProgressDrawable();
                seekBar.setBackgroundColor(Color.HSVToColor(new float[] {val, 1f, 1f}));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int val = seekBar.getProgress();
                int hue = (int) (val / 100.0 * 360);

                DoodleView doodle = (DoodleView) findViewById(R.id.DoodleView);
                doodle.setColor(hue);
            }

        });

        brushBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int val = seekBar.getProgress();
                float width = (float) (val);

                DoodleView doodle = (DoodleView) findViewById(R.id.DoodleView);
                doodle.setWidth(width);
            }

        });

        opacityBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int val = seekBar.getProgress();
                int alpha = (int) (255 - val/100.0 * 255);

                DoodleView doodle = (DoodleView) findViewById(R.id.DoodleView);
                doodle.setOpacity(alpha);
            }

        });
    }
}
