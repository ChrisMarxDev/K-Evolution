package kevolution.repopulation.mutator

import kevolution.extensions.cleanPercentage
import kevolution.models.GenoType
import kotlin.random.Random

abstract class Mutator<T : Any>(chance: Double) {

    val chance = chance.cleanPercentage()

    protected abstract fun mutation(gt: GenoType<T>): GenoType<T>
    fun mutate(gt: GenoType<T>): GenoType<T> {
        return if (Random.nextDouble(0.0, 1.0) < chance) mutate(gt) else gt
    }
}