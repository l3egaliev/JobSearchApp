package kg.rakhim.classes.jobsearchapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @Column(name = "vacancy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotNull(message = "Название вакансии не может быть пустым!")
    @Size(min = 3, max = 60, message = "от 3 до 60 символов")
    private String name;

    @Column(name = "description")
    @NotNull(message = "Пожалуйста введите описание вакансии...")
    @Size(min = 10, message = "Должно быть не менее 10 слов!")
    private String description;
    @Column(name = "salary_from")
    private Integer salaryFrom;
    @Column(name = "salary_to")
    private Integer salaryTo;
    @Column(name = "contacts")
    private String contacts;

    @Column(name = "company_id")
    private Integer company_id;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Vacancy(String name, String description, int salaryFrom, int salaryTo, String contacts) {
        this.name = name;
        this.description = description;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.contacts = contacts;
    }

    public Vacancy(){}


    public void setSalaryFrom(Integer salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public void setSalaryTo(Integer salaryTo) {
        this.salaryTo = salaryTo;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSalaryFrom() {
        return salaryFrom;
    }
    public Integer getSalaryTo(){
        return salaryTo;
    }

    public void setSalaryFrom(int salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public void setSalaryTo(int salaryTo){this.salaryTo=salaryTo;}

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }


    public Date getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
}
