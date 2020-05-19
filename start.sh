#!/usr/bin/env bash
./gradlew jibDockerBuild
docker-compose up -d
echo "Waiting 30 seconds for docker compose services to be ready"
sleep 30
docker-compose ps


