package kg.rakhim.classes.jobsearchapp.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull(message = "Название компании не может быть пустым!")
    @Size(min = 2, message = "Минимум от 2 символов!")
    private String name;

    @Column(name = "email")
    @Email
    @NotNull(message = "Адрес электронной почты компании!")
    private String email;

    @OneToMany(mappedBy = "company")
    private List<Vacancy> vacancies;

    @Column(name = "about")
    private String about;
    @Column(name = "address")
    @Pattern(regexp = "[А-Я]\\p{IsCyrillic}+, [а-я]\\p{IsCyrillic}+ [А-Я]\\p{IsCyrillic}+ \\d+",
            message = "Адрес имеет такой формат(" +
            "Бишкек, улица Раззакова 12)")
    private String address;

    public Company(String name, String email, String about, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.about = about;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public String getAbout(){
        return about;
    }

    public void setAbout(String about){
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
