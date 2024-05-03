package nuricanozturk.dev.management.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import nuricanozturk.dev.management.student.dto.CreateStudentResponseDTO;
import nuricanozturk.dev.management.student.dto.ResponseMessage;
import nuricanozturk.dev.management.student.dto.StudentCreateDTO;
import nuricanozturk.dev.management.student.service.IStudentService;
import nuricanozturk.dev.management.student.util.DataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static nuricanozturk.dev.management.student.util.BeanNames.TEST_PROPERTIES_FILE;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = TEST_PROPERTIES_FILE)
public class StudentManagementControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStudentService studentService;

    @Test
    public void testCreateStudent_withGivenInvalidNameLeastConstraint_shouldReturnsErrorMessage() throws Exception
    {
        StudentCreateDTO dto = DataProvider.provideStudentWithArgs("n", "OZTURK", "Bx23498543");

        ResponseMessage<CreateStudentResponseDTO> response = new ResponseMessage<>("Name must be at least 2 characters long", 400, null);

        when(studentService.createStudent(dto)).thenReturn(response);

        mockMvc.perform(post("/api/v1/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid Input")))
                .andExpect(jsonPath("$.status_code", is(400)))
                .andExpect(jsonPath("$.data.name", is("Name must be at least 2 characters long")));
    }

    @Test
    public void testCreateStudent_withGivenInvalidNameMaxConstraint_shouldReturnsErrorMessage() throws Exception
    {
        StudentCreateDTO dto = DataProvider.provideStudentWithArgs("NURINURINURINURINURINURINURINURINURINURINURI", "OZTURK", "Bx23498543");

        ResponseMessage<CreateStudentResponseDTO> response = new ResponseMessage<>("Name must be at max 40 characters long", 400, null);

        when(studentService.createStudent(dto)).thenReturn(response);

        mockMvc.perform(post("/api/v1/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid Input")))
                .andExpect(jsonPath("$.status_code", is(400)))
                .andExpect(jsonPath("$.data.name", is("Name must be at max 40 characters long")));
    }


    @Test
    public void testCreateStudent_withGivenInvalidStudentNumberLeastAndMaxConstraint_shouldReturnsErrorMessage() throws Exception
    {
        StudentCreateDTO dto = DataProvider.provideStudentWithArgs("Nuri Can", "OZTURK", "Bx2349854");

        ResponseMessage<CreateStudentResponseDTO> response = new ResponseMessage<>("Student Number must be 10 characters long", 400, null);

        when(studentService.createStudent(dto)).thenReturn(response);

        mockMvc.perform(post("/api/v1/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid Input")))
                .andExpect(jsonPath("$.status_code", is(400)))
                .andExpect(jsonPath("$.data.stdNumber", is("Student Number must be 10 characters long")));


        dto = DataProvider.provideStudentWithArgs("Nuri Can", "OZTURK", "Bx234985434235432");

        response = new ResponseMessage<>("Student Number must be 10 characters long", 400, null);

        when(studentService.createStudent(dto)).thenReturn(response);

        mockMvc.perform(post("/api/v1/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid Input")))
                .andExpect(jsonPath("$.status_code", is(400)))
                .andExpect(jsonPath("$.data.stdNumber", is("Student Number must be 10 characters long")));
    }

    @Test
    public void testCreateStudent_withGivenEmptyGradesConstraint_shouldReturnsErrorMessage() throws Exception
    {
        StudentCreateDTO dto = DataProvider.provideStudentWithArgs("Ali", "OZTURK", "Bx23498543", null);

        ResponseMessage<CreateStudentResponseDTO> response = new ResponseMessage<>("Grades cannot be empty", 400, null);

        when(studentService.createStudent(dto)).thenReturn(response);

        mockMvc.perform(post("/api/v1/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid Input")))
                .andExpect(jsonPath("$.status_code", is(400)))
                .andExpect(jsonPath("$.data.grades", is("Grades cannot be empty")));
    }
}