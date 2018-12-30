import {TASK_LIST, TASK_CREATE, TASK_EDIT, TASK_DELETE} from '../action/taskConstants';

const defaultStore = [];

export const tasksReducer =  (oldStore = defaultStore, action) => {
	
	if(action.type === TASK_LIST) {
		return action.payload;
	}

	if(action.type === 'TASK_DELETE') {
		return oldStore.filter(e => e.projectSequnce !== action.payload.seq);
	}
	
	return oldStore;
};

export const taskReducer = (oldStore = {}, action) => {
	
	if(action.type === 'TASK_DISPLAY') {
		return action.payload;
	}
	
	return oldStore;
};