package trace

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.image.ImageView
import javafx.stage.Stage
import kotlin.random.Random

class Main : Application() {

    private val scene = Scene(listOf(
        Sphere(Vector(1e5 + 1, 40.8, 81.6), 1e5, Color(.75, .25, .25), Reflection.Diffuse, Color.Black),
        Sphere(Vector(-1e5 + 99, 40.8, 81.6), 1e5, Color(.25, .25, .75), Reflection.Diffuse, Color.Black),
        Sphere(Vector(50.0, 40.8, 1e5), 1e5, Color.LightGrey, Reflection.Diffuse, Color.Black),
        Sphere(Vector(50.0, 40.8, -1e5 + 170), 1e5, Color.Black, Reflection.Diffuse, Color.Black),
        Sphere(Vector(50.0, 1e5, 81.6), 1e5, Color.LightGrey, Reflection.Diffuse, Color.Black),
        Sphere(Vector(50.0, -1e5 + 81.6, 81.6), 1e5, Color.LightGrey, Reflection.Diffuse, Color.Black),
        Sphere(Vector(27.0, 16.5, 47.0), 16.5, Color.White * .999, Reflection.Specular, Color.Black),
        Sphere(Vector(73.0, 16.5, 78.0), 16.5, Color.White * .999, Reflection.Refractive, Color.Black),
        Sphere(Vector(50.0, 681.6 - .27, 81.6), 600.0, Color.Black, Reflection.Diffuse, Color(12.0, 12.0, 12.0))
    ))

    private val xs = 1024
    private val ys = 768
    private val camera = Camera(Vector(50.0, 52.2, 295.6), Direction(0.0, -0.042612, -1.0), 54.3609968, 140.0, xs, ys)
    private val random = Random(0x12345678)

    override fun start(stage: Stage) {
        val image = render()
        show(stage, image)
        image.save("result2.png")
    }

    private fun render(): Image {
        val image = Image(xs, ys)
        for (x in 0 until image.xs) {
            for (y in 0 until image.ys) {
                val result = ColorSummer()
                for (s in 1..1) {
                    result.update(trace(x + random.nextDouble(), y + random.nextDouble()), 1.0)
                }
                image[x, y] = result.result()
            }
        }
        return image
    }

    private fun trace(x: Double, y: Double): Color {
        val ray = camera.ray(x, y)
        val hit = scene.shoot(ray)
        return hit?.sphere?.color ?: Color.Black
    }

    private fun show(stage: Stage, image: Image) {
        val scene = javafx.scene.Scene(Group(ImageView(image.toWritableImage())))
        stage.title = "Welcome to JavaFX!"
        stage.scene = scene
        stage.sizeToScene()
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }

}
