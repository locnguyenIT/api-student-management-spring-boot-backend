package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //This annotation make this class request API layer
@RequestMapping(path = "/api/spring-boot/student") //mapping to the endpoint url
public class StudentController { //Request API , Response API

    private final StudentService studentService;

    @Autowired //StudentService with @Service will be instantiated and autowired inject into StudentController
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping //GET student
    public List<Student> getStudent()
    {
        return studentService.getStudent();
    }

    @GetMapping(path = "/get/facultyId/{facultyId}") //GET student
    public List<Student> getStudentInWhichFaculty(@PathVariable("facultyId") Integer facultyId)
    {
        return studentService.getStudentInWhichFaculty(facultyId);
    }

    @GetMapping(path = "/get/{studentId}") //GET student by id
    public Student getStudentById(@PathVariable("studentId") Integer studentId) //@PathVariable is extract the values (studentID) from the URL
    {
        return studentService.getStudentById(studentId);
    }

    @PostMapping(path = "/add/classroomId/{classroomId}") //POST student
    public void addStudent(@RequestBody Student student,@PathVariable("classroomId") Integer classroomId) //@RequestBody is take the request body provided and map it into a student
    {
        studentService.addStudent(student,classroomId);
    }

    @DeleteMapping(path = "/delete/{studentId}") //Delete student with studentId
    public void deleteStudent(@PathVariable("studentId") Integer studentId) //@PathVariable is extract the values (studentID) from the URL
    {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "/update/{studentId}") //Update student with studentId (http://localhost:8080/api/spring-boot/student/update/"id"?name="?"&email="?"&classroomId="?")
    public void updateStudent(@PathVariable("studentId") Integer studentId, //@PathVariable is extract the values (studentID) from the URL
                              @RequestParam(required = false) String name,  //@RequestParam is extract the query parameters from the URL. required = false mean is not required have parameter
                              @RequestParam(required = false) String email,//@RequestParam is extract the query parameters from the URL. required = false mean is not required have parameter
                              @RequestParam(required = false) Integer classroomId)
    {
        studentService.updateStudent(studentId,name,email,classroomId);
    }
}
