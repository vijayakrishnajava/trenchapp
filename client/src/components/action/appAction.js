import axios from 'axios';

export const taskStatusListAction = () => {
	return async(dispatch, getStore) => {
		
		const response = await axios.get('http://localhost:1000/api/tasks/statuses');
		console.log(response);

		dispatch({type:'TASK_STATUS_LIST', payload: response.data});
	}	
}


export const taskPriorityListAction = () => {
	return async(dispatch, getStore) => {
		
		const response = await axios.get('http://localhost:1000/api/tasks/priorities');
		console.log(response);

		dispatch({type:'TASK_PRIORITY_LIST', payload: response.data});
	}	
}
