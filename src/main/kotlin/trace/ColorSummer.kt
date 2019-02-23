package trace

data class ColorSummer(var color: Color = Color.Black, var sum: Double = 0.0) {

    fun update(elem: Color, weight: Double) {
        color += elem * weight
        sum += weight
    }

    fun result() = color / sum

}

