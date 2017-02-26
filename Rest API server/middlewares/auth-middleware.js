'use strict';

const passport = require('passport');

module.exports = {
    isInRole(role) {
        return (req, res, next) => {
            if (req.user && req.user.roles.indexOf(role) !== -1) {
                next();
            } else {
                res.status(401).json({
                    success: false,
                    message: 'Not authorized!'
                });
            }
        }
    },
    isAuthenticated(req, res, next) {
        var auth = passport.authenticate('token', function (err, user, info) {
            if (err) {
                console.log(err);
                return res.status(401).json({
                    success: false,
                    message: 'Not authorized! ' + err
                });
            }

            if (!user) {
                res.status(401).json({
                    success: false,
                    message: 'Not authorized!'
                });
                return;
            }

            req.user = user;
            next();
        });
        auth(req, res, next);
    }
}