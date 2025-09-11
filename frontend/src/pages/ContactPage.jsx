import { FaPhoneAlt, FaEnvelope } from "react-icons/fa";
import { MdHelpOutline } from "react-icons/md";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";

export default function Assistenza() {
    const { t } = useTranslation();

    return (
        <div className="w-full pt-2 pb-150 flex flex-col items-center justify-center px-2">
            {/* Icona grande e titolo */}
            <div className="flex flex-col items-center mb-4">
                <span className="bg-blue-100 rounded-full p-4 mb-3 shadow-md">
                    <MdHelpOutline className="w-10 h-10 text-blue-500" />
                </span>
                <h1 className="text-3xl md:text-4xl font-extrabold text-gray-900 mb-2 text-center">
                    Assistenza e Supporto
                </h1>
                <p className="text-gray-500 text-center max-w-lg mb-6">
                    Hai bisogno di aiuto o vuoi segnalarci un problema? Il nostro team è a tua disposizione!
                </p>
            </div>
            {/* Card contenuto assistenza */}
            <div className="bg-white rounded-3xl shadow-lg px-4 py-6 sm:px-8 sm:py-8 flex flex-col gap-6 w-full max-w-md">
                <div className="flex items-center gap-3">
                    <FaPhoneAlt className="text-green-500 w-6 h-6" />
                    <div>
                        <div className="font-bold text-gray-800">Telefono</div>
                        <div className="text-gray-600 text-sm">800 123 456 (lun-ven 9:00-18:00)</div>
                    </div>
                </div>
                <div className="flex items-center gap-3">
                    <FaEnvelope className="text-blue-500 w-6 h-6" />
                    <div>
                        <div className="font-bold text-gray-800">Email</div>
                        <div className="text-gray-600 text-sm">assistenza@restfinder.it</div>
                    </div>
                </div>
                <div className="border-t border-gray-200 pt-4 mt-2">
                    <div className="text-gray-700 text-center text-sm">
                        <strong>FAQ:</strong> consulta la nostra <Link to={"/faqs"} className="underline cursor-pointer text-blue-500">sezione domande frequenti</Link> per risposte rapide.
                    </div>
                </div>
            </div>
        </div>
    );
}
