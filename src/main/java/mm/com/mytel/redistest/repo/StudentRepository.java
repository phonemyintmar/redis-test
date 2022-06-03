package mm.com.mytel.redistest.repo;

import mm.com.mytel.redistest.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,String> {
    

}
