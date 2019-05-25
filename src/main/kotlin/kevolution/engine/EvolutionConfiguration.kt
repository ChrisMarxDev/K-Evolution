package kevolution.engine

import kevolution.extensions.cleanPercentage
import kevolution.extensions.positivie

class EvolutionConfiguration(
        generations: Int = 1000,
        populationSize: Int = 200,
        generateNew: Int = 20,
        hallOfFameSize: Int = 50) {

    // General population size
    val populationSize: Int = populationSize.positivie()

    // Amount of newly generated geno types each generation
    val generateNew: Int = generateNew.positivie()

    // Amount of generations to go through
    val generations: Int = generations.positivie()

    // Size of the hall of fame
    val hallOfFameSize: Int = hallOfFameSize.positivie()

}