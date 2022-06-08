const Sequelize = require('sequelize');
const db = require('../config/db');

const history = db.define(
    'history',
    {
        history: {type: Sequelize.STRING},
    }   
);

module.exports = history;