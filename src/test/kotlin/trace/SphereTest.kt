package trace

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.jupiter.api.Test

class SphereTest {

    private val sphere = Sphere(Vector(3.0, 0.0, 0.0), 2.0, Color.Black, Reflection.Diffuse, Color.Black)

    @Test
    fun `ray does not intersect sphere`() {
        noIntersect(Ray(Vector(-1.0, 0.0, 0.0), Direction(1.0, 1.0, 0.0)))
    }

    @Test
    fun `ray intersects sphere once`() {
        // left
        intersect(Ray(Vector(1.0, -1.0, 0.0), Direction(0.0, 1.0, 0.0)), 1.0)
        intersect(Ray(Vector(1.0, 3.0, 0.0), Direction(0.0, -1.0, 0.0)), 3.0)

        // right
        intersect(Ray(Vector(5.0, -1.0, 0.0), Direction(0.0, 1.0, 0.0)), 1.0)
        intersect(Ray(Vector(5.0, 3.0, 0.0), Direction(0.0, -1.0, 0.0)), 3.0)

        // top
        intersect(Ray(Vector(0.0, 2.0, 0.0), Direction(1.0, 0.0, 0.0)), 3.0)
        intersect(Ray(Vector(10.0, 2.0, 0.0), Direction(-1.0, 0.0, 0.0)), 7.0)

        // bot
        intersect(Ray(Vector(0.0, -2.0, 0.0), Direction(1.0, 0.0, 0.0)), 3.0)
        intersect(Ray(Vector(10.0, -2.0, 0.0), Direction(-1.0, 0.0, 0.0)), 7.0)

        // front
        intersect(Ray(Vector(0.0, 0.0, 2.0), Direction(1.0, 0.0, 0.0)), 3.0)
        intersect(Ray(Vector(10.0, 0.0, 2.0), Direction(-1.0, 0.0, 0.0)), 7.0)

        // behind
        intersect(Ray(Vector(0.0, 0.0, -2.0), Direction(1.0, 0.0, 0.0)), 3.0)
        intersect(Ray(Vector(10.0, 0.0, -2.0), Direction(-1.0, 0.0, 0.0)), 7.0)
    }

    @Test
    fun `ray intersects sphere twice at closest intersection point`() {
        // left
        intersect(Ray(Vector(0.0, 0.0, 0.0), Direction(1.0, 0.0, 0.0)), 1.0)

        // right
        intersect(Ray(Vector(10.0, 0.0, 0.0), Direction(-1.0, 0.0, 0.0)), 5.0)

        // top
        intersect(Ray(Vector(3.0, 8.0, 0.0), Direction(0.0, -1.0, 0.0)), 6.0)

        // bot
        intersect(Ray(Vector(3.0, -4.0, 0.0), Direction(0.0, 1.0, 0.0)), 2.0)

        // front
        intersect(Ray(Vector(3.0, 0.0, -5.0), Direction(0.0, 0.0, 1.0)), 3.0)

        // back
        intersect(Ray(Vector(3.0, 0.0, 10.0), Direction(0.0, 0.0, -1.0)), 8.0)
    }

    @Test
    fun `ray intersects sphere at slanted angles`() {
        intersect(Ray(Vector(3.0, 0.0, 0.0), Direction(1.0, 1.0, 1.0)), 2.0)
    }

    @Test
    fun ray_does_not_hit_at_negative_distances() {
        // left
        noIntersect(Ray(Vector(0.0, 0.0, 0.0), Direction(-1.0, 0.0, 0.0)))

        // right
        noIntersect(Ray(Vector(10.0, 0.0, 0.0), Direction(1.0, 0.0, 0.0)))

        // top
        noIntersect(Ray(Vector(3.0, 8.0, 0.0), Direction(0.0, 1.0, 0.0)))

        // bot
        noIntersect(Ray(Vector(3.0, -4.0, 0.0), Direction(0.0, -1.0, 0.0)))

        // front
        noIntersect(Ray(Vector(3.0, 0.0, -5.0), Direction(0.0, 0.0, -1.0)))

        // back
        noIntersect(Ray(Vector(3.0, 0.0, 10.0), Direction(0.0, 0.0, 1.0)))
    }

    @Test
    fun ray_hits_from_inside_sphere() {
        intersect(Ray(Vector(2.0, 0.0, 0.0), Direction(1.0, 0.0, 0.0)), 3.0)
    }

    private fun noIntersect(ray: Ray) {
        assertThat(sphere.intersect(ray), equalTo(Double.POSITIVE_INFINITY))
    }

    private fun intersect(ray: Ray, distance: Double) {
        assertThat(sphere.intersect(ray), equalTo(distance))
    }

}
