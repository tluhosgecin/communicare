package com.hci.prototype.communicare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hci.prototype.communicare.R;
import com.hci.prototype.communicare.Receiver.Structure.AlertStructure;

import java.util.Objects;

public class AlertAdapter extends ArrayAdapter<AlertStructure>
{
    public AlertAdapter(Context context, AlertStructure[] objects)
    {
        super(context, R.layout.listitem_alert, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        final AlertStructure lAlert = getItem(position);

        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.listitem_alert, parent, false);
        }

        TextView lTextBed       = view.findViewById(R.id.textview_item_alert_bed);
        TextView lTextTime      = view.findViewById(R.id.textview_item_alert_time);
        Button   lButtonAssist  = view.findViewById(R.id.button_item_alert_assist);
        Button   lButtonResolve = view.findViewById(R.id.button_item_alert_resolve);

        lTextBed.setText(Objects.requireNonNull(lAlert).getName());
        lTextTime.setText(Objects.requireNonNull(lAlert).getTime());

        lButtonAssist.setOnClickListener(Objects.requireNonNull(lAlert).getOnClickListener(AlertStructure.BUTTON_ASSIST));
        lButtonResolve.setOnClickListener(Objects.requireNonNull(lAlert).getOnClickListener(AlertStructure.BUTTON_RESOLVE));

        return view;
    }
}