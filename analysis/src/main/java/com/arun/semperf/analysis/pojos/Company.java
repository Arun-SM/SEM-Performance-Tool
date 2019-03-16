package com.arun.semperf.analysis.pojos;

/**
 * Company defines attributes the analysis tool would like to track about a company.
 * 
 * It includes things like overall profit margin/performance metric, number of times a company has
 * appeared in the search engine,
 * 
 * @author arunasm
 *
 */
public class Company {

  private final String name;
  private double performanceMetric = 0.0;
  private int totalAppearances = 0;
  private double performanceRank;

  public Company(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public double getPerformanceMetric() {
    return performanceMetric;
  }

  public int getTotalAppearances() {
    return totalAppearances;
  }

  public double getPerformanceRank() {
    return performanceRank;
  }

  public void addPerformanceMetric(final double performanceMetric) {
    this.performanceMetric += performanceMetric;
  }

  public void markAppeared() {
    this.totalAppearances += 1;
  }

  public void setPerformanceRank(final double performanceRank) {
    this.performanceRank = performanceRank;
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
    Company company = (Company) obj;
    if (!company.getName().equals(this.getName())) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return this.getName() + " : " + this.getPerformanceRank();
  }
}
