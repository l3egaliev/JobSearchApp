



document.addEventListener("DOMContentLoaded", function(){
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
    form.querySelector("button").addEventListener("click", function(){
        console.log(getFormValues())
    
        var postUrl = "http://localhost:8080/vacancies"
        var postRequest = new XMLHttpRequest()
    
        postRequest.open('POST', postUrl, true)
        postRequest.setRequestHeader('Content-Type', 'application/json');
    
        postRequest.onload = function(){
          var response = JSON.parse(postRequest.response)
          if(postRequest.status >= 200 && postRequest.status <= 300){
              window.location.href = "vacancy.html?id="+response.id
          }
        }

        postRequest.send(JSON.stringify(getFormValues()))
       

    })



})



