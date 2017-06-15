const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

exports.getSecrets = functions.https.onRequest((req, res) => {
    var db = admin.database();
    var ref = db.ref('secrets').once("value", function(snapshot) {
        res.send(JSON.stringify(snapshot.val()));
    });
});

exports.answer = functions.https.onRequest((req, res) => {
    var db = admin.database();

    var ref = db.ref("answers").child(req.body.id);
    ref.once("value", function(snapshot){
        if (req.body.answer === snapshot.val()) {
            db.ref("secrets").child(req.body.id).update({
                "passed": true
            });
            res.send({
                succes: true
            });            
        } else {
            res.send({
                succes: false
            });        
        }
    })
});
