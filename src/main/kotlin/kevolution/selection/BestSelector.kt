package kevolution.selection

import kevolution.extensions.cleanPercentage
import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType

class BestSelector<T : Any, C : Comparable<C>>(percentage: Double) : Selector<T, C>() {

    val percent = percentage.cleanPercentage()


    override fun select(input: List<GeneInfoWrapper<T, C>>): List<GenoType<T>> {
        return input.take((input.size * percent).toInt()).map { it.genoType }
    }

}