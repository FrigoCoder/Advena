package trace

interface Filter {
    val left: Double
    val right: Double
    operator fun invoke(t: Double): Double
}
