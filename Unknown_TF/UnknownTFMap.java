package package2;

/*

Author: Akash Darak
Date: 03/25/2016

*/
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UnknownTFMap extends Mapper<LongWritable, Text, Text, Text>{
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] parse = value.toString().split("\t");
		context.write(new Text(parse[1]), new Text(parse[0] + "\t " + parse[2]));
	}
}