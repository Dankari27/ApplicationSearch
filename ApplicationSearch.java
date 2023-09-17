import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Keep looping until the user chooses to exit
        while (true) {
            String fileName = getFileName(scanner); // Get the input file name
            ArrayList<String> applicationNames = readApplicationNames(fileName); // Read application names from the file

            if (applicationNames != null) {
                String searchTerm = getSearchTerm(scanner); // Get the search term from the user
                ArrayList<String> matchingApplications = findMatchingApplications(searchTerm, applicationNames); // Find
                                                                                                                 // matching
                                                                                                                 // applications

                if (!matchingApplications.isEmpty()) {
                    String outputFileName = getOutputFileName(scanner); // Get the output file name
                    writeMatchingApplicationsToFile(outputFileName, matchingApplications); // Write matching
                                                                                           // applications to a file

                    System.out.println("\nMatching applications:");
                    for (String app : matchingApplications) {
                        System.out.println(app); // Display matching applications
                    }

                    System.out.print("\nSearch again (yes/no)? ");
                    String again = scanner.next();
                    if (!again.equalsIgnoreCase("yes")) {
                        break; // Exit the loop if the user doesn't want to search again
                    }
                } else {
                    System.out.println("\nNo matching applications found.");
                }
            }
        }

        scanner.close();
    }

    // Prompt the user for the input file name
    private static String getFileName(Scanner scanner) {
        System.out.print("Enter file name: ");
        return scanner.next();
    }

    // Read application names from the specified file
    private static ArrayList<String> readApplicationNames(String fileName) {
        try {
            ArrayList<String> applicationNames = new ArrayList<>();
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            // Read and store non-empty lines as application names
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    applicationNames.add(line);
                }
            }

            fileScanner.close();
            return applicationNames;
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found. Please try again.\n");
            return null;
        }
    }

    // Prompt the user for the search term
    private static String getSearchTerm(Scanner scanner) {
        System.out.print("\nEnter a term to search for: ");
        return scanner.next();
    }

    // Find applications that match the search term
    private static ArrayList<String> findMatchingApplications(String searchTerm, ArrayList<String> applicationNames) {
        ArrayList<String> matchingApplications = new ArrayList<>();
        for (String appName : applicationNames) {
            if (appName.matches(".*" + searchTerm + ".*")) {
                matchingApplications.add(appName);
            }
        }
        return matchingApplications;
    }

    // Prompt the user for the output file name
    private static String getOutputFileName(Scanner scanner) {
        System.out.print("\nEnter output file name: ");
        return scanner.next();
    }

    // Write matching applications to the specified output file
    private static void writeMatchingApplicationsToFile(String fileName, ArrayList<String> matchingApplications) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (String app : matchingApplications) {
                fileWriter.write(app + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("\nError writing to the output file. Please try again.\n");
        }
    }
}
