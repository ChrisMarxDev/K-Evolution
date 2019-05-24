package kevolution.engine

abstract class AbstractEvolution {

    abstract fun generate()
    abstract fun select()
    abstract fun combine()
    abstract fun mutate()

}