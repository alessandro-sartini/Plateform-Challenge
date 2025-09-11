import { useRef, useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import RegionCard from "./RegionCard";
import "swiper/css/navigation";
import { useTranslation } from "react-i18next";

const regions = [
  {
    nome: "Valle d'Aosta",
    color: "from-orange-400/100 via-orange-400/100",
    icon: "/images/regioni/valle-d-aosta.png",
    img: "https://www.dovedormire.info/wp-content/uploads/sites/119/valle-daosta-hd.jpg",
    capoluogo: {
      nome: "L'Aquila",
      coordinate: { lat: 42.3506, lng: 13.3995 }
    }
  },
  {
    nome: "Piemonte",
    color: "from-green-400/100 via-green-400/100",
    icon: "/images/regioni/piemonte.png",
    img: "https://www.italia.it/content/dam/tdh/it/interests/piemonte/torino/torino-in-48-ore/media/20220223132855-piazza-san-carlo-torino-piemonte-shutterstock-2102981095-rid.jpg",
    capoluogo: {
      nome: "Torino",
      coordinate: { lat: 45.0703, lng: 7.6869 }
    }
  },
  {
    nome: "Lombardia",
    color: "from-blue-400/100 via-blue-400/100",
    icon: "/images/regioni/lombardia.png",
    img: "https://storbyferie.com/wp-content/uploads/2025/04/Hvor-bor-man-bo-i-Milano.jpg", // Lago di Como
    capoluogo: {
      nome: "Milano",
      coordinate: { lat: 45.4642, lng: 9.19 }
    }
  },
  {
    nome: "Veneto",
    color: "from-orange-400/100 via-orange-400/100",
    icon: "/images/regioni/veneto.png",
    img: "https://www.itabus.it/on/demandware.static/-/Sites-ITABUS-Library/default/dw32751e5c/Destinazioni/Veneto/Venezia/Canal-Grande-e-sulla-Basilica-di-Santa-Maria-della-Salute-a-Venezia.jpg", // Venezia
    capoluogo: {
      nome: "Venezia",
      coordinate: { lat: 45.4408, lng: 12.3155 }
    }
  },
  {
    nome: "Trentino",
    color: "from-blue-400/100 via-blue-400/100",
    icon: "/images/regioni/trentino.png",
    img: "https://a.travel-assets.com/findyours-php/viewfinder/images/res70/157000/157462-Trentino-Alto-Adige.jpg", // Dolomiti
    capoluogo: {
      nome: "Trento",
      coordinate: { lat: 46.0702, lng: 11.1190 }
    }
  },
  {
    nome: "Liguria",
    color: "from-violet-400/100 via-violet-400/100",
    icon: "/images/regioni/liguria.png",
    img: "https://www.italia.it/content/dam/tdh/it/destinations/liguria/media/20210324125900-cinqueterre-riomaggiore-istock-521206872.jpg", // Cinque Terre
    capoluogo: {
      nome: "Genova",
      coordinate: { lat: 44.4056, lng: 8.9463 }
    }
  },
  {
    nome: "Friuli",
    color: "",
    icon: "/images/regioni/friuli.png",
    img: "https://image.jimcdn.com/app/cms/image/transf/none/path/sf63059cf0e80ccf2/image/i23213469e6e182c4/version/1528902586/image.jpg", // Trieste
    capoluogo: {
      nome: "Trieste",
      coordinate: { lat: 45.6495, lng: 13.7768 }
    }
  },
  {
    nome: "Emilia-Romagna",
    color: "from-violet-400/100 via-violet-400/100",
    icon: "/images/regioni/emilia-romagna.png",
    img: "https://www.italiavai.com/wp-content/uploads/2024/02/cosa-vedere-emilia-romagna.jpg", // Bologna
    capoluogo: {
      nome: "Bologna",
      coordinate: { lat: 44.4949, lng: 11.3426 }
    }
  },
  {
    nome: "Toscana",
    color: "from-orange-400/100 via-orange-400/100",
    icon: "/images/regioni/toscana.png",
    img: "https://storage.googleapis.com/mytour-prod/blog/1685703260795_toscana-jpg.jpeg", // Colline toscane

    capoluogo: {
      nome: "Firenze",
      coordinate: { lat: 43.7696, lng: 11.2558 }
    }
  },
  {
    nome: "Umbria",
    color: "from-green-400/100 via-green-400/100",
    icon: "/images/regioni/umbria.png",
    img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0wR_fFnrkaZLsVFTgYylgWIuZkt9iJWqDq_FqjqvRUoOkreB6oAsGmXpSOEXIPlt3PRg&usqp=CAU", // Campagna umbra

    capoluogo: {
      nome: "Perugia",
      coordinate: { lat: 43.1107, lng: 12.3908 }
    }

  },
  {
    nome: "Marche",
    color: "from-blue-400/100 via-blue-400/100",
    icon: "/images/regioni/marche.png",
    img: "https://www.anitavillas.it/blog/wp-content/uploads/10-cose-da-vedere-nelle-Marche-e1557132135427.jpg", // Riviera del Conero
    capoluogo: {
      nome: "Ancona",
      coordinate: { lat: 43.6158, lng: 13.5189 }
    }
  },
  {
    nome: "Lazio",
    color: "from-violet-400/100 via-violet-400/100",
    icon: "/images/regioni/lazio.png",
    img: "https://www.lazionascosto.it/wp-content/uploads/2019/06/colosseo.jpg", // Roma
    capoluogo: {
      nome: "Roma",
      coordinate: { lat: 41.9028, lng: 12.4964 }
    }
  },
  {
    nome: "Abruzzo",
    color: "from-orange-400/100 via-orange-400/100",
    icon: "/images/regioni/abruzzo.png",
    img: "https://www.dovedormire.info/wp-content/uploads/sites/119/abruzzo-hd.jpg",
    capoluogo: {
      nome: "L'Aquila",
      coordinate: { lat: 42.3506, lng: 13.3995 }
    }
  },
  {
    nome: "Molise",
    color: "from-green-400/100 via-green-400/100",
    icon: "/images/regioni/molise.png",
    img: "https://d28r45jypu6nt9.cloudfront.net/o/d40/img/w_1280,h_720/https.www.visitmolise.eu/documents/1538198/0/abbazia_s_vincenzo.jpg/58586b63-d92d-d78d-54dd-c641f8ddcdf9?t=1624522732045", // Natura

    capoluogo: {
      nome: "Campobasso",
      coordinate: { lat: 41.5607, lng: 14.6626 }
    }
  },
  {
    nome: "Campania",
    color: "from-blue-400/100 via-blue-400/100",
    icon: "/images/regioni/campania.png",
    img: "https://www.campania.info/wp-content/uploads/sites/111/napoli-notte.jpg", // Napoli/Costiera

    capoluogo: {
      nome: "Napoli",
      coordinate: { lat: 40.8522, lng: 14.2681 }
    }
  },
  {
    nome: "Puglia",
    color: "from-violet-400/100 via-violet-400/100",
    icon: "/images/regioni/puglia.png",
    img: "https://www.salentovacanza.com/images/le-meraviglie-della-puglia.png", // Mare pugliese
    capoluogo: {
      nome: "Bari",
      coordinate: { lat: 41.1171, lng: 16.8719 }
    }
  },
  {
    nome: "Basilicata",
    color: "from-orange-400/100 via-orange-400/100",
    icon: "/images/regioni/basilicata.png",
    img: "https://static2-viaggi.corriereobjects.it/wp-content/uploads/2015/06/basilicata-guide-getty-1080x721.jpg?v=1572449831", // Matera
    capoluogo: {
      nome: "Potenza",
      coordinate: { lat: 40.6418, lng: 15.8086 }
    }
  },
  {
    nome: "Calabria",
    color: "from-green-400/100 via-green-400/100",
    icon: "/images/regioni/calabria.png",
    img: "https://images.visititaly.eu/uploads/articoli/paragrafo/2024135716-7-giorni-in-calabria-pentedattilo.jpg", // Costa degli Dei
    capoluogo: {
      nome: "Catanzaro",
      coordinate: { lat: 38.9098, lng: 16.5877 }
    }
  },
  {
    nome: "Sicilia",
    color: "from-blue-400/100 via-blue-400/100",
    icon: "/images/regioni/sicilia.png",
    img: "https://www.sicilia.info/wp-content/uploads/sites/91/taormina-teatro-hd.jpg",
    capoluogo: {
      nome: "Palermo",
      coordinate: { lat: 38.1157, lng: 13.3615 }
    }
  },
  {
    nome: "Sardegna",
    color: "from-violet-400/100 via-violet-400/100",
    icon: "/images/regioni/sardegna.png",
    img: "https://t3.ftcdn.net/jpg/01/71/26/22/360_F_171262230_LRm2AFKGbyqSBQD1peEC7FfkSQaK4MTF.jpg", // Spiagge Sardegna

    capoluogo: {
      nome: "Cagliari",
      coordinate: {
        lat: 39.2238,
        lng: 9.1217
      }
    }
  },
];


export default function RegionCarouselModern() {
  const { t } = useTranslation();
  const swiperRef = useRef(null);
  const [isAtStart, setIsAtStart] = useState(true);
  const [isAtEnd, setIsAtEnd] = useState(false);

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
    <section className="max-w-screen-2xl mx-auto mt-16 mb-8 ">
      <h2 className="text-xl sm:text-xl md:text-2xl font-bold text-gray-900 mb-3 lm:text-center sm:text-left pl-5">
        {t("explore_by_region")}
      </h2>
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

          //
          >
            {regions.map((region, idx) => (
              <SwiperSlide key={idx}>
                <RegionCard
                  name={region.nome}
                  img={region.img}
                  icon={region.icon}
                  city={region.capoluogo}
                  color={region.color}
                  
                />
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
            <svg className="w-6 h-6 text-gray-700" fill="none" stroke="currentColor" strokeWidth="3" viewBox="0 0 24 24">
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
            <svg className="w-6 h-6 text-gray-700" fill="none" stroke="currentColor" strokeWidth="3" viewBox="0 0 24 24">
              <path d="M9 5l7 7-7 7" />
            </svg>
          </button>
        )}
      </div>
    </section>
  );
}