import { useState } from "react";
import { useGlobalContext } from "../context/GlobalContext";

const COLOR_MAP = {
    Italiana: "bg-red-500 text-white",
    Sushi: "bg-pink-500 text-white",
    Burger: "bg-yellow-400 text-yellow-900",
    Pizza: "bg-orange-500 text-white",
    Vegetariana: "bg-emerald-500 text-white",
    // ...aggiungi altre categorie se vuoi
};

export default function ScopriPerCucina({ onSelect }) {

    const { categories } = useGlobalContext();

    return (
        <section className="w-full mx-auto px-5">
            <div className="rounded-2xl h-[300px] p-6 bg-gradient-to-r from-yellow-400 to-orange-500 flex flex-col gap-4 shadow-lg relative overflow-hidden">
                {/* Icona piatto/chef */}
                <span className="absolute top-4 right-4 bg-white/80 rounded-xl p-2">
                    <svg
                        className="w-7 h-7 text-orange-500"
                        viewBox="0 0 32 32"
                        fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        {/* Forchetta */}
                        <path d="M9.5 3C9.776 3 10 3.224 10 3.5v9a.5.5 0 0 1-1 0v-9C9 3.224 9.224 3 9.5 3zM7.5 3C7.776 3 8 3.224 8 3.5v9a.5.5 0 0 1-1 0v-9C7 3.224 7.224 3 7.5 3zM11.5 3C11.776 3 12 3.224 12 3.5v9a.5.5 0 0 1-1 0v-9c0-.276.224-.5.5-.5zM8.5 14a1.5 1.5 0 0 0 1.5-1.5V12h-3v.5A1.5 1.5 0 0 0 8.5 14zm0 0v13a1 1 0 0 0 2 0V14" />
                        {/* Coltello */}
                        <path d="M21.5 3c.828 0 1.5.672 1.5 1.5V24c0 1.105-.895 2-2 2h-.5a.5.5 0 0 1-.5-.5v-22A1.5 1.5 0 0 1 21.5 3z" />
                    </svg>
                </span>
                <h3 className="font-extrabold text-2xl text-gray-900 mb-2">
                    Di che cucina hai voglia oggi?
                </h3>
                <div className="flex flex-wrap gap-3">
                    {categories.map((cat) => (
                        <button
                            key={cat.id}
                            type="button"
                            onClick={() => {
                                setActive(cat.name);
                                if (onSelect) onSelect(cat.name);
                            }}
                            className={`px-4 py-2 rounded-xl font-bold text-sm shadow transition
                ${active === cat.name ? COLOR_MAP[cat.name] || "bg-gray-200 text-gray-800" : "bg-white text-gray-800"}
                ${active !== cat.name ? "opacity-90 hover:scale-105" : "scale-110"}
              `}
                        >
                            {cat.name}
                        </button>
                    ))}
                </div>
            </div>
        </section>
    );
}
