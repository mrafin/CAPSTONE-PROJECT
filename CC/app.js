const path = require("path");
const express = require("express");
const bodyParser = require("body-parser");
const multer = require("multer");
const { PrismaClient } = require("@prisma/client");

const prisma = new PrismaClient();
const app = express();

const listener = app.listen(8080, function () {
  console.log("Listening on port" + listener.address().port);
});

app.get("/", (req, res) => res.send("Connected"));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, "./uploads");
  },
  filename: (req, file, cb) => {
    cb(null, Date.now() + path.extname(file.originalname));
  },
});

const upload = multer({
  storage,
});

app.post("/register", async (req, res) => {
  try {
    const { email, password } = req.body;

    await prisma.user.create({ data: { email, password } });

    res.send("Success Register");
  } catch (err) {
    console.error(err.message);
    res.status(500).send("Server error");
  }
});

app.get("/login", async (req, res) => {
  const { email, password } = req.body;
  const user = await prisma.user.findMany({
    where: { email },
  });
  res.json(user);
});

app.get("/users/:id/histories", async (req, res) => {
  const histories = await prisma.history.findMany({
    where: { userId: id },
  });
  res.send(histories);
});

app.get("/city", async (req, res) => {
  const city = await prisma.food.findMany({
    where: { FoodsOnRestaurants },
  });
  res.send(city);
});

app.post("/food", async (req, res) => {
  try {
    const { nameFood, recipe, maps } = req.body;

    const newFood = new food({
      nameFood,
      recipe,
      maps,
    });
    await newFood.save();

    res.json(newFood);
  } catch (err) {
    console.error(err.message);
    res.status(500).send("Server error");
  }
});

app.get("/foods", async (req, res) => {
  try {
    const getAllFood = await food.findAll({});
    res.json(getAllFood);
  } catch (err) {
    console.error(err.message);
    res.status(500).send("Server error");
  }
});

app.get("/food/:nameFood", async (req, res) => {
  try {
    const getIdFood = await food.findOne({});
    res.json(getIdFood);
  } catch (err) {
    console.error(err.message);
    res.status(500).send("Server error");
  }
});

app.use("/food/:nameFood", (req, res, next) => {
  const filter = req.query;
  const filteredFood = data.filter((food) => {
    let isValid = true;
    for (key in filter) {
      console.log(
        key,
        food[key],
        filter[key],
        description[key],
        recipe[key],
        maps[key],
        file[key]
      );
      (isValid = isValid && food[key] == filter[key]),
        recipe[key],
        maps[key],
        image[key];
    }
    return isValid;
  });
  res.send(filteredFood);
});

app.use("uploads", express.static("uploads"));

app.post("/uploads", upload.single("file"), (req, res, next) => {
  const file = req.file;
  console.log(req.file);
  if (!file) {
    return res.status(400).send({ message: "Please upload a file." });
  }
  return res.send({ message: "File uploaded successfully.", file });
});
