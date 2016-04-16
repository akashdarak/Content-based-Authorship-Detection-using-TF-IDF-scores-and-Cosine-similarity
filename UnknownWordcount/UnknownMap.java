package package2;

/*
Author: Akash Darak
Date: 03/25/2016
*/

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UnknownMap extends Mapper<LongWritable,Text,Text,IntWritable>{

	private String author =null;
	private String keys=null;
	private String line=null;
	private String[] str=null;
	private Text word=new Text();
	private final static IntWritable one = new IntWritable(1);
	
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException{
		
		try{
			if((value.toString()).contains("<===>")) {
				
				str = (value.toString()).split("<===>");
				author = str[0].toString().toLowerCase().replaceAll("[^a-zA-Z]"," ");
				line=str[1].toString().toLowerCase().replaceAll("[^a-zA-Z]"," ");
				StringTokenizer tokenizer = new StringTokenizer(line);
			
			while (tokenizer.hasMoreTokens())
			{
				String token = tokenizer.nextToken().replaceAll("[^a-zA-Z]","");
				
				if(token.length() != 0) {
					keys = author + "\t" + token;
					word.set(keys);
					context.write(word, one);
				}
			}
		     }
		}catch(Exception e){
			
		}
	}
}
