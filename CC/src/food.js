const Sequelize = require('sequelize');
const db = require('../config/db');

const food = db.define(
    'food',
    'nameFood',
    {
        nameFood: {type: Sequelize.STRING},
        description: {type: Sequelize.TEXT},
        recipe: {type: Sequelize.TEXT},
        maps: {type: Sequelize.DOUBLE},
        Image: {type: Sequelize.BLOB},
    },
);

module.exports = food;