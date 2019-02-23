package eReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public interface EReport {
	public static void main(String[] args) throws IOException {
		String[] lines = Files.readAllLines(new File("employees.dat").toPath()).toArray(new String[0]);
		for(String line: lines) {
		   // int courseId = Integer.parseInt(line.substring(0, 4));
		   // String studentName = line.substring(4, 24).trim();
		    // etc...
			System.out.println(line);
		}
	}
}
