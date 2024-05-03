package nuricanozturk.dev.management.student.service;

import nuricanozturk.dev.management.student.dal.StudentServiceHelper;
import nuricanozturk.dev.management.student.dto.GradeCreateDTO;
import nuricanozturk.dev.management.student.dto.ResponseMessage;
import nuricanozturk.dev.management.student.dto.StudentCreateDTO;
import nuricanozturk.dev.management.student.mapper.IStudentManagementMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

@Service
public class StudentService implements IStudentService
{
    private final StudentServiceHelper m_serviceHelper;
    private final IStudentManagementMapper m_studentMapper;


    public StudentService(StudentServiceHelper serviceHelper, IStudentManagementMapper studentMapper)
    {
        m_serviceHelper = serviceHelper;
        m_studentMapper = studentMapper;
    }

    @Override
    public ResponseMessage<Object> createStudent(StudentCreateDTO dto)
    {
        if (m_serviceHelper.findStudentById(dto.getStdNumber()).isPresent())
            return new ResponseMessage<>("Student already exists", HttpStatus.BAD_REQUEST.value(), null);

        // Calculate the average of the grades
        dto.setGrades(calculateAverageGrades(dto.getGrades()));

        var student = m_studentMapper.toStudentCreateDTO(dto);

        // Set the student field for each grade
        student.getGrades().forEach(g -> g.setStudent(student));

        // Save the student entity along with the grades
        var savedStudent = m_serviceHelper.createStudent(student);

        var responseDTO = m_studentMapper.toCreateStudentResponseDTO(savedStudent);

        return new ResponseMessage<>("Student created successfully", HttpStatus.CREATED.value(), responseDTO);
    }


    private List<GradeCreateDTO> calculateAverageGrades(List<GradeCreateDTO> grades)
    {
        return grades.stream()
                .collect(groupingBy(GradeCreateDTO::code, averagingDouble(GradeCreateDTO::value)))
                .entrySet()
                .stream()
                .map(s -> new GradeCreateDTO(s.getKey(), s.getValue()))
                .toList();
    }
}
