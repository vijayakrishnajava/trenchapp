import React from 'react';
import ReactDOM from 'react-dom';
import { connect } from 'react-redux';
import {taskListAction} from './action/taskAction';
import TaskListContainer from './TaskListContainer';
import { Link } from 'react-router-dom';


class TaskDashboard extends React.Component {
	
	
	constructor(props){
		super(props);
	}
	
	componentDidMount() {

		console.log(this.props.match.params.identifier);
		this.props.taskListAction(this.props.match.params.identifier);
		
	}
	
	render() {

		return (
			<div className="container">
			<Link to={`/addTask/${this.props.match.params.identifier}`} className="btn btn-primary mb-3">
				<i className="fas fa-plus-circle"> Create Project Task</i>
			</Link>
			<br />
			<hr />
			<div className="container">
				<div className="row">
					<div className="col-md-4">
						<TaskListContainer headerTitle="TO DO" taskListStatus="TO_DO"/>
					</div>
					<div className="col-md-4">
						<TaskListContainer headerTitle="In Progress" taskListStatus="IN_PROGRESS"/>
					</div>
					<div className="col-md-4">
						<TaskListContainer headerTitle="Done" taskListStatus="DONE"/>
					</div>
				</div>
			</div>
			</div>

		);
	}
	
}


export default connect(null, {taskListAction})(TaskDashboard);