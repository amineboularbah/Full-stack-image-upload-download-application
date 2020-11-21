import './App.css';
import axios from "axios";
import React, { useState, useEffect, useCallback } from "react";
import { useDropzone } from 'react-dropzone'

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
      {user.id ? <img className="image" src={`http://localhost:8080/api-v1/userProfile/${user.id}/image/download`}></img> : null }
      <br /><br />
      <h1>{user.userName}</h1>
      <p>{user.id}</p>
      <MyDropzone id={user.id} />
      <br />
    </div>)

  })
}

function MyDropzone({ id }) {
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file);
    const formData = new FormData();
    formData.append("file", file);
    axios.post(`http://localhost:8080/api-v1/userProfile/${id}/image/upload`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }).then(() => {
        window.location.reload(false);
      }).catch(err => {
        console.log(err)
      })
  }, [])
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop })

  return (
    <div {...getRootProps()} className="image-area">
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the image here ...</p> :
          <p>Drag 'n' drop some files here, or click to select profile image</p>
      }
    </div>
  )
}


function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
