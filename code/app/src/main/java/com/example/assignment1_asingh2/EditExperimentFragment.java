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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditExperimentFragment extends DialogFragment {

    private EditText experimentName;
    private EditText experimentDate;
    private EditText experimentDescription;
    private OnFragmentInteractionListener listener;
    int success;
    int failure;
    String success_rate;

    public interface OnFragmentInteractionListener{
        void onEditOkPressed(Experiment newExperiment);
    }

    static EditExperimentFragment newInstance(Experiment experiment){
        Bundle args = new Bundle();
        args.putSerializable("experiment", experiment);

        EditExperimentFragment fragment = new EditExperimentFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditExperimentFragment.OnFragmentInteractionListener){
            listener = (EditExperimentFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_experiment_fragment, null);
        experimentName = view.findViewById(R.id.experiment_name_editText);
        String experimentNamestring = ((MainActivity) getActivity()).experiment;
        Experiment experiment = (Experiment) getArguments().getSerializable("experiment");
        failure = experiment.failure;
        success = experiment.success;
        success_rate = experiment.getSuccess_rate(success,failure);

        TextView successInt = view.findViewById(R.id.success_int);
        successInt.setText(experiment.getSuccess());
        TextView failureInt = view.findViewById(R.id.failure_int);
        failureInt.setText(experiment.getFailure());
        TextView successRate = view.findViewById(R.id.success_rate_inFrag);

        experimentDescription = view.findViewById(R.id.experiment_description_edit);
        experimentDescription.setText(experiment.getDescription());
        experimentDate = view.findViewById(R.id.experiment_date_edit);
        experimentDate.setText(experiment.getDate());

        if (success_rate != null ){
            System.out.println(success_rate);
            successRate.setText(success_rate);

        }
        else {
            successRate.setText("0");
        }


        experimentName.setText(experimentNamestring);

        //TextView successInt = view.findViewById(R.id.success_int);
        //TextView failureInt = view.findViewById(R.id.failure_int);

        Button successButton = view.findViewById(R.id.add_success_button);
        successButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                experiment.success++;
                success = experiment.success;
                successInt.setText(String.valueOf(experiment.success));
                Log.e("ye","no");
            }
        });


        Button failureButton = view.findViewById(R.id.add_failure_button);
        failureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                experiment.failure++;
                failure = experiment.failure;
                failureInt.setText(String.valueOf(experiment.failure));
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit experiment")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String experiment = experimentName.getText().toString();
                        String description = experimentDescription.getText().toString();
                        String date = experimentDate.getText().toString();
                        listener.onEditOkPressed(new Experiment(experiment, date, description, success, failure, success+failure));
                    }}).create();
    }

}
