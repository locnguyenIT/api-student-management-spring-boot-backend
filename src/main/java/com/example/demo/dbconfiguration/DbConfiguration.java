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
            Faculty faculty_3 = new Faculty("English Language");

            facultyRepository.saveAll(List.of(faculty_1,faculty_2,faculty_3));

            //INSERT Classroom
            Classroom classDCT1  = new Classroom("DCT1171",faculty_1);
            Classroom classDCT2  = new Classroom("DCT1172",faculty_1);

            Classroom classDKQ1  = new Classroom("DKQ1171",faculty_2);
            Classroom classDKQ2  = new Classroom("DKQ1172",faculty_2);

            Classroom classDEL1  = new Classroom("DEL1171",faculty_3);
            Classroom classDEL2  = new Classroom("DEL1172",faculty_3);

            classroomRepository.saveAll(List.of(classDCT1,classDCT2,classDKQ1,classDKQ2,classDEL1,classDEL2));

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
            Student huy = new Student(
                    "Nguyen Huy",
                    "huy@gmail.com",
                    "TPHCM",
                    LocalDate.of(1998, Month.MARCH, 20),
                    classDEL1);
            Student hoang = new Student(
                    "Nguyen Hoang",
                    "hoang@gmail.com",
                    "Ninh Thuan",
                    LocalDate.of(2001, Month.MAY, 23),
                    classDEL1);
            Student phu = new Student(
                    "Nguyen Hoang Phu",
                    "phu@gmail.com",
                    "Can Tho",
                    LocalDate.of(1999, Month.MAY, 17),
                    classDEL2);
            Student nhat = new Student(
                    "Nguyen Nhat",
                    "Nhat@gmail.com",
                    "Kien Giang",
                    LocalDate.of(2001, Month.MAY, 23),
                    classDEL2);

            studentRepository.saveAll(List.of(loc,linh,bao,minh,han,nam,hien,phuc,huy,hoang,phu,nhat));

            //INSERT Library_card
            LibraryCard card_1 = new LibraryCard(loc,"1001");
            LibraryCard card_2 = new LibraryCard(nam,"1003");
            LibraryCard card_3 = new LibraryCard(hoang,"1002");
            LibraryCard card_4 = new LibraryCard(linh,"1010");
            LibraryCard card_5 = new LibraryCard(huy,"1007");
            LibraryCard card_6 = new LibraryCard(phu,"1004");
            LibraryCard card_7 = new LibraryCard(hien,"1005");
            LibraryCard card_8 = new LibraryCard(nhat,"1011");
            LibraryCard card_9 = new LibraryCard(phuc,"1020");
            LibraryCard card_10 = new LibraryCard(han,"1015");
            LibraryCard card_11 = new LibraryCard(minh,"1030");
            LibraryCard card_12 = new LibraryCard(bao,"1018");


            libraryCardRepository.saveAll(List.of(card_1,card_2,card_3,card_4,card_5,card_6,card_7,card_8,card_9,card_10,card_11,card_12));

            //INSERT Course
            Course course_1 = new Course("Kỹ thuật lập trình",faculty_1);
            Course course_2 = new Course("Lập trình Java",faculty_1);
            Course course_3 = new Course("Spring Boot Project",faculty_1);
            Course course_4 = new Course("Xác xuất thống kê",faculty_2);
            Course course_5 = new Course("Quản trị doanh nghiệp",faculty_2);
            Course course_6 = new Course("Kế hoạch kinh doanh",faculty_2);
            Course course_7 = new Course("Tiếng anh thương mại",faculty_3);
            Course course_8 = new Course("Thương mại quốc tế ",faculty_3);
            Course course_9 = new Course("Tiếng anh du lịch ",faculty_3);
            courseRepository.saveAll(List.of(course_1,course_2,course_3,course_4,course_5,course_6,course_7,course_8,course_9));

            //INSERT Enrolment
            Enrolment enrolment_1 = new Enrolment(loc,course_1,8) ;
            Enrolment enrolment_2 = new Enrolment(linh,course_2,5) ;
            Enrolment enrolment_3 = new Enrolment(bao,course_1,6) ;
            Enrolment enrolment_4 = new Enrolment(loc,course_3,9) ;
            Enrolment enrolment_5 = new Enrolment(hien,course_4,7) ;
            Enrolment enrolment_6 = new Enrolment(phuc,course_6,6) ;
            Enrolment enrolment_7 = new Enrolment(nam,course_5,8) ;
            Enrolment enrolment_8 = new Enrolment(nam,course_4,5) ;
            Enrolment enrolment_9 = new Enrolment(hoang,course_7,7) ;
            Enrolment enrolment_10 = new Enrolment(phuc,course_8,4) ;
            Enrolment enrolment_11 = new Enrolment(nhat,course_9,10) ;
            Enrolment enrolment_12 = new Enrolment(nhat,course_8,8) ;
            enrolmentRepository.saveAll(List.of(enrolment_1, enrolment_2, enrolment_3, enrolment_4, enrolment_5, enrolment_6,enrolment_7,enrolment_8,enrolment_9,enrolment_10,enrolment_11,enrolment_12));


        };
    }
}
