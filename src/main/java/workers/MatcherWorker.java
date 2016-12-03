package workers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import file.reader.RecordStringMatcher;

public class MatcherWorker implements Runnable {

	public static final Logger Log = LoggerFactory.getLogger(MatcherWorker.class);
	private CountDownLatch latch;
	private List<File> filesToWorkWith;

	public MatcherWorker(List<File> filesToWorkWith, CountDownLatch latch) {
		this.latch = latch;
		this.filesToWorkWith = filesToWorkWith;
	}

	RecordStringMatcher matcher = new RecordStringMatcher();

	@Override
	public void run() {
		for (File jsonFile : filesToWorkWith) {
			RecordStringMatcher matcher = new RecordStringMatcher();
			try {
				double startTime = System.currentTimeMillis();
				BufferedReader reader = Files.newBufferedReader(jsonFile.toPath(), StandardCharsets.UTF_8);
				// matcher.findMatches(new FileInputStream(jsonFile));
				matcher.findMatches(reader);
				Log.info(String.format("File %s processed on [%s] thread with time [%s sec] and following matches: %s ", jsonFile.getName(),
						Thread.currentThread().getName(), ((System.currentTimeMillis() - startTime) / 1000), matcher.getMatches()));
			} catch (IOException e) {
				Log.error(String.format("IO exception happens for file: %s. \n Error: %s", jsonFile.getName(), e.getMessage()));
			} finally {

			}
		}
		latch.countDown();
	}

}
