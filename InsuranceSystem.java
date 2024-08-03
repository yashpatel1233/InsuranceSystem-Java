package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // Initialising ArrayLists for proile name and ages
  private ArrayList<String> nameList = new ArrayList<String>();
  private ArrayList<String> ageList = new ArrayList<String>();

  // boolean to check if a profile is loaded
  private boolean profileLoaded = false;

  // String to store the name of the loaded profile
  private String loadedProfileName;

  // String to store the age of the loaded profile
  private String loadedProfileAge;

  // ArrayList to store the profiles
  private ArrayList<Profile> profiles = new ArrayList<Profile>();

  // ArrayList to store the policiesfor printing
  private ArrayList<Policy> policies;

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
    // String nameList;

  }

  // Assigns fiseduserName as the userName in the correct format
  public String fixUser(String userName) {
    // method to fix the userName to the correct format
    String fixeduserName =
        userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

    return fixeduserName;
  }

  // Method to print a profile
  public void printProfile(int index) {

    // string pluralPolicy initialised
    String pluralPolicy;
    // gets amount of policies in the profile
    String policyAmount = Integer.toString(profiles.get(index).getAmountOfPolicies());

    // The order number of the profile
    int orderNumber = index + 1;
    String stringOrderNumber = Integer.toString(orderNumber);

    // if loop to check if there is one or more policies and assign a boolean to the int one
    // or more

    double discount = profiles.get(index).calculateAndReturnDiscount();

    // Get the sum of the premiums
    double sum = profiles.get(index).getSum();

    String discountedPremium = String.format("%.0f", sum * discount);

    if (Integer.parseInt(policyAmount) == 1) {
      pluralPolicy = "y";
    } else {
      pluralPolicy = "ies";
    }

    // if the profile is the loaded profile, it prints a different message
    if (profiles.get(index).getUsername().equals(loadedProfileName)) {
      MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
          "*** ",
          stringOrderNumber,
          nameList.get(index),
          ageList.get(index),
          policyAmount,
          pluralPolicy,
          discountedPremium);

      // print the policies for the index profile
      policies = profiles.get(index).getPolicies();
      // for loop to print the policies
      for (int i = 0; i < profiles.get(index).getAmountOfPolicies(); i++) {
        // prints the policy
        policies.get(i).printPolicy(discount);
        // if the policy is home it prints a different message

      }

    } else {
      // prints the normal message
      MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
          "",
          stringOrderNumber,
          nameList.get(index),
          ageList.get(index),
          policyAmount,
          pluralPolicy,
          discountedPremium);

      policies = profiles.get(index).getPolicies();
      // for loop to print the policies
      for (int i = 0; i < policies.size(); i++) {
        // prints the policy
        policies.get(i).printPolicy(discount);
      }
    }
  }

  public void printDatabase() {
    // TODO: Complete this method.

    // if loop checks whether there are any profiles in the database and prints a suitable response
    if (nameList.size() == 0) {
      // Prints saying that there are zero profiles
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");

      // prints saying there is one profile
    } else if (nameList.size() == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");

      // prints the profile
      printProfile(0);

    } else {
      // prints a plural profiles and prints a list of profiles
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(nameList.size()), "s", ":");
      for (int i = 0; i < nameList.size(); i++) {
        // prints the profile
        printProfile(i);
      }
    }
  }

  public void createNewProfile(String userName, String age) {
    // TODO: Complete this method.

    // checks whether there is a profile loaded and prints a suitable message
    if (profileLoaded == true) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(loadedProfileName);

    } else {
      String fixeduserName = fixUser(userName);

      // Checks the length of the name to check validity then prints the suitable message
      if (fixeduserName.length() < 3) {
        MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(fixeduserName);

        // Checks whether an identical proile userName is already present
      } else if (nameList.contains(fixeduserName) == true) {
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(fixeduserName);

        // Checks whether age is a positive integer to allow the processing of the profile
      } else if (Integer.parseInt(age) < 0) {
        MessageCli.INVALID_AGE.printMessage(age, fixeduserName);

        // Once all the possible invalid terms have been checked the profile gets processed
      } else {
        // Username is added to the arraylist of names
        nameList.add(fixeduserName);
        // age is added in the corresponding index into ageList the same as the nameList
        ageList.add(age);

        newPolicyProfile(fixeduserName, age);

        // prints the profile created message
        MessageCli.PROFILE_CREATED.printMessage(fixeduserName, age);
      }
    }
  }

  private void newPolicyProfile(String fixeduserName, String age) {
    // Create a new profile using profile class
    Profile newProfile = new Profile(fixeduserName, age);

    // Add the new profile to the profiles arraylist
    profiles.add(newProfile);
  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.
    String fixedUserName = fixUser(userName);
    // Checks whether the profile is in the database
    if (nameList.contains(fixedUserName) == true) {

      // prints load profile message
      MessageCli.PROFILE_LOADED.printMessage(fixedUserName);

      // Since the profile is in the database it is set to true as it is loaded successfully
      profileLoaded = true;

      // sets loadedProfileName to the fixedUserName
      loadedProfileName = fixedUserName;

      // find index of the profile in the ageList
      int index = nameList.indexOf(fixedUserName);

      // sets loadedProfileAge to the age of the profile
      loadedProfileAge = ageList.get(index);

      // loadedProfile = profiles.get(index);

    } else {
      // print no profile found message
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(fixedUserName);
    }
  }

  public void unloadProfile() {
    // TODO: Complete this method.
    // checks whether a profile is loaded
    if (profileLoaded == true) {
      // prints the unload profile message
      MessageCli.PROFILE_UNLOADED.printMessage(loadedProfileName);

      // boolean is set to false for the loading of the profile
      profileLoaded = false;

      // nulls the loaded name indicating no profile is loaded
      loadedProfileName = null;

      // loadedProfile = null;

    } else {
      // prints no profile loaded message
      MessageCli.NO_PROFILE_LOADED.printMessage();
    }
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
    String fixedUserName = fixUser(userName);

    // if fixedUserName is the same as the loadedProfileName it relays the cannot delete profile
    // while loaded
    if (fixedUserName.equals(loadedProfileName)) {

      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(fixedUserName);

      // checks whether fixedusername is in nameList and deletes the corresponding profile
    } else if (nameList.contains(fixedUserName) == true) {
      // gets the index of the profile to be deleted
      int index = nameList.indexOf(fixedUserName);

      // removes the profile from the nameList
      nameList.remove(index);

      // removes the profile from the ageList
      ageList.remove(index);

      // removes the profile from the profiles list
      deletePolicyProfile(index);

      // prints the profile deleted message
      MessageCli.PROFILE_DELETED.printMessage(fixedUserName);

    } else {
      // prints no profile found message
      MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(fixedUserName);
    }
  }

  private void deletePolicyProfile(int index) {
    // TODO Auto-generated method stub
    profiles.remove(index);
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
    // checks whether a profile is loaded
    if (profileLoaded == true) {
      // find the index of the loaded profile
      int index = nameList.indexOf(loadedProfileName);
      // If the type is home then it creates a home policy
      if (type == PolicyType.HOME) {
        // creates a home policy
        String premium;
        // if the rental is true than premium is 2% of the sum insured and if it is false than it is
        // 1%
        if (options[2].contains("y")) {
          premium = String.format("%.0f", ((Integer.parseInt(options[0])) * (0.02)));
        } else {
          premium = String.format("%.0f", ((Integer.parseInt(options[0])) * (0.01)));
        }
        HomePolicy homePolicy = new HomePolicy(options[0], premium, options[1]);

        // adds the home policy to the profile
        (profiles.get(index)).addPolicy(homePolicy);

        // prints the policy created message
        MessageCli.NEW_POLICY_CREATED.printMessage("home", loadedProfileName);

        // if the type is car then it creates a car policy
      } else if (type == PolicyType.CAR) {

        // initialises the premium
        String premium;

        // if the index age is less than 25 then the premium is 15% of the sum insured and if it is
        // above 25 then it is 10%
        if (Integer.parseInt(loadedProfileAge) < 25) {
          premium = String.format("%.0f", ((Integer.parseInt(options[0])) * (0.15)));
        } else {
          premium = String.format("%.0f", ((Integer.parseInt(options[0])) * (0.10)));
        }

        // if it is covered by mechanical breakdown insurance then the premium is 80 more
        if (options[3].contains("yes") || options[3].contains("y")) {
          premium = Integer.toString(Integer.parseInt(premium) + 80);
        }

        // creates a car policy
        CarPolicy carPolicy = new CarPolicy(options[0], premium, options[1], options[2]);

        // adds the car policy to the profile
        (profiles.get(index)).addPolicy(carPolicy);

        // prints the policy created message
        MessageCli.NEW_POLICY_CREATED.printMessage("car", loadedProfileName);
      } else if (type == PolicyType.LIFE) {
        // creates a life policy

        // if age is over 100 than print invalid age message or if the profile already has a life
        // policy than print already has a life policy
        if (Integer.parseInt(loadedProfileAge) > 100) {
          MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(loadedProfileName);

        } else if ((profiles.get(index).getHasLifePolicy()) == true) {
          MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(loadedProfileName);
        } else {
          // if the age is less than 100 and the profile does not have a life policy then it creates
          // a life policy
          // initialises the percentage
          double percentage = (1 + (Double.parseDouble(loadedProfileAge) / 100)) * 0.01;

          String premium = String.format("%.0f", (Integer.parseInt(options[0]) * percentage));

          LifePolicy lifePolicy = new LifePolicy(options[0], premium);

          // adds the life policy to the profile
          (profiles.get(index)).addPolicy(lifePolicy);

          profiles.get(index).setHasLifePolicy(true);

          // prints the policy created message
          MessageCli.NEW_POLICY_CREATED.printMessage("life", loadedProfileName);
        }
      }

    } else {
      // prints no profile loaded message
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
    }
  }
}
