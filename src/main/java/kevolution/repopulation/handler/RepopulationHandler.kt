package kevolution.repopulation.handler

import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType

abstract class RepopulationHandler<T : Any,C:Comparable<C>>() {
    abstract fun handleRepopulation(evaluatedPopulation: List<GeneInfoWrapper<T,C>>): List<GenoType<T>>
}