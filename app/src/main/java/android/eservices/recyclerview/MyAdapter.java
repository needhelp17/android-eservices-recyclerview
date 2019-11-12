package android.eservices.recyclerview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<GameViewModel> mDataset;
    private GameActionInterface game;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView desc;
        public ImageView back;
        public ImageButton info;
        public ImageButton imge;

        public MyViewHolder(View v) {
            super(v);
            title  = v.findViewById(R.id.title_textview);
            desc = v.findViewById(R.id.description_textview);
            back = v.findViewById(R.id.icon_imageview);
            info = v.findViewById(R.id.info_button);
            imge = v.findViewById(R.id.game_button);

        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<GameViewModel> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        if (parent.getContext() instanceof GameActionInterface) {
            game = (GameActionInterface) parent.getContext();
        }

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(mDataset.get(position).getTitle());
        holder.desc.setText(mDataset.get(position).getDescription());
        //holder.back.setImageResource(R.drawable.borderlands);
        //holder.back.
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(holder.back).load(mDataset.get(position).getImageUrl()).apply(options).into(holder.back);

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.onGameInfoClicked(holder.title.getText().toString());
            }
        });
        holder.imge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.onGameClicked(holder.title.getText().toString());
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
