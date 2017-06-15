# From Monoliths to Microservices using CQRS and Event Sourcing

## Introduction

In a Microservices architecture state is distributed across many applications and databases. Keeping all the microservices consistent is a non trivial task and here is where CQRS and Event Sourcing really shine.

In this workshop we will move from a terrible Monolith to a nice Microservices app using CQRS/ES as the middle man. The recipe to Microservices that we will follow here will be:
1. Start with an ugly Monolith.
2. Add some CQRS and ES to the Monolith using Spring Streams and Apache Kafka.
3. Break your CQRS Monolith into small microservices using Spring Cloud.

## Prerequisites

### Git

You must have a working installation of GIT in your machine (Git Downloads)[https://git-scm.com/downloads]. You can test that it works by running the following command:

```shell
$ git clone https://github.com/manuel-alvarez-alvarez/microservices-workshop.git
```

By the way, it will clone the main repository for the workshop (two birds one stone).


### JDK 8

Make sure you have a working installation of the JDK 8 in your machine ([Java SE Development Kit 8 Downloads](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)). You can test it by running the following command in a console:

```shell
$ javac -version
```

### Docker

You must have a working installation of Docker as well, the community verison will work just fine ([Download Docker Community Edition](https://www.docker.com/community-edition#/download)). You can test it by running the following command in a console:

```shell
$ docker run docker/whalesay cowsay I'm ready to rock
```

You should see something like this:
```
 ___________________
< I'm ready to rock >
 -------------------
    \
     \
      \
                    ##        .
              ## ## ##       ==
           ## ## ## ##      ===
       /""""""""""""""""___/ ===
  ~~~ {~~ ~~~~ ~~~ ~~~~ ~~ ~ /  ===- ~~~
       \______ o          __/
        \    \        __/
          \____\______/
```        

In order to prevent downloading too much data the day of the conference I recomment you to prefetch some of the docker images we are going to use:

```shell
$ docker pull openjdk:8-alpine
```

```shell
$ docker pull wurstmeister/zookeeper:3.4.6
```

```shell
$ docker pull wurstmeister/kafka:0.10.2.1
```

## Domain

> **Disclaimer**. All resemblance with reality is pure fiction, nothing shown here is accurate from a physics point of view.

The purpose of this workshop is to build a particle detector that will try to find evidence of the theoretical particle called [Chameleon](https://en.wikipedia.org/wiki/Chameleon_particle). The current [Standard Model](https://en.wikipedia.org/wiki/Standard_Model) includes the following particles:

![Standard Model](https://upload.wikimedia.org/wikipedia/commons/0/00/Standard_Model_of_Elementary_Particles.svg)

The workshop will provide you an [accelerator](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/tree/master/accelerator) that will send [snapshots](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/Snapshot.java) every second to a RabbitMQ containing the lists of detected particles in the different [experiments](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/Experiment.java). For each [deteced particle](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/DetectedParticle.java) you will find the following information:

1. The  [experiment](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/Experiment.java) where it was detected
2. The [spin](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/Spin.java) of the particle
3.  The [charge](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/Charge.java) of the particle
4.  The mass in MeV/c2 (it's always an exact number, e.g. for an electron it will always be 0.511MeV/c2 and for the tau neutrino it will be 15.5 MeV/c2)

The tool we are about to build should:

1. Detect collisions inside [snapshots](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/Snapshot.java). A collision happens when three or more particles are detected in the same [experiment](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/Experiment.java)
2.  Analize each one of the collisions and get the [type](https://github.com/manuel-alvarez-alvarez/microservices-workshop-common/blob/master/api/src/main/java/es/malvarez/microservices/api/ParticleType.java) of the particles to find the elusive [chameleons](https://en.wikipedia.org/wiki/Chameleon_particle)


## Workflow

As stated before, we are going to follow the next steps in the workshop:

1. Build a monolith in order to get used to the domain and some technologies we will use later
2. Convert the monolith ot a CQRS/ES application
3. Break the CQRS/ES application into different microservices

In order to do it there are several branches (the ones with the suffix *_start* are the starting point for the step, and the ones with *_end* contain my very own lousy solution).

* Monolith
1. ```git checkout monolith_start```
2. ```git checkout monolith_end```
* CQRS/ES
1. ```git checkout cqrs_start```
2. ```git checkout cqrs_end```
* Microservices
1. ```git checkout microservices_start```
2. ```git checkout microservices_end```

So let's start buidling the **Monolith**

```shell
$ git checkout monolith_start
```
