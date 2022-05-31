package mindustry.graphics;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.gl.*;
import arc.math.*;
import arc.util.*;

public class IndexedRenderer implements Disposable{
    private static final int vsize = 5;

    private final Shader program = new Shader(
    """
    attribute vec4 a_position;
    attribute vec4 a_color;
    attribute vec2 a_texCoord0;
    uniform mat4 u_projTrans;
    varying vec4 v_color;
    varying vec2 v_texCoords;
    void main(){
       v_color = a_color;
       v_color.a = v_color.a * (255.0/254.0);
       v_texCoords = a_texCoord0;
       gl_Position = u_projTrans * a_position;
    }
    """,

    """
    varying lowp vec4 v_color;
    varying vec2 v_texCoords;
    uniform sampler2D u_texture;
    void main(){
      gl_FragColor = v_color * texture2D(u_texture, v_texCoords);
    }
    """
    );
    private Mesh[][] meshes;
    private float[] tmpVerts = new float[vsize * 6];

    private Mat[][] projMatrices;
    private Mat[][] transMatrices;
    private Mat[][] combineds;
    private float color = Color.white.toFloatBits();
    private final int numChunkRow;
    private final int numChunkCol;

    public IndexedRenderer(int numRow, int numCol, int sprites){
        this.numChunkRow = numRow;
        this.numChunkCol = numCol;

        this.projMatrices = new Mat[this.numChunkCol][this.numChunkRow];
        this.transMatrices = new Mat[this.numChunkCol][this.numChunkRow];
        this.combineds = new Mat[this.numChunkCol][this.numChunkRow];
        for(int x = 0; x < this.numChunkCol; x++){
            for(int y = 0; y < this.numChunkRow; y++){
                this.projMatrices[x][y] = new Mat();
                this.transMatrices[x][y] = new Mat();
                this.combineds[x][y] = new Mat();
            }
        }

        resize(sprites);
    }

    public void render(int col, int row, Texture texture){
        Gl.enable(Gl.blend);

        updateMatrix(col, row);

        program.bind();
        texture.bind();

        program.setUniformMatrix4("u_projTrans", combineds[col][row]);
        program.setUniformi("u_texture", 0);

        meshes[col][row].render(program, Gl.triangles, 0, meshes[col][row].getMaxVertices());
    }

    public void setColor(Color color){
        this.color = color.toFloatBits();
    }

    public void draw(int col, int row, int index, TextureRegion region, float x, float y, float w, float h){
        float fx2 = x + w;
        float fy2 = y + h;
        float u = region.u;
        float v = region.v2;
        float u2 = region.u2;
        float v2 = region.v;

        float[] vertices = tmpVerts;
        float color = this.color;

        int idx = 0;
        vertices[idx++] = x;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;

        vertices[idx++] = x;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;

        vertices[idx++] = fx2;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;

        //tri2
        vertices[idx++] = fx2;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;

        vertices[idx++] = fx2;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;

        vertices[idx++] = x;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;

        meshes[col][row].updateVertices(index * vsize * 6, vertices);
    }

    public void draw(int col, int row, int index, TextureRegion region, float x, float y, float w, float h, float rotation){
        float u = region.u;
        float v = region.v2;
        float u2 = region.u2;
        float v2 = region.v;

        float originX = w / 2, originY = h / 2;

        float cos = Mathf.cosDeg(rotation);
        float sin = Mathf.sinDeg(rotation);

        float fx = -originX;
        float fy = -originY;
        float fx2 = w - originX;
        float fy2 = h - originY;

        float worldOriginX = x + originX;
        float worldOriginY = y + originY;

        float x1 = cos * fx - sin * fy + worldOriginX;
        float y1 = sin * fx + cos * fy + worldOriginY;
        float x2 = cos * fx - sin * fy2 + worldOriginX;
        float y2 = sin * fx + cos * fy2 + worldOriginY;
        float x3 = cos * fx2 - sin * fy2 + worldOriginX;
        float y3 = sin * fx2 + cos * fy2 + worldOriginY;
        float x4 = x1 + (x3 - x2);
        float y4 = y3 - (y2 - y1);

        float[] vertices = tmpVerts;
        float color = this.color;

        int idx = 0;
        vertices[idx++] = x1;
        vertices[idx++] = y1;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;

        vertices[idx++] = x2;
        vertices[idx++] = y2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;

        vertices[idx++] = x3;
        vertices[idx++] = y3;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;

        //tri2
        vertices[idx++] = x3;
        vertices[idx++] = y3;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;

        vertices[idx++] = x4;
        vertices[idx++] = y4;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;

        vertices[idx++] = x1;
        vertices[idx++] = y1;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;

        meshes[col][row].updateVertices(index * vsize * 6, vertices);
    }

    public Mat getTransformMatrix(int col, int row){
        return transMatrices[col][row];
    }

    public void setProjectionMatrix(int col, int row, Mat matrix){
        projMatrices[col][row] = matrix;
    }

    public void resize(int sprites){
        this.meshes = new Mesh[this.numChunkCol][this.numChunkRow];

        for(int x = 0; x < this.numChunkCol; x++){
            for(int y = 0; y < this.numChunkRow; y++){
                if(this.meshes[x][y] != null) this.meshes[x][y].dispose();
                this.meshes[x][y] = new Mesh(true, 6 * sprites, 0,
                        VertexAttribute.position,
                        VertexAttribute.color,
                        VertexAttribute.texCoords);
                this.meshes[x][y].setVertices(new float[6 * sprites * vsize]);
            }
        }
    }

    private void updateMatrix(int col, int row){
        combineds[col][row].set(projMatrices[col][row]).mul(transMatrices[col][row]);
    }

    @Override
    public void dispose(){
        for(int x = 0; x < this.numChunkCol; x++) {
            for (int y = 0; y < this.numChunkRow; y++) {
                meshes[x][y].dispose();
            }
        }
        program.dispose();
    }

    public int getNumChunkRow() {
        return numChunkRow;
    }

    public int getNumChunkCol() {
        return numChunkCol;
    }
}
