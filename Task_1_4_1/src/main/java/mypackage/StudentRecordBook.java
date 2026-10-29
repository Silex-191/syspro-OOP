package mypackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a student's electronic record book and provides methods to calculate various academic
 * achievements and statuses.
 */
public class StudentRecordBook {

    private final List<RecordEntry> records;
    private final boolean isPaidForm;
    private final int currentSemester;

    /**
     * Constructs a new StudentRecordBook.
     *
     * @param isPaidForm      true if the student is on a paid form of study, false if on budget
     * @param currentSemester the current semester of the student
     */
    public StudentRecordBook(boolean isPaidForm, int currentSemester) {
        this.records = new ArrayList<>();
        this.isPaidForm = isPaidForm;
        this.currentSemester = currentSemester;
    }

    /**
     * Adds a new academic record entry to the record book.
     *
     * @param entry the record entry to add
     */
    public void addRecord(RecordEntry entry) {
        records.add(entry);
    }

    /**
     * Calculates the current Grade Point Average (GPA) for the entire period of study. Only numeric
     * grades are taken into account.
     *
     * @return the average grade, or 0.0 if there are no numeric grades
     */
    public double calculateAverageGrade() {
        return records.stream()
            .filter(r -> r.grade().isNumeric())
            .mapToInt(r -> r.grade().getNumericValue())
            .average()
            .orElse(0.0);
    }

    /**
     * Determines if the student is eligible to transfer from a paid form of study to the budget
     * form.
     *
     * @return true if the student meets the transfer requirements, false otherwise
     */
    public boolean canTransferToBudget() {
        if (!isPaidForm || currentSemester < 2) {
            return false;
        }

        List<RecordEntry> lastTwoSessions = records.stream()
            .filter(r -> r.semester() == currentSemester
                || r.semester() == currentSemester - 1)
            .toList();

        boolean hasExamThree = lastTwoSessions.stream()
            .anyMatch(r -> r.type() == ControlType.EXAM
                && r.grade() == Grade.SATISFACTORY);

        boolean hasFail = lastTwoSessions.stream()
            .anyMatch(r -> r.grade() == Grade.FAIL || r.grade() == Grade.UNSATISFACTORY);

        return !hasExamThree && !hasFail;
    }

    /**
     * Predicts if the student is eligible to obtain a diploma with honors (red diploma). The
     * prediction is based on the final grades for each subject across all recorded semesters.
     *
     * @return true if the student is on track to receive a diploma with honors, false otherwise
     */
    public boolean canGetRedDiploma() {
        Map<String, RecordEntry> finalGrades = records.stream()
            .collect(Collectors.toMap(
                RecordEntry::subjectName,
                entry -> entry,
                (existing, replacement)
                    -> existing.semester() > replacement.semester() ? existing : replacement
            ));

        boolean hasBadGrades = finalGrades.values().stream()
            .anyMatch(r -> r.grade() == Grade.SATISFACTORY
                || r.grade() == Grade.UNSATISFACTORY);

        boolean hasFails = finalGrades.values().stream()
            .anyMatch(r -> r.grade() == Grade.FAIL);

        if (hasBadGrades || hasFails) {
            return false;
        }

        Optional<RecordEntry> fqw = records.stream()
            .filter(r -> r.type() == ControlType.FQW_DEFENSE)
            .findFirst();

        if (fqw.isPresent() && fqw.get().grade() != Grade.EXCELLENT) {
            return false;
        }

        long totalNumericGrades = finalGrades.values().stream()
            .filter(r -> r.grade().isNumeric())
            .count();

        long excellentGrades = finalGrades.values().stream()
            .filter(r -> r.grade() == Grade.EXCELLENT)
            .count();

        if (totalNumericGrades == 0) {
            return true;
        }

        double excellentPercentage = (double) excellentGrades / totalNumericGrades;
        return excellentPercentage >= 0.75;
    }

    /**
     * Determines if the student is eligible for an increased scholarship in the upcoming semester.
     *
     * @return true if the student qualifies for an increased scholarship, false otherwise
     */
    public boolean canGetIncreasedScholarship() {
        List<RecordEntry> lastSession = records.stream()
            .filter(r -> r.semester() == currentSemester)
            .toList();

        if (lastSession.isEmpty()) {
            return false;
        }

        return lastSession.stream().allMatch(r ->
            r.type() == ControlType.OTHER ||
                (r.grade().isNumeric() && r.grade() == Grade.EXCELLENT) ||
                (r.grade() == Grade.PASS)
        );
    }
}