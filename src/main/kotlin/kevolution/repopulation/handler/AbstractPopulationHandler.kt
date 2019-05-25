package kevolution.repopulation.handler

import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType

abstract class AbstractPopulationHandler<T : Any, C : Comparable<C>>() {

    // Blanket function to handle the whole repopulation in case there is desire to build new kinds of combination mutation orders
    abstract fun handleRepopulation(evaluatedPopulation: List<GeneInfoWrapper<T, C>>): List<GenoType<T>>

    fun createInitialPopulation(populationSize: Int): List<GenoType<T>> {
        return (0 until populationSize).map { generate() }
    }

    // Function that generates a geno type (List of traits)
    abstract fun generate(): GenoType<T>

    // Function that selects a sublist of genotypes, based on the Wrapper class that contains additional information such as its fitness
    abstract fun select(input: List<GeneInfoWrapper<T, C>>): List<GenoType<T>>

    // Crossover method to combine two geno types and create a new one
    abstract fun combine(gt1: GenoType<T>, gt2: GenoType<T>): GenoType<T>

    // Method that mutates a genotype
    abstract fun mutate(gt: GenoType<T>): GenoType<T>
}