'use strict';

module.exports = function (models) {
    let Contact = models.Contact;
    return {
        getAllContacts() {
            return Contact.find();
        },
        createContact(contactData, user) {
            let contact = {
                name: contactData.name,
                phone: contactData.phone,
                notes: contactData.notes,
                createdBy: user.username,
                createdAt: new Date()
            };
            return Contact.create(contact);
        },
        updateContact(id, data) {
            return Contact.findByIdAndUpdate(id, data);
        },
        deleteContact(id) {
            return Contact.findByIdAndRemove(id);
        },
        getById(id) {
            return Contact.findById(id);
        }
    }
};