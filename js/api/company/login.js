

document.addEventListener("DOMContentLoaded", function(){
    function getFormValues() {
        // Получаем форму по ее ID
        const form = document.getElementById("block");
      
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
    
    var form = document.getElementById("block")
    form.querySelector("button").addEventListener("click", function(){
        console.log(getFormValues())
    
        var postUrl = "http://localhost:8080/companies/create"
        var postRequest = new XMLHttpRequest()
    
        postRequest.open('POST', postUrl, true)
        postRequest.setRequestHeader('Content-Type', 'application/json');
    
        postRequest.onload = function(){
          var response = postRequest.response
          if(postRequest.status >= 200 && postRequest.status <= 300){
              console.log("Успешно" + response)
          }else{
            feedback(response)
          }
        }

        postRequest.send(JSON.stringify(getFormValues()))
       

    })


    function feedback(response){
        

        var message = JSON.parse(response).message
        
        splitMessages(message)

    }


    function splitMessages(message){
        var messages = message.split(";")
        var name_feedback = document.getElementById("name-feedback")
        var email_feedback = document.getElementById("email-feedback")
        var about_feedback = document.getElementById("about-feedback")
        var address_feedback = document.getElementById("address-feedback")
        
        messages.forEach(element => {
            var nameMessage = element.split('-')
            if(nameMessage != null){
              if(nameMessage[0] === "address"){
                address_feedback.textContent = nameMessage[1]
                address_feedback.style.display = "block"
              }else if(nameMessage[0] === "name"){
                name_feedback.textContent = nameMessage[1]
                name_feedback.style.display = "block"
              }else if(nameMessage[0] === "email"){
                email_feedback.textContent = nameMessage[1]
                email_feedback.style.display = "block"
              }else if(nameMessage[0] === "about"){
                about_feedback.textContent = nameMessage[1]
                about_feedback.style.display = "block"
              }
            }else{
                var feedbacks = document.querySelector(".invalid-feedback")
                feedbacks.style.display = "none"
            }
          console.log(element)    
            
        });
    }
})