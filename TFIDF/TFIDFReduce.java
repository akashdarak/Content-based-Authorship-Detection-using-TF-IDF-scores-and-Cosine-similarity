package package2;

/*

Author: Akash Darak
Date: 03/22/2016

*/

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class TFIDFReduce extends Reducer<Text,Text,Text,Text>{
	double Nmax = 0;
	double ni;
	double tf;
	double N;

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		
		ArrayList<String> ValueList = new ArrayList<String>(); 
		
		for (Text val : values) {
			ValueList.add(val.toString());
			double count = Double.parseDouble(val.toString().split("\t")[1]);
			if (count > Nmax){
				Nmax = count;
			}	
		}
		for(String val : ValueList){
			String[] valsplit = val.split("\t");
			ni = Double.parseDouble(valsplit[3]);
			tf = ni/Nmax;
			N =  Double.parseDouble(valsplit[2])/ni;
						
			double idf = Math.log10(N) / Math.log10(2);
			double newtf = Double.parseDouble(valsplit[1])/Nmax;
			
			double tfidf = newtf * idf ;
			
			context.write(key, new Text(valsplit[0] + "\t" + idf + "\t" + tfidf));
		}
	}
}
