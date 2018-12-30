import axios from 'axios';
import historyy from '../history';
export const registrationCreateAction = (data) => {
	return async(dispatch, getState) => {
		
		try { 
			const response = await axios.post('http://localhost:1000/registration', data);
			console.log(response);
			console.log(response.data);
			dispatch({type:'ERROR_GENERATED', payload:{} });
			historyy.push('/');
			
		}
		catch(e) {
			console.log(e);
			console.log(e.response.data);
			dispatch({type:'ERROR_GENERATED', payload:e.response.data });
		}
	}
}