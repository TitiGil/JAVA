/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package james;

import org.jamesframework.core.problems.objectives.Objective;
import org.jamesframework.core.problems.objectives.evaluations.Evaluation;
import org.jamesframework.core.problems.objectives.evaluations.SimpleEvaluation;
import org.jamesframework.core.search.neigh.Move;
import org.jamesframework.core.subset.SubsetSolution;

/**
 *
 * @author AMG
 */
public class JSPObjective implements Objective<JSPSolution, JSPData>{

    @Override
    public boolean isMinimizing() {
       return true;   }

    @Override
    public Evaluation evaluate(JSPSolution solution, JSPData data) {
        double value = 0.0;
        
     value= new evaling(solution, data.getOrder(), data.getData(), data.getMachineSize(),data.getJobSize()).Evaling();
            
        
        return SimpleEvaluation.WITH_VALUE(value);   }


}
