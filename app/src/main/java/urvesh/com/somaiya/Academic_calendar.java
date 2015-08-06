package urvesh.com.somaiya;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static urvesh.com.somaiya.R.id.app_bar;


public class Academic_calendar extends ActionBarActivity {

    private RecyclerView recyclerView;
    private AcademicAdapter academicAdapter;
    private Toolbar mToolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);
        mToolbar = (Toolbar) findViewById(app_bar);
        setSupportActionBar(mToolbar);
        recyclerView = (RecyclerView) findViewById(R.id.academicList);
        academicAdapter = new AcademicAdapter(getApplicationContext(),getData());
        recyclerView.setAdapter(academicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    public static List<Info_academic> getData(){
        List<Info_academic> data = new ArrayList<>();
        String[] title = {"College Reopens","Commencement of Lectures for all First Year classes.(Depending on Mumbai University)","Ramzan EID","Independence Day","Parsi New Year (Shahenshahi)","INTERNAL CLASS TEST (I/III/V) begins","ADDITIONAL INTERNAL CLASS TEST (I/III/V) begins","Ganesh Chaturthi","Bakari-EID","S.Y. REGULAR (ODD SEM) and Noncredit ATKT Exam begins","Mahatma Gandhi Jayanti","F.Y. REGULAR (ODD SEM) Exam begins","Dassera","Moharum","Diwali Vacation","S.Y. (ODD SEM) RESULT DECLARATION","Diwali Amavasya (Lakshmi Pujan)","Diwali (Balipratipada)","College reopens after Diwali Vacation: Lectures resume as per Time Table","F.Y. (ODD SEM) Result Declaration","Guru Nanak Jayanti","Utkarsh Celebrations","Acceptance of the forms for the best student selection","NSS camp","Id-E-Milad","Christmas","Winter Vacation","College reopens after Winter Vacation","Interview for Best Student Selection","Internal Class Test (II/IV/VI)","Republic Day","College Sports Day","Annual Prize Distribution Function","Additional Internal Class Test (II/IV/VI)","Chhatrapati Shivaji Maharaj Jayanti","S.Y. Regular (EVEN SEM) & NON CREDIT ATKT begins","Maha Shivratri","F.Y. Regular (EVEN SEM) begins","FY/SY ODD SEM Additional Exam begins","Holi","Good Friday","S.Y. (EVEN SEM) Result Declaration","F.Y. (EVEN SEM) Result Declaration","Gudi Padwa","Dr. Babasaheb Ambedkar Jayanti","Ram Navami","Mahavir Jayanti","EVEN SEM Additional Exam begins","Staff Common Room Meeting - Last Working Day"};
        String[] subTitle = {"8th June 2015","1st July 2015","18th July 2015","15th August 2015","18th August 2015","19th August 2015","9th September 2015","17th September 2015","25th September 2015","28th September 2015","2nd October 2015","6th October 2015","22nd October 2015","24th October 2015","22nd October 2015 - 15th November 2015","5th November 2015","11th November 2015","12th November 2015","16th November 2015","18th November 2015","25th November 2015","3rd December 2015 - 5th December 2015","7th December 2015 - 11th December 2015","17th December 2015 - 23rd December 2015","24th December 2015","25th December 2015","26th December 2015 - 1th January 2016","2nd January 2016","4th January 2016 - 5th January 2016","19th January 2016","26th January 2016","30th January 2016","6th February 2016","8th February 2016","19th February 2016","1st March 2016","7th March 2016","9th March 2016","21th March 2016","24th March 2016","25th March 2016","1st April 2016","6th April 2016","8th April 2016","14th April 2016","15th April 2016","19th April 2016","20th April 2016","2nd May 2016"};
        String[] color = {"college","college","public","public","public","college","college","public","public","college","public","college","public","public","college","college","public","public","college","college","public","college","college","college","public","public","college","college","college","college","public","college","college","college","public","college","public","college","college","public","public","college","college","public","public","public","public","college","college"};
        for(int i = 0; i < title.length&& i < subTitle.length; i++){
            Info_academic current = new Info_academic();
            current.title = title[i];
            current.subTitle = subTitle[i];
            current.color = color[i];
            data.add(current);
        }
        return data;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_academic_calendar, menu);
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
