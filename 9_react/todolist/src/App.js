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

  return (
    <AppContainer title={"TodoList"}>
      <TodoInsert/>
      <TodoListView
        todos={todos}
      />
    </AppContainer>
  );
}

export default App;
