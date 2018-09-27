package com.allison.fresco.utils;

import android.content.ClipData;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.RecyclerView;

import com.allison.fresco.interfaces.ItemTouchHelperAdapter;

/**
 * Thanks & credit to https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf#.ndllchrdz
 */

public class RecipeTouchHelper extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter mAdapter;

    public RecipeTouchHelper(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (direction == ItemTouchHelper.START) {
            mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
        } else {
            mAdapter.onItemSwipeEnd(viewHolder.getAdapterPosition());
        }
    }


}
