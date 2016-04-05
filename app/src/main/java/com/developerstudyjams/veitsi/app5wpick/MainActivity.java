package com.developerstudyjams.veitsi.app5wpick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ViewFlipper;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    TextView quantityTextView;
    int stage = 0;
    float score = 0.0f;
    float add=0;
    int divider = 1;
    final String[] stages = new String[]{"Intro", "1.What (to dress)?", "2.Where (to go)?",
            "3.Who (is target)?", "4.Why (he answers)?", "5.When (to meet next time)?"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        quantityTextView = (TextView) findViewById(
                R.id.txtStatus);
        ListView lstEvents = (ListView) findViewById(R.id.lstEvents);
        ListView lstGreetings = (ListView) findViewById(R.id.lstGreeting);

        final String[] events = new String[]{
                "Java, how to improve performance", "Swinger party", "QA for beginners", "R meetup",
                "Kyiv Algorithms Club #27 Backtracking","Speakers’ Corner. Multi threaded JS applications in the browser",
                "Встреча сообщества Smart Talks 51: .NET, C#","Конференція ScalaUA","О чем говорят айтишники: Woman’s Day"
        };
        final int[] adds={20, 1, 20, 15, 20, 15, 20, 10};

        // определяем массив типа String
        final String[] greetings = new String[]{
                "Hello, I hate Apple too",
                "My name is Evgrafia Petrovna Zapolatskaya, I want to get merried. You looks not very good, but I don't care about this right now",
                "Hello, do you prefer vim or ed?", "Hi, I like your speech", "Myname is Tanya ", "Heyyyy!"
        };


        //                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
        //                Toast.LENGTH_SHORT).show();


        ArrayAdapter<String> eventAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, events);
        lstEvents.setAdapter(eventAdapter);
        lstEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                add=20.0f;
                add/=divider;
                score+=add;
                divider*=2;
                display("for event " + position);
            }
        });

        ArrayAdapter<String> greetAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, greetings);
        lstGreetings.setAdapter(greetAdapter);
        lstGreetings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                display("for greet " + position);
            }
        });
    }

    public void nextClick(View view) {
        nextStage();
    }

    public void scoring(float score){

    }

    public void nextStage() {
        display("coming new stage");
        if (stage < 5) {
            stage += 1;
            this.setTitle(stages[stage]);
            viewFlipper.showNext();
            divider = 1;
            switch (stage){
                case 2:
                    break;
                case 4:
                    break;
            }
        }
    }

    public void display(String m) {
        m = m + " score: " + score;
        quantityTextView.setText(m);
        Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();
    }

    public void guyClick(View view) {
        //view.setVisibility(View.INVISIBLE);
        view.setClickable(false);
        float add = 0f;
        String m = "";
        switch (view.getId()) {
            case R.id.imgGuy01:
                m = ("Good choice. Go ahead!");
                add = 20.0f;
                break;
            case R.id.imgGuy03:
                m = ("probably he is not a human");
                add = 0.5f;
                break;
            case R.id.imgGuy02:
                m = ("He probably is a deputy");
                add = 1.0f;
                break;
            case R.id.imgGuy04:
                m = ("You are great!");
                add = 20.0f;
                break;
        }
        add = add / divider;
        score += add;
        divider *= 2;

        display(m);
    }
}

