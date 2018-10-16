/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package james;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author AMG
 */
public class evaling {
    
    List<Integer> solution;
    int[] round;
    LinkedList<Time>[] ml;
    double[][] data;
    int[][] order;
    int[] lastMachine;
    public evaling(JSPSolution solution,int[][] order,double[][] data,int numMachine,int numJob) {
        this.solution = solution.getOperations();
       ml=new LinkedList[numMachine];
       for(int i=0;i<numMachine;i++){
           ml[i]=new LinkedList<>();
       }
       this.order=order;
       
       //maximum probablity of operation size
       round=new int[numJob];
       lastMachine=new int[this.solution.size()];
       this.data=data;
    }
    /**
     * Start Decoding operations based on time of each one and find the machine with 
     * longest time duration as Evaluation of this solution
     * @return 
     */
    public double Evaling(){
        double value=0.0;
        int i=0;
  
         for(;i<solution.size();i++){
           //if this the first time job has visited
           if(round[solution.get(i)]==0){
               if(ml[order[solution.get(i)][0]].size()==0 ){
                   ml[order[solution.get(i)][0]].add(new Time(0,solution.get(i) ,data[solution.get(i)][0]));
                  
                   lastMachine[solution.get(i)]=order[solution.get(i)][0];
                    round[solution.get(i)]++;
               }
               else if(ml[order[solution.get(i)][0]].size()==1 ){
                   if(data[solution.get(i)][round[solution.get(i)]]<=ml[order[solution.get(i)][0]].get(0).getStartTime()){
                   ml[order[solution.get(i)][0]].addFirst(new Time(ml[order[solution.get(i)][0]].getLast().getEndTime(),solution.get(i) ,data[solution.get(i)][0]));
                 
                   lastMachine[solution.get(i)]=order[solution.get(i)][0];
                     round[solution.get(i)]++;
                   }else{
                       
                   ml[order[solution.get(i)][0]].add(new Time(ml[order[solution.get(i)][0]].getLast().getEndTime(),solution.get(i) ,data[solution.get(i)][0]));
                   
                   lastMachine[solution.get(i)]=order[solution.get(i)][0];
                   round[solution.get(i)]++;
                   }
               }
              else
               {
                   boolean added=false;
                   for(int k=0;k<ml[order[solution.get(i)][0]].size()-2 && !added;k++){
                       if(dif(order[solution.get(i)][0],k,k+1)>=data[solution.get(i)][0]){
                           ml[order[solution.get(i)][0]].add(k+1, new Time(ml[order[solution.get(i)][0]].get(k).getEndTime(),solution.get(i),data[solution.get(i)][0] ));
                           
                           lastMachine[solution.get(i)]=order[solution.get(i)][0];
                           round[solution.get(i)]++;
                           added=true;
                           break;
                       }
                   }
                   if(!added){
                   ml[order[solution.get(i)][0]].add( new Time(ml[order[solution.get(i)][0]].getLast().getEndTime(),solution.get(i),data[solution.get(i)][0] ));
                          
                           lastMachine[solution.get(i)]=order[solution.get(i)][0];
                            round[solution.get(i)]++;
                    }
               }
               
               
           }
           //if this is not the first operation of job
           else
           {
               double endTime=0.0;
               for( int m=0;m<ml[lastMachine[solution.get(i)]].size();m++){
                   if(ml[lastMachine[solution.get(i)]].get(m).getId()==solution.get(i)){
                       endTime=ml[lastMachine[solution.get(i)]].get(m).getEndTime();
                       break;
                   }
               }
               
               if( ml[order[solution.get(i)][round[solution.get(i)]]].size()==0){
                       ml[order[solution.get(i)][round[solution.get(i)]]].add( new Time(endTime,solution.get(i),data[solution.get(i)][round[solution.get(i)]] ));
                         
                           lastMachine[solution.get(i)]=order[solution.get(i)][round[solution.get(i)]];
                             round[solution.get(i)]++;
               }
               else if( ml[order[solution.get(i)][round[solution.get(i)]]].size()==1){
                   if(ml[order[solution.get(i)][round[solution.get(i)]]].getLast().getEndTime()<=endTime){
                       ml[order[solution.get(i)][round[solution.get(i)]]].add(new Time(endTime,solution.get(i), data[solution.get(i)][round[solution.get(i)]]));
                   
                      lastMachine[solution.get(i)]=order[solution.get(i)][round[solution.get(i)]];
                         round[solution.get(i)]++;
                   }  else {
                       ml[order[solution.get(i)][round[solution.get(i)]]].add(new Time(ml[order[solution.get(i)][round[solution.get(i)]]].get(0).getEndTime(),solution.get(i), data[solution.get(i)][round[solution.get(i)]]));
                    
                      lastMachine[solution.get(i)]=order[solution.get(i)][round[solution.get(i)]];
                        round[solution.get(i)]++;
                   }
               }
               // the size of machine is bigger than 1
               else
               {    
                   boolean added=false;
                   
               for(int k=0;k<ml[order[solution.get(i)][round[solution.get(i)]]].size()-2 && !added;k++){
                   if((dif(order[solution.get(i)][round[solution.get(i)]],k,k+1)>=data[solution.get(i)][round[solution.get(i)]])&&(ml[order[solution.get(i)][round[solution.get(i)]]].get(k).getEndTime()>=endTime)&&(ml[order[solution.get(i)][round[solution.get(i)]]].get(k+1).getStartTime()-endTime>=0)){
                          ml[order[solution.get(i)][round[solution.get(i)]]].add(k+1,new Time(endTime,solution.get(i), data[solution.get(i)][round[solution.get(i)]]));
                      
                      lastMachine[solution.get(i)]=order[solution.get(i)][round[solution.get(i)]];
                      round[solution.get(i)]++;
                      
                      added=true;
                      break;
                   }
               }
                   if(!added  ){
                        ml[order[solution.get(i)][round[solution.get(i)]]].add(new Time( ml[order[solution.get(i)][round[solution.get(i)]]].getLast().getEndTime(),solution.get(i), data[solution.get(i)][round[solution.get(i)]]));
                     
                      lastMachine[solution.get(i)]=order[solution.get(i)][round[solution.get(i)]];
                    round[solution.get(i)]++;}
                  
               
                   
                   
               }
               
           }
       }

         
         for(int j=0;j<ml.length;j++)
          if(  ml[j].getLast().getEndTime()>value)
                  value= ml[j].getLast().getEndTime();
        return value;
    }
    private double dif(int machine ,int k,int m){
        return ml[machine].get(m).getStartTime()-ml[machine].get(k).getEndTime();
    }
    
}
