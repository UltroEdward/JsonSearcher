package helpers;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static List<File> getFilesPaths(String folderToSearch, final String desiredFileExtension) {
        File dir = new File(folderToSearch);
        File[] matches = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(desiredFileExtension);
            }
        });

        if (matches == null || matches.length == 0) {
            return new ArrayList<File>();
        }
        return new ArrayList<File>(Arrays.asList(matches));
    }

}
