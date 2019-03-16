package com.arun.semperf.analysis;

import com.arun.semperf.analysis.business.DataAnalyzer;
import com.arun.semperf.analysis.exceptions.InvalidArgumentException;
import com.arun.semperf.analysis.exceptions.KnownException;

// This tool will perform analysis of organizations
// Input will be a CSV file with following details
// "Search Keyword", "Impression", "CTR", "Cost", "Position", "Company", "Revenue"
public class App {

  public static void main(String[] args) {
    try {
      if (args.length < 2) {
        throw new InvalidArgumentException("Invalid number of arguments");
      }
      // Read input from CSV data file
      DataAnalyzer.performAnalysis(args[1]);
    } catch (KnownException e) {
      System.out.println("Program exited with error: " + e.getMessage());
      System.exit(e.getErrorCode());
    } catch (Exception e) {
      System.out.println("Unknown error occurred: " + e);
      System.exit(1);
    }
    // Successfully completed execution
  }
}
