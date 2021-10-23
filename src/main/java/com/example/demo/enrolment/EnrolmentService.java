package com.example.demo.enrolment;

import com.example.demo.course.CourseRepository;
import com.example.demo.exception.APIEntityNotFoundException;
import com.example.demo.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnrolmentService {

    private final EnrolmentRepository enrolmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired //dependency injection
    public EnrolmentService(EnrolmentRepository enrolmentRepository, StudentRepository studentRepository, CourseRepository courseRepository)
    {
        this.enrolmentRepository = enrolmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    public List<Enrolment> getAllEnrolment()
    {
        return enrolmentRepository.findAll();
    }

    public List<Enrolment> getEnrolmentByGradeGreaterThanEqual(Integer grade)
    {
        return enrolmentRepository.findEnrolmentByGradeGreaterThanEqual(grade);
    }

    public Enrolment getOneEnrolment(Integer enrolmentId) {
        return enrolmentRepository.findById(enrolmentId).orElseThrow(()-> new APIEntityNotFoundException("Enrolment with id "+enrolmentId+" was not found"));
    }

    public void addEnrolment(Enrolment enrolment, Integer studentId, Integer courseId)
    {
        boolean existsStudent = studentRepository.existsById(studentId);
        if(!existsStudent)
        {
            throw new APIEntityNotFoundException("Student with id "+studentId+" was not found");
        }
        boolean existsCourse = courseRepository.existsById(courseId);
        if(!existsCourse)
        {
            throw new APIEntityNotFoundException("Course with id "+courseId+" was not found");
        }
        int student_facultyId = studentRepository.getById(studentId).getClassroom().getFaculty().getId(); //find this student belongs to which faculty
        String student_facultyName = studentRepository.getById(studentId).getClassroom().getFaculty().getName();
        int course_facultyId = courseRepository.getById(courseId).getFaculty().getId(); //find this course belongs to which faculty
        String course_facultyName = courseRepository.getById(courseId).getFaculty().getName();
        if(student_facultyId != course_facultyId) // if the student and course different in the faculty
        {
            throw new IllegalStateException("Student with id "+studentId+" have faculty of "+student_facultyName+" can't enroll. Because this courseId "+courseId+" belong to faculty of "+course_facultyName);
        }
        Optional<Enrolment> findEnrolment = enrolmentRepository.findEnrolmentByStudentIdAndCourseId(studentId,courseId);
        if(findEnrolment.isPresent())
        {
            throw new IllegalStateException("Can't enrol. Because student with id "+studentId+" already enrol course with id "+courseId);
        }
        enrolment.setStudent(studentRepository.getById(studentId));
        enrolment.setCourse(courseRepository.getById(courseId));
        enrolmentRepository.save(enrolment);
        System.out.println(enrolment);

        System.out.println("Enrolment: "+findEnrolment);

    }

    public void deleteEnrolment(Integer enrolmentId)
    {
        boolean existsById = enrolmentRepository.existsById(enrolmentId);
        if(!existsById)
        {
            throw new APIEntityNotFoundException("Enrolment with id "+enrolmentId+" was not found");
        }
        enrolmentRepository.deleteById(enrolmentId);
        System.out.println("Enrolment with id "+enrolmentId+" has been delete");
    }

    @Transactional //use setter to automatically update the entity in database
    public void updateEnrolment(Integer enrolmentId, Integer studentId, Integer courseId, Integer grade)
    {
        String update_successful = "Update successful";
        Enrolment enrolment = enrolmentRepository.findById(enrolmentId).orElseThrow(()-> new APIEntityNotFoundException("Enrolment with id "+enrolmentId+" was not found"));
        //update studentId
        if(studentId != null && enrolment.getStudent().getId() != studentId)
        {
            enrolment.setStudent(studentRepository.getById(studentId));
            System.out.println(update_successful);
        }
        //update courseId
        if(courseId != null && enrolment.getCourse().getId() != courseId)
        {
            enrolment.setCourse(courseRepository.getById(courseId));
            System.out.println(update_successful);
        }
        //update grade
        if(grade != null && enrolment.getGrade() != grade)
        {
            enrolment.setGrade(grade);
            System.out.println(update_successful);
        }
    }



}
