package com.arun.semperf.analysis.pojos;

/**
 * POJO that reads one entry at a time from the input file. It's a singleton class.
 * 
 * @author arunasm
 *
 */
public class DataEntry {

  private static DataEntry dataEntry = null;
  private Company company;
  private Keyword keyword;
  private AnalyticsData analyticsData;

  private DataEntry() {
    // do nothing;
    super();
  }

  public final AnalyticsData getAnalyticsData() {
    return analyticsData;
  }

  public final Company getCompany() {
    return company;
  }

  public static DataEntry getInstance() {
    if (null == dataEntry) {
      dataEntry = new DataEntry();
    }
    return dataEntry;
  }

  public final Keyword getKeyword() {
    return keyword;
  }

  public void setDataEntry(final Company company, final Keyword keyword,
      final AnalyticsData analyticsData) {
    this.company = company;
    this.keyword = keyword;
    this.analyticsData = analyticsData;
  }
}
