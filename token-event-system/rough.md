POST /user - only by admins  
GET /users - only by admins

POST /department - only by admins  
GET /departments - by everyone

GET /token/{departmentId} - by everyone (generate new token)  
GET /token/status/{userId} - by logged-in users (view their tokens)  
PUT /token/{tokenId} - by admins/staff (update token status)

POST /login - public  
POST /logout - public

GET /token/current/{departmentId} - optional, get currently serving token  
GET /token/queue/{departmentId} - optional, get entire queue info for department
