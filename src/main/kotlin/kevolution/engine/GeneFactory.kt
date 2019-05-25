package kevolution.engine

import kevolution.models.GenoType

class GeneFactory<T : Any>(val creator: (Int) -> T, val geneLenght: Int) {

    fun createGenoTypes(number: Int): List<GenoType<T>> {
        return (0 until number).map { createGenoType() }
    }

    fun createGenoType(): GenoType<T> {
        return GenoType((0 until geneLenght).map { creator(0) })
    }

}