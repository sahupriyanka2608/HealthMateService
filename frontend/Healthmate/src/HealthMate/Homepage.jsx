import React from "react";
import "./Homepage.css";
import download from "../assets/download.jpg";
import { useState, useEffect, useContext } from "react";
import { api } from "../services/api";
import { Outlet, Link, useNavigate, useLocation } from "react-router-dom";
import Button from "react-bootstrap/Button";
import { Auth } from "./Auth";
import { RegisterButton, Register } from "./Register";
import { LoginButton, Login } from "./Login";
import { ShowAppointments, ShowAppointmentsButton } from "./ShowAppointments";
import { LogOutButton } from "./Logout";
import { FindNearByDoctors, GetNearByDoctorsButton } from "./FindNearByDoctors";

function Header() {
  const { user } = useContext(Auth); //changed
  const location = useLocation();
  const { hash, pathname, search } = location;
  console.log("pathname:" + pathname);
  console.log(user);
  return (
    <div className="row">
      <div className="container">
        <h1>HealthMate</h1>
        {pathname == "/" && (
          <div style={{ float: "right" }}>
            <h6>Hi!{user.userName}</h6>
            <LogOutButton />
            <ShowAppointmentsButton />
          </div>
        )}
        {pathname == "/appointments/list" && (
          <div style={{ float: "right" }}>
            <h6>Hi!{user.userName}</h6>
            <GetNearByDoctorsButton />
            <LogOutButton />
          </div>
        )}

        {/* Login Page */}
        {pathname == "/login" && <RegisterButton />}

        {/* Register Page */}
        {pathname == "/register" && <LoginButton />}

        {/* {state.user && <h1>{state.user.username}</h1>} */}
      </div>
    </div>
  );
}

function Homepage() {
  return (
    <div className="header">
      <Header />
      <Outlet />
    </div>
  );
}

export default Homepage;
