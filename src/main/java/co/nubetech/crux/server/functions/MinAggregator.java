package co.nubetech.crux.server.functions;

import org.apache.hadoop.hbase.util.Bytes;

import co.nubetech.crux.util.CruxException;

public class MinAggregator extends FunctionBase implements CruxAggregator{
	
	private Long min;
	
	/*
	 * this method is invoked for each value
	 */
	@Override
	public void aggregate(Object o) throws CruxException{
		if (o != null) {
			Long oLong = null;
			if (o instanceof byte[]) {
				oLong = Bytes.toLong((byte[]) o);				
			}			
			else {
					oLong = (Long) o;
			}
			if (min != null) {
				if (oLong != null) {
					if (oLong.compareTo(min) < 0) {
						min = oLong;
					}
				}
			}
			else {
				min = oLong;
			}
		}
			
	}
	
	
	
	/*
	 * invoked in the end to get the result
	 */
	@Override
	public Object getAggregate() {
		return min;		
	}
	
	@Override
	public boolean isAggregate() {
		return true;
	}
	

}
