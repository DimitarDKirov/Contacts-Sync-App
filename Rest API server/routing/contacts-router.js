'use strict';

const router = require('express').Router(),
    auth = require('../middlewares/auth-middleware');

module.exports = function (app, data) {
    const contactsController = require('../controller/contacts-controller')(data);
    router
        .get('/contacts', auth.isAuthenticated, contactsController.getContacts)
        .get('/contact/:contactId', auth.isAuthenticated, contactsController.findContactById)
        .post('/contacts', auth.isAuthenticated, contactsController.createContacts)
        .put('/contact/:contactId', auth.isAuthenticated, contactsController.updateContactById)
        .delete('/contact/:contactId', auth.isAuthenticated, contactsController.deleteContactById)


    app.use('/api', router);
}