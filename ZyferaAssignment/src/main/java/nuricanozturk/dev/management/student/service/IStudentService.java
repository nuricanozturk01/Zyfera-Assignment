package nuricanozturk.dev.management.student.service;

import nuricanozturk.dev.management.student.dto.CreateStudentResponseDTO;
import nuricanozturk.dev.management.student.dto.ResponseMessage;
import nuricanozturk.dev.management.student.dto.StudentCreateDTO;


/**
 * Service interface for student operations
 */
public interface IStudentService
{
    /**
     * Create a student with the given dto class
     * Check is the student already exists then Calculate the average of the grades and save the student entity to the database
     * if user exists return a response message with a bad request status and message "Student already exists".
     *
     * @param dto Student data
     * @return Response message
     */
    ResponseMessage<CreateStudentResponseDTO> createStudent(StudentCreateDTO dto);
}
