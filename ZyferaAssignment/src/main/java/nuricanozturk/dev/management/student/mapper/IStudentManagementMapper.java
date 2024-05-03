package nuricanozturk.dev.management.student.mapper;

import nuricanozturk.dev.management.student.dto.CreateStudentResponseDTO;
import nuricanozturk.dev.management.student.dto.GradeCreateDTO;
import nuricanozturk.dev.management.student.dto.StudentCreateDTO;
import nuricanozturk.dev.management.student.entity.Grade;
import nuricanozturk.dev.management.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for Student Management
 */
@Mapper(implementationName = "StudentMapperImpl", componentModel = "spring")
public interface IStudentManagementMapper
{
    /**
     * Maps StudentCreateDTO to Student
     *
     * @param dto StudentCreateDTO
     * @return Student
     */
    @Mapping(target = "studentId", source = "stdNumber")
    Student toStudentCreateDTO(StudentCreateDTO dto);


    /**
     * Maps GradeCreateDTO to Grade
     *
     * @param dto GradeCreateDTO
     * @return Grade
     */
    Grade toGradeCreateDTO(GradeCreateDTO dto);


    /**
     * Maps Student to CreateStudentResponseDTO
     *
     * @param student Student
     * @return CreateStudentResponseDTO
     */
    @Mapping(target = "stdNumber", source = "studentId")
    CreateStudentResponseDTO toCreateStudentResponseDTO(Student student);
}
