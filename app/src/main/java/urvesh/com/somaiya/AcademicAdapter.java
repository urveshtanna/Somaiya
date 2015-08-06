package urvesh.com.somaiya;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by User on 19-07-2015.
 */
public class AcademicAdapter extends RecyclerView.Adapter<AcademicAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    List<Info_academic> data = Collections.emptyList();
    public AcademicAdapter(Context context, List<Info_academic> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.academic_list_row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Info_academic current = data.get(position);
        holder.title.setText(current.title);
        holder.subTitle.setText(current.subTitle);
        if(current.color.equals("college")){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#D32F2F"));
        }
        else if(current.color.equals("public")){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#388E3C"));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,subTitle;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subTitle = (TextView) itemView.findViewById(R.id.subTitle);
            cardView = (CardView) itemView.findViewById(R.id.cardAcademic);

        }
    }
}
