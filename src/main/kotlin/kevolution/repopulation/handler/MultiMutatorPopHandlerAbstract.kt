package kevolution.repopulation.handler

import kevolution.engine.GeneFactory
import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType
import kevolution.repopulation.combinator.Combinator
import kevolution.repopulation.mutator.Mutator
import kevolution.repopulation.mutator.RandomValueMutator
import kevolution.repopulation.mutator.ShuffleMutator
import kevolution.selection.Selector

// Repopulation handler that takes in multiple mutators
class MultiMutatorPopHandlerAbstract<T:Any,C : Comparable<C>>(factory : GeneFactory<T>, selector: Selector<T, C>, combinator: Combinator<T>, private val mutators: List<Mutator<T>>) : SingleSourceRepopulationHandler<T,C>(factory,selector,combinator,ShuffleMutator(0.0)) {

    override fun mutate(gt: GenoType<T>): GenoType<T> {
        return mutators.fold(gt, { g, mutator -> mutator.mutate(g) })
    }
}