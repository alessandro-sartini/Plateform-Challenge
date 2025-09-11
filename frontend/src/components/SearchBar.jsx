import { useEffect, useState } from 'react';
import ReactDatePicker from "react-datepicker";
import { useGlobalContext } from "../context/GlobalContext"
import { useNavigate } from 'react-router-dom';
import { useTranslation } from "react-i18next";
import { useLocation } from "react-router-dom"
import { FaSearch } from "react-icons/fa";
import { motion, AnimatePresence } from "framer-motion";


const suggestions = [
    {
        icon: (
            <svg className="w-7 h-7 text-blue-400" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path d="M12 19l7-7-7-7" /></svg>
        ),
        title: "Nelle vicinanze",
        desc: "Scopri le opzioni intorno a te"
    },
    {
        icon: (
            <svg className="w-7 h-7 text-blue-400" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path d="M6 18L18 6M6 6l12 12" /></svg>
        ),
        title: "Parigi, Francia",
        desc: "Per attrazioni come 'Torre Eiffel'"
    },
    {
        type: "city",
        icon: <span className="w-7 h-7 bg-yellow-100 rounded-xl flex items-center justify-center">🏙️</span>,
        title: "Torino, Piemonte",
        desc: "Ideale per un weekend fuori porta",
    },
    {
        type: "restaurant",
        icon: <span className="w-7 h-7 bg-green-100 rounded-xl flex items-center justify-center">🍽️</span>,
        title: "La Pergola",
        desc: "Ristorante 3 stelle Michelin a Roma",
        city: "Roma",
    },
    {
        type: "restaurant",
        icon: <span className="w-7 h-7 bg-red-100 rounded-xl flex items-center justify-center">🍣</span>,
        title: "Sushi B",
        desc: "Sushi raffinato a Milano",
        city: "Milano",
    },
    {
        type: "city",
        icon: <span className="w-7 h-7 bg-pink-100 rounded-xl flex items-center justify-center">🏰</span>,
        title: "Barcellona, Spagna",
        desc: "Famosa meta balneare",
    },
];





function SearchModal({ open, onClose }) {
    const [query, setQuery] = useState("");
    const navigate = useNavigate();
    const { handleSearch } = useGlobalContext();
    const { t } = useTranslation();

    const handleGeoLocation = () => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    const coords = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };
                    localStorage.setItem("userLocation", JSON.stringify(coords));
                    // Avvia subito la ricerca con le coordinate
                    handleSearch(`${coords.lat},${coords.lng}`);
                },
                (error) => {
                    alert("Impossibile ottenere la posizione.");
                }
            );
        } else {
            alert("Geolocalizzazione non supportata dal browser.");
        }
    };

    const onSubmit = (e) => {
        e.preventDefault();
        handleSearch(query);
        if (query) {
            navigate(`/ristoranti?search=${encodeURIComponent(query)}`);
            onClose();
        }
        onClose();
    };
    if (!open) return null;

    return (
        <div className="fixed inset-0 z-50 rounded-lg flex items-center justify-center bg-black/40 px-2">
            <div className="bg-white w-full max-w-none mx-0 rounded-2xl shadow-2xl relative p-5">
                {/* X button */}
                <button
                    onClick={onClose}
                    className="absolute top-4 right-4 text-gray-400 hover:text-gray-600 text-2xl font-bold"
                >
                    &times;
                </button>
                {/* Titolo */}
                <h2 className="text-2xl font-bold mb-5 text-gray-900">{t("where")}</h2>
                {/* Campo di ricerca */}
                <div className="flex items-center mb-4 border border-gray-300 rounded-lg px-3 py-2">
                    <FaSearch className="text-gray-400 mr-2" />
                    <input
                        className="flex-1 bg-transparent border-none focus:outline-none text-gray-800 placeholder-gray-400 text-base"
                        placeholder="Nelle vicinanze"
                        value={query}
                        onChange={e => setQuery(e.target.value)}
                    />
                </div>
                {/* Sottotitolo */}
                <div className="text-xs text-gray-400 mb-3 font-semibold uppercase">{t("destinations_suggested")}</div>
                {/* Lista suggerimenti */}
                <div className="flex flex-col gap-2 mb-6">
                    {suggestions
                        .filter(s => !query || s.title.toLowerCase().includes(query.toLowerCase()))
                        .map((s, i) => (
                            <div key={i} className="flex items-center gap-3 p-2 rounded-xl hover:bg-gray-100 cursor-pointer transition"
                                onClick={s.title === "Nelle vicinanze" ? handleGeoLocation : undefined} >
                                {s.icon}
                                <div>
                                    <div className="font-semibold text-gray-900 text-[15px]">{s.title}</div>
                                    <div className="text-xs text-gray-500">{s.desc}</div>
                                </div>
                            </div>
                        ))}
                </div>
                {/* Pulsanti */}
                <div className="flex justify-end items-center mt-1">
                    <button className="flex items-center gap-2 bg-gradient-to-r from-orange-400 to-orange-600 text-white rounded-full px-6 py-3 text-base font-bold shadow-md transition"
                        onClick={onSubmit}>
                        <FaSearch className="text-white" /> Cerca
                    </button>
                </div>
            </div>
        </div>
    );
}

export default function SearchBar() {
    const navigate = useNavigate();
    const { t } = useTranslation();
    const routerLocation = useLocation();
    const [activeField, setActiveField] = useState(null);
    const [modalOpen, setModalOpen] = useState(false);
    const { location, selectedCategory, setLocation, priceCategory } = useGlobalContext();

    const onSubmit = async (e) => {
        e.preventDefault();
        setActiveField(null);
        const params = new URLSearchParams();
        if (location) params.append("search", location);
        if (selectedCategory) params.append("category", selectedCategory);
        if (priceCategory) params.append("priceCategory", priceCategory);

        navigate(`/ristoranti?${params.toString()}`);
    };

    useEffect(() => {
        if (routerLocation.pathname !== "/ristoranti") {
            setLocation(""); // svuota la ricerca solo se NON sei nella pagina risultati

        }
        if (routerLocation.pathname == "/ristoranti") {
            setActiveField(null); // chiudi il campo attivo
        }
    }, [routerLocation.pathname]);


    return (
        <>
            {/* MOBILE SEARCH BAR */}
            <div className="block md:hidden w-full px-5 cursor-pointer">
                <button
                    type="button"
                    className="w-full flex items-center justify-center bg-white rounded-full shadow-xl/10 py-4 text-gray-700 font-medium text-base focus:outline-none"
                    onClick={() => setModalOpen(true)}
                >
                    <svg className="w-5 h-5 text-gray-400" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24">
                        <circle cx="11" cy="11" r="8" />
                        <path d="M21 21l-3.5-3.5" />
                    </svg>
                    {t("start_search")}
                </button>
                <SearchModal open={modalOpen} onClose={() => setModalOpen(false)} />
            </div>


            {/* DESKTOP/TABLET SEARCH BAR */}
            <div className="">
                <form
                    className="hidden md:flex w-full flex-row rounded-full shadow-lg items-stretch relative"
                    onSubmit={onSubmit}
                >
                    {/* LOCATION */}

                    <div className="relative flex-1 min-w-[500px]">
                        <div className="flex items-center w-full bg-white rounded-l-4xl px-4 py-3 md:py-2 transition">
                            <div className="flex flex-col items-start ml-3 py-2 w-full">
                                <span className="text-sm font-bold text-gray-600 mb-1">{t("input")}</span>
                                <div className="flex items-center w-full">
                                    <input
                                        autoFocus
                                        type="text"
                                        placeholder={t("search_placeholder")}
                                        value={location}
                                        onChange={e => setLocation(e.target.value)}
                                        className="text-xs w-full py-1 border-none bg-transparent focus:outline-none"
                                        onFocus={() => setActiveField("location")}
                                    />
                                </div>
                            </div>
                        </div>
                        <AnimatePresence>
                            {activeField === "location" && location && (
                                <>
                                    <div
                                        className="fixed inset-0 z-40"
                                        onClick={() => setActiveField(null)}
                                        tabIndex={-1}
                                    />
                                    <motion.div
                                        key="airbnb-popover"
                                        initial={{ opacity: 0, scale: 0.92, y: 20 }}
                                        animate={{ opacity: 1, scale: 1, y: 0 }}
                                        exit={{ opacity: 0, scale: 0.97, y: 10 }}
                                        transition={{ duration: 0.23, ease: [0.21, 1, 0.34, 1] }} // Curva simile a Airbnb
                                        className="absolute left-0 top-full mt-2 w-155 bg-white rounded-2xl shadow-2xl border border-gray-100 p-4 z-50"
                                    >
                                        <div className="mb-2 text-gray-700 font-semibold">
                                            {`${t("searching_for")}: ${location}`}
                                        </div>
                                        <div>
                                            {suggestions
                                                .filter(s => s.title.toLowerCase().includes(location.toLowerCase()))
                                                .map((s) => (
                                                    <button
                                                        type="button"
                                                        key={s}
                                                        onClick={() => { setLocation(s.title); setActiveField(null); }}
                                                        className="w-full text-left py-2 px-2 hover:bg-gray-100 rounded-lg"
                                                    >
                                                        <span className="mr-2">{s.icon}</span>
                                                        <span className="font-semibold">{s.title}</span>
                                                        <span className="block text-xs text-gray-500">{s.desc}</span>
                                                    </button>
                                                ))}
                                        </div>
                                    </motion.div>
                                </>
                            )}
                        </AnimatePresence>
                    </div>

                    {/* BUTTON SUBMIT */}
                    <button
                        type="submit"
                        className="text-xl rounded-r-4xl bg-gradient-to-r from-orange-400 to-orange-600 font-semibold text-white px-8 w-full cursor-pointer"
                    >
                        {t("search_button")}
                    </button>
                </form>

                {/* Overlay per chiudere il popover cliccando fuori */}
                {activeField && (
                    <div
                        className="fixed inset-0 z-40"
                        onClick={() => setActiveField(null)}
                        tabIndex={-1}
                    />
                )}
            </div>
        </>
    );
}
