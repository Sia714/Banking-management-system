import React, { useState } from "react";
import TransactionService from "../services/TransactionService";

const TransactionForm = () => {
  const [fromAccountId, setFromAccountId] = useState("");
  const [toAccountId, setToAccountId] = useState("");
  const [amount, setAmount] = useState("");
  const [transactionType, setTransactionType] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    console.log("Transaction Data Being Sent:", {
      fromAccountId,
      toAccountId,
      amount,
      transactionType
    });

    try {
      let response;
      if (transactionType === "WITHDRAW") {
        response = await TransactionService.withdraw(fromAccountId, amount);
      } else if (transactionType === "DEPOSIT") {
        response = await TransactionService.deposit(toAccountId, amount);
      } else if (transactionType === "TRANSFER") {
        response = await TransactionService.transfer(fromAccountId, toAccountId, amount);
      }

      console.log("Transaction Success:", response.data);
    } catch (error) {
      console.error("Error processing transaction:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        From Account ID:
        <input
          type="number"
          value={fromAccountId}
          onChange={(e) => setFromAccountId(e.target.value)}
          required
          disabled={transactionType === "DEPOSIT"}
        />
      </label>
      <br />
      <label>
        To Account ID:
        <input
          type="number"
          value={toAccountId}
          onChange={(e) => setToAccountId(e.target.value)}
          required
          disabled={transactionType === "WITHDRAW"}
        />
      </label>
      <br />
      <label>
        Amount:
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          required
        />
      </label>
      <br />
      <label>
        Transaction Type:
        <select
          value={transactionType}
          onChange={(e) => setTransactionType(e.target.value)}
          required
        >
          <option value="">Select Type</option>
          <option value="TRANSFER">Transfer</option>
          <option value="WITHDRAW">Withdraw</option>
          <option value="DEPOSIT">Deposit</option>
        </select>
      </label>
      <br />
      <button type="submit">Submit Transaction</button>
    </form>
  );
};

export default TransactionForm;
