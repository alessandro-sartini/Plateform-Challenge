# ✅ RESTFINDER.IT - CHECKLIST BACK-END DEFINITIVA

---

## 1. **Setup iniziale**
- [x ] Scegliere stack tecnologico (es. Node.js, Laravel, Django, etc.)
- [ x] Inizializzare repository e ambiente (env, linter, ecc.)
- [ x] Setup database e scrittura schema iniziale (migrations)

---

## 2. **Autenticazione e Sicurezza Staff**
- [x ] Model Staff (email, password hash)
- [ x] Endpoint login/logout staff
- [x ] Middleware autenticazione (JWT/session)
- [ x] Protezione aree riservate (back-office)
- [ ] Rate limiting backend e protezione endpoint sensibili

---

## 3. **Gestione Ristoranti**
- [ x] Model/Entity Ristorante con tutti i campi richiesti:
    - [ x] nome
    - [ x] indirizzo, località, CAP, provincia, nazione
    - [ x] latitudine, longitudine (solo DB)
    - [ x] Place ID Google (solo DB)
    - [ x] immagine principale (file name)
    - [ x] link menù
    - [ x]x sito web
    - [ x] telefono (solo DB)
    - [ x] fascia di prezzo
    - [ x] rank Google
    - [ x] numero recensioni
    - [ ]x punteggio recensioni
    - [ x] URL diretto Google Maps
    - [ x] ID Plateform (se cliente)
    - [ x] link prenotazione Plateform (se cliente)
    - [ x] relazione categorie (N:N)
    - [ ] categoria principale (FK)
- [ x] CRUD ristoranti (create, read, update, delete)
- [ ]x Upload/associazione immagini
- [ x] Validazione dati ristoranti (campi obbligatori, formati, deduplicazione)

---

## 4. **Categorie e Tag**
- [ x] Model Categoria
- [ x] CRUD categorie (create, read, update, delete)
- [ x] Relazione categorie-ristoranti N:N
- [ x] Selezione e gestione categoria principale per ristorante
- [ x] Endpoint lista categorie (per filtri FE)

---

## 5. **Ricerca e Filtri avanzati**
- [ ]x Endpoint ricerca ristoranti con:
    - [x ] ricerca per nome/località/piatto
    - [ x] filtro per località
    - [ x] filtro per categoria
    - [ x] filtro per fascia di prezzo
    - [ x] filtro per rating
    - [ x] filtro per servizi
- [ x] Paginazione risultati ricerca
- [ x] Endpoint risultati mappa (restituzione coordinate + info base)

---

## 6. **Scheda Dettaglio Ristorante**
- [ x] Endpoint dettaglio ristorante (tutti i dati previsti)
- [ ] Logica “Prenota ora” se ID/link Plateform presente
- [ ] Logica “Non prenotabile online” + link “Sei il proprietario?”

---

## 7. **Area Back-Office Staff**
- [ x] CRUD ristoranti
- [ x] CRUD categorie/tag
- [ x] CRUD pagine di servizio (Contatti / FAQ)
- [ x] Visualizzazione e gestione richieste contatto ricevute
- [ ] Endpoint protetti da autenticazione staff

---

## 8. **Motore di Importazione/Sincronizzazione Google**
- [ x] Integrazione API Google Maps Places
- [ x] Endpoint ricerca locali da Google (categoria + zona)
- [ x] Visualizzazione risultati trovati (backoffice)
- [ x] Selezione uno o più ristoranti da importare
- [ x] Import nuovi ristoranti o aggiornamento esistenti
- [ ] Deduplicazione basata su nome, indirizzo, prossimità
- [ ] Logging importazioni (chi, cosa, quando)
- [ x] Gestione errori import/sincronizzazione

---

## 9. **Pagine di Servizio (Contatti / FAQ)**
- [ ] Model pagina servizio (titolo, testo, lingua, tipo)
- [ ] CRUD pagine servizio
- [ ] Endpoint invio richieste contatto (form staff e pubblico)
- [ ] Salvataggio richieste su DB e/o notifica email

---

## 10. **Bilingua/Localizzazione**
- [ ] Struttura dati per supporto IT/EN (campi testo ristorante, pagine di servizio)
- [ ] Endpoint con parametro lingua per restituire dati localizzati

---

## 11. **SEO, Accessibilità, Best Practice**
- [ ] Supporto per slug url parlanti (es: `/ristoranti/pizzeria-da-mario`)
- [ ] API per meta tag/social preview (se richiesto dal FE)
- [ ] Supporto dati strutturati/schema.org lato API (se richiesto dal FE)

---

## 12. **Monitoraggio e Logging**
- [ x] Logging accessi staff (login/logout, import, edit)
- [ x] Endpoint base statistiche utilizzo (ristoranti aggiunti, modifiche)
- [ x] Esportazione log per audit (facoltativo)

---

## 13. **Testing & Documentazione**
- [ ] Test unitari e di integrazione (su modelli, endpoint critici)
- [x ] Test API con Postman/Swagger
- [ ] Istruzioni deploy + setup dev/prod (README)
- [ ] Documentazione API per frontend (Swagger/OpenAPI/altro)

---

## 14. **Bonus/Nice to have**
- [ ] Ruoli multipli staff (admin/editor)
- [ ] Audit log modifiche
- [ ] Notifiche email automatiche nuove richieste contatto/import

---

