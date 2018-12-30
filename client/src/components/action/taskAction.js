import axios from 'axios';
import {TASK_LIST, TASK_CREATE, TASK_EDIT, TASK_DELETE} from './taskConstants';
import historyy from '../history';

export const taskListAction = (identifier) => {
	
	return async (dispatch, getState) => {
		const response = await axios.get(`http://localhost:1000/api/tasks/${identifier}`);
		console.log(response);
		console.log(response.data);
		dispatch({type:TASK_LIST, payload: response.data});
	};

}

export const taskCreateAction = (data) => {
	
	return async (dispatch, getState) => {
		
		try {
	
			const response = await axios.post(`http://localhost:1000/api/tasks/${data.identifier}`, data);
			console.log(response);
			console.log(response.data);
			dispatch({type:'ERROR_GENERATED', payload:{}});

			historyy.push(`/taskDashboard/${data.identifier}`);
		} catch(e) {

			console.log(e);
			console.log(e.response.data);
			dispatch({type:'ERROR_GENERATED', payload:e.response.data});
		
		}

	};
}


export const taskGetAction = (identifier, seq) => {
	
	return async (dispatch, getState) => {
		
		try {
			const response = await axios.get(`http://localhost:1000/api/tasks/${identifier}/${seq}`);
			console.log(response);
			console.log(response.data);
			dispatch({type:'TASK_DISPLAY', payload:response.data});
		} catch(e) {

			console.log(e);
			console.log(e.response.data);
			historyy.push('/');		
		}

	};
}


export const taskEditAction = (data, identifier, seq) => {
	
	return async (dispatch, getState) => {
		
		try {
	
			const response = await axios.put(`http://localhost:1000/api/tasks/${identifier}/${seq}`,
			data);
			console.log(response);
			console.log(response.data);
			dispatch({type:'ERROR_GENERATED', payload:{}});

			historyy.push(`/taskDashboard/${identifier}`);
		} catch(e) {

			console.log(e);
			console.log(e.response.data);
			dispatch({type:'ERROR_GENERATED', payload:e.response.data});
		
		}

	};
}


export const taskDeleteAction = (identifier, seq) => {
	
	return async (dispatch, getState) => {
		
		try {
	
			const response = await axios.delete(`http://localhost:1000/api/tasks/${identifier}/${seq}`);
			console.log(response);
			console.log(response.data);
			dispatch({type:'TASK_DELETE', payload:{identifier, seq}});

		} catch(e) {

			console.log(e);
			console.log(e.response.data);
			historyy.push(`/taskDashboard/${identifier}`);
		
		}

	};
}


