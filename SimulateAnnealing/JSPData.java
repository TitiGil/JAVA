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
public class JSPData {
    
    //operation per job time spanding
    private double[][] data;
    private int[][] order; 
    private int jobSize;
    private int machineSize;

    public JSPData(double[][] data, int[][] order, int jobSize, int machineSize) {
        this.data = data;
        this.order = order;
        this.jobSize = jobSize;
        this.machineSize = machineSize;
    }

   

    public double getTime(int job , int operation) {
        return data[job][operation];
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public int[][] getOrder() {
        return order;
    }

    public void setOrder(int[][] order) {
        this.order = order;
    }

    public int getJobSize() {
        return jobSize;
    }

    public void setJobSize(int jobSize) {
        this.jobSize = jobSize;
    }

    public int getMachineSize() {
        return machineSize;
    }

    public void setMachineSize(int machineSize) {
        this.machineSize = machineSize;
    }

 
    
    
}
