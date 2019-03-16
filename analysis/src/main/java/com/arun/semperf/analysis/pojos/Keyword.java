package com.arun.semperf.analysis.pojos;

/**
 * This class contains information about keyword. It's used to profile in information about each
 * keyword. It includes information such as keyword searched for.
 * 
 * @author arunasm
 *
 */
public class Keyword {

  private final String name;
  // Total impression for this keyword
  private int impressions = 0;

  public Keyword(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getImpressions() {
    return impressions;
  }

  public void addImpressions(final int impressions) {
    this.impressions += impressions;
  }

  @Override
  public int hashCode() {
    // hashCode of keyword is guaranteed to be unique
    // A good analytics grouping would have grouped similar
    // data and given distinct keywords
    return getName().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (null == obj) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    Keyword key = (Keyword) obj;
    if (!key.getName().equals(this.getName())) {
      return false;
    }
    return true;
  }
}
