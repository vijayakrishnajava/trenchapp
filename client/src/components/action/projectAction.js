
import axios from 'axios';
import historyy from "../history";


export const projectUpdateAction = (data) => async (dispatch, getStore) => {
	
	try{
		const response = await axios.post("http://localhost:1000/api/project", data);
		console.log(response);
		dispatch({type:'PROJECT_UPDATION', payload:response.data});
		dispatch({type:'ERROR_GENERATED', payload: {}});
		historyy.push('/dashboard');
		
	} catch(e) {
		
		console.log(e);
		console.log(e.response);
		dispatch({type:'ERROR_GENERATED', payload:e.response.data});
	}
	
}

export const projectGetAction = (id) => { 
		return async (dispatch, getStore) => {
	
	try{
		const response = await axios.get(`http://localhost:1000/api/project/${id}`);
		console.log(response);
		dispatch({type:'PROJECT_GET', payload:response.data});
		
	} catch(e) {
		
		console.log(e);
		historyy.push('/dashboard');
	}
  }
}

export const projectCreateAction = (data) => {
	 
	 return async(dispatch, getStore) => {
		 
		 let response;
		 try {
		 response  = await axios.post('httP://localhost:1000/api/project', data, {
				headers: {
							'Access-Control-Allow-Origin': '*',
							crossdomain: true 
						 }
				
			});

		 dispatch({type:'PROJECT_CREATION', payload: response.data});
		 dispatch({type:'ERROR_CLEAR'});
		 historyy.push('/dashboard');

		}
		 
		 catch(e) {
			 console.log(e);
			 console.log(e.response.data);
			 dispatch({type:'ERROR_GENERATED', payload: e.response.data});
		 }
		 
	 }
 } 
 
 
export const projectListAction = () => {
	 
	 return async(dispatch, getStore) => {
		 
		 const response  = await axios.get('httP://localhost:1000/api/project', {
				headers: {
							'Access-Control-Allow-Origin': '*',
							crossdomain: true 
						 }
			});
		 
		 console.log(response);
		 
		 dispatch({type:'PROJECT_LIST', payload: response.data
		 
		 
		 });
	 }
 } 

export const projectDelete = (id) => {
	return async (dispatch) => {
		
		try {
			const response = await axios.delete(`http://localhost:1000/api/project/${id}`);
			console.log(response);
			dispatch({type:'PROJECT_DELETE', payload: id});
		} catch(e) {
			console.log(e);
			historyy.push('/dashboard');
		}
	}
} 
