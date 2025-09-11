import { useNavigate } from "react-router-dom";
import { useGlobalContext } from "../context/GlobalContext";
import { useTranslation } from "react-i18next";

const gradientMap = {
    Veneto: "from-blue-400/100 via-blue-400/90",
    Lombardia: "from-red-500/90 via-red-400/90",
    "Valle d'Aosta": "from-green-600/100 via-green-600/90 ",
    Piemonte: "from-orange-400/90 via-orange-400/90 ",
    Trentino: "from-green-600/90 via-green-600/90 ",
    Liguria: "from-orange-400/90 via-orange-400/90 ",
    Friuli: "from-red-500/90 via-red-400/90 ",
    "Emilia-Romagna": "from-blue-400/100 via-blue-400/90",
    Toscana: "from-green-600/90 via-green-600/90",
    Umbria: "from-orange-400/90 via-orange-400/90",
    Marche: "from-red-500/90 via-red-400/90",
    Lazio: "from-blue-400/100 via-blue-400/90",
    Abruzzo: "from-green-600/90 via-green-600/90",
    Molise: "from-orange-400/90 via-orange-400/90",
    Campania: "from-red-500/90 via-red-400/90",
    Puglia: "from-blue-400/100 via-blue-400/90",
    Basilicata: "from-green-600/90 via-green-600/90",
    Calabria: "from-orange-400/90 via-orange-400/90",
    Sicilia: "from-red-500/90 via-red-400/90",
    Sardegna: "from-blue-400/100 via-blue-400/90",

};


export default function RegionCard({ name, img, icon, city }) {

    const navigate = useNavigate();
    const { updateMapCenter, handleSearch } = useGlobalContext();
    const { t } = useTranslation();
    const gradient = gradientMap[name] || "from-gray-400/80 via-gray-200/40 to-transparent";
    const onSubmit = async (city) => {
        updateMapCenter(city.coordinate);
        await handleSearch(city.nome);
        navigate(`/ristoranti?search=${encodeURIComponent(city.nome)}`);
    };

    return (
        <div className="relative w-full rounded-3xl overflow-hidden group cursor-pointer shadow-md aspect-square md:aspect-auto md:h-80">
            <img src={img} alt={name} className="absolute inset-0 w-full h-full object-cover" />
            {/* Top gradient */}
            <div className={`absolute top-0 left-0 w-full h-[30%] bg-gradient-to-b ${gradient} to-transparent pointer-events-none`} />

            {/* Bottom gradient */}
            <div className={`absolute bottom-0 left-0 w-full h-[40%] bg-gradient-to-t ${gradient} to-transparent pointer-events-none`} />
            {/* Badge icona regione */}
            <div className="absolute top-3  left-3  bg-white/30 rounded-2xl p-2 shadow flex items-center justify-center">
                <img
                    src={icon}
                    alt={name + " icona"}
                    className="w-6 h-6 object-contain"
                />
            </div>

            {/* Nome Regione */}
            <div className="absolute right-0 bottom-10 sm:bottom-14 md:bottom-16 w-full flex flex-col items-center ">
                <span className="text-l sm:text-2xl md:text-2xl font-bold text-white drop-shadow-md">{name}</span>
            </div>

            {/* Button */}
            <div className="absolute left-1/2 -translate-x-1/2 bottom-2 sm:bottom-3 md:bottom-5">
                <button
                    onClick={() => onSubmit(city)}
                    type="button"
                    className="bg-[#FFB626] text-white font-semibold text-[10px] sm:text-[15px] rounded-full px-2 sm:px-3 md:px-4 py-2 shadow-lg transition-transform duration-150 hover:scale-105 active:scale-95 focus:outline-none focus:ring-2 focus:ring-yellow-300 cursor-pointer"
                >
                    {t("discover")}
                </button>
            </div>
        </div>
    );
}