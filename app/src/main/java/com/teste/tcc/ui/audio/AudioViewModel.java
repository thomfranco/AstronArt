package com.teste.tcc.ui.audio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AudioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AudioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aqui você encontrará programas destinados à produção e edição de áudio");
    }

    public LiveData<String> getText() {
        return mText;
    }
}