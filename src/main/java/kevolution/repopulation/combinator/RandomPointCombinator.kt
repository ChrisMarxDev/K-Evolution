package kevolution.repopulation.combinator

import kevolution.extensions.cleanPercentage
import kevolution.models.GenoType
import kotlin.random.Random

class RandomPointCombinator<T:Any>() : Combinator<T> {

    override fun combine(gt1: GenoType<T>, gt2: GenoType<T>): GenoType<T> {

        val interval = Random.nextInt(gt1.genes.size)

        val newGenes = gt1.genes.subList(0, interval).toMutableList()
        newGenes.addAll(gt2.genes.subList(interval, gt2.genes.size))

        return GenoType(newGenes)

    }

}