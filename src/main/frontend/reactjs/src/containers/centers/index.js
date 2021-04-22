import React, { useState, useEffect } from 'react';
import {Button, Form, Input, Table} from 'antd';
import './rep.css';
import '../../App.css'
import {getCenters, addCenters} from "../../store/actions/centerActions";
import Header from "../../components/header";
import {withRouter} from 'react-router-dom'
import {connect} from "react-redux";

const onMount = props => () => {
    props.getCenters()
};

const columns = [
    {
        title: 'Id',
        dataIndex: 'id',
    },
    {
        title: 'Location',
        dataIndex: 'location',
        render: text => <a>{text}</a>,
    },
    {
        title: 'Name',
        dataIndex: 'name',

    }
];

function Centers(props) {
    useEffect(onMount(props), []);

    const {isAuth} = props.authReducer;
    const {centers} = props.centerReducer;
    const [isOpen, setOpen] = useState(false);
    const [formData, setFormData] = useState({
        location: '',
        name: '',
    });
    const handleChange = e =>{
        setFormData({...formData, [e.target.name]: e.target.value})
    };

    const handleSave = () =>{
        props.addCenters(formData)
    };

    const centerList = centers.map(item => (
        {
            name: item.name,
            location: item.location
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

    const [selectionType, setSelectionType] = useState('checkbox');



    const openForm = () =>{
        if(isOpen){
            setOpen(false)
        }else {
            setOpen(true)
        }
    };

    const layout = {
        labelCol: { span: 8 },
        wrapperCol: { span: 8 },
    };
    const tailLayout = {
        wrapperCol: { offset: 8, span: 8 },
    };

    const onFinish = values => {
        console.log('Success:', values);
    };

    const onFinishFailed = errorInfo => {
        console.log('Failed:', errorInfo);
    };

    const noForm =(
        <span/>
    );

    const deviceForm =(
        <Form
            {...layout}
            name="basic"
            initialValues={{ remember: true }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
        >
            <Form.Item
                label="Location"
                name="location"
                rules={[{ required: true, message: 'Please input your location!' }]}
            >
                <Input name="location" value={formData.location} onChange={handleChange}/>
            </Form.Item>
            <Form.Item
                label="Name"
                name="name"
                rules={[{ required: true, message: 'Please input your name' }]}
            >
                <Input name="name" value={formData.name} onChange={handleChange}/>
            </Form.Item>

            <Form.Item {...tailLayout}>
                <Button type="primary" htmlType="submit" onClick={handleSave}>
                    Submit
                </Button>
            </Form.Item>
        </Form>
    );

    const isLoggedIn =(
        <div>
            <Button type="primary" onClick={openForm}>Primary</Button>
            <div>
                {isOpen ? deviceForm: noForm}
            </div>
        </div>
    );

    return (
        <div>
            <Header/>
            <div className="container-inner container">
                <div>
                    <Table
                        rowSelection={{
                            type: selectionType,
                            ...rowSelection,
                        }}
                        columns={columns}
                        dataSource={centerList}/>
                </div>
                {isAuth ? isLoggedIn: noForm}
            </div>
        </div>
    );
}

const mapStateToProps = state =>({
    centerReducer: state.centerReducer,
    authReducer:state.authReducer,
});

const mapDispatchToProps = {
    getCenters,
    addCenters,
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(withRouter(Centers))



