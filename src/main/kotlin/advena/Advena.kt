package advena

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Mesh
import com.badlogic.gdx.graphics.VertexAttribute
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import org.lwjgl.opengl.GL20

class Advena : ApplicationAdapter() {

    private lateinit var shader: ShaderProgram
    private lateinit var mesh: Mesh
    private var startTime: Long = 0
    private var width: Int = 0
    private var height: Int = 0

    override fun create() {
        shader = ShaderProgram(Gdx.files.internal("vertex.glsl"), Gdx.files.internal("fragment.glsl"))
        if (!shader.isCompiled) {
            System.err.println(shader.log)
            System.exit(-1)
        }

        mesh = Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked(), VertexAttribute.TexCoords(0))
        mesh.setVertices(floatArrayOf(
            -0.5f, -0.5f, 0f, 1f, 1f, 1f, 1f, 0f, 1f,
            0.5f, -0.5f, 0f, 1f, 1f, 1f, 1f, 1f,
            1f, 0.5f, 0.5f, 0f, 1f, 1f, 1f, 1f, 1f, 0f,
            -0.5f, 0.5f, 0f, 1f, 1f, 1f, 1f, 0f, 0f
        ))
        mesh.setIndices(shortArrayOf(0, 1, 2, 2, 3, 0))

        startTime = System.currentTimeMillis()
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        shader.begin()
//        shader.setUniformf("iTime", (System.currentTimeMillis() - startTime) / 1000f)
//        shader.setUniformf("iResolution", Vector2(width.toFloat(), height.toFloat()))
        mesh.render(shader, GL20.GL_TRIANGLES)
        shader.end()
    }

    override fun resize(width: Int, height: Int) {
        this.width = width;
        this.height = height
    }

}
