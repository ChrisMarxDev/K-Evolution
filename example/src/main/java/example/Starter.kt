package example

import kevolution.engine.Engine
import kevolution.engine.EvolutionConfiguration
import kevolution.engine.GeneFactory
import kevolution.models.GenoType
import kevolution.repopulation.combinator.RandomPointCombinator
import kevolution.repopulation.mutator.RandomValueMutator
import kevolution.selection.TournamentSelector
import kotlin.random.Random


class Starter {

    companion object {

        //2. Evaluation
        fun eval(gt: GenoType<Int>): Double {
            return gt.genes.fold(0, Int::plus).toDouble()
        }




        @JvmStatic
        fun main(args: Array<String>) {

            val simpleMutationHanlder = RandomValueMutator(.3, { Random.nextInt(10) })

            //1. GeneFactory
            fun randomInt(): Int {
                return Random.nextInt(100)
            }

            val factory: GeneFactory<Int> = GeneFactory({ randomInt() }, 20)


            val config = EvolutionConfiguration(generations = 1000, populationSize = 200)

            //3.Selection
            val engine = Engine(
                    factory= factory,
                    eval = { eval(it) },
                    combinator = RandomPointCombinator(),
                    mutator = RandomValueMutator(0.05, { randomInt() }),
                    selector = TournamentSelector(4),
                    config = config)

            engine.run()

            val population = engine.population

            //4. Mutation

            //5. Result

        }
    }
}