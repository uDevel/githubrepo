package com.udevel.githubrepo.features.repolist;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.udevel.githubrepo.R;
import com.udevel.githubrepo.utils.GlideUtil;

import java.util.List;
import java.util.Random;

/**
 * Created by benny_zw on 1/13/2018.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    private static final String TAG = ImageListAdapter.class.getSimpleName();
    @NonNull
    private final RequestManager glide;
    private final Random random;
    private List<String> avatarUrlList;

    public ImageListAdapter(@NonNull RequestManager glide) {
        this.glide = glide;
        random = new Random();
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
        RequestBuilder<Drawable> requestBuilder = glide.load(avatarUrlList.get(position))
                .apply(GlideUtil.glideOptions);

        // No animation most of the time.
        int nextInt = random.nextInt(10);
        switch (nextInt) {
            case 0:
                requestBuilder = requestBuilder.transition(GenericTransitionOptions.with(GlideUtil.animationObject));
                break;
            case 1:
                requestBuilder = requestBuilder.transition(GenericTransitionOptions.with(GlideUtil.animationObjectDelayed));
                break;
            case 2:
                requestBuilder = requestBuilder.transition(GenericTransitionOptions.with(GlideUtil.animationObjectDelayed2));
                break;
            default:
                // No animation.
                break;
        }

        requestBuilder
                .into(viewHolder.imageViewAvatar);
    }

    @Override
    public int getItemCount() {
        return avatarUrlList.size();
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        glide.clear(holder.imageViewAvatar);
    }

    public void updateData(@NonNull List<String> avatarUrlList) {
        int originalSize = avatarUrlList.size();
        this.avatarUrlList = avatarUrlList;
        notifyItemRangeInserted(originalSize + 1, avatarUrlList.size());
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
