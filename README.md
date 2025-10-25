# ChmuraApp
## Enviroment variables
### Backend
DB_HOST
DB_PORT
DB_NAME
DB_USER
DB_PASSWORD
<br /><br />
### Frontend
API_BASE_URL
<br /><br />



## How to run
### Backend 
#### Local Database
docker run --rm --name simple-notatnik -p 8080:8080 \
  --add-host=host.docker.internal:host-gateway \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=5432 \
  -e DB_NAME=notepad \
  -e DB_USER=postgres \
  -e DB_PASSWORD=postgres \
  simple-notatnik
<br />
<br /><br />
### Frontend
docker run --rm -p 4200:80 my-angular-app
