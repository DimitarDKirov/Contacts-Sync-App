'use strict';

const passport = require('passport'),
    jwt = require('jsonwebtoken'),
    config = require('../config/app/config');

module.exports = function (data) {
    return {
        loginLocal(req, res, next) {
            const auth = passport.authenticate('local', function (error, user) {
                if (error) {
                    next(error);
                    return;
                }

                if (!user) {
                    res.json({
                        success: false,
                        message: 'Invalid name or password!'
                    });
                }

                req.login(user, error => {
                    if (error) {
                        next(error);
                        return;
                    }

                    const token = jwt.sign(user.id, config.key);
                    res.status(200).json({
                        success: true,
                        message: `User ${user.username} logged successfully!`,
                        token: token
                    });
                });
            });

            auth(req, res, next);
        },
        logout(req, res) {
            req.logout();
            res.status(200).json({ success: true });
        },
        register(req, res) {
            const user = {
                username: req.body.username,
                password: req.body.password,
                phone: req.body.phone
            };

            if (user.username.length < 3) {
                return res.status(400).json({
                    success: false,
                    message: `username must be at least 3 symbols long`
                });
            }

            if (user.password.length < 3) {
                return res.status(400).json({
                    success: false,
                    message: `passowrd must be at least 3 symbols long`
                });
            }

            data.createUser(user)
                .then(dbUser => {
                    res.status(201).json({
                        success: true,
                        message: `User ${dbUser.username} registered`
                    });
                })
                .catch(error => res.status(500).json(error));
        }
    }
};