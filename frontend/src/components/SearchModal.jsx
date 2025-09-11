function SearchModal({ open, onClose }) {
    if (!open) return null;
    return (
      <div className="fixed inset-0 z-50 bg-black/60 flex items-start justify-center">
        <div className="bg-white w-full max-w-lg mx-auto mt-10 rounded-3xl shadow-lg p-6 relative animate-fadeIn">
          <button className="absolute top-4 right-4 text-gray-500 text-xl" onClick={onClose}>
            &times;
          </button>
          <h3 className="text-xl font-bold mb-4 text-gray-800">Filtra la ricerca</h3>
          {/* Qui metti tutta la search bar dettagliata (puoi copiare la tua form avanzata) */}
          {/* ... */}
          <form>
            {/* Location */}
            <input className="w-full border border-gray-200 rounded-xl px-4 py-3 mb-3" placeholder="Roma" />
            {/* DateField e altre opzioni */}
          </form>
        </div>
      </div>
    );
  }