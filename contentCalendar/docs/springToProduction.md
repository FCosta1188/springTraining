https://github.com/danvega/content-calendar/blob/main/docs/production.md

### How to generate production artifacts to deploy the app on a host service
- **Uber JAR**: `./mvnw clean package`. The `.jar` is saved in the `target` folder and can only run in environments/hosts where a JVM is present. 
- **Containers**: 
  1. `./mvnw spring-boot:build-image`
  2. `docker image ls -a`
  3. `docker run -it -p8080:8080 content-calendar-0.0.1-SNAPSHOT`
- **Native Image**: `mvn -Pnative native:compile`.

### Hosts
- [Amazon Web Services (AWS)](https://aws.amazon.com/)
- [Azure Spring Apps](https://azure.microsoft.com/en-us/products/spring-apps)
- [Google Cloud](https://cloud.google.com/)
- [OpenShift](https://www.redhat.com/en/technologies/cloud-computing/openshift)
- [Cloud Foundry](https://www.cloudfoundry.org/)
- [Digital Ocean](https://www.digitalocean.com/)
- [Railway](https://railway.app/)
- [Heroku](https://www.heroku.com/)
- [Render](https://render.com/)
- [Fly.io](https://fly.io/)
- [Porter](https://porter.run/)
