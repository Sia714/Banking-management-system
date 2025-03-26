import axios from "axios";

const API_URL = "http://localhost:8080/banking_system/transactions";

const TransactionService = {
  withdraw: (accountId, amount) =>
    axios.post(`${API_URL}/withdraw`, null, { params: { accountId, amount } }),

  deposit: (accountId, amount) =>
    axios.post(`${API_URL}/deposit`, null, { params: { accountId, amount } }),

  transfer: (fromAccountId, toAccountId, amount) =>
    axios.post(`${API_URL}/transfer`, null, { params: { fromAccountId, toAccountId, amount } }),

  getAllTransactions: () => axios.get(`${API_URL}`),

  getTransactionById: (id) => axios.get(`${API_URL}/${id}`),

  getTransactionsByUser: (userId) => axios.get(`${API_URL}/user/${userId}`),

  deleteTransaction: (id) => axios.delete(`${API_URL}/${id}`),

  deleteAllTransactions: () => axios.delete(`${API_URL}/removeAll`),

  getTransactionsByMinAmount: (amount) => axios.get(`${API_URL}/minAmount/${amount}`),

  getTransactionsByTimeRange: (start, end) =>
    axios.get(`${API_URL}/time`, {
      params: { start, end },
    }),
};

export default TransactionService;
