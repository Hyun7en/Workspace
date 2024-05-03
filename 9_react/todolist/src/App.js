import logo from './logo.svg';
import './App.css';
import AppContainer from './components/AppContainer';
import TodoInsert from './components/TodoInsert';
import { useState } from 'react';
import TodoListView from './components/TodoListView';

const dummyTodos = [{
  id: new Date().getTime() + 1, //key대체
  title: "예습하기",
  isDone: true
},{
  id: new Date().getTime() + 2, //key대체
  title: "학습하기",
  isDone: true
},{
  id: new Date().getTime() + 3, //key대체
  title: "복습하기",
  isDone: false
}]

function App() {
  const [todos, setTodos] = useState([...dummyTodos]);
  // todos => 데이터를 추가해주자 -> 
  // setTodos -> TodoInsert내부에 input요소의 value가 필요하다.

  const onInsert = (title) => {
    const todo = {
      id: new Date().getTime(), //key대체
      title: title,
      isDone: false
    }
   
    setTodos(todos => todos.concat(todo))
    //concat() : 인자로 주어진 배열이나 값들을 기존 배열에 합쳐서 새 배열을 반환
  }

  return (
    <AppContainer title={"TodoList"}>
      <TodoInsert onInsert={onInsert}/>
      <TodoListView
        todos={todos}
      />
    </AppContainer>
  );
}

export default App;
