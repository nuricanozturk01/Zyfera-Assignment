package nuricanozturk.dev.management.student.repository;

import nuricanozturk.dev.management.student.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Student entity
 */
@Repository
public interface IStudentRepository extends CrudRepository<Student, String>
{
    /**
     * Check if the student exists by the student id
     *
     * @param id Student id
     * @return True if the student exists, otherwise false
     */
    @Query("select case when count(s) > 0 then true else false end from Student s where s.m_studentId = :id")
    boolean existsStudentByStudentId(String id);
}
