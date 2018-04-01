package com.example.stl.falldownchecker;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by STL on 2018/03/22.
 */

public class Entry extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        final Cursor person_c = db.query("person", new String[] { "name", "address" }, null,
                null, null, null, null);

        final EditText nameText = (EditText) findViewById(R.id.editName);
        final EditText addressText = (EditText) findViewById(R.id.editaddress);

        Button entryButton = (Button) findViewById(R.id.insert);
        entryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String address = addressText.getText().toString();

                ContentValues insertValues = new ContentValues();
                insertValues.put("name", name);
                insertValues.put("address", address);
                long id = db.insert("person", name, insertValues);
                Toast.makeText(Entry.this, "登録しました",
                        Toast.LENGTH_SHORT).show();
                TextView textView = findViewById(R.id.textView7);
                while(person_c.moveToNext()) {
                    textView.setText(person_c.getString(0));
                }
            }
        });

        Button updateButton = (Button) findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String address = addressText.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(Entry.this, "名前を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues updateValues = new ContentValues();
                    updateValues.put("address", address);
                    db.update("person", updateValues, "name=?", new String[] { name });
                    Toast.makeText(Entry.this, "更新しました",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*Button deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String address = addressText.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(Entry.this, "名前を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    db.delete("person", "name=?", new String[] { name });
                    Toast.makeText(Entry.this, "削除しました",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        Button deleteAllButton = (Button) findViewById(R.id.deleteAll);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String address = addressText.getText().toString();
                db.delete("person", null, null);
                Toast.makeText(Entry.this, "削除しました",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


}
