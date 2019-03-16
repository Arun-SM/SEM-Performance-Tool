package com.arun.semperf.analysis.pojos;

/**
 * Comprises of data read from file "Impression", "CTR", "Cost", "Position", "Revenue" Helps in
 * computing metrics done on the read data.
 * 
 * Note: Company details are not stored, as and when we read the data it's processed and handled
 * accordingly.
 * 
 * @author arunasm
 *
 */
public class AnalyticsData {

  private int impression;
  private double ctr;
  private double cost;
  private int position;
  private double revenue;
  private double clicks;
  private double revenuePerClick;

  public AnalyticsData(final double cost, final double ctr, final int impression,
      final int position, final double revenue) {
    this.impression = impression;
    this.ctr = ctr;
    this.cost = cost;
    this.position = position;
    this.revenue = revenue;
  }

  public final double getCost() {
    return cost;
  }

  public final double getCtr() {
    return ctr;
  }

  public final int getImpression() {
    return impression;
  }

  public final int getPosition() {
    return position;
  }

  public final double getRevenue() {
    return revenue;
  }

  public final double getClicks() {
    return clicks;
  }

  public final double getRevenuePerClick() {
    return revenuePerClick;
  }

  public void setClicks(final double clicks) {
    this.clicks = clicks;
  }

  public void setRevenuePerClick(final double revenuePerClick) {
    this.revenuePerClick = revenuePerClick;
  }
}
