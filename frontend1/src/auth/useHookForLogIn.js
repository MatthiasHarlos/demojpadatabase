import { useState, useEffect } from "react";

export function useLogInCheck() {
   /* const [isLoggedIn, setLoggedIn] = useState(false);

    useEffect(() => {
        if(localStorage.getItem("token") === true) {
            alert("token ist nicht null");
            setLoggedIn(localStorage.getItem("token"));
        }
    }, [isLoggedIn]);
    
    return isLoggedIn; */

    if (localStorage.getItem("token") !== "true") {
        return false;
    }

    return true;
}