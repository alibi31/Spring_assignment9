
import {ADD_CENTERS, GET_CENTERS} from '../actions/types'


const initialState = {
    centers: [],
};

export default function (state=initialState, action){
    if (action.type === GET_CENTERS) {
        return {
            ...state,
            centers: action.payload
        }
    }else  if (action.type === ADD_CENTERS) {
        return {
            ...state,
            centers: [...state.centers, action.payload]
        }
    }
    else {
        return state;
    }
}
