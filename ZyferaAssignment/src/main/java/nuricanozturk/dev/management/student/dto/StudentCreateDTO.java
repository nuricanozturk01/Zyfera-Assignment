package nuricanozturk.dev.management.student.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * DTO for creating a student
 */
public class StudentCreateDTO
{
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must be at least 2 characters long")
    @Size(max = 40, message = "Name must be at max 40 characters long")
    private final String name;

    @NotNull(message = "Surname cannot be null")
    @NotEmpty(message = "Surname cannot be empty")
    @Size(min = 3, message = "Surname must be at least 2 characters long")
    @Size(max = 40, message = "Surname must be at max 40 characters long")
    private final String surname;


    @NotNull(message = "Student Number cannot be null")
    @NotEmpty(message = "Student Number cannot be empty")
    @Size(min = 10, message = "Student Number must be 10 characters long")
    @Size(max = 10, message = "Student Number must be 10 characters long")
    private final String stdNumber;

    @NotEmpty(message = "Grades cannot be empty")
    @Valid
    private List<GradeCreateDTO> grades;

    public StudentCreateDTO(String name, String surname, String stdNumber, List<GradeCreateDTO> grades)
    {
        this.name = name;
        this.surname = surname;
        this.stdNumber = stdNumber;
        this.grades = grades;
    }

    public List<GradeCreateDTO> getGrades()
    {
        return grades;
    }

    public void setGrades(List<GradeCreateDTO> grades)
    {
        this.grades = grades;
    }

    public String getStdNumber()
    {
        return stdNumber;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getName()
    {
        return name;
    }
}
