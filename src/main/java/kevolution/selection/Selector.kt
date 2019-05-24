package kevolution.selection

import kevolution.extensions.cleanPercentage
import kevolution.models.GeneInfoWrapper
import kevolution.models.GenoType

abstract class Selector<T:Any,C:Comparable<C>>{

    abstract fun select(input: List<GeneInfoWrapper<T, C>>):List<GenoType<T>>
}


