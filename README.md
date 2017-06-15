# From Monoliths to Microservices using CQRS and Event Sourcing

## The CQRS/ES Monolith

So, are you ready to shape our ugly Monolith into something more scalable? The first step is to apply CQRS/ES principles to the Monolith 
(aggregates, commands, events, ...). Since we are already experts in the accelerator/detector domain, we can think about two 
possible aggregates:
1. A collision detector
2. A particle detector

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

If you want to debug your CQRS/ES Monolith you can connect with an external debugger to the port 5005 in your local machine.

## Steps

All the steps that you need to take are marked with ```TODO x``` in the source code:

1. Enter a unique name for you detector (this is just for fun)
2. Configure [Spring Cloud Stream](https://cloud.spring.io/spring-cloud-stream/) to connect to Kafka using the *accelerator* and *events* topics
3. Tell spring to enable the stream artifacts in the project, we will be using:
    * [AcceleratorSink](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/AcceleratorSink.java) 
    * [BroadcastProcessor](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/cqrs/src/main/java/es/malvarez/microservices/cqrs/BroadcastProcessor.java)
    * [EventStoreProcessor](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/cqrs/src/main/java/es/malvarez/microservices/cqrs/EventStoreProcessor.java)
4. Implement the business logic of the [collision detector](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/cqrs_start/src/main/java/es/malvarez/microservices/cqrs/aggregate/CollisionDetector.java)         
5. Do the same with the [particle detector](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/cqrs_start/src/main/java/es/malvarez/microservices/cqrs/aggregate/ParticleDetector.java)
6. Connect the the collision detector with the accelerator  
7. Link the particle detector with the collision detector through the broadcast stream
8. Populate our collision read model with the proper event

Remember that, in order to have a successful CQRS/ES application, all the tests in [CollisionDetectorTest](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/cqrs_start/src/test/java/es/malvarez/microservices/cqrs/aggregate/CollisionDetectorTest.java)
and [ParticleDetectorTest](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/cqrs_start/src/test/java/es/malvarez/microservices/cqrs/aggregate/ParticleDetectorTest.java) must pass.

## Troubleshooting

If you face any issues, you can either ask me a question or compare the branches *cqrs_start* and *cqrs_end* that is a lousy solution implemented by me.






