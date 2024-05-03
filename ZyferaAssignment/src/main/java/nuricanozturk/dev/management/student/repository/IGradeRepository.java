package nuricanozturk.dev.management.student.repository;

import nuricanozturk.dev.management.student.entity.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGradeRepository extends CrudRepository<Grade, Long>
{
}
