package package2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class Map3 extends Mapper<LongWritable, Text, Text, Text> {
	
	private String author=null;
	private String word=null;
	private String[] str=null;
	BufferedReader input = null;
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		try{
			if((value.toString()).contains("<===>")) {
				
				str = (value.toString()).split("<===>");
				author = str[0].toString().toLowerCase().replaceAll("[^a-zA-Z]"," ");
				word=str[1].toString().toLowerCase().replaceAll("[^a-zA-Z]"," ");
				StringTokenizer tokenizer = new StringTokenizer(word);
			
			while (tokenizer.hasMoreTokens())
			{
				String token = tokenizer.nextToken().replaceAll("[^a-zA-Z]","");
				
				if(token.length() != 0) {
					
					context.write(new Text(token), new Text(author));
				}
	
			}
		
			}
		}catch(Exception e){
			
		}
	}
	
	
}	
	
