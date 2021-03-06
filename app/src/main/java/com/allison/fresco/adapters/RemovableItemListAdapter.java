package com.allison.fresco.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allison.fresco.models.Meal;
import com.allison.fresco.R;
import com.allison.fresco.models.ShoppingListItem;
import com.allison.fresco.fragments.AddRecipeFragment;
import com.allison.fresco.fragments.ShoppingListFragment;
import com.allison.fresco.interfaces.ItemTouchHelperAdapter;

import java.util.List;

public class RemovableItemListAdapter<T> extends RecyclerView.Adapter<RemovableItemListAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<Object> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView mTextView;

        private ViewHolder(View v) {
            super(v);
            mTextView = itemView.findViewById (R.id.recipe_item);
        }
    }

    public RemovableItemListAdapter(List<Object> items) {
        mDataset = items;
    }

    public void updateList(List<Object> newList) {
        mDataset.clear();
        mDataset.addAll(newList);
        this.notifyDataSetChanged();
    }

    @Override
    public RemovableItemListAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_item, parent, false);

        return new RemovableItemListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RemovableItemListAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void onItemDismiss(int position) {
        // Load and delete item from DB
        Object item = mDataset.get(position);
        if (item instanceof Meal) {
            Meal m = (Meal) mDataset.get(position);
            Meal.load(Meal.class, m.getId()).delete();

            // Delete from list
            mDataset.remove(position);
            notifyItemRemoved(position);

            // Update list size
            AddRecipeFragment.updateListSize();
        } else if (item instanceof ShoppingListItem) {
            ShoppingListItem s = (ShoppingListItem) mDataset.get(position);
            ShoppingListItem.load(ShoppingListItem.class, s.getId()).delete();

            // Delete from list
            mDataset.remove(position);
            notifyItemRemoved(position);

            // Update list size
            ShoppingListFragment.updateListSize();
        }
    }

    public void onItemSwipeEnd(int position) {}
    public boolean onItemMove(int i, int j) {
        return true;
    }
}
