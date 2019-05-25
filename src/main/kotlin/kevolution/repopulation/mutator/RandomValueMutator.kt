package kevolution.repopulation.mutator

import kevolution.extensions.replace
import kevolution.models.GenoType
import kotlin.random.Random

//Randomly mutates a gene by using the gene creator
class RandomValueMutator<T : Any>(chance: Double, val geneCreator: () -> T) : Mutator<T>(chance) {


    override fun mutation(gt: GenoType<T>): GenoType<T> {

        val index = Random.nextInt(gt.genes.size)

        return GenoType(gt.genes.replace(index, geneCreator()))
    }


}