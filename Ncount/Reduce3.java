package package2;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.util.Set;
//import java.util.TreeSet;



import java.util.Set;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.mapreduce.Reducer.Context;


	  public class Reduce3 extends Reducer<Text,Text,Text,IntWritable> {
		  
	//      int max = 0;
	      int wordcount;
	      Set<String> s = new TreeSet<String>();
		  
		  private IntWritable word = new IntWritable();

		    public void reduce(Text key, Iterable<Text> values,
		                       Context context) throws IOException, InterruptedException {
		      
		  //    BufferedReader input = null;
	
		  //  	  String[] input = val.toString().split("\t");
	
				  //    BufferedReader input = null;
		    		  int sum=0;
			
				      for (Text val : values) {
				    	  s.add(val.toString());
				    	  sum += 1;
				      }
				      
				      word.set(sum);
				      context.write(key, word);     
		      
		    }
		  }



