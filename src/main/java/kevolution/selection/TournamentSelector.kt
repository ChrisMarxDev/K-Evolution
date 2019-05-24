package kevolution.selection

import kevolution.extensions.selectFittest
import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType

class TournamentSelector<T : Any, C : Comparable<C>>(val tournamentSize: Int) : Selector<T, C>() {

    override fun select(input: List<GeneInfoWrapper<T, C>>): List<GenoType<T>> {
        val listOfLists = input.shuffled().withIndex()
                .groupBy { it.index / tournamentSize }
                .map { it.value.map { it.value } }

        return listOfLists.map { it.selectFittest().genoType }
    }


}