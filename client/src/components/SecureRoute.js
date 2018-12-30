import React from "react";
import { connect } from "react-redux";
import { Route, Redirect } from "react-router-dom";

const SecureRoute = ({ component: Component, login, ...otherProps }) => {
  return (
    <Route
      {...otherProps}
      render={props => {
        console.log(login.sucess);
        return login.sucess ? (
          <Component {...props} />
        ) : (
          <Redirect to="login" />
        );
      }}
    />
  );
};

const mapStateToProps = state => {
  return {
    login: state.login
  };
};

export default connect(mapStateToProps)(SecureRoute);
