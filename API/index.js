const express = require("express");
const cors = require("cors");
const mysql = require("mysql2/promise"); // Usamos mysql2 con promesas
const app = express(); 
const PORT = 3000;

// Crear la conexión al pool de MySQL en XAMPP (local)
const myPool = mysql.createPool({
  host: "localhost", 
  user: "root", 
  password: "", 
  database: "loginoscar", 
  port: 3306, 
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

app.use(cors());
app.use(express.json());

// Iniciar el servidor
app.listen(PORT, () => {
  console.log("Servidor funcionando!!!");
  console.log(`El servidor está escuchando en http://localhost:${PORT}`);
});

// Login
app.get('/login', async (req, res) => {
    const { username, password } = req.query; 
    console.log(req.query);

    try {
        const [user] = await myPool.query('SELECT id, username FROM users WHERE username = ? AND password = ?', [username, password]);

        if (user.length > 0) {
            console.log("Sesión iniciada");
            res.status(200).json({ message: 'Inicio de sesión exitoso', user: { id: user[0].id, username: user[0].username } });
        } else {
            res.status(401).json({ error: 'Credenciales incorrectas' });
        }

    } catch (error) {
        console.error('Error al iniciar sesión:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});


// Registro
app.post('/register', async (req, res) => {
    const { username, password } = req.body;
    console.log(req.body);
  
    try {
      // Verificar si el usuario ya existe
      const [existingUser] = await myPool.query('SELECT * FROM users WHERE username = ?', [username]);
  
      if (existingUser.length > 0) {
        res.status(400).json({ error: 'El usuario ya está registrado' });
      } else {
        // Registrar el nuevo usuario
        await myPool.query('INSERT INTO users (username, password) VALUES (?, ?)', [username, password]);
        console.log("Usuario registrado");
        res.status(201).json({ message: 'Usuario registrado exitosamente' });
      }
    } catch (error) {
      console.error('Error al registrar usuario:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  });
  