package com.example.stl.falldownchecker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

/**
 * Created by STL on 2018/03/19.
 */

public class Exercise extends AppCompatActivity{
    String[] training = {
            "踵おとし",
            "膝を伸ばした階段おり",
            "重心移動A",
            "重心移動B",
            "台姿勢バランス",
    };
    Integer exercise_num = 1;
    String num_null = null;
    String level = null;
    ContentValues Values = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

        /*Intent intent = getIntent();
        level = intent.getStringExtra("level");
        Values.put("level", level);
*/
        final VideoView videoview = findViewById(R.id.videoView);
        videoview.setVideoPath("android.resource://" + this.getPackageName() + "/" + R.raw.video2);
        videoview.start();
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });
    }

    public void skip(View view){
        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        Values.put("training" + String.valueOf(exercise_num), num_null);
        if(exercise_num == 5){
            db.insert("result", null, Values);
            Intent intent = new Intent(this, ExerciseResult.class);
            startActivity(intent);
        } else{
            exercise_num++;
        }
    }

    public void complete (View view){
        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        Values.put(("training" + String.valueOf(exercise_num)), training[exercise_num - 1]);
        if(exercise_num == 5){
            db.insert("result", null, Values);
            Intent intent = new Intent(this, ExerciseResult.class);
            startActivity(intent);
        }else{
            exercise_num++;
        }

    }
}
