package kevolution.engine

import kevolution.engine.logging.Logger
import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType
import java.util.*
import kotlin.collections.ArrayList

class HallOfFame<C : Comparable<C>>(val hallOfFameMaxSize: Int) {

    var hallOfFame = LinkedList<GeneInfoWrapper<*, C>>()

    fun length(): Int {
        return hallOfFame.size
    }

    fun processPotentialEntry(entry: GeneInfoWrapper<*, C>) {
        if (length() < hallOfFameMaxSize) {
            hallOfFame.add(entry)
            Logger.hofEntry(entry)

        } else {

            run loop@{
                hallOfFame.forEachIndexed { index, pair ->
                    if (entry.fitness.compareTo(pair.fitness) == 1) {
                        hallOfFame.add(index, entry)
                        Logger.hofEntry(entry)
                        hallOfFame.removeAt(hallOfFame.size - 1)
                        return@loop
                    }
                }
            }

        }
    }

    fun reset() {
        hallOfFame = LinkedList<GeneInfoWrapper<*, C>>()
    }
}