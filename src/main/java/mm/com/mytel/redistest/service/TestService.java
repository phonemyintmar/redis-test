package mm.com.mytel.redistest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mm.com.mytel.redistest.model.TestModel;
import mm.com.mytel.redistest.repo.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TestService {
    @Autowired
    TestRepo repo;

    public TestModel testRedisWithTime(Jedis jedis) throws JsonProcessingException {
        List<TestModel> models= (List<TestModel>) repo.findAll();
        ObjectMapper mapper = new ObjectMapper();
        for (int i=0;i<models.size();i++){
            String jsonValue= mapper.writeValueAsString(models.get(i));
            jedis.set(models.get(i).getId(),jsonValue , SetParams.setParams().ex(3600));
        }
        TestModel modelFromJedis = mapper.readValue(jedis.get("QNygwkKxJe2up7sGoLci0"),TestModel.class);
        return modelFromJedis;
    }

    public TestModel putNewIfExists(Jedis jedis ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (jedis.exists("QNygwkKxJe2up7sGoLci0")){
            TestModel modelFromJedis = mapper.readValue(jedis.get("QNygwkKxJe2up7sGoLci0"),TestModel.class);
            log.info("Took the data for {} from redis","QNygwkKxJe2up7sGoLci0");
            return modelFromJedis;
        }
        else {
            Optional<TestModel> modelOptional =repo.findById("QNygwkKxJe2up7sGoLci0");
            jedis.set(modelOptional.get().getId(),mapper.writeValueAsString(modelOptional.get()),SetParams.setParams().ex(3600));
            log.info("Took the data for {} from database","QNygwkKxJe2up7sGoLci0");
            return modelOptional.get();
        }
    }
}
