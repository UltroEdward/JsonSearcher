package helpers;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
	
	
	//TODO: fast implementation, need refactor
	public static <T> List<List<T>> splitList(List <T> listForSplit, int counts){
		List <List<T>> splittedLists = new ArrayList<>();
		
		int commonSize = counts/listForSplit.size();
		
		if (commonSize == 0){
			commonSize = 1;
		}
		
		int end = 0;
		
		for(int i = 0; i < counts; i++){
			if(i == counts-1){
				splittedLists.add(listForSplit.subList(end, listForSplit.size()));
				break;
			}
			splittedLists.add(listForSplit.subList(end, commonSize));
			end = commonSize;
			commonSize = commonSize*2;
		}
		return splittedLists;
		
	}

}
