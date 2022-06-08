const Sequelize = require('sequelize');
const db = require('../config/db');

const User = db.define(
    'User',
    {
        email: {type: Sequelize.STRING},
        password: {type: Sequelize.STRING},
    },
);

module.exports = User;