import React from 'react';
import ReactDOM from 'react-dom';
import { connect } from 'react-redux';
import { registrationCreateAction } from './action/registrationAction';

class RegistrationCreate extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			fullName:'',
			username:'',
			password:'',
			confirmPassword:''
		}
	}
	
	componentDidMount() {
		
	}
	
	onChangeEvent = (e) => {
		this.setState({[e.target.name]:e.target.value});
	}
	
	onSubmitEvent = (e) => {
		e.preventDefault();
		console.log(this.state);
		this.props.registrationCreateAction(this.state);
	}
	
	
	render() {
		
		return (
		
		 <div className="register">
				<div className="container">
					<div className="row">
						<div className="col-md-8 m-auto">
							<h1 className="display-4 text-center">Sign Up</h1>
							<p className="lead text-center">Create your Account</p>
							<form onSubmit={this.onSubmitEvent}>
								<div className="form-group">
									<input type="text" className="form-control form-control-lg"
									placeholder="Name" name="fullName" value={this.state.fullName} onChange={this.onChangeEvent}
										required />
								</div>
								{this.props.errors.fullName && (<div>{this.props.errors.fullName}</div>)}

								<div className="form-group">
									<input type="email" className="form-control form-control-lg"
									placeholder="Email Address" name="username" 
									value={this.state.username} onChange={this.onChangeEvent}/>
								</div>
								{this.props.errors.username && (<div>{this.props.errors.username}</div>)}

								<div className="form-group">
									<input type="password" className="form-control form-control-lg"
									placeholder="Password" name="password"
									value={this.state.password} onChange={this.onChangeEvent}/>
								</div>
								{this.props.errors.password && (<div>{this.props.errors.password}</div>)}

								<div className="form-group">
									<input type="password" className="form-control form-control-lg"
									placeholder="Confirm Password"
										name="confirmPassword"
									value={this.state.confirmPassword} onChange={this.onChangeEvent}/>
								</div>
								{this.props.errors.confirmPassword && (<div>{this.props.errors.confirmPassword}</div>)}

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
	}
}

export default connect(mapStateToProps, {registrationCreateAction})(RegistrationCreate);