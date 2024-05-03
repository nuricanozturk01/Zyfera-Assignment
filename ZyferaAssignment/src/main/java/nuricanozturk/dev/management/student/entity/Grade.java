package nuricanozturk.dev.management.student.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "grade")
public class Grade
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long m_id;

    @Column(name = "code", length = 11)
    private String m_code;

    @Column(name = "grade")
    private double m_value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student m_student;

    public Grade()
    {
    }


    public Grade(String code, int value)
    {
        m_code = code;
        m_value = value;
    }


    @Override
    public boolean equals(Object other)
    {
        return other instanceof Grade g &&
                m_id == g.m_id &&
                m_code.equals(g.m_code) &&
                m_value == g.m_value;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(m_id, m_code, m_value, m_student);
    }

    public long getId()
    {
        return m_id;
    }

    public void setId(long id)
    {
        m_id = id;
    }

    public String getCode()
    {
        return m_code;
    }

    public void setCode(String code)
    {
        m_code = code;
    }

    public double getValue()
    {
        return m_value;
    }

    public void setValue(double value)
    {
        m_value = value;
    }

    public Student getStudent()
    {
        return m_student;
    }

    public void setStudent(Student student)
    {
        this.m_student = student;
    }

}
