package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataHandler {
	public static final int DTYPE_KEYPRESS_SOURCE1 = 0;
	
	List<DataSource> sources;
	List<DataReciever> recievers;
	Map<Integer, ArrayList<DataReciever>> typemap;
	Set<Integer> availabletypes;
	
	public DataHandler() {
		sources = new ArrayList<DataSource>();
		recievers = new ArrayList<DataReciever>();
		availabletypes = new HashSet<Integer>(); 
		typemap = new HashMap<Integer, ArrayList<DataReciever>>();
	}
	
	/*Add a source of some type of data*/
	public void addSource(DataSource ds) {
		sources.add(ds);
		availabletypes.add(ds.getDataType());
		typemap.put(ds.getDataType(), new ArrayList<DataReciever>());
	}
	
	/*Add a receiver with some data reqs. True if data available, else false*/
	public boolean addReciever(DataReciever dr) {
		for (int type : dr.getDataTypes()) {
			if (!availabletypes.contains(type)) {
				return false;
			}
		}
		
		for (int type : dr.getDataTypes()) {
			typemap.get(type).add(dr);
		}
		
		recievers.add(dr);
		
		return true;
	}
	
	public void pushData(int type, Object data) {
		for (DataReciever rec : typemap.get(type)) {
			System.out.println("DH: sent data");
			rec.recieveData(type, data);
		}
	}
}
