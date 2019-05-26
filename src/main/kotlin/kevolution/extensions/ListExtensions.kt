package kevolution.extensions

import kevolution.models.GeneInfoWrapper
import kotlin.random.Random

fun <T> List<T>.replace(index: Int, newElement: T): List<T> {

    return this.mapIndexed { i, old -> if (index == i) newElement else old }
}

fun <T : Any, C : Comparable<C>> List<GeneInfoWrapper<T, C>>.selectFittest(): GeneInfoWrapper<T, C> {
    return this.reduce { first, second -> if (first.fitness.compareTo(second.fitness) >= 1) first else second }
}

/**
 * Method that selects elements with a random chance. Elements have a gradually lower chance the lower they are in the list
 *
 * @param curve: curve parameter decides the curvature of the chance graph. 1 leads to a linear chance distrubution.
 * Values above 1 lead to a convex distribution, values between 0 and 1 lead to a concav distribution
 * Negative values dont work and will be treated as 1
 *
 */
fun <T> List<T>.gradientSelection(curve: Double): List<T> {
    val usedCurve = if (curve <= 0.0) 1.0 else curve
    val size = this.size
    return this.filterIndexed { index, t -> index.getGradientChance(size, curve) >= Random.nextDouble(1.0) }
}

fun Int.getGradientChance(max: Int, curve: Double): Double {
    return Math.pow((1 - this.toDouble() / max), curve)
}