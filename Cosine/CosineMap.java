package package2;

/*
 
  Author: Akash Darak
  Date: 03/26/2016
 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

public class CosineMap extends Mapper<LongWritable, Text, Text, Text>{

	Path[] cache = new Path[0];
	private String buffer=null;
	private String line=null;
	
	ArrayList<String> array = new ArrayList();
	@SuppressWarnings("deprecation")
	@Override
	  public void setup(Context context) 
	  {
	      Configuration conf = context.getConfiguration();
	   
	      try 
	      {
	    	  cache = DistributedCache.getLocalCacheFiles(conf);
	    	  BufferedReader reader = new BufferedReader(new FileReader(cache[0].toString())); 
			    
	      	  String line;
	      	  while ((line = reader.readLine())!= null) 
	      	  {
	      		array.add(line);
	      	  }    	
	      }	   
	      catch (IOException e) 
	      {
	    	  e.printStackTrace();
	      }

	   } 
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		String[] parse = value.toString().split("\t");
		ArrayList<String> arraylist = new ArrayList<String>();
		
		String newtf = "";
		String word = "";
		String Key = "";
		String author = "";
		
		for(int i = 0; i < array.size(); i++)
		{
			String nth = array.get(i);
			String[] itr = nth.split("\t");
			
			if(parse[0].equals(itr[0]))
			{
				Double idf = Double.parseDouble(parse[2]);
				Double tfidf = Double.parseDouble(itr[2]) * idf;
				word = parse[0];
				author = parse[1];
				
				Double newtfidf = Double.parseDouble(parse[2]);
				Key = author + "\t" + word + "\t" + newtfidf + "\t" + tfidf;
				arraylist.add(Key);
			}
		}
		for(int j = 0; j < arraylist.size(); j++)
		{
			String writeline = arraylist.get(j);
			String[] writeContext = writeline.split("\t");
			context.write(new Text(writeContext[0]), new Text(writeContext[1] + "\t " + writeContext[2] + "\t" + writeContext[3]));
		}
	}
}
