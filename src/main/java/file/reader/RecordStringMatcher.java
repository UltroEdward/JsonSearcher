package file.reader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import mappingobject.Record;

public class RecordStringMatcher extends StringMatcher {

	public static final String FIELD_NAME = "name";
	public static final String FIELD_AUDIO = "audioLength";
	private Map<String, AtomicLong> matches = new HashMap<String, AtomicLong>() {
		{
			put(FIELD_AUDIO, new AtomicLong(0));
			put(FIELD_NAME, new AtomicLong(0));
		}
	};

	@Override
	public void objectChecker(Object obj) {
		String audioLength = ((Record) obj).getAudioLength();
		String name = ((Record) obj).getName();

		if (isEmpty(audioLength)) {
			matches.get(FIELD_AUDIO).incrementAndGet();
		}
		if (isEmpty(name)) {
			matches.get(FIELD_NAME).incrementAndGet();
		}
	}
	
	public Map<String, AtomicLong> getMatches(){
		return matches;
	}
}
