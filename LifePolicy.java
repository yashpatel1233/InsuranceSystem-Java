package nz.ac.auckland.se281;

public class LifePolicy extends Policy {

  // Constructor for the LifePolicy class
  public LifePolicy(String sumInsured, String premium) {
    super(TypeOfPolicy.LIFE, sumInsured, premium);
  }

  @Override
  public void printPolicy(double discount) {
    // System.out.println();
    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
        sumInsured, premium, String.format("%.0f", (discount * Double.parseDouble(premium))));
  }
}
