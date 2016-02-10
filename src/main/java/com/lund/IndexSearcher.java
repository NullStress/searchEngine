package com.lund;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Kristian
 * Date: 10.02.2016
 * Time: 18:58
 */
public class IndexSearcher {

  public static Map<String, Integer> computeSearchMatchCount(String[] searchWords, Map<String,Map<String, Integer>> indexMap) {
    Map<String, Integer> searchFreq = new HashMap<>();
    for(String word: searchWords) {
      searchFreq.compute(word, (k,v) -> v == null ? 1 : v + 1);
    }
    Map<String, Integer> searchMatchCount = new HashMap<>();
    searchFreq.forEach((word, freq) -> {
              if(indexMap.containsKey(word)) {
                Map<String, Integer> wordEntry = indexMap.get(word);
                wordEntry.forEach((key, value) -> {
                  searchMatchCount.compute(key, (k,v) -> v == null ? freq : v + freq);
                });
              }
            }

    );
    return searchMatchCount.entrySet()
            .stream()
            .sorted(Comparator.comparing(Map.Entry::getValue))
            .limit(10)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}
