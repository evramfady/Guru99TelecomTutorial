package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;


public class TestDataGenerator {
    
    private static final Random random = new Random();
    

    public static String generateFirstName() {
        String[] firstNames = {"Ahmed", "Mohamed", "Ali", "Hassan", "Omar", 
                               "Fatima", "Aisha", "Sara", "Nour", "Mariam"};
        return firstNames[random.nextInt(firstNames.length)];
    }

    public static String generateLastName() {
        String[] lastNames = {"Ibrahim", "Hassan", "Mahmoud", "Abdullah", "Khalil",
                             "Salem", "Farid", "Nabil", "Karim", "Rashid"};
        return lastNames[random.nextInt(lastNames.length)];
    }

    public static String generateEmail() {
        String randomString = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
        return "test" + randomString + "@example.com";
    }
    

    public static String generateAddress() {
        int streetNumber = random.nextInt(500) + 1;
        String[] streets = {"Tahrir Street", "Nile Corniche", "Al Azhar Street", 
                           "Pyramids Road", "Heliopolis Avenue"};
        String[] cities = {"Cairo", "Giza", "Alexandria", "Nasr City", "Zamalek"};

        return streetNumber + " " + streets[random.nextInt(streets.length)] +
                " " + cities[random.nextInt(cities.length)];
    }

    public static String generateMobileNumber() {
        // Egyptian mobile format: 01XXXXXXXXX (11 digits)
        String[] prefixes = {"010", "011", "012", "015"};
        String prefix = prefixes[random.nextInt(prefixes.length)];
        String number = RandomStringUtils.randomNumeric(7);
        return prefix + number;
    }
}
