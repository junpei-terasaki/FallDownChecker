package com.example.stl.falldownchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by STL on 2018/03/19.
 */

public class FallDownChecker extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.falldown_checker);


    }

    public void result (View view){
        Intent intent = new Intent(this, CheckResult.class);
        intent.putExtra("Point", point);
        startActivity(intent);
    }

    String str;
    Integer point = 0;
    public void check_point(View view){
        Boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            point += 1;
        } else{
            point -= 1;
        }

        str = String.valueOf(point);
        Log.d("点数は",str);

    }
}
