package com.ngwiri.flexnany.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ngwiri.flexnany.R;
import com.ngwiri.flexnany.models.Maids;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class maidsListAdapter extends RecyclerView.Adapter<maidsListAdapter.maidsViewHolder> {

    private ArrayList<Maids> mMaids = new ArrayList<>();
    private Context mContext;

    public maidsListAdapter(Context context, ArrayList<Maids> maids){

        mContext = context;
        mMaids = maids;
    }

    @Override
    public maidsListAdapter.maidsViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.maids_list_item, viewGroup, false);
        maidsViewHolder viewHolder = new maidsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull maidsViewHolder maidsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class maidsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.maidPlaceholderImage) CircleImageView mMaidPlaceholderImage;
        @BindView(R.id.maidPhoneNumber) TextView mMaidPhoneNumber;
        @BindView(R.id.maidDescription) TextView mMaidDescription;
        @BindView(R.id.maidAddress) TextView mMaidAddress;
        @BindView(R.id.maidRating) TextView mMaidRating;
        @BindView(R.id.maidServices) TextView mMaidServices;
        @BindView(R.id.maidStatus) TextView mMaidStatus;


        private Context mContext;

        public maidsViewHolder(View maidItemView){
            super(maidItemView);
            ButterKnife.bind(this, maidItemView);
            mContext = maidItemView.getContext();

        }



    }






}
