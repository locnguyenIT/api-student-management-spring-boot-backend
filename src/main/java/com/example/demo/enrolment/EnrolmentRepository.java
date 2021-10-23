package com.example.demo.enrolment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment,Integer> {

    //case 1
//    @Query(value = "SELECT * FROM Enrolment e WHERE e.student_id = ?1 AND e.course_id = ?2",nativeQuery = true)
//    Optional<Enrolment> findEnrolmentByStudentIdAndCourseId(int studentId, int courseId);

    //case 2 JPQL
    Optional<Enrolment> findEnrolmentByStudentIdAndCourseId(int studentId, int courseId);

    //SELECT e FROM Enrolment e WHERE e.grade >= ?1
    List<Enrolment> findEnrolmentByGradeGreaterThanEqual(int grade);
}
