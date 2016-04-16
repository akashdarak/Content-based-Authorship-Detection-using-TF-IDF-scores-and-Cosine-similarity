package package2;

/*
Author: Akash Darak
Date: 03/20/2016
*/

import java.io.BufferedReader;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

	public class Map2 extends Mapper<Object,Text,Text,Text> {
	private String[] str=null;
	private Text authorc=new Text();
	BufferedReader input = null;
	String read = "";
	private Text wone=new Text();
	
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			
		String line = value.toString();
		if(line.contains("\t")){
			String[] input = value.toString().split("\t");
			String author = input[0];
			read = "one";
			wone.set(read);
			authorc.set(author);
	        context.write(new Text(read), new Text(author));

		} 
		
	}
	
}
