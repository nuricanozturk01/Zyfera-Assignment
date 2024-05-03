package nuricanozturk.dev.management.student.service;

import nuricanozturk.dev.management.student.dto.ResponseMessage;
import nuricanozturk.dev.management.student.dto.StudentCreateDTO;
import org.springframework.stereotype.Service;


public interface IStudentService
{
    ResponseMessage<Object> createStudent(StudentCreateDTO dto);
}
