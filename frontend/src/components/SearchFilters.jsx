import { useEffect, useState } from "react";
import { Star } from "lucide-react";
import { useGlobalContext } from "../context/GlobalContext";
import { useTranslation } from "react-i18next";
import { FaSearch } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const priceLevels = [
  {
    value: "€",
    light: "bg-green-100 text-green-700",
    dark: "bg-green-600 text-white"
  },
  {
    value: "€€",
    light: "bg-green-100 text-green-700",
    dark: "bg-green-600 text-white"
  },
  {
    value: "€€€",
    light: "bg-green-100 text-green-700",
    dark: "bg-green-600 text-white"
  },
];

export default function SearchFilters() {
  const { t } = useTranslation();
  const [selectedPrice, setSelectedPrice] = useState("");
  const [ratingSelected, setRatingSelected] = useState(false);
  const {
    categories,
    categoriesFetch,
    setselectedCategory,
    selectedCategory,
    setPriceCategory,
    setMinRating,
    lang,
    handleSearch,
    location
  } = useGlobalContext();

  const navigate = useNavigate();

  useEffect(() => {
    categoriesFetch();
    // eslint-disable-next-line
  }, []);

  // Handler rating
  // const handleRatingClick = () => {
  //   setRatingSelected(prev => {
  //     setMinRating(!prev ? 4 : null);
  //     return !prev;
  //   });
  // };

  const handleRatingClick = () => {
  const nuovoValore = !ratingSelected;
  setRatingSelected(nuovoValore);
  setMinRating(nuovoValore ? 4 : null);
};


  // Handler cerca
  const onSubmit = (e) => {
    e.preventDefault();
    handleSearch(
      location,
      selectedCategory,
      selectedPrice ? selectedPrice.length : "",
      ratingSelected ? 4 : null
    );
    let url = `/ristoranti?search=${encodeURIComponent(location || "")}`;
    if (selectedCategory) url += `&category=${encodeURIComponent(selectedCategory)}`;
    if (selectedPrice) url += `&price=${selectedPrice.length}`;
    if (ratingSelected) url += `&minRating=4`;
    navigate(url);
  };

  return (
    <div className="bg-white rounded-3xl shadow-lg px-4 sm:px-6 py-6 sm:py-8 flex flex-col gap-6 w-full max-w-full">
      {/* Header */}
      <div className="flex items-center gap-2 mb-2">
        <h2 className="text-xl sm:text-2xl font-extrabold text-gray-800">{t("filters")}</h2>
      </div>

      {/* Categoria */}
      <div>
        <div className="font-bold text-gray-700 text-base mb-1">{t("category")}</div>
        <div className="flex flex-wrap gap-2">
          {categories.map((c) => (
            <button
              type="button"
              key={c.id}
              onClick={() =>
                setselectedCategory((prev) => (prev === c.name ? "" : c.name))
              }
              className={`
                rounded-2xl px-3 py-1.5 sm:px-4 sm:py-1.5 text-sm font-semibold truncate shadow-sm transition-all duration-200
                ${selectedCategory === c.name
                  ? `${c.dark} ring-2 ring-primary/40 shadow-lg scale-105`
                  : `${c.light} opacity-90 hover:scale-105`}
              `}
              style={{ maxWidth: 100 }}
            >
              {c.name && lang === "en" ? c.name : c.itName}
            </button>
          ))}
        </div>
      </div>

      {/* Price Range */}
      <div>
        <div className="font-bold text-gray-700 text-base mb-1">{t("price_range")}</div>
        <div className="flex gap-2 flex-wrap">
          {priceLevels.map((p) => (
            <button
              key={p.value}
              type="button"
              onClick={() => {
                if (selectedPrice === p.value) {
                  setSelectedPrice("");
                  setPriceCategory("");
                } else {
                  setSelectedPrice(p.value);
                  setPriceCategory(p.value.length);
                }
              }}
              className={`
                rounded-xl py-2 px-3 sm:px-4 font-bold text-sm shadow-sm transition-all duration-200
                ${selectedPrice === p.value
                  ? `${p.dark} ring-2 ring-primary/40 shadow-lg scale-105`
                  : `${p.light} opacity-90 hover:scale-105`}
              `}
            >
              {p.value}
            </button>
          ))}
        </div>
      </div>

      {/* Rating */}
      <div>
        <div className="font-bold text-gray-700 text-base mb-1">{t("reviews")}</div>
        <div className="flex gap-2 flex-wrap">
          <button
            type="button"
            onClick={handleRatingClick}
            className={`
              flex items-center gap-2 font-semibold text-base transition-all duration-200
              rounded-2xl px-4 py-1.5 shadow-sm
              ${ratingSelected
                ? "bg-violet-500 text-white ring-2 ring-violet-300 shadow-lg scale-105"
                : "bg-violet-100 text-violet-700 opacity-90 hover:scale-105"}
            `}
          >
            <span className={`rounded-full w-8 h-8 flex items-center justify-center transition-all duration-200
              ${ratingSelected
                ? "bg-violet-600 text-white shadow"
                : "bg-violet-100 text-violet-700"}
            `}>
              <Star className="w-4 h-4" />
            </span>
            4+
          </button>
        </div>
        <button
          className="flex items-center justify-center gap-2 bg-gradient-to-r from-orange-400 to-orange-600 text-white rounded-full w-full sm:w-auto px-6 py-3 text-base font-bold shadow-md transition mt-4"
          onClick={onSubmit}
        >
          <FaSearch className="text-white" />
        </button>
      </div>
    </div>
  );
}
