# MoneyLionTechAssessment - AI Engineer by WB (24/11/2022)

Java 19 and Build with Gradle
Hosted on AWS Lambda, Endpoint as follows: (https://73dln5ba1g.execute-api.ap-southeast-1.amazonaws.com/dev/feature)
*If try the endpoints, please wait awhile on first call, as lambda need to do through cold start.
 - GET /feature -> https://73dln5ba1g.execute-api.ap-southeast-1.amazonaws.com/dev/feature?email=XXX&featureName=YYY
 - POST /feature -> https://73dln5ba1g.execute-api.ap-southeast-1.amazonaws.com/dev/feature

## To run the application

```
./gradlew bootRun
```

## To build the application

```
./gradlew build
```

## To run test

```
./gradlew test
```

## Host on AWS Lambda via serverless package (change compatible in build.gradle to Java11)

```
npm install -g serverless
serverless deploy --region ap-southeast-1
```

## Task

- [x] Requirement GET/POST API Endpoints
- [x] Connected to managed PostgreSQL on cloud (ElephantSQL because is free!) 
- [] Automated test
- [] Frontend
- [x] Hosting
- [] Domain Name