package com.example.stl.falldownchecker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.VideoView;

/**
 * Created by STL on 2018/03/19.
 */

public class Exercise extends AppCompatActivity{
    String[] training = {
            "トレーニング1",
            "トレーニング2",
            "トレーニング3",
            "トレーニング4",
            "トレーニング5",
    };
    Integer exercise_num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues insertValues = new ContentValues();

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
        /*MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues insertValues = new ContentValues();

        training[exercise_num - 1] = null;
        insertValues.put("training" + exercise_num.toString(), training[exercise_num - 1]);
        db.insert("result", null, insertValues);*/
        exercise_num++;
        if(exercise_num == 6){
            Intent intent = new Intent(this, ExerciseResult.class);
            startActivity(intent);
        }
    }

    public void complete (View view){

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues insertValues = new ContentValues();

        insertValues.put("training" + exercise_num.toString(), training[exercise_num -1]);
        db.insert("result",null, insertValues);

        exercise_num++;
        if(exercise_num == 6){
            Intent intent = new Intent(this, ExerciseResult.class);
            startActivity(intent);
        }
    }
}
