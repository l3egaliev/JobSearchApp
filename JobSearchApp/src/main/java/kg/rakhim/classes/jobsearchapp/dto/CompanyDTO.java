package kg.rakhim.classes.jobsearchapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.rakhim.classes.jobsearchapp.entities.Company;
import kg.rakhim.classes.jobsearchapp.entities.Vacancy;

import java.util.List;

public class CompanyDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Название компании не может быть пустым!")
    @Size(min = 2, message = "Минимум от 2 символов!")
    private String name;

    @Column(name = "email")
    @Email(message = "Адрес электронной почты должен быть правильным")
    @NotNull(message = "Это поле не может быть пустым")
    private String email;

    @Size(max=100, message = "Максимум 100 символов")
    private String about;

    @Pattern(regexp = "[А-Я]\\S+, [а-я]\\S+ [А-Я]\\S+ \\d+", message = "Адрес имеет такой формат(" +
            "Бишкек, улица Раззакова 12)")
    private String address;

    public static CompanyDTO convertToDto(Company company){
        CompanyDTO dto = new CompanyDTO();
        dto.setAbout(company.getAbout());
        dto.setAddress(company.getAddress());
        dto.setName(company.getName());
        dto.setEmail(company.getEmail());
        dto.setId(company.getId());
        return dto;
    }


    public static Company convertToModel(CompanyDTO dto){
        Company company = new Company();
        company.setAbout(dto.getAbout());
        company.setAddress(dto.getAddress());
        company.setName(dto.getName());
        company.setEmail(dto.getEmail());
        company.setId(dto.getId());

        return company;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
