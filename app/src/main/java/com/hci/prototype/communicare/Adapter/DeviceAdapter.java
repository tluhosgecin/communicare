package com.hci.prototype.communicare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hci.prototype.communicare.Database.Structure.DeviceStructure;
import com.hci.prototype.communicare.R;

import java.util.Objects;

public class DeviceAdapter extends ArrayAdapter<DeviceStructure>
{
    public DeviceAdapter(Context context, DeviceStructure[] objects)
    {
        super(context, R.layout.listitem_bed, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        final DeviceStructure lDevice = getItem(position);

        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.listitem_bed, parent, false);
        }

        TextView lTextBed    = view.findViewById(R.id.textview_item_bed_bed);
        TextView lTextPhone  = view.findViewById(R.id.textview_item_bed_phone);
        Button   lTextRemove = view.findViewById(R.id.button_item_bed_remove);

        lTextBed.setText(Objects.requireNonNull(lDevice).getName());
        lTextPhone.setText(Objects.requireNonNull(lDevice).getNumber());

        lTextRemove.setOnClickListener(Objects.requireNonNull(lDevice).getOnClickListener(DeviceStructure.BUTTON_REMOVE));

        return view;
    }
}