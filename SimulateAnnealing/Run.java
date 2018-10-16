/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package james;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.jamesframework.core.problems.GenericProblem;
import org.jamesframework.core.problems.Problem;
import org.jamesframework.core.problems.objectives.evaluations.Evaluation;
import org.jamesframework.core.problems.sol.RandomSolutionGenerator;
import org.jamesframework.core.search.algo.RandomDescent;
import org.jamesframework.core.search.stopcriteria.MaxRuntime;
import org.jamesframework.core.subset.SubsetProblem;
import org.jamesframework.core.subset.SubsetSolution;
import org.jamesframework.core.subset.neigh.SingleSwapNeighbourhood;

/**
 *
 * @author AMG
 */
public class Run {
    
        public static final RandomSolutionGenerator<JSPSolution, JSPData> RANDOM_SOLUTION_GENERATOR = (rnd, data) -> {
        // create random permutation of cities
        List<Integer> operation = new ArrayList<>();
        int n = data.getMachineSize();
        for(int i=0; i<data.getJobSize(); i++){
            for(int j=0;j<data.getMachineSize();j++){
               operation.add(i); 
            }
            
        }
        Collections.shuffle(operation, rnd);
        // create and return TSP solution
        return new JSPSolution(operation);
    };
    
    
    
    
    
    
    public static void main(String[] args) {
         String[] names ;
         String[] buffer;
         double[][] dist ;
         double maxTemp,minTemp;
         int equil;
         /// The data source file ///
         String filepath="D:\\data2.jsp";
         JSPData data=Readfile.read(filepath,false);
        for(double[] d:data.getData()){
            System.err.println(Arrays.toString(d));
        }
        for(int[] d:data.getOrder()){
            System.err.println(Arrays.toString(d));
        }
         JSPObjective obj=new JSPObjective();
         Problem<JSPSolution> problem =new GenericProblem<>(data,obj,RANDOM_SOLUTION_GENERATOR);
         //inout parameter
         maxTemp=9900000;minTemp=0.1;equil=30;
         SimulateAnnealing<JSPSolution> sa;
            sa = new SimulateAnnealing<>(null,problem,new JSPNeighbourhood(),maxTemp,minTemp,equil);
            
            sa.setCoolingmode(sa.EXPONENTIAL);
            sa.setAlpha(0.8);
            sa.searchStep();
            JSPSolution js=sa.getBestSolution();
            System.err.println(Arrays.toString(js.getOperations().toArray()));
            Evaluation ev=sa.getBestSolutionEvaluation();
            System.out.println();
           System.err.println("Best Solution Value : " + ev.getValue());
    }
}
