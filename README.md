# From Monoliths to Microservices using CQRS and Event Sourcing

## The final Microservices

Now it's time to separate things and put them in different modules / applications / services ... We have already separated
two things:
* Our aggregates that went to the *write-model* module
* Our only read model that went to the *collision-read-model*, we have also configured this module as a service that will be
registered with Eureka

## Prerequisites

First try to build the detector by running a gradle build:

```shell
$ gradlew build
```

When everything builds properly you can startup the whole system with the the following command:

```shell
$ docker-compose up
```

It will start:
1. The accelerator available at [http://localhost:8080](http://localhost:8080)
2. A *Zookeeper* / *Kafka* instance
3. An Eureka service registry available at [http://localhost:8090](http://localhost:8090)
4. A Zuul gateway that will be used to connect to fetch the collision read model [http://localhost:8091/api/collision](http://localhost:8091/api/collision)
5. The write model
6. Finally the collision read model available at [http://localhost:8081](http://localhost:8081)

If you want to debug your Microservices you can connect with an external debugger to:
1. The port 5005 to debug the *write-model* module
2. The port 5006 to debug the *collision-read-model* module

Be careful with docker this time, if some of your containers die inexplicably then increase the number of cores and RAM memory available for docker. As an example these
are my settings:

![Docker settings](https://raw.githubusercontent.com/manuel-alvarez-alvarez/microservices-workshop/microservices_start/docker-config.png)


## Steps

This time I only marked with ```TODO x``` the steps to complete the aggregates with you collision/particle detection logic:

1. Implement the business logic of the [collision detector](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/microservices_start/write-model/src/main/java/es/malvarez/microservices/wm/aggregate/CollisionDetector.java)         
2. Do the same with the [particle detector](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/microservices_start/write-model/src/main/java/es/malvarez/microservices/wm/aggregate/ParticleDetector.java)

Remember that, in order to have a successful Microserviced architecture, all the tests in [CollisionDetectorTest](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/microservices_start/write-model/src/test/java/es/malvarez/microservices/wm/aggregate/CollisionDetectorTest.java)
and [ParticleDetectorTest](https://github.com/manuel-alvarez-alvarez/microservices-workshop/blob/microservices_start/write-model/src/test/java/es/malvarez/microservices/wm/aggregate/ParticleDetectorTest.java) must pass.


## Homework

Next you can find one exercise that you can try to solve if you have some time left before the workshop ends:

Create another micro service called *statistic-read-model* that will return a JSON string the total number of particles found in all the experiments. 
This new read model should:
* Be registered with the name ```spring.application.name: statistic-read-model```
* Have a single REST endpoint listening at ```/statistic```
* Return JSON like the following:

``` 
{
    "QUARK_UP": 12,
    "QUARK_BOTTOM: 7
    "MUON_NEUTRINO": 23,
    "TAU_NEUTRINO: 82
    ...
}
```

## Troubleshooting

If you face any issues, you can either ask me a question or compare the branches *microservices_start* and *microservices_end* that is a lousy solution implemented by me.






