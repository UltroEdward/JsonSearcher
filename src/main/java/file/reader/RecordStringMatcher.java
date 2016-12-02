package file.reader;

import java.util.HashMap;
import java.util.Map;

import mappingobject.Record;

public class RecordStringMatcher extends StringMatcher {

	public Map<String, Integer> matches = new HashMap<String, Integer>() {{put("audioLength", 0); put("name", 0); }};

	@Override
	public void objectChecker(Object obj) {
		String audioLength = ((Record) obj).getAudioLength();
		String name = ((Record) obj).getName();

		if (isStringBroken(audioLength)) {
			matches.put("audioLength", matches.get("audioLength") +1);
		}
		if (isStringBroken(name)) {
			matches.put("name", matches.get("name") +1);
		}
	}

}
