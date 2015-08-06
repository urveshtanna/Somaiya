package urvesh.com.somaiya;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by User on 06-08-2015.
 */
public class UserSettingActivity extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
