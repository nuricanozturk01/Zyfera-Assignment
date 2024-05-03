package nuricanozturk.dev.management.student.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student
{
    @Id
    @Column(name = "student_id", length = 10)
    private String m_studentId;

    @Column(name = "name", length = 40)
    private String m_name;

    @Column(name = "surname", length = 40)
    private String m_surname;


    @OneToMany(mappedBy = "m_student", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Grade> m_grades;

    public Student()
    {
        m_grades = new HashSet<>();
    }


    @Override
    public boolean equals(Object other)
    {
        return other instanceof Student s &&
                m_studentId.equals(s.m_studentId) &&
                m_name.equals(s.m_name) &&
                m_surname.equals(s.m_surname);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(m_studentId, m_name, m_surname);
    }

    public String getStudentId()
    {
        return m_studentId;
    }

    public void setStudentId(String studentId)
    {
        m_studentId = studentId;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getSurname()
    {
        return m_surname;
    }

    public void setSurname(String surname)
    {
        m_surname = surname;
    }

    public Set<Grade> getGrades()
    {
        return m_grades;
    }

    public void setGrades(Set<Grade> grades)
    {
        m_grades = grades;
    }
}
