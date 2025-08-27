# Makefile for Spring Boot + Redis Docker Compose

# Variables
APP_NAME=weather-app
COMPOSE_FILE=docker-compose.yml

# Build the Docker images
build:
	docker-compose -f $(COMPOSE_FILE) build

# Start the services in the background
up:
	docker-compose -f $(COMPOSE_FILE) up -d

# Stop and remove containers, networks
down:
	docker-compose -f $(COMPOSE_FILE) down

# Show logs of all services
logs:
	docker-compose -f $(COMPOSE_FILE) logs -f

# Rebuild and restart services
rebuild: down build up

# Stop and remove all containers, volumes, networks (full cleanup)
clean:
	docker-compose -f $(COMPOSE_FILE) down -v --remove-orphans
