package com.example.demo.student;

import com.example.demo.classroom.Classroom;
import com.example.demo.enrolment.Enrolment;
import com.example.demo.librarycard.LibraryCard;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Entity(name = "Student") //Mapping Student class to table in database which have column (id, name, email, dob)
@Table
public class Student  {

    @Id //id will be primary key of table student
    @SequenceGenerator(name = "student_sequence",sequenceName = "student_sequence",allocationSize = 1) //generate sequence with id auto increment begin 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence") //use sequence is just defined above
    @Column(name = "id",updatable = false,nullable = false)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "dob",nullable = false)
    private LocalDate dob;

    @Transient //This annotation mean is no need column age in database because age will calculate by dob
    private int age;

    @ManyToOne
    @JoinColumn(name = "classroom_id")//classroom_id  column  is Foreign Key in student table
    private Classroom classroom;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Enrolment> enrolment;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private LibraryCard libraryCard;

    public Student() {}

    public Student(String name, String email, String address,LocalDate dob, Classroom classroom) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.classroom = classroom;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    } //count age


    public void setAge(int age) {
        this.age = age;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", age=" + getAge() +
                ", classroom=" + classroom +
                '}';
    }
}
