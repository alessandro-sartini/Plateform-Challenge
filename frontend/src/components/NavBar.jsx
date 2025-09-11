import { Home, HelpCircle, Headset, Globe } from "lucide-react";
import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useGlobalContext } from "../context/GlobalContext";

export default function Navbar() {
    const { t } = useTranslation();
    const { i18n } = useTranslation();
    const { setLang } = useGlobalContext();

    const handleLanguageChange = (e) => {
        const newLang = e.target.value;
        setLang(newLang);
        i18n.changeLanguage(newLang);
        localStorage.setItem("lang", newLang);
    }
    
    return (
        <>
            {/* TOP NAV */}
            <nav className="bg-white/90 backdrop-blur shadow-sm py-2 top-0 left-0 border-b border-slate-100 hidden md:flex">
                <div className="max-w-screen-2xl mx-auto flex items-center justify-between w-full px-5">
                    {/* LOGO */}
                    <Link to={"/"}>

                        <div className="flex items-center">
                            <span className="font-bold text-2xl text-gray-900 tracking-tight">
                                RestFinder.it
                            </span>
                        </div>

                    </Link>
                    {/* DESKTOP MENU */}
                    <div className="hidden md:flex items-center gap-4">
                        <Link to={"/"} className="font-semibold text-gray-800 hover:text-orange-500 px-3 py-2 rounded-xl transition flex items-center gap-2">
                            <Home className="w-5 h-5" /> Home
                        </Link>
                        <Link to={"/faqs"} className="font-semibold text-gray-800 hover:text-orange-500 px-3 py-2 rounded-xl transition flex items-center gap-2">
                            <HelpCircle className="w-5 h-5" /> FAQ
                        </Link>
                        <Link to={"/assistenza"} className="font-semibold text-gray-800 hover:text-orange-500 px-3 py-2 rounded-xl transition flex items-center gap-2">
                            <Headset className="w-5 h-5" /> {t("assistance")}
                        </Link>
                        {/* LANGUAGE SELECTOR */}
                        <div className="flex items-center gap-1 px-3 py-2 rounded-xl hover:bg-gray-50 transition cursor-pointer">
                            <Globe className="w-5 h-5 text-gray-500" />
                            <select
                                value={i18n.language}
                                onChange={handleLanguageChange}
                                className="bg-transparent border-0 focus:ring-0 text-gray-800 font-bold"
                            >
                                <option value="it">IT</option>
                                <option value="en">EN</option>
                            </select>
                        </div>
                    </div>
                </div>
            </nav>
        </>
    );
}
