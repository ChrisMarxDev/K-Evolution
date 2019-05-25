package kevolution.repopulation.mutator

import com.sun.tools.javah.Gen
import kevolution.engine.logging.Logger
import kevolution.models.GenoType
import kotlin.random.Random


// Takes a geno type and shuffles the position of two genes
// Ordered Mutation
class TwoGeneSwapMutator<T : Any>(chance: Double) : Mutator<T>(chance) {

    override fun mutation(gt: GenoType<T>): GenoType<T> {

        if (gt.genes.size >= 2) {
            val firstIndex = Random.nextInt(gt.genes.size)
            val secondIndex = Random.nextInt(gt.genes.size)

            val firstElement = gt.genes[firstIndex]
            val secondElement = gt.genes[secondIndex]

            return GenoType(gt.genes.mapIndexed { index, t -> if (index == firstIndex) secondElement else if (index == secondIndex) firstElement else t })

        } else {
            Logger.e(this.javaClass.simpleName + " got a gene with less than 2 genes; nothing to shuffle; useless call")
            return gt
        }


    }
}