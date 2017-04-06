package com.example.rahulkapoor.studentdata.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rahulkapoor.studentdata.R;
import com.example.rahulkapoor.studentdata.StudentData;
import com.example.rahulkapoor.studentdata.model.StudentInfo;

import java.util.ArrayList;

/**
 * recycleitems class extending adapter;
 */
public class RecycleItems extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<StudentInfo> mstudentInfoArrayList;
    private  Context mContext;
    private  Intent intent;
    private TextView nameTextView;
    private TextView rollnoTextView;


    /**
     *
     * @param context context is set;
     * @param al array list is set;
     */
    public RecycleItems(final Context context, final ArrayList<StudentInfo> al) {
        this.mstudentInfoArrayList = al;
        this.mContext = context;

    }

    /**
     *
     * @param parent parent viewgroup
     * @param viewType view type;
     * @return returns view holder;
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View customView = inflater.inflate(R.layout.activity_recycle_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(customView);
        return viewHolder;
    }

    /**
     *
     * @param holder view holder of recycler view;
     * @param position position in array list of objects;
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        StudentInfo objinfo = mstudentInfoArrayList.get(position);

        TextView textViewRollno = rollnoTextView;
        textViewRollno.setText(objinfo.getRollno());
        TextView textViewName = nameTextView;
        textViewName.setText(objinfo.getName());


    }

    /**
     *
     * @return context returned;
     */
    private Context getContext() {
        return mContext;
    }

    /**
     *
     * @return size of array list returned;
     */
    @Override
    public int getItemCount() {
        return mstudentInfoArrayList.size();
    }

    /**
     * static class of view holder made and alert dialog boxto edit, view or delete data called here;
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         *
         * @param itemView refernce of the item view at the current pos is returned;
         */
        public ViewHolder(final View itemView) {

            super(itemView);

            final int pos = getAdapterPosition();


            rollnoTextView = (TextView) itemView.findViewById(R.id.tvrollno);
            nameTextView = (TextView) itemView.findViewById(R.id.tvname);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View v) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                    alertDialogBuilder.setMessage("Choose an option to continue: ");
                    alertDialogBuilder.setPositiveButton("View",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(final DialogInterface arg0, final int arg1) {
                                    intent = new Intent(mContext, StudentData.class);
                                    StudentInfo obj = mstudentInfoArrayList.get(pos);
                                    intent.putExtra("keyobject", obj);
                                    intent.putExtra("key", "view");
                                    mContext.startActivity(intent);
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Edit",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(final DialogInterface arg0, final int arg1) {
                                    intent = new Intent(mContext, StudentData.class);
                                    StudentInfo obj = mstudentInfoArrayList.get(pos);
                                    intent.putExtra("key", "edit");
                                    intent.putExtra("keyobject", obj);
                                    mContext.startActivity(intent);

                                }
                            });

                    alertDialogBuilder.setNeutralButton("Delete",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(final DialogInterface arg0, final int arg1) {
                                    mstudentInfoArrayList.remove(pos);


                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                }
            });


        }


    }

}
