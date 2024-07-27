# kafka-binder-demo
Demo project for Spring Cloud Stream with Kafka Binder

This Project is created from Spring Initialized with the given options below:
![Spring Initializer](/documents/img/spring_initializer.png?raw=true "Spring Initializer")

We dont need kafka dependency so it is commented in the pom.xml

This project shows how to define producer, consumer and processor functions, and how to
map these functions to the Kafka topics. The main aim is binding the functions to the Kafka 
topics.

Kafkalytic is used for displaying Kakfa topics under Intellij

For supplier functions : the aim is create something to produce. So it needs
a topic to push data in it.  So if you create a bean with Supplier, Kafka Binder jar 
understand it is a supplier and needs a topic so it creates directly a topic with bean name with out prefix 
and also with an integer. For example when you create a bean with name producerBinding  then topic name will
be directly like  producerBinding-out-0. please chek if we run the application given below
![Producer Binding](/documents/img/producer_binding.png?raw=true "Producer Binding")

So we need to map these bean to Kafka topics with binding configuration.
Firstly we need to add bean names to the function definition configuartion
later on we need to add bindings as given below.

![Result](/documents/img/result.png?raw=true "Result")

* Red ones are the functions and definitions.
* Light Blue ones are the topics we define in application.yml so Kafka created them.
* Green one is Supplier Binding.  Binding from : producerBinding-out-0 -> processor-topic 
What does it mean? It means Supplier creates data and put it to processor-topic
* Yellow ones is related with Processor. Processor is a Function takes one input and creates
an output. So thats why it needs a topic read and topic out. So what supplier creates
is readed by Processor and processed and put it to another topic .
  processorBinding-in-0 -> (bind to) processor-topic ( this is the topic supplier produces data)
  and processorBinding-out-0 -> (bind to) consumer-topic ( this is the topic after processor function 
process and put data)
* Orange one is the consumer topic. consumerBinding-in-0 -> consumer-topic

As you see in dark blue light is the output of these application.
Supplier creates a data with String 'NEW DATA' written. then it push it to processor topic.
Processor function reads the data from processor topic, appends current-time-millis to the data of 'NEW DATA' string.
and push it to consumer topic. 
Consumer function reads this data and prints to console.

Thats All!!!

Have Fun.