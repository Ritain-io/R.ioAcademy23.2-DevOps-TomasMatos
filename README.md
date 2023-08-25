
# Math Base API

The **Math** software solution is a versatile tool that provides two distinct services: **Odd vs Even** and **Prime Number Check**. These services allow users to seamlessly determine whether a number is odd or even, as well as whether a number is prime.

## Capabilities

- **Odd vs Even Service:** This service helps users determine if a given number is odd or even.

- **Prime Number Check Service:** This service assists users in determining whether a given number is prime. However, this service only accepts input numbers until 1000. 

## Technologies Used

- Java 17
- Spring Boot 3.1.2

## Usage

The Math software solution offers the following components:

### 1. REST API

The solution exposes a RESTful API that enables programmatic access to the services. To interact with the API, make requests to the appropriate endpoints:


![Swagger Documentation](https://i.imgur.com/bOp6rdM.png)


- To check if a number is odd or even:

``` bash
curl -X GET --location 'http://localhost:8080/api/odd-vs-even/3'

curl -X POST --location 'http://localhost:8080/api/odd-vs-even' \
--header 'Content-Type: application/json' \
--data '{
    "number":3
}'

```

- To check if a number is prime:

``` bash
curl -X GET --location 'http://localhost:8080/api/prime/3'

curl -X POST --location 'http://localhost:8080/api/prime' \
--header 'Content-Type: application/json' \
--data '{
    "number":3
}'
```

``` bash
curl -X GET --location 'http://localhost:8080/api/prime/1001'

# Should return {"message":"An error occurred","success":false,"errorMessage":"Wow wow wow... We have no computing resources to know if 1001 is a prime number! We can only compute this for numbers <= 1000 !"}
```

You may find documentation on these endpoints at the swagger endpoint of this API. To do so, access `/swagger-ui/index.html`.



### 2. WebUI

The WebUI provides a user-friendly interface for interactively checking whether a number is odd, even, or prime. To access the WebUI:

1. In your browser, access `http://localhost:8080/`. You should see this webpage.

![Root Web Page](https://i.imgur.com/A1HQRiY.png)

2. Then, you should select a service to experiment, which will redirect you to that service's webpage.
   
## Getting Started

To get started with the Math software solution, follow these steps:

1. **Clone the Repository:** Clone this repository to your local machine.
 

2. **Run the Application:** Navigate to the project directory and run the application.

``` bash
cd Math
mvn spring-boot:run
```


3. **You May Also Package the Application into a JAR and run the JAR:** Package the application into a JAR file using the following command, and run it using the following commands:

``` bash
cd Math

# Run tests before packaging
mvn clean package

# Avoid running the tests before packaging
mvn clean package -DskipTests=true

# After running one of these commands, a jar file will be create under /target.
# Then, you may execute the jar file to run the application.
java -jar target/math-0.0.1-SNAPSHOT.jar
```

## Testing

The Math software solution includes both unit tests and end-to-end tests to ensure the reliability and correctness of the services.

1. You may run all tests:

```bash
cd Math
mvn test
```

2. Or you may choose which tests you want to perform:

``` bash
cd Math

# Run only unitary tests
mvn '-Dtest=rafaeldireito.com.math.unit_tests.*Tests' test

# Perform the REST API related end-to-end tests
mvn '-Dtest=rafaeldireito.com.math.end_to_end_tests.api.*Tests' test

# Perform the WebUI related end-to-end tests
# For these tests you'll need firefox to be installed (or you may change the testing code to rely on Chrome). Either way, you'll have to install the chosen browser's drive as it will be employed by Selenium to test the UI.
# Additional information at:
# - Chrome Web Driver: https://chromedriver.chromium.org/
# - Firefox Web Driver: https://github.com/mozilla/geckodriver/releases
mvn '-Dtest=rafaeldireito.com.math.end_to_end_tests.ui.*Tests' test

```






## Creating a Basic Helm Chart Structure

To create a basic Helm chart structure, you can use the following command:

```bash
helm create <chart-name>
```
This will generate a new directory <chart-name> with the basic Helm chart structure. You can then move your application-specific configurations into this structure.
Math Application Helm Chart

This Helm chart deploys the Math application to a Kubernetes cluster. The Math application is a simple web service for performing mathematical operations.

## Prerequisites

    A running Kubernetes cluster.
    Helm installed on your local machine.

## Usage

    Clone this repository to your local machine.

    Navigate to the Helm chart directory:

```bash

cd math-chart
```
Customize the chart values in the values.yaml file according to your requirements.

Install the Helm chart using the following command:

```bash

helm install <release-name> . -f values.yaml
```
Replace <release-name> with the desired name for your release.

To upgrade the Helm chart, use:

```bash

helm upgrade <release-name> . -f values.yaml
```
To uninstall the Helm chart, run:

```bash

    helm uninstall <release-name>
```
## Configuration

You can customize the Helm chart by modifying the values in the values.yaml file. Key configurations include:

    replicaCount: Number of Math application replicas.
    image.repository: Math application container image repository.
    service.type: Kubernetes service type for Math application (e.g., ClusterIP or NodePort).
    ingress.enabled: Enable Ingress for Math application.
    ingress.hosts: Ingress hostnames for the Math application.

## Testing

Test the deployed Math application using the service's ClusterIP or exposed Ingress endpoint.

