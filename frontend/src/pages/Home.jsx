import SearchBar from "../components/SearchBar"
import FamousRestaurants from "../components/FamousRestaurants"
import ReviewsSection from "../components/ReviewsSection"
import ScopriPerCucina from "../components/ScopriPerCucina"
import RegionCarousel from "../components/RegionCarousel"
import BadgeCategorie from '../components/BadgeResturant';
import "react-datepicker/dist/react-datepicker.css";

import { useGlobalContext } from "../context/GlobalContext"
import { useEffect } from "react";
import GeoLocation from "../components/GeoLocation"

//Traduzione
import { useTranslation } from "react-i18next";

export default function Home() {

    const { t } = useTranslation();
    const { cityRestaurants, fetchRestaurantsByCity, handleMost,
        mostVisited } = useGlobalContext();

    useEffect(() => {
        fetchRestaurantsByCity();
        handleMost();
    }, []);


    return (
        <>
            <div className="flex flex-col items-center justify-center pt-15">
                {/* --- HEADLINE + ICONS --- */}
                <div className="relative flex flex-col items-center mb-4 w-fit">

                    {/* Icona pesce */}
                    <span className="hidden md:block absolute -top-8 -left-12 floaty">
                        <svg className="w-8 h-8 text-blue-400 drop-shadow-md" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24">
                            <path d="M4 12c0 4 4 6 8 6s8-2 8-6-4-6-8-6-8 2-8 6z" />
                            <circle cx="12" cy="12" r="2" />
                        </svg>
                    </span>
                    {/* Icona vegano/foglia */}
                    <span className="hidden md:block absolute -top-10 left-1/2 -translate-x-1/2 floaty">
                        <svg className="w-8 h-8 text-green-500 drop-shadow" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24">
                            <path d="M12 2C8 2 4 6 4 10c0 5 5 8 8 12 3-4 8-7 8-12 0-4-4-8-8-8z" />
                        </svg>
                    </span>
                    {/* Icona carne */}
                    <span className="hidden md:block absolute -top-8 -right-12 floaty">
                        <svg className="w-8 h-8 text-red-500 drop-shadow-sm" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24">
                            {/* Icona bistecca stilizzata */}
                            <ellipse cx="12" cy="12" rx="8" ry="5" />
                            <circle cx="15" cy="12" r="2" />
                        </svg>
                    </span>
                    <h1 className="text-4xl md:text-5xl font-extrabold text-gray-900 text-center relative z-10">
                        {t("h1")}
                    </h1>
                </div>

                {/* --- Sottotitolo --- */}
                <p className="text-lg md:text-xl text-gray-500 text-center max-w-2xl mb-8">
                    {t("p")}
                </p>
                {/* --- Badge Categorie --- */}
                {/* <BadgeCategorie /> */}
                {/* --- Search Bar --- */}
                <SearchBar />
            </div>
            {/* <GeoLocation /> */}
            <RegionCarousel />
            {/* <RegionGrid /> */}

            <section className="max-w-screen-2xl mx-auto mt-10">
                <h2 className="text-xl sm:text-xl md:text-2xl font-bold text-gray-900 mb-3 lm:text-center sm:text-left pl-5">
                    {t("most_visited")}
                </h2>
                <FamousRestaurants ristoranti={mostVisited} />
            </section>
            <section className="max-w-screen-2xl mx-auto mt-10">
                <h2 className="text-xl sm:text-xl md:text-2xl font-bold text-gray-900 mb-3 lm:text-center sm:text-left pl-5">
                    {t("restaurants_in_milano")}
                </h2>
                <FamousRestaurants ristoranti={cityRestaurants.milano} />
            </section>
            <section className="max-w-screen-2xl mx-auto mt-10">
                <h2 className="text-xl sm:text-xl md:text-2xl font-bold text-gray-900 mb-3 lm:text-center sm:text-left pl-5">
                    {t("restaurants_in_roma")}
                </h2>
                <FamousRestaurants ristoranti={cityRestaurants.roma} />
            </section>
            <section className="max-w-screen-2xl mx-auto mt-10 pb-5">
                <h2 className="text-xl sm:text-xl md:text-2xl font-bold text-gray-900 mb-3 lm:text-center sm:text-left pl-5">
                    {t("restaurants_in_napoli")}
                </h2>
                <FamousRestaurants ristoranti={cityRestaurants.napoli} />
            </section>
            {/* <div className="max-w-screen-2xl items-stretch mx-auto mt-10">
                <ScopriPerCucina />
            </div> */}
        </>


    )
}