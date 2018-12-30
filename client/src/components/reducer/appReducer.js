export const taskStatusListReducer = (oldStore = [], action) => {
	if (action.type === 'TASK_STATUS_LIST') {
		return action.payload;
	}
	return oldStore;
} 

export const taskPriorityListReducer = (oldStore = [], action) => {
	if (action.type === 'TASK_PRIORITY_LIST') {
		return action.payload;
	}
	return oldStore;
} 

