package processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import helpers.CollectionUtils;
import helpers.FileUtils;
import workers.MatcherWorker;

public class MainProcessor {

	public static final String DESIRED_EXTENSION = ".txt";
	public static final int THREAD_COUNT = 1; // Runtime.getRuntime().availableProcessors();
	public static final Logger Log = LoggerFactory.getLogger(MainProcessor.class);

	public static void main(String[] args) throws FileNotFoundException {

		final String folferToSearch = "E:\\Test"; // args[0];
		//JsonGerenator.generateTestData(folferToSearch,JsonGerenator.SAMPLE_JSON_WITH_PROBLEMS, 10, 2500000);
		List<File> filesToRead = FileUtils.getFilesPaths(folferToSearch, DESIRED_EXTENSION);

		Log.info(String.format("### Starting processing %d file(-s) in folder [%s] with extension [%s] on [%d] threads ###", filesToRead.size(), folferToSearch, DESIRED_EXTENSION, THREAD_COUNT));
		double startTime = System.currentTimeMillis();

		final List<List<File>> smallerLists = CollectionUtils.splitList(filesToRead, THREAD_COUNT);

		try {
			CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

			for (int n = 0; n < THREAD_COUNT; n++) {
				Thread worker = new Thread(new MatcherWorker(smallerLists.get(n), latch));
				worker.start();
			}
			latch.await();
		} catch (Exception err) {
			Log.error("Uh-OH! Something wrong! Maybe it makes sense: " + err.getMessage());
		}

		Log.info(String.format("### Processing is done with %f seconds ###", (System.currentTimeMillis() - startTime) / 1000));
	}



}
