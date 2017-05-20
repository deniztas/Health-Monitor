package com.example.deniz.health_monitor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by deniz on 20.05.2017.
 */

public class MainActivity extends AppCompatActivity {

    private BodyParts eyeObject;     //variables
    private BodyParts armObject;
    private BodyParts brainObject;
    private BodyParts legObject;
    private BodyParts heartObject;
    private TextView eye_energy;
    private TextView arm_energy;
    private TextView leg_energy;
    private TextView brain_energy;
    private TextView heart_energy;
    private TextView second;
    private TextView timer;

    private Button joggingID;
    private Button cyclingID;
    private Button footballID;
    private Button courseID;
    private Button musicID;
    private Button cinemaID;
    private Button eatingID;
    private Button sleepingID;

    private ImageView armImage;
    private ImageView legImage;
    private ImageView brainImage;
    private ImageView eyeImage;
    private ImageView heartImage;
    private File file;

    private Thread thread;
    private int seekTime=0;
    private int timeCount=0;
    private int count =0;
    private boolean isClicked = false;

    SeekBar timeBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        armObject = new Arm();
        eyeObject = new Eye();
        brainObject = new Brain();
        legObject = new Leg();
        heartObject= new Heart();

        joggingID = (Button) findViewById(R.id.joggingID);
        cyclingID = (Button) findViewById(R.id.cyclingID);
        footballID = (Button) findViewById(R.id.footballID);
        courseID = (Button) findViewById(R.id.courseID);
        musicID = (Button) findViewById(R.id.musicID);
        cinemaID = (Button) findViewById(R.id.cinemaID);
        eatingID = (Button) findViewById(R.id.eatingID);
        sleepingID = (Button) findViewById(R.id.sleepingID);

        heart_energy = (TextView) findViewById(R.id.heart_energy);
        eye_energy= (TextView) findViewById(R.id.eye_energy);
        arm_energy= (TextView) findViewById(R.id.arm_energy);
        leg_energy= (TextView) findViewById(R.id.leg_energy);
        brain_energy= (TextView) findViewById(R.id.brain_energy);
        second = (TextView) findViewById(R.id.secondID);
        timer = (TextView) findViewById(R.id.timerID);

        armImage = (ImageView) findViewById(R.id.armImage);
        legImage = (ImageView) findViewById(R.id.legImage);
        brainImage= (ImageView) findViewById(R.id.brainImage);
        eyeImage = (ImageView) findViewById(R.id.eyeImage);
        heartImage = (ImageView) findViewById(R.id.heartImage);

        timeBar = (SeekBar) findViewById(R.id.seekBarID);

        file = createFile();
        SeekBarActivate();
    }
    //create txt file
    public File createFile(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},23
                );
            }
        }
        String filename ="Activities.txt";
        File root = new File(Environment.getExternalStorageDirectory(), "Notes");
        if (!root.exists()) {
            root.mkdirs();
        }

        File gpxfile = new File(root, filename);
        return gpxfile;
    }

    //write txt file
    public void appendFile(File file,String body){
        try
        {
            FileWriter writer = new FileWriter(file,true);
            BufferedWriter out = new BufferedWriter(writer);
            out.append(body+ "\n");
            out.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();

        }
    }

    //Disable Buttons
    public void disableButtons(){
        footballID.setEnabled(false);
        courseID.setEnabled(false);
        joggingID.setEnabled(false);
        sleepingID.setEnabled(false);
        cyclingID.setEnabled(false);
        eatingID.setEnabled(false);
        musicID.setEnabled(false);
        cinemaID.setEnabled(false);
    }

    //Enable Buttons
    private void enableButtons(){
        footballID.setEnabled(true);
        courseID.setEnabled(true);
        joggingID.setEnabled(true);
        sleepingID.setEnabled(true);
        cyclingID.setEnabled(true);
        eatingID.setEnabled(true);
        musicID.setEnabled(true);
        cinemaID.setEnabled(true);
    }

    //Changing organ's energy
    public void operation(String activity){
        if(activity.equals("Football")){
            ((Arm) armObject).adjustEnergy("Football", armObject.getEnergy());
            ((Leg) legObject).adjustEnergy("Football", legObject.getEnergy());
            ((Heart) heartObject).adjustEnergy("Football", heartObject.getEnergy());
        }
        else if (activity.equals("Jogging")){
            ((Arm) armObject).adjustEnergy("Jogging", armObject.getEnergy());
            ((Leg) legObject).adjustEnergy("Jogging", legObject.getEnergy());
            ((Heart) heartObject).adjustEnergy("Jogging", heartObject.getEnergy());
        }
        else if(activity.equals("Cycling")){
            ((Arm) armObject).adjustEnergy("Cycling", armObject.getEnergy());
            ((Leg) legObject).adjustEnergy("Cycling", legObject.getEnergy());
            ((Heart) heartObject).adjustEnergy("Cycling", heartObject.getEnergy());
        }
        else if(activity.equals("Course")){
            ((Brain) brainObject).adjustEnergy("Course", brainObject.getEnergy());
            ((Eye) eyeObject).adjustEnergy("Course", eyeObject.getEnergy());
        }
        else if(activity.equals("Music")){
            ((Brain) brainObject).adjustEnergy("Music", brainObject.getEnergy());
            ((Eye) eyeObject).adjustEnergy("Music", eyeObject.getEnergy());
        }
        else if(activity.equals("Cinema")){
            ((Brain) brainObject).adjustEnergy("Cinema", brainObject.getEnergy());
            ((Eye) eyeObject).adjustEnergy("Cinema", eyeObject.getEnergy());
        }
        else if(activity.equals("Eating")){
            ((Arm) armObject).adjustEnergy("Eating", armObject.getEnergy());
            ((Leg) legObject).adjustEnergy("Eating", legObject.getEnergy());
            ((Heart) heartObject).adjustEnergy("Eating", heartObject.getEnergy());
        }
        else{
            ((Arm) armObject).adjustEnergy("Sleeping", armObject.getEnergy());
            ((Leg) legObject).adjustEnergy("Sleeping", legObject.getEnergy());
            ((Heart) heartObject).adjustEnergy("Sleeping", heartObject.getEnergy());
        }
    }

    //Print screen energy and image
    private void printScreen(){
        changingImages();
        arm_energy.setText(armObject.getEnergy() + "");
        eye_energy.setText(eyeObject.getEnergy() + "");
        brain_energy.setText(brainObject.getEnergy() + "");
        leg_energy.setText(legObject.getEnergy() + "");
        heart_energy.setText(heartObject.getEnergy() + "");
        timer.setText(Integer.toString(timeCount)+ "");

    }

    //Click function and timer
    public void onClick(View v) {
        seekTime = 0;
        switch (v.getId()) {
            case R.id.footballID:
                isClicked = true;
                disableButtons();
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < count; i++) {
                            operation("Football");
                            timeCount++;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // This code will always run on the UI thread, therefore is safe to modify UI elements.
                                    seekTime++;
                                    if(seekTime == count){
                                        enableButtons();
                                    }
                                    printScreen();

                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                    }
                });
                thread.start();
                appendFile(file, (String) footballID.getText() +" ");
                //createFile((String) footballID.getText() +" ");

                break;

            case R.id.joggingID:
                disableButtons();
                isClicked = true;
                thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < count; i++) {

                            timeCount++;
                            operation("Jogging");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekTime++;
                                    if(seekTime == count){
                                        enableButtons();
                                    }
                                    printScreen();

                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    }
                });

                thread.start();
                appendFile(file, (String) joggingID.getText() +" ");

                break;

            case R.id.cyclingID:
                disableButtons();
                isClicked = true;
                thread= new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < count; i++) {

                            timeCount++;
                            operation("Cycling");


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekTime++;
                                    if(seekTime == count){
                                        enableButtons();
                                    }
                                    printScreen();


                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                });

                thread.start();
                appendFile(file, (String) cyclingID.getText() +" ");

                break;

            case R.id.courseID:
                disableButtons();
                isClicked = true;
                thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < count; i++) {
                            operation("Course");
                            timeCount++;


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekTime++;
                                    if(seekTime == count){
                                        enableButtons();
                                    }
                                    printScreen();
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
                appendFile(file, (String) courseID.getText() +" ");
                //createFile((String) courseID.getText() +" ");

                break;

            case R.id.musicID:
                disableButtons();
                isClicked = true;
                thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < count; i++) {

                            timeCount++;
                            operation("Music");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekTime++;
                                    if(seekTime == count){
                                        enableButtons();
                                    }
                                    printScreen();
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                });

                thread.start();
                appendFile(file, (String) musicID.getText() +" ");
                //createFile((String) musicID.getText() +" ");

                break;

            case R.id.cinemaID:
                disableButtons();
                isClicked = true;
                thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <count ; i++) {

                            timeCount++;
                            operation("Cinema");


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekTime++;
                                    if(seekTime == count){
                                        enableButtons();
                                    }
                                    printScreen();
                                }
                            });
                            try {

                                Thread.sleep(500);

                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                });

                thread.start();
                appendFile(file, (String) cinemaID.getText() +" ");

                break;


            case R.id.eatingID:
                disableButtons();
                isClicked = true;
                thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < count; i++) {

                            timeCount++;
                            operation("Eating");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekTime++;
                                    if(seekTime == count){
                                        enableButtons();
                                    }
                                    printScreen();
                                }
                            });
                            try {

                                Thread.sleep(500);

                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
                appendFile(file, (String) eatingID.getText() +" ");

                break;

            case R.id.sleepingID:
                disableButtons();
                isClicked = true;
                thread= new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < count; i++) {
                            timeCount++;
                            operation("Sleeping");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekTime++;
                                    if(seekTime == count){
                                        enableButtons();
                                    }
                                    printScreen();
                                }
                            });
                            try {

                                Thread.sleep(500);

                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
                appendFile(file, (String) sleepingID.getText() +" ");
                break;
        }

    }

    //changing image function
    public void changingImages(){

        if (armObject.getEnergy() >= 65) {
            armImage.setImageResource(R.drawable.arm_green);
        } else if (armObject.getEnergy() >= 35 && armObject.getEnergy() < 65) {
            armImage.setImageResource(R.drawable.arm_orange);
        } else {
            armImage.setImageResource(R.drawable.arm_red);
        }

        if (legObject.getEnergy() >= 65) {
            legImage.setImageResource(R.drawable.leg_green);
        } else if (legObject.getEnergy() >= 35 && legObject.getEnergy() < 65) {
            legImage.setImageResource(R.drawable.leg_orange);
        } else {
            legImage.setImageResource(R.drawable.leg_red);
        }

        if (heartObject.getEnergy() > 65) {
            heartImage.setImageResource(R.drawable.heart_green);
        } else if (heartObject.getEnergy() >= 35 && heartObject.getEnergy() < 65) {
            heartImage.setImageResource(R.drawable.heart_orange);
        } else {
            heartImage.setImageResource(R.drawable.heart_red);
        }

        if (brainObject.getEnergy() > 65) {
            brainImage.setImageResource(R.drawable.brain_green);
        } else if (brainObject.getEnergy() >= 35 && brainObject.getEnergy() < 65) {
            brainImage.setImageResource(R.drawable.brain_orange);
        } else {
            brainImage.setImageResource(R.drawable.brain_red);
        }

        if (eyeObject.getEnergy() >= 65) {
            eyeImage.setImageResource(R.drawable.eye_green);
        } else if (eyeObject.getEnergy() >= 35 && eyeObject.getEnergy() < 65) {
            eyeImage.setImageResource(R.drawable.eye_orange);
        } else {
            eyeImage.setImageResource(R.drawable.eye_red);
        }
    }

    //Seek bar for select time
    public void SeekBarActivate(){
        timeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekTime = seekBar.getProgress();
                count = seekTime;
                second.setText(seekTime + "");
                seekBar.setMax(101);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int seekTime = seekBar.getProgress();
                second.setText(seekTime + "");
                seekBar.setMax(100);
            }
        });
    }

    //Option menu for changing page
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.records){
            Intent intent = new Intent(MainActivity.this,RecordsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.values){
            Intent intent = new Intent(MainActivity.this,ValuesActivity.class);
            startActivity(intent);
        }
        if (id == R.id.exit) {
            finishAffinity();
            file.delete();
        }

        return super.onOptionsItemSelected(item);
    }
}
