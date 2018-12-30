
const oldStoreData = {}

const errorReducer = (oldStore = oldStoreData, action) => {
	
	if (action.type === 'ERROR_GENERATED') {
		return action.payload;
	}

	if (action.type === 'ERROR_CLEAR') {
		return oldStoreData;
	}	
	return oldStore;
}

export default errorReducer;