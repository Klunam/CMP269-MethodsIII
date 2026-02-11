// Necesseary Libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// Going with Option 1 example from Demo,





public class GradeBookProcessor {
    public static void main(String[] args) {
        File inputFile = new File("students.txt");
        File outputFile = new File("grades_report.txt");

        // Try-with-resources with Scanner:
        try (
            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(outputFile)
        ) {
            //Scanner and PrintWriter declarations into a single try block:
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // skip blank lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    // Sends line to GradeLogic.java file
                    String result = GradeLogic.processStudent(line);
                    writer.println(result);
                } catch (NumberFormatException e) {
                    // Handles lines  "Barry Alen 1 ERROR 23"
                    System.out.println("Warning: Invalid data format found. Skipping student data: " + line);
                } catch (LowGradeException e) {
                    // Bonus Challenge: Marks a student with a warning flag instead of skipping
                    writer.println(e.getReportOutput());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file students.txt could not be found.");
            //use a finally block at the end of the prorgam
        } finally {
            System.out.println("Processing Complete");
        }
    }
}