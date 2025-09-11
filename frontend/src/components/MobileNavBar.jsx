import LanguageSwitcher from "./UI/LanguageSwitcher";
import { Search, Home, HelpCircle, Headset } from "lucide-react";
import { NavLink } from "react-router-dom";
import { useTranslation } from "react-i18next";

export function MobileBottomNav() {
    const { i18n } = useTranslation()
    const { t } = useTranslation();

    return (
        <nav className="fixed bottom-0 left-0 w-full z-40 md:hidden bg-white border-t border-slate-200 shadow-2xl flex items-center justify-between pt-2 pb-1 px-5">
            <NavLink
                to="/"
                className={({ isActive }) =>
                    `flex flex-col items-center gap-0.5 transition ${isActive
                        ? "text-orange-500 font-bold"
                        : "text-gray-500 hover:text-orange-400"
                    }`
                }
            >
                <Home className="w-7 h-7" />
                <span className="text-[10px]">Home</span>
            </NavLink>

            <NavLink
                to="/faqs"
                className={({ isActive }) =>
                    `flex flex-col items-center gap-0.5 transition ${isActive
                        ? "text-orange-500 font-bold"
                        : "text-gray-500 hover:text-orange-400"
                    }`
                }
            >
                <HelpCircle className="w-7 h-7" />
                <span className="text-[10px]">FAQ</span>
            </NavLink>

            <NavLink
                to="/ristoranti"
                className={({ isActive }) =>
                    `flex flex-col items-center gap-0.5 transition ${isActive
                        ? "text-orange-500 font-bold"
                        : "text-gray-500 hover:text-orange-400"
                    }`
                }
            >
                <Search className="w-9 h-9" />
                <span className="text-[10px]">{t("search_button")}</span>
            </NavLink>

            <NavLink
                to="/assistenza"
                className={({ isActive }) =>
                    `flex flex-col items-center gap-0.5 transition ${isActive
                        ? "text-orange-500 font-bold"
                        : "text-gray-500 hover:text-orange-400"
                    }`
                }
            >
                <Headset className="w-7 h-7" />
                <span className="text-[10px]">{t("assistance")}</span>
            </NavLink>


            <div className="flex flex-col items-center gap-0.5">
                <LanguageSwitcher />
                <span className="text-[10px]">{i18n.language.toUpperCase()}</span>
            </div>
        </nav>
    );
}
