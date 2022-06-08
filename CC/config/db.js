const sequelize = require('sequelize');

const db = new sequelize('kulinerin', 'root', '', {
    dialect: 'mysql'
});

db.sync({});

module.exports = db;