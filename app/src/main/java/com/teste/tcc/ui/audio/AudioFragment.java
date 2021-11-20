package com.teste.tcc.ui.audio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.teste.tcc.R;
import com.teste.tcc.databinding.FragmentAudioBinding;
import com.teste.tcc.ui.DadosFragment;
import com.teste.tcc.ui.Recomendacao;
import com.teste.tcc.ui.RecomendacaoAdapter;
import com.teste.tcc.ui.config.FirebaseConfig;

import java.util.ArrayList;
import java.util.List;

public class AudioFragment extends Fragment implements RecomendacaoAdapter.OnAppListener {

    private AudioViewModel audioViewModel;
    private FragmentAudioBinding binding;

    List<Recomendacao> recomendacoes;
    RecomendacaoAdapter recomendacaoAdapter;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        audioViewModel =
                new ViewModelProvider(this).get(AudioViewModel.class);

        binding = FragmentAudioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAudio;

        audioViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        binding.recyclerAudio.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        recomendacoes = new ArrayList<>();
        recomendacaoAdapter = new RecomendacaoAdapter(recomendacoes, this); //adapter recebe a lista
        databaseReference = FirebaseConfig.getFirebaseDatabase();

        databaseReference.child("RecomendacaoAudio").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn:snapshot.getChildren()){
                    Recomendacao r = dn.getValue(Recomendacao.class);
                    recomendacoes.add(r);

                    binding.recyclerAudio.setAdapter(recomendacaoAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    @Override
    public void onAppCLick(int position) {
        Recomendacao recomendacao = recomendacoes.get(position);

        AppCompatActivity activity = (AppCompatActivity) binding.getRoot().getContext();
        activity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_frombottom, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out_tobottom)
                .replace(R.id.nav_host_fragment_content_main,new DadosFragment(recomendacao.getNome(), recomendacao.getAreaArte(), recomendacao.getPlataformas(), recomendacao.getWebsite(), recomendacao.getDesc(), recomendacao.getImage(), recomendacao.getAppScreenshot(), recomendacao.getAppScreenshot2()))
                .addToBackStack(null).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}