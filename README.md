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
