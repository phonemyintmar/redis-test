package mm.com.mytel.redistest.service;

import lombok.extern.slf4j.Slf4j;
import mm.com.mytel.redistest.model.TestModel;
import mm.com.mytel.redistest.repo.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.List;

@Service
@Slf4j
public class TestService {
    @Autowired
    TestRepo repo;

    public List<TestModel> testRedisWithTime(Jedis jedis){
        List<TestModel> models= (List<TestModel>) repo.findAll();
        for (int i=0;i<models.size();i++){
            jedis.set(models.get(i).getId(),models.get(i).toString(), SetParams.setParams().ex(3600));
        }
        return models;
    }
}
