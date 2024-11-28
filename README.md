# Quiz API Documentation

This is a RESTful API built with Spring Boot to manage a quiz application. It supports functionality for tracking user performance and serving random multiple-choice questions for a quiz.

### Running the Application
- Clone the repository:
```
git clone https://github.com/yourusername/quiz-api.git
```
- Navigate to the project directory
```
cd quiz-api
```
- Build and run the application using Maven:

```bash
./mvnw clean install
./mvnw spring-boot:run
```
The application will run on http://localhost:3456

## Endpoints

### Create User Endpoint
POST /users
This endpoint allows you to create a new user.

#### Request Body:
```json
{
    "username": "user1"
}
```
#### Response:
```json
{
    "id": 1,
    "username": "user1",
    "totalAttemptedQuestions": 0,
    "answeredCorrectly": 0,
    "correctPercent": 0.0
}
```
### Add Question Endpoint
POST /questions
This endpoint allows you to add a new quiz question.

#### Request Body:
```json
{
    "questionText": "What is the capital of France?",
    "options": ["Paris", "London", "Berlin", "Madrid"],
    "correctAnswer": "Paris"
}
```
#### Response:
```json
{
    "id": 1,
    "questionText": "What is the capital of France?",
    "options": ["Paris", "London", "Berlin", "Madrid"],
    "correctAnswer": null
}
```
### 1. Dashboard Endpoint
**GET** `/dashboard/{userId}`  
This endpoint returns the user's performance, including the total questions attempted, number of correct answers, and the overall score (percentage).

#### Response:
```json
{
    "id": 1,
    "username": "user1",
    "totalAttemptedQuestions": 5,
    "answeredCorrectly": 3,
    "correctPercent": 60.0
}
```
### 2. Take Quiz Endpoint
POST /quiz/take/{userId}
This endpoint serves a random multiple-choice question from the database (without the correct answer). It returns the question text, options, and question ID.

#### Request:
No request body.

#### Response:
```json
{
    "id": 1,
    "questionText": "What is the capital of France?",
    "options": ["Paris", "London", "Berlin", "Madrid"]
}
```
### 3. Submit Answer Endpoint
POST /quiz/submit/{userId}
This endpoint accepts the question ID and the user's selected answer. It validates the answer, updates the user's performance, and returns whether the answer is correct or incorrect.

#### Request:
```URL
http://localhost:3456/quiz/submit/{userId}?selectedAnswer=<selectedoption>&questionId=<questionid>
```
#### Response:
```json
{
    "message": "Correct!",
    "isCorrect": true
}
```
### 4. End Quiz Endpoint
POST /quiz/end/{userId}
This endpoint marks the quiz as ended for the user and returns their final performance.

#### Response:
```json
{
    "id": 1,
    "username": "user1",
    "totalAttemptedQuestions": 5,
    "answeredCorrectly": 3,
    "correctPercent": 60.0
}
```

### Notes
- H2 Database: The application uses an in-memory H2 database for storing users and quiz questions. Ensure the database is properly configured for persistence if required.
- Error Codes: Standard HTTP status codes are used to indicate the success or failure of requests. For example, 404 for not found and 200 for success.
