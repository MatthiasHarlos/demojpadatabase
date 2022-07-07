import { Link } from 'react-router-dom';

import classes from './MainNavigation.module.css';

function MainNavigation() {
    return <header className={classes.header}>
        <div className={classes.logo}>Attendances to Look</div>
        <nav>
            <ul>
            <li><Link to='/'>Startpage</Link></li>
            <li><Link to='/attendances'>Attendances</Link></li>
            <li><Link to='/newAttendances'>NewAttendances</Link></li>
            </ul>
        </nav>
    </header>
}

export default MainNavigation;