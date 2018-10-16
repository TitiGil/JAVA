/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package james;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author AMG
 */
public class Readfile {
    
    public static JSPData read(String filePath,Boolean Stick){
        try {
            FileReader fr=new FileReader(new File(filePath));
            BufferedReader br=new BufferedReader(fr);
             
            int job;
            int machine;
            double[][] data;
            int[][] order;
            String[] buffer;
           
            buffer=br.readLine().split(",");
              
            job=Integer.parseInt(buffer[0]);
             machine=Integer.parseInt(buffer[1]);
             data=new double[job][machine];
             order=new int[job][machine];
            
             if(Stick){
                 
             try{
             for(int i=0;i<job;i++){
                 buffer=br.readLine().split(",");
                 int k=0;
                 for(int j=0;j<2*machine;){
                     order[i][k]=Integer.parseInt(buffer[j]);
                     j++;
                     data[i][k]=Double.parseDouble(buffer[j]);
                     j++;
                     k++;
                 }
             }
             return new JSPData(data, order, job, machine);
             }catch(Exception e){System.out.println("james.Readfile.read() 1: "+e.getMessage());}}
             else{
              try{
                  for(int i=0;i<job;i++){
                 buffer=br.readLine().split(",");
                 
                 for(int j=0;j<machine;j++){
                      data[i][j]=Double.parseDouble(buffer[j]);
                 }
                  }
                      for(int i=0;i<job;i++){
                 buffer=br.readLine().split(",");
                 
                 for(int j=0;j<machine;j++){
                      order[i][j]=Integer.parseInt(buffer[j])-1;
                 }
                  }
                  return new JSPData(data, order, job, machine);
              }catch(Exception e){System.out.println("james.Readfile.read() 2: "+e.getMessage());}
             }
             
        } catch (Exception e) {System.out.println("james.Readfile.read() : "+e.getMessage());
        }
     
            return null;
        
    }
    
}
