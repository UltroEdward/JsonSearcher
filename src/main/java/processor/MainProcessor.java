package processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import file.reader.RecordStringMatcher;
import helpers.CollectionUtils;
import helpers.FileUtils;
import helpers.JsonGerenator;
import workers.MatcherWorker;

public class MainProcessor {

	public static final String DESIRED_EXTENSION = ".txt";
	public static final Logger Log = LoggerFactory.getLogger(MainProcessor.class);

	public static void main(String[] args) throws FileNotFoundException {

		final String folferToSearch = "E:\\Test"; // args[0];
		// generateTestData(folferToSearch,JsonGerenator.SAMPLE_JSON_WITH_PROBLEMS, 1, 2500000);
		List<File> filesToRead = FileUtils.getFilesPathes(folferToSearch, DESIRED_EXTENSION);

		Log.info(String.format("### Starting processing %d file(-s) in folder [%s] with extension [%s] ###", filesToRead.size(), folferToSearch, DESIRED_EXTENSION));
		double startTime = System.currentTimeMillis();

		int cores = 4;
		final List<List<File>> smallerLists = CollectionUtils.splitList(filesToRead, cores);
		
		try{
            CountDownLatch latch = new CountDownLatch(cores);
            
            for (int n=0; n<cores; n++) {
                Thread t = new Thread(new MatcherWorker(smallerLists.get(n),latch));
                t.start();
            }
            latch.await();
        }catch(Exception err){
            err.printStackTrace();
        }
		
		Log.info(String.format("### Processing is done with %f seconds ###", (System.currentTimeMillis() - startTime) / 60));

	}

	private static void generateTestData(String folder, String jsonToDuplicate, int filesCount, int rowsCountPerFile) {
		JsonGerenator generator = new JsonGerenator();
		List<File> files = new ArrayList<>();

		for (int i = 0; i < filesCount; i++) {
			files.add(new File(String.format("%s//TestFile_%d_%d.txt", folder, i, rowsCountPerFile)));
		}

		for (File sampleFile : files) {
			generator.arrayBuilder(sampleFile, jsonToDuplicate, rowsCountPerFile);
		}

		Log.info(String.format("Files %d created", filesCount));
	}

}
