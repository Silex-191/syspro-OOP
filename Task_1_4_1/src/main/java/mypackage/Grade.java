package mypackage;

/**
 * Represents a student's grade for a specific assessment.
 */
public enum Grade {
    EXCELLENT(5),
    GOOD(4),
    SATISFACTORY(3),
    UNSATISFACTORY(2),
    PASS(0),
    FAIL(0);

    private final int numericValue;

    Grade(int numericValue) {
        this.numericValue = numericValue;
    }

    /**
     * Gets the numeric equivalent of the grade.
     *
     * @return the numeric value of the grade
     * @throws IllegalStateException if the grade does not have a numeric equivalent (e.g.,
     *     PASS/FAIL)
     */
    public int getNumericValue() {
        if (!isNumeric()) {
            throw new IllegalStateException(
                "Cannot get numeric value for a non-numeric grade (PASS/FAIL).");
        }
        return numericValue;
    }

    public boolean isNumeric() {
        return this == EXCELLENT || this == GOOD || this == SATISFACTORY || this == UNSATISFACTORY;
    }
}