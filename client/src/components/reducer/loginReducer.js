
const defaultStore = {sucess:false}

export const loginReducer = (oldStore = defaultStore, action) => {
	if (action.type === 'LOGIN') {
		return action.payload;
	}
	if (action.type === 'LOGOUT') {
		return defaultStore;
	}
	
	return oldStore;
}