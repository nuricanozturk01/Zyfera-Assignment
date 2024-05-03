package nuricanozturk.dev.management.student.repository;

import nuricanozturk.dev.management.student.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends CrudRepository<Student, String>
{
}
