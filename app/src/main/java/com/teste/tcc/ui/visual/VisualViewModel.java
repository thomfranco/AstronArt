package com.teste.tcc.ui.visual;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VisualViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VisualViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aqui você encontrará programas destinados a manipulação de imagens e ilustrações");
    }

    public LiveData<String> getText() {
        return mText;
    }
}