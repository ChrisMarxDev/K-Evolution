package kevolution.engine.logging

import kevolution.engine.HallOfFame
import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType

object Logger {

    val GENERATION = "PROCESSING GENERATION: "
    final val INFO = "[INFO]: "

    val FINAL_POPULATION = "[FINAL POPULATION: ]"
    val HOF = "[HALL OF FAME BEST RESULTS: ]"

    fun i(info: String) {
        println(INFO + info)
    }

    fun genoType(gt: GenoType<*>) {
        val sb = StringBuilder()
        sb.append("GENES: ")

        gt.genes.forEach {
            sb.append(it.toString() + "; ")
        }
        println(sb.toString())
    }

    fun geneInfoWrapper(wrap: GeneInfoWrapper<*, *>) {
        val sb = StringBuilder()
        sb.append("[GENE]: Fitness=" + wrap.fitness + "; Generation=" + wrap.generation + " ;")

        sb.append("; Genes={ ")

        wrap.genoType.genes.forEach {
            sb.append(it.toString() + "; ")
        }
        sb.append("} ")
        println(sb.toString())
    }


    fun hof(hof: HallOfFame<*>) {
        i(HOF)
        hof.hallOfFame.forEach { geneInfoWrapper(it) }

    }

    fun hofEntry(entry: GeneInfoWrapper<*, *>) {
        i("New Hall of Fame Entry found: ")
        geneInfoWrapper(entry)
    }

    fun result(population: List<GeneInfoWrapper<*, *>>, hallOfFame: HallOfFame<*>) {
        i(FINAL_POPULATION)
        population.forEach { geneInfoWrapper(it) }
        hof(hallOfFame)
    }

    fun generation(gen: Int) {
        i(GENERATION + gen)

    }


}