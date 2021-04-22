import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import {store} from './store'
import {Provider} from 'react-redux';
import SignIn from './containers/auth/signin'
import SignUp from './containers/auth/signup'
import Rep from './containers/repariers'
import FinDev from './containers/finishedDevices'
import Centers from './containers/centers'
import Devices from './containers/devices'
import Device from './components/addDevice'

function App() {
  return (
      <Provider store={store}>
        <div className="App">
            <Router>
                <Route path="/api/auth" exact component={SignIn}/>
                <Route path="/api/repariers/signUp" exact component={SignUp}/>
                <Route path="/api/repariers" exact component={Rep}/>
                <Route path="/api/finishedDevices" exact component={FinDev}/>
                <Route path="/api/centers" exact component={Centers}/>
                <Route path="/api/devices" exact component={Devices}/>
                <Route path="/api/newDevice" exact component={Device}/>
            </Router>
        </div>
      </Provider>
  );
}

export default App;
