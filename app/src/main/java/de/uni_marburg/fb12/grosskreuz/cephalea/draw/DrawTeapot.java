package de.uni_marburg.fb12.grosskreuz.cephalea.draw;

import de.uni_marburg.fb12.grosskreuz.cephalea.ResLoader;

/**
 * TODO: all.
 *
 * Created by Felix on 05.04.2015.
 */
public class DrawTeapot {

    float[] modelView;
    float[] modelViewProjection;
    float[] LIGHT_POS_IN_WORLD_SPACE;
    float[] lightPosInEyeSpace;

    private ResLoader resLoader;

    public DrawTeapot(ResLoader resLoader,
                      float[] modelView,
                      float[] modelViewProjection,
                      float[] LIGHT_POS_IN_WORLD_SPACE,
                      float[] lightPosInEyeSpace) {

        this.resLoader = resLoader;
        this.modelView = modelView;
        this.modelViewProjection = modelViewProjection;
        this.LIGHT_POS_IN_WORLD_SPACE = LIGHT_POS_IN_WORLD_SPACE;
        this.lightPosInEyeSpace = lightPosInEyeSpace;

    }
    public void onSurfaceCreated() {

    }

    public void draw(float[] perspective, float[] view) {

    }
}
