/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package james;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jamesframework.core.problems.sol.Solution;
import org.jamesframework.core.search.neigh.Move;
import org.jamesframework.core.search.neigh.Neighbourhood;

/**
 *
 * @author AMG
 */
public class JSPNeighbourhood implements Neighbourhood<JSPSolution>{

    @Override
    public Move<? super JSPSolution> getRandomMove(JSPSolution st, Random random) {
       int n=st.getOperations().size();
       int i=random.nextInt(n);
       int j=random.nextInt(n-1);
       if(j==i)j++;
       return new SwapMove(i, j);
    }

    @Override
    public List<SwapMove> getAllMoves(JSPSolution st) {
          int n = st.getOperations().size();
        List<SwapMove> moves = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i != j){
                    moves.add(new SwapMove(i, j));
                }
            }
        }
        return moves;
    }
    
    }
    

