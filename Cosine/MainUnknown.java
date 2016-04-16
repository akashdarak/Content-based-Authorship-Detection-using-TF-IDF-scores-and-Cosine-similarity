package package2;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

@SuppressWarnings("deprecation")
public class MainUnknown {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args)throws Exception{
		Configuration conf = new Configuration();
		
		Job job=Job.getInstance(conf);
		job.setJarByClass(MainUnknown.class);
		
		
		job.setMapperClass(CosineMap.class);		
		job.setReducerClass(CosineReduce.class);	
		
		job.setOutputKeyClass(Text.class);		
		job.setOutputValueClass(Text.class);			
		
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
			
		DistributedCache.addCacheFile(new Path(args[0]).toUri(), job.getConfiguration());		
		
		FileInputFormat.setInputPaths(job, new Path(args[1]));						
		FileOutputFormat.setOutputPath(job, new Path(args[2]));						

		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		if(job.waitForCompletion(true))
			System.out.println("Job Completed");
		else System.out.println("Job Failed");
	}

}
