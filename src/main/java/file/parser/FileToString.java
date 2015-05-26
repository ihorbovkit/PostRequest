package file.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileToString {
	
	public String fileToString(String path) throws IOException{
		
		List<String> lines = Files.readAllLines(Paths.get(path));
		String st = "";
		for(String s : lines){
			st += s + "\t";
		}
		return st;
		
	}
}
