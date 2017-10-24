package helpers;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {

    // TODO: make this method better?
    public static <T> List<List<T>> splitList(List<T> listForSplit, int counts) {

        List<List<T>> splitLists = new ArrayList<>(counts);

        for (int i = 0; i < counts; i++) {
            splitLists.add(new ArrayList<T>());
        }

        while (listForSplit.size() > 0) {

            for (int i = 0; i < counts; i++) {
                if (listForSplit.size() == 0) {
                    break;
                }
                splitLists.get(i).add(listForSplit.get(0));
                listForSplit.remove(0);
            }
        }

        return splitLists;
    }

}
