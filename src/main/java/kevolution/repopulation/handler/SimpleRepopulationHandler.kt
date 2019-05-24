package kevolution.repopulation.handler

import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType
import kevolution.repopulation.combinator.Combinator
import kevolution.repopulation.mutator.Mutator
import kevolution.selection.Selector

class SimpleRepopulationHandler<T:Any,C:Comparable<C>>(val selector: Selector<T,C>,val combinator: Combinator<T>, val mutator: Mutator<T>) : RepopulationHandler<T,C>() {

    override fun handleRepopulation(evaluatedPopulation: List<GeneInfoWrapper<T, C>>): List<GenoType<T>> {
        val populationSize = evaluatedPopulation.size
        val selected = selector.select(evaluatedPopulation)


        val newPop = generateCompletePopulation(selected, populationSize - selected.size)

        return newPop.map { mutate(it, mutator) }
    }

    private fun mutate(genoType: GenoType<T>, mutator: Mutator<T>): GenoType<T> {
        return mutator.mutate(genoType)
    }

    private fun generateCompletePopulation(selected: List<GenoType<T>>, toGenerate: Int): List<GenoType<T>> {
        val new = (0..toGenerate).map { generateChild(selected, combinator) }.toMutableList()
        new.addAll(selected)

        return new
    }

    private fun generateChild(selected: List<GenoType<T>>, combinator: Combinator<T>): GenoType<T> {
        return combinator.combine(selected.random(), selected.random())
    }
}