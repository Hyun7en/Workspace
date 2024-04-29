let todoList = [];

//할일을 todoList에 넣어주기
function addTodo(){
    //input요소 가져오기
    const searchInput = document.querySelector("#search-bar input");
    todoList.push({
        title: searchInput.value,
        date : new Date().getTime()
    });
    drawTodoList();
}

//할일목록을 ui에 그려주기
function drawTodoList(){
    const removeTodo = function(removeTodo){
         //todoList에서 removeTodo와같은 데이터를 삭제
        todoList = todoList.filter(t => (removeTodo.date !== t.date && removeTodo.title !== t.title)) //반환값이 true인 데이터만 남기고 전부 삭제된 배열을 반환
        //todoList에서 todo와같은 데이터를 삭제
        drawTodoList();
    }
    //ul요소 가져오기
    const todoUl = document.querySelector(".todo-list");
    todoUl.innerHTML = "";

    for(let todo of todoList) {
        // todoUl.innerHTML += `<li>${todo.title}
        //      <div class="todo-remove-btn"><i class="fa-solid fa-xmark"></i></div></li>`
        
        const todoLi = document.createElement('li'); // <li></li>
        todoLi.innerHTML = todo.title;// <li>${todo.title}</li>
        todoUl.appendChild(todoLi);// todoUl.innerHTML = <li>${todo.title}</li>;
        
        const removeBtn = document.createElement('div');
        removeBtn.className = 'todo-remove-btn';
        removeBtn.innerHTML = '<i class="fa-solid fa-xmark"></i>';
        todoLi.appendChild(removeBtn);

        removeBtn.onclick = function(ev){
            //todoList데이터지우기
            removeTodo(todo)
        }

    }
}