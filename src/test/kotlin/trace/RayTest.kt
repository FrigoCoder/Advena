package trace

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.core.IsEqual.equalTo
import org.junit.jupiter.api.Test

class RayTest {

    private val origin = Vector(1.0, 2.0, 3.0)
    private val direction = Direction(2.0, 3.0, 4.0)
    private val ray = Ray(origin, direction)

    @Test
    fun `ray is initialized properly`() {
        assertThat(ray.origin, equalTo(origin))
        assertThat(ray.direction, equalTo(direction))
    }

    @Test
    fun `ray equals works properly`() {
        assertThat(ray, equalTo(Ray(origin, direction)))
        assertThat(ray, not(equalTo(Ray(origin, Direction(2.0, 3.0, 5.0)))))
        assertThat(ray, not(equalTo(Ray(Vector(1.0, 2.0, 4.0), direction))))
        assertThat(ray, not(nullValue()))
    }

    @Test
    fun `ray hashcode works properly`() {
        assertThat(ray.hashCode(), equalTo(31 * origin.hashCode() + direction.hashCode()))
    }

    @Test
    fun `ray toString works properly`() {
        assertThat(ray.toString(), equalTo("Ray(origin=$origin, direction=$direction)"))

    }

}
