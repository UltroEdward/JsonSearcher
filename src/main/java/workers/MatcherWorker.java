package workers;

import file.reader.RecordStringMatcher;
import file.reader.StringMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.Callable;

public class MatcherWorker implements Callable<StringMatcher> {

    private static final Logger Log = LoggerFactory.getLogger(MatcherWorker.class);
    private File fileToWorkWith;

    public MatcherWorker(File fileToWorkWith) {
        this.fileToWorkWith = fileToWorkWith;
    }

    @Override
    public StringMatcher call() throws Exception {

        StringMatcher matcher = new RecordStringMatcher();

        try {
            double startTime = System.currentTimeMillis();
            BufferedReader reader = Files.newBufferedReader(fileToWorkWith.toPath(), StandardCharsets.UTF_8);
            matcher.findMatches(reader);
            Log.info(String.format("File %s processed on [%s] thread with time [%s sec] and following matches: %s ", fileToWorkWith.getName(),
                Thread.currentThread().getName(), ((System.currentTimeMillis() - startTime) / 1000), matcher.getMatches()));
        } catch (IOException e) {
            Log.error(String.format("IO exception happens for file: %s. \n Error: %s", fileToWorkWith.getName(), e.getMessage()));
        }

        return matcher;
    }
}

