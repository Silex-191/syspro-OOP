package mypackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentRecordBookTest {

    private StudentRecordBook recordBook;

    @BeforeEach
    void setUp() {
        recordBook = new StudentRecordBook(true, 2);
    }

    @Test
    void testCalculateAverageGrade() {
        assertEquals(0.0, recordBook.calculateAverageGrade(), 0.001);

        recordBook.addRecord(new RecordEntry("Math", 1,
            ControlType.EXAM, Grade.GOOD));
        recordBook.addRecord(new RecordEntry("Physics", 1,
            ControlType.EXAM, Grade.EXCELLENT));
        recordBook.addRecord(new RecordEntry("PE", 1,
            ControlType.CREDIT, Grade.PASS));

        assertEquals(4.5, recordBook.calculateAverageGrade(), 0.001);
    }

    @Test
    void testCanTransferToBudget_Success() {
        recordBook.addRecord(new RecordEntry("Math", 1,
            ControlType.EXAM, Grade.GOOD));
        recordBook.addRecord(new RecordEntry("Physics", 2,
            ControlType.EXAM, Grade.EXCELLENT));
        recordBook.addRecord(
            new RecordEntry("History", 2,
                ControlType.DIFF_CREDIT, Grade.SATISFACTORY));

        assertTrue(recordBook.canTransferToBudget());
    }

    @Test
    void testCanTransferToBudget_FailsDueToExamThree() {
        recordBook.addRecord(new RecordEntry("Math", 2,
            ControlType.EXAM, Grade.SATISFACTORY));

        assertFalse(recordBook.canTransferToBudget());
    }

    @Test
    void testCanTransferToBudget_FailsDueToBudgetForm() {
        StudentRecordBook budgetBook = new StudentRecordBook(false, 2);
        budgetBook.addRecord(new RecordEntry("Math", 1,
            ControlType.EXAM, Grade.EXCELLENT));

        assertFalse(budgetBook.canTransferToBudget());
    }

    @Test
    void testCanTransferToBudget_FailsDueToEarlySemester() {
        StudentRecordBook earlyBook = new StudentRecordBook(true, 1);
        earlyBook.addRecord(new RecordEntry("Math", 1,
            ControlType.EXAM, Grade.EXCELLENT));

        assertFalse(earlyBook.canTransferToBudget());
    }

    @Test
    void testCanGetRedDiploma_SuccessWithOverwrittenGrade() {
        recordBook.addRecord(
            new RecordEntry("Math Analysis", 1,
                ControlType.EXAM, Grade.SATISFACTORY));
        recordBook.addRecord(
            new RecordEntry("Math Analysis", 2,
                ControlType.EXAM, Grade.EXCELLENT));

        recordBook.addRecord(new RecordEntry("Physics", 2,
            ControlType.EXAM, Grade.EXCELLENT));
        recordBook.addRecord(new RecordEntry("PE", 2,
            ControlType.CREDIT, Grade.PASS));

        assertTrue(recordBook.canGetRedDiploma());
    }

    @Test
    void testCanGetRedDiploma_FailsDueToPercentage() {
        recordBook.addRecord(new RecordEntry("Math", 1,
            ControlType.EXAM, Grade.GOOD));
        recordBook.addRecord(new RecordEntry("Physics", 2,
            ControlType.EXAM, Grade.EXCELLENT));
        recordBook.addRecord(new RecordEntry("Chemistry", 2,
            ControlType.EXAM, Grade.EXCELLENT));

        assertFalse(recordBook.canGetRedDiploma());
    }

    @Test
    void testCanGetRedDiploma_FailsDueToVkrGrade() {
        recordBook.addRecord(new RecordEntry("Math", 1,
            ControlType.EXAM, Grade.EXCELLENT));
        recordBook.addRecord(
            new RecordEntry("Thesis", 8,
                ControlType.FQW_DEFENSE, Grade.GOOD));

        assertFalse(recordBook.canGetRedDiploma());
    }

    @Test
    void testCanGetIncreasedScholarship_Success() {
        recordBook.addRecord(new RecordEntry("Math", 2,
            ControlType.EXAM, Grade.EXCELLENT));
        recordBook.addRecord(new RecordEntry("PE", 2,
            ControlType.CREDIT, Grade.PASS));

        recordBook.addRecord(new RecordEntry("History", 1,
            ControlType.EXAM, Grade.SATISFACTORY));

        assertTrue(recordBook.canGetIncreasedScholarship());
    }

    @Test
    void testCanGetIncreasedScholarship_FailsDueToGradeFour() {
        recordBook.addRecord(new RecordEntry("Math", 2, ControlType.EXAM,
            Grade.GOOD));

        assertFalse(recordBook.canGetIncreasedScholarship());
    }

    @Test
    void testCanGetIncreasedScholarship_IgnoresOtherControlTypes() {
        recordBook.addRecord(new RecordEntry("Math", 2,
            ControlType.EXAM, Grade.EXCELLENT));
        recordBook.addRecord(new RecordEntry("Homework", 2,
            ControlType.OTHER, Grade.SATISFACTORY));

        assertTrue(recordBook.canGetIncreasedScholarship());
    }
}
