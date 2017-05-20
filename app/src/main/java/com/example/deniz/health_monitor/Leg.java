package com.example.deniz.health_monitor;

/**
 * Created by deniz on 16.05.2017.
 */
public class Leg extends BodyParts{

    public void adjustEnergy(String activity, int energy){ //Changing leg energy
        if(activity.equals("Football")){
            energy = energy - 5;
        }
        else if(activity.equals("Cycling")){
            energy= energy -4;
        }
        else if(activity.equals("Jogging")){
            energy= energy -3;
        }
        else if(activity.equals("Eating")){
            energy= energy + 3;
        }
        else if(activity.equals("Sleeping")){
            energy= energy + 4;
        }
        this.setEnergy(energy);
    }
}
