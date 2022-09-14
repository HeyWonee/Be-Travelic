import React from "react";
import { Routes, Route } from "react-router-dom";
import { RecommendPlaceMain, PlaceDetailMain } from "./pages/index";
import Navbar from "../src/components/common/Navbar";
import Footer from "./components/common/Footer";

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route
          path="/recommendMain"
          element={
            <RecommendPlaceMain
              latitude={36.68489220533342}
              longitude={127.46794555678892}
            />
          }
        />
        <Route
          path='/place/1'
          element={
            <PlaceDetailMain/>
          }
        />
      </Routes>
      <Footer />
    </>
  );
}

export default App;
