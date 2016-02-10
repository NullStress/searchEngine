package com.lund;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kristian
 * Date: 10.02.2016
 * Time: 18:41
 */
public class Indexer {

  private Map<String,Map<String, Integer>> globalMap;

  public Indexer() {
    this.globalMap = new HashMap<>();
  }

  public Map<String,Map<String, Integer>> createIndexFromDirectory(File directory) {
    Map<String,Map<String, Integer>> indexMap = new HashMap<>();
    String[] filesToIndex = directory.list();
    System.out.println("Reading " + filesToIndex.length + " files into index from " + directory.getAbsolutePath());

    for(String fileName : filesToIndex) {
      Map<String, Integer> wordMap = indexFilePath(directory.getAbsolutePath() + "\\" + fileName);
      addWordMapToIndexMap(wordMap, indexMap, fileName);
    }
    return indexMap;
  }

  public Map<String,Map<String, Integer>> addWordMapToIndexMap(Map<String, Integer> wordMap, Map<String,Map<String, Integer>> indexMap, String fileName) {
    wordMap.forEach((key, value) -> {
      Map<String, Integer> indexEntryForWord;
      if(indexMap.containsKey(key)){
        indexEntryForWord = indexMap.get(key);
      } else {
        indexEntryForWord = new HashMap<>();
      }
      indexEntryForWord.put(fileName, value);
      indexMap.put(key, indexEntryForWord);
    });
    return indexMap;
  }

  public Map<String, Integer> indexFilePath(String filePath) {
    Map<String, Integer> wordMap = new HashMap<>();
    try {
      Files.lines(Paths.get(filePath)).forEach(line -> indexLine(line, wordMap));
    } catch (IOException io) {
      System.out.println("Could not read file " + filePath);
    }

    return wordMap;
  }

  public Map<String, Integer> indexLine(String line, Map<String, Integer> wordMap) {
    for(String word : line.split("\\s+")) {
      String tmp = word.toLowerCase().replaceAll("[^a-z]", "");
      if(wordMap.containsKey(tmp)) {
        wordMap.put(tmp, wordMap.get(tmp) + 1);
      } else {
        wordMap.put(tmp, 1);
      }
    }
    return wordMap;
  }

  public void setGlobalMap(Map<String, Map<String, Integer>> globalMap) {
    this.globalMap = globalMap;
  }

  public Map<String, Map<String, Integer>> getGlobalMap() {
    return globalMap;
  }

}
