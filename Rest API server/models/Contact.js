'use strict';

const mongoose = require('mongoose');

const pasteSchema = new mongoose.Schema({
    name: {
        type: String,
        minlength: 3,
        required: true
    },
    phoneNumber: {
        type: String,
        required: true
    },
    company: {
        type: String
    },
    notes: {
        type: String
    },
    createdBy: {
        type: String
    },
    createdAt: {
        type: Date,
        default: new Date()
    }
});

mongoose.model('Contact', pasteSchema);

module.exports = mongoose.model('Contact');