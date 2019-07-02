package com.example.myapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapi.Model.LanguageItem;
import com.example.myapi.Model.RadiosItem;
import com.example.myapi.R;

import java.util.List;

public class AdapterLanguage extends RecyclerView.Adapter<AdapterLanguage.ViewHolderLanguage> {
    private List<LanguageItem> languageItemsAdapter;
    private Context context;
    private AdapterRadio.OnItemClickListener listenerLanguage;


    public void setListenerLanguage(AdapterRadio.OnItemClickListener listenerLanguage) {
        this.listenerLanguage = listenerLanguage;
    }

    public AdapterLanguage(List<LanguageItem> languageItemsAdapter, Context context) {
        this.languageItemsAdapter = languageItemsAdapter;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderLanguage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolderLanguage(LayoutInflater.from(context)
                .inflate(R.layout.language_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLanguage holder, final int position) {
        final LanguageItem languageItem = languageItemsAdapter.get(position);
        String strBtn_language = languageItem.getId() + ": " + languageItem.getLanguage();
        holder.btn_language.setText(strBtn_language);
        holder.btn_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenerLanguage != null) {
                    listenerLanguage.onItemClick(languageItem, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return languageItemsAdapter == null ? 0 : languageItemsAdapter.size();
    }

    public void changeData(List<LanguageItem> languageItems) {
        languageItemsAdapter = languageItems;
        notifyDataSetChanged();
    }

    class ViewHolderLanguage extends RecyclerView.ViewHolder {
        private Button btn_language;
        private RadioButton imgSound;

         ViewHolderLanguage(@NonNull View itemView) {
            super(itemView);
            btn_language = itemView.findViewById(R.id.btn_language);
            imgSound = itemView.findViewById(R.id.testt);

            imgSound.setVisibility(View.GONE);
        }
    }
}
