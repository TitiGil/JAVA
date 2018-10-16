

import ca.pfv.spmf.patterns.cluster.Cluster;
import ca.pfv.spmf.patterns.cluster.DoubleArray;
import ca.pfv.spmf.patterns.cluster.DoubleArrayInstance;


public class DoubleArrayDBS extends DoubleArrayInstance{
	
	public boolean visited = false;
	Cluster cluster = null;

	/**
	 * Constructor
	 * @param data an array of double values
	 * @param String the name of this array
	 */
	public DoubleArrayDBS(double[] data, String name) {
		super(data, name);
	}

}
