package com.manny.memoryleak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private BackgroundThread backgroundThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.click_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backgroundThread != null){
                    finish();
                }

                backgroundThread = new BackgroundThread(MainActivity.this);
                backgroundThread.execute();
            }
        });
    }



   private class BackgroundThread extends AsyncTask<Void, Void, Void>{

        private Context context;

       public BackgroundThread(Context context){
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_background);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}