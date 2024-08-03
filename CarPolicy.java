package nz.ac.auckland.se281;

public class CarPolicy extends Policy {
  // initialising variables
  private String carMake;
  private String licensePlate;

  // constructor for the CarPolicy class
  public CarPolicy(String sumInsured, String premium, String carMake, String licensePlate) {
    super(TypeOfPolicy.CAR, sumInsured, premium);
    this.carMake = carMake;
    this.licensePlate = licensePlate;
  }

  // method to get the car make
  public String getCarMake() {
    return carMake;
  }

  // method to get the license plate
  public String getLicensePlate() {
    return licensePlate;
  }

  @Override
  public void printPolicy(double discount) {
    // System.out.println();
    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
        carMake,
        sumInsured,
        premium,
        String.format("%.0f", (discount * Double.parseDouble(premium))));
  }
}
