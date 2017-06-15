# From Monoliths to Microservices using CQRS and Event Sourcing

## The Monolith

The Monolith is the first step of our journey, we will build a particle detector as an ugly Monolith based on [Spring Boot](https://projects.spring.io/spring-boot/)
and [Spring Cloud Stream](https://cloud.spring.io/spring-cloud-stream/).

## Prerequisites

First try to build the detector by running a gradle build:

```shell
$ gradlew build
```

When everything builds properly you can startup the whole system with the the following command:

```shell
$ docker-compose up
```

It will start the accelerator, a *Kafka* node and finally your detector:
1. Accelerator available at [http://localhost:8080](http://localhost:8080)
2. Detector available at [http://localhost:8081](http://localhost:8081)

If you want to debug your Monolith you can connect with an external debugger to the port 5005 in your local machine.

## Steps

All the steps that you need to take are marked with ```TODO x``` in the source code:

1. Enter a unique name for you detector (this is just for fun)
2. Configure [Spring Cloud Stream](https://cloud.spring.io/spring-cloud-stream/) to connect to Kafka using the *accelerator* topic
3. Tell spring to enable the stream artifacts in the project, we will be using the sink [AcceleratorSink](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/AcceleratorSink.java)
4. Start listening to the data coming from the accelerator
5. Implement the method to find collisions from snapshots coming from the accelerator
6. Discover the particles part of the collisions from the previous point

Remember that, in order to have a successful Monolith, all the tests in [DetectorServiceTest](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/monolith_start/src/test/java/es/malvarez/microservices/monolith/service/DetectorServiceTest.java) must pass.

## Troubleshooting

If you face any issues, you can either ask me a question or compare the branches *monolith_start* and *monolith_end* that is a lousy solution implemented by me.






