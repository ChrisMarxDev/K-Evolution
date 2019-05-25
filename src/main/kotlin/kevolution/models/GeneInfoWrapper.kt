package kevolution.models

class GeneInfoWrapper<T : Any, C: Comparable<C>>(val genoType: GenoType<T>, val fitness: C,val  generation: Int)