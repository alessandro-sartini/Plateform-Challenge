import { useTranslation } from "react-i18next";

const colorClasses = [
  "bg-blue-100 text-blue-700",
  "bg-teal-100 text-teal-600",
  "bg-orange-100 text-orange-600",
  "bg-purple-100 text-purple-600",
  "bg-pink-100 text-pink-600",
  "bg-yellow-100 text-yellow-700",
  // Aggiungi altri colori se vuoi!
];

export default function RestaurantCard({ name, primaryImage, categories, rating, city }) {
  const { t } = useTranslation();

  return (
    <div className="flex flex-col rounded-3xl w-full items-start mt-2 md:max-w-xs md:mx-auto
    transition-transform transition-shadow duration-100
    active:scale-97 active:shadow-md
    cursor-pointer
    focus:outline-nones">
      {/* Immagine sopra, quadrata */}
      <div className="w-full aspect-square rounded-3xl overflow-hidden min-h-[150px] mb-2">
        <img src={primaryImage} alt={name} className="w-full h-full object-cover rounded-2xl" />
      </div>

      {/* Nome ristorante */}
      <h3 className="font-extrabold text-base sm:text-lg leading-tight text-gray-900 mb-1 text-center">{name}</h3>

      {/* Categorie */}
      <div className="flex gap-1 flex-wrap justify-center">
        {categories?.map((cat, i) => (
          <span
            key={cat}
            className={`
        px-2 py-1 rounded-xl text-[0.80rem] font-semibold
        ${colorClasses[i % colorClasses.length]}
        max-w-[80px] overflow-hidden text-ellipsis whitespace-nowrap
      `}
            title={cat}
          >
            {cat}
          </span>
        ))}
      </div>

      {/* Rating, città e bottone tutti sulla stessa riga */}
      <div className="flex font-medium gap-2 justify-between w-full m-1">
        <div className="flex items-center gap-2">
          <span className="flex items-center text-xs text-gray-500">
            <svg className="w-4 h-4 mr-0.5 text-yellow-400" fill="currentColor" viewBox="0 0 20 20"><path d="M10 15l-5.09 2.68 1-5.81-4.23-4.13 5.85-.85L10 2l2.47 5.03 5.85.85-4.23 4.13 1 5.81z" /></svg>
            {rating}
          </span>
          <span className="flex items-center text-gray-500 text-xs font-medium">
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
        {/* Bottone prenota in basso a destra */}
        {/* <button
          type="button"
          className="bg-blue-600 text-white font-bold rounded-xl px-4 py-1.5 shadow-md text-sm sm:text-base transition hover:scale-105 focus:outline-none cursor-pointer"
        >
          {t("book")}
        </button> */}
      </div>
    </div>
  );
}

