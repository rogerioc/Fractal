package rogerio.com.fractal

import android.app.Activity
import android.os.Bundle

class fractal : Activity() {
    /** Called when the activity is first created.  */
    var screen: Screen? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screen = Screen(this)
        setContentView(screen)
    }
}