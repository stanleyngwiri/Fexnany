package com.ngwiri.flexnany.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ngwiri.flexnany.R;
import com.ngwiri.flexnany.models.Maids;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class maidsListAdapter extends RecyclerView.Adapter<maidsListAdapter.maidsViewHolder> {


    public static final int MAX_WIDTH = 100;
    public static final int MAX_HEIGHT = 100;
    private ArrayList<Maids> mMaids = new ArrayList<>();
    private Context mContext;

    public maidsListAdapter(Context context, ArrayList<Maids> maids){

        mContext = context;
        mMaids = maids;
    }

    public class maidsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.maidPlaceholderImage) CircleImageView mMaidPlaceholderImage;
        @BindView(R.id.maidName) TextView mMaidName;
        @BindView(R.id.maidMsisdn) TextView mMaidMsisdn;
        @BindView(R.id.maidEmail) TextView mMaidEmail;
        @BindView(R.id.maidDescription) TextView mMaidDescription;
        @BindView(R.id.maidAddress) TextView mMaidAddress;
        @BindView(R.id.maidRating) TextView mMaidRating;
        @BindView(R.id.maidExperience) TextView mMaidExperience;
        @BindView(R.id.maidAge) TextView mMaidAge;
        @BindView(R.id.maidServices) TextView mMaidServices;
        @BindView(R.id.maidStatus) TextView mMaidStatus;

        private Context mContext;

        public maidsViewHolder(View maidItemView){
            super(maidItemView);
            ButterKnife.bind(this, maidItemView);
            mContext = maidItemView.getContext();

        }

        // bindMaids() set the contents of the layout's TextViews to the attributes of a specific maid
        public void bindMaids(Maids maids) {
            Picasso.get().load("").placeholder(R.drawable.female_avatar).resize(MAX_WIDTH, MAX_HEIGHT).into(mMaidPlaceholderImage);
            mMaidName.setText(maids.getmName());
            mMaidMsisdn.setText(maids.getmMsisdn());
            mMaidEmail.setText(maids.getmEmail());
            mMaidDescription.setText(maids.getmDescription());
            mMaidAddress.setText(maids.getmAddress());
            mMaidRating.setText(maids.getmRating() + "/5");
            mMaidExperience.setText(maids.getmExperience());
            mMaidAge.setText(maids.getmAge() + "Years");
            mMaidServices.setText(maids.getmServices());
            mMaidStatus.setText(maids.getmStatus());
        }
    }


    // method inflates the layout, and creates the ViewHolder object required from the adapter
    @Override
    public maidsListAdapter.maidsViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.maids_list_item, viewGroup, false);
        maidsViewHolder viewHolder = new maidsViewHolder(view);
        return viewHolder;
    }

    //updates the contents of the ItemView
    @Override
    public void onBindViewHolder( maidsListAdapter.maidsViewHolder holder, int i) {
        holder.bindMaids(mMaids.get(i));
    }

    //sets the number of items the adapter will display.
    @Override
    public int getItemCount() {
        return mMaids.size();
    }









}
