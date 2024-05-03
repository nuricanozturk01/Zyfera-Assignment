package nuricanozturk.dev.management.student;

import nuricanozturk.dev.management.student.dto.CreateStudentResponseDTO;
import nuricanozturk.dev.management.student.dto.ResponseMessage;
import nuricanozturk.dev.management.student.dto.StudentCreateDTO;
import nuricanozturk.dev.management.student.util.DataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static nuricanozturk.dev.management.student.util.BeanNames.TEST_PROPERTIES_FILE;

@SpringBootTest
@TestPropertySource(locations = TEST_PROPERTIES_FILE)
class StudentCreateServiceTest
{
    @Autowired
    private Injection m_injection;

    @Test
    void testCreateStudent_withGivenValidDataAndNotMultipleSameEntries_shouldReturnsResponseMessage()
    {
        var grades = DataProvider.provideGradeCreateDTOsWithNotMultipleSameEntities();

        var expectedName = "Nuri Can";
        var expectedSurname = "OZTURK";
        var expectedStdNumber = "Bx23498543";
        var studentCreateDTO = new StudentCreateDTO(expectedName, expectedSurname, expectedStdNumber, grades);

        ResponseMessage<CreateStudentResponseDTO> response = m_injection.getStudentService().createStudent(studentCreateDTO);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("Student created successfully", response.message());
        Assertions.assertEquals(expectedName, response.data().name());
        Assertions.assertEquals(expectedSurname, response.data().surname());
        Assertions.assertEquals(expectedStdNumber, response.data().stdNumber());
        Assertions.assertNotNull(response.data().grades());
        Assertions.assertEquals(grades.size(), response.data().grades().size());
    }


    @Test
    void testCreateStudent_withGivenValidDataAndMultipleSameEntries_shouldReturnsResponseMessage()
    {
        // Multiple same entries. (SE 3314 (3) and SE 3316 are same entities (2).) expected: size - 3
        var grades = DataProvider.provideGradeCreateDTOsWithMultipleSameEntities();

        var expectedName = "Halil Can";
        var expectedSurname = "OZTURK";
        var expectedStdNumber = "Bx23498542";
        var studentCreateDTO = new StudentCreateDTO(expectedName, expectedSurname, expectedStdNumber, grades);

        ResponseMessage<CreateStudentResponseDTO> response = m_injection.getStudentService().createStudent(studentCreateDTO);
        // api check
        Assertions.assertNotNull(response);
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("Student created successfully", response.message());
        // data check
        Assertions.assertEquals(expectedName, response.data().name());
        Assertions.assertEquals(expectedStdNumber, response.data().stdNumber());

        Assertions.assertNotNull(response.data().grades());

        var expectedGradesCount = grades.size() - 3;
        Assertions.assertEquals(expectedGradesCount, response.data().grades().size());
        // grade check
        var expectedAverageNote_SE3314 = 60.0;
        var expectedAverageNote_SE3316 = 60.0;

        response.data().grades().stream().filter(g -> g.code().equals("SE 3314"))
                .findFirst()
                .ifPresent(g -> Assertions.assertEquals(expectedAverageNote_SE3314, g.value()));

        response.data().grades().stream().filter(g -> g.code().equals("SE 3316"))
                .findFirst()
                .ifPresent(g -> Assertions.assertEquals(expectedAverageNote_SE3316, g.value()));
    }
}
