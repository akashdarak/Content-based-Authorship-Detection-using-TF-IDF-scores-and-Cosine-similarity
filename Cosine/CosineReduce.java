package package2;


/*

Author: Akash Darak
Date: 03/26/2016

*/

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class CosineReduce extends Reducer<Text, Text,Text, Text>{
	
	double dotp = 0.0d;
	double A = 0.0d;
	double B = 0.0d;
	
	double Cosine = 0.0d;

	private Text read = new Text();
	
	public void reduce(Text key, Iterable<Text> values,
            Context context
            ) throws IOException, InterruptedException {
		
		for (Text val : values) {
			String[] parse = val.toString().split("\t");
			double SetA = Double.parseDouble(parse[1]);
			double SetB = Double.parseDouble(parse[2]);
			dotp +=  SetA * SetB;
			A += Math.pow(SetA, 2);
			B += Math.pow(SetB, 2);
		}	
		Cosine = dotp / (Math.sqrt(A) * Math.sqrt(B));
		String str = new Double(Cosine).toString();
		read.set(str);
		context.write(key, new Text(str));
	}
		
}

