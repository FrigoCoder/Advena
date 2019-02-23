package trace

import kotlin.math.roundToInt

data class Color(val r: Double, val g: Double, val b: Double) {

    companion object {
        val Black = Color(0.0, 0.0, 0.0)
        val DarkGrey = Color(0.25, 0.25, 0.25)
        val Grey = Color(0.5, 0.5, 0.5)
        val LightGrey = Color(0.75, 0.75, 0.75)
        val White = Color(1.0, 1.0, 1.0)

        val Red = Color(1.0, 0.0, 0.0)
        val Green = Color(0.0, 1.0, 0.0)
        val Blue = Color(0.0, 0.0, 1.0)

    }

    operator fun unaryPlus() = this
    operator fun unaryMinus() = Color(-r, -g, -b)

    operator fun times(x: Double) = Color(r * x, g * x, b * x)
    operator fun div(x: Double) = Color(r / x, g / x, b / x)

    operator fun plus(that: Color) = Color(r + that.r, g + that.g, b + that.b)
    operator fun minus(that: Color) = Color(r - that.r, g - that.g, b - that.b)

    fun clip() = Color(r.coerceIn(0.0, 1.0), g.coerceIn(0.0, 1.0), b.coerceIn(0.0, 1.0))
    
    fun toInt() = (255 shl 24) + ((r * 255.0).roundToInt() shl 16) + ((g * 255.0).roundToInt() shl 8) + (b * 255.0)
        .roundToInt()

}

operator fun Double.times(that: Color) = Color(this * that.r, this * that.g, this * that.b)
