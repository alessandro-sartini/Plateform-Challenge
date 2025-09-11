import GoogleMapComponent from "../components/GoogleMapComponent";
import SearchFilters from "../components/SearchFilters";
import ResturantCard from "../components/ResturantCard";
import SearchBar from "../components/SearchBar";
import { useEffect, useState } from "react";
import { useGlobalContext } from "../context/GlobalContext";
import { Link, useLocation } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { MdFirstPage, MdLastPage, MdNavigateBefore, MdNavigateNext } from "react-icons/md";




export default function ResultList() {
  const [showMap, setShowMap] = useState(false);
  const [showFilters, setShowFilters] = useState(false);
  const { handleSearch, restaurants, page, links, handleFetchPage } = useGlobalContext();
  const { t } = useTranslation();

  const markers = restaurants;
  const location = useLocation();

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const search = params.get("search") || "";
    const category = params.get("category") || "";
    const priceCategory = params.get("priceCategory") || "";

    handleSearch(search, category, priceCategory);
  }, [location.search]);

  // --- Render ---
  return (
    <>
      <div className="flex justify-center py-5">
        <SearchBar />
      </div>

      {/* Bottoni per mobile */}
      <div className="flex gap-4 mb-6 md:hidden justify-center">
        <button
          className="px-4 py-2 rounded-lg bg-gray-100 text-gray-700 font-semibold shadow hover:bg-gray-200 border border-gray-200 transition"
          onClick={() => setShowMap((v) => !v)}
        >
          {showMap ? t("hide_map") : t("show_map")}
        </button>
        <button
          className="px-4 py-2 rounded-lg bg-gray-100 text-gray-700 font-semibold shadow hover:bg-gray-200 border border-gray-200 transition"
          onClick={() => setShowFilters((v) => !v)}
        >
          {showFilters ? t("hide_filters") : t("show_filters")}
        </button>
      </div>
      <div className="flex flex-col gap-1">
        {/* Mappa e filtri visibili solo se attivati su mobile */}
        {showMap && (
          <div className="mb-5 px-5 pxmd:px-0">
            <GoogleMapComponent markers={restaurants} />
          </div>

        )}
        {showFilters && (
          <div className="mb-5 px-5 md:hidden">
            <SearchFilters />
          </div>
        )}
      </div>

      <section className="w-full">
        <div className="max-w-screen-2xl mx-auto px-5 md:px-5 lg:px-5 xl:px-5 2xl:px-0">
          <div className="flex flex-col md:flex-row gap-10">
            {/* Colonna sinistra: visibile solo su desktop */}
            <div className="hidden md:flex flex-col w-full md:w-1/4 gap-6">
              <div className="rounded-3xl shadow-lg border border-gray-200 bg-white overflow-hidden">
                <GoogleMapComponent center={{ lat: 41.9028, lng: 12.4964 }} zoom={15} markers={markers} />
              </div>
              <div>
                <SearchFilters />
              </div>
            </div>
            {/* Colonna destra: Risultati */}
            <div className="w-full md:w-3/4">
              <h2 className="text-xl sm:text-xl md:text-2xl font-bold text-gray-900 mb-3 lm:text-center sm:text-left">
                {t("search_results", { count: restaurants?.length })}
              </h2>

              <div className="flex-1 h-[calc(100vh-11rem)] overflow-y-auto hide-scrollbar">
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 2xl:grid-cols-5 gap-2.5 ">
                  {restaurants?.map((rest) => (
                    <Link to={`/ristorante/${rest.slug}`} key={rest.id}>
                      <ResturantCard
                        {...rest}
                        primaryImage={rest.primaryImage}
                      />
                    </Link>
                  ))}
                </div>
              </div>

              {/* Paginazione */}
              <div className="flex gap-2 mb-3 flex-wrap items-center justify-center pt-3">
                {links?.first && (
                  <button
                    onClick={() => handleFetchPage(links.first.href)}
                    className="bg-gray-100 text-gray-700 font-bold shadow hover:bg-gray-200 px-3 py-2 flex items-center gap-2 transition rounded-lg md:rounded-full border border-gray-200"
                    title={t("first")}
                    aria-label={t("first")}
                    tabIndex={0}
                  >
                    <MdFirstPage className="text-xl" />
                    <span className="hidden sm:inline">{t("first")}</span>
                  </button>
                )}
                {links?.prev && (
                  <button
                    onClick={() => handleFetchPage(links.prev.href)}
                    className="bg-gray-100 text-gray-700 font-bold shadow hover:bg-gray-200 px-3 py-2 flex items-center gap-2 transition rounded-lg md:rounded-full border border-gray-200"
                    title={t("prev")}
                    aria-label={t("prev")}
                    tabIndex={0}
                  >
                    <MdNavigateBefore className="text-xl" />
                    <span className="hidden sm:inline">{t("prev")}</span>
                  </button>
                )}
                <span className="text-sm text-gray-400 font-semibold mx-2">
                  {page && typeof page?.number === "number"
                    ? `${t("page")} ${page.number + 1} / ${page.totalPages}`
                    : null}
                </span>
                {links?.next && (
                  <button
                    onClick={() => handleFetchPage(links.next.href)}
                    className="bg-gray-100 text-gray-700 font-bold shadow hover:bg-gray-200 px-3 py-2 flex items-center gap-2 transition rounded-lg md:rounded-full border border-gray-200"
                    title={t("next")}
                    aria-label={t("next")}
                    tabIndex={0}
                  >
                    <span className="hidden sm:inline">{t("next")}</span>
                    <MdNavigateNext className="text-xl" />
                  </button>
                )}
                {links?.last && (
                  <button
                    onClick={() => handleFetchPage(links.last.href)}
                    className="bg-gray-100 text-gray-700 font-bold shadow hover:bg-gray-200 px-3 py-2 flex items-center gap-2 transition rounded-lg md:rounded-full border border-gray-200"
                    title={t("last")}
                    aria-label={t("last")}
                    tabIndex={0}
                  >
                    <span className="hidden sm:inline">{t("last")}</span>
                    <MdLastPage className="text-xl" />
                  </button>
                )}
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}
