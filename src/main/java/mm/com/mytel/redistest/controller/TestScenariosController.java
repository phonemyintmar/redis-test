package mm.com.mytel.redistest.controller;

import mm.com.mytel.redistest.model.TestModel;
import mm.com.mytel.redistest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestScenariosController {
    @Autowired
    TestService service;

    @GetMapping("/putData")
    public Iterable<TestModel> putData(Jedis jedis){
      return service.testRedisWithTime(jedis);
    }
}
