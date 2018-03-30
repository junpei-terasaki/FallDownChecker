package com.example.stl.falldownchecker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.VideoView;

/**
 * Created by STL on 2018/03/19.
 */

public class Exercise extends AppCompatActivity{
    String[] training = {
            "かかとおとし",
            "膝を伸ばした階段降り",
            "重心移動A",
            "重心移動B",
            "台姿勢バランス",
    };
    Integer exercise_num = 1;
    String num_null = "";
    String level = null;
    int video_num = 1;
    ContentValues Values = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

        /*Intent intent = getIntent();
        level = intent.getStringExtra("level");
        Values.put("level", level);
*/
        VideoView videoview = findViewById(R.id.videoView);
        videoview.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.video1));
        videoview.start();
    }

    public void skip(View view){
        VideoView videoview = findViewById(R.id.videoView);
        video_num++;
        switch (video_num){
            case 2:
                videoview.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.video2));
                videoview.start();
                break;
            case 3:
                videoview.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.video3));
                videoview.start();
                break;
            case 4:
                videoview.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.video4));
                videoview.start();
                break;
            case 5:
                videoview.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.video5));
                videoview.start();
                break;
        }
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
        VideoView videoview = findViewById(R.id.videoView);
        video_num++;
        switch (video_num){
            case 2:
                videoview.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.video2));
                videoview.start();
                break;
            case 3:
                videoview.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.video3));
                videoview.start();
                break;
            case 4:
                videoview.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.video4));
                videoview.start();
                break;
            case 5:
                videoview.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.video5));
                videoview.start();
                break;
        }

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
