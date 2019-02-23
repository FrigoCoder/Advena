package trace

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class SceneTest {

    private val sphere1 = Sphere(Vector(3.0, 0.0, 0.0), 2.0, Color.Black, Reflection.Diffuse, Color.Black)
    private val sphere2 = Sphere(Vector(6.0, 0.0, 0.0), 2.0, Color.Black, Reflection.Diffuse, Color.Black)
    private val scene = Scene(listOf(sphere1, sphere2))

    @Test
    fun `ray misses`() {
        val ray = Ray(Vector(0.0, 0.0, 0.0), Direction(0.0, 1.0, 0.0))
        val hit = scene.shoot(ray)
        assertThat(hit, nullValue())
    }

    @Test
    fun `ray hits first sphere`() {
        val ray = Ray(Vector(0.0, 0.0, 0.0), Direction(1.0, 0.0, 0.0))
        val hit = scene.shoot(ray)!!
        assertThat(hit.sphere, equalTo(sphere1))
        assertThat(hit.distance, equalTo(1.0))
    }

    @Test
    fun `ray hits second sphere`() {
        val ray = Ray(Vector(10.0, 0.0, 0.0), Direction(-1.0, 0.0, 0.0))
        val hit = scene.shoot(ray)!!
        assertThat(hit.sphere, equalTo(sphere2))
        assertThat(hit.distance, equalTo(2.0))
    }

}
