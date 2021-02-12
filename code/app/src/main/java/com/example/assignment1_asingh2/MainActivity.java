/*
    Aryan Singh
    1533732

    References:
    1. CMPUT 301 lab 1 - lab 3

    2. https://icarus.cs.weber.edu/~dab/cs1410/textbook/9.Classes_And_Objects/static.html

    3. https://stackoverflow.com/questions/27584465/delete-row-from-listview-on-dialogue-boxs-button-click
        user: xyz  on StackOverflow: https://stackoverflow.com/users/3960528/xyz

    4. https://stackoverflow.com/questions/23103356/remove-item-from-custom-listview-on-button-click
        users: Raghunandhan & alexbt on StackOverflow https://stackoverflow.com/users/653856/raghunandan, https://stackoverflow.com/users/641627/alexbt

    5. https://stackoverflow.com/questions/49595617/android-attempt-to-invoke-virtual-method-void-android-widget-textview-settext
        user sachinmaharjan of StackOverflow https://stackoverflow.com/users/5790919/sachinmaharjan

    6. https://stackoverflow.com/questions/9366280/android-round-to-2-decimal-places
        user OleGG on StackOverflow https://stackoverflow.com/users/900214/olegg

    7. https://www.youtube.com/watch?v=bS7jBuqmq4A&t=244s

    8. https://www.youtube.com/watch?v=PCFcGpE2Fm8

    9. https://developer.android.com/reference/java/io/Serializables


*/






package com.example.assignment1_asingh2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AddExperimentFragment.OnFragmentInteractionListener, EditExperimentFragment.OnFragmentInteractionListener{

    ListView experimentList;
    ArrayAdapter<Experiment> experimentAdapter;
    ArrayList<Experiment> experimentDataList;

    String experiment;
    int success;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        experimentList = findViewById(R.id.Binomial_trials_tracker);

        experimentDataList = new ArrayList<>();

        experimentAdapter = new ExperimentList(this, experimentDataList);

        experimentList.setAdapter(experimentAdapter);




        FloatingActionButton addExperimentButton = findViewById(R.id.add_city_button);
        addExperimentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddExperimentFragment().show(getSupportFragmentManager(), "ADD_EXPERIMENT");
            }
        });


    }


    @Override
    public void onOkPressed(Experiment newExperiment) {
        experimentAdapter.add(newExperiment);
        experimentAdapter.notifyDataSetChanged();

    }


    @Override
    public void onEditOkPressed(Experiment newExperiment) {
        experimentDataList.set(position, newExperiment);
        experimentAdapter.notifyDataSetChanged();
    }





}