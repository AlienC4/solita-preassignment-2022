import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import FarmList from './FarmList';
import FarmEdit from "./FarmEdit";

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/farms' exact={true} component={FarmList}/>
                    <Route path='/farms/:id' component={FarmEdit}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
