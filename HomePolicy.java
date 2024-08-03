package nz.ac.auckland.se281;

public class HomePolicy extends Policy {

  // Initialising variables
  private String propertyAddress;

  // Constructor for the HomePolicy class
  public HomePolicy(String sumInsured, String premium, String propertyAddress) {
    super(TypeOfPolicy.HOME, sumInsured, premium);
    this.propertyAddress = propertyAddress;
  }

  // Method to get the property address
  public String getPropertyAddress() {
    return propertyAddress;
  }

  public String getPremiumWithDiscount() {
    return null;
  }

  @Override
  public void printPolicy(double discount) {
    // System.out.println();
    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
        propertyAddress,
        sumInsured,
        premium,
        String.format("%.0f", (discount * Double.parseDouble(premium))));
  }
}
