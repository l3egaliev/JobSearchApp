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
      var salary = document.createElement('div')
      var vacancies = document.createElement("div")
      var btns = document.createElement("div")
      var reaction_btn = document.createElement("a")
      var show_contacts_btn = document.createElement("a")


        name.classList.add("vacancy-name")
        salary.classList.add("vacancy-salary")
        vacancies.classList.add("vacancies")
        btns.classList.add("btns")
        reaction_btn.classList.add("reaction-btn")
        show_contacts_btn.classList.add("show-contacts-btn")

        reaction_btn.textContent = "Откликнуться"
        show_contacts_btn.textContent = "Показать контакты"
        name.textContent = jsonVacancies[i].name
        salary.textContent = jsonVacancies[i].salary + " ₽"
        

        btns.appendChild(reaction_btn)
        btns.appendChild(show_contacts_btn)
        vacancies.appendChild(name)
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

