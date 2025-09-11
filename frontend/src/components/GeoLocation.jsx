import { useState } from "react";


export default function GeoLocation() {
    const [posizione, setPosizione] = useState(null);
    const [errore, setErrore] = useState(null);
  
    const chiediPosizione = () => {
      if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(
          (pos) => {
            setPosizione({
              lat: pos.coords.latitude,
              lon: pos.coords.longitude,
            });
            setErrore(null);
          },
          (err) => {
            setErrore(err.message);
            setPosizione(null);
          }
        );
      } else {
        setErrore("La geolocalizzazione non è supportata dal browser.");
      }
    };
  
    return (
      <div>
        <button onClick={chiediPosizione} className="px-4 py-2 bg-orange-400 text-white rounded-lg">
          Trova la mia posizione
        </button>
        {posizione && (
          <div>
            <p>Latitudine: {posizione.lat}</p>
            <p>Longitudine: {posizione.lon}</p>
          </div>
        )}
        {errore && <div className="text-red-500">{errore}</div>}
      </div>
    );
  }