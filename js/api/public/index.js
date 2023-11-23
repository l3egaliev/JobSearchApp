
var inpValue


function indexSearch(){
  var form = document.getElementById("form")
  var input = form.querySelector('#search')
  
  input.addEventListener("input", function(){
    inpValue = input.value
  })

  } 




  document.addEventListener('DOMContentLoaded', function(){
      indexSearch()
      Log()
  })



document.getElementById("search-btn").addEventListener('click', function(event){
  event.preventDefault()
  window.location.href = "vacancies.html?search="+inpValue
  
})


