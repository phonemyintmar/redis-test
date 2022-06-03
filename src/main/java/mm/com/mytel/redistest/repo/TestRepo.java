package mm.com.mytel.redistest.repo;

import mm.com.mytel.redistest.model.TestModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends CrudRepository<TestModel,String> {

}
