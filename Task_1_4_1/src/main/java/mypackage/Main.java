package mypackage;

/**
 * Entry point for demonstrating the functionality of the StudentRecordBook class.
 */
public class Main {

    /**
     * The main method that populates a sample record book and outputs academic statuses.
     *
     * @param args command-line arguments (not used)
     */
    static void main(String[] args) {
        StudentRecordBook recordBook = new StudentRecordBook(true, 2);

        recordBook.addRecord(new RecordEntry("Mathematical Analysis", 1,
            ControlType.EXAM, Grade.GOOD));
        recordBook.addRecord(new RecordEntry("Analytic Geometry", 1,
            ControlType.EXAM, Grade.EXCELLENT));
        recordBook.addRecord(new RecordEntry("Physical Education", 1,
            ControlType.CREDIT, Grade.PASS));

        recordBook.addRecord(new RecordEntry("Mathematical Analysis", 2,
            ControlType.EXAM, Grade.GOOD));
        recordBook.addRecord(new RecordEntry("Theoretical Mechanics", 2,
            ControlType.DIFF_CREDIT, Grade.EXCELLENT));
        recordBook.addRecord(new RecordEntry("Foreign Language", 2,
            ControlType.CREDIT, Grade.PASS));

        System.out.printf("Current Average Grade: %.2f%n", recordBook.calculateAverageGrade());
        System.out.println("Eligible for Budget Transfer: " + recordBook.canTransferToBudget());
        System.out.println("On track for Red Diploma: " + recordBook.canGetRedDiploma());
        System.out.println(
            "Eligible for Increased Scholarship: " + recordBook.canGetIncreasedScholarship());
    }
}