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
    showVacancy(vacancy)
  }

    


  function showVacancy(vacancyToShow){
        var block = document.getElementById("vacancy-block")
        var name = document.createElement("div")
        var salary = document.createElement("div")
        var btns = document.createElement("div")
        var reaction = document.createElement("a")
        
        var vacancy_contacts = document.getElementById("vacancy-contacts")
        var contacts = document.createElement("div")
        
        var desc_block = document.getElementById("desc_block")
        var desc = document.createElement("p")

        var salary_text = document.querySelector(".salary-level")
        var show_contacts_btn = document.createElement("a")


        name.classList.add("vacancy-name")
        salary.classList.add("vacancy-salary")
        btns.classList.add("btns")
        reaction.classList.add("reaction-btn")
        show_contacts_btn.classList.add("show-contacts-btn")
        desc.classList.add("desc")
        contacts.classList.add("vacancy-contacts")

        name.textContent = vacancyToShow.name
        salary.textContent = vacancyToShow.salary + " ₽ "
        reaction.textContent = "Откликнуться"
        show_contacts_btn.textContent = "Показать контакты"
        desc.textContent = vacancyToShow.description
        contacts.textContent = vacancyToShow.contacts


        btns.appendChild(reaction)
        btns.appendChild(show_contacts_btn)
        block.appendChild(name)
        salary_text.append(salary)
        block.append(salary_text)
        block.appendChild(btns)
        

        desc_block.appendChild(desc)
        vacancy_contacts.appendChild(contacts)

        reaction.addEventListener('click', function(){
            showModal()
        })

        show_contacts_btn.addEventListener("click", function(){
            showContact(contacts)
        })
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