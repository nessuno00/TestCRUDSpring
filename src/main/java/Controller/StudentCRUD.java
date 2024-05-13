package Controller;

import Service.StudentRepository;
import Service.StudentService;
import com.example.testcrud.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentCRUD {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @PostMapping
    public Student createStudent (@RequestBody Student student) {

        return studentRepository.save(student);
    }


    @GetMapping
    public List<Student> getAllStudents() {

        return  studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById (@PathVariable Long id ) {

        return studentRepository.findById(id).orElseThrow(() ->  new RuntimeException("Student not found"));
    }
    @PutMapping("/{id}")

    public Student updateStudent (@PathVariable Long id, @RequestBody Student studentUpdate) {

        Student student = studentRepository.findById(id).orElseThrow(() ->  new RuntimeException("Student not found"));

       student.setFirstName(studentUpdate.getFirstName());
       student.setLastName(studentUpdate.getLastName());

       return  studentRepository.save(student);

    }
        public Student updateIsWorking(@PathVariable Long id, @RequestParam boolean working) {

        studentService.changeIsWorking(id, working);

            return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        }

        public void deleteStudent(@PathVariable Long id) {
            studentRepository.deleteById(id);
        }
    }

