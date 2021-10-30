package com.example.demo.dbconfiguration;

import com.example.demo.classroom.Classroom;
import com.example.demo.classroom.ClassroomRepository;
import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import com.example.demo.faculty.Faculty;
import com.example.demo.faculty.FacultyRepository;
import com.example.demo.enrolment.Enrolment;
import com.example.demo.enrolment.EnrolmentRepository;
import com.example.demo.librarycard.LibraryCard;
import com.example.demo.librarycard.LibraryCardRepository;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class DbConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        ClassroomRepository classroomRepository,
                                        FacultyRepository facultyRepository,
                                        CourseRepository courseRepository,
                                        EnrolmentRepository enrolmentRepository,
                                        LibraryCardRepository libraryCardRepository)
    {
        return args ->  {

            //INSERT Faculty
            Faculty faculty_1  = new Faculty("Information Technology") ;
            Faculty faculty_2  = new  Faculty("Business Administration") ;

            facultyRepository.saveAll(List.of(faculty_1,faculty_2));

            //INSERT Classroom
            Classroom classDCT1  = new Classroom("DCT1171",faculty_1);
            Classroom classDCT2  = new Classroom("DCT1172",faculty_1);

            Classroom classDKQ1  = new Classroom("DKQ1171",faculty_2);
            Classroom classDKQ2  = new Classroom("DKQ1172",faculty_2);


            classroomRepository.saveAll(List.of(classDCT1,classDCT2,classDKQ1,classDKQ2));

            //INSERT Student
            Student loc = new Student(
                    "Nguyen Thanh Loc",
                    "ntloc.developer@gmail.com",
                    "Khanh Hoa",
                    LocalDate.of(1999, Month.SEPTEMBER, 28),
                    classDCT1);
            Student linh = new Student(
                    "Tran Ha Linh",
                    "halinh@gmail.com",
                    "TPHCM",
                    LocalDate.of(2002, Month.DECEMBER, 12),
                    classDCT1);
            Student bao = new Student(
                    "Tran Ba  Bao",
                    "babao@gmail.com",
                    "TPHCM",
                    LocalDate.of(1999, Month.NOVEMBER, 18),
                    classDCT2);
            Student minh = new Student(
                    "Nguyen Hoang Minh",
                    "minh@gmail.com",
                    "Dong Nai",
                    LocalDate.of(1999, Month.NOVEMBER, 05),
                    classDCT2);
            Student han = new Student(
                    "Nguyen Ngoc Han",
                    "ngochan@gmail.com",
                    "Lam Dong",
                    LocalDate.of(2000, Month.MARCH, 22),
                    classDKQ1);
            Student nam = new Student(
                    "Do Nam",
                    "donam@gmail.com",
                    "Ben Tre",
                    LocalDate.of(2000, Month.JUNE, 18),
                    classDKQ1);
            Student hien = new Student(
                    "Pham Hien",
                    "hien@gmail.com",
                    "Long An",
                    LocalDate.of(2001, Month.JULY, 12),
                    classDKQ2);
            Student phuc = new Student(
                    "Huynh Minh Phuc",
                    "phuc@gmail.com",
                    "Vung Tau",
                    LocalDate.of(2000, Month.MAY, 07),
                    classDKQ2);

            studentRepository.saveAll(List.of(loc,linh,bao,minh,han,nam,hien,phuc));

            //INSERT Library_card
            LibraryCard card_1 = new LibraryCard(loc,"1001");
            LibraryCard card_2 = new LibraryCard(nam,"1003");
            LibraryCard card_3 = new LibraryCard(linh,"1010");
            LibraryCard card_4 = new LibraryCard(hien,"1005");
            LibraryCard card_5 = new LibraryCard(phuc,"1020");
            LibraryCard card_6 = new LibraryCard(han,"1015");
            LibraryCard card_7 = new LibraryCard(minh,"1030");
            LibraryCard card_8 = new LibraryCard(bao,"1018");


            libraryCardRepository.saveAll(List.of(card_1,card_2,card_3,card_4,card_5,card_6,card_7,card_8));

            //INSERT Course
            Course course_1 = new Course("Kỹ thuật lập trình",faculty_1);
            Course course_2 = new Course("Lập trình Java",faculty_1);
            Course course_3 = new Course("Spring Boot Project",faculty_1);
            Course course_4 = new Course("Xác xuất thống kê",faculty_2);
            Course course_5 = new Course("Quản trị doanh nghiệp",faculty_2);
            Course course_6 = new Course("Kế hoạch kinh doanh",faculty_2);
            courseRepository.saveAll(List.of(course_1,course_2,course_3,course_4,course_5,course_6));

            //INSERT Enrolment
            Enrolment enrolment_1 = new Enrolment(loc,course_1,8) ;
            Enrolment enrolment_2 = new Enrolment(linh,course_2,5) ;
            Enrolment enrolment_3 = new Enrolment(bao,course_1,6) ;
            Enrolment enrolment_4 = new Enrolment(loc,course_3,9) ;
            Enrolment enrolment_5 = new Enrolment(hien,course_4,7) ;
            Enrolment enrolment_6 = new Enrolment(phuc,course_6,6) ;
            Enrolment enrolment_7 = new Enrolment(minh,course_3,8) ;
            Enrolment enrolment_8 = new Enrolment(nam,course_4,5) ;
            Enrolment enrolment_9 = new Enrolment(hien,course_5,7) ;
            Enrolment enrolment_10 = new Enrolment(han,course_5,4) ;
            enrolmentRepository.saveAll(List.of(enrolment_1, enrolment_2, enrolment_3, enrolment_4, enrolment_5, enrolment_6,enrolment_7,enrolment_8,enrolment_9,enrolment_10));


        };
    }
}
