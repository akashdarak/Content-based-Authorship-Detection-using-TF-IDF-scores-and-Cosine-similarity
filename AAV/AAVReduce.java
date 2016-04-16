package package2;

/*

Author: Akash Darak
Date: 03/24/2016

*/

import java.util.HashMap;
import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;


public class AAVReduce extends Reducer<Text,Text,Text,Text> {
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		String VECTOR="";
		HashMap<String, Integer> aav=new HashMap<String, Integer>();
		for (Text val : values) {
			String[] split= val.toString().split("\t");
			VECTOR=split[0]+"\t"+split[1];
			aav.put(VECTOR.toString(), 1);
			
		}
		context.write(key, new Text("( Vector starts " + aav + " Author ends )" ));

	}
}



