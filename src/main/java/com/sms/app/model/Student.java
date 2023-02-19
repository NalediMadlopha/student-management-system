package com.sms.app.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @GenericGenerator(
            name = "student_seq",
            strategy = "com.sms.app.model.StudentIdGenerator",
            parameters = {
                    @Parameter(name = StudentIdGenerator.VALUE_PREFIX_PARAMETER, value = "Stu"),
                    @Parameter(name = StudentIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%07d")
            })
    private String STUDENT_NUMBER;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;

    public Student() {
    }

    public Student(String STUDENT_NUMBER, String firstName, String lastName, String email) {
        this.STUDENT_NUMBER = STUDENT_NUMBER;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
