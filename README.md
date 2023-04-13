# basic-benchmarks

JMH and JCStress benchmarks of various field access patterns.

Tested with Java 17.

#### DISCLAIMER: Still Work-In-Progress

## Build and run

### JMH

```shell
mvn clean install -Pjmh && java -jar target/benchmarks.jar jmh.* -wi 8 -w 1 -f 2 -r 1 -i 5
```

### JCStress

```shell
mvn clean install -Pjcstress && java -jar target/jcstress.jar -v
```