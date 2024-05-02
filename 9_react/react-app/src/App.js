import logo from './logo.svg';
import './App.css';
import Menu from './components/Menu';
import Comment from './components/Comment';
import CommentList from './classComponent/CommentList';
import UseStateTest from './reactHook/UseStateTest';
import SignUp from './sample/SignUp';
import LandingPage from './sample/LandingPage';
import UseEffectTest from './reactHook/UseEffectTest';
import UseMemoTest from './reactHook/useMemo/useMemoTest';
import UseCallbackTest2 from './reactHook/useCallback/UseCallbackTest2';
import UseRefTest from './reactHook/UseRefTest';


function App() {
  return (
    <div className="App">
      <UseRefTest/>
    </div>
  );
}

export default App;
