RESTfull API server for Contact Sync App
===================
Default port: 3001

User model
--------
```javascript
 username: {
        type: String,
        minlength: 4,
        required: true,
        unique: true
    },
    passHash: {
        type: String,
        required: true
    },
    salt: {
        type: String,
        required: true
    },
    phoneNumber:{
        type: String,
        required: false
    }
```

Routes User
------

 - POST /user/register - register user. Accepts user data as JSON:
```javascript
{
	"username":"user",
	"password":"123",
	"phoneNumber":"081111"
}
```
Username must be at least 4 symbols long. Returns message if register successful or error:
```javascript
{
  "success": true,
  "message": "User user registered"
}
or
{
  "success": false,
  "message": "username must be at least 3 symbols long"
}
```
 - POST /user/login - user login with username and password
```javascript
{
	"username":"user",
	"password":"123"
}
```
Returns token if login successful or error message:
```javascript
{
  "success": true,
  "message": "User user logged successfully!",
  "token": "eyJhbGciOiJIUzI1NiJ9.NThiMzMxNDc3YzMzYTA0YzQ0NjQyMWM0.ueJM7-qohXPPczXg_J5qDKb-gLZSZXXMR8zYf4vFN-g"
}
or
{
  "success": false,
  "message": "Invalid name or password!"
}
```
The token must be sent as x-token header for each route that requires authentication

Contact model
--------
```javascript
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
```

Routes Contact
-------
All routes are authenticated - user must send token as header x-token

 - POST /api/contacts - adds contacts as array:
```javascript
{"contacts":[{
	"name":"user1",
	"phoneNumber":"08111",
	"company":"Corp"
},{
	"name":"user2",
	"phoneNumber":"082222",
	"company":"Corp",
	"notes":"CEO"
}]}
```
Message if insert is OK:
```javascript
{
  "success": true,
  "message": "Contacts created"
}
```
 - GET /api/contacts - returns all contacts
```javascript
  [
  {
    "_id": "58b38e447c33a04c446421c5",
    "name": "user1",
    "phoneNumber": "08111",
    "company": "Corp",
    "createdBy": "mitko",
    "__v": 0,
    "createdAt": "2017-02-27T02:26:12.322Z"
  },
  {
    "_id": "58b38e447c33a04c446421c6",
    "name": "user2",
    "phoneNumber": "082222",
    "notes": "CEO",
    "company": "Corp",
    "createdBy": "mitko",
    "__v": 0,
    "createdAt": "2017-02-27T02:26:12.324Z"
]
```
 - GET /api/contact/contactId - returns information for the contact with the given Id
```javascript
{
  "_id": "58b38e447c33a04c446421c6",
  "name": "user2",
  "phoneNumber": "082222",
  "notes": "CEO",
  "company": "Corp",
  "createdBy": "mitko",
  "__v": 0,
  "createdAt": "2017-02-27T02:26:12.324Z"
}
```
 - PUT /api/contact/contactId - updates the contact with the given ID
```javascript
{
	"notes":"Vice president"
}
Returns:
{
  "success": true,
  "message": "Contact updated",
  "contact": {
    "_id": "58b390717c33a04c446421c8",
    "name": "user2",
    "phoneNumber": "082222",
    "notes": "CEO",
    "company": "Corp",
    "createdBy": "mitko",
    "__v": 0,
    "createdAt": "2017-02-27T02:35:29.394Z"
  }
}
```
 - DELETE /api/contact/contactId - deletes the contact with the given Id
Returns
```javascript
{
  "success": true,
  "message": "Contact deleted",
  "contact": {
    "_id": "58b2cccc9a7456141815dfd4",
    "name": "mitko2",
    "phone": "123",
    "createdBy": "mitko",
    "__v": 0,
    "createdAt": "2017-02-26T12:40:44.219Z"
  }
}
```
