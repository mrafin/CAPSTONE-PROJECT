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

    const user = await prisma.user.create({ data: { email, password } });

    res.send(user);
  } catch (err) {
    console.error(err.message);
    res.status(500).send("Failed");
  }
});

app.post("/login", async (req, res) => {
  const { email, password } = req.body;

  const user = await prisma.user.findUnique({ where: { email } });

  if (user && user.password == password) {
    res.send(user);
  } else {
    res.status(404).send({ message: "Login Failed" });
  }
});

app.post("/histories", async (req, res) => {
  try {
    const { foodName } = req.body;
    const { id } = req.params;

    const user = await prisma.user.findMany({
      where: { id, histories },
    });
    const history = await prisma.user.create({
      data: {
        id,
        foodName,
      },
    });

    res.send(history);
  } catch (err) {
    console.error(err.message);
    res.status(500).send("Cannot founds history");
  }
});

app.get("/histories", async (req, res) => {
  const { id, foodName, userId } = req.body;
  const histories = await prisma.history.findMany({
    where: { id, foodName, userId },
  });
  res.send(histories);
});

app.get("/city", async (req, res) => {
  const { name, food } = req.body;
  const city = await prisma.food.findMany({
    where: { name, food },
  });
  res.send(city);
});

app.post("/foods", async (req, res) => {
  const { name, description, recipe, images } = req.body;

  try {
    const food = await prisma.food.create({
      data: {
        name,
        description,
        recipe,
        images: { create: images?.map((url) => ({ url })) },
      },
      include: {
        images: !!images,
      },
    });
    res.send(food);
  } catch (err) {
    console.error(err.message);
    res.status(500).send({ message: "Cannot post food" });
  }
});

app.post("/restaurants", async (req, res) => {
  const { name, lat, long } = req.body;

  try {
    const restaurant = await prisma.restaurant.create({
      data: {
        name,
        lat,
        long,
      },
    });
    res.send(restaurant);
  } catch (err) {
    console.error(err.message);
    res.status(500).send({ message: "Cannot post restaurant" });
  }
});

app.get("/restaurants", async (req, res) => {
  const { id, name, lat, long } = req.body;

  const restaurants = await prisma.restaurant.findMany({
    where: { id, name, lat, long },
  });
  res.send(restaurants);
});

app.get("/restaurants/:id", async (req, res) => {
  const { id } = req.body;

  const restaurant = await prisma.restaurant.findUnique({
    where: { id },
  });
  if (restaurant === id) {
    res.send(restaurant);
  } else {
    res.status(404).send({ message: "Restaurant not founds" });
  }
});

app.get("/foods", async (req, res) => {
  const { id, name, description, recipe, restaurants, images } = req.body;

  const foods = await prisma.food.findMany({
    where: { id, name, description, recipe, restaurants, images },
  });
  res.send(foods);
});

app.get("/foods/:id", async (req, res) => {
  const { id } = req.params;

  const food = await prisma.food.findUnique({
    where: { id },
  });

  if (food) {
    res.send(food);
  } else {
    res.status(404).send({ message: "Food cannot founds" });
  }
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

app.get("/uploads", async (req, res, next) => {
  const { url } = req.params;

  const fileUrl = await prisma.image.findUnique({
    where: { url },
  });

  res.send(fileUrl);
  if (!file === url) {
    res.sendFile(fileUrl.url);
  }
  return res.send({ message: "We get the file ", fileUrl });
});
