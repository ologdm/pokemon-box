package com.example.pokemonbox.data

/*
1.1 Total Pokemon Count & List
'https://pokeapi.co/api/v2/pokemon/'

1.2 Total Pokemon List with Pagination
'https://pokeapi.co/api/v2/pokemon/?limit=25'


2. Selected Pokemon details by ID or Name
'https://pokeapi.co/api/v2/pokemon/1'


3. List of Pokemon by Egg group
'https://pokeapi.co/api/v2/egg-group/monster'


4. List of Pokemon by Type
'https://pokeapi.co/api/v2/type/normal'


5. Gender type of Pokemon
'https://pokeapi.co/api/v2/gender/?name=bulbasaur'


6. Pokemon Species by Colour
'https://pokeapi.co/api/v2/pokemon-color/green'


7. Pokemon Species by Shape
'https://pokeapi.co/api/v2/pokemon-shape/tentacles'

8. Pokemon Species by Speed
'https://pokeapi.co/api/v2/growth-rate/medium-slow'


##################################################################
1. (disponibile su ...pokemon/{id on name})
- Ogni Pokémon ha uno o due dei 18 tipi totali:
Normale, Fuoco, Acqua, Erba, Elettro, Ghiaccio,
Lotta, Veleno, Terra, Volante, Psico, Coleottero,
Roccia, Spettro, Drago, Buio, Acciaio e Folletto.


2. (disponibile su ........pokemon-species/{id or name})
- "Flavor Text" - contiene il testo di descrizione
che fornisce informazioni narrative o di contesto
sul Pokémon

- "Genera" - definisce brevemente la tipologia di Pokémon
(es: "Seed Pokémon" per Bulbasaur o "Mouse Pokémon" per Pikachu).

 */


/* DETAIL JSON - 18 campi base
{
    "id": 1,
    "name": "bulbasaur",
    "base_experience": 64,
    "height": 7,
    "is_default": true,
    "order": 1,
    "weight": 69,
    "abilities": [
        {
            "ability": {
                "name": "overgrow",
                "url": "https://pokeapi.co/api/v2/ability/65/"
            },
            "is_hidden": false,
            "slot": 1
        },
        {
            "ability": {
                "name": "chlorophyll",
                "url": "https://pokeapi.co/api/v2/ability/34/"
            },
            "is_hidden": true,
            "slot": 3
        }
    ],
    "types": [
        {
            "slot": 1,
            "type": {
                "name": "grass",
                "url": "https://pokeapi.co/api/v2/type/12/"
            }
        },
        {
            "slot": 2,
            "type": {
                "name": "poison",
                "url": "https://pokeapi.co/api/v2/type/4/"
            }
        }
    ],
    "stats": [
        {
            "base_stat": 45,
            "effort": 0,
            "stat": {
                "name": "hp",
                "url": "https://pokeapi.co/api/v2/stat/1/"
            }
        },
        {
            "base_stat": 49,
            "effort": 0,
            "stat": {
                "name": "attack",
                "url": "https://pokeapi.co/api/v2/stat/2/"
            }
        }
    ],
    "sprites": {
        "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
        "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png"
    },
    "moves": [     // ############################################# la parte piu lunga 10k righe
        {
            "move": {
                "name": "razor-wind",
                "url": "https://pokeapi.co/api/v2/move/13/"
            },
            "version_group_details": [
                {
                    "level_learned_at": 0,
                    "version_group": {
                        "name": "red-blue",
                        "url": "https://pokeapi.co/api/v2/version-group/1/"
                    },
                    "move_learn_method": {
                        "name": "machine",
                        "url": "https://pokeapi.co/api/v2/move-learn-method/4/"
                    }
                }
            ]
        }
    ],

    "species": {
        "name": "bulbasaur",
        "url": ""

    // !!! altre
    "past_abilities": [],

    "past_types": [],
 */