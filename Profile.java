package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {

  // Initialising variables
  private String username;
  private String age;
  private int amountOfPolicies;
  private double discount;
  ArrayList<Policy> policies;
  boolean hasLifePolicy;
  private double sum;

  // Constructor for the Profile class
  public Profile(String username, String age) {
    this.username = username;
    this.age = age;
    this.amountOfPolicies = 0;
    this.policies = new ArrayList<Policy>();
    this.sum = 0;
  }

  // Method to get the username
  public String getUsername() {
    return username;
  }

  // Method to add to the amount of policies
  public void addPolicy(Policy policy) {
    amountOfPolicies++;
    policies.add(policy);
    sum = sum + Double.parseDouble(policy.getPremium());
  }

  // Method to get the sum of the premiums
  public double getSum() {
    return sum;
  }

  // Method to get the amount of policies
  public int getAmountOfPolicies() {
    return amountOfPolicies;
  }

  // Method to get the policies
  public ArrayList<Policy> getPolicies() {
    return policies;
  }

  // Method to set the hasLifePolicy variable
  public void setHasLifePolicy(boolean hasLifePolicy) {
    this.hasLifePolicy = hasLifePolicy;
  }

  // Method to get the hasLifePolicy variable
  public boolean getHasLifePolicy() {
    return hasLifePolicy;
  }

  // Method to calulate the discount where if amount is 2 it is a 10% discount and if its 3 or more
  // its a 20% discount
  public double calculateAndReturnDiscount() {
    if (amountOfPolicies == 2) {
      // discount set for 10% off
      discount = 0.9;
    } else if (amountOfPolicies >= 3) {
      discount = 0.8;
      // discount set for 20% off
    } else {
      // Discount set to 1 as no discount is applied
      discount = 1;
    }
    return discount;
  }

  // Method to get the age
  public String getAge() {
    return age;
  }
}
