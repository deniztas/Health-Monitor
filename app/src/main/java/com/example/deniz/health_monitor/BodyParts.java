package com.example.deniz.health_monitor;

/**
 * Created by deniz on 16.05.2017.
 */
public class BodyParts {

    private int energy;
    private String status;

    public BodyParts() {
        this.energy = 100;
        this.status = "healthy";
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        if(this.energy > 100)
            this.energy = 100;
        if(this.energy < 1)
            this.energy = 0;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status;}

}
