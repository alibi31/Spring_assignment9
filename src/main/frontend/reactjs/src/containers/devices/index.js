import React, { useState, useEffect } from 'react';
import { Table } from 'antd';
import './rep.css';
import '../../App.css'
import { Form, Input, Button, Select } from 'antd';
import {getDevices, addDevice} from "../../store/actions/deviceActions";
import {finishDevice} from "../../store/actions/finishedDevices";
import {getReparierByName, getRepariers} from "../../store/actions/repariers";
import Header from "../../components/header";
import {withRouter} from 'react-router-dom'
import {connect} from "react-redux";

const onMount = props => () => {
    props.getDevices();
    props.getRepariers()

};
const {Option} = Select;


function Devices(props) {

    const {devices} = props.deviceReducer;
    const {repariers} = props.reparierReducer;
    let rep = null;
    const reparier = repariers.map(item =>
        {
            if (item.username === props.authReducer.username){
                rep = item;
                return item
            }
        });

    const columns = [
        {
            title: 'Device owner',
            dataIndex: 'deviceOwnerName',
        },
        {
            title: 'Device owner phone',
            dataIndex: 'deviceOwnerPhone',
        },{
            title: 'Explanation',
            dataIndex: 'explanation',
        },{
            title: 'Manufacturer',
            dataIndex: 'manufacturer',
        },{
            title: 'Year',
            dataIndex: 'year',
        },
        {
            title: 'Is Done',
            dataIndex: 'isDone',
        },
        {
            title: 'Action',
            dataIndex: 'id',
            key: 'id',
            render: (id) => (
                <span
                    onClick={() => props.finishDevice(id, rep.id)}
                >
            Finish
          </span>
            ),
        },
    ];

    const deviceList = devices.map(item => (
        {

            deviceOwnerName: item.deviceOwnerName,
            deviceOwnerPhone: item.deviceOwnerPhone,
            explanation: item.explanation,
            manufacturer: item.manufacturer,
            isDone: String(item.isDone),
            year: item.year,
            id: item.id

        }
    ));
    const rowSelection = {
        onChange: (selectedRowKeys, selectedRows) => {
            console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
        },
        getCheckboxProps: record => ({
            disabled: record.name === 'Disabled User', // Column configuration not to be checked
            name: record.name,
        }),
    };


    useEffect(onMount(props), []);
    return (
        <div>
            <Header/>
            <div className="container-inner container">
                <div>
                    <Table
                        rowSelection={{
                            ...rowSelection,
                        }}
                        columns={columns}
                        dataSource={deviceList}/>
                </div>
                {console.log(rep)}
            </div>
        </div>
    );
}

const mapStateToProps = state =>({
    deviceReducer: state.deviceReducer,
    authReducer: state.authReducer,
    finishedDevicesReducer:state.finishedDevicesReducer,
    reparierReducer:state.reparierReducer,
});

const mapDispatchToProps = {
    getDevices,
    addDevice,
    finishDevice,
    getRepariers,
    getReparierByName,
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(withRouter(Devices))