package com.haz.myapplication;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 4N0n1M0u53 on 16/07/2017.
 */

public class detail extends AppCompatActivity {
    TextView txtnama,txtket;
    ImageView imggambar;
    MediaPlayer player;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        player = new MediaPlayer();
        txtnama = (TextView)findViewById(R.id.textView4);
        txtket = (TextView)findViewById(R.id.textView5);
        imggambar = (ImageView)findViewById(R.id.imageView2);
        Intent intent = getIntent();
        txtnama.setText(intent.getStringExtra("nama"));
        txtket.setText(intent.getStringExtra("ket"));
        try
        {
            // get input stream
            InputStream ims = getAssets().open(intent.getStringExtra("url"));
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            imggambar.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
        }
    startSound("v.mp3");
    }


    private void startSound(String filename){
        try {
            AssetFileDescriptor afd = getAssets().openFd(filename);

            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.prepare();
            player.start();
        }catch (Exception e){

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
