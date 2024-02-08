import React, { useState } from "react";

import "./Navbar.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

export default function Navbar() {
  const [isDropdownVisible, setIsDropdownVisible] = useState(false);
  const toggleDropdown = () => setIsDropdownVisible(!isDropdownVisible);

  return (
    <nav className="nav">
      <a href="/" className="site-title">
        Project Rabbit
      </a>
      <ul className="nav-links">
        <li className="active">
          <a href="/test-page">Test Page</a>
        </li>
        <li>
          <a href="/your-work">Your work</a>
        </li>
        <li>
          <a href="/projects">Projects</a>
        </li>
        {/* Mobile "More" button */}
        <li className="mobile-more-button" onClick={toggleDropdown}>
          More
        </li>
      </ul>
      {/* Mobile dropdown menu */}
      <div className={`mobile-dropdown ${isDropdownVisible ? "visible" : ""}`}>
        <a href="/test-page">Test Page</a>
        <a href="/your-work">Your work</a>
        <a href="/projects">Projects</a>
      </div>
      <div className="search-box">
        <FontAwesomeIcon
          icon={faMagnifyingGlass}
          style={{ color: "grey", paddingLeft: "10px", paddingRight: "5px" }}
        />
        <input type="text" placeholder="Search" />
      </div>

      <a href="/account">Account</a>
    </nav>
  );
}
