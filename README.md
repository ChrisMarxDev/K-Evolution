# K-Evolution

Evolutionary Algorithm Framework for Kotlin

Hobby Project by Christopher Marx
## Installation
### Gradle
```groovy
allprojects {
    repositories {
        // Add this to repositories
        maven { url 'https://jitpack.io' }
    }
}



dependencies {
    // Add this to dependencies
    implementation 'com.github.ChrisMarxDev:K-Evolution:0.1'
}
```

### Maven
```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
```xml
<dependency>
    <groupId>com.github.ChrisMarxDev</groupId>
    <artifactId>K-Evolution</artifactId>
    <version>0.1</version>
</dependency>
```


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

## License

MIT License

Copyright (c) 2019 Christopher Marx

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.



## TODO
+ Examples that don't suck ass 

+ Everything else

+ Better Evolution Strategies

+ More combinators, mutators, and selectors

+ Better distinction between ordered mutators, selectors

+ Neural Networks

+ Doumentation

+ Convergence Tracker, general statistics

+ Overall improvements

+ Seriously. Overall improvements