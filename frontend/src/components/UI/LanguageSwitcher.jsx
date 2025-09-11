import { useTranslation } from "react-i18next";

const FLAGS = {
    it: "/bandiere/italia.png",
    en: "/bandiere/usa.png",
};


export default function LanguageSwitcher() {

    const { i18n } = useTranslation();
    const lang = i18n.language;

    const handleToggle = () => {
        const newLang = lang === "it" ? "en" : "it";
        i18n.changeLanguage(newLang);
        localStorage.setItem("lang", newLang); // <-- salva la lingua
    };

    return (
        <button
            type="button"
            aria-label="Cambia lingua"
            className="rounded-full border border-gray-200 shadow-sm w-7 h-7 flex items-center justify-center bg-white focus:outline-none active:scale-95 transition"
            onClick={handleToggle}
        >
            <img
                src={FLAGS[lang]}
                alt={lang === "it" ? "Italiano" : "English"}
                className="rounded-full object-cover w-7 h-7"
                style={{ boxShadow: "0 1px 4px 0 #0001" }}
            />
        </button>
        
    );
}
