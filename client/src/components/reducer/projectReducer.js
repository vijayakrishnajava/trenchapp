export const projectsReducer = (oldProjects = [], action) => {
	
	switch(action.type) {
		case 'PROJECT_CREATION':
			return [...oldProjects, action.payload];
		case 'PROJECT_UPDATION':
			const index = oldProjects.findIndex(project => project.id === action.payload.id);
			oldProjects[index] = action.payload;
			return  [...oldProjects];
		case 'PROJECT_LIST':
			return action.payload;
		case 'PROJECT_DELETE':
		console.log(action);
			const temp = oldProjects.filter(project => project.projectIdentifier !== action.payload);
			console.log(temp);
			return temp;
			
	default:
			return oldProjects;
			
	}
	

}

export const projectReducer = (oldProject = {}, action) => {
	
	if (action.type === 'PROJECT_GET') {
		return action.payload;
	}
	
	return oldProject;
} 