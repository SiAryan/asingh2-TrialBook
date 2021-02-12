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

import java.io.Serializable;
import java.util.Date;

import static java.lang.Math.round;

public class Experiment implements Serializable {
    String name;
    String date;
    String description;
    int success = 0;
    int failure = 0;
    int trials = success + failure;
    double success_rate;


    public Experiment(String name){
        this.name  = name;

    }

    public Experiment(String name, String date, String description){
        this.name = name;
        this.date = date;
        this.description = description;
        //this.success = 0;
        //this.failure = 0;
    }

    public Experiment(String name, String date, String description, int success, int failure, int trials){
        this.name = name;
        this.date = date;
        this.description = description;
        this.success = success;
        this.failure = failure;
        this.trials = trials;
    }


    String getSuccess(){ return String.valueOf(this.success);}

    //must get a number input or this will fail
    public void setSuccess(String success){
        int foo = Integer.parseInt(success);
        this.success = foo;
        return;
    }

    String getFailure(){return  String.valueOf(this.failure);}

    public void setFailure(String failure){
        int foo = Integer.parseInt(failure);
        this.failure = foo;
        return;
    }

    public String getExperimentName(){ return this.name;}

    public String getDate(){return this.date;}

    public void setDate(String date){this.date = date;}

    public String getDescription(){return this.description;}

    public void setDescription(String description){
        this.description = description;
    }

    public int getTrials(){return this.trials;}

    public String getSuccess_rate(int success, int failure) {
        if (success+failure==0) {
            return String.valueOf(0);
        }

        else {
            return String.format("%.3f",(double) success/(success + failure));

        }

    }
}
