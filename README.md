# Connect to PgAdmin

1. Go to browser http://localhost:8000
2. Enter credentials: 
    ```
    admin@mail.ru
    admin
    ```
3. Add New Server -> Connection Tab
   ```$xslt
    host: postgre-db
    port: 5432
    database: cinema
    username: postgres
    password: password
    ```

# Run Locally
```$xslt
chmod +x start.sh
chmod +x stop.sh

./start.sh

./stop.sh
```


Open in browser `localhost:8080/cinema`

#Api -  Swagger Documentation
http://localhost:8080/swagger-ui.html


# Run Locally in dev mode
In docker-compose.yml set:
```$xslt
cinema-app:
        image: 'com.example/cinema:latest'
```
Run:

```$xslt
./gradlew clean build
./gradlew jibDockerBuild
docker-compose up -d
```

# Push image to docker hub
```$xslt
./gradlew jibDockerBuild (to build image)

docker images (to find your image id - IMAGE_ID)

docker tag IMAGE_ID ilka/ilka_images_repository:cinema-app

docker images (to check that image with the desired tag exists)

docker push ilka/ilka_images_repository:cinema-app
```


