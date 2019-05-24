package kevolution.repopulation.handler

import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType
import kevolution.repopulation.combinator.Combinator
import kevolution.repopulation.mutator.Mutator

class MultiMutatorPopHandler<T:Any,C : Comparable<C>>(val combinator: Combinator<T>, val mutators: List<Mutator<T>>) : RepopulationHandler<T,C>() {
    override fun handleRepopulation(evaluatedPopulation: List<GeneInfoWrapper<T, C>>): List<GenoType<T>> {

//        val newPop = generateCompletePopulation(selected, toGenerate)

//        return newPop.map { mutate(it, mutators) }

        //TODO fix this mess
        return evaluatedPopulation.map { it.genoType }
    }

    private fun mutate(genoType: GenoType<T>, mutators: List<Mutator<T>>): GenoType<T> {
        return mutators.fold(genoType, { gt, mutator -> mutator.mutate(gt) })
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