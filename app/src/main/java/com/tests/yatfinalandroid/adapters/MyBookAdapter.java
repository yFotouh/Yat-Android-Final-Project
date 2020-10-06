package com.tests.yatfinalandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.tests.yatfinalandroid.R;
import com.tests.yatfinalandroid.dto.webdto.Item;
import com.tests.yatfinalandroid.sql.AppDatabase;
import com.tests.yatfinalandroid.sql.Book;

import java.util.List;

public class MyBookAdapter extends
        RecyclerView.Adapter<MyBookAdapter.ViewHolder> {

    // ... view holder defined above...

    // Store a member variable for the contacts
    private List<Book> books;
    Context context;

    // Pass in the contact array into the constructor
    public MyBookAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public MyBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.view_holder_add_book, parent, false);

        // Return a new holder instance
        MyBookAdapter.ViewHolder viewHolder = new MyBookAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book bookItem = books.get(position);
        holder.fab_add_book.setVisibility(View.GONE);
        holder.tv_book_title.setText(bookItem.bookTitle);
//        Glide.with(context).load(book.imageUrl).into(holder.iv_book_image);
        Picasso.get().load(bookItem.imageUrl).into(holder.iv_book_image);
    }


    @Override
    public int getItemCount() {
        return books.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tv_book_title;
        public FloatingActionButton fab_add_book;
        public ImageView iv_book_image;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            fab_add_book = (FloatingActionButton) itemView.findViewById(R.id.fab_add_book);
            iv_book_image = (ImageView) itemView.findViewById(R.id.iv_book_image);
            tv_book_title = (TextView) itemView.findViewById(R.id.tv_book_title);
        }
    }
}