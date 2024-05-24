import React from "react";
import { useEffect, useState, useContext } from "react";
import Homepage from "./HealthMate/Homepage";
import { Register } from "./HealthMate/Register";
import { Login, LoginButton } from "./HealthMate/Login";
import { LogOutButton } from "./HealthMate/Logout";
import { Auth } from "./HealthMate/Auth";
import { ShowAppointments } from "./HealthMate/ShowAppointments";
import { FindNearByDoctors } from "./HealthMate/FindNearByDoctors";
import { CreateAppointment } from "./HealthMate/CreateAppointment";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import "./App.css";

function App() {
  // const [isUserLoggedIn, setIsUserLoggedIn] = useState(false);
  const { user } = useContext(Auth);
  useEffect(() => {}, []);
  return (
    <div className="app">
      <React.StrictMode>
        <BrowserRouter>
          {/* <App /> */}
          <Routes>
            <Route
              path="/"
              element={user ? <Homepage /> : <Navigate to="/login" />}
            >
              <Route index element={<FindNearByDoctors />} />
              <Route path="/appointments/list" element={<ShowAppointments />} />
              <Route
                path="appointments/create"
                element={<CreateAppointment />}
              />
            </Route>

            <Route
              path="/register"
              element={!user ? <Register /> : <Navigate to="/" />}
            />
            <Route
              path="/login"
              element={!user ? <Login /> : <Navigate to="/" />}
            />

            {/* <Route path="/register" element={<Register />} /> */}
          </Routes>
        </BrowserRouter>
      </React.StrictMode>
    </div>
  );
}

export default App;
