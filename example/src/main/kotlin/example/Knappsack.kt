package example

import example.HighIntSum.Companion.eval
import kevolution.engine.Engine
import kevolution.engine.EvolutionConfiguration
import kevolution.engine.GeneFactory
import kevolution.models.GenoType
import kevolution.repopulation.combinator.RandomPointCombinator
import kevolution.repopulation.mutator.RandomValueMutator
import kevolution.repopulation.mutator.TwoGeneSwapMutator
import kevolution.selection.TournamentSelector
import kotlin.random.Random

class Knappsack {

    companion object {
        val knappsackSize = 30
        val items = listOf(
                4 to 40,
                2 to 35,
                1 to 10,
                2 to 37,
                8 to 85,
                9 to 89,
                2 to 24,
                3 to 29,
                3 to 33,
                3 to 36,
                3 to 28,
                4 to 40,
                4 to 46,
                3 to 29,
                3 to 34,
                9 to 90,
                3 to 25,
                4 to 30,
                4 to 34,
                4 to 37,
                4 to 29,
                5 to 41,
                5 to 47,
                4 to 34,
                3 to 29,
                3 to 33,
                3 to 36,
                3 to 28,
                4 to 40,
                4 to 46,
                2 to 41,
                0 to 88,
                2 to 24,
                3 to 29
        )

        @JvmStatic
        fun main(args: Array<String>) {

            fun eval(gt: GenoType<Boolean>): Int {
                val sackVal = items.filterIndexed { index, _ -> gt.genes[index] }.reduce { a, b ->
                    Pair(a.first + b.first, a.second + b.second)
                }
                return if (sackVal.first > knappsackSize) -1 else sackVal.second
            }

            //1. GeneFactory
            val factory: GeneFactory<Boolean> = GeneFactory({ Random.nextBoolean() }, items.size)


            val config = EvolutionConfiguration(generations = 1000, populationSize = 500)

            //3.Selection
            val engine = Engine(
                    factory = factory,
                    eval = { eval(it) },
                    combinator = RandomPointCombinator(),
                    mutator = TwoGeneSwapMutator(0.20),
                    selector = TournamentSelector(5),
                    config = config)

            engine.run()

            val population = engine.population

            //4. Mutation

            //5. Result
        }
    }
}