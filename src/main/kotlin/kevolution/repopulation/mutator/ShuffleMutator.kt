package kevolution.repopulation.mutator

import kevolution.engine.logging.Logger
import kevolution.models.GenoType

// Shuffles all genes of a geno type
// Ordered Mutation
class ShuffleMutator<T : Any>(chance: Double) : Mutator<T>(chance) {

    override fun mutation(gt: GenoType<T>): GenoType<T> {
        return GenoType(gt.genes.shuffled())
    }
}