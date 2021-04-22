import {GET_REPARIERS,GET_REPARIERS_BY_NAME} from './types'
import axios from 'axios'

export const getRepariers = () => dispatch =>{
    axios.get('/api/repariers')
        .then(res => {
            dispatch({
                type: GET_REPARIERS,
                payload: res.data
            })
        })
        .catch(err => console.log(err))
};

export const getReparierByName = username => dispatch =>{
    console.log("heeeeey" + username);
    axios.get(`/api/repariers/username/${username}`)
        .then(res => {
            dispatch({
                type: GET_REPARIERS_BY_NAME,
                payload: res.data
            })
        })
        .catch(err => console.log(err.msg))
};

export const deleteRepariers = (id) => dispatch =>{
    axios.delete(`/api/repariers/${id}`)
        .then(res => {
            dispatch({
                type: GET_REPARIERS,
                payload: id
            })
        })
        .catch(err => console.log(err))
};