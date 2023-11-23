document.addEventListener("DOMContentLoaded", function(){
    const urlParams = new URLSearchParams(window.location.search);
    const vacancyId = urlParams.get('id');

    console.log(vacancyId)

    var getOne = "http://localhost:8080/vacancies/"+vacancyId

    var requestToOne = new XMLHttpRequest()

    requestToOne.open("GET", getOne)

    requestToOne.responseType = "json"
    requestToOne.send()

        
    requestToOne.onload = function() {
      if(requestToOne.status >= 200 && requestToOne.status <= 300){
          var vacancy = requestToOne.response
          console.log(vacancy)
          setData(vacancy)
      }
    }
        
    

    function setData(data){
      
        const form = document.getElementById("vacancyForm");
        form.elements.name.value =  data.name    
        form.elements.description.value =  data.description
        form.elements.contacts.value =  data.contacts
        form.elements.salaryFrom.value =  data.salaryFrom
        form.elements.salaryTo.value =  data.salaryTo
      
    }

 
    
    function getFormValues() {
        // Получаем форму по ее ID
        const form = document.getElementById("vacancyForm");
      
        // Создаем объект, в который будем сохранять значения из формы
        let formData = {};
      
        // Проходим по всем элементам формы
        for (let i = 0; i < form.elements.length; i++) {
          let element = form.elements[i];
      
          // Проверяем, является ли элемент полем ввода (не кнопкой и т.д.)
          if (element.type !== "button") {
            // Присваиваем значение свойству объекта с именем элемента
            formData[element.name] = element.type === "checkbox" ? element.checked : element.value;
          }
        }


        // // Возвращаем объект с данными формы (опционально)
        return formData;
      }


    
    var form = document.getElementById("vacancyForm")
    form.querySelector("#save").addEventListener("click", function(){
        console.log(getFormValues())
    
        var patchUrl = "http://localhost:8080/vacancies/edit/"+vacancyId
        var patchRequest = new XMLHttpRequest()
    
        patchRequest.open('PATCH', patchUrl, true)
        patchRequest.setRequestHeader('Content-Type', 'application/json');
        
        patchRequest.onload = function(){
          if(patchRequest.status >= 200 && patchRequest.status <= 300){
              console.log("Вакансия успешно сохранена")
              window.location.href = "/pages/public/vacancy.html?id="+vacancyId
          }else{
              console.log(patchRequest.response.message)
          } 
        }

        patchRequest.send(JSON.stringify(getFormValues()))
       

    })


        
    var delForm = document.getElementById("vacancyForm")
    delForm.querySelector("#delete").addEventListener("click", function(){
            var deleteRequest = new XMLHttpRequest()
            var deleteUrl = "http://localhost:8080/vacancies/edit/"+vacancyId
            deleteRequest.open('DELETE',deleteUrl)
            deleteRequest.send()
            deleteRequest.onload = function(){
              if(deleteRequest.status >= 200 && deleteRequest.status <= 300){
                window.location.href = "/pages/public/vacancies.html"
              }
            }

           
        })

})