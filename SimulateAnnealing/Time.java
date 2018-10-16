/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package james;

/**
 *
 * @author AMG
 */
public class Time {
    
    private double  startTime;
    private double endTime;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        if(startTime<=0 )
            throw new IllegalArgumentException("The start time shoud be stinctly Positive");
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
              if(startTime<=0 )
            throw new IllegalArgumentException("The start time shoud be stinctly Positive");
    }

    public Time(double startTime, int id,double Time) {
     
        this.startTime = startTime;
        this.endTime = startTime+Time;
        this.id=id;
    }
    
}
