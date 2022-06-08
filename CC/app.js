const express = require('express');
const bodyParser = require('body-parser');
const db = require('./config/db');
const food = require('./src/food');
const User = require('./src/User');
const cors = requir('cors');

const app = express();

app.use(cors());
app.get('/', (req, res) => res.send('Connected'));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));


app.post("/register", async (req, res) => {
    try{
        const { email, password, description, recipe, maps, image } =req.body;

        const newUser =new User({
            email, password, description,recipe, maps, image
        })

        await newUser.save();
        res.send('Success Register')
    } catch (err){
    console.error(err.message);
    res.status(500).send('Server error')
    }
});

app.get("/user", async(req, res) => {
    try {
        const getAllUser = await User.findAll({});
        res.json(getAllUser);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server error');
    }
});

app.get("/user/:id", async (req, res) => {
    res.send('GET USER: GET /user/' + req.params.id);
}); 

app.post("/user/:id/history", async (req, res) => {
    res.send({
        message: 'CREATE NEW: POST /user/history',
        body: req.body
 });
});

app.get("/user/:id/history", async (req, res) => {
    res.send('GET HISTORY: GET /user/history' + req.params.id);
});

app.post("/food", async(req, res) => {
    try {
        const { nameFood,  recipe, maps } = req.body;
        
        const newFood = new food ({
            nameFood,  recipe, maps
        })
        await newFood.save();

        res.json(newFood);
    } catch (err) {
    console.error(err.message);
    res.status(500).send('Server error')
    }
});

app.get("/foods", async(req, res) => {
    try {
        const getAllFood = await food.findAll({});
        res.json(getAllFood);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server error');
    }
});

app.get("/food/:nameFood", async (req, res) => {
    try {
        const getIdFood = await food.findOne({});
        res.json(getIdFood);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server error');
    }
});

app.use('/food/:nameFood', (req, res, next ) => {
    const filter =req.query;
    const filteredFood = data.filter(food => {
        let isValid = true;
        for (key in filter) {
            console.log(key, food[key], filter[key], description[key], recipe[key], maps[key], file[key]);
            isValid = isValid && food[key] == filter[key], recipe[key], maps[key], image[key];
        }
        return isValid;
    });
    res.send(filteredFood);
});

app.use('uploads', express.static('uploads'));

app.get('/uploads', (req, res) => {
    res.send('Upload Success');
});

app.post('/uploadfile', upload.single('dataFile'), (req, res, next) => {
    const file = req.file;
    if (!file) {
       return res.status(400).send({ message: 'Please upload a file.' });
    }
    return res.send({ message: 'File uploaded successfully.', file });
 });

app.listen(PORT, () => console.log('Connected'));