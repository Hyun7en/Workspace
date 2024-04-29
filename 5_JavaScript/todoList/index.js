
//할일을 ul태그에 넣어주기
function addTodo(){
    //input요소 가져오기
    const searchInput = document.querySelector("#search-bar input");
    //ul요소 가져오기
    const todoUl = document.querySelector(".todo-list");

    todoUl.innerHTML += `<li>${searchInput.value}<div><i class="fa-solid fa-xmark"></i></div></li>`
}