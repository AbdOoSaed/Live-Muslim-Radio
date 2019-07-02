package com.example.myapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapi.Model.RadiosItem;
import com.example.myapi.R;

import java.util.List;

public class AdapterRadio extends RecyclerView.Adapter<AdapterRadio.ViewHolderRadio> {
    private Context context;
    private List<RadiosItem> radiosItems;
    private int lastSelectedPosition = -1;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public AdapterRadio(List<RadiosItem> radiosItems, Context context) {
        this.context = context;
        this.radiosItems = radiosItems;
    }

    @NonNull
    @Override
    public AdapterRadio.ViewHolderRadio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new AdapterRadio.ViewHolderRadio(LayoutInflater.from(context)
                .inflate(R.layout.language_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRadio.ViewHolderRadio holder, final int position) {
        final RadiosItem radiosItem = radiosItems.get(position);
        holder.btn_language.setText(radiosItem.getName());
        holder.imgSound.setChecked(lastSelectedPosition == position);
        holder.imgSound.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (listener != null) {
                    if (lastSelectedPosition == position || radiosItem.isStetusCheck()) {
                        lastSelectedPosition = -1;
                        holder.imgSound.setChecked(false);
                    } else {
                        lastSelectedPosition = position;
                    }
                    notifyDataSetChanged();
                    listener.onItemClick(radiosItem, lastSelectedPosition);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return radiosItems == null ? 0 : radiosItems.size();
    }

    public void changeData(List<RadiosItem> radiosItems) {
        this.radiosItems = radiosItems;
        notifyDataSetChanged();
    }

    class ViewHolderRadio extends RecyclerView.ViewHolder {
        private Button btn_language;
        private RadioButton imgSound;

        ViewHolderRadio(@NonNull View itemView) {
            super(itemView);
            btn_language = itemView.findViewById(R.id.btn_language);
            imgSound = itemView.findViewById(R.id.testt);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Object item, int position);
    }
}
