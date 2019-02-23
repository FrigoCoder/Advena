package trace

data class Camera(
    val origin: Vector,
    val direction: Direction,
    val fov: Double,
    var near: Double,
    val xs: Int,
    val ys: Int) {

    private val angle = Math.tan(0.5 * fov * Math.PI / 180)
    private val aspectRatio = xs.toDouble() / ys.toDouble()
    private val cx = Vector(aspectRatio, 0.0, 0.0) * angle
    private val cy = (direction.vector x cx).normalize() * angle

    fun ray(x: Double, y: Double): Ray {
        val direction = Direction(direction.vector + cx * (x / xs - 0.5) + cy * (y / ys - 0.5))
        return Ray(origin + direction.vector * near, direction)
    }

}
