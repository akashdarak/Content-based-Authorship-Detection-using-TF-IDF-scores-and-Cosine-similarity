package package2;

/*

Author: Akash Darak
Date: 03/24/2016

*/

import java.io.IOException;
//import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AAVMap extends Mapper<LongWritable,Text,Text,Text>{
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] parse = value.toString().split("\t");
		context.write(new Text(parse[1]), new Text(parse[0] + "\t " + parse[2]));
		
	}

}
