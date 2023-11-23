package kg.rakhim.classes.jobsearchapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.rakhim.classes.jobsearchapp.entities.Company;
import kg.rakhim.classes.jobsearchapp.entities.Vacancy;

import java.util.Date;

public class VacancyDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Название вакансии не может быть пустым!")
    @Size(min = 3, max = 60, message = "от 3 до 60 символов")
    private String name;

    @NotNull(message = "Пожалуйста введите описание вакансии...")
    @Size(min = 10, message = "Должно быть не менее 10 слов!")
    private String description;

    private Integer salaryFrom;

    private Integer salaryTo;

    private String contacts;

    private CompanyDTO company;

    public static VacancyDTO convertToDto(Vacancy vacancy){
        VacancyDTO dto = new VacancyDTO();
        dto.setContacts(vacancy.getContacts());
        dto.setDescription(vacancy.getDescription());
        dto.setName(vacancy.getName());
        dto.setSalaryFrom(vacancy.getSalaryFrom());
        dto.setSalaryTo(vacancy.getSalaryTo());
        dto.setCompany(CompanyDTO.convertToDto(vacancy.getCompany()));
        dto.setId(vacancy.getId());


        return dto;
    }

    public static Vacancy convertToVacancy(VacancyDTO dto) {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(dto.getName());
        vacancy.setDescription(dto.getDescription());
        vacancy.setSalaryFrom(dto.getSalaryFrom());
        vacancy.setSalaryTo(dto.getSalaryTo());
        vacancy.setContacts(dto.getContacts());


        return vacancy;
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

    public void setSalaryFrom(Integer salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public Integer getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Integer salaryTo) {
        this.salaryTo = salaryTo;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO companyDTO) {
        this.company = companyDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
