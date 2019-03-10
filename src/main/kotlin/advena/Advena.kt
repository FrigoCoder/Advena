package advena

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Mesh
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.VertexAttribute
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.math.Vector2
import org.lwjgl.opengl.GL20

class Advena : ApplicationAdapter() {

    private lateinit var mesh: Mesh
    private lateinit var shader: ShaderProgram

    private var frame: Int = 0
    private var mouse: Vector2 = Vector2(0f, 0f)
    private var resolution: Vector2 = Vector2(0f, 0f)
    private var startTime: Long = 0

    override fun create() {
        mesh = Mesh(true, 4, 6, VertexAttribute.Position())
        mesh.setVertices(floatArrayOf(
            -1.0f, -1.0f, 0f,
            1.0f, -1.0f, 0f,
            1.0f, 1.0f, 0f,
            -1.0f, 1.0f, 0f
        ))
        mesh.setIndices(shortArrayOf(0, 1, 2, 2, 3, 0))

        val header = Gdx.files.internal("header.glsl").readString()
        val fragment = Gdx.files.internal("fragment.glsl").readString()
        val footer = Gdx.files.internal("footer.glsl").readString()

        ShaderProgram.pedantic = false
        shader = ShaderProgram(Gdx.files.internal("vertex.glsl").readString(), "$header\n$fragment\n$footer")
        if (!shader.isCompiled) {
            System.err.println(shader.log)
            System.exit(-1)
        }

        for (i in 0..3) {
            val file = Gdx.files.internal("iChannel$i.png")
            if (file.exists()) {
                Texture(file).bind(i)
                shader.setUniformi("iChannel$i", i)
            }
        }

        startTime = System.currentTimeMillis()
    }

    override fun render() {
        shader.begin()
        shader.setUniformi("iFrame", frame())
        shader.setUniformf("iMouse", mouse())
        shader.setUniformf("iResolution", resolution)
        shader.setUniformf("iTime", time())
        mesh.render(shader, GL20.GL_TRIANGLES)
        shader.end()
    }

    private fun frame(): Int = frame++

    private fun mouse(): Vector2 {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            mouse = Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
        }
        return mouse
    }

    override fun resize(width: Int, height: Int) {
        resolution = Vector2(width.toFloat(), height.toFloat())
    }

    private fun time() = (System.currentTimeMillis() - startTime) / 1000f
}
