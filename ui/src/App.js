import React from 'react';

import { Route, Switch } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import Sidebar from './components/Sidebar/Sidebar';
import UserForm from './components/UserForm/UserForm';
import Dashboard from './components/Dashboard/Dashboard';
import Account from './components/Account/Account';
import Instructor from './components/Instructor/Instructor';
import Map from './components/Map/Map';
import Notification from './components/Notification/Notification';









function App() {
  return (
    <div className="content">
      <Navbar />
      <Sidebar />

      <Switch>
        <Route path="/dashboard" component={Dashboard} />
        <Route path="/account" component={Account} />
        <Route path="/instructor" component={Instructor} />
        <Route path="/userprofile" component={UserForm} />
        <Route path="/maps" component={Map} />
        <Route path="/notification" component={Notification} />
      </Switch>

    </div>
  );
}

export default App;
