const urlParams = new URLSearchParams(window.location.search);
const vacancyId = urlParams.get('id');

console.log(vacancyId)

var getOne = "http://localhost:8080/vacancies/"+vacancyId

var requestToOne = new XMLHttpRequest()

requestToOne.open("GET", getOne)

requestToOne.responseType = "json"
requestToOne.send()

requestToOne.onload = function() {
    var vacancy = requestToOne.response
    console.log(vacancy)
    showVacancy(vacancy)
  }

    


  function showVacancy(vacancyToShow){
        var block = document.getElementById("vacancy-block")
        var name = document.createElement("div")

        var company = document.getElementById("company")
        var company_name = document.createElement("a")
        var company_address = document.createElement("p")
        
        var salary = document.createElement("div")
        var salaryFrom = document.createElement("div")
        var salaryTo = document.createElement("div")
        var nullSalary = document.createElement("span")
        nullSalary.style.display = "none"

        var btns = document.createElement("div")
        var reaction = document.createElement("a")
        
        var vacancy_contacts = document.getElementById("vacancy-contacts")
        var contacts = document.createElement("div")
        
        var desc_block = document.getElementById("desc_block")
        var desc = document.createElement("p")

        var salary_text = document.querySelector(".salary-level")
        var show_contacts_btn = document.createElement("a")


        addClassList(name, "vacancy-name")
        addClassList(company_name, "company_name")
        addClassList(salary, "salaryFromTo")
        addClassList(salaryFrom, "salaryFrom")
        addClassList(salaryTo, "salaryTo")
        addClassList(btns, "btns")
        addClassList(reaction, "reaction-btn")
        addClassList(show_contacts_btn, "show-contacts-btn")        
        addClassList(desc, "desc")
        addClassList(contacts, "vacancy-contacts")
        addClassList(company_address, "company-address")
        
        nullSalary.textContent = "Зарплата не указана"
        name.textContent = vacancyToShow.name
        salaryFrom.textContent = "от "+formatNumberWithSpace(vacancyToShow.salaryFrom)
        salaryTo.textContent = "до "+formatNumberWithSpace(vacancyToShow.salaryTo)+" ₽"
        if(vacancyToShow.company != null){
            company_name.textContent = vacancyToShow.company.name
            company_address.textContent = vacancyToShow.company.address
        }
        
        reaction.textContent = "Откликнуться"
        show_contacts_btn.textContent = "Показать контакты"
        desc.textContent = vacancyToShow.description
        contacts.textContent = vacancyToShow.contacts
        if(vacancyToShow.contacts === ""){
            contacts.textContent = "Контакты не указаны"
        }

        if(vacancyToShow.salaryFrom == null && vacancyToShow.salaryTo == null){
            salaryFrom.style.display = "none"
            salaryTo.style.display = "none"
            nullSalary.style.display = "block"
            nullSalary.style.color = "red"
        }else if(vacancyToShow.salaryFrom === 0 && vacancyToShow.salaryTo === 0){
            salaryFrom.style.display = "none"
            salaryTo.style.display = "none"
            nullSalary.style.display = "block"
            nullSalary.style.color = "red"

        }

        btns.appendChild(reaction)
        btns.appendChild(show_contacts_btn)
        block.appendChild(name)
        salary.appendChild(salaryFrom)
        salary.appendChild(salaryTo)
        salary.appendChild(nullSalary)
        salary_text.append(salary)
        block.append(salary_text)
        block.appendChild(btns)
        company.appendChild(company_name)
        company.appendChild(company_address)

        desc_block.appendChild(desc)
        vacancy_contacts.appendChild(contacts)

        reaction.addEventListener('click', function(){
            showModal()
        })

        show_contacts_btn.addEventListener("click", function(){
            showContact(contacts)
        })
  }


  function addClassList(element, classOfElement){
        element.classList.add(classOfElement)
  }


  function reactionTest(name){
      alert("Вы откликнулись на вакансию: "+name.textContent)
  }


  function showContact(contacts){
      alert("Контакты работадателя: "+contacts.textContent)
  }

  function showModal(){
    var modal = document.querySelector(".modal")
    var modal_block = document.createElement("div")
    var modal_text = document.createElement("p")
    var modal_button = document.createElement("a")

    modal_block.classList.add("modal_block")
    modal_text.classList.add("modal_text")
    modal_button.classList.add("modal_button")

    modal_text.textContent = "Ваше резюме доставлено работадателю ✔"
    modal_button.textContent = "Ок"

    modal_block.appendChild(modal_text)
    modal_block.appendChild(modal_button)
    modal.appendChild(modal_block)


    modal_button.addEventListener("click", function(){
        modal_block.style.display = "none"
    })

  }


  function formatNumberWithSpace(number){
    const strNumber = String(number)
    if(strNumber.length <= 5){
        return strNumber.slice(0,2)+" " + strNumber.slice(2)
    }else if(strNumber.length >= 6){
        return strNumber.slice(0, 3) + " " + strNumber.slice(3)
    }else{
        return strNumber
    }


  }