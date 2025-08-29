# Spring Boot Weather API

A RESTful weather API built with **Java Spring Boot**. The API allows clients to fetch current weather information for a specified location. The project uses **Maven** for build management and is deployed using **Docker**.  

**Live API:** [https://springbootweatherapi.onrender.com/](https://springbootweatherapi.onrender.com/)

## Features

- Get current weather information by location.
- Supports multiple metrics (e.g., Celsius/Fahrenheit).  
- Lightweight and easy-to-use API.

## Tech Stack

- **Backend:** Java Spring Boot  
- **Build Tool:** Maven  
- **Deployment:** Docker, Render  

## API Endpoints

### Get Weather

```
GET /weather?location={location}
```

**Parameters:**

- `location` (required): Name of the city or location.  

**Example:**

```
https://springbootweatherapi.onrender.com/weather?location=Tokyo
```

**Response:**

```
{
  "location": "Tokyo",
  "temperature": 28,
  "unit": "C",
  "description": "Clear sky"
}
```

## Usage

1. Make sure the backend is deployed and running.  
2. Send a GET request to the `/weather` endpoint with the `location` parameter.  
3. Use the JSON response in your frontend application or any client.

## Deployment

The API can be deployed using Docker:

```
# Build the Docker image
docker build -t springboot-weather-api .

# Run the container
docker run -p 8080:8080 springboot-weather-api
```

After running, the API will be available at `http://localhost:8080/weather?location=Tokyo`.

## Notes

- On free Render hosting, the API may be asleep when not in use. Wake it up by visiting:  
  [https://springbootweatherapi.onrender.com/](https://springbootweatherapi.onrender.com/)
