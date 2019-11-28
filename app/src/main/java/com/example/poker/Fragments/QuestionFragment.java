package com.example.poker.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.poker.Adapter.VoteRecyclerViewAdapter;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionFragment extends Fragment {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    static int position = 0;
    VoteRecyclerViewAdapter adapter;

    public QuestionFragment()
    {
        //required empty constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //setting te layout
        View view = inflater.inflate(R.layout.fragment_question, container, false);


        // a card recycler view-ba az ertekek, ezek lehetnek a kerdesekre a valaszok
        final String[] data = {"0", "3", "6", "9", "12", "15", "18", "21", "24","27","30","33"};

        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        // set up the RecyclerView
        // ez a kartyak recyclerviewjanak a megvalositasa
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvNumbers);
        int numberOfColumns = 4;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        adapter = new VoteRecyclerViewAdapter(getContext(), data,0);

        recyclerView.setAdapter(adapter);

        //kiveszi a masik fragment altal kuldott adatokat, amiket felfog hasznalni
        Bundle bundle = this.getArguments();
        final String groupid = bundle.getString("groupid");
        final String name = bundle.getString("name");

        //Log.d("tags", groupid + " "+ name);
        TextView question_tv = view.findViewById(R.id.Question_textview);
        Button submit_button = view.findViewById(R.id.submit);

        //beallitja a kerdeseket amik ahhoz a group-hoz tartoznak
        setQuestion(submit_button,question_tv,groupid, name);

        return view;


    }

    public void setQuestion (final Button b ,final TextView t, final String groupid, final String name )
    {
        final Question question;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // lekerjuk az adott group_id-s group-ot
                Group g = dataSnapshot.child(groupid).getValue(Group.class);

                // lekerjuk a grouphoz tartozo osszes kerdest
                final Question question = dataSnapshot.child(groupid).child("questions").child(String.valueOf(position)).getValue(Question.class);

                //kivesszuk listaba a kerdes objektumokat a group-bol
                final ArrayList<Question> q1 = g.getQuestions();
                final ArrayList<User> users;

                //a position valtozo minden egyes kiposztolt kerdes utan no, addig no amig nincs tobb uj kerdes amit meg nem tett ki
                //ha kisebb meg mint a q1 tomb, ami az osszes kerdest tartalmazza akkor tovabb lep
                if (position<q1.size()) {

                    //ha van a kerdesre mar valasz, akkor azt kiveszi egy tombbe
                    //ha nincs, ures tombot hoz letre
                    if (q1.get(position).getUsers() == null) {
                        users = new ArrayList<>();
                    } else {
                        users = question.getUsers();
                    }
                }
                else
                {
                    users = new ArrayList<>();
                }

                final ArrayList<String> questionString = new ArrayList<String>();
                for(Question q : q1)
                {

                    questionString.add(q.getQuestion());
                    //kivesszuk egy kulon tombbe csak a kerdeseket

                }

                //ha aktiv a kerdes es meg mindig van kovetkezo kerdes akkor beallitjuk a position-adik kerdest
                if ((position<q1.size() ) && (question.isStatus().equals("active"))) {
                    // Log.d("status", q1.get(position).isStatus());
                    t.setText(question.getQuestion());
                    // Log.d("question", q1.get(position).getQuestion());

                }
                else
                {
                    //ha nem aktiv, akkor noveljuk a position-t s tovabb lep
                    position++;
                    setQuestion(b, t,groupid, name);
                }
                // ha mar nincs tobb kerdes, akkor ezt allitjuk be
                if (position>=q1.size())
                {
                    t.setText("All the questions were answered!");
                    b.setText("Submit all Answers");

                }

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v ) {


                        //amikor mar nincs tobb kerdes
                        if (t.getText().toString().equals("All the questions were answered!")) {
                            QuestionListFragment Qfragment = new QuestionListFragment();

                            // tovabb adjuk a kovetkezo fragmentnek a groupid-t, es a kulon tombbe kikert kerdeseket
                            final Bundle bundle = new Bundle();
                            bundle.putStringArrayList("questions", questionString);
                            bundle.putString("groupid",groupid);
                            Qfragment.setArguments(bundle);

                            FragmentTransaction fr = getFragmentManager().beginTransaction();
                            fr.replace(R.id.container, Qfragment);
                            fr.addToBackStack(null);
                            fr.commit();
                        }
                        else
                        {
                            //ha van meg kerdes

                            //letrehozzuk a kulcsot
                            String key = databaseReference.child(groupid).child("questions").child(String.valueOf(position)).child("users").push().getKey();

                            //user objektum
                            //adapterbol kinhyert valasz, amire klikkelt
                            User user = new User(key, name,  adapter.getItem(adapter.getPos()));

                            //a mar meglevo user tombhoz, amit az elejen kertunk le, a mar eddig meglevo valaszokkal, ahhoz hozza adjuk
                            users.add(user);
                            //a fent lekert question tombhoz hozza adjuk a user tombot, ami all az uj+a regi valaszokbol
                            question.setUsers(users);

                            //felulirjuk az eddigi group_id adott pozicion levo kerdest
                            databaseReference.child(groupid).child("questions").child(String.valueOf(position)).setValue(question);
                            Log.d ("questionpos", String.valueOf(position));
                            Log.d("position", position + "");
                            Log.d("pos", adapter.getItem(adapter.getPos()));
                            //tovabb leptetjuk
                            position++;
                            setQuestion(b, t,groupid, name);
                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}