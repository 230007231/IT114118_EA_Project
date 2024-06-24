package com.example.elderbox.record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.elderbox.R;

public class ScoresAdapter extends ArrayAdapter<Scores> {

    private Context context;
    private int layoutResourceID;
    private Scores scoresData[] = null;


    public ScoresAdapter(Context context, int layoutResourceID, Scores[] scoresData) {
        super(context, layoutResourceID, scoresData);

        this.context = context;
        this.layoutResourceID = layoutResourceID;
        this.scoresData = scoresData;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        ScoresHolder scoresHolder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Step 1: initialize place holder
        if(row == null) {
            row = inflater.inflate(layoutResourceID,parent,false);

            scoresHolder = new ScoresHolder();
            scoresHolder.playername = (TextView) row.findViewById(R.id.playername);

            scoresHolder.score1 = (TextView) row.findViewById(R.id.score1);
            scoresHolder.score2 = (TextView) row.findViewById(R.id.score2);
            scoresHolder.score3 = (TextView) row.findViewById(R.id.score3);
            scoresHolder.score4 = (TextView) row.findViewById(R.id.score4);
            scoresHolder.scoreTotal = (TextView) row.findViewById(R.id.tScore);

            row.setTag(scoresHolder);
        } else {
            scoresHolder = (ScoresHolder) row.getTag();
        }

        // Step 2: Place Data in place holder
        Scores scores = scoresData[position];

        scoresHolder.playername.setText(" " + scores.playername);
        scoresHolder.score1.setText(" Score 1: " + scores.score1);
        scoresHolder.score2.setText(" Score 2: " + scores.score2);
        scoresHolder.score3.setText(" Score 3: " + scores.score3);
        scoresHolder.score4.setText(" Score 4: " + scores.score4);
        int scoreTotal = scores.score1 + scores.score2 + scores.score3 + scores.score4;
        scoresHolder.scoreTotal.setText(" Total: " + scoreTotal);

        return row;
    }

    private class ScoresHolder {
        TextView id;
        TextView playername;
        TextView playerpw;
        TextView score1;
        TextView score2;
        TextView score3;
        TextView score4;

        TextView scoreTotal;

    }

}