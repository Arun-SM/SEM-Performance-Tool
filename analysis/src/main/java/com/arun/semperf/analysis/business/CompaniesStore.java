package com.arun.semperf.analysis.business;

import java.util.ArrayList;
import java.util.Optional;
import com.arun.semperf.analysis.pojos.Company;

/**
 * A class that stores all the new company names seen in the input file. If an entry is present a
 * Company object is returned otherwise a new one is created.
 * 
 * It's a singleton instance.
 * 
 * @author arunasm
 *
 */
public class CompaniesStore {

  private final ArrayList<Company> companies;
  private static CompaniesStore companiesStore = null;

  private CompaniesStore() {
    companies = new ArrayList<Company>();
  }

  public static CompaniesStore getInstance() {
    if (null == companiesStore) {
      companiesStore = new CompaniesStore();
    }
    return companiesStore;
  }
  
  public ArrayList<Company> getCompanies() {
    return companies;
  }

  public Company getCompany(String companyName) {
    // Return the instance of company which matches given string.
    // Insert to the list if there's no match.
    Company toReturn = null;
    Optional<Company> company =
        companies.stream().filter(comp -> comp.getName().equals(companyName)).findFirst();
    if (!company.isPresent()) {
      Company newCompany = new Company(companyName);
      companies.add(newCompany);
      toReturn = newCompany;
    } else {
      toReturn = company.get();
    }
    return toReturn;
  }
}
