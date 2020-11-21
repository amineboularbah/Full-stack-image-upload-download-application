import logo from './logo.svg';
import './App.css';
import axios from "axios";
import React, { useState, useEffect } from "react";

const UserProfiles = () => {
  const [userProfiles, setUserProfiles] = useState([]);
  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api-v1/userProfile").then(res => {
      console.log(res);
      setUserProfiles(res.data);

    })
  }
  useEffect(() => {
    fetchUserProfiles();
  }, []);
  return userProfiles.map((user, index) => {
    return (<div key={index}>
      <h1>{user.userName}</h1>
      <p>{user.id}</p>
    </div>)

  })
}


function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
