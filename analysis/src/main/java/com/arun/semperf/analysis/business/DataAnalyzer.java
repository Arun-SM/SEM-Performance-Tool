package com.arun.semperf.analysis.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import com.arun.semperf.analysis.exceptions.DuplicateEntryException;
import com.arun.semperf.analysis.pojos.AnalyticsData;
import com.arun.semperf.analysis.pojos.Company;
import com.arun.semperf.analysis.pojos.DataEntry;
import com.arun.semperf.analysis.pojos.Keyword;
import javafx.util.Pair;

/**
 * Has core business logic which applies the metric calculation for companies ranking
 * 
 * @author arunasm
 *
 */
public class DataAnalyzer {

  private static DataAnalyzer dataAnalyzer = null;

  private DataAnalyzer() {
    super();
    // do nothing;
  }

  private static DataAnalyzer getInstance() {
    if (null == dataAnalyzer) {
      dataAnalyzer = new DataAnalyzer();
    }
    return dataAnalyzer;
  }

  // Core business logic, the metric is computed based on the logic we have come up with
  private double getApproximateClicks(AnalyticsData analyticsData) {
    return analyticsData.getCtr() * analyticsData.getImpression();
  }

  public static void performAnalysis(String dataFile) {
    // Data is already available in a file. Tool focuses on analytics part
    // than storing the data for later purposes.
    // Tool is intended to be optimized to read and process the data.
    DataAnalyzer dataAnalyzer = DataAnalyzer.getInstance();

    // Maintain a hashmap of keyword against list of companies. This is required
    // to avoid duplicate entries.
    /*
     * TODO: Duplicate entries are raised as an exception with current code. This of better
     * handling.
     */
    HashMap<Keyword, ArrayList<Pair<Company, AnalyticsData>>> keywordsParsed = new HashMap<>();

    AnalyticsFile file = new AnalyticsFile(dataFile);
    // read one entry at a time from the file
    Optional<DataEntry> dataEntry = file.getNextDataEntry();
    while (dataEntry.isPresent()) {
      DataEntry entry = dataEntry.get();
      Company company = entry.getCompany();
      Keyword keyword = entry.getKeyword();
      AnalyticsData analyticsData = entry.getAnalyticsData();

      // Apply DataAnalytics result to the Company
      double clicks = dataAnalyzer.getApproximateClicks(analyticsData);
      analyticsData.setClicks(clicks);
      double revenuePerClick = entry.getAnalyticsData().getRevenue() / clicks;
      analyticsData.setRevenuePerClick(revenuePerClick);

      // Increase total impressions for a keyword
      keyword.addImpressions(analyticsData.getImpression());
      // Increase total appearances for a company
      company.markAppeared();

      // Detect duplicate entry
      ArrayList<Pair<Company, AnalyticsData>> companiesPairParsed = null;
      if (keywordsParsed.containsKey(keyword)) {
        companiesPairParsed = keywordsParsed.get(keyword);
        if (null == companiesPairParsed) {
          companiesPairParsed = new ArrayList<>();
        }
        if (companiesPairParsed.stream().anyMatch(param -> param.getKey().equals(company))) {
          // Duplicate entry detected
          throw new DuplicateEntryException("A duplicate entry for the keyword " + keyword.getName()
              + " is found for the company " + company.getName());
        }
        companiesPairParsed.add(new Pair<Company, AnalyticsData>(company, analyticsData));
      }
      keywordsParsed.put(keyword, companiesPairParsed);
    }

    // Find total impressions for a keyword
    for (Keyword key : keywordsParsed.keySet()) {
      ArrayList<Pair<Company, AnalyticsData>> companiesPairParsed = keywordsParsed.get(key);
      companiesPairParsed.stream().forEach(entry -> {
        int totalImpressionsForKeyword = key.getImpressions();
        Company company = entry.getKey();
        AnalyticsData analyticsData = entry.getValue();
        double clicksPerTotalImpression = analyticsData.getClicks() / totalImpressionsForKeyword;
        double costIfPaidPerClick = analyticsData.getCost() / clicksPerTotalImpression;
        double performanceMetric = analyticsData.getRevenuePerClick() - costIfPaidPerClick;
        company.addPerformanceMetric(performanceMetric);
      });
    }

    // Analysis is done, company profiles will have all the data
    // Find the average and rank them
    CompaniesStore companiesStore = CompaniesStore.getInstance();
    ArrayList<Company> companies = companiesStore.getCompanies();
    companies.stream().forEach(company -> company
        .setPerformanceRank(company.getPerformanceMetric() / company.getTotalAppearances()));

    // Sort companies in ascending order of their performance rank
    Collections.sort(companies, new Comparator<Company>() {
      @Override
      public int compare(Company com1, Company com2) {
        return Double.compare(com1.getPerformanceRank(), com2.getPerformanceRank());
      }
    });

    printCompanyPerformance(companies);
  }

  private static void printCompanyPerformance(ArrayList<Company> companies) {
    for (Company company : companies) {
      System.out.println(company);
    }
  }

}
