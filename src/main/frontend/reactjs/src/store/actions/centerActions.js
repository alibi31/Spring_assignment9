import {ADD_CENTERS, ADD_DEVICES, GET_CENTERS} from './types'
import axios from 'axios'

export const getCenters = () => dispatch =>{
    axios.get('/api/centers')
        .then(res => {
            console.log("Response: ", res, res.data)
            dispatch({
                type: GET_CENTERS,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const addCenters = (centre) => dispatch =>{
    axios.post('/api/centers', centre)
        .then(res => {
            dispatch({
                type: ADD_CENTERS,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};
