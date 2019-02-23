package trace

import kotlin.math.cos
import kotlin.math.sin

data class Dirichlet(val radius: Double) : Filter {

    override val left get() = -radius
    override val right get() = radius

    override operator fun invoke(t: Double): Double = when (t) {
        0.0 -> 1.0
        in left..right -> {
            val x = t * Math.PI / radius
            0.5 / radius * (1.0 + cos(x)) * sin(x * radius) / sin(x)
        }
        else -> 0.0
    }

}
