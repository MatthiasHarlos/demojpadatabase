
import { Navigate, Outlet } from "react-router-dom";
import { useLogInCheck } from "./useHookForLogIn";

export const PrivateRoute = () => {
    const isAuth = useLogInCheck();

        return isAuth ? <Outlet /> : <Navigate to="/login" />;
    
}