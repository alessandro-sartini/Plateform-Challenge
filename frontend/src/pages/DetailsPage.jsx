import { Star, MapPin, Phone, Clock, Users } from "lucide-react";
import { useGlobalContext } from "../context/GlobalContext";
import { useParams } from "react-router-dom";
import { useEffect, useMemo } from "react";
import GoogleMpaSingle from "../components/GoogleMpaSingle";
import { Swiper, SwiperSlide } from "swiper/react";
import { useTranslation } from "react-i18next";
import FamousRestaurants from "../components/FamousRestaurants";
import "swiper/css";

export default function DetailsPage() {
    const { slug } = useParams();
    const { singleRestaurant, fechSigleRestaurant, lang, mostVisited, handleMost } = useGlobalContext();
    const { t } = useTranslation();


    useEffect(() => {
        fechSigleRestaurant(slug);
        handleMost();
    }, [slug]);

    const {
        name, imageUrls, rating, address, city, postalcode, province,
        country, latitude, longitude, placeId, menuLink, priceCategory,
        reviewCount, mapUrl, websiteUrl, isPartner, categories
    } = singleRestaurant;

    const markers = useMemo(() => latitude && longitude ? [{
        id: singleRestaurant.id,
        name,
        latitude,
        longitude,
        address,
        city,
        slug,
        primaryImage: imageUrls && imageUrls[0],
    }] : [], [latitude, longitude, name, address, city, slug, imageUrls, singleRestaurant.id]);

    const center = { lat: latitude, lng: longitude };

    if (!name || latitude === undefined || longitude === undefined) {
        return <div className="text-center py-10">Caricamento...</div>;
    }

    return (
        <section className="w-full max-w-4xl mx-auto pt-5 px-5 md:px-5">
            <div className="relative rounded-3xl overflow-hidden shadow-lg mb-8 bg-gray-200 flex items-center justify-center">
                <img src={imageUrls ? imageUrls[0] : null} className="w-full h-full object-cover" alt={name} />
                <div className="absolute inset-0 bg-gradient-to-t from-black/60 to-transparent" />
                <div className="absolute bottom-0 left-0 w-full px-4 sm:px-6 md:px-8 py-6 flex flex-col gap-3 md:flex-row items-start md:items-end justify-between z-10">
                    <div>
                        {name && (
                            <h1 className="text-2xl sm:text-3xl md:text-4xl font-extrabold text-white mb-2 drop-shadow">
                                {name}
                            </h1>
                        )}
                        <div className="flex items-center gap-2 mb-1 flex-wrap">
                            <Star className="w-5 h-5 text-yellow-400" fill="#fbbf24" />
                            <span className="text-white text-lg font-semibold">{rating}</span>
                            <span className="text-gray-200 text-sm font-normal ml-2">
                                {reviewCount} recensioni
                            </span>
                            {priceCategory && (
                                <span className="ml-4 text-orange-300 font-bold">{priceCategory}</span>
                            )}
                        </div>
                        <div className="flex gap-2 flex-wrap mb-2">
                            {categories?.map((cat) => (
                                <span
                                    key={cat}
                                    className="bg-blue-100 text-blue-700 px-3 py-1 rounded-xl font-semibold text-sm"
                                >
                                    {cat}
                                </span>
                            ))}
                        </div>
                        <div className="flex items-center gap-2 text-white flex-wrap">
                            <MapPin className="w-5 h-5 text-orange-500" />
                            <span className="font-medium bg-white/20 px-3 py-1 rounded-xl text-white text-sm">
                                {address}
                            </span>
                            <span className="font-medium bg-white/20 px-3 py-1 rounded-xl text-white text-sm">
                                {city}
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <div className="flex flex-col md:flex-row gap-8 mb-8 md:justify-end">
                <div className="flex flex-col justify-end items-start md:items-center">
                    <button className="w-full md:w-auto bg-gradient-to-r from-orange-400 to-orange-600 transition text-white font-extrabold rounded-2xl px-8 py-3 shadow-md text-lg cursor-pointer">
                        {t("book_table")}
                    </button>
                    <span className="mt-2 text-gray-500 text-sm flex items-center gap-1">
                        <Users className="w-4 h-4" /> {t("availability_updated")}
                    </span>
                </div>
            </div>

            <div className="mb-8">
                <h2 className="text-xl sm:text-2xl font-bold text-gray-900 mb-2 sm:mb-4">{t("where_is")}</h2>
                <div className="rounded-3xl overflow-hidden shadow-md border border-gray-200">
                    <GoogleMpaSingle
                        markers={markers}
                        center={center}
                        zoom={15}
                    />
                </div>
            </div>




            {imageUrls?.length > 0 && (
                <div className="mb-8">
                    <h2 className="text-xl sm:text-2xl font-bold text-gray-900 mb-2 sm:mb-4">{t("gallery")}</h2>
                    <Swiper spaceBetween={10} slidesPerView={1.2} breakpoints={{
                        640: { slidesPerView: 2 },
                        768: { slidesPerView: 3 },
                        1024: { slidesPerView: 4 },
                    }}>
                        {imageUrls.map((img, idx) => (
                            <SwiperSlide key={idx}>
                                <img
                                    src={img}
                                    alt={`Gallery ${idx + 1}`}
                                    className="rounded-xl w-full h-40 sm:h-48 md:h-40 object-cover"
                                />
                            </SwiperSlide>
                        ))}
                    </Swiper>
                </div>
            )}

            <div className="mb-10 -mx-4 sm:-mx-6 md:-mx-8">
                <h2 className="px-4 sm:px-6 md:px-8 text-xl sm:text-2xl font-bold text-gray-900 mb-4">{t("restaurant_suggestions")}</h2>
                <FamousRestaurants ristoranti={mostVisited} />
            </div>

            <div className="flex gap-3 mt-4 flex-wrap">
                {/* {websiteUrl && (
                    <a
                        href={websiteUrl}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="inline-flex items-center gap-2 bg-blue-100 text-blue-700 hover:bg-blue-200 font-semibold rounded-xl px-4 py-2 text-sm sm:text-base transition"
                    >
                        <MapPin className="w-4 h-4" />
                        Sito web
                    </a>
                )} */}
                {menuLink && (
                    <a
                        href={menuLink}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="inline-flex items-center gap-2 bg-green-100 text-green-700 hover:bg-green-200 font-semibold rounded-xl px-4 py-2 text-sm sm:text-base transition"
                    >
                        <Clock className="w-4 h-4" />
                        Menu
                    </a>
                )}
                {mapUrl && (
                    <a
                        href={mapUrl}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="inline-flex items-center gap-2 bg-yellow-100 text-yellow-700 hover:bg-yellow-200 font-semibold rounded-xl px-4 py-2 text-sm sm:text-base transition"
                    >
                        <MapPin className="w-4 h-4" />
                        Mappa
                    </a>
                )}
                {singleRestaurant?._links?.self && (
                    <a
                        href={singleRestaurant._links.self.href}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="inline-flex items-center gap-2 bg-gray-100 text-gray-700 hover:bg-gray-200 font-semibold rounded-xl px-4 py-2 text-sm sm:text-base transition"
                    >
                        Self
                    </a>
                )}
                {singleRestaurant?._links?.first && (
                    <a
                        href={singleRestaurant._links.first.href}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="inline-flex items-center gap-2 bg-gray-100 text-gray-700 hover:bg-gray-200 font-semibold rounded-xl px-4 py-2 text-sm sm:text-base transition"
                    >
                        First
                    </a>
                )}
                {singleRestaurant?._links?.prev && (
                    <a
                        href={singleRestaurant._links.prev.href}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="inline-flex items-center gap-2 bg-gray-100 text-gray-700 hover:bg-gray-200 font-semibold rounded-xl px-4 py-2 text-sm sm:text-base transition"
                    >
                        Prev
                    </a>
                )}
                {singleRestaurant?._links?.next && (
                    <a
                        href={singleRestaurant._links.next.href}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="inline-flex items-center gap-2 bg-gray-100 text-gray-700 hover:bg-gray-200 font-semibold rounded-xl px-4 py-2 text-sm sm:text-base transition"
                    >
                        Next
                    </a>
                )}
            </div>
        </section>
    );
}

