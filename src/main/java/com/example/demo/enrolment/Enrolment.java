package com.example.demo.enrolment;

import com.example.demo.course.Course;
import com.example.demo.student.Student;

import javax.persistence.*;

@Entity(name = "Enrolment")
@Table
public class Enrolment {

    @Id //primary key
    @SequenceGenerator(name = "enrolment_sequence",sequenceName = "enrolment_sequence",allocationSize = 1) //generate sequence with id auto increment begin 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrolment_sequence") //use sequence is just defined above
    @Column(name = "id",nullable = false,updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id") //foreign key
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id") //foreign key
    private Course course;

    @Column(name = "grade",columnDefinition = "INT")
    private int grade;

    public Enrolment() {
    }

    public Enrolment(Student student, Course course, int grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                ", grade=" + grade +
                '}';
    }
}
