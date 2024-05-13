package Service;

import com.example.testcrud.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

   @Autowired
 private StudentRepository  studentRepository;

   public void changeIsWorking (Long studentId, boolean isWorking) {

       Student student = studentRepository.findById(studentId).orElseThrow(()  ->  new RuntimeException("Student not found"));

       student.setWorking(isWorking);
       studentRepository.save(student);


   }



}
