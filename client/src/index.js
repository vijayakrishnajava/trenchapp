//import the react and react dom libraries
import React from "react";
import ReactDOM from "react-dom";
import App from "./components/App";
import { combineReducers, createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import thunk from 'redux-thunk';
import { projectReducer, projectsReducer} from "./components/reducer/projectReducer";
import errorReducer from "./components/reducer/errorReducer";
import { tasksReducer,taskReducer } from "./components/reducer/TaskReducer";
import {taskStatusListReducer,taskPriorityListReducer} from './components/reducer/appReducer';
import {loginReducer} from './components/reducer/loginReducer';

const reducers = combineReducers({
					projects: projectsReducer,
					errors: errorReducer,
					project: projectReducer,
					tasks: tasksReducer,
					taskStatusList: taskStatusListReducer,
					taskPriorityList: taskPriorityListReducer,
					task:taskReducer,
					login:loginReducer
				});
				
const store = createStore(reducers, applyMiddleware(thunk));

ReactDOM.render(

	<Provider store= {store}>
		<App/>
	</Provider>
	,
	document.querySelector('#root')
);