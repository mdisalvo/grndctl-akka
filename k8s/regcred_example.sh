kubectl create secret docker-registry regcred \
    --docker-server=https://index.docker.io/v1/ \
    --docker-username=<USERNAME> \
    --docker-password=<PASSWORD> \
    --docker-email=<EMAIL>
