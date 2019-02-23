package trace

class Direction {

    val vector: Vector

    constructor (x: Double, y: Double, z: Double) {
        this.vector = Vector(x, y, z).normalize()
    }

    constructor (vector: Vector) {
        this.vector = vector.normalize()
    }

    override fun equals(other: Any?) = other is Direction && vector == other.vector
    override fun hashCode() = vector.hashCode()
    override fun toString() = "Direction(vector=$vector)"

}
