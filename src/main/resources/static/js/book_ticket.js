function buy_ticket(filmName) {
    console.log("buy ticket for film:" + filmName)

    sendEmail('ilkachaffee@mail.ru', filmName)
}

function sendEmail(toEmail, filmTitle) {
    Email.send({
        Host: "smtp.gmail.com",
        Username: "testshopizer@gmail.com",
        Password: "Testshopizer12345",
        To: toEmail,
        From: "cinema@bycard.com",
        Subject: "Cinema ticket booked - " + filmTitle,
        Body: "Hello, this is your ticket id: " + generateGUID()
    })
        .then(function (message) {
            console.log("mail sender message: " + message)
            alert("Mail sent successfully!")
        });
}

function generateGUID() {
    //'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'
    return randomString(8) + '-'
        + randomString(4) + '-'
        + randomString(4) + '-'
        + randomString(4) + '-'
        + randomString(12)
}

function randomString(length) {
    return Math.random().toString(36).substr(2, 2 + length);
}

// $(document).ready(function() {
//     $("#buy_ticket_btn").click(buy_ticket);
// });
