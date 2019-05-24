# K-Evolution

Evolutionary Algorithm Framework for Kotlin

Hobby Project by Christopher Marx

## Example Usage

1. Define a gene creation factory 
```kotlin
fun createGene(): Int {
                return Random.nextInt(100)
            }
            
            val factory: GeneFactory<Int> = GeneFactory({ createGene() }, 20)
```
This is a factory method that creates a geno type of random 20 integers from 0 to 100

2. Define an fitness method

```kotlin
fun eval(gt: GenoType<Int>): Double {
            return gt.genes.fold(0, Int::plus).toDouble()
        }
```
Just a simple method that adds all Integers

3. Optionally define some evolution parameters

```kotlin
val config = EvolutionConfiguration(generations = 1000, populationSize = 200)
```

4. Create an engine object, supplying it with the factory, evaluation method, a combinator, a mutator, a selector and the config.

```kotlin
val engine = Engine(
        factory= factory, 
        eval = { eval(it) },
        combinator = RandomPointCombinator(),
        mutator = RandomValueMutator(0.05, { randomInt() }),
        selector = TournamentSelector(4),
        config = config)
```
Definitely need to document framework specific combinators, mutators, selectors

5. Run that bad boy and harvest the results 

```kotlin
engine.run()
val goodPopulation = engine.population
```





## TODO
+ Example that doesn't suck ass 

+ Everything else

+ Better Evolution Strategies

+ More combinators, mutators, and selectors

+ Doumentation