package com.teste.tcc.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teste.tcc.R;

import java.util.List;

public class RecomendacaoAdapter extends RecyclerView.Adapter { //um adapter serve para listar todos os dados (e talvez identificar quais estão sendo clicados pelo usuário)

    List<Recomendacao> recomendacoes;
    private OnAppListener mOnAppListener;

    public RecomendacaoAdapter(List<Recomendacao> recomendacoes, OnAppListener onAppListener) {
        this.recomendacoes = recomendacoes;
        this.mOnAppListener = onAppListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recomendacao, parent, false);
        ViewHolderClass vhClass = new ViewHolderClass(view, mOnAppListener);
        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhClass = (ViewHolderClass) holder;
        Recomendacao recomendacao = recomendacoes.get(position);
        vhClass.tvNome.setText(recomendacao.getNome());
        vhClass.tvArea.setText(recomendacao.getAreaArte());
        vhClass.tvPlataformas.setText(recomendacao.getPlataformas());

        String websiteUri = recomendacao.getWebsite();

        String imageUri = null;
        imageUri = recomendacao.getImage();
        Picasso.get().load(imageUri).into(vhClass.ivImage);

        vhClass.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(websiteUri != null){
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(websiteUri));
                    activity.startActivity(intent);
                }
                //código abaixo era a antiga forma de abrir o fragment dos dados de cada recomendação
                /*AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_frombottom, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out_tobottom)
                        .replace(R.id.nav_host_fragment_content_main,new DadosFragment(recomendacao.getNome(), recomendacao.getAreaArte(), recomendacao.getImage()))
                        .addToBackStack(null).commit();*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return recomendacoes.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener { //classe responsável por tratar os itens que serão mostrados na tela, aonde serão mostrados
        TextView tvNome;
        TextView tvArea;
        TextView tvPlataformas;
        ImageView ivImage;
        OnAppListener onAppListener;

        public ViewHolderClass(@NonNull View itemView, OnAppListener onAppListener) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvArea = itemView.findViewById(R.id.tvArea);
            tvPlataformas = itemView.findViewById(R.id.tvPlataformas);
            ivImage = itemView.findViewById(R.id.ivImage);

            this.onAppListener = onAppListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAppListener.onAppCLick(getBindingAdapterPosition());
        }
    }

    public interface OnAppListener {
        void onAppCLick(int position);
    }
}
