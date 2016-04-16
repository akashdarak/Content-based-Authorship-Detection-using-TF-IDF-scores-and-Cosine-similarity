package package2;

import java.io.BufferedReader;
import java.io.IOException;
//import java.util.StringTokenizer;


//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;



import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class Map3 extends Mapper<LongWritable, Text, Text, Text> {
	
	private String author=null;
//	private String keys=null;
	private String word=null;
	private String[] str=null;
//	private final static IntWritable one = new IntWritable(1);
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
		
		
		/*
		String line = value.toString();
		if(line.contains("\t")){
			String[] input = value.toString().split("\t");
			String author1 = input[0];
			String word1 = input[1];
			
			word.set(word1);
			author.set(author1);
			
	        context.write(new Text(word), new Text(author));
			
		//	line = input.readLine();
	        
			
		} 
		*/
	
