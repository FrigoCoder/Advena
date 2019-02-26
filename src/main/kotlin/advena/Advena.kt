package advena

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Mesh
import com.badlogic.gdx.graphics.VertexAttribute
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.math.Vector2
import org.lwjgl.opengl.GL20

class Advena : ApplicationAdapter() {

    private lateinit var shader: ShaderProgram
    private lateinit var mesh: Mesh
    private var iFrame: Int = 0
    private var startTime: Long = 0
    private var width: Int = 0
    private var height: Int = 0

    override fun create() {
        ShaderProgram.pedantic = false
        shader = ShaderProgram(Gdx.files.internal("vertex.glsl"), Gdx.files.internal("fragment.glsl"))
        if (!shader.isCompiled) {
            System.err.println(shader.log)
            System.exit(-1)
        }

        mesh = Mesh(true, 4, 6, VertexAttribute.Position())
        mesh.setVertices(floatArrayOf(
            -1.0f, -1.0f, 0f,
            1.0f, -1.0f, 0f,
            1.0f, 1.0f, 0f,
            -1.0f, 1.0f, 0f
        ))
        mesh.setIndices(shortArrayOf(0, 1, 2, 2, 3, 0))

        startTime = System.currentTimeMillis()
    }

    override fun render() {
        shader.begin()
        shader.setUniformi("iFrame", iFrame++)
        shader.setUniformf("iMouse", Vector2(0f, 0f))
        shader.setUniformf("iResolution", Vector2(width.toFloat(), height.toFloat()))
        shader.setUniformf("iTime", (System.currentTimeMillis() - startTime) / 1000f)
        mesh.render(shader, GL20.GL_TRIANGLES)
        shader.end()
    }

    override fun resize(width: Int, height: Int) {
        this.width = width;
        this.height = height
    }

}
