package id.sch.smktelkom_mlg.privateassignment.xirpl131.showmovie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by LENOVO Z40-75 on 08/05/2017.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<HomeListItem> homeListItems;
    private Context context;

    public HomeAdapter(List<HomeListItem> homeListItems, Context context) {
        this.homeListItems = homeListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HomeListItem homeitem = homeListItems.get(position); // yang homelistitem untuk ngeload pertama kali

        holder.textViewHead.setText(homeitem.getHead());
        holder.textViewDesc.setText(homeitem.getDesc());

        Glide // untuk gambar
                .with(context)
                .load(homeitem.getImageUrl())
                .into(holder.imageViewOtof);

        holder.linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "MANTAB" + homeitem.getHead(), Toast.LENGTH_LONG).show();
                Intent singleBlogIntent = new Intent(context, DetailActivity.class);
                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //membuka activity baru dari fragment
                singleBlogIntent.putExtra("blog_id",position); // mengirimkan variabel untuk mengirim positionnya
                context.startActivity(singleBlogIntent); // menampilkan activity
            }
        });
    }


    @Override
    public int getItemCount() {

        return homeListItems.size(); //berapa banyak data yang akan ditampilkan
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageViewOtof;
        public LinearLayout linearLayout; //buat ini dulu baru bisa buat di linear layout pada line -->holder bawahnya glide

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            imageViewOtof = (ImageView) itemView.findViewById(R.id.imageViewOtof);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout);

        }
    }

}
