package com.tests.yatfinalandroid.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tests.yatfinalandroid.R;
import com.tests.yatfinalandroid.adapters.AddBooksAdapter;
import com.tests.yatfinalandroid.adapters.MyBookAdapter;
import com.tests.yatfinalandroid.sql.AppDatabase;
import com.tests.yatfinalandroid.sql.Book;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyBooksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyBooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyBooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyBooksFragment newInstance(String param1, String param2) {
        MyBooksFragment fragment = new MyBooksFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_books, container, false);
        RecyclerView rv_mybooks = (RecyclerView) v.findViewById(R.id.rv_mybooks);
        AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        List<Book> books = db.bookDao().getAll();
        MyBookAdapter adapter = new MyBookAdapter(books, getActivity());
        rv_mybooks.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_mybooks.setAdapter(adapter);

        FloatingActionButton floatingActionButton = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                SearchBooksFragment searchBooksFragment = new SearchBooksFragment();
                ft.replace(R.id.frame_layout, searchBooksFragment);
                ft.addToBackStack("stack");
                ft.commit();
            }
        });

        return v;
    }
}