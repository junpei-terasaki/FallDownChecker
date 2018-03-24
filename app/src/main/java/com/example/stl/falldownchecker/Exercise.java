package com.example.stl.falldownchecker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.VideoView;

/**
 * Created by STL on 2018/03/19.
 */

public class Exercise extends AppCompatActivity{

    /*MyOpenHelper helper = new MyOpenHelper(this);
    final SQLiteDatabase db = helper.getWritableDatabase();
    ContentValues insertValues = new ContentValues();
    String[] training = {
            "トレーニング1",
            "トレーニング2",
            "トレーニング3",
            "トレーニング4",
            "トレーニング5",
    };
    Integer exercise_num = 1;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

        VideoView videoview = findViewById(R.id.videoView);
        videoview.setVideoPath("android.resource://" + this.getPackageName() + "/" + R.raw.video);
        videoview.start();
    }

    /*public void skip(View view){
        training[exercise_num - 1] = null;
        insertValues.put("training" + exercise_num.toString(), training[exercise_num - 1]);
        db.insert("result", null, insertValues);
        exercise_num++;
        if(exercise_num == 5){
            Intent intent = new Intent(this, ExerciseResult.class);
            startActivity(intent);
        }
    }

    public void complete (View view){

        insertValues.put("training" + exercise_num.toString(), training[exercise_num -1]);
        db.insert("result",null, insertValues);

        exercise_num++;
        if(exercise_num == 5){
            Intent intent = new Intent(this, ExerciseResult.class);
            startActivity(intent);
        }
    }*/
}
