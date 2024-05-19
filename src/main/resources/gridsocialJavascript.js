// Replace 'your_username', 'your_password', and 'your_database_name' with
//your actual MySQL credentials and database name.
//Uses MySQL and Node.js with Express.js

const express = require('express');
const bodyParser = require('body-parser');
const mysql = require('mysql');

const app = express();
const PORT = 3000;

// MySQL connection
const connection = mysql.createConnection({
  host: 'localhost',
  user: 'your_username',
  password: 'your_password',
  database: 'your_database_name'
});

connection.connect((err) => {
  if (err) {
    console.error('Error connecting to MySQL:', err);
    return;
  }
  console.log('Connected to MySQL');
});

// Middleware
app.use(bodyParser.urlencoded({ extended: false }));

// Handle POST request for sign-up
app.post('/signup', (req, res) => {
  const { firstName, lastName, email, password } = req.body;

  // Insert user data into the database
  const sql = 'INSERT INTO users (firstName, lastName, email, password) VALUES (?, ?, ?, ?)';
  connection.query(sql, [firstName, lastName, email, password], (err, result) => {
    if (err) {
      console.error('Error inserting user:', err);
      res.status(500).send('Internal Server Error');
      return;
    }
    console.log('User signed up successfully:', result);
    res.send('User signed up successfully!');
  });
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
