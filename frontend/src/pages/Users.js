import React, { useEffect, useState } from "react";

function Users() {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/banking_system/users") // Admin sees all users
      .then((res) => res.json())
      .then((data) => setData(data))
      .catch((err) => console.error("Error fetching users:", err));
  }, []);

  return (
    <div>
      <h1>All Users (Admin Only)</h1>
      {data ? data.map((user) => <p key={user.id}>{user.name}</p>) : "Loading..."}
    </div>
  );
}

export default Users;
