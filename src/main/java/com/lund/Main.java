package com.lund;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    if (args.length == 0 ) {
      throw new IllegalArgumentException( "No directory given to index." );
    }
    final File indexableDirectory = new File (args[ 0 ]);

    if(!indexableDirectory.isDirectory()) {
      throw new IllegalArgumentException( "Not a valid directory given to index." );
    }
    Indexer indexer = new Indexer();
    indexer.setGlobalMap(indexer.createIndexFromDirectory(indexableDirectory));

    Scanner keyboard = new Scanner(System.in);
    while (true) {
      System.out.print("search> ");
      final String line = keyboard.nextLine();
      String[] searchWords = line.split("\\s+");
      Map<String, Integer> searchMatchCount = IndexSearcher.computeSearchMatchCount(searchWords, indexer.getGlobalMap());
      if(searchMatchCount.size() == 0) {
        System.out.println("no matches found");
      } else {
        searchMatchCount.forEach((docName, matchCount) -> {
          System.out.println(docName + " : " + ((matchCount.doubleValue()/searchWords.length) * 100) + "%");
        });
      }
    }
  }

}