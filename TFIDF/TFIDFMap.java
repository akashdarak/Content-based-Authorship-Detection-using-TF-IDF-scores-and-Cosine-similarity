package package2;

/*

Author: Akash Darak
Date: 03/22/2016

*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

public class TFIDFMap extends Mapper<LongWritable, Text, Text, Text>  {
	
	Path[] cache = new Path[0];
	Path[] cache1 = new Path[1];
	public int ncnt;
	public int Authorc;
	public int Ni;
	@SuppressWarnings("deprecation")
	@Override
	  public void setup(Context context) 
	  {
	      Configuration conf = context.getConfiguration();
	      Configuration conf1 = context.getConfiguration();
	   
	      try 
	      {
	    	  cache = DistributedCache.getLocalCacheFiles(conf);
	    	  @SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(cache[0].toString())); 
	    
	    	  
	    	  cache1 = DistributedCache.getLocalCacheFiles(conf1);
	    	  @SuppressWarnings("resource")
			BufferedReader reader1 = new BufferedReader(new FileReader(cache1[1].toString())); 
    	  
	    	  String line;
	    	  String[] token = new String[3];
	    	  while ((line = reader.readLine())!= null) 
	    	  {
	    		  token = line.split("\t");
	    	  }
	    	  token[1].trim();
	    	  Authorc = Integer.parseInt(token[1]);
	  	  
	    	  String line1;
	    	  String[] token1 = new String[3];
	    	  while ((line1 = reader1.readLine())!= null) 
	    	  {
	    		  token1 = line1.split("\t");
	    	  }
	    	  token1[1].trim();
	    	  Ni = Integer.parseInt(token1[1]);
	 	  
	    	  
	      }
	   
	      catch (IOException e) 
	      {
	    	  e.printStackTrace();
	      }

	   } 
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	
		String[] parse = value.toString().split("\t");
		context.write(new Text(parse[1]), new Text(parse[0] + "\t " + parse[2] + "\t" + Authorc + "\t"+ Ni));
	
	
}
	
	
}
