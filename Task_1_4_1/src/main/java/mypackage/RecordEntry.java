package mypackage;

/**
 * Represents a single assessment entry in the student's record book.
 *
 * @param subjectName the name of the academic subject
 * @param semester    the semester during which the assessment took place
 * @param type        the type of academic control
 * @param grade       the grade received by the student
 */
public record RecordEntry(String subjectName, int semester, ControlType type, Grade grade) {}
