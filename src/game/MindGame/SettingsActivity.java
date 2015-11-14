package game.MindGame;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by DVitinnik on 20-Oct-15.
 */
public class SettingsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        getActionBar().hide();
    }
}
