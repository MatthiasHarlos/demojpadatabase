import { Link } from 'react-router-dom';
import { useLogInCheck } from '../auth/useHookForLogIn';

function MainNavigation() {
    const loggingText = useLogInCheck ? "logout" : "login";
    return <header >
        <div >hello</div>
        <nav>
            <ul>
            <li><Link to='/'>Home</Link></li>
            <li><Link to={{pathname: '/' + useLogInCheck ? "logout" : "login"}}>{loggingText}</Link></li>
            </ul>
        </nav>
    </header>
}

export default MainNavigation;