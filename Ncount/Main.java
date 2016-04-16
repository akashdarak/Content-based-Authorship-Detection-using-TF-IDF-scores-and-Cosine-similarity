package package2;

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
		
		/*
		job.setMapperClass(Map1.class);
		job.setReducerClass(Reduce1.class);
		*/
		
		job.setMapperClass(Map3.class);
		job.setReducerClass(Reduce3.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
			
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		if(job.waitForCompletion(true))
			System.out.println("Job Completed");
		else System.out.println("Job Failed");
	}

}
