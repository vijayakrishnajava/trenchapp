import React from 'react';
import ReactDOM from 'react-dom';
import {projectUpdateAction, projectGetAction} from './action/projectAction';
import { connect } from 'react-redux';

class EditProject extends React.Component {
	
	constructor(props) {
		
		super(props);
		
		this.state = {
				id: "",
				projectName: "",
				projectIdentifier: "",
				description: "",
				startDate: "",
				endDate: ""
		};
		
	}

	componentDidMount() {
		
		console.log(this.props);
		
		const id = this.props.match.params.projectid;
		console.log(id);
		this.props.projectGetAction(id);
	}
	
	onChangeEvent = (e) => {
		this.setState({ [e.target.name]: e.target.value});
	}
	
	onSubmitEvent = (e) => {
		
		e.preventDefault();
		const actionInputData = this.state ;
		console.log(actionInputData);
		this.props.projectUpdateAction(actionInputData);
		
	}
	
	componentWillReceiveProps = (nextprops) => {
		
		if (1 === 1) {
			this.setState(nextprops.project);
		}
		
		
	}
	
	render() {
		
		return (
			<React.Fragment>

    <div className="project" onSubmit={this.onSubmitEvent}>
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <h5 className="display-4 text-center">Edit Project form</h5>
                    <hr />
                    <form>
                        <div className="form-group">
                            <input type="text" name="projectName" 
							className="form-control form-control-lg "
							placeholder="Project Name"
							value={this.state.projectName}
							onChange={this.onChangeEvent}/>
                        </div>
						{(this.props.errors.projectName) && (<div>{this.props.errors.projectName}</div>)}
                        <div className="form-group">
                            <input type="text" name="projectIdentifier" className="form-control form-control-lg" 
							placeholder="Unique Project ID"
							value={this.state.projectIdentifier}
							onChange={this.onChangeEvent}/>
                        </div>
						{(this.props.errors.projectIdentifier) && (<div>{this.props.errors.projectIdentifier}</div>)}
                        <div className="form-group">
                            <textarea name="description" className="form-control form-control-lg"
							placeholder="Project Description"
							value={this.state.description}
							onChange={this.onChangeEvent}
							></textarea>
                        </div>
						{(this.props.errors.description) && (<div>{this.props.errors.description}</div>)}
                        <h6>Start Date</h6>
                        <div className="form-group">
                            <input type="date"
							className="form-control form-control-lg"
							name="startDate"
							value={this.state.startDate}
							onChange={this.onChangeEvent}/>
                        </div>
						{(this.props.errors.startDate) && (<div>{this.props.errors.startDate}</div>)}
                        <h6>Estimated End Date</h6>
                        <div className="form-group">
                            <input type="date" 
							className="form-control form-control-lg" 
							name="endDate"
							value={this.state.endDate}
							onChange={this.onChangeEvent}
							/>
                        </div>
						{(this.props.errors.endDate) && (<div>{this.props.errors.endDate}</div>)}

                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>
			
			</React.Fragment>
		);
	}
	
}

const mapStateToProp = (store, props) => {return {errors: store.errors, project: store.project}}

export default connect(mapStateToProp, {projectUpdateAction, projectGetAction})(EditProject);