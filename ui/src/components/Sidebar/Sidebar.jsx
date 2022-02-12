import React from "react";
import { Link } from "react-router-dom";

import "./Sidebar.css";

const sidebar = () => {
  return (
    <div className="Sidenav nav flex-column">
      <Link className="Linklist" to="/dashboard">
        DASHBOARD
      </Link>
      <Link className="Linklist" to="/account">
        ACCOUNT
      </Link>
      <Link></Link>
      <Link className="Linklist" to="/instructor">
        INSTRUCTOR
      </Link>
      <Link>
        <Link className="Linklist" to="/userprofile">
          USER PROFILE
        </Link>
      </Link>
      <Link className="Linklist" to="/maps">
        MAPS
      </Link>
      <Link className="Linklist" to="/notification">
        NOTIFICATION
      </Link>
    </div>
  );
};

export default sidebar;
