package com.example.deniz.health_monitor;

/**
 * Created by deniz on 16.05.2017.
 */
public class Eye extends BodyParts{


    public void adjustEnergy(String activity, int energy){   //Changing eye energy
        if(activity.equals("Cinema")){
            energy = energy - 3;
        }
        else if(activity.equals("Music")){
            energy = energy - 1;
        }
        else if(activity.equals("Course")){
            energy = energy - 2;
        }
        this.setEnergy(energy);
    }
}
