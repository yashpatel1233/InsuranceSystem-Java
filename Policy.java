package nz.ac.auckland.se281;

public abstract class Policy {

  public enum TypeOfPolicy {
    HOME,
    CAR,
    LIFE,
  }

  // Initialising variables
  protected String sumInsured;
  protected String premium;
  protected TypeOfPolicy type;

  // Constructor for the Policy class
  public Policy(TypeOfPolicy type, String sumInsured, String premium) {
    // TODO Auto-generated constructor stub

    this.type = type;
    this.sumInsured = sumInsured;
    this.premium = premium;
  }

  // Method to get the sum insured
  public String getSumInsured() {
    return sumInsured;
  }

  // Method to get the premium
  public String getPremium() {
    return premium;
  }

  // Method to get the type of policy
  public TypeOfPolicy getType() {
    return type;
  }

  // Method to print the policy

  public abstract void printPolicy(double discount);
}
