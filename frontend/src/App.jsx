import { BrowserRouter, Routes, Route } from 'react-router-dom';
import GlobalProvider from "./context/GlobalContext"
import "./i18n.js"
import { LoadScript } from '@react-google-maps/api';

//components
import Home from './pages/Home.jsx';
import DetailsPage from './pages/DetailsPage.jsx';
import ResultsList from './pages/ResultsList.jsx';
import ContactPage from './pages/ContactPage.jsx';
import DefaultLayout from './layout/DefaultLayout.jsx';
import Faqs from './pages/Faqs.jsx';

function App() {

  return (
    <>
      <LoadScript googleMapsApiKey={import.meta.env.VITE_GOOGLE_API}>
        <GlobalProvider>
          <BrowserRouter>
            <Routes>
              <Route element={<DefaultLayout />}>
                <Route path="/" element={<Home />} />
                <Route path="/ristoranti" element={<ResultsList />} />
                <Route path="/ristorante/:slug" element={<DetailsPage />} />
                <Route path="/faqs" element={<Faqs />} />
                <Route path="/assistenza" element={<ContactPage />} />
                <Route path="*" />
              </Route>
            </Routes>
          </BrowserRouter>
        </GlobalProvider>
      </LoadScript>


    </>
  )
}

export default App
