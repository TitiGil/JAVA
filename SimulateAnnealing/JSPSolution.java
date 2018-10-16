/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package james;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Objects;
import org.jamesframework.core.problems.sol.Solution;

/**
 *
 * @author AMG
 */
public class JSPSolution extends Solution{
    // list of operation in the order in which they are visited
   
    private List<Integer> operation;

    public JSPSolution(List<Integer> operation){
        this.operation = operation;
    }

    public List<Integer> getOperations(){
        return operation;
    }

    // swap the i-th and j-th operation
    public void swapOperations(int i, int j){
        //check if two operation 
        if(operation.get(i).compareTo(operation.get(j))!=0){
        Collections.swap(operation, i, j); }
    }

    @Override
    public JSPSolution copy() {
        return new JSPSolution(new ArrayList<>(operation));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JSPSolution other = (JSPSolution) obj;
        return Objects.equals(this.operation, other.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(operation);
    }

}
