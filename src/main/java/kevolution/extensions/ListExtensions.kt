package kevolution.extensions

import kevolution.models.GeneInfoWrapper

fun <T> List<T>.replace(index: Int, newElement: T): List<T> {

    return this.mapIndexed { i, old -> if (index == i) newElement else old }
}

fun <T : Any, C : Comparable<C>> List<GeneInfoWrapper<T, C>>.selectFittest():GeneInfoWrapper<T, C> {
    return this.reduce { first, second -> if (first.fitness.compareTo(second.fitness) >= 1) first else second }
}