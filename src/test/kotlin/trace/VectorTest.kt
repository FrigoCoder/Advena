package trace

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.sameInstance
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

class VectorTest {

    private val v1 = Vector(1.0, 2.0, 3.0)
    private val v2 = Vector(4.0, 5.0, 6.0)

    @Test
    fun `vector is initialized correctly`() {
        assertThat(v1.x, equalTo(1.0))
        assertThat(v1.y, equalTo(2.0))
        assertThat(v1.z, equalTo(3.0))
    }

    @Test
    fun `constant vectors are initialized correctly`() {
        assertThat(Vector.Zero, equalTo(Vector(0.0, 0.0, 0.0)))
        assertThat(Vector.X, equalTo(Vector(1.0, 0.0, 0.0)))
        assertThat(Vector.Y, equalTo(Vector(0.0, 1.0, 0.0)))
        assertThat(Vector.Z, equalTo(Vector(0.0, 0.0, 1.0)))
    }

    @Test
    fun `unary plus returns same vector`() {
        assertThat(+v1, sameInstance(v1))
    }

    @Test
    fun `unary minus returns negated vector`() {
        assertThat(-v1, equalTo(Vector(-1.0, -2.0, -3.0)))
    }

    @Test
    fun `multiplication by scalar`() {
        assertThat(v1 * 2.0, equalTo(Vector(2.0, 4.0, 6.0)))
    }

    @Test
    fun `multiplication of scalar by vector`() {
        assertThat(2.0 * v1, equalTo(Vector(2.0, 4.0, 6.0)))
    }

    @Test
    fun `division by scalar`() {
        assertThat(v1 / 2.0, equalTo(Vector(0.5, 1.0, 1.5)))
    }

    @Test
    fun `addition of two vectors`() {
        assertThat(v1 + v2, equalTo(Vector(5.0, 7.0, 9.0)))
    }

    @Test
    fun `subtraction of two vectors`() {
        assertThat(v1 - v2, equalTo(Vector(-3.0, -3.0, -3.0)))
    }

    @Test
    fun `dot product of two vectors`() {
        assertThat(v1 * v2, equalTo(32.0))
    }

    @Test
    fun `cross product of two vectors`() {
        assertThat(v1 x v2, equalTo(Vector(-3.0, 6.0, -3.0)))
    }

    @Test
    fun `length of vector`() {
        assertThat(v1.length(), equalTo(sqrt(14.0)))
    }

    @Test
    fun `normalized vector`() {
        assertThat(v1.normalize(), equalTo(Vector(1.0 / sqrt(14.0), 2.0 / sqrt(14.0), 3.0 / sqrt(14.0))))
    }

}
