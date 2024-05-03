package nuricanozturk.dev.management.student.controller;

import jakarta.validation.Valid;
import nuricanozturk.dev.management.student.dto.CreateStudentResponseDTO;
import nuricanozturk.dev.management.student.dto.ResponseMessage;
import nuricanozturk.dev.management.student.dto.StudentCreateDTO;
import nuricanozturk.dev.management.student.service.IStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static callofproject.dev.library.exception.util.ExceptionUtil.subscribe;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller class for student management
 */
@RestController
@RequestMapping("/api/v1/student")
public class StudentManagementController
{
    private final IStudentService m_studentService;

    /**
     * Constructor injection used with final keyword
     *
     * @param studentService Student service
     */
    public StudentManagementController(IStudentService studentService)
    {
        m_studentService = studentService;
    }

    /**
     * Create a student with the given dto class
     * Check is the student already exists then Calculate the average of the grades and save the student entity to the database
     * if user exists return a response message with a bad request status and message "Student already exists".
     *
     * @param dto Student data
     * @return Response message
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseMessage<CreateStudentResponseDTO>> create(@RequestBody @Valid StudentCreateDTO dto)
    {
        return subscribe(() -> ok(m_studentService.createStudent(dto)), ex ->
                internalServerError().body(new ResponseMessage<>(ex.getMessage(), INTERNAL_SERVER_ERROR.value(), null)));
    }
}
