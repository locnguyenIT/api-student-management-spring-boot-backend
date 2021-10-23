package com.example.demo.course;

import com.example.demo.enrolment.Enrolment;
import com.example.demo.faculty.Faculty;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Course")
@Table
public class Course {

    @Id
    @SequenceGenerator(name = "course_sequence",sequenceName = "course_sequence",allocationSize = 1) //generate sequence with id auto increment begin 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence") //use sequence is just defined above
    @Column(name = "id",updatable = false,nullable = false)
    private int id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "faculty_id") //foreign key
    private Faculty faculty;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Enrolment> enrolment;

    public Course() {
    }


    public Course(String name, Faculty faculty) {
        this.name = name;
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faculty=" + faculty +
                '}';
    }
}
