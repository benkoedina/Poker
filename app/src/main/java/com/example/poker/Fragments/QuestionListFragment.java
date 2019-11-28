package com.example.poker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poker.Adapter.QuestionListAdapter;
import com.example.poker.Interface.onClickInterface;
import com.example.poker.Model.Group;
import com.example.poker.Model.Question;
import com.example.poker.Model.User;
import com.example.poker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionListFragment extends Fragment {

    // QuestionListFragment => kilistazza az osszes kerdest, aktivot es inaktivot is, es ha onnan rakatint egy kerdesre, akkor megtudja nezni az adott kerdesre adott osszes valaszt
    QuestionListAdapter adapter;
    RecyclerView recyclerView;
    public onClickInterface onclickInterface;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public QuestionListFragment(){};

    public void Oncreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        final View view = inflater.inflate(R.layout.fragment_question_list, container, false);
        Bundle bundle = this.getArguments();
        final  String groupid = bundle.getString("groupid"); //adott group_id lekerese

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Group g = dataSnapshot.child(groupid).getValue(Group.class);
                //adott group_id -hoz tartozo kerdesek lekerese
                final ArrayList<Question> q1 = g.getQuestions();



                onclickInterface = new onClickInterface() {
                    @Override
                    public void setClick(int pos) {

                        AnswerListFragment Afragment = new AnswerListFragment();

                        final Bundle bundle = new Bundle();

                        if (q1.get(pos).getUsers()!=null){

                            ArrayList <User> users = q1.get(pos).getUsers();
                            ArrayList <String> userAnswers = new ArrayList<>();
                            //lekerjuk az adott kerdesre a valaszokat es azt adjuk tovabb


                            for (User u : users)
                            {
                                String value = u.getName() +": " + u.getAnswer();
                                userAnswers.add(value);
                            }
                            bundle.putStringArrayList("answers", userAnswers);



                        }
                        else {
                            // ha nincs a kerdesre semmi valaszt akkor ures listat ad tovabb a masik fragmentnek
                            ArrayList<String> userAnswers = new ArrayList<>();
                            bundle.putStringArrayList("answers", userAnswers);

                        }
                        Afragment.setArguments(bundle);

//fragment valtas
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.container, Afragment);
                        fr.addToBackStack(null);
                        fr.commit();


                    }
                };

                //adapter megvalositas es recycler view
                recyclerView = (RecyclerView)view.findViewById(R.id.questionrecyclerView);
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
                adapter = new QuestionListAdapter(q1,getContext(), onclickInterface);

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
