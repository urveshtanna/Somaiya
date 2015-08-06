package urvesh.com.somaiya;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import static urvesh.com.somaiya.R.id.app_bar;
import static urvesh.com.somaiya.R.id.app_bar_arts1;
import static urvesh.com.somaiya.R.id.app_bar_commerce1;
import static urvesh.com.somaiya.R.id.app_bar_science1;


public class Course extends ActionBarActivity {

    private Toolbar mToolbar,toolbarArts,toolbarCommerce,toolbarScience;
    public LinearLayout scienceLayout,commerceLayout,artsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        mToolbar = (Toolbar) findViewById(app_bar);
        toolbarArts = (Toolbar) findViewById(app_bar_arts1);
        toolbarArts.setTitle(R.string.title_activity_arts_courses);
        toolbarCommerce = (Toolbar) findViewById(app_bar_commerce1);
        toolbarCommerce.setTitle(R.string.title_activity_commerce_courses);
        toolbarScience = (Toolbar) findViewById(app_bar_science1);
        toolbarScience.setTitle(R.string.title_activity_science);
        setSupportActionBar(mToolbar);
        if(Build.VERSION.SDK_INT >= 21){

        }

        scienceLayout = (LinearLayout) findViewById(R.id.layout_science);
        scienceLayout.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Science_main.class);
                String transitionName = getString(R.string.action_bar_animation_science);
                View sharedElements = toolbarScience;
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(Course.this,sharedElements,transitionName);
                startActivity(intent,activityOptions.toBundle());
            }
        });
        commerceLayout = (LinearLayout) findViewById(R.id.layout_commerce);
        commerceLayout.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Commerce_courses.class);
                String transitionName = getString(R.string.action_bar_animation_commerce);
                View sharedElements = toolbarCommerce;
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(Course.this,sharedElements,transitionName);
                startActivity(intent,activityOptions.toBundle());
            }
        });
        artsLayout = (LinearLayout) findViewById(R.id.layout_arts);
        artsLayout.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Arts_courses.class);
                String transitionName = getString(R.string.action_bar_animation_arts);
                View sharedElements = toolbarArts;
                ActivityOptions transitionActivity = ActivityOptions.makeSceneTransitionAnimation(Course.this,sharedElements,transitionName);
                startActivity(intent,transitionActivity.toBundle());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
