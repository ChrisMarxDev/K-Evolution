package kevolution.extensions

fun Double.cleanPercentage(): Double {
    return if (this > 1) 1.0 else if (this < 0) 0.0 else this
}

fun Int.positivie(): Int {
    return if (this < 0) 0 else this

}