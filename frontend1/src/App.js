import './App.css';
import { Home } from './pages/home';
import { Login } from './auth/login';
import { Route, Routes, BrowserRouter } from 'react-router-dom';
import MainNavigation from './pages/MainNavigation.js';
import { PrivateRoute } from './auth/PrivateRoute';
import { history } from './pages/history';
import { Logout } from './auth/logout';

function App() {
  return (
  <BrowserRouter location={history.location} navigator={history}>
    <MainNavigation />
    <Routes>
      <Route exact path='/' element={<PrivateRoute/>} >
        <Route exact path='/' element={<Home />}></Route>
      </Route>
      <Route exact path='/logout' element={<PrivateRoute/>} >
        <Route exact path='/logout' element={<Logout />}></Route>
      </Route>
      <Route exact path='/login' element={<Login/>} />
      
    </Routes>
  </BrowserRouter>
  );
}

export default App;
