

kompose convert -o ./k8s

kubectl apply -f ./k8s



# Development Environment

* Setting up a local machine for developing Kafka applications

## Step 0: Machine Configuration

Machine configuration is based on a lot of personal preferences. This showcases some of the available options, but
specific choices are personal preferences.

Here we cover the core pieces as part of our developer setup.

* Terminal
* Shell
* Java
* Container Runtime

### Terminal

iterm2
alacritty
warp

Terminal Multiplexor - tmux


### Shell

zsh
bash

```
export PATH=/usr/local/bin
export PATH=$PATH:/usr/bin
export PATH=$PATH:/bin
export PATH=$PATH:/usr/sbin
export PATH=$PATH:/sbin
export PATH=$PATH:/opt/homebrew/bin
```

#### Bash

#### ZSH

##### ZShell Auto Completions

### Java

* You don't need to install Java 17 if you are deploying your application on Java 17 JVMs.

Install the most-recent version of Java you can, that your build tool and build plugins will allow. 
Java can easily be configured to set source version and target version, so having Java 22 build Java 17 byte-code is not difficult.
The difficulty is usually with plugins that leverage byte-code manipulation and internal Java APIs.

* If you do need multiple version of Java installed, you still do not need to use a package manager to do so; but you can if you like.

  * The `java.net` archive is the best way to get multiple installations set up locally, if you do like to manage it yourself.

    * [JDK Archive](https://jdk.java.net/archive/)
    
    * On MacOS installing them in `/Library/Java/JavaVirtualMachines` would align where MacOS would install them.

  * Supporting multiple versions is just setting JAVA_HOME to the version you are needing to use.
  
    ``` 
    export JAVA21_HOME=/Library/Java/JavaVirtualMachines/jdk-21.0.2.jdk/Contents/Home
    export JAVA22_HOME=/Library/Java/JavaVirtualMachines/jdk-22.0.1.jdk/Contents/Home
    export JAVA_HOME=$JAVA22_HOME
    ```


### Container Runtime

#### Docker
![img.png](img.png)


#### Rancher

#### ContainerD

#### Podman

#### Minikube




## Step 1: Command Line Interface

The installation of command line tools is optional, as the containers themselves can be used to gain access to the Apache Kafka command line tools.
However, associating your command line tools to your containers will require more steps and you end up spending time writing wrapping scripts, to make your life easier.

### Option A: Confluent Community Installation

The benefit of using the Confluent Community Installation of Apache Kafka is the additional tools for supporting Avro and Protobuf.
Specific console consumers and producers are provided: `kafka-avro-console-producer`, `kafka-avro-console-consumer`, `kafka-protobuf-console-producer`, and `kafka-protobuf-console-consumer`.
In addition, the command suffix of `.sh` is removed; which I am a big fan. 
While the implementation of these commands are scripts, I find it confusing and leaks the implementation details to the user of those commands.

I recommend bookmark the [installing_cp](https://docs.confluent.io/platform/current/installation/installing_cp/zip-tar.html) webpage, and using it to obtain the latest version to install.

```
cd ~/Downloads
curl -O https://packages.confluent.io/archive/7.6/confluent-community-7.6.1.tar.gz
cd /usr/local
sudo tar xfv ~/Downloads/confluent-community-7.6.1.tar.gz
sudo mkdir /usr/local/confluent-7.6.1/logs
sudo chmod 777 /usr/local/confluent-7.6.1/logs
sudo ln -s ./confluent-7.6.1 ./confluent
```

If this is your first installation, add `confluent/bin` to your classpath. Use the symbolic link so you don't have to do this change when you upgrade to a newer version.

```
export PATH=$PATH:/usr/local/confluent/bin
```

### Option B: Apache Kafka Installation

https://kafka.apache.org/downloads

```
cd /usr/local
sudo tar xfv ~/Downloads/kafka_2.13-3.7.0.tgz
sudo ln -s ./kafka_2.13-3.7.0 ./kafka
```

If this is your first installation, add `kafka/bin` to your classpath. Use the symbolic link so you don't have to do this change when you upgrade to a newer version.

```
export PATH=$PATH:/usr/local/kafka/bin
```

### Option C: Container

If you do not want to install Kafka locally, you can use the Kafka containers themselves.
Also, if the container is configured to add a Java agent for metric reporting, that environment variable needs to be unset.
Also, the Avro and Protobuf console cools tools are deployed in the schema-registry container only.
If you are not using Confluent based containers, then the syntax and CLI availability would vary.

```
docker exec -it $BROKER sh -c "unset KAFKA_OPTS; $CMD"
```

In the cluster installations provided, there is a wrapper script to make this easier.
Also KAFKA_OPTS may be defined in the container's environment, be sure to unset that before running the command.

## Step 2: Cluster Installation 

### Docker Compose

#### Main Cluster
#### Minimal Install
### 
## Step 3: Installation Validation

## Step 4: Kafka Connect

## Step 5: Producer

## Step 6: Consumer

## Step 7: Kafka Streams