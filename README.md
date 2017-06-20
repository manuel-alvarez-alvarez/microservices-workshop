# From Monoliths to Microservices using CQRS and Event Sourcing

## The final Microservices

Now it's time to separate things and put them in different modules / applications / services ... We have already separated
two things:
* Our aggregates that went to the *collision-detector* and *particle-detector* modules
* Our only read model that went to the *collision-read-model*, we have also configured this module as a service that will be
registered with Eureka

## Homework

This time there are no TODOs, now you have to be brave and try to do the following exercises:

### 1. Deploy two replicas of the collision read model

To do this, you only have to touch the *docker-compose.yml* file, but be careful with the consumer groups in *Kafka* : ```KAFKA_GROUP=collision-read-model```

### 2. Create a new read model / microservice

Create another micro service called *statistics-read-model* that will return a JSON string with the total number of particles found in all the experiments. 
This new read model should:
* Be registered with the name ```spring.application.name: statistics-read-model```
* Have a single REST endpoint listening at ```/api```
* Be registered in docker with ```API_HREF=http://localhost:8081/statistics/api``` and ```API_MODE=statistics```
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

> The UI for this new read model will be automatically provided by the ```es.malvarez.microservices.workshop:web``` plugin

If you do it correctly it will be available at [http://localhost:8081/statistics/](http://localhost:8081/statistics/)

### 3. Create a new read model and use differnt set of technologies (brave hearts only)

Think about another possible read model that can be built using the existing events of our ubiquitous language and try to build a new read model / microservice using a totally different set of technologies (for instance Node.js).

> **Hint**. You can use a [sidecar](http://projects.spring.io/spring-cloud/spring-cloud.html#_polyglot_support_with_sidecar) to do the linking between spring cloud and your read model.

## Build and run

You can build the application just by running:

```shell
$ gradlew build
```

To run the whole system you only have to execute the command:

```shell
$ docker-compose up
```

It will start:
1. The accelerator available at [http://localhost:8080](http://localhost:8080)
2. A *Zookeeper* / *Kafka* instance
3. An Eureka service registry
4. A Zuul gateway that will be used to connect to the collision read model [http://localhost:8081/collisions/](http://localhost:8081/collisions/)
5. The collision detector write model
6. The particle detector read model
7. Finally, the collision read model

Observe how this time we access the whole application only through the Zuul gateway

Be careful with docker this time, if some of your containers die inexplicably then increase the number of cores and RAM memory available for docker. As an example these
are my settings:

![Docker settings](https://raw.githubusercontent.com/manuel-alvarez-alvarez/microservices-workshop/microservices_start/docker-config.png)

## Troubleshooting

If you face any issues, you can either ask me a question or compare the branches *microservices_start* and *microservices_end* that is a lousy solution implemented by me.





