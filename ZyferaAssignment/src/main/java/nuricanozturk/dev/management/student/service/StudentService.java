package nuricanozturk.dev.management.student.service;

import nuricanozturk.dev.management.student.dal.StudentServiceHelper;
import nuricanozturk.dev.management.student.dto.CreateStudentResponseDTO;
import nuricanozturk.dev.management.student.dto.GradeCreateDTO;
import nuricanozturk.dev.management.student.dto.ResponseMessage;
import nuricanozturk.dev.management.student.dto.StudentCreateDTO;
import nuricanozturk.dev.management.student.mapper.IStudentManagementMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

/**
 * Service class for student management
 */
@Service
public class StudentService implements IStudentService
{
    private final StudentServiceHelper m_serviceHelper;
    private final IStudentManagementMapper m_studentMapper;


    /**
     * Constructor injection used with final keyword
     *
     * @param serviceHelper Student service helper
     * @param studentMapper Student mapper
     */
    public StudentService(StudentServiceHelper serviceHelper, IStudentManagementMapper studentMapper)
    {
        m_serviceHelper = serviceHelper;
        m_studentMapper = studentMapper;
    }

    /**
     * Create a student with the given dto class
     * Check is the student already exists then Calculate the average of the grades and save the student entity to the database
     * if user exists return a response message with a bad request status and message "Student already exists".
     *
     * @param dto Student data
     * @return Response message
     */
    @Override
    public ResponseMessage<CreateStudentResponseDTO> createStudent(StudentCreateDTO dto)
    {
        // check user exists
        if (m_serviceHelper.existsByStudentId(dto.getStdNumber()))
            return new ResponseMessage<>("Student already exists", HttpStatus.BAD_REQUEST.value(), null);

        // Calculate the average of the grades
        dto.setGrades(calculateAverageGrades(dto.getGrades()));

        // Map the dto to the student entity
        var student = m_studentMapper.toStudentCreateDTO(dto);

        // Set the student field for each grade
        student.getGrades().forEach(g -> g.setStudent(student));

        // Save the student entity along with the grades (two-way relationship in one-to-many mapping)
        var savedStudent = doForDataService(() -> m_serviceHelper.createStudent(student), "ExceptionUtil::throwException");

        // Map the saved student entity to the response dto
        //End-user will see the response with average grades for course if multiple entries exist for a single course
        var responseDTO = m_studentMapper.toCreateStudentResponseDTO(savedStudent);

        return new ResponseMessage<>("Student created successfully", HttpStatus.CREATED.value(), responseDTO);
    }


    /**
     * Calculate the average of the grades for each course using Stream API
     * Firstly, group the grades by course code and then calculate the average of the values then map the result to the GradeCreateDTO list
     *
     * @param grades List of grades
     * @return List of grades with average values
     */
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
