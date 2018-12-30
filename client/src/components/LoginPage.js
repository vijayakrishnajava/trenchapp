import React from 'react';
import ReactDOM from 'react-dom';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { loginAction } from './action/loginAction';

class LoginPage extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			username:'',
			password:''
		};
	}
	
	onChangeEvent = (e) => {
		this.setState({[e.target.name]:e.target.value});
	}
	
	onSubmitEvent = (e) => {
		e.preventDefault();
		console.log(this.state);
		this.props.loginAction(this.state);
	}
	
	render() {
		
		return (
		
			<div className="login">
				<div className="container">
					<div className="row">
						<div className="col-md-8 m-auto">
							<h1 className="display-4 text-center">Log In</h1>
							<form onSubmit={this.onSubmitEvent}>
								<div className="form-group">
									<input type="email" className="form-control form-control-lg"
									placeholder="username or Email Address" name="username" required
									onChange={this.onChangeEvent}/>
								</div>
								{ this.props.errors.username && (<div>{this.props.errors.username}</div>)}
								<div className="form-group">
									<input type="password" className="form-control form-control-lg"
									placeholder="Password" name="password"  required
									onChange={this.onChangeEvent}/>
								</div>
								{ this.props.errors.password && (<div>{this.props.errors.password}</div>)}
								<input type="submit" className="btn btn-info btn-block mt-4" />
							</form>
						</div>
					</div>
				</div>
			</div>	
		);
		
	}
	
}

const mapStateToProps = (store, props) => {
	return {
		errors: store.errors
	};
}
export default connect(mapStateToProps, {loginAction})(LoginPage);