package org.acm.nitsurat.acmsvnit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity {
    private RecyclerView mTeamRecyclerView;
    private MemberAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        if(!MainActivity.checkConnection(getApplicationContext()))
        {
            TextView statusTV=(TextView)findViewById(R.id.status);
            statusTV.setText("No Internet Connectivity\n\nTry Again Later");
        }
        else
        {
            mTeamRecyclerView	=	(RecyclerView)findViewById(R.id.team_list);
            mTeamRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
            updateUI();
        }
    }
    private	void	updateUI()	{
        List<TeamMember> teamMembers=new ArrayList<TeamMember>();
        for(int i=0; i < 24; i++)
        {
            teamMembers.add(new TeamMember("Member "+i,"Executive","2016-2017","Java","1234567890",BitmapFactory.decodeResource(getResources(),R.drawable.team)));
        }
        mAdapter = new MemberAdapter(teamMembers);
        mTeamRecyclerView.setAdapter(mAdapter);
    }
    private	class	MemberHolder	extends	RecyclerView.ViewHolder	{
        private TextView mNameTextView;
        private TextView mYearTextView;
        private TextView mRoleTextView;
        private TextView mMobNoTextView;
        private ImageView mMemberImage;
        public	MemberHolder(View itemView)	{
            super(itemView);
            mNameTextView = (TextView)itemView.findViewById(R.id.name);
            mYearTextView = (TextView)itemView.findViewById(R.id.year);
            mRoleTextView = (TextView)itemView.findViewById(R.id.role);
            mMemberImage = (ImageView)itemView.findViewById(R.id.img);
        }
    }
    private	class MemberAdapter extends RecyclerView.Adapter<MemberHolder>	{
        private List<TeamMember> mTeamMembers;
        public	MemberAdapter(List<TeamMember> teamMembers)	{
            mTeamMembers = teamMembers;
        }
        @Override
        public	MemberHolder onCreateViewHolder(ViewGroup parent, int	viewType)	{
            LayoutInflater layoutInflater	=	LayoutInflater.from(TeamActivity.this);
            View view = layoutInflater.inflate(R.layout.list_item_team, parent, false);
            return	new	MemberHolder(view);
        }
        @Override
        public	void	onBindViewHolder(MemberHolder holder, int position)	{
            TeamMember member	=	mTeamMembers.get(position);
            holder.mNameTextView.setText(member.getMemberName());
            holder.mYearTextView.setText(member.getYear());
            holder.mRoleTextView.setText(member.getRole());
            holder.mMemberImage.setImageBitmap(getCircleBitmap(member.getImage()));
        }
        @Override
        public	int	getItemCount()	{
            return	mTeamMembers.size();
        }
    }
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
