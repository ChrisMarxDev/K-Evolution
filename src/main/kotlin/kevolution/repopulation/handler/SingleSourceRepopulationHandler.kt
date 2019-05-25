package kevolution.repopulation.handler

import kevolution.engine.GeneFactory
import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType
import kevolution.repopulation.combinator.Combinator
import kevolution.repopulation.mutator.Mutator
import kevolution.selection.Selector

// Very simple repopulation handler that takes in one of each selector, combinator and mutator
open class SingleSourceRepopulationHandler<T : Any, C : Comparable<C>>(val factory: GeneFactory<T>, val selector: Selector<T, C>, val combinator: Combinator<T>, val mutator: Mutator<T>) : PopHandler<T, C>() {
    override fun generate(): GenoType<T> {
        return factory.createGenoType()
    }

    override fun select(input: List<GeneInfoWrapper<T, C>>): List<GenoType<T>> {
        return selector.select(input)
    }

    override fun combine(gt1: GenoType<T>, gt2: GenoType<T>): GenoType<T> {
        return combinator.combine(gt1, gt2)
    }

    override fun mutate(gt: GenoType<T>): GenoType<T> {
        return mutator.mutate(gt)
    }
}