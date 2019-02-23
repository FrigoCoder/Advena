package trace

import javafx.scene.image.WritableImage
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB
import java.io.File
import javax.imageio.ImageIO

class Image(val xs: Int, val ys: Int) {

    private val pixels = Array(xs) { Array(ys) { Color.Black } }

    operator fun get(x: Int, y: Int) = pixels[x][y]
    operator fun set(x: Int, y: Int, color: Color) {
        pixels[x][y] = color
    }

    fun save(filename: String) {
        val image = toBufferedImage()
        val file = File(filename)
        ImageIO.write(image, file.extension, file)
    }

    fun toBufferedImage(): BufferedImage {
        val image = BufferedImage(xs, ys, TYPE_INT_RGB)
        for (x in 0 until xs) {
            for (y in 0 until ys) {
                image.setRGB(x, y, this[x, y].clip().toInt())
            }
        }
        return image
    }

    fun toWritableImage(): WritableImage {
        val image = WritableImage(xs, ys)
        val pixelWriter = image.pixelWriter
        for (x in 0 until xs) {
            for (y in 0 until ys) {
                pixelWriter.setArgb(x, y, this[x, y].clip().toInt())
            }
        }
        return image
    }

}
