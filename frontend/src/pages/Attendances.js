import React, { Component } from 'react';

class AttendancesPage extends Component {
  state = {
    clients: []
  };

  async componentDidMount() {
    const response = await fetch('/attendance');
    const body = await response.json();
    this.setState({clients: body});
  }

  render() {
    const {clients} = this.state;
    return (
            <div className="App">
              <header className="App-header">
                <div className="App-intro">
                  <h2>Anwesenheiten</h2>
                  {clients.map(client =>
                      <div key={client.id}>
                        {client.id} / {client.user.username} ({client.date})
                      </div>
                  )}
                </div>
              </header>
            </div>
        );
      }
    }
export default AttendancesPage;