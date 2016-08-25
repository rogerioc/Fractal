package rogerio.com.fractal;

import android.app.Activity;
import android.os.Bundle;

public class fractal extends Activity {
    /** Called when the activity is first created. */
	Screen screen; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = new Screen(this);
        setContentView(screen);
    }
}