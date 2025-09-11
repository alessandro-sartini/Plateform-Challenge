import { useState } from "react";

export default function ReviewsSection() {
  const reviews = [
    {
      name: "Giulia Bianchi",
      img: "/img/giulia.jpg",
      text: "Esperienza fanfastica! Cibo delizioso e servizio eccellente."
    },
    {
      name: "Kristin Watson",
      img: "/img/kristin.jpg",
      text: "Great place with amazing food and friendly service!"
    }
  ];

  const [active, setActive] = useState(0);

  return (
    <section className="w-full mx-auto">
      <div className="bg-white/80 rounded-2xl shadow-lg p-6 flex flex-col items-center text-center">
        <img
          src={reviews[active].img}
          alt={reviews[active].name}
          className="w-14 h-14 rounded-full object-cover mb-2"
        />
        <h3 className="font-bold text-lg text-gray-900 mb-1">
          {reviews[active].name}
        </h3>
        <p className="text-gray-800 text-base mb-4">
          {reviews[active].text}
        </p>
        {/* Dots */}
        <div className="flex items-center justify-center mt-2">
          {reviews.map((_, idx) => (
            <button
              key={idx}
              className={`w-6 h-6 flex items-center justify-center rounded-full mx-1 focus:outline-none transition ${
                active === idx ? "bg-yellow-400" : "bg-yellow-200"
              }`}
              onClick={() => setActive(idx)}
              aria-label={`Vai alla recensione ${idx + 1}`}
            >
              <span className={`block w-1.5 h-1.5 rounded-full bg-white ${active === idx ? "" : "opacity-60"}`}></span>
            </button>
          ))}
        </div>
      </div>
    </section>
  );
}