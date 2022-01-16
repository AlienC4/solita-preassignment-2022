import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class FarmList extends Component {
    baseUrl = "http://localhost:8080"
    constructor(props) {
        super(props);
        this.state = {farms: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch(this.baseUrl + '/farms', {
            headers: {
                'Access-Control-Allow-Origin': '*',
            }
        })
            .then(response => response.json())
            .then(data => this.setState({farms: data}));
    }

    async remove(id) {
        await fetch(`${this.baseUrl}/farms/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
            }
        }).then(() => {
            let updatedClients = [...this.state.farms].filter(i => i.id !== id);
            this.setState({farms: updatedClients});
        });
    }

    render() {
        const {farms} = this.state;

        const clientList = farms.map(client => {
            return <tr key={client.id}>
                <td style={{whiteSpace: 'nowrap'}}>{client.name}</td>
                <td>{client.email}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/farms/" + client.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(client.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/farms/new">Add Client</Button>
                    </div>
                    <h3>Clients</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Name</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {clientList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default FarmList;