import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import Home from "./pages/Home";
import Users from "./pages/Users";
import Transactions from "./components/Transactions";
import TransactionForm from "./components/TransactionForm";

function App() {
  const [role, setRole] = useState("user");

  return (
    <Router>
      <nav>
        <Link to="/">Home</Link> | 
        {role === "admin" && <Link to="/users">Users</Link>} | 
        <Link to="/transactions">Transactions</Link>
      </nav>

      <button onClick={() => setRole("admin")}>Set as Admin</button>
      <button onClick={() => setRole("user")}>Set as User</button>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/transactions" element={<Transactions role={role} />} />
        {role === "admin" && <Route path="/users" element={<Users />} />}
      </Routes>

      <h1>Banking System</h1>
      <TransactionForm role={role} />
    </Router>
  );
}

export default App;
