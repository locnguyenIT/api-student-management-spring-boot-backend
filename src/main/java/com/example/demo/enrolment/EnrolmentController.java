package com.example.demo.enrolment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/spring-boot/enrolment")
public class EnrolmentController {

    private final EnrolmentService enrolmentService;

    @Autowired
    public EnrolmentController(EnrolmentService enrolmentService) {
        this.enrolmentService = enrolmentService;
    }

    @GetMapping
    public List<Enrolment> getAllEnrolment()
    {
        return enrolmentService.getAllEnrolment();
    }

    @GetMapping(path = "/get/{enrolmentId}")
    public Enrolment getOneEnrolment(@PathVariable("enrolmentId") Integer enrolmentId)
    {
        return enrolmentService.getOneEnrolment(enrolmentId);
    }

    @GetMapping(path = "/get/grade/{grade}")
    public List<Enrolment> getEnrolmentByGradeGreaterThanEqual(@PathVariable("grade") Integer grade)
    {
        return enrolmentService.getEnrolmentByGradeGreaterThanEqual(grade);
    }

    @PostMapping(path = "/add/studentId/{studentId}/courseId/{courseId}")
    public void addGrade(@RequestBody Enrolment enrolment,
                          @PathVariable("studentId") Integer studentId,
                          @PathVariable("courseId") Integer courseId)
    {
        enrolmentService.addEnrolment(enrolment,studentId,courseId);
    }

    @DeleteMapping(path = "/delete/{enrolmentId}")
    public void deleteEnrolment(@PathVariable("enrolmentId") Integer enrolmentId)
    {
        enrolmentService.deleteEnrolment(enrolmentId);
    }

    @PutMapping(path = "/update/{enrolmentId}") //http://localhost:8080/api/spring-boot/enrolment/update/"enrolmentId"?studentId="id"&courseId="";
    public void updateEnrolment(@PathVariable("enrolmentId") Integer enrolmentId,
                            @RequestParam(required = false) Integer studentId,
                            @RequestParam(required = false) Integer courseId,
                            @RequestParam(required = false) Integer grade)
    {
        enrolmentService.updateEnrolment(enrolmentId,studentId,courseId,grade);
    }
}
