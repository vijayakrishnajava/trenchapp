import React from 'react';
import ReactDOM from 'react-dom';
import Project from './Project';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { projectListAction } from './action/projectAction';


class Dashboard extends React.Component {
	
	componentDidMount() {
		
		this.props.projectListAction();
		
	}
	
	
	renderProjects = () => {
	
	if (!this.props.projects || this.props.projects.length == 0) {
		return <div>Loading...</div>;
	}
		
	return this.props.projects.map((project) => {
		
			return (
					<Project project={project}/>
			
			);
		
		});
	}
	
	render() {
		
		
		
		
		return (
			    <div className="projects">
					<div className="container">
						<div className="row">
							<div className="col-md-12">
								<h1 className="display-4 text-center">Projects</h1>
								<br />
								<Link to="addProject" className="btn btn-lg btn-info">
									Create a Project
								</Link>
								<br />
								
								{this.renderProjects()}

							</div>
						</div>
					</div>
				</div>
		);
	}
	
}

const mapStoreToProps = (store, props) => {
	return {projects: store.projects}
}

export default connect(mapStoreToProps, {projectListAction})(Dashboard);