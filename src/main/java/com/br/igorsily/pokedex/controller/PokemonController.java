package com.br.igorsily.pokedex.controller;

import com.br.igorsily.pokedex.model.Pokemon;
import com.br.igorsily.pokedex.model.PokemonEvent;
import com.br.igorsily.pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping(PokemonController.REQUEST_MAPPING)
public class PokemonController {

    protected final static String REQUEST_MAPPING = "pokemons";

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping
    public Flux<Pokemon> getAllPokemons() {
        return this.pokemonRepository.findAll();
    }

    ;

    @GetMapping("{id}")
    public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable("id") String id) {
        return this.pokemonRepository.findById(id)
                .map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        return this.pokemonRepository.save(pokemon);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable("id") String id, @RequestBody Pokemon pokemon) {

        return this.pokemonRepository.findById(id).flatMap(existingPokemon -> {
            existingPokemon.setNome(pokemon.getNome());
            existingPokemon.setCategoria(pokemon.getCategoria());
            existingPokemon.setHabilidade(pokemon.getHabilidade());
            existingPokemon.setPeso(pokemon.getPeso());
            return this.pokemonRepository.save(existingPokemon);
        }).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deletePokemon(@PathVariable("id") String id) {
        return this.pokemonRepository.findById(id).flatMap(existingPokemon -> this.pokemonRepository.delete(existingPokemon).then(
                Mono.just(ResponseEntity.ok().<Void>build())
        )).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllPokemon() {
        return this.pokemonRepository.deleteAll();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getPokemonEvents() {
        return Flux.interval(Duration.ofSeconds(5)).map(val -> new PokemonEvent(val, "Evento de pokemonn"));
    }
}
