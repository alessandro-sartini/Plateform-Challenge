const categorie = [
    { nome: "Pizzerie", icon: <span className="mr-2 text-base">🍕</span> },
    { nome: "Fast food", icon: <span className="mr-2 text-base">🍔</span> },
    { nome: "Trattorie", icon: <span className="mr-2 text-base">🍝</span> },
    { nome: "Vegetariano", icon: <span className="mr-2 text-base">🥗</span> },
    { nome: "Pesce", icon: <span className="mr-2 text-base">🐟</span> },
    { nome: "Sushi", icon: <span className="mr-2 text-base">🍣</span> },
    { nome: "Steakhouse", icon: <span className="mr-2 text-base">🥩</span> },
    { nome: "Cucina cinese", icon: <span className="mr-2 text-base">🥡</span> },
    { nome: "Dessert", icon: <span className="mr-2 text-base">🍰</span> },
    { nome: "Street Food", icon: <span className="mr-2 text-base">🌭</span> },
    { nome: "Gourmet", icon: <span className="mr-2 text-base">👨‍🍳</span> },
    { nome: "Gluten Free", icon: <span className="mr-2 text-base">🚫🌾</span> }
];

export default function BadgeCategorie() {
    // Duplica le badge per l'effetto loop fluido
    const badgeList = [...categorie, ...categorie];
  
    return (
        <div className="relative w-full max-w-[500px] mx-auto overflow-x-hidden py-2 mb-6">
          {/* Gradient sinistra */}
    
          <div className="flex gap-4 animate-marquee whitespace-nowrap">
            {badgeList.map(({ nome, icon }, i) => (
              <span
                key={nome + i}
                className="flex items-center bg-white rounded-xl px-3 py-1 shadow-sm border border-gray-100 text-gray-700 font-medium text-xs mx-1 cursor-pointer hover:bg-gray-50 transition"
              >
                {icon}
                <span className="text-xs">{nome}</span>
              </span>
            ))}
          </div>
        </div>
      );
  }