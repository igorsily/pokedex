package com.br.igorsily.pokedex;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestFlux {

    @Test
    public void testeFlux1() {
        Flux.empty();
    }

    @Test
    public void testeFlux2() {
        Flux<String> flux = Flux.just("Pokemon");
        flux.subscribe(System.err::println);
    }

    @Test
    public void testeFlux3() {
        Flux<String> flux = Flux.just("Sharizard", "Blastoide", "Pixachua");
        flux.subscribe(System.err::println);
    }


}
