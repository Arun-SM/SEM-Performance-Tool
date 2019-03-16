package com.arun.semperf.analysis.business;

import java.util.ArrayList;
import java.util.Optional;
import com.arun.semperf.analysis.pojos.Keyword;

/**
 * A class that stores all the new keywords seen in the input file. If an entry is present a
 * Keyword object is returned otherwise a new one is created.
 * 
 * It's a singleton instance.
 * 
 * @author arunasm
 *
 */
public class KeywordStore {

  private final ArrayList<Keyword> keywords;
  private static KeywordStore keywordStore = null;

  private KeywordStore() {
    keywords = new ArrayList<Keyword>();
  }

  public static KeywordStore getInstance() {
    if (null == keywordStore) {
      keywordStore = new KeywordStore();
    }
    return keywordStore;
  }

  public Keyword getKeyword(String keywordName) {
    // Return the instance of keyword which matches given string.
    // Insert to the list if there's no match.
    Keyword toReturn = null;
    Optional<Keyword> keyword =
        keywords.stream().filter(key -> key.getName().equals(keywordName)).findFirst();
    if (!keyword.isPresent()) {
      Keyword newKeyword = new Keyword(keywordName);
      keywords.add(newKeyword);
      toReturn = newKeyword;
    } else {
      toReturn = keyword.get();
    }
    return toReturn;
  }
}
