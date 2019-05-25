package kevolution.repopulation.handler

import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType
import kevolution.repopulation.combinator.Combinator

abstract class PopHandler<T : Any, C : Comparable<C>> : AbstractPopulationHandler<T, C>() {

    override fun handleRepopulation(evaluatedPopulation: List<GeneInfoWrapper<T, C>>): List<GenoType<T>> {
        val populationSize = evaluatedPopulation.size
        val selected = select(evaluatedPopulation)

        val newPop = generateCompletePopulation(selected, populationSize - selected.size)

        return newPop.map { mutate(it) }
    }

    private fun generateCompletePopulation(selected: List<GenoType<T>>, toGenerate: Int): List<GenoType<T>> {
        val new = (0 until toGenerate).map { generateChild(selected) }.toMutableList()
        new.addAll(selected)

        return new
    }

    private fun generateChild(selected: List<GenoType<T>>): GenoType<T> {
        return combine(selected.random(), selected.random())
    }
}