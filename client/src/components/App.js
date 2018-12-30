import React from "react";
import ReactDOM from "react-dom";
import Navbar from "./layout/Navbar";
import { Router, Route, Switch } from "react-router-dom";
import Dashboard from "./Dashboard";
import AddProject from "./AddProject";
import EditProject from "./EditProject";
import TaskDashboard from "./TaskDashboard";
import historyy from "./history";
import AddTask from "./AddTask";
import EditTask from "./EditTask";
import RegistrationCreate from "./RegistrationCreate";
import LandingPage from "./LandingPage";
import LoginPage from "./LoginPage";
import { connect } from "react-redux";
import { sessionLoadAction } from "./action/loginAction";
import SecureRoute from "./SecureRoute";

class App extends React.Component {
  componentDidMount() {
    this.props.sessionLoadAction();
  }

  render() {
    return (
      <div>
        <Router history={historyy}>
          <div>
            <Navbar />
            <Route exact path="/" component={LandingPage} />
            <Route path="/createRegistration" component={RegistrationCreate} />
            <Route path="/login" component={LoginPage} />

            <Switch>
              <SecureRoute path="/dashboard" component={Dashboard} />
              <SecureRoute path="/addProject" component={AddProject} />
              <SecureRoute
                path="/editProject/:projectid"
                component={EditProject}
              />
              <SecureRoute
                path="/taskDashboard/:identifier"
                component={TaskDashboard}
              />
              <SecureRoute path="/addTask/:identifier" component={AddTask} />
              <SecureRoute
                path="/editTask/:identifier/:seq"
                component={EditTask}
              />
            </Switch>
          </div>
        </Router>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    login: state.login
  };
};

export default connect(
  mapStateToProps,
  { sessionLoadAction }
)(App);
