package com.okta.springbootvue.entity.test;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

import javax.validation.constraints.*;


@Data
@Entity
@NoArgsConstructor
@Table(name="STUDENT",uniqueConstraints = @UniqueConstraint(columnNames = {"STUDENT_ID"}))
public class Student {

    @Id
    @SequenceGenerator(name="student_seq",sequenceName="student_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="student_seq")
    @Column(name = "STUDENT_ID", unique = true, nullable = true)
    private Long id;

    @NotNull
    @Column(name="STUDENT_CODE")
    private String stuCode;

    @NotNull
    @Column(name="NAME")
    private String name;

    @NotNull
    @Column(name = "SUBJECT")
    private String subject;

    @NotNull
    @Column(name="PHONE_NUMBER")
    private String tel;

}
