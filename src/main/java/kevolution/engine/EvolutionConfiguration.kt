package kevolution.engine

import kevolution.extensions.cleanPercentage
import kevolution.extensions.positivie

class EvolutionConfiguration(
        generations: Int = 1000,
        populationSize: Int = 200,
        hallOfFameSize: Int = 40) {

    val populationSize: Int = populationSize.positivie()
    val generations: Int = generations.positivie()
    val hallOfFameSize: Int = hallOfFameSize.positivie()

}