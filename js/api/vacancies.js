const urlParams = new URLSearchParams(window.location.search);
const page = urlParams.get('page');
const vacancy_per_page = urlParams.get('vacancy_per_page')

var requestUrl = "http://localhost:8080/vacancies"+"/"+page+"/"+vacancy_per_page

if(page == null & vacancy_per_page == null){
  requestUrl = "http://localhost:8080/vacancies"
}


  console.log(requestUrl)
  var requestToAll = new XMLHttpRequest()

  requestToAll.open("GET", requestUrl)

  requestToAll.responseType = "json"
  requestToAll.send()


  requestToAll.onload = function() {
    var vacancies = requestToAll.response
    showVacancies(vacancies)
  }


  
  
  function showVacancies(jsonVacancies){
    var block = document.getElementById("block")
   
    
    for(var i = 0; i < jsonVacancies.length; i++){
      var name = document.createElement("a")
      var salary = document.createElement("div")
      var nullSalary = document.createElement("span")
      var salaryFrom = document.createElement('div')
      var salaryTo = document.createElement('div')
      var vacancies = document.createElement("div")
      var btns = document.createElement("div")
      var reaction_btn = document.createElement("a")
      var show_contacts_btn = document.createElement("a")


      name.classList.add("vacancy-name")
      salaryFrom.classList.add("salaryFrom")
      salaryTo.classList.add("salaryTo")
      vacancies.classList.add("vacancies")
      btns.classList.add("btns")
      reaction_btn.classList.add("reaction-btn")
      show_contacts_btn.classList.add("show-contacts-btn")
      salary.classList.add("salary")

      
      

      nullSalary.style.display = "none"
      nullSalary.textContent = "Зарплата не указана"
      reaction_btn.textContent = "Откликнуться"
      show_contacts_btn.textContent = "Показать контакты"
      name.textContent = jsonVacancies[i].name
      salaryFrom.textContent = "от " + formatNumberWithSpace(jsonVacancies[i].salaryFrom)
      salaryTo.textContent = "до " + formatNumberWithSpace(jsonVacancies[i].salaryTo) + " ₽"
      
      if(jsonVacancies[i].salaryFrom === null && jsonVacancies[i].salaryTo === null){
        salaryFrom.style.display = "none"
        salaryTo.style.display = "none"
        nullSalary.style.display = "block"
        nullSalary.style.fontSize = "24px"
    } else if(jsonVacancies[i].salaryFrom === 0 && jsonVacancies[i].salaryTo === 0){
        salaryFrom.style.display = "none"
        salaryTo.style.display = "none"
        nullSalary.style.display = "block"
        nullSalary.style.fontSize = "24px"
    }
        

        btns.appendChild(reaction_btn)
        btns.appendChild(show_contacts_btn)
        vacancies.appendChild(name)
        salary.appendChild(salaryFrom)
        salary.appendChild(salaryTo)
        salary.appendChild(nullSalary)
        vacancies.appendChild(salary)
        vacancies.appendChild(btns)
        block.appendChild(vacancies)
        
        name.addEventListener('click', (function (index) {
          return function () {
            redirectToPage(jsonVacancies[index].id);
          };
        })(i));
    
    }
    

}

function redirectToPage(id) {
  var pageUrl = 'vacancy.html?id='+id;
  
  
  // Переходим на новую страницу
  window.open(pageUrl, '_blank');

}


function search(){
  var form = document.getElementById("searchForm")
  var input = form.querySelector(".search")

  form.querySelector("button").addEventListener("click", function(){
    console.log(input.value)
    var url = "http://localhost:8080/vacancies/search/"+input.value

    var xhr = new XMLHttpRequest()
  
    xhr.open("GET", url)
  
    xhr.responseType = "json"
    xhr.send()
  
  
    xhr.onload = function() {
      var founded = xhr.response
      console.log(founded)
      foundedVacancies(founded)
    }

  })

}


function foundedVacancies(founded){
  var block = document.getElementById("block")
  while(block.firstChild){
    block.removeChild(block.firstChild)
  }
    showVacancies(founded)
}

document.addEventListener("DOMContentLoaded", function(){
  search()
})



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