#!/usr/bin/env bash
./gradlew clean build
./gradlew jibDockerBuild
image_id=$( docker images | grep com.example/cinema -m 1 | awk '{print $3}' )
echo Image id: "$image_id"
docker tag "$image_id" ilka/ilka_images_repository:cinema-app
docker push ilka/ilka_images_repository:cinema-app




