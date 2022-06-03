package mm.com.mytel.redistest.controller;

import mm.com.mytel.redistest.entities.Student;
import mm.com.mytel.redistest.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

@RestController
public class TestController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping()
    public void setData(Jedis js){
        Student student = new Student("abcd", "John Doe", Student.Gender.MALE, 1);
        studentRepository.save(student);
        js.set("second-jedis","hello world", SetParams.setParams().ex(3600));
    }

    @GetMapping("/persist")
    public void persist(Jedis jedis){
        jedis.persist("first-jedis");

    }

    @GetMapping("/get")
    public Student getData(){
        Student retrievedStudent =
                studentRepository.findById("Eng2015001").get();
        return retrievedStudent;
    }

    @GetMapping("/del")
    public void delete(){
        studentRepository.deleteById("Eng2015001");
    }
}
