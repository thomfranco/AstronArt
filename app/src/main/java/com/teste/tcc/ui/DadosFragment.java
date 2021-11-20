package com.teste.tcc.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teste.tcc.R;
import com.teste.tcc.databinding.FragmentDadosBinding;
import com.teste.tcc.ui.home.HomeFragment;

public class DadosFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FragmentDadosBinding binding;
    public String nome, areaArte, plataformas, website, desc, image, appScreenshot, appScreenshot2;

    public DadosFragment() {

    }

    public DadosFragment(String nome, String areaArte, String plataformas, String website, String desc, String image, String appScreenshot, String appScreenshot2) {
        this.nome = nome;
        this.areaArte = areaArte;
        this.plataformas = plataformas;
        this.website = website;
        this.desc = desc;
        this.image = image;
        this.appScreenshot = appScreenshot;
        this.appScreenshot2 = appScreenshot2;
    }

    public static DadosFragment newInstance(String param1, String param2) {
        DadosFragment fragment = new DadosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDadosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //View view=inflater.inflate(R.layout.fragment_dados, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbarDados);
        toolbar.setTitle(nome);
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);

        TextView nomeHolder = root.findViewById(R.id.nomeHolder);
        TextView areaArteHolder = root.findViewById(R.id.areaArteHolder);
        TextView plataformasHolder = root.findViewById(R.id.plataformasHolder);
        TextView descHolder = root.findViewById(R.id.descHolder);
        ImageView imageHolder = root.findViewById(R.id.imageHolder);
        ImageView appScreenshotHolder = root.findViewById(R.id.appScreenshotHolder);
        ImageView appScreenshotHolder2 = root.findViewById(R.id.appScreenshotHolder2);

        Button button = root.findViewById(R.id.button);
        TextView screenshotsDesc = root.findViewById(R.id.screenshotsDesc);

        nomeHolder.setText(nome);
        areaArteHolder.setText(areaArte);
        plataformasHolder.setText(plataformas);
        descHolder.setText(desc);
        screenshotsDesc.setText(getString(R.string.info_screenshots) + " " + nome + ":");

        Picasso.get().load(image).into(imageHolder);
        Picasso.get().load(appScreenshot).into(appScreenshotHolder);
        Picasso.get().load(appScreenshot2).into(appScreenshotHolder2);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle any click event
                onBackPressed();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(website != null){
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(website));
                    activity.startActivity(intent);
                }
            }
        });

        return  root;
    }

    public void onBackPressed()
    {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().popBackStack();
        onDestroyView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}