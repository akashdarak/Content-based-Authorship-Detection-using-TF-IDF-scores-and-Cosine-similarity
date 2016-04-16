package package2;

/*

Author: Akash Darak
Date: 03/22/2016

*/

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.filecache.DistributedCache;
//import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

//@SuppressWarnings("deprecation")
public class MainUnknown {
	
//	@SuppressWarnings("deprecation")
	public static void main(String[] args)throws Exception{
		Configuration conf = new Configuration();
		
		Job job=Job.getInstance(conf);
		job.setJarByClass(MainUnknown.class);
		
		
		job.setMapperClass(AAVMap.class);		
		job.setReducerClass(AAVReduce.class);	
		
		job.setOutputKeyClass(Text.class);		
		job.setOutputValueClass(Text.class);			
		
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		/*
		DistributedCache.addCacheFile(new Path(args[0]).toUri(), job.getConfiguration());		//Cosine
		FileInputFormat.setInputPaths(job, new Path(args[1]));									//Cosine
		FileOutputFormat.setOutputPath(job, new Path(args[2]));									//Cosine	
		*/
		
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
	}

}
