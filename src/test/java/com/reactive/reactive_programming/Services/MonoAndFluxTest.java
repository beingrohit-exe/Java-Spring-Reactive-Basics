package com.reactive.reactive_programming.Services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MonoAndFluxTest {

    MonoAndFlux monoAndFlux = new MonoAndFlux();

    @Test
    void fluxService() {
        var fluxData = monoAndFlux.fluxService();
        StepVerifier.create(fluxData)
                .expectNext("Rohit", "Parihar", "Hello", "World")
                .verifyComplete();
    }

    @Test
    void monoService() {
        var monoData = monoAndFlux.monoService();
        StepVerifier.create(monoData)
                .expectNext("Rohit")
                .verifyComplete();
    }

    @Test
    void fluxMap() {
        var fluxData = monoAndFlux.fluxMap();
        StepVerifier.create(fluxData)
                .expectNext("Rohit".toUpperCase(), "Parihar".toUpperCase(), "Hello".toUpperCase(), "World".toUpperCase())
                .verifyComplete();
    }

    @Test
    void fluxFilter() {
        var fluxData = monoAndFlux.fluxFilter();
        StepVerifier.create(fluxData)
                .expectNext("Parihar", "Banana")
                .verifyComplete();
    }

    @Test
    void fluxFlatmap() {
        var fluxData = monoAndFlux.fluxFlatmap();
        StepVerifier.create(fluxData)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void fluxFlatmapWithDelay() {
        var fluxData = monoAndFlux.fluxFlatmapWithDelay();
        StepVerifier.create(fluxData)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void fluxWithConcatMap() {
        var fluxData = monoAndFlux.fluxWithConcatMap();
        StepVerifier.create(fluxData)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void monoFlatmap() {
        var monoData = monoAndFlux.monoFlatmap();
        StepVerifier.create(monoData)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void monoFlatMapMany() {
        var monoData = monoAndFlux.monoFlatMapMany();
        StepVerifier.create(monoData)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fluxTransform() {
        var fluxData = monoAndFlux.fluxTransform(5);
        StepVerifier.create(fluxData)
                .expectNext("Parihar", "Banana")
                .verifyComplete();
    }

    @Test
    void fluxTransformDefaultIfEmpty() {
        var fluxData = monoAndFlux.fluxTransformDefaultIfEmpty(10);
        StepVerifier.create(fluxData)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        var fruitsFlux
                = monoAndFlux.fruitsFluxTransformSwitchIfEmpty(8);

        StepVerifier.create(fruitsFlux)
                .expectNext("Pineapple","Jack Fruit")
                .verifyComplete();

    }

    @Test
    void fruitsFluxConcat() {

        var fruitsFlux = monoAndFlux.fruitsFluxConcat().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","Orange","Tomato","Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWith() {
        var fruitsFlux = monoAndFlux.fruitsFluxConcatWith().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","Orange","Tomato","Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsMonoConcatWith() {

        var fruitsFlux = monoAndFlux.fruitsMonoConcatWith().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitsFlux = monoAndFlux.fruitsFluxMerge().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","Tomato","Orange","Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitsFlux = monoAndFlux.fruitsFluxMergeWith().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","Tomato","Orange","Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWithSequential() {
        var fruitsFlux = monoAndFlux
                .fruitsFluxMergeWithSequential().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","Orange","Tomato","Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitsFlux = monoAndFlux
                .fruitsFluxZip().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("MangoTomato","OrangeLemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var fruitsFlux = monoAndFlux
                .fruitsFluxZipWith().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("MangoTomato","OrangeLemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var fruitsFlux = monoAndFlux
                .fruitsFluxZipTuple().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("MangoTomatoPotato","OrangeLemonBeans")
                .verifyComplete();
    }

    @Test
    void fruitsMonoZipWith() {

        var fruitsFlux = monoAndFlux
                .fruitsMonoZipWith().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("MangoTomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterDoOn() {
        var fruitsFlux = monoAndFlux
                .fruitsFluxFilterDoOn(5).log();

        StepVerifier.create(fruitsFlux)
                .expectNext("Orange","Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorReturn() {
        var fruitsFlux = monoAndFlux
                .fruitsFluxOnErrorReturn().log();

        StepVerifier.create(fruitsFlux)
                .expectNext("Apple","Mango","Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorContinue() {
        var fruitsFlux = monoAndFlux
                .fruitsFluxOnErrorContinue().log();

        StepVerifier.create(fruitsFlux)
                .expectNext("APPLE","ORANGE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorMap() {
        //Hooks.onOperatorDebug();
        var fruitsFlux = monoAndFlux
                .fruitsFluxOnErrorMap().log();

        StepVerifier.create(fruitsFlux)
                .expectNext("APPLE")
                .expectError(IllegalStateException.class)
                .verify();
    }

    @Test
    void fruitsFluxOnError() {
        var fruitsFlux = monoAndFlux
                .fruitsFluxOnError().log();

        StepVerifier.create(fruitsFlux)
                .expectNext("APPLE")
                .expectError(RuntimeException.class)
                .verify();
    }



}