package nuricanozturk.dev.management.student.util;

import nuricanozturk.dev.management.student.dto.GradeCreateDTO;
import nuricanozturk.dev.management.student.dto.StudentCreateDTO;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import java.util.List;

public final class DataProvider
{
    private DataProvider()
    {
    }

    public static List<GradeCreateDTO> provideGradeCreateDTOsWithNotMultipleSameEntities()
    {
        return List.of(
                new GradeCreateDTO("SE 3314", 10.0),
                new GradeCreateDTO("SE 3315", 20.0),
                new GradeCreateDTO("SE 3316", 30.0),
                new GradeCreateDTO("SE 3317", 40.0),
                new GradeCreateDTO("SE 3318", 50.0),
                new GradeCreateDTO("SE 3319", 60.0)
        );
    }

    // include two same entities. These entities are "SE 3316 (30, 90)" and "SE 3314 (10, 70, 100)"
    public static List<GradeCreateDTO> provideGradeCreateDTOsWithMultipleSameEntities()
    {
        return List.of(
                new GradeCreateDTO("SE 3314", 10.0),
                new GradeCreateDTO("SE 3315", 20.0),
                new GradeCreateDTO("SE 3316", 30.0),
                new GradeCreateDTO("SE 3316", 90.0),
                new GradeCreateDTO("SE 3317", 40.0),
                new GradeCreateDTO("SE 3319", 60.0),
                new GradeCreateDTO("SE 3314", 70.0),
                new GradeCreateDTO("SE 3314", 100.0)
        );
    }

    public static StudentCreateDTO provideStudentWithArgs(String name, String surname, String stdNumber)
    {
        return new StudentCreateDTO(name, surname, stdNumber, provideGradeCreateDTOsWithNotMultipleSameEntities());
    }

    public static StudentCreateDTO provideStudentWithArgs(String name, String surname, String stdNumber, List<GradeCreateDTO> grades)
    {
        return new StudentCreateDTO(name, surname, stdNumber, grades);
    }
}
