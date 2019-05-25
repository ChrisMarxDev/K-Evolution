package kevolution.engine

import kevolution.engine.logging.Logger
import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType
import kevolution.repopulation.combinator.Combinator
import kevolution.repopulation.handler.SingleSourceRepopulationHandler
import kevolution.repopulation.handler.AbstractPopulationHandler
import kevolution.repopulation.mutator.Mutator
import kevolution.selection.Selector
import kevolution.selection.TournamentSelector

class Engine<T : Any, C : Comparable<C>>(private val eval: (GenoType<T>) -> C,
                                         var abstractPopulationHandler: AbstractPopulationHandler<T, C>,
                                         private val config: EvolutionConfiguration = EvolutionConfiguration()) {

    constructor(factory: GeneFactory<T>,
                eval: (GenoType<T>) -> C,
                combinator: Combinator<T>,
                mutator: Mutator<T>,
                selector: Selector<T, C> = TournamentSelector(4),
                config: EvolutionConfiguration = EvolutionConfiguration()) : this(eval, SingleSourceRepopulationHandler(factory, selector, combinator, mutator), config)


    var population: List<GenoType<T>>

    private val hallOfFame: HallOfFame<C> = HallOfFame(config.hallOfFameSize)


    init {
        population = ArrayList<GenoType<T>>()
    }

    fun run() {

        resetAllParameters()

        for (i in 0 until config.generations) {
            Logger.generation(i)
            population = processGeneration(population, i)
        }

        val evaluatedPop = getSortedEvaluatedPop(population, config.generations)

        Logger.result(evaluatedPop.subList(0, config.hallOfFameSize), hallOfFame)

    }

    private fun resetAllParameters() {
        hallOfFame.reset()

        population = abstractPopulationHandler.createInitialPopulation(config.populationSize)

    }


    fun processGeneration(population: List<GenoType<T>>, generation: Int): List<GenoType<T>> {
        // evaluate
        val evaluatedPopulation = getSortedEvaluatedPop(population, generation)

        halllOfFameCheck(evaluatedPopulation)

        // create new population through combination and mutation
        return abstractPopulationHandler.handleRepopulation(evaluatedPopulation)
    }

    private fun getSortedEvaluatedPop(population: List<GenoType<T>>, generation: Int): List<GeneInfoWrapper<T, C>> {
        return sortByFitness(evaluateFitness(population, eval, generation))
    }

    private fun halllOfFameCheck(selected: List<GeneInfoWrapper<T, C>>) {
        hallOfFame.processPotentialEntry(selected.first())
    }

    private fun evaluateFitness(population: List<GenoType<T>>, eval: (GenoType<T>) -> C, generation: Int): List<GeneInfoWrapper<T, C>> {
        return population.map { GeneInfoWrapper(it, eval(it), generation) }
    }

    private fun sortByFitness(population: List<GeneInfoWrapper<T, C>>): List<GeneInfoWrapper<T, C>> {
        return population.sortedByDescending { it.fitness }
    }

}