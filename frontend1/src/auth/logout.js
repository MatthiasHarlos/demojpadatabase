
import { useState } from "react";
import { useLogInCheck } from "./useHookForLogIn";
import { Navigate } from "react-router-dom";

export function Logout() {

    const [isAuth, setAuth] = useState(useLogInCheck());

    localStorage.setItem("token", null);

    return <Navigate to="/" />;
   
}