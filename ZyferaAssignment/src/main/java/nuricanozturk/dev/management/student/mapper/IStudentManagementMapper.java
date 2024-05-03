package nuricanozturk.dev.management.student.mapper;

import nuricanozturk.dev.management.student.dto.CreateStudentResponseDTO;
import nuricanozturk.dev.management.student.dto.GradeCreateDTO;
import nuricanozturk.dev.management.student.dto.StudentCreateDTO;
import nuricanozturk.dev.management.student.entity.Grade;
import nuricanozturk.dev.management.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "StudentMapperImpl", componentModel = "spring")
public interface IStudentManagementMapper
{
    @Mapping(target = "studentId", source = "stdNumber")
    Student toStudentCreateDTO(StudentCreateDTO dto);


    Grade toGradeCreateDTO(GradeCreateDTO dto);


    @Mapping(target = "stdNumber", source = "studentId")
    CreateStudentResponseDTO toCreateStudentResponseDTO(Student student);
}
