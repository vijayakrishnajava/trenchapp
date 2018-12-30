import React from 'react';
import ReactDOM from 'react-dom';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { taskDeleteAction } from './action/taskAction';

const Task = (props) => {
	
	
	return (
						<div className="card mb-1 bg-light">

							<div className="card-header text-primary">
								ID: {props.task.projectSequnce} -- Priority: {props.task.priority}
							</div>
							<div className="card-body bg-light">
								<h5 className="card-title">{props.task.summary}</h5>
								<p className="card-text text-truncate ">
									{props.task.acceptanceCriteria}
								</p>
								<Link to={`/editTask/${props.task.projectIdentifier}/${props.task.projectSequnce}`} className="btn btn-primary">
									View / Update
								</Link>

								<button onClick={(e) => props.taskDeleteAction(props.task.projectIdentifier, props.task.projectSequnce)} className="btn btn-danger ml-4">
									Delete
								</button>
							</div>
						</div>
			);
	
}

export default connect(null, {taskDeleteAction})(Task);