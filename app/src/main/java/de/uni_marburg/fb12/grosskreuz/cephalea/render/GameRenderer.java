package de.uni_marburg.fb12.grosskreuz.cephalea.render;

import de.uni_marburg.fb12.grosskreuz.cephalea.draw.DrawBackground;
import de.uni_marburg.fb12.grosskreuz.cephalea.draw.DrawFloor;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.google.vrtoolkit.cardboard.CardboardView;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;


import javax.microedition.khronos.egl.EGLConfig;

import de.uni_marburg.fb12.grosskreuz.cephalea.ResLoader;
import de.uni_marburg.fb12.grosskreuz.cephalea.draw.DrawTeapot;

/**
 * We should discuss to exclude the variables or make them global.
 *
 * Created by Felix on 31.03.2015.
 */
public class GameRenderer implements CardboardView.StereoRenderer{

    private static final String TAG = "GameRenderer";
    private static final float[] LIGHT_POS_IN_WORLD_SPACE = new float[] { 0.0f, 2.0f, 0.0f, 1.0f };
    private static final float Z_NEAR = 0.1f;
    private static final float Z_FAR = 100.0f;
    private static final float CAMERA_Z = 0.01f;
    private final float[] lightPosInEyeSpace = new float[4];

    private float[] camera;
    private float[] view;
    private float[] headView;
    private float[] modelViewProjection;
    private float[] modelView;

    private DrawFloor drawFloor;
    private DrawFloor drawCeiling;
    private DrawTeapot drawTeapot;
    private DrawBackground drawBackground;

    private ResLoader resLoader;

    public GameRenderer(ResLoader resLoader) {
        this.resLoader = resLoader;

        camera = new float[16];
        view = new float[16];
        modelViewProjection = new float[16];
        modelView = new float[16];
        headView = new float[16];

        drawFloor = new DrawFloor(resLoader,
                modelView,
                modelViewProjection,
                LIGHT_POS_IN_WORLD_SPACE,
                lightPosInEyeSpace,
                true);
        drawCeiling = new DrawFloor(resLoader,
                modelView,
                modelViewProjection,
                LIGHT_POS_IN_WORLD_SPACE,
                lightPosInEyeSpace,
                false);
        drawTeapot = new DrawTeapot(resLoader,
                modelView,
                modelViewProjection,
                LIGHT_POS_IN_WORLD_SPACE,
                lightPosInEyeSpace);
        drawBackground = new DrawBackground();
    }

    /**
     *
     * @param headTransform The head transformation in the new frame.
     */
    @Override
    public void onNewFrame(HeadTransform headTransform) {

        // Build the camera matrix and apply it to the ModelView.
        Matrix.setLookAtM(camera, 0, 0.0f, 0.0f, CAMERA_Z, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);

        headTransform.getHeadView(headView, 0);

        checkGLError("onReadyToDraw");
    }

    /**
     * In onDrawEye we call the draw methods.
     *
     * @param eye The eye to render. Includes all required transformations.
     */

    @Override
    public void onDrawEye(Eye eye) {
        checkGLError("colorParam");
        drawBackground.draw();

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        checkGLError("colorParam");

        // Apply the eye transformation to the camera.
        Matrix.multiplyMM(view, 0, eye.getEyeView(), 0, camera, 0);

        // Set the position of the light
        Matrix.multiplyMV(lightPosInEyeSpace, 0, view, 0, LIGHT_POS_IN_WORLD_SPACE, 0);

        // Set modelView for the floor, so we draw floor in the correct location
        float[] perspective = eye.getPerspective(Z_NEAR, Z_FAR);
        drawFloor.draw(perspective, view);
        drawCeiling.draw(perspective, view);
        drawTeapot.draw(perspective, view);



    }

    @Override
    public void onFinishFrame(Viewport viewport) {

    }

    @Override
    public void onSurfaceChanged(int i, int i2) {

    }

    @Override
    public void onSurfaceCreated(EGLConfig eglConfig) {
        drawFloor.onSurfaceCreated();
        drawCeiling.onSurfaceCreated();
        drawTeapot.onSurfaceCreated();

    }

    @Override
    public void onRendererShutdown() {


    }


    /**
     * Checks if we've had an error inside of OpenGL ES, and if so what that error is.
     *
     * @param label Label to report in case of error.
     */
    private static void checkGLError(String label) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, label + ": glError " + error);
            throw new RuntimeException(label + ": glError " + error);
        }
    }
}