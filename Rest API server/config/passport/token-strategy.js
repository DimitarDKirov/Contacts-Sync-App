const UniqueTokenStrategy = require('passport-unique-token').Strategy,
	jwt = require('jsonwebtoken'),
	config = require('../app/config');
const strategyOptions = {
	tokenQuery: 'x-token',
	tokenParams: 'x-token',
	tokenField: 'x-token',
	tokenHeader: 'x-token'
};

module.exports = function (passport, data) {
	let strategy = new UniqueTokenStrategy(
		strategyOptions,
		function (token, done) {
			const userId = jwt.decode(token, config.key);
			data.findUserById(userId)
				.then(user => {
					if (!user) return done(null, false);
					return done(null, user);
				})
				.catch(error => done(error, false));
		}
	);

	passport.use(strategy);
}