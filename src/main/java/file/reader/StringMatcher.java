package file.reader;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import mappingobject.Record;

public abstract class StringMatcher {

	public static final Logger Log = LoggerFactory.getLogger(StringMatcher.class);

	public abstract void objectChecker(Object obj);

	public void findMatches(BufferedReader inputReader) {
		try {
			JsonReader reader = new JsonReader(inputReader);
			Gson gson = new GsonBuilder().create();
			reader.beginArray();

			while (reader.hasNext()) {
				Record object = gson.fromJson(reader, Record.class);
				objectChecker(object);
			}
			reader.close();
		} catch (IOException ex) {
			Log.error(String.format("IO exception happens \n Error: %s", ex.getMessage()));
		}
	}

	public boolean isEmpty(String string) {
		if (string == null || string.isEmpty()) {
			return true;
		}
		return false;
	}
}
