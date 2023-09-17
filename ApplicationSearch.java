import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Loop until the user chooses to exit
        while (true) {
            String fileName = getFileName(scanner);
            ArrayList<String> applicationNames = readApplicationNames(fileName);

            if (applicationNames != null) {
                String searchTerm = getSearchTerm(scanner);
                ArrayList<String> matchingApplications = findMatchingApplications(searchTerm, applicationNames);

                if (!matchingApplications.isEmpty()) {
                    String outputFileName = getOutputFileName(scanner);
                    writeMatchingApplicationsToFile(outputFileName, matchingApplications);

                    System.out.println("\nMatching applications:");
                    for (String app : matchingApplications) {
                        System.out.println(app);
                    }

                    System.out.print("\nSearch again (yes/no)? ");
                    String again = scanner.next();
                    if (!again.equalsIgnoreCase("yes")) {
                        break;
                    }
                } else {
                    System.out.println("\nNo matching applications found.");
                }
            }
        }

        scanner.close();
    }

    private static String getFileName(Scanner scanner) {
        System.out.print("Enter file name: ");
        return scanner.next();
    }

    private static ArrayList<String> readApplicationNames(String fileName) {
        try {
            ArrayList<String> applicationNames = new ArrayList<>();
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

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

    private static String getSearchTerm(Scanner scanner) {
        System.out.print("\nEnter a term to search for: ");
        return scanner.next();
    }

    private static ArrayList<String> findMatchingApplications(String searchTerm, ArrayList<String> applicationNames) {
        ArrayList<String> matchingApplications = new ArrayList<>();
        for (String appName : applicationNames) {
            if (appName.matches(".*" + searchTerm + ".*")) {
                matchingApplications.add(appName);
            }
        }
        return matchingApplications;
    }

    private static String getOutputFileName(Scanner scanner) {
        System.out.print("\nEnter output file name: ");
        return scanner.next();
    }

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
