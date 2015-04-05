package de.uni_marburg.fb12.grosskreuz.cephalea;

import android.os.Bundle;

import com.google.vrtoolkit.cardboard.CardboardActivity;
import com.google.vrtoolkit.cardboard.CardboardView;
import de.uni_marburg.fb12.grosskreuz.cephalea.render.GameRenderer;

/**
 * Created by Felix on 31.03.2015.
 */

public class MainActivity extends CardboardActivity{

    private static final String TAG = "MainActivity";

    private CardboardOverlayView overlayView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        CardboardView cardboardView = (CardboardView) findViewById(R.id.cardboard_view);
        cardboardView.setRenderer(new GameRenderer(new ResLoader(this.getApplicationContext())));
        setCardboardView(cardboardView);

        overlayView = (CardboardOverlayView) findViewById(R.id.overlay);
        overlayView.show3DToast("Hello VR World!");
    }
}
