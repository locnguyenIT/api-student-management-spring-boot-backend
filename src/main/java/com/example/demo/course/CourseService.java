package com.example.demo.course;

import com.example.demo.exception.APIEntityNotFoundException;
import com.example.demo.faculty.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, FacultyRepository facultyRepository) {
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
    }

    public List<Course> getAllCourse()
    {
        return courseRepository.findAll();
    }

    public Course getOneCourse(Integer courseId)
    {
        return courseRepository.findById(courseId).orElseThrow(()-> new APIEntityNotFoundException("Course with id "+courseId+" was not found"));
    }

    public void addCourse(Course course, Integer facultyId)
    {
        Optional<Course> courseByName = courseRepository.findCourseByName(course.getName());
        if(courseByName.isPresent())
        {
            throw new IllegalStateException("Name already exist in database");
        }
        boolean exists = facultyRepository.existsById(facultyId);
        if(!exists)
        {
            throw new APIEntityNotFoundException("Faculty with id "+facultyId+ " was not found");
        }
        course.setFaculty(facultyRepository.getById(facultyId));
        courseRepository.save(course);
        System.out.println(course);
    }

    public void deleteCourse(Integer courseId)
    {
        boolean exists = courseRepository.existsById(courseId);
        if(!exists)
        {
            throw new IllegalStateException("Course with id = "+courseId+ " does not exist in database");
        }
        System.out.println("Course id = "+courseId+" has been delete");
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(Integer courseId, String name, Integer facultyId)
    {
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new APIEntityNotFoundException("Course with id "+courseId+" was not found"));
        if(name != null && name.length() > 0 && name != course.getName())
        {
            course.setName(name);
        }
        if(facultyId != null && course.getFaculty().getId() != facultyId) //if the new facultyId has been provided is not the same classroomId in database
        {
            course.setFaculty(facultyRepository.getById(facultyId));
        }
    }


}
