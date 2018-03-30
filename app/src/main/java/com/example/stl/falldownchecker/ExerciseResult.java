package com.example.stl.falldownchecker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by STL on 2018/03/19.
 */

public class ExerciseResult extends AppCompatActivity{

    String level = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_result);

        MyOpenHelper helper = new MyOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Intent intent = getIntent();
        level = intent.getStringExtra("level");

        Cursor result_c = db.query("result", new String[] { "training1", "training2", "training3" ,"training4", "training5"}, null,
                null, null, null, null);
        Cursor mPerson_c= db.query("m_person", new String[] { "level" }, null,
                null, null, null, null);
        TextView textview = findViewById(R.id.textView);
        TextView training1 = findViewById(R.id.training1);
        TextView training2 = findViewById(R.id.training2);
        TextView training3 = findViewById(R.id.training3);
        TextView training4 = findViewById(R.id.training4);
        TextView training5 = findViewById(R.id.training5);

        while(mPerson_c.moveToNext()){
            textview.setText(mPerson_c.getString(0));
        }
        while(result_c.moveToNext()) {
            training1.setText(result_c.getString(0));
            training2.setText(result_c.getString(1));
            training3.setText(result_c.getString(2));
            training4.setText(result_c.getString(3));
            training5.setText(result_c.getString(4));
        }
        result_c.close();
        mPerson_c.close();

    }

    public void sendMail(View view) {
        MyOpenHelper helper = new MyOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor result_c = db.query("result", new String[] { "training1", "training2", "training3" ,"training4", "training5"}, null,
                null, null, null, null);
        Cursor person_c = db.query("person", new String[] { "name", "address" }, null,
                null, null, null, null);
        Cursor mPerson_c= db.query("m_person", new String[]{"level"}, null,
                null, null, null, null);
        result_c.moveToFirst();
        mPerson_c.moveToFirst();
        Intent intent = new Intent(this, MainActivity.class);
        String send_address = null;


        boolean mov = person_c.moveToFirst();
        while (mov) {
            send_address = person_c.getString(1);
            mov = person_c.moveToNext();
        }

        final String email = "tera0208soccer@gmail.com";
        final String password = "hknjky28";
        String body = "診断結果\n\n危険度「" + String.valueOf(mPerson_c.getString(0) ) + "」でした\n\nあなたが行ったトレーニング一覧\n\n" + result_c.getString(0) + "\n\n" + result_c.getString(1) + "\n\n" + result_c.getString(2) + "\n\n" + result_c.getString(3) + "\n\n" + result_c.getString(4);
        String subject = "診断結果";

        mPerson_c.close();
        person_c.close();
        result_c.close();
        db.close();
        try {

            //email と password更新
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            sp.edit().putString("email", email).commit();
            sp.edit().putString("password", password).commit();

            //以下メール送信
            final Properties property = new Properties();
            property.put("mail.smtp.host",                "smtp.gmail.com");
            property.put("mail.host",                     "smtp.gmail.com");
            property.put("mail.smtp.port", "587");
            property.put("mail.smtp.auth", "true");
            property.put("mail.smtp.starttls.enable", "true");

            // セッション
            final Session session = Session.getInstance(property, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });

            MimeMessage mimeMsg = new MimeMessage(session);

            mimeMsg.setSubject(subject, "utf-8");
            mimeMsg.setFrom(new InternetAddress(email));
            mimeMsg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(send_address));
            mimeMsg.setText(body,"utf-8");

            // メール送信する。
            final Transport transport = session.getTransport("smtp");
            transport.connect(email,password);
            transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
            transport.close();

            startActivity(intent);

        } catch (MessagingException e) {
            System.out.println("exception = " + e);

        } finally {
            System.out.println("finish sending email");
        }
    }
}
