package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain;

import javax.persistence.*;

// ENTITY - Student - Subclase de Person
// Usa SINGLE_TABLE inheritance con discriminador value "Student"
@Entity
@DiscriminatorValue("Student")
public class Student extends Person {

    // ATRIBUTO - studentCode - Código único del estudiante KEYWORD: STUDENT_CODE, VALIDATION
    private String studentCode;

    // CONSTRUCTOR por defecto para JPA
    public Student() {
    }

    // GETTER - retorna el código del estudiante VALIDATION: String puede ser null
    public String getStudentCode() {
        return studentCode;
    }

    // SETTER - asigna el código del estudiante VALIDATION: requiere String válido
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
}