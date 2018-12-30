import React from 'react';
import ReactDOM from 'react-dom';
import { connect } from 'react-redux';
import { taskCreateAction } from './action/taskAction';
import {taskStatusListAction,taskPriorityListAction} from './action/appAction';



class AddTask extends React.Component {

	constructor(props) {
		super(props);
		
				this.props.taskStatusListAction();
		this.props.taskPriorityListAction();

		
		
		this.state = {
			summary:'',			
			acceptanceCriteria:'',
			dueDate:'',
			priority:'0',
			status:'',
			errors:{}
		};
	}
	
	componentDidMount() {
		console.log(this.props.match.params.identifier);
	}
	
	onChangeEvent = (e) => {
		e.preventDefault();
		this.setState({[e.target.name]:e.target.value});
	}
	
	onSubmitEvent = (e) => {
		e.preventDefault();
		var {summary, acceptanceCriteria, dueDate,priority, status} = this.state;
		console.log({summary, acceptanceCriteria, dueDate,priority, status});
		this.props.taskCreateAction({summary, acceptanceCriteria, dueDate,priority, status,
		'identifier':this.props.match.params.identifier});
	}
	
	renderStatusList() {
		if(this.props.taskStatusList) {
			return this.props.taskStatusList.map( e => (<option value={e[0]}>{e[1]}</option>));
		}
		
	}
	
	renderPriorityList() {
		if(this.props.taskPriorityList) {
			return this.props.taskPriorityList.map( e => (<option value={e[0]}>{e[1]}</option>));
		}
		
	}

	componentWillReceiveProps(newprops) {
		this.setState({['errors']:newprops.errors});
	}
	
	render() {
		return (
		
	<div className="add-PBI">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <a href="#" className="btn btn-light">
                        Back to Project Board
                    </a>
                    <h4 className="display-4 text-center">Add /Update Project Task</h4>
                    <p className="lead text-center">Project Name + Project Code</p>
                    <form onSubmit={this.onSubmitEvent}>
                        <div className="form-group">
                            <input type="text" className="form-control form-control-lg"
							name="summary" placeholder="Project Task summary"
							value={this.state.summary} onChange={this.onChangeEvent}/>
                        </div>
						{this.state.errors && (<div className="error">{this.state.errors.summary}</div>)}
                        <div className="form-group">
                            <textarea className="form-control form-control-lg"
							placeholder="Acceptance Criteria" name="acceptanceCriteria"
							value={this.state.acceptanceCriteria} onChange={this.onChangeEvent}></textarea>
                        </div>
						{this.state.errors && (<div className="error">{this.state.errors.acceptanceCriteria}</div>)}
                        <h6>Due Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg" name="dueDate" 
							value={this.state.dueDate} onChange={this.onChangeEvent}/>
                        </div>
						{this.state.errors && (<div className="error">{this.state.errors.dueDate}</div>)}
                        <div className="form-group">
                            <select className="form-control form-control-lg" name="priority"
							value={this.state.priority} onChange={this.onChangeEvent}>
                                <option value="0">Select Priority</option>
								{this.renderPriorityList()}
                            </select>
                        </div>
						{this.state.errors && (<div className="error">{this.state.errors.priority}</div>)}

                        <div className="form-group">
                            <select className="form-control form-control-lg" name="status"
							value={this.state.status} onChange={this.onChangeEvent}>
                                <option value="">Select Status</option>
								{this.renderStatusList()}
                            </select>
                        </div>

                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>

		);
	}
	
}

const mapStateTOProps = (store, props) => {
	return {
		taskPriorityList:Object.entries(store.taskPriorityList),
		taskStatusList:Object.entries(store.taskStatusList),
		errors: store.errors
	}
}

export default connect(mapStateTOProps, {taskCreateAction,taskStatusListAction,taskPriorityListAction})(AddTask);