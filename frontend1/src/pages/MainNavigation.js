import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { useLogInCheck } from '../auth/useHookForLogIn';

function MainNavigation() {
    const [useLoggingText, setLoggingText] = useState("login");

    useEffect(() => { 
            setLoggingText(useLogInCheck);
            console.log(useLoggingText);
    }, [useLoggingText]);

    return <header >
        <div >hello</div>
        <nav>
            <ul>
            <li><Link to='/'>Home</Link></li>
            <li><Link to={{pathname: '/' + useLogInCheck ? "logout" : "login"}}>{useLoggingText ? "logout" : "login"}</Link></li>
            </ul>
        </nav>
    </header>
}

export default MainNavigation;