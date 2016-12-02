package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class JsonGerenator {

	public static final String SAMPLE_JSON_WITH_PROBLEMS = ""
			+ "{ \"recordId\" : \"tst\", \"Name\" : \"tst\", \"DateCreated\" : \"tst\", \"DueDate\" : \"tst\", \"DateUpdated\" : \"tst\", \"AudioLength\" : \"\", \"Content\" : \"tst\" },"
			+ "{ \"recordId\" : \"tst\", \"Name\" : \"\", \"DateCreated\" : \"tst\", \"DueDate\" : \"tst\", \"DateUpdated\" : \"tst\", \"AudioLength\" : \"\", \"Content\" : \"tst\" },"
			+ "{ \"recordId\" : \"tst\", \"Name\" : \"tst\", \"DateCreated\" : \"tst\", \"DueDate\" : \"tst\", \"DateUpdated\" : \"tst\", \"AudioLength\" : \"\", \"Content\" : \"tst\" },"
			+ "{ \"recordId\" : \"tst\", \"Name\" : \"tst\", \"DateCreated\" : \"tst\", \"DueDate\" : \"\", \"DateUpdated\" : \"tst\", \"AudioLength\" : \"\", \"Content\" : \"tst\" }";

	public void arrayBuilderOld(File file, String jsonBody, int itemsCount) throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("[");

		while (itemsCount > 0) {
			builder.append(jsonBody);
			if (itemsCount > 1) {
				builder.append(",");
			}
			itemsCount--;
		}
		builder.append("]");

		FileUtils.writeStringToFile(file, builder.toString(), "UTF-8");
	}

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
			e.printStackTrace();
		}
	}

}
