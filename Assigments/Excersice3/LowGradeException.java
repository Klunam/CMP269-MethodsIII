public class LowGradeException extends Exception {
    private String reportOutput;

    public LowGradeException(String message, String reportOutput) {
        super(message);
        this.reportOutput = reportOutput;
    }

    // Retrieves the formatted string with the warning attached
    public String getReportOutput() {
        return reportOutput;
    }
}