package com.example.health.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.health.R;

public class ContactActivity extends AppCompatActivity {
    ImageView icBack, ivEmail,ivPhone, ivMessage, ivLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        anhXa();

        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ivEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:leminhtrung@gmail.com?subject=" + "" + "&body=" + "body");
                intent.setData(data);
                startActivity(intent);
            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "0123456789";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:" +number));
                startActivity(intent);
            }
        });

        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", "0123456789");
                smsIntent.putExtra("sms_body","Hello =))");
                startActivity(smsIntent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/place/280+An+D.+V%C6%B0%C6%A1ng,+Ph%C6%B0%E1%BB%9Dng+4,+Qu%E1%BA%ADn+5,+Th%C3%A0nh+ph%E1%BB%91+H%E1%BB%93+Ch%C3%AD+Minh/@10.7613494,106.6800195,17z/data=!3m1!4b1!4m5!3m4!1s0x31752f1b888ab357:0xc469f6e800231314!8m2!3d10.7613494!4d106.6822082"));
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        icBack = findViewById(R.id.iv_back_support);
        ivEmail = findViewById(R.id.iv_email);
        ivPhone = findViewById(R.id.iv_phone);
        ivMessage = findViewById(R.id.iv_message);
        ivLocation = findViewById(R.id.iv_location);
    }
}