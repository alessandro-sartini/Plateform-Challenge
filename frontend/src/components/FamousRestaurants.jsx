import { useRef, useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useGlobalContext } from "../context/GlobalContext";
const colorClasses = [
    "bg-blue-100 text-blue-700",
    "bg-orange-100 text-orange-600",
    "bg-green-100 text-green-800",
];

export default function FamousRestaurants({ ristoranti = [] }) {
    const { lang } = useGlobalContext();
    const swiperRef = useRef(null);
    const [isAtStart, setIsAtStart] = useState(true);
    const [isAtEnd, setIsAtEnd] = useState(false);
    const { t } = useTranslation();

    const breakpoints = {
        0: { slidesPerView: 2.15, spaceBetween: 10 },
        480: { slidesPerView: 2.15, spaceBetween: 10 },
        640: { slidesPerView: 2.15, spaceBetween: 10 },
        768: { slidesPerView: 4, spaceBetween: 10 },
        940: { slidesPerView: 5, spaceBetween: 10 },
        1024: { slidesPerView: 6, spaceBetween: 10 },
        1280: { slidesPerView: 6, spaceBetween: 10 },
        1440: { slidesPerView: 7, spaceBetween: 10 },
    };

    return (

        <div className="relative">
            <div className="pl-5 md:pr-5">
                <Swiper
                    onSwiper={(swiper) => {
                        swiperRef.current = swiper;
                        setIsAtStart(swiper.isBeginning);
                        setIsAtEnd(swiper.isEnd);
                    }}
                    onSlideChange={(swiper) => {
                        setIsAtStart(swiper.isBeginning);
                        setIsAtEnd(swiper.isEnd);
                    }}
                    breakpoints={breakpoints}
                    ristoranti={ristoranti}
                >

                    {ristoranti.map(({ id, name, primaryImage, categories, itCategories, rating, city, slug, isPartner }) => (

                        <SwiperSlide key={id}>
                            <Link to={`/ristorante/${slug}`}>
                                <div className="flex flex-col rounded-3xl w-full transition-transform transition-shadow duration-100
          active:scale-97 active:shadow-md
          cursor-pointer
          focus:outline-none">
                                    <div className="relative w-full rounded-2xl overflow-hidden aspect-square min-h-[120px] mb-1 ">
                                        {/* Badge "Famoso" */}
                                        {isPartner && (
                                            <div className="absolute top-3 sm:top-5 left-0 z-10">
                                                <span className="flex items-center gap-1 bg-[#FEC33F] text-dark font-semibold px-3 py-1 sm:px-4 sm:py-1.5 rounded-tr-xl rounded-br-xl text-xs sm:text-base md:text-xs shadow">
                                                    {t("suggestions")}
                                                </span>
                                            </div>
                                        )}
                                        <img src={`${primaryImage}`} alt={name} className="w-full h-full object-cover rounded-2xl" />
                                    </div>

                                    <h3 className="font-extrabold text-sm sm:text-lg md:text-[1.0rem] leading-tight text-gray-700 mb-1">{name}</h3>

                                    <div className="flex gap-1 sm:gap-2 mb-1 flex-wrap">

                                        {lang === "en" ?



                                            categories.map((cat, i) => (
                                                <span
                                                    key={cat}
                                                    className={`
                                                ${i > 0 ? "hidden sm:inline" : ""}
                                                px-2 py-1 rounded-xl text-[0.75rem] sm:text-[0.7rem] font-semibold
                                                ${colorClasses[i % colorClasses.length]}
                                                max-w-[80px] sm:max-w-none overflow-hidden text-ellipsis whitespace-nowrap
                                            `}
                                                    title={cat}
                                                >
                                                    {cat}
                                                </span>
                                            ))


                                            :


                                            itCategories.map((cat, i) => (
                                                <span
                                                    key={cat}
                                                    className={`
                                                ${i > 0 ? "hidden sm:inline" : ""}
                                                px-2 py-1 rounded-xl text-[0.75rem] sm:text-[0.7rem] font-semibold
                                                ${colorClasses[i % colorClasses.length]}
                                                max-w-[80px] sm:max-w-none overflow-hidden text-ellipsis whitespace-nowrap
                                            `}
                                                    title={cat}
                                                >
                                                    {cat}
                                                </span>
                                            ))



                                        }


                                    </div>

                                    <div className="flex items-center font-medium gap-1">
                                        <span className="flex items-center text-xs sm:text-xs text-gray-500">
                                            <svg className="w-4 h-4 mr-0.5 text-yellow-400" fill="currentColor" viewBox="0 0 20 20"><path d="M10 15l-5.09 2.68 1-5.81-4.23-4.13 5.85-.85L10 2l2.47 5.03 5.85.85-4.23 4.13 1 5.81z" /></svg>
                                            {rating}
                                        </span>
                                        <span className="flex items-center text-gray-500 text-xs sm:text-xs font-medium">
                                            <svg
                                                className="w-4 h-4 mr-0.5"
                                                viewBox="0 0 24 24"
                                                fill="url(#orange-gradient)"
                                                xmlns="http://www.w3.org/2000/svg"
                                            >
                                                <defs>
                                                    <linearGradient id="orange-gradient" x1="0" y1="0" x2="0" y2="24" gradientUnits="userSpaceOnUse">
                                                        <stop stopColor="#fb923c" />
                                                        <stop offset="1" stopColor="#ea580c" />
                                                    </linearGradient>
                                                </defs>
                                                <path
                                                    d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5a2.5 2.5 0 1 1 0-5 2.5 2.5 0 0 1 0 5z"
                                                    fill="url(#orange-gradient)"
                                                />
                                            </svg>
                                            {city}
                                        </span>
                                    </div>
                                </div>
                            </Link>

                        </SwiperSlide>
                    ))}
                </Swiper>
            </div>


            {/* Freccia SINISTRA */}
            {!isAtStart && (
                <button
                    className="hidden md:block absolute left-0 top-1/2 -translate-y-1/2 z-10 bg-white/80 rounded-full shadow p-2 hover:bg-white transition"
                    onClick={() => swiperRef.current?.slidePrev()}
                    type="button"
                    aria-label="Slide precedente"
                >
                    <svg className="w-6 h-6" fill="none" stroke="currentColor" strokeWidth="3" viewBox="0 0 24 24">
                        <path d="M15 19l-7-7 7-7" />
                    </svg>
                </button>
            )}

            {/* Freccia DESTRA */}
            {!isAtEnd && (
                <button
                    className="hidden md:block absolute right-0 top-1/2 -translate-y-1/2 z-10 bg-white/80 rounded-full shadow p-2 hover:bg-white transition"
                    onClick={() => swiperRef.current?.slideNext()}
                    type="button"
                    aria-label="Slide successiva"
                >
                    <svg className="w-6 h-6" fill="none" stroke="currentColor" strokeWidth="3" viewBox="0 0 24 24">
                        <path d="M9 5l7 7-7 7" />
                    </svg>
                </button>
            )}
        </div>
    );
}
