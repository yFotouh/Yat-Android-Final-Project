package com.tests.yatfinalandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Set;

public class AddBooksAdapter extends
        RecyclerView.Adapter<AddBooksAdapter.ViewHolder> {

    // ... view holder defined above...

    // Store a member variable for the contacts
    private List<Item> books;
    Context context;
    Set<String> localBookIds;

    // Pass in the contact array into the constructor
    public AddBooksAdapter(List<Item> books, Set<String> localBookIds, Context context) {
        this.books = books;
        this.context = context;
        this.localBookIds = localBookIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.view_holder_add_book, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Item bookItem = books.get(position);
        String serverId = bookItem.getId();
        if (localBookIds.contains(serverId)) {
            holder.fab_add_book.setVisibility(View.GONE);
        }
        holder.tv_book_title.setText(bookItem.getVolumeInfo().getTitle());
//        Glide.with(context).load(book.imageUrl).into(holder.iv_book_image);
        Picasso.get().load(bookItem.getVolumeInfo().getImageLinks().getThumbnail()).into(holder.iv_book_image);
        holder.fab_add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "database-name").allowMainThreadQueries().build();
                Book book = new Book();
                book.bookTitle = bookItem.getVolumeInfo().getTitle();
                book.imageUrl = bookItem.getVolumeInfo().getImageLinks().getThumbnail();
                book.serverId = bookItem.getId();
                db.bookDao().insertAll(book);
                Toast.makeText(context, "Book : " + book.bookTitle + " was saved", Toast.LENGTH_LONG).show();
            }
        });
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
