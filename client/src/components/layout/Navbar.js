import React from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { logoutAction } from '../action/loginAction';

const renderLoginoutbutton = (props) => {
		
		if (props.login && props.login.sucess) {
			
						return (<ul className="navbar-nav ml-auto">
							<li className="nav-item">
							{props.login.decodedtoken.fullname}
							</li>
							
							<li className="nav-item">
								<button className="nav-link" onClick={() => {props.logoutAction()}}>
									Logout
								</button>
							</li>
						</ul>);

		} else {
			
						return (<ul className="navbar-nav ml-auto">
							<li className="nav-item">
								<Link className="nav-link " to="/createRegistration">
									Sign Up
								</Link>
							</li>
							<li className="nav-item">
								<Link className="nav-link" to="/login">
									Login
								</Link>
							</li>
						</ul>);

		}
}

const Navbar = (props) => {
	
	return (
	
			<nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
				<div className="container">
					<Link className="navbar-brand" to="/">
						Personal Project Management Tool
					</Link>
					<button className="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#mobile-nav">
						<span className="navbar-toggler-icon" />
					</button>

					<div className="collapse navbar-collapse" id="mobile-nav">
						{props.login && props.login.sucess && (<ul className="navbar-nav mr-auto">
							<li className="nav-item">
								<Link className="nav-link" to="/dashboard">
									Dashboard
								</Link>
							</li>
						</ul>)}
						{renderLoginoutbutton(props)}
					</div>
				</div>
			</nav>
	
	);
	
	
}

const mapStateToProps = (state) => {
	return {
		login: state.login
	};
} 
export default connect(mapStateToProps, {logoutAction})(Navbar);