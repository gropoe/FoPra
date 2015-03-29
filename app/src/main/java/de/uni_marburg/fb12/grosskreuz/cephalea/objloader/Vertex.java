package de.uni_marburg.fb12.grosskreuz.cephalea.objloader;

/**
 * Created by Felix on 31.03.2015.
 */
public class Vertex {
    private float[] position;
    private float[] color;
    private float[] texCoord;
    private float[] normal;

    public Vertex(float[] position, float[] color, float[] texCoord, float[] normal) {
        this.position = position;
        this.color = color;
        this.texCoord = texCoord;
        this.normal = normal;
    }

    public float[] getPosition() {
        return position;
    }

    public float[] getColor() {
        return color;
    }

    public float[] getTexCoord() {
        return texCoord;
    }

    public float[] getNormal() {
        return normal;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    public void setColor(float[] color) {
        this.color = color;
    }

    public void setTexCoord(float[] texCoord) {
        this.texCoord = texCoord;
    }

    public void setNormal(float[] normal) {
        this.normal = normal;
    }
}
