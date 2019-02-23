package trace

data class Sphere(
    val position: Vector,
    val radius: Double,
    val color: Color,
    val reflection: Reflection,
    val emission: Color = Color.Black) {

    /**
     * Based on smallpt and https://en.wikipedia.org/wiki/Line%E2%80%93sphere_intersection. Does not allow negative
     * distances.
     */

    fun intersect(ray: Ray): Double {
        val oc = ray.origin - position
        val ldotoc = ray.direction.vector * oc
        val determinant = ldotoc * ldotoc - oc * oc + radius * radius
        if (determinant < 0) {
            return Double.POSITIVE_INFINITY
        }
        val sqrtDeterminant = Math.sqrt(determinant)
        val distance1 = -ldotoc - sqrtDeterminant
        if (distance1 >= 0) {
            return distance1
        }
        val distance2 = -ldotoc + sqrtDeterminant
        if (distance2 >= 0) {
            return distance2
        }
        return Double.POSITIVE_INFINITY
    }

}
