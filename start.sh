#!/usr/bin/env bash
chmod +x gradlew
./gradlew clean build
./gradlew clean docker
docker-compose up -d

