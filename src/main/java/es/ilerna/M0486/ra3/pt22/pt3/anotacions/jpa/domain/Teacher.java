package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain;

import javax.persistence.*;

// ENTITY - Teacher - Subclase de Person
// Usa SINGLE_TABLE inheritance con discriminador value "Teacher"
@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends Person {

    // ATRIBUTO - teacherCode - Código único del profesor KEYWORD: TEACHER_CODE, VALIDATION
    private String teacherCode;

    // CONSTRUCTOR por defecto para JPA
    public Teacher() {
    }

    // GETTER - retorna el código del profesor VALIDATION: String puede ser null
    public String getTeacherCode() {
        return teacherCode;
    }

    // SETTER - asigna el código del profesor VALIDATION: requiere String válido
    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }
}