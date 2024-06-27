package com.example.elderbox.ranking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.elderbox.R;

public class UsersAdapter extends ArrayAdapter<Users> {

    private Context context;
    private int layoutResourceID;
    private Users usersData[] = null;

    public UsersAdapter(Context context, int layoutResourceID, Users[] usersData) {
        super(context, layoutResourceID, usersData);

        this.context = context;
        this.layoutResourceID = layoutResourceID;
        this.usersData = usersData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UsersHolder usersHolder;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceID, parent, false);

            usersHolder = new UsersHolder();
            usersHolder.name = row.findViewById(R.id.playername);
            usersHolder.score1 = row.findViewById(R.id.score1);
            usersHolder.score2 = row.findViewById(R.id.score2);
            usersHolder.score3 = row.findViewById(R.id.score3);
            usersHolder.score4 = row.findViewById(R.id.score4);
            usersHolder.scoreTotal = row.findViewById(R.id.tScore);

            row.setTag(usersHolder);
        } else {
            usersHolder = (UsersHolder) row.getTag();
        }

        Users users = usersData[position];

        usersHolder.name.setText("   Name: " + users.name);
        usersHolder.score1.setText("   Score 1: " + users.score1);
        usersHolder.score2.setText("   Score 2: " + users.score2);
        usersHolder.score3.setText("   Score 3: " + users.score3);
        usersHolder.score4.setText("   Score 4: " + users.score4);
        int scoreTotal = users.score1 + users.score2 + users.score3 + users.score4;
        usersHolder.scoreTotal.setText("   Total: " + scoreTotal);

        return row;
    }

    private static class UsersHolder {
        TextView name;
        TextView score1;
        TextView score2;
        TextView score3;
        TextView score4;
        TextView scoreTotal;
    }
}