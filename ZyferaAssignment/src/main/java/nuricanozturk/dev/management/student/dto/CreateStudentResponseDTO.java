package nuricanozturk.dev.management.student.dto;

import java.util.List;

public record CreateStudentResponseDTO(
        String name,
        String surname,
        String stdNumber,
        List<GradeCreateDTO> grades
)
{
}
