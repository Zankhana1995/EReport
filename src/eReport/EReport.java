package eReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface EReport {
	public static void main(String[] args) throws IOException {
		String[] lines = Files.readAllLines(new File("employees.dat").toPath()).toArray(new String[0]);
		List<String> list = new ArrayList<>();
		for (String line : lines) {
			if (!line.startsWith("#")) {
				list.add(line);
			}
		}
		sortByEmployeeNumber(list);
		System.out.println("\n");
		sortByEmployeeLastName(list);
	}

	public static void sortByEmployeeLastName(List<String> list) {
		TreeMap<String, String> hmap = new TreeMap<>();
		for (String line : list) {
			String[] elements = line.split(",");
			String[] fullname = elements[1].trim().split("\\s+");
			hmap.put(fullname[1], elements[0]+","+elements[1].trim());
		}
		System.out.println("Processing by last (family) Name...");
		for (Map.Entry<String, String> entry : hmap.entrySet()) {
			System.out.println(entry.getValue().trim());
		}

	}

	public static void sortByEmployeeNumber(List<String> list) {
		Map<Integer, String> map = new TreeMap<Integer, String>();
		for (String line : list) {
			int employeeNumber = Integer.parseInt(line.substring(0, 3));
			map.put(employeeNumber, line.substring(4));
		}
		System.out.println("Processing by employee number...");
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue().trim());
		}
	}
}
