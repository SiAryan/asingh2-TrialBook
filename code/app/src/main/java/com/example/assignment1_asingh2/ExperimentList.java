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

    9. https://developer.android.com/reference/java/io/Serializable

*/



package com.example.assignment1_asingh2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ExperimentList extends ArrayAdapter<Experiment> {
    private ArrayList<Experiment> experiments;
    private Context context;

    //
    public ExperimentList(Context context, ArrayList<Experiment> experiments) {
        super(context,0,experiments);
        this.experiments = experiments;
        this.context = context;

    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        }

        Experiment experiment = experiments.get(position);

        TextView experimentName = view.findViewById(R.id.experiment_text);

        TextView experimentDate = view.findViewById(R.id.experiment_date);

        TextView experimentDescription = view.findViewById(R.id.experiment_description);

        TextView experimentTrials = view.findViewById((R.id.num_trials));

        TextView experimentSuccessRate = view.findViewById(R.id.success_rate);

        experimentName.setText(experiment.getExperimentName());

        experimentDate.setText((experiment.getDate()));

        experimentDescription.setText(experiment.getDescription());

        experimentTrials.setText(String.valueOf(experiment.getTrials()));

        experimentSuccessRate.setText(experiment.getSuccess_rate(Integer.parseInt(experiment.getSuccess()), Integer.parseInt(experiment.getFailure())));



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).position = position;
                ((MainActivity) context).experiment = experiments.get(position).getExperimentName();


                EditExperimentFragment.newInstance(experiment).show( ((MainActivity) context).getSupportFragmentManager(), "EDIT EXPERIMENT");

            }
        });

        Button removeButton = view.findViewById(R.id.remove_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).position = position;
                experiments.remove(position);
                notifyDataSetChanged();
            }
        });


        return view;
    }
}
