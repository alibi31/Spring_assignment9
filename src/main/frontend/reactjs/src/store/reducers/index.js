
import {combineReducers} from 'redux'


import reparierReducer from "./reparierReducer";
import authReducer from "./authReducer";
import finishedDevicesReducer from "./finishedDevicesReducer";
import centerReducer from "./centerReducer";
import deviceReducer from "./deviceReducer";

export default combineReducers({
    reparierReducer: reparierReducer,
    authReducer:authReducer,
    finishedDevicesReducer:finishedDevicesReducer,
    centerReducer:centerReducer,
    deviceReducer:deviceReducer,
})