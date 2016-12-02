package file.reader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import mappingobject.Record;

public class RecordStringMatcher extends StringMatcher {

	@SuppressWarnings("serial")
	public Map<String, AtomicLong> matches = new HashMap<String, AtomicLong>() {
		{
			put("audioLength", new AtomicLong(0));
			put("name", new AtomicLong(0));
		}
	};

	@Override
	public void objectChecker(Object obj) {
		String audioLength = ((Record) obj).getAudioLength();
		String name = ((Record) obj).getName();

		if (isEmpty(audioLength)) {
			matches.get("audioLength").incrementAndGet();
		}
		if (isEmpty(name)) {
			matches.get("name").incrementAndGet();
		}
	}

}
