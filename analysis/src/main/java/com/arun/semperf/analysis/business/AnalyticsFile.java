package com.arun.semperf.analysis.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import com.arun.semperf.analysis.constants.WordPosition;
import com.arun.semperf.analysis.exceptions.FileReadException;
import com.arun.semperf.analysis.pojos.AnalyticsData;
import com.arun.semperf.analysis.pojos.Company;
import com.arun.semperf.analysis.pojos.DataEntry;
import com.arun.semperf.analysis.pojos.Keyword;

/**
 * A class to perform operations on a file. Provides operations such as reading a line at a time,
 * parsing line
 * 
 * @author arunasm
 *
 */
public class AnalyticsFile {

  private final String filename;
  private final KeywordStore keywordStore;
  private final CompaniesStore companiesStore;
  private BufferedReader reader = null;

  public AnalyticsFile(final String filename) {
    this.filename = filename;
    this.companiesStore = CompaniesStore.getInstance();
    this.keywordStore = KeywordStore.getInstance();
  }

  public final String getFilename() {
    return filename;
  }

  public Optional<DataEntry> getNextDataEntry() {
    try {
      if (null == reader) {
        // Throw if file is not found
        reader = new BufferedReader(new FileReader(this.getFilename()));
        // First line is the header in excel sheet
        reader.readLine();
      }

      // Data is present from second line onwards
      String line = reader.readLine();

      if (line == null) {
        // reached end of the file
        reader.close();
        return Optional.empty();
      }
      // Get DataEntry object from line
      return Optional.of(getDataEntry(line));
    } catch (IOException e) {
      // Convert to custom exception
      // TODO: Try to avoid such conversions
      throw new FileReadException("Bad happened while reading the file: " + e);
    }
  }

  private DataEntry getDataEntry(String line) {
    String[] words = line.split(",");

    // WordPosition enum has knowledge on which index each of the word is stored
    Keyword keyword = keywordStore.getKeyword(words[WordPosition.KEYWORD.getValue()]);
    Company company = companiesStore.getCompany(words[WordPosition.COMPANY.getValue()]);
    double cost = Double.parseDouble(words[WordPosition.COST.getValue()]);
    double ctr = Double.parseDouble(words[WordPosition.CTR.getValue()]);
    int impression = Integer.parseInt(words[WordPosition.IMPRESSION.getValue()]);
    int position = Integer.parseInt(words[WordPosition.POSITION.getValue()]);
    double revenue = Double.parseDouble(words[WordPosition.REVENUE.getValue()]);
    AnalyticsData analyticsData = new AnalyticsData(cost, ctr, impression, position, revenue);

    // Create DataEntry object and return
    DataEntry dataEntry = DataEntry.getInstance();
    dataEntry.setDataEntry(company, keyword, analyticsData);
    return dataEntry;
  }
}
