import React from 'react';
import ReactDOM from 'react-dom';
import { connect } from 'react-redux';
import Task from './Task';
 
class TaskListContainer extends React.Component {
	
	
	constructor(props) {
		super(props);
	}

	renderTasks = () => {
		
		if (!this.props.tasks) {
			return "Loading...";
		}
		
		return this.props.tasks.map((e) => (
							<Task task={e}/>
						));
	}
	
	render() {
		return (
					<React.Fragment>
					
						<div className="card text-center mb-2">
							<div className="card-header bg-secondary text-white">
								<h3>{this.props.headerTitle}</h3>
							</div>
						</div>
						
						{this.renderTasks()}

					</React.Fragment>
		);
	}
} 

const mapStateToProps = (store, props) => {
	
	return {
		tasks: store.tasks.filter((e) => e.status === props.taskListStatus)
	};
}

export default connect(mapStateToProps)(TaskListContainer);