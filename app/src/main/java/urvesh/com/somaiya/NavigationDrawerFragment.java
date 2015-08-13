package urvesh.com.somaiya;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nispok.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment{

    private  InfoAdapter adapter;
    public RecyclerView recyclerView;
    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mdrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    public Intent intent;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private TextView username;

    public NavigationDrawerFragment() {
        // Required empty public constructor

    }
    public static List<Info_drawer> getData(){
        List<Info_drawer> data = new ArrayList<>();
        String[] title = {"Academic Calendar","Campus","Courses","News","Student","About Us"};
        int[] icons ={ R.drawable.ic_event_drawer, R.drawable.ic_campus_drawer,R.drawable.ic_course_drawer,R.drawable.ic_new_drawer, R.drawable.ic_student_drawer, R.drawable.ic_about_drawer};
        for(int i = 0; i <title.length;i++){
            Info_drawer current = new Info_drawer();
            current.iconId = icons[i];
            current.title = title[i];
            data.add(current);
        }
        return data;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));
        if(savedInstanceState != null){
            mFromSavedInstanceState = true;
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new InfoAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecycleListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(),Academic_calendar.class));
                        break;
                    case 1:
                        //Snackbar.with(getActivity()).text("On Click " + position).textColor(Color.WHITE).color(Color.rgb(66, 66, 66)).show(getActivity());
                        startActivity(new Intent(getActivity(),Location.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(),Course.class));
                        break;
                    case 3:
                        Snackbar.with(getActivity()).text("On Click " + position).textColor(Color.WHITE).color(Color.rgb(66, 66, 66)).show(getActivity());
                        break;
                    case 4:
                        Snackbar.with(getActivity()).text("On Click " + position).textColor(Color.WHITE).color(Color.rgb(66, 66, 66)).show(getActivity());
                        break;
                    case 5:
                        Snackbar.with(getActivity()).text("On Click " + position).textColor(Color.WHITE).color(Color.rgb(66, 66, 66)).show(getActivity());
                        break;
                }
                if(position == 2){

                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return layout;
    }


    public void setUp(int fragmentId,DrawerLayout drawerLayout, Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mdrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER,mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        if(!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mdrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mdrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.apply();
    }
    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }
    class RecycleListener implements RecyclerView.OnItemTouchListener{
        private GestureDetector gestureDetector;
        private ClickListener clickListener;
        public RecycleListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {

                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if(childView!=null && clickListener!=null){
                        clickListener.onLongClick(childView,recyclerView.getChildPosition(childView));
                    }
                    super.onLongPress(e);
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View childView=recyclerView.findChildViewUnder(e.getX(),e.getY());
            if(childView!=null && clickListener!=null && gestureDetector.onTouchEvent(e)){
                    clickListener.onClick(childView,rv.getChildPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }
    }
    public static interface  ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }
}
