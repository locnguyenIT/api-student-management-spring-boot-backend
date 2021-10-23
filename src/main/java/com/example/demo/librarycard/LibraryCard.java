package com.example.demo.librarycard;

import com.example.demo.student.Student;

import javax.persistence.*;

@Entity(name = "Library_card")
@Table
public class LibraryCard {

    @Id
    @SequenceGenerator(name = "library_card_sequence",sequenceName = "library_card_sequence",allocationSize = 1) //generate sequence with id auto increment begin 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "library_card_sequence") //use sequence is just defined above
    @Column(name = "id",updatable = false,nullable = false)
    private int id;

    @Column(name = "card_number",nullable = false,unique = true)
    private String card_number;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "student_id",nullable = false,unique = true)
    private Student student;

    public LibraryCard() {
    }

    public LibraryCard(Student student, String cardNumber) {
        this.student = student;
        this.card_number = cardNumber;
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

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    @Override
    public String toString() {
        return "LibraryCard{" +
                "id=" + id +
                ", student=" + student +
                ", card_number='" + card_number + '\'' +
                '}';
    }
}
