import React from "react";
import "./Navbar.css";

const navbar = () => {
  return (
    <nav className="navbar sticky-top Navbar">
      <i className="fas fa-laptop-code fa-logo"> ZORBA</i>
      <ul className="navbar-nav">
        <li>Log out</li>
      </ul>
    </nav>
  );
};

export default navbar;
