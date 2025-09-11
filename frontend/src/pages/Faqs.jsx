import { useEffect, useState } from "react";
import { useGlobalContext } from "../context/GlobalContext";

// Icona semplice per l’accordion
function ChevronIcon({ open }) {
    return (
        <svg
            className={`w-6 h-6 transition-transform duration-300 ${open ? "rotate-180 text-indigo-600" : "text-blue-300"}`}
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
        >
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 9l-7 7-7-7" />
        </svg>
    );
}

export default function Faqs() {
    const { faqsFetch, faqs, error, lang } = useGlobalContext();
    const [openIndex, setOpenIndex] = useState(null);

    useEffect(() => {
        faqsFetch();
    }, []);

    const toggleFaq = (idx) => {
        setOpenIndex(openIndex === idx ? null : idx);
    };

    return (
        // << AGGIUNGI min-h-screen QUI >>
        <div className="pt-2 pb-100 flex items-start">
            <div className="max-w-2xl w-full mx-auto px-2 py-8 md:py-12">
                <div className="w-full">
                    <h1 className="text-3xl font-bold text-center text-black pt-6 pb-2 relative z-10 drop-shadow">
                        <span className="relative z-10">Domande Frequenti</span>
                        <span className="text-gray-400 font-normal relative z-10"> (FAQ)</span>
                    </h1>
                    {error && (
                        <div className="text-red-500 text-center mb-6">
                            {error}
                        </div>
                    )}

                    <div className="space-y-6 mt-8 w-full">
                        {faqs?.map((faq, idx) => (
                            <div
                                key={faq.id || idx}
                                className={`group bg-white/80 backdrop-blur-sm rounded-2xl shadow-md border border-white/60 transition-all duration-300 ${
                                    openIndex === idx
                                        ? "shadow-xl border-indigo-300 scale-[1.03] ring-2 ring-indigo-100"
                                        : "hover:shadow-lg"
                                }`}
                            >
                                <button
                                    onClick={() => toggleFaq(idx)}
                                    className="w-full flex items-center justify-between gap-4 p-6 focus:outline-none group-hover:bg-blue-50/50 rounded-t-2xl transition"
                                >
                                    <span className="text-lg font-semibold text-black text-left drop-shadow-sm">
                                        {lang === "en" ? faq.question : faq.itQuestion}
                                    </span>
                                    <ChevronIcon open={openIndex === idx} />
                                </button>
                                <div
                                    className={`px-6 transition-all duration-400 overflow-hidden ${
                                        openIndex === idx ? "max-h-80 opacity-100 py-4" : "max-h-0 opacity-0 py-0"
                                    }`}
                                    style={{
                                        transitionProperty: "max-height, opacity, padding",
                                    }}
                                >
                                    <p className="text-base text-black leading-relaxed">
                                        {lang === "en" ? faq.response : faq.itResponse}
                                    </p>
                                    {openIndex === idx && (
                                        <div
                                            className="mt-4 h-1 w-1/2 rounded-full blur-[2px] mx-auto opacity-50"
                                            style={{
                                                background: "linear-gradient(90deg,#a5b4fc,#818cf8,#e0e7ff)",
                                            }}
                                        ></div>
                                    )}
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
}

