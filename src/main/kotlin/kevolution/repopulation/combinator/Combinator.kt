package kevolution.repopulation.combinator

import kevolution.models.GenoType

interface Combinator<T:Any>{
    fun combine(gt1:GenoType<T>,gt2:GenoType<T>):GenoType<T>
}