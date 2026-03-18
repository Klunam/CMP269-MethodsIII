public class GradeLogic {
    
    public static String processStudent(String line) throws NumberFormatException, LowGradeException {
        // Split the line by spaces
        String[] parts = line.trim().split("\\s+");
        
        // Ensure there are enough parts to have at least a name and 3 scores
        if (parts.length < 4) {
            throw new NumberFormatException("Not enough data to parse.");
        }
        
        int length = parts.length;
        
        // The last 3 elements are the scores. If they contain "ERROR", this throws NumberFormatException
        int score1 = Integer.parseInt(parts[length - 3]);
        int score2 = Integer.parseInt(parts[length - 2]);
        int score3 = Integer.parseInt(parts[length - 1]);
        
        // Everything before the last 3 elements makes up the student's name
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < length - 3; i++) {
            nameBuilder.append(parts[i]).append(" ");
        }
        String name = nameBuilder.toString().trim();
        
        // Calculate the average
        double average = (score1 + score2 + score3) / 3.0;
        
        // Format the output string
        String result = String.format("Student: %s | Average: %.2f", name, average);
        
        // Check for the bonus challenge requirement
        if (average < 60) {
            throw new LowGradeException("Low grade detected for " + name, result + " [WARNING: Low Grade]");
        }
        
        return result;
    }
}