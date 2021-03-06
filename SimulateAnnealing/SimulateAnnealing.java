/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package james;
/*
 * Copyright 2014 Ghent University, Bayer CropScience.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import org.jamesframework.core.exceptions.JamesRuntimeException;
import org.jamesframework.core.problems.Problem;
import org.jamesframework.core.problems.objectives.evaluations.Evaluation;
import org.jamesframework.core.problems.sol.Solution;
import org.jamesframework.core.search.SingleNeighbourhoodSearch;
import org.jamesframework.core.search.neigh.Move;
import org.jamesframework.core.search.neigh.Neighbourhood;

/**
 * Metropolis search (fixed temperature). Iteratively samples a random neighbour and accepts it based on a criterion
 * that depends on both the improvement in evaluation (\(\Delta E\)) and the temperature of the system (\(T\)).
 * When a valid neighbour is obtained with \(\Delta E \ge 0\), which indicates improvement, it is always accepted
 * as the new current solution. Else, it is accepted with
 * probability
 * \[
 *      e^{\frac{\Delta E}{T}}
 * \]
 * The probability of acceptance increases when the temperature \(T\) is higher or when (the negative) \(\Delta E\)
 * is closer to zero.
 * <p>
 * Note that it is important to carefully choose the temperature, depending on the scale of the evaluations
 * and expected deltas, as well as the landscape of the objective function. Setting a high temperature decreases
 * the probability of ending in a local optimum, but also impedes convergence and generally slows down the search
 * process. Vice versa, a low temperature aids convergence but yields a search that is more sensitive to local
 * optima. It is therefore strongly advised to experiment with different temperatures for each specific problem
 * so that an appropriate temperature can be selected.
 * <p>
 * The search usually does not terminate internally except in the rare event that no random neighbour can
 * be selected. It generally depends on external stop criteria for termination.
 * 
 * @param <SolutionType> solution type of the problems that may be solved using this search, required to extend {@link Solution}
 * @author <a href="mailto:herman.debeukelaer@ugent.be">Herman De Beukelaer</a>
 */
public class SimulateAnnealing<SolutionType extends Solution> extends SingleNeighbourhoodSearch<SolutionType> {

    // temperature
    private double maxTemperature;
    private double minTemperature;
   
    /**
     * Creates a new Metropolis search, specifying the problem to solve, the applied neighbourhood and the temperature.
     * The problem and neighbourhood can not be <code>null</code>, and the temperature should be strictly positive.
     * The search name defaults to "MetropolisSearch".
     * 
     * @throws NullPointerException if <code>problem</code> or <code>neighbourhood</code> are <code>null</code>
     * @throws IllegalArgumentException if <code>temperature</code> is not strictly positive
     * @param problem problem to solve
     * @param neighbourhood neighbourhood used to create neighbouring solutions
     * @param temperature temperature of the system
     */
   

    /******************* constructor disabled****************/
    /* public SimulateAnnealing(Problem<SolutionType> problem,
    Neighbourhood<? super SolutionType> neighbourhood,
    double maxTemperature,double minTempreture){
    this(null, problem, neighbourhood, maxTemperature,minTemperature);
    }*/
    /**
     * Creates a new Metropolis search, specifying the problem to solve, the applied neighbourhood and temperature,
     * and a custom search name. The problem and neighbourhood can not be <code>null</code>, and the temperature
     * should be strictly positive. The search name can be <code>null</code> in which case the default name
     * "MetropolisSearch" is assigned.
     *
     * @throws NullPointerException if <code>problem</code> or <code>neighbourhood</code> are <code>null</code>
     * @throws IllegalArgumentException if <code>temperature</code> is not strictly positive
     * @param name custom search name
     * @param problem problem to solve
     * @param neighbourhood neighbourhood used to create neighbouring solutions
     * @param maxTemperature maximum temperature of the system
     * @param minTempreture minimum temperature of the system
     */
    public SimulateAnnealing(String name, Problem<SolutionType> problem, Neighbourhood<? super SolutionType> neighbourhood, double maxTemperature, double minTemperature,int equilibrium) {
        super(name != null ? name : "Simulate Annealing", problem, neighbourhood);
        // check temperature
        if(maxTemperature <= 0.0 || minTemperature<= 0.0){
            throw new IllegalArgumentException("Minimum and maximum Temperature of Simulate annealing  search should be strictly positive.");
        }
        // set temperature
        this.maxTemperature = maxTemperature;
        this.minTemperature= minTemperature;
        this.temperature=maxTemperature;
        setEquilibrium(equilibrium);
       
        setCurrentSolution(problem.createRandomSolution());
        setNeighbourhood(neighbourhood);
    }
    
    /**
     * Set the temperature (\(T &gt; 0\)).
     * 
     * @param temperature new temperature
     * @throws IllegalArgumentException if <code>temperature</code> is not strictly positive
     */
    public void setNewMaxTemperature(double maxTemperature){
        // check temperature
        if(maxTemperature <= 0.0){
            throw new IllegalArgumentException("Maximum Temperature of Metropolis search should be strictly positive.");
        }
        // update temperature
        this.maxTemperature = maxTemperature;
    }
    
        public void setNewMinTemperature(double minTemperature){
        // check temperature
        if(minTemperature <= 0.0){
            throw new IllegalArgumentException("Minimum Temperature of Metropolis search should be strictly positive.");
        }
        // update temperature
        this.minTemperature = minTemperature;
    }
    /**
     * Get the temperature \(T\) of the system.
     * 
     * @return temperature
     */
    public double getMaxTemperature(){
        return maxTemperature;
    }
     public double getMinTemperature(){
        return minTemperature;
    }


    /**
     * Creates a random neighbour of the current solution and accepts it as the new current solution
     * if it is valid and either improves the current solution or
     * \[
     *      e^{\frac{\Delta E}{T}} &gt; R(0,1)
     * \]
     * where \(\Delta E\) is the difference between the evaluation of the neighbour and that of the
     * current solution, \(T\) is the temperature, and \(R(0,1)\) is a random number in the interval
     * \([0,1]\) sampled using the search's dedicated random generator.
     * 
     * @throws JamesRuntimeException if depending on malfunctioning components (problem, neighbourhood, ...)
     */
     
     private double temperature;
     public  short coolingMode=0;
     public  final short LINEAR=1;
      public  final short EXPONENTIAL=2;
      public  final short LOGARITHMIC=3;
      private int equilibrium=0;

    public int getEquilibrium() {
        return equilibrium;
    }

    public void setEquilibrium(int equilibrium) {
        if(equilibrium<1)
            throw new IllegalArgumentException("set appropriate Equilibrium value");
        this.equilibrium = equilibrium;
    }

      
      public void setCoolingmode(short coolingmode){
          coolingMode=coolingmode;
      }
    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        if(beta<=0.0)
            throw new IllegalArgumentException("beta should be strictly positive ! ");
        this.beta = beta;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        if(alpha<0.5 || alpha >1)
            throw new IllegalArgumentException("alpha should be a value between 0.5 and 0.99 ! ");
        this.alpha = alpha;
    }
      double beta=0.0;
      double alpha=0.0;
      
     private double cooling(double temperature ,int round){
         if(coolingMode==0)
             throw new IllegalArgumentException("You should choose Cooling Mode ! by setCoolingMode function");
         if(coolingMode==LINEAR){
             return temperature-(round*beta);
         }
         if(coolingMode==EXPONENTIAL){
             return temperature*alpha;
         }
         if(coolingMode==LOGARITHMIC){
             return temperature/Math.log(round);
         }
         throw new IllegalArgumentException("cooling mode has not appropriate value !");
     }
     
    @Override
    protected void searchStep() {
        int round=2;
        if(coolingMode==0)
            throw new IllegalArgumentException("You should set cooling mode");
        if(coolingMode==LINEAR && beta==0.0)
           throw new IllegalArgumentException("You should set 'Beta' value for LINAER cooling mode");
       if(coolingMode==EXPONENTIAL && alpha==0.0)
           throw new IllegalArgumentException("You should set 'Alpha' value for Exponential cooling mode");
   
        while(temperature>=minTemperature){
        // get random move
       for(int i=equilibrium;i>1;i--){
        
        Move<? super SolutionType> move;
           move = getNeighbourhood().getRandomMove( getCurrentSolution(), getRandom());
        
        // got move?
        if(move != null){
            // valid move?
            if(validate(move).passed()){
                // valid move: improvement?
                
                if(isImprovement(move)){
                    // improvement: always accept
                    accept(move);
                } else {
                    // no improvement: accept with probability based on temperature and delta
                    
                    
                    double delta = computeDelta(evaluate(move), getCurrentSolutionEvaluation());
                    double r = getRandom().nextDouble();
                    if(Math.exp(delta/temperature) > r){
                        // accept inferior move
                        accept(move);
                    } else {
                        // reject inferior move
                        reject(move);
                    }
                }
            } else {
                // invalid move: reject
                reject(move);
            }
        } else {
            // no move/neighbour found
            stop();            
        }
        i--;
        }
       temperature=cooling(temperature,round);
       round++;
       
    }
    }
    
}