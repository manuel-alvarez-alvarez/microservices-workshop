# From Monoliths to Microservices using CQRS and Event Sourcing

## The CQRS/ES Monolith

So, are you ready to shape our ugly Monolith into something more scalable? The first step is to apply CQRS/ES principles to the Monolith 
(aggregates, commands, events, ...). Since we are already experts in the accelerator/detector domain, we can think about two 
possible aggregates:
1. A collision detector
2. A particle detector

For this part you have a [CQRS/ES framework](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/tree/master/cqrs) 
has ben built, it's a naive implementation that only illustrates how you can implement CQRS/ES. 

## Homework

All the steps that you need to take are marked with ```TODO x``` in the source code:

1. Configure [Spring Cloud Stream](https://cloud.spring.io/spring-cloud-stream/) to connect to Kafka using the *accelerator* and *events* topics
2. Tell spring to enable the stream artifacts in the project, we will be using:
    * [AcceleratorSink](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/AcceleratorSink.java) 
    * [EventStoreProcessor](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/cqrs/src/main/java/es/malvarez/microservices/cqrs/EventStoreProcessor.java)
3. Implement the business logic of the [collision detector](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/cqrs_start/src/main/java/es/malvarez/microservices/cqrs/aggregate/CollisionDetector.java)         
4. Do the same with the [particle detector](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/cqrs_start/src/main/java/es/malvarez/microservices/cqrs/aggregate/ParticleDetector.java)
5. Connect the the collision detector with the accelerator  
6. Link the particle detector with the collision detector through the broadcast stream
7. Populate our collision read model with the proper event
8. Update the particle types according to the related event

## Build and run

You can build the application just by running:

```shell
$ gradlew build
```

To run the whole system you only have to execute the command:

```shell
$ docker-compose up
```

It will start the accelerator, a *Zoo Keeper* node, a *Kafka* node and finally your detector:
1. Accelerator available at [http://localhost:8080](http://localhost:8080)
2. Detector available at [http://localhost:8081](http://localhost:8081)

If you want to debug your Monolith you can connect with an external debugger to the port 5005 in your local machine.

## Troubleshooting

If you face any issues, you can either ask me a question or compare the branches *cqrs_start* and *cqrs_end* that is a lousy solution implemented by me.






