import {ADD_DEVICES, GET_DEVICES, SIGN_UP} from './types'
import axios from 'axios'

export const getDevices = () => dispatch =>{
    axios.get('/api/devices')
        .then(res => {
            console.log("Response: ", res, res.data)
            dispatch({
                type: GET_DEVICES,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const addDevice = (device) => dispatch =>{
    axios.post('/api/devices', device)
        .then(res => {
            dispatch({
                type: ADD_DEVICES,
                payload: res.data
            });
        })
        .catch(err => console.log(err))
};