package file.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import mappingobject.Record;

public abstract class StringMatcher {

	public void findMatches(InputStream stream) {
		try {

			JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
			Gson gson = new GsonBuilder().create();
			reader.beginArray();

			while (reader.hasNext()) {
				Record object = gson.fromJson(reader, Record.class);
				objectChecker(object);
			}
			reader.close();
		} catch (IOException ex) {

		}
	}

	public abstract void objectChecker(Object obj);

	public boolean isStringBroken(String string) {
		if (string == null || string.isEmpty()) {
			return true;
		}
		return false;
	}
}
