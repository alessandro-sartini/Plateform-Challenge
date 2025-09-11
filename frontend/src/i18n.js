import i18n from "i18next"
import { initReactI18next } from "react-i18next";
import translationEN from "./locales/en/translationEN.json";
import translationIT from "./locales/it/translationIT.json";

const savedLang = localStorage.getItem("lang") || "it";

i18n
    .use(initReactI18next) // Passa i18n a react-i18next
    .init({
        resources: {
            en: { translation: translationEN },
            it: { translation: translationIT }
        },
        lng: savedLang, //  <-- usa la lingua salvata!
        fallbackLng: "it", // Lingua di fallback
        interpolation: { escapeValue: false }
    });

export default i18n;