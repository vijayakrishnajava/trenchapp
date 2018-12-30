import axios from 'axios';
import historyy from '../history';
import setJwtToken from '../setJwtToken';
import jwt_decode from 'jwt-decode';

export const loginAction = (data) => {
	
	return async (dispatch, getState) => {
		try {
			const response = await axios.post('http://localhost:1000/login', data);
			console.log(response);
			console.log(response.data);
			
			if (response.data.sucess) {
				
				//get the token
				const { jwttoken } = response.data;
				const { sucess } = response.data;
				
				//store the token in localStorage
				localStorage.setItem('jwtToken', jwttoken);
				
				//store the token in axios header for furtur requests
				setJwtToken(jwttoken);
				
				//extract the userdata from token
				console.log(jwttoken);
				const decodedtoken = jwt_decode(jwttoken);
				console.log(decodedtoken);
				
				dispatch({type:'LOGIN', payload:{sucess, decodedtoken}});
				dispatch({type:'ERROR_GENERATED', payload:{}});
				historyy.push('/dashboard');
			} else {
				historyy.push('/');
			}
		} catch(e) {
			console.log(e);
			if(e.response) {
				console.log(e.response);
				if (e.response.data) {
					console.log(e.response.data);
					dispatch({type:'ERROR_GENERATED', payload:e.response.data});
				} else {
					historyy.push('/');
				}
			}
		}
	}
}

export const logoutAction = (data) => {
	
	return (dispatch, getState) => {
		localStorage.removeItem('jwtToken');
		dispatch({type:'LOGOUT'});
		window.location.replace('http://localhost:3000/');
	}
}

export const sessionLoadAction = () => {
	
	return async (dispatch) => {
		const token = localStorage.getItem('jwtToken');
		if (token) {
			//get the token
			//validate the token
			try {
				const response = await axios.post('http://localhost:1000/validate', {token});
				console.log(response.data);
					//if valid
					if(response.data.status) {
						
						const sucess = response.data.status;
						//update axios header state with token
						setJwtToken(token);
						//decode the token
						const decodedtoken = jwt_decode(token);
						//update the store with token decode info
						dispatch({type:'LOGIN', payload:{sucess, decodedtoken}});
					} else {

					//if not valid
						//check error and remove localStore token
						localStorage.removeItem('jwtToken');
						//redirect to main page for login/signup page
						historyy.push('/');
					}
			} catch(e){
				console.log(e);
			}
		} 
	}
}