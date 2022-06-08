var image = $("#imageid")[0].files[0];
var formdata = new FormData();
formdata.append('image', image);
$.ajax({
    url: '/uploads/',
    data: formdata,
    contentType: false,
    processData: false,
    type: 'POST',
    'success':function(data){
        alert(data);
    }
});

var storage = multer.diskStorage({
    destination: function (req, file, cb) {
       cb(null, 'uploads');
    },
    filename: function (req, file, cb) {
       cb(null, `${file.fieldname}-${Date.now()}${path.extname(file.originalname)}`);
    }
 });
 var upload = multer({ storage: storage });

 module.exports = { storage, upload };