package nuricanozturk.dev.management.student.dal;

import nuricanozturk.dev.management.student.entity.Student;
import nuricanozturk.dev.management.student.repository.IGradeRepository;
import nuricanozturk.dev.management.student.repository.IStudentRepository;
import org.springframework.stereotype.Component;

import static callofproject.dev.library.exception.util.CopDataUtil.doForRepository;

/**
 * Facade class for the student service.
 * This class is responsible for the repository operations.
 * This method wrote for assignment specifications. Some of the methods will be created in the future. But now,
 * I need two methods for the student service.
 * <p>
 * Also, I use Functional exception handling library for the repository operations.
 * (We wrote it for graduation project and inspired by CSD)
 */
@Component
public class StudentServiceHelper
{
    private final IStudentRepository m_studentRepository;

    @SuppressWarnings("FieldCanBeLocal")
    private final IGradeRepository m_gradeRepository;

    /**
     * Constructor injection used with final keyword
     *
     * @param studentRepository Student repository
     * @param gradeRepository   Grade repository
     */
    public StudentServiceHelper(IStudentRepository studentRepository, IGradeRepository gradeRepository)
    {
        m_studentRepository = studentRepository;
        m_gradeRepository = gradeRepository;
    }

    /**
     * Create a student entity
     *
     * @param student Student entity
     * @return Student entity
     */
    public Student createStudent(Student student)
    {
        return doForRepository(() -> m_studentRepository.save(student), "Unexpected Error while creating student");
    }

    /**
     * Check if the student exists by the student id
     *
     * @param id Student id
     * @return True if the student exists, otherwise false
     */
    public boolean existsByStudentId(String id)
    {
        return doForRepository(() -> m_studentRepository.existsStudentByStudentId(id), "Unexpected Error while checking student");
    }

    //...
}


