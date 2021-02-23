package com.br.igorsily.pokedex;

import com.br.igorsily.pokedex.model.Pokemon;
import com.br.igorsily.pokedex.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokedexApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init(ReactiveMongoOperations operations, PokemonRepository pokemonRepository) {
//        return args -> {
//            Flux<Pokemon> pokemonFlux = Flux.just(
//                    new Pokemon("Bulbassauro", "Semente", "OverGrow", 6.09),
//                    new Pokemon("Charizard", "Fogo", "Blaze", 90.05),
//                    new Pokemon("Blastoise", "Marisco", "Torrente", 6.09)
//            ).flatMap(pokemonRepository::save);
//
//            pokemonFlux.thenMany(pokemonRepository.findAll()).subscribe(System.err::println);
//        };
//    }
}
