package kg.rakhim.classes.jobsearchapp.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    @Column(name = "salary_from")
    private Integer salaryFrom;
    @Column(name = "salary_to")
    private Integer salaryTo;
    @Column(name = "contacts")
    private String contacts;

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
}
