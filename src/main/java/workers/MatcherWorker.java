package workers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
				matcher.findMatches(new FileInputStream(jsonFile));
				Log.info(String.format("File %s processed with following matches: %s", jsonFile.getName(), matcher.matches));
			} catch (FileNotFoundException e) {
				Log.error(String.format("IO exception happens for file: %s. \n Error: %s", jsonFile.getName(), e.getMessage()));
			} finally {

			}
		}
		latch.countDown();
	}

}
