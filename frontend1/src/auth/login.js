import { useState } from "react";
import { Navigate } from "react-router-dom";
import { useLogInCheck } from "./useHookForLogIn";


export function Login() {

const [isAuth, setAuth] = useState(useLogInCheck());

const handleClick = () => {
    localStorage.setItem("token", !isAuth);
    setAuth(true);
    
};


if (!isAuth) {
    return <button onClick={handleClick}>Login</button>
}
    return <Navigate to="/" />

}