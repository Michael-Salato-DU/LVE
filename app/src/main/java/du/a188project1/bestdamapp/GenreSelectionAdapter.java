package du.a188project1.bestdamapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class GenreSelectionAdapter extends RecyclerView.Adapter<GenreSelectionAdapter.GenreViewHolder>{

    private Context context;
    private ArrayList<String> genreList;
    private CompoundButton.OnCheckedChangeListener cListener;

    //Constructor for the Adapter
    public GenreSelectionAdapter(Context context, ArrayList<String> dataset, CompoundButton.OnCheckedChangeListener listener){
        this.context = context;
        this.genreList = dataset;
        this.cListener = listener;
    }

    //Create GenreViewHolder that inherits RecyclerView
    public class GenreViewHolder extends RecyclerView.ViewHolder  {

        public CheckBox genreChkBox;
        //Checkbox listener
        private CompoundButton.OnCheckedChangeListener cListener;

        //Constructor for the GenreViewHolder class
        public GenreViewHolder(View itemView, CompoundButton.OnCheckedChangeListener listener) {
            super(itemView);
            genreChkBox = itemView.findViewById(R.id.genre_type);
            cListener = listener;
            genreChkBox.setOnCheckedChangeListener(listener);
        }

    }
    //utilized abstract method from RecyclerView adapter, just a fill-in to prevent errors
    public int getItemCount(){
        return genreList.size();
    }

    //ViewHolder referencing genre_cell.xml
    public GenreSelectionAdapter.GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_cell, parent, false);
        GenreViewHolder vh = new GenreViewHolder(v,cListener);
        return vh;
    }

    //Updates ViewHolder with types of genres from array
    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.genreChkBox.setText(genreList.get(position));
    }


}
