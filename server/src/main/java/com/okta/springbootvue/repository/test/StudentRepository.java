package com.okta.springbootvue.repository.test;

import com.okta.springbootvue.entity.test.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public
interface StudentRepository extends JpaRepository<Student, Long> {
    Student findById(long id);

    @Query( value = "SELECT * FROM STUDENT WHERE STUDENT_CODE LIKE :stuCode ",
            nativeQuery = true)
    Collection<Student> findByStudentCode(@Param("stuCode") String stuCode);

}