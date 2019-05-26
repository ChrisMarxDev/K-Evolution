package kevolution.selection

import kevolution.extensions.gradientSelection
import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType

/**
 * Selector that selects by random linearly declining chance. First element has 100% chance, middle element has 50% last elemen has 0%
 *
 * @param curve: @param curve: curve parameter decides the curvature of the chance graph. 1 leads to a linear chance distrubution.
 * Values above 1 lead to a convex distribution, values between 0 and 1 lead to a concav distribution
 * Negative values dont work and will be treated as 1
 * @param take: optional parameter to take the first x amount of elements with 100% certainty
 * @param drop: optional parameter to definitely leave out the last x elements
 *
 */
class GradientSelector<T : Any, C : Comparable<C>>(val curve: Double = 1.0,val take: Int = 0, val drop: Int = 0) : Selector<T, C>() {
    override fun select(input: List<GeneInfoWrapper<T, C>>): List<GenoType<T>> {
        //save first x entries
        val taken = input.take(take)

        // drop first x from gradient selection
        var tempList = input.drop(take)

        // drop last x
        tempList = tempList.dropLast(drop)

        // do linear gradient selection
        tempList = tempList.gradientSelection(curve).toMutableList()

        // add guaranteed elements to list
        tempList.addAll(taken)

        return tempList.map { it.genoType }

    }
}