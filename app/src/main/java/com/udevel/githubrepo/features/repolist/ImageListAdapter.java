package com.udevel.githubrepo.features.repolist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.udevel.githubrepo.R;

import java.util.List;

/**
 * Created by benny_zw on 1/13/2018.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    private static final String TAG = ImageListAdapter.class.getSimpleName();
    @NonNull
    private final RequestManager glide;
    private List<String> avatarUrlList;

    public ImageListAdapter(@NonNull RequestManager glide) {
        this.glide = glide;
    }

    public void updateData(@NonNull List<String> avatarUrlList){
        this.avatarUrlList = avatarUrlList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_avatar_image, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        glide.load(avatarUrlList.get(position)).into(viewHolder.imageViewAvatar);
    }

    @Override
    public int getItemCount() {
        return avatarUrlList.size();
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewAvatar;

        ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            imageViewAvatar = v.findViewById(R.id.avatar);
        }
    }
}
