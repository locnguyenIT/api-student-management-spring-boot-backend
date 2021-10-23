package com.example.demo.student;

import com.example.demo.classroom.Classroom;
import com.example.demo.classroom.ClassroomRepository;
import com.example.demo.exception.APIEntityNotFoundException;
import com.example.demo.faculty.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service //this annotation marks a Java class that performs some service, such as execute business logic
public class StudentService {

    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired //studentRepository auto wired inject into StudentService
    public StudentService(StudentRepository repository, ClassroomRepository classroomRepository) {
        this.studentRepository = repository;
        this.classroomRepository = classroomRepository;
    }

    public List<Student> getStudent()
    {
        return studentRepository.findAll();
    }

    public List<Student> getStudentInWhichFaculty(Integer facultyId)
    {
        return studentRepository.findStudentInWhichFaculty(facultyId);
    }

    public Student getStudentById(Integer studentId)
    {
        return studentRepository.findById(studentId).orElseThrow(() //if(studentRepository.findById(studentId) == true) return Student else Error Exception
                -> new APIEntityNotFoundException("student with id "+studentId+ " was not found"));

    }

    public void addStudent(Student student,Integer classroomId) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()) //if email of student is present in database
        {
            throw new IllegalStateException("Email already exist in database");
        }
        boolean exists = classroomRepository.existsById(classroomId);
        if(!exists)
        {
            throw new APIEntityNotFoundException("Classroom with id "+classroomId+ " was not found");
        }
        student.setClassroom(classroomRepository.getById(classroomId));
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Integer studentId)
    {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
        {
            throw new APIEntityNotFoundException("student with id = "+studentId+ " does not exist in database");
        }
        System.out.println("Student id = "+studentId+" has been delete");
        studentRepository.deleteById(studentId);
    }

    @Transactional //use setter to automatically update the entity in database when it's possible
    public void updateStudent(Integer studentId, String name, String email, Integer classroomId)
    {
        //Check studentId in database
        Student student = studentRepository.findById(studentId).orElseThrow(()
                -> new IllegalStateException("student with id "+studentId+ " does not exist"));

        if(name != null && name.length() > 0 && student.getName() != name) //if the new name has been provided is not the same name in database
        {
            student.setName(name);
        }
        if(email != null && email.length() > 0 && student.getEmail() != email) //if the new email has been provided is not the same email in database
        {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if(studentByEmail.isPresent()) //if new email of student is present in database
            {
                throw new IllegalStateException("Email already exist in database");
            }
            student.setEmail(email);
        }
        if(classroomId != null && student.getClassroom().getId() != classroomId) //if the new classroomId has been provided is not the same classroomId in database
        {
            student.setClassroom(classroomRepository.getById(classroomId));
        }
    }


}
