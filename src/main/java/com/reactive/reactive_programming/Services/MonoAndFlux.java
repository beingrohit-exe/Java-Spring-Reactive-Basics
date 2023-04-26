package com.reactive.reactive_programming.Services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class MonoAndFlux {
    public Flux<String> fluxService(){
        /**
         * log() will emit all the event that has to happen here
         */
        return Flux.fromIterable(List.of("Rohit", "Parihar", "Hello", "World")).log();
    }

    public Flux<String> fluxMap(){
        return Flux.fromIterable(List.of("Rohit", "Parihar", "Hello", "World"))
                //.map(s -> s.toUpperCase())
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fluxFlatmap(){
        return Flux.fromIterable(List.of("Rohit", "Parihar", "Hello", "World"))
                //.map(s -> s.toUpperCase())
                .flatMap(a -> Flux.just(a.split("")))
                .log();
    }

    public Flux<String> fluxFlatmapWithDelay(){
        return Flux.fromIterable(List.of("Rohit", "Parihar", "Hello", "World"))
                //.map(s -> s.toUpperCase())
                .flatMap(a -> Flux.just(a.split(""))
                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)
                        )))
                .log();
    }
    public Flux<String> fluxWithConcatMap(){
        return Flux.fromIterable(List.of("Rohit", "Parihar", "Hello", "World"))
                //.map(s -> s.toUpperCase())
                .concatMap(a -> Flux.just(a.split(""))
                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)
                        )))
                .log();
    }


    public Flux<String> fluxFilter(){
        return Flux.fromIterable(List.of("Rohit", "Parihar", "Hello", "World", "Apple", "Banana"))
                .filter(s-> s.length()>5);
    }

    public Mono<String> monoService(){
        return Mono.just("Rohit");
    }

    public Mono<List<String>> monoFlatmap(){
        return Mono.just("Rohit")
                .flatMap(a-> Mono.just(List.of(a.split(""))))
                .log();
    }

    public Flux<String> monoFlatMapMany(){
        return Mono.just("Rohit")
                .flatMapMany(a -> Flux.just(a.split("")))
                .log();
    }

    public Flux<String> fluxTransform(int number){
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(a -> a.length()>5);
        return Flux.fromIterable(List.of("Rohit", "Parihar", "Hello", "World", "Apple", "Banana"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fluxTransformDefaultIfEmpty(int number){
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(a -> a.length()>number);
        return Flux.fromIterable(List.of("Rohit", "Parihar", "Hello", "World", "Apple", "Banana"))
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
    }
    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number) {

        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("Pineapple","Jack Fruit")
                        .transform(filterData))
                .log();

    }

    public Flux<String> fruitsFluxConcat() {
        var fruits = Flux.just("Mango","Orange");
        var veggies = Flux.just("Tomato","Lemon");

        return Flux.concat(fruits,veggies);
    }

    public Flux<String> fruitsFluxConcatWith() {
        var fruits = Flux.just("Mango","Orange");
        var veggies = Flux.just("Tomato","Lemon");

        return fruits.concatWith(veggies);
    }


    public Flux<String> fruitsMonoConcatWith() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");

        return fruits.concatWith(veggies);
    }

    public Flux<String> fruitsFluxMerge() {
        var fruits = Flux.just("Mango","Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato","Lemon")
                .delayElements(Duration.ofMillis(75));

        return Flux.merge(fruits,veggies);
    }

    public Flux<String> fruitsFluxMergeWith() {
        var fruits = Flux.just("Mango","Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato","Lemon")
                .delayElements(Duration.ofMillis(75));

        return fruits.mergeWith(veggies);
    }

    public Flux<String> fruitsFluxMergeWithSequential() {
        var fruits = Flux.just("Mango","Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato","Lemon")
                .delayElements(Duration.ofMillis(75));

        return Flux.mergeSequential(fruits,veggies);
    }

    public Flux<String> fruitsFluxZip() {
        var fruits = Flux.just("Mango","Orange");
        var veggies = Flux.just("Tomato","Lemon");

        return Flux.zip(fruits,veggies,
                (first,second) -> first+second).log();
    }

    public Flux<String> fruitsFluxZipWith() {
        var fruits = Flux.just("Mango","Orange");
        var veggies = Flux.just("Tomato","Lemon");

        return fruits.zipWith(veggies,
                (first,second) -> first+second).log();
    }

    public Flux<String> fruitsFluxZipTuple() {
        var fruits = Flux.just("Mango","Orange");
        var veggies = Flux.just("Tomato","Lemon");
        var moreVeggies = Flux.just("Potato","Beans");

        return Flux.zip(fruits,veggies,moreVeggies)
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3());
    }

    public Mono<String> fruitsMonoZipWith() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");

        return fruits.zipWith(veggies,
                (first,second) -> first+second).log();
    }


    public Mono<String> fruitMono() {
        return Mono.just("Mango").log();
    }


    public Flux<String> fruitsFluxFilterDoOn(int number) {
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .filter(s -> s.length() > number)
                .doOnNext(s -> {
                    System.out.println("s = " + s);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("subscription.toString() = " + subscription.toString());
                })
                .doOnComplete(() -> System.out.println("Completed!!!"));
    }


    public Flux<String> fruitsFluxOnErrorReturn() {
        return Flux.just("Apple","Mango")
                .concatWith(Flux.error(
                        new RuntimeException("Exception Occurred")
                ))
                .onErrorReturn("Orange");
    }

    public Flux<String> fruitsFluxOnErrorContinue() {
        return Flux.just("Apple","Mango","Orange")
                .map(s -> {
                    if (s.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("Exception Occurred");
                    return s.toUpperCase();
                })
                .onErrorContinue((e,f) -> {
                    System.out.println("e = " + e);
                    System.out.println("f = " + f);
                });
    }

    public Flux<String> fruitsFluxOnErrorMap() {
        return Flux.just("Apple","Mango","Orange")
                .checkpoint("Error Checkpoint1")
                .map(s -> {
                    if (s.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("Exception Occurred");
                    return s.toUpperCase();
                })
                .checkpoint("Error Checkpoint2")
                .onErrorMap(throwable -> {
                    System.out.println("throwable = " + throwable);
                    return new IllegalStateException("From onError Map");
                });
    }

    public Flux<String> fruitsFluxOnError() {
        return Flux.just("Apple","Mango","Orange")
                .map(s -> {
                    if (s.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("Exception Occurred");
                    return s.toUpperCase();
                })
                .doOnError(throwable -> {
                    System.out.println("throwable = " + throwable);

                });
    }

    public static void main(String[] args) {
        MonoAndFlux monoAndFlux = new MonoAndFlux();

        /**
         * Without method reference
         */
        //monoAndFlux.fluxService().subscribe(s-> System.out.println(s));

        /**
         * With method reference
         */
        //monoAndFlux.fluxService().subscribe(System.out::println);

        //monoAndFlux.monoService().subscribe(System.out::println);

        /**
         * Flux Map
         */
        monoAndFlux.fluxMap().subscribe(System.out::println);
    }
}
