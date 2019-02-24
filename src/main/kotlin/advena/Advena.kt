package advena

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Mesh
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import org.lwjgl.opengl.GL20
import com.badlogic.gdx.graphics.VertexAttribute



class Advena : ApplicationAdapter(){

    private lateinit var shader: ShaderProgram
    private lateinit var mesh: Mesh

    override fun create() {
        val vertexShader = Gdx.files.internal("vertex.glsl").readString()
        val fragmentShader = Gdx.files.internal("fragment.glsl").readString()
        shader = ShaderProgram(vertexShader, fragmentShader)
        mesh = Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked(), VertexAttribute
            .TexCoords(0))
        mesh.setVertices(floatArrayOf(-0.5f, -0.5f, 0f, 1f, 1f, 1f, 1f, 0f, 1f, 0.5f, -0.5f, 0f, 1f, 1f, 1f, 1f, 1f,
            1f, 0.5f, 0.5f, 0f, 1f, 1f, 1f, 1f, 1f, 0f, -0.5f, 0.5f, 0f, 1f, 1f, 1f, 1f, 0f, 0f))
        mesh.setIndices(shortArrayOf(0, 1, 2, 2, 3, 0))
    }

    override fun dispose() {
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        shader.begin()
//        shader.setUniformMatrix("u_projTrans", matrix)
//        shader.setUniformi("u_texture", 0)
        mesh.render(shader, GL20.GL_TRIANGLES)
        shader.end();
    }

}

