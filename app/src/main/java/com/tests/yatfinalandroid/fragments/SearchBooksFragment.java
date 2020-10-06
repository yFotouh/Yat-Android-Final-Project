package com.tests.yatfinalandroid.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.tests.yatfinalandroid.R;
import com.tests.yatfinalandroid.activities.MainActivity;
import com.tests.yatfinalandroid.adapters.AddBooksAdapter;
import com.tests.yatfinalandroid.dto.webdto.BookResponse;
import com.tests.yatfinalandroid.network.BookService;
import com.tests.yatfinalandroid.sql.AppDatabase;
import com.tests.yatfinalandroid.sql.Book;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchBooksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchBooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchBooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchBooksFragment newInstance(String param1, String param2) {
        SearchBooksFragment fragment = new SearchBooksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    EditText ed_search;
    ProgressBar progressBar;
    Set<String> bookIdKeys = new HashSet<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_books, container, false);
        ((MainActivity) getActivity()).toolbar.setTitle("Search books");
        RecyclerView rvContacts = (RecyclerView) v.findViewById(R.id.rv_search_books);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        ImageView iv_search = (ImageView) v.findViewById(R.id.iv_search);
        ed_search = (EditText) v.findViewById(R.id.ed_search);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        List<Book> books = db.bookDao().getAll();
        for (int i = 0; i < books.size(); i++) {
            bookIdKeys.add(books.get(i).serverId);
        }
        BookService service = retrofit.create(BookService.class);
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callWebApi(service, rvContacts);
            }
        });


        return v;
    }

    private void callWebApi(BookService service, RecyclerView rvContacts) {
        progressBar.setVisibility(View.VISIBLE);
        String query = ed_search.getText().toString();
        service.getBookByName(query).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                Log.d("", "");
                AddBooksAdapter adapter = new AddBooksAdapter(response.body().getItems(), bookIdKeys, getActivity());
                rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvContacts.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Log.d("", "");
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}