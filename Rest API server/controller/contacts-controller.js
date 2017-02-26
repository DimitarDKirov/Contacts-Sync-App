'use strict';
const MIN_LENGTH = 3;
function verify(field) {
    return field.length >= MIN_LENGTH;
}

module.exports = function (data) {
    return {
        getContacts(req, res) {
            data.getAllContacts()
                .then(contacts => res.status(200).json(contacts))
                .catch(err => {
                    console.log(err);
                    return res.status(500).json(err);
                });
        },
        createContacts(req, res) {
            let contacts = req.body.contacts;
            contacts.forEach(contact => {
                // if (!verify(contact.name)) {
                //     return res.status(400).json({
                //         success: false,
                //         message: `Name must be at least ${MIN_LENGTH} symbols long ${contact.name}: ${contact.phone}`
                //     });
                // }
                data.createContact(contact, req.user)
                    .catch(err => {
                        console.log(err);
                        return res.status(500).json({
                            success: false,
                            message: err
                        });
                    });
            });

            res.status(201).json({
                success: true,
                message: 'Contacts created'
            });
        },
        updateContactById(req, res) {
            let contactId = req.params.contactId;
            let updatedData = {};
            if (typeof req.body.name !== "undefined") updatedData['name'] = req.body.name;
            if (typeof req.body.phone !== "undefined") updatedData['phone'] = req.body.phone;
            if (typeof req.body.notes !== "undefined") updatedData['notes'] = req.body.notes;

            data.updateContact(contactId, updatedData)
                .then(dbContact => res.status(200).json({
                    success: true,
                    message: 'Contact updated',
                    contact: dbContact
                }))
                .catch(err => res.status(500).json({
                    success: false,
                    message: err
                }));
        },
        deleteContactById(req, res) {
            let id = req.params.contactId;
            data.deleteContact(id)
                .then(dbContact => res.status(200).json({
                    success: true,
                    message: 'Contact deleted',
                    contact: dbContact
                }))
                .catch(err => res.status(500).json({
                    success: false,
                    message: err
                }));
        },
        findContactById(req, res) {
            let id = req.params.contactId;
            data.getById(id)
                .then(contact => res.status(200).json(contact))
                .catch(err => res.status(500).json({
                    success: false,
                    message: err
                }));
        }
    }
}