package package2;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

	  public class Reduce3 extends Reducer<Text,Text,Text,IntWritable> {

	      int wordcount;
	      Set<String> s = new TreeSet<String>();
		  
		  private IntWritable word = new IntWritable();

		    public void reduce(Text key, Iterable<Text> values,
		                       Context context) throws IOException, InterruptedException {
		    		      
		    		      int sum=0;
				      for (Text val : values) {
				    	  s.add(val.toString());
				    	  sum += 1;
				      }
				      
				      word.set(sum);
				      context.write(key, word);     
		      
		    	}
		}
