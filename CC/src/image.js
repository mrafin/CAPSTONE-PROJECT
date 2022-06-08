const multer = require('multer');
const Sequelize = require('sequelize');

const Image = Sequelize.define(
  'Image',
  {
    type: {type: Sequelize.STRING},
    name: {type: Sequelize.STRING},
    data: {type: Sequelize.BLOB}
  },
);

module.exports = Image;