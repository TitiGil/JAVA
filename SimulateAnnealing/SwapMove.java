/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package james;

import org.jamesframework.core.search.neigh.Move;

/**
 *
 * @author AMG
 */
public class SwapMove implements Move<JSPSolution>{
    
    private int i,j;

    public SwapMove(int i, int j) {
        if(i == j){
            throw new IllegalArgumentException("Error: i and j should be distinct positions.");
        }
        this.i = i;
        this.j = j;
    }
    
     public int getI(){
        return i;
    }
    
    public int getJ(){
        return j;
    }

    @Override
    public void apply(JSPSolution st) {
    st.swapOperations(i, j);
    
    }

    @Override
    public void undo(JSPSolution st) {
       apply(st);   
    }
}
