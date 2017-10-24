package processor;

import file.reader.StringMatcher;
import helpers.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import workers.MatcherWorker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MainProcessor {

    private static final String DESIRED_EXTENSION = ".txt";
    private static final String SCAN_FOLDER = "E:\\Test";
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private static final Logger Log = LoggerFactory.getLogger(MainProcessor.class);

    public static void main(String[] args) throws FileNotFoundException {
        MainProcessor processor = new MainProcessor();
        List<File> filesToRead = FileUtils.getFilesPaths(SCAN_FOLDER, DESIRED_EXTENSION);
        processor.execute(filesToRead);
    }

    private void execute(List<File> filesToRead) {

        //for testing, test data can be generated via:
        //JsonGerenator.generateTestData(SCAN_FOLDER, JsonGerenator.SAMPLE_JSON_WITH_PROBLEMS, 10, 1000000);

        Log.info(String.format("### Starting processing %d file(-s) with extension [%s] on [%d] threads ###", filesToRead.size(), DESIRED_EXTENSION, THREAD_COUNT));
        double startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        List<Future<StringMatcher>> features = filesToRead.stream().map(file -> {
            return executor.submit(new MatcherWorker(file));
        }).collect(Collectors.toList());
        executor.shutdown();

        try {
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Log.error("Execution not finished", e);
            executor.shutdownNow();
        }

        Log.info(String.format("### Processing is done with %f seconds ###", (System.currentTimeMillis() - startTime) / 1000));
    }

}
