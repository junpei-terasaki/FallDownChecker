package com.example.stl.falldownchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by STL on 2018/03/19.
 */

public class FallDownChecker extends AppCompatActivity{
    TextView textView = findViewById(R.id.textView);
    String[] column = {
            "質問1.よくつまずくことがある",
            "質問2.めまいがおきることがある",
            "質問3.家の中に障害物がある",
            "質問4.タオルがきつく絞れない",
            "質問5.杖を使っている",
            "質問6.ひざが痛む",
            "質問7.横断歩道が青信号のうちに渡れない",
    };
    Integer point = 0;
    Integer column_num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.falldown_checker);
        textView.setText(column[column_num]);
    }

    public void check_yes(View view){
        textView.setText(column[column_num]);
        point++;
        column_num++;
        if(column_num == 6){
            Intent intent = new Intent(this, CheckResult.class);
            intent.putExtra("Point", point);
            startActivity(intent);
        }
    }

    public void check_no(View view){
        textView.setText(column[column_num]);
        point--;
        column_num++;
        if(column_num == 6){
            Intent intent = new Intent(this, CheckResult.class);
            intent.putExtra("Point", point);
            startActivity(intent);
        }
    }

}
