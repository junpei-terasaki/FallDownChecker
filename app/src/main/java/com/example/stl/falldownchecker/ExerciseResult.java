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
    MyOpenHelper helper = new MyOpenHelper(this);
    SQLiteDatabase db = helper.getReadableDatabase();
    Cursor result_c = db.query("result", new String[] { "level", "training1" }, null,
            null, null, null, null);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_result);

        TextView textview = findViewById(R.id.textView);
        textview.setText(result_c.getString(0));

    }

    public void sendMail(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String send_address = null;


        Cursor person_c = db.query("person", new String[] { "name", "address" }, null,
                null, null, null, null);

        boolean mov = person_c.moveToFirst();
        while (mov) {
            //TextView textView = new TextView(this);
            //textView.setText(String.format("%s : %s", c.getString(0),
            //        c.getString(1)))
            send_address = person_c.getString(1);
            mov = person_c.moveToNext();
        }
        person_c.close();



        final String email = "tera0208soccer@gmail.com";
        final String password = "hknjky28";
        String body = "これがメールの本文になります";
        String subject = "診断結果\n\n危険度「" + person_c.getString(0) + "」でした。\n\nあなたが行ったトレーニング一覧\n\n" + result_c.getString(1) + "\n\n" + result_c.getString(2) + "\n\n" + result_c.getString(3) + "\n\n" + result_c.getString(4) + "\n\n" + result_c.getString(5);

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

            /* 添付ファイルをする場合はこれを使う
            final MimeBodyPart txtPart = new MimeBodyPart();
            txtPart.setText(body, "utf-8");

            final MimeBodyPart filePart = new MimeBodyPart();
            File file = new File("[添付ファイルパス]");
            FileDataSource fds = new FileDataSource(file);
            DataHandler data = new DataHandler(fds);
            filePart.setDataHandler(data);
            filePart.setFileName(MimeUtility.encodeWord("[メール本文の添付ファイル名]")); */


            //final Multipart mp = new MimeMultipart();
            //mp.addBodyPart(txtPart);
            //mp.addBodyPart(filePart); //添付ファイルをする場合はこれ
            //mimeMsg.setContent(mp);

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
