package package2;

/*
Author: Akash Darak
Date: 03/20/2016
*/

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

	  public class Reduce2 extends Reducer<Text,Text,Text,Text> {
		  int sum=0;
	      int max = 0;
	      private Text word=new Text();
	      String size = "";
	      String Key="Author count";

		    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		    	Set<String> s = new TreeSet<String>();
	
		      for (Text val : values) {
		    	  s.add(val.toString());
		      }
		      
		      size = Integer.toString(s.size()); 
		      word.set(Key);
		      context.write(word, new Text(size));
		      
		  }
	  }


