package com.example.elderbox.ranking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.elderbox.R;

public class UsersAdapter extends ArrayAdapter<Users> {

    // Context for the adapter
    private Context context;
    // Layout resource ID for the row
    private int layoutResourceID;
    // Array of Users data
    private Users usersData[] = null;

    public UsersAdapter(Context context, int layoutResourceID, Users[] usersData) {
        super(context, layoutResourceID, usersData);

        // Initialize context, layoutResourceID, and usersData
        this.context = context;
        this.layoutResourceID = layoutResourceID;
        this.usersData = usersData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UsersHolder usersHolder;

        if (row == null) {
            // Inflate the row layout if it's null
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceID, parent, false);

            // Create a new UsersHolder and find views
            usersHolder = new UsersHolder();
            usersHolder.name = row.findViewById(R.id.playername);
            usersHolder.score1 = row.findViewById(R.id.score1);
            usersHolder.score2 = row.findViewById(R.id.score2);
            usersHolder.score3 = row.findViewById(R.id.score3);
            usersHolder.score4 = row.findViewById(R.id.score4);
            usersHolder.scoreTotal = row.findViewById(R.id.tScore);

            // Set the tag for the row
            row.setTag(usersHolder);
        } else {
            // Reuse existing UsersHolder
            usersHolder = (UsersHolder) row.getTag();
        }

        // Get the Users object at the current position
        Users users = usersData[position];

        // Set text for each TextView
        usersHolder.name.setText("   Name: " + users.name);
        usersHolder.score1.setText("   Score 1: " + users.score1);
        usersHolder.score2.setText("   Score 2: " + users.score2);
        usersHolder.score3.setText("   Score 3: " + users.score3);
        usersHolder.score4.setText("   Score 4: " + users.score4);
        int scoreTotal = users.score1 + users.score2 + users.score3 + users.score4;
        usersHolder.scoreTotal.setText("   Total: " + scoreTotal);

        return row;
    }

    // Static inner class to hold views
    private static class UsersHolder {
        TextView name;
        TextView score1;
        TextView score2;
        TextView score3;
        TextView score4;
        TextView scoreTotal;
    }
}
