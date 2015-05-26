package filewriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class WriteToFile {
	public static void writeToFile(String accessToken) throws IOException{
		FileWriter fw = new FileWriter("src/test/resources/result.json");
	    BufferedWriter bw = new BufferedWriter(fw);
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String codeFromSms = br.readLine();
	   	bw.write("{\n" + "\"accessToken\" : " + "\"" + accessToken + "\"," + "\n" + "\"code\" : " + "\"" + codeFromSms + "\"\n }" );
	    bw.close(); 
	}

}
