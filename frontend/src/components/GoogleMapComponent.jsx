import { useState } from "react";
import { GoogleMap, Marker, InfoWindow } from '@react-google-maps/api';
import { useGlobalContext } from '../context/GlobalContext';
import { Link } from "react-router-dom";
import { motion, AnimatePresence } from "framer-motion";

const containerStyle = {
  width: '100%',
  height: '320px',
  borderRadius: '1.5rem',
  boxShadow: '0 2px 12px rgba(0,0,0,0.10)'
};

function StarRating({ value = 0 }) {
  return (
    <div className="flex items-center gap-0.5 mt-1 mb-1">
      {[1, 2, 3, 4, 5].map(star => (
        <svg
          key={star}
          className="w-4 h-4"
          fill={star <= Math.round(value) ? "#fbbf24" : "none"}
          stroke="#fbbf24"
          viewBox="0 0 24 24"
        >
          <polygon points="12,2 15.1,8.1 22,9.2 17,14.1 18.2,21 12,17.8 5.8,21 7,14.1 2,9.2 8.9,8.1 " />
        </svg>
      ))}
      <span className="ml-1 text-sm text-gray-600 font-semibold">{value}</span>
    </div>
  );
}

const defaultCenter = { lat: 41.9028, lng: 12.4964 };

export default function GoogleMapComponent({ markers = [], center = defaultCenter, zoom = 13 }) {
  const { mapCenter, setMapCenter } = useGlobalContext();
  const [selected, setSelected] = useState(null);
  const [showFullMap, setShowFullMap] = useState(false);
  
  return (
    <>
      <GoogleMap
        mapContainerStyle={containerStyle}
        center={mapCenter}
        zoom={zoom}
        options={{ disableDefaultUI: true }}
        onClick={() => setShowFullMap(true)}
      >
        {markers.map((r, idx) => (
          <Marker
            key={r.id || r.name || idx}
            position={{ lat: r.latitude, lng: r.longitude }}
            label={r.name}

          />
        ))}
      </GoogleMap>
      {/* Overlay trasparente per intercettare il click */}
     
      <AnimatePresence>
        {showFullMap && (
          <motion.div
            initial={{ opacity: 0, scale: 0.98 }}
            animate={{ opacity: 1, scale: 1 }}
            exit={{ opacity: 0, scale: 0.98 }}
            transition={{ duration: 0.25 }}
            className="fixed inset-0 z-50 bg-black/70 flex items-center justify-center"
          >
            <div className="absolute inset-0">
              <GoogleMap
                mapContainerStyle={{ width: '100vw', height: '100vh' }}
                center={mapCenter}
                zoom={zoom}
                options={{ disableDefaultUI: false }}
              >
                {markers.map((r, idx) => (
                  <Marker
                    key={r.id || r.name || idx}
                    position={{ lat: r.latitude, lng: r.longitude }}
                    label={r.name}
                    onClick={() => setSelected(r)}
                  />
                ))}
                {selected && (
                  <InfoWindow
                    position={{ lat: selected.latitude, lng: selected.longitude }}
                    onCloseClick={() => setSelected(null)}
                  >
                    <div className="p-2 min-w-[180px]">
                      <Link to={`/ristorante/${selected.slug}`}>
                        <img src={selected.primaryImage} alt={selected.name} className="w-full h-20 object-cover rounded mb-2" />
                      
                      </Link>
                      <div className="font-bold">{selected.name}</div>
                      <div className="text-xs text-gray-600">{selected.address}</div>
                      <div className="text-xs text-gray-600">{selected.city}</div>
                      {/* Puoi aggiungere rating, categoria, ecc */}
                    </div>
                  </InfoWindow>
                )}
              </GoogleMap>
            </div>
            <button
              className="absolute top-6 right-6 bg-white rounded-full px-4 py-2 shadow text-gray-800 font-bold"
              onClick={() => setShowFullMap(false)}
            >
              Chiudi
            </button>
          </motion.div>
        )}
      </AnimatePresence>
    </>
  );
}
