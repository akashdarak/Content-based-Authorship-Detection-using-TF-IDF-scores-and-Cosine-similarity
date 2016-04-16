package package2;

/*

Author: Akash Darak
Date: 03/20/2016

*/

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

//import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Main {
	
	public static void main(String[] args)throws Exception{
		Configuration conf = new Configuration();
		
		Job job=Job.getInstance(conf);
		job.setJarByClass(Main.class);
		
		
		job.setMapperClass(Map2.class);
		job.setReducerClass(Reduce2.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		
		/*
		job.setMapperClass(AAVMap.class);		//AAV
		job.setReducerClass(AAVReduce.class);	//AAV
		
		job.setOutputKeyClass(Text.class);		//Unknown
		job.setOutputValueClass(Text.class);			//Unknown
		
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		*/	
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		
	}

}
