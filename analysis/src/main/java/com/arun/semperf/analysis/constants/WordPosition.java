package com.arun.semperf.analysis.constants;

/**
 * Defines position of different words present in the input file
 * 
 * @author arunasm
 *
 */
public enum WordPosition {
  KEYWORD(0), IMPRESSION(1), CTR(2), COST(3),
  // This is index position, not to be confused with the enum name
  POSITION(4), COMPANY(5), REVENUE(6);

  int wordPosition;

  WordPosition(final int wordPosition) {
    this.wordPosition = wordPosition;
  }
  
  public int getValue() {
    return wordPosition;
  }
}
