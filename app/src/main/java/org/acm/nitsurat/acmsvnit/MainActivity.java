package org.acm.nitsurat.acmsvnit;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mEventRecyclerView;
    private	EventAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        if(!MainActivity.checkConnection(getApplicationContext()))
        {
            TextView statusTV=(TextView)findViewById(R.id.status);
            statusTV.setText("No Internet Connectivity\n\nTry Again Later");
        }
        else
        {
            mEventRecyclerView	=	(RecyclerView)findViewById(R.id.event_list);
            mEventRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            updateUI();
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        reset();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public static boolean checkConnection(Context context) {
        ConnectivityManager connectivityManager =  (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo()==null)
            return false;
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        reset();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id==R.id.exit)
        {
            finish();
        }
        else if(id==R.id.query)
        {
            Intent intent=new Intent(getApplicationContext(),QueryActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.suggestion)
        {
            Intent intent=new Intent(getApplicationContext(),SuggestionActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.team)
        {
            Intent intent=new Intent(getApplicationContext(),TeamActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void reset(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
    }
    private	class	EventHolder	extends	RecyclerView.ViewHolder	{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mTimingTextView;
        public	EventHolder(View itemView)	{
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title);
            mDateTextView = (TextView) itemView.findViewById(R.id.date);
            mTimingTextView = (TextView) itemView.findViewById(R.id.time);
        }
    }
    private	class EventAdapter extends RecyclerView.Adapter<EventHolder>	{
        private List<Event> mEvents;
        public	EventAdapter(List<Event> events)	{
            mEvents = events;
        }
        @Override
        public	EventHolder	onCreateViewHolder(ViewGroup parent, int	viewType)	{
            LayoutInflater layoutInflater	=	LayoutInflater.from(getApplicationContext());
            View view = layoutInflater.inflate(R.layout.list_item_event, parent, false);
            return	new	EventHolder(view);
        }
        @Override
        public	void	onBindViewHolder(EventHolder holder, int position)	{
            Event event	=	mEvents.get(position);
            holder.mTitleTextView.setText(event.getTitle());
            holder.mDateTextView.setText(event.getDate());
            holder.mTimingTextView.setText(event.getTimings());
        }
        @Override
        public	int	getItemCount()	{
            return	mEvents.size();
        }
    }
    private	void	updateUI()	{
        List<Event> events=new ArrayList<Event>();
        for(int i=0; i < 25; i++)
        {
          events.add(new Event("Event "+i,"January 10, 2017","12:00 AM"));
        }
        mAdapter	=	new	EventAdapter(events);
        mEventRecyclerView.setAdapter(mAdapter);
    }
}