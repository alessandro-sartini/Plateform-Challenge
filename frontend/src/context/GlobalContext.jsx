import { createContext, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const GlobalContext = createContext();

export default function GlobalProvider({ children }) {
    const [cityRestaurants, setCityRestaurants] = useState({
        milano: [],
        roma: [],
        napoli: [],
    });

    const fetchRestaurantsByCity = async () => {
        const cities = ["milano", "roma", "napoli"];
        const results = {};

        for (const city of cities) {
            try {
                const res = await fetch(`http://localhost:8080/api/restaurants/paged?search=${city}`);
                const data = await res.json();
                results[city] = data?._embedded?.restaurantSummaryDtoList || [];
            } catch (error) {
                console.error(`Errore fetch per ${city}:`, error);
                results[city] = [];
            }
        }
        setCityRestaurants(results);
    }

    const [restaurants, setRestaurants] = useState([]);
    const [location, setLocation] = useState('');
    const [page, setPage] = useState({});
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setselectedCategory] = useState("");
    const [priceCategory, setPriceCategory] = useState(null);
    const [minRating, setMinRating] = useState(null)
    const [singleRestaurant, setSingleRestaurant] = useState({});
    const [links, setLinks] = useState({});

    // Leggi il centro mappa dal localStorage all'avvio
    const [mapCenter, setMapCenter] = useState(() => {
        const saved = localStorage.getItem("mapCenter");
        return saved ? JSON.parse(saved) : { lat: 41.9028, lng: 12.4964 };
    });

    // Funzione per aggiornare e salvare il centro mappa
    const updateMapCenter = (center) => {
        setMapCenter(center);
        localStorage.setItem("mapCenter", JSON.stringify(center));
    };

    const getCoordinateFromSearch = (search) => {
        const coordsMap = {
            "milano": { lat: 45.4642, lng: 9.19 },
            "roma": { lat: 41.9028, lng: 12.4964 },
            "napoli": { lat: 40.8522, lng: 14.2681 }
        };
        const key = search.trim().toLowerCase();
        return coordsMap[key] || { lat: 41.9028, lng: 12.4964 };
    };

    const handleSearch = async (location, selectedCategory, priceCategory) => {
        try {
            let url = `http://localhost:8080/api/restaurants/paged?search=${location}`;
            if (selectedCategory) url += `&category=${selectedCategory}`;
            if (priceCategory) url += `&priceCategory=${priceCategory}`;
            if (minRating) url += `&minRating=${minRating}`;
            const res = await fetch(url);
            const data = await res.json();
            setRestaurants(data?._embedded?.restaurantSummaryDtoList);
            setLinks(data?._links);
            setPage(data.page);
            // Aggiorna il centro della mappa in base alla ricerca
            const coordinate = getCoordinateFromSearch(location);
            if (coordinate) updateMapCenter(coordinate);
        } catch (error) {
            console.error("Errore durante il fetch:", error);
        }
    };





    // Fetch delle categorie all'avvio del provider
    const categoriesFetch = async () => {
        try {
            const res = await fetch(`http://localhost:8080/api/categories`);
            const data = await res.json();
            setCategories(data);
        } catch (error) {
            console.error("Errore nel fetch delle categorie:", error);
        }
    }




    const fechSigleRestaurant = async (slug) => {
        setSingleRestaurant({});
        try {
            const res = await fetch(`http://localhost:8080/api/restaurants/slug/${slug}`);
            const data = await res.json();
            setSingleRestaurant(data)
        } catch (error) {
            console.error("Errore nel fetch del ristorante:", error);
        }
    }


    const [faqs, setFaqs] = useState([]);
    const [error, setError] = useState(null);

    const faqsFetch = async () => {
        try {
            const res = await fetch(`http://localhost:8080/api/faqs`);
            const data = await res.json();
            setFaqs(data);
        } catch (error) {
            setError("Errore nel fetch delle faqs:", error);
        }
    }



    const [mostVisited, setMostVisited] = useState([]);

    const handleMost = async () => {
        try {

            const res = await fetch(`http://localhost:8080/api/restaurants/most-viewed`);
            const data = await res.json();
            setMostVisited(data?.content);
        } catch (error) {
            console.error("Errore durante il fetch:", error);
        }
    };

    // --- Gestione paginazione SPA ---
    const handleFetchPage = async (url) => {
        try {
            const res = await fetch(url);
            const data = await res.json();
            setRestaurants(data?._embedded?.restaurantSummaryDtoList || []);
            setLinks(data?._links || {});
            setPage(data.page || {});
            // Scroll to top on page change
            window.scrollTo({ top: 0, behavior: "smooth" });
        } catch (error) {
            console.error("Errore durante il fetch paginazione:", error);
        }
    };

    const [lang, setLang] = useState(localStorage.getItem("lang") || "it");




    const [search, setSearch] = useState("");


    const value = {
        cityRestaurants,
        fetchRestaurantsByCity,
        restaurants,
        setRestaurants,
        page,
        setPage,
        categories,
        categoriesFetch,
        handleSearch,
        setselectedCategory,
        selectedCategory,
        setLocation,
        location,
        setPriceCategory,
        priceCategory,
        setMinRating,
        minRating,
        fechSigleRestaurant,
        singleRestaurant,
        faqsFetch,
        faqs,
        error,
        handleMost,
        mostVisited,
        links,
        handleFetchPage,
        setMapCenter,
        mapCenter,
        updateMapCenter,
        setLang,
        lang,
        search, 
        setSearch
    }
    return (
        <GlobalContext.Provider value={value}>
            {children}
        </GlobalContext.Provider>
    )
}

export function useGlobalContext() {
    return useContext(GlobalContext)
}