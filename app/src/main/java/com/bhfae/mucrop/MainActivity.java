package com.bhfae.mucrop;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.ucrop.UCrop;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_crop = (TextView)findViewById(R.id.tv_crop);
        tv_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri destinationUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/crop/" + "test1.jpg"));
                Uri sourceUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/crop/" + "source.jpg"));

                UCrop.of(sourceUri, destinationUri).withOverlay(true).withAspectRatio(16, 10).start(MainActivity.this);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            Toast.makeText(MainActivity.this,"resultUri",Toast.LENGTH_SHORT).show();
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Toast.makeText(MainActivity.this,cropError.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
