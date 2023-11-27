package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RecursiveAlgorithm {

	public static void main(String[] args) throws IOException {

		String inputFile = "Slips_Slaps.txt";
		String outputFile = "Slops_Output.txt";

		processFile(inputFile, outputFile);

		try (BufferedReader outputReader = new BufferedReader(new FileReader(outputFile))) {
			String line;
			while ((line = outputReader.readLine()) != null) {
				System.out.println(line);
			}
		}
	}

	private static void processFile(String inputFile, String outputFile) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

			int numberOfLines = Integer.parseInt(reader.readLine());
			writer.write("SLOPS OUTPUT\n");

			for (int i = 0; i < numberOfLines; i++) {
				String line = reader.readLine();
				if (isSlop(line)) {
					writer.write("YES\n");
				} else {
					writer.write("NO\n");
				}
			}

			writer.write("END OF OUTPUT\n");
		}
	}

	private static boolean isSlip(String s) {
		if (s.length() < 2 || (s.charAt(0) != 'D' && s.charAt(0) != 'E')) {
			return false;
		}
		int fCount = 0;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == 'F') {
				fCount++;
			} else {
				break;
			}
		}
		if (fCount == 0) {
			return false;
		}
		String rest = s.substring(fCount + 1);
		return rest.equals("G") || isSlip(rest);
	}

	private static boolean isSlap(String s) {
		if (s.equals("AH")) {
			return true;
		} else if (s.length() > 2 && s.charAt(0) == 'A' && s.charAt(s.length() - 1) == 'C') {
			if (s.charAt(1) == 'B') {
				return isSlap(s.substring(2, s.length() - 1));
			} else {
				return isSlip(s.substring(1, s.length() - 1));
			}
		}
		return false;
	}

	private static boolean isSlop(String s) {
		for (int i = 1; i < s.length(); i++) {
			if (isSlap(s.substring(0, i)) && isSlip(s.substring(i))) {
				return true;
			}
		}
		return false;
	}

}