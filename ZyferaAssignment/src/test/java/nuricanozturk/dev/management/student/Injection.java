package nuricanozturk.dev.management.student;

import nuricanozturk.dev.management.student.controller.StudentManagementController;
import nuricanozturk.dev.management.student.service.IStudentService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@Component
@SpringBootTest
public class Injection
{
    private final IStudentService m_studentService;

    private final StudentManagementController m_studentManagementController;

    public Injection(IStudentService studentService, StudentManagementController studentManagementController)
    {
        m_studentService = studentService;
        m_studentManagementController = studentManagementController;
    }

    public IStudentService getStudentService()
    {
        return m_studentService;
    }

    public StudentManagementController getStudentManagementController()
    {
        return m_studentManagementController;
    }
}