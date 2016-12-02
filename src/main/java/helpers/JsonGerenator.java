package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonGerenator {
	
	public static final Logger Log = LoggerFactory.getLogger(JsonGerenator.class);
	public static final String SAMPLE_JSON_WITH_PROBLEMS = ""
			+ "{ \"recordId\" : \"tst\", \"Name\" : \"tst\", \"DateCreated\" : \"tst\", \"DueDate\" : \"tst\", \"DateUpdated\" : \"tst\", \"AudioLength\" : \"\", \"Content\" : \"tst\" },"
			+ "{ \"recordId\" : \"tst\", \"Name\" : \"\", \"DateCreated\" : \"tst\", \"DueDate\" : \"tst\", \"DateUpdated\" : \"tst\", \"AudioLength\" : \"\", \"Content\" : \"tst\" },"
			+ "{ \"recordId\" : \"tst\", \"Name\" : \"tst\", \"DateCreated\" : \"tst\", \"DueDate\" : \"tst\", \"DateUpdated\" : \"tst\", \"AudioLength\" : \"\", \"Content\" : \"tst\" },"
			+ "{ \"recordId\" : \"tst\", \"Name\" : \"tst\", \"DateCreated\" : \"tst\", \"DueDate\" : \"\", \"DateUpdated\" : \"tst\", \"AudioLength\" : \"\", \"Content\" : \"tst\" }";

	public void arrayBuilder(File file, String jsonBody, int itemsCount) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

			bw.write("[");
			while (itemsCount > 0) {
				bw.write(jsonBody);
				if (itemsCount > 1) {
					bw.write(",");
				}
				itemsCount--;
			}
			bw.write("]");

		} catch (IOException e) {
			Log.error(String.format("IO exception happens for file: %s. \n Error: %s", file.getName(), e.getMessage()));
		}
	}

	public static void generateTestData(String folder, String jsonToDuplicate, int filesCount, int rowsCountPerFile) {
		JsonGerenator generator = new JsonGerenator();
		List<File> files = new ArrayList<>();

		for (int i = 0; i < filesCount; i++) {
			files.add(new File(String.format("%s//TestFile_%d_%d.txt", folder, i, rowsCountPerFile)));
		}

		for (File sampleFile : files) {
			generator.arrayBuilder(sampleFile, jsonToDuplicate, rowsCountPerFile);
		}

		Log.info(String.format("Files %d created", filesCount));
	}
}
