package trace

data class Scene(private val spheres: List<Sphere>) {

    fun shoot(ray: Ray): SceneHit? {
        return spheres
            .map { sphere -> SceneHit(sphere, sphere.intersect(ray)) }
            .filter { hit -> hit.distance < Double.POSITIVE_INFINITY }
            .minBy { hit -> hit.distance }
    }

}

data class SceneHit(val sphere: Sphere, val distance: Double)
