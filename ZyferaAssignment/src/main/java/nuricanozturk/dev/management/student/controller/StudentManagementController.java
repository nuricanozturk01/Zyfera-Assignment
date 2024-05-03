package nuricanozturk.dev.management.student.controller;

import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/api/v1/student")
public class StudentManagementController
{
    private final IStudentService m_studentService;

    public StudentManagementController(IStudentService studentService)
    {
        m_studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage<Object>> create(@RequestBody @Valid StudentCreateDTO dto)
    {
        return subscribe(() -> ok(m_studentService.createStudent(dto)), ex ->
                internalServerError().body(new ResponseMessage<>(ex.getMessage(), INTERNAL_SERVER_ERROR.value(), null)));
    }
}
