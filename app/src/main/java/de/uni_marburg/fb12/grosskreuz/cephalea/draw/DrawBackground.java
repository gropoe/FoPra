package de.uni_marburg.fb12.grosskreuz.cephalea.draw;

import android.opengl.GLES20;

/**
 * Just sets the background color, but we might extend this class later
 *
 * Created by Felix on 05.04.2015.
 */
public class DrawBackground {
    public void draw() {
        GLES20.glClearColor(0.01f, 0.01f, 0.01f, 0.5f);
    }
}