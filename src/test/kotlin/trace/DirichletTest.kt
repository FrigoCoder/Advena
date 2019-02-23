package trace

import org.junit.jupiter.api.Test

class DirichletTest {

    @Test
    fun `test trigonometric dirichlet against naive dirichlet`() {
        assertFilterEquals(Dirichlet(1.0), DirichletNaive(1), 0.125)
        assertFilterEquals(Dirichlet(2.0), DirichletNaive(2), 0.125)
        assertFilterEquals(Dirichlet(3.0), DirichletNaive(3), 0.125)
        assertFilterEquals(Dirichlet(4.0), DirichletNaive(4), 0.125)
    }

}

data class DirichletNaive(val radius: Int) : Filter {

    override val left get() = -radius.toDouble()
    override val right get() = radius.toDouble()

    override operator fun invoke(t: Double): Double = when (t) {
        in left..right -> {
            val x = t * Math.PI / radius
            (0..radius).sumByDouble { i -> weight(i) * Math.cos(i * x) } / radius
        }
        else -> 0.0
    }

    private fun weight(i: Int) = when (i) {
        0 -> 0.5
        in 1 until radius -> 1.0
        radius -> 0.5
        else -> 0.0
    }

}
