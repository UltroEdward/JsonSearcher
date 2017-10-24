package file.reader;

import model.Record;

import java.util.HashMap;
import java.util.Map;

public class RecordStringMatcher extends StringMatcher<Map<String, Integer>> {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_AUDIO = "audioLength";
    private Map<String, Integer> matches = new HashMap<String, Integer>() {
        {
            put(FIELD_AUDIO, new Integer(0));
            put(FIELD_NAME, new Integer(0));
        }
    };

    @Override
    public void objectChecker(Object obj) {
        String audioLength = ((Record) obj).getAudioLength();
        String name = ((Record) obj).getName();

        if (isEmpty(audioLength)) {
            matches.put(FIELD_AUDIO, matches.get(FIELD_AUDIO) + 1);
        }
        if (isEmpty(name)) {
            matches.put(FIELD_NAME, matches.get(FIELD_NAME) + 1);
        }
    }

    @Override
    public Map<String, Integer> getMatches() {
        return matches;
    }

}
