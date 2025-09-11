import { Outlet } from 'react-router-dom';

import NavBar from '../components/NavBar.jsx';
import { MobileBottomNav } from '../components/MobileNavBar.jsx';
import { useState } from "react";

export default function DefaultLayout() {
    const [lang, setLang] = useState("IT");
    return (
        <>
            <NavBar lang={lang} setLang={setLang} />
            <div className="bg-gradient-to-b from-blue-100 to-blue-50 pb-[68px] md:pb-0">
                <div className="">
                    <Outlet />
                </div>
            </div>
            <MobileBottomNav lang={lang} setLang={setLang} />

        </>
    )
}