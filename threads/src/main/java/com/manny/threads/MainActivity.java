package com.manny.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button startButton;
    private Handler handler = new Handler();

    // Volatile means accessing the most recent value of the data instead of the catched Once.

    private volatile boolean stopThread = false;
    ThreadClass threadClass = new ThreadClass(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.button_start_thread);
    }

    public void startThread(View view) {

        stopThread = false;
        threadClass.start();

     //  RunnableClass runnableClass = new RunnableClass(16);
      //  new Thread(runnableClass).start();

    }

    public void stopThread(View view) {
              stopThread = true;
    }


    class ThreadClass extends Thread{

        int seconds;

        ThreadClass(int time){
            this.seconds = time;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < seconds; i++) {
                Log.d(TAG, "startThread: " + i);
                try {
                    if (i == (seconds/2)){

//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                               startButton.setText("50%");
//                            }
//                        });

//                        Handler newHandler = new Handler(Looper.getMainLooper());
//                        newHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                startButton.setText("50%");
//                            }
//                        });


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startButton.setText("50%");
                            }
                        });

//                        startButton.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                startButton.setText("50%");
//                            }
//                        });
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class RunnableClass implements Runnable{

        int secs;
        RunnableClass(int secs){
            this.secs = secs;
        }

        @Override
        public void run() {
            for (int i = 0; i < secs; i++) {

                if (stopThread){
                    return;
                }

                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}