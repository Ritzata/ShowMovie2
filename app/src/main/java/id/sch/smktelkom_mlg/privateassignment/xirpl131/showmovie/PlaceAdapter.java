package id.sch.smktelkom_mlg.privateassignment.xirpl131.showmovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by LENOVO Z40-75 on 14/05/2017.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder>{
    private final Context context;
    ArrayList<Place> pItem;

    public PlaceAdapter(ArrayList<Place> place, Context context) {
        this.pItem = place;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Place place = pItem.get(position);
        holder.textViewHeadPlace.setText(place.title);
        holder.textViewDescPlace.setText(place.description);
        Glide
                .with(context)
                .load(place.urlgambar)
                .into(holder.imageViewOtofPlace);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHeadPlace;
        public TextView textViewDescPlace;
        public ImageView imageViewOtofPlace;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewHeadPlace = (TextView) itemView.findViewById(R.id.textViewHeadPlace);
            textViewDescPlace = (TextView) itemView.findViewById(R.id.textViewDescPlace);
            imageViewOtofPlace = (ImageView) itemView.findViewById(R.id.imageViewOtofPlace);
        }
    }

}

