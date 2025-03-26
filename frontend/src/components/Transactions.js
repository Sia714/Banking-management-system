import React, { useEffect, useState } from "react";
import TransactionService from "../services/TransactionService";

const Transactions = ({ role }) => {
  const [transactions, setTransactions] = useState([]);
  const userId = 22; // Simulating a logged-in user ID

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response =
          role === "admin"
            ? await TransactionService.getAllTransactions()
            : await TransactionService.getTransactionsByUser(userId);
            console.log("Fetched Transactions:", response.data); // Debugging
            setTransactions(response.data);
          } catch (error) {
            console.error("Error fetching transactions:", error);
          }
        };
      
        fetchData();
      }, [role]);

  return (
    <div>
      <h2>{role === "admin" ? "All Transactions" : "My Transactions"}</h2>
      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>From Account</th>
            <th>To Account</th>
            <th>Amount</th>
            <th>Type</th>
            <th>Timestamp</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((txn) => (
            <tr key={txn.id}>
              <td>{txn.id}</td>
              <td>{txn.fromAccount ? txn.fromAccount.id : "N/A"}</td>
              <td>{txn.toAccount ? txn.toAccount.id : "N/A"}</td>
              <td>{txn.amount}</td>
              <td>{txn.transactionType}</td>
              <td>{new Date(txn.timestamp).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Transactions;
