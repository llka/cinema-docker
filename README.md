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
