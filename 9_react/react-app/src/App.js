import logo from './logo.svg';
import './App.css';
import Menu from './components/Menu';
import Comment from './components/Comment';
import CommentList from './classComponent/CommentList';


function App() {
  return (
    <div className="App">
      <CommentList />
    </div>
  );
}

export default App;
