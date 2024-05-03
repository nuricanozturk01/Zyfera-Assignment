package nuricanozturk.dev.management.student.dal;

import nuricanozturk.dev.management.student.entity.Grade;
import nuricanozturk.dev.management.student.entity.Student;
import nuricanozturk.dev.management.student.repository.IGradeRepository;
import nuricanozturk.dev.management.student.repository.IStudentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentServiceHelper
{
    private final IStudentRepository m_studentRepository;
    private final IGradeRepository m_gradeRepository;

    public StudentServiceHelper(IStudentRepository studentRepository, IGradeRepository gradeRepository)
    {
        m_studentRepository = studentRepository;
        m_gradeRepository = gradeRepository;
    }

    public Student createStudent(Student student)
    {
        return m_studentRepository.save(student);
    }

    public Grade createGrade(Grade grade)
    {
        return m_gradeRepository.save(grade);
    }

    public Iterable<Grade> saveAllGrades(Iterable<Grade> grades)
    {
        return m_gradeRepository.saveAll(grades);
    }

    public Optional<Student> findStudentById(String studentId)
    {
        return m_studentRepository.findById(studentId);
    }

    //...
}


