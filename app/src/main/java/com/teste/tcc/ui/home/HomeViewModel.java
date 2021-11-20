package com.teste.tcc.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Acesse o menu lateral, clicando no botão superior esquerdo ou deslizando o dedo da esquerda para a direita da tela. Então, selecione uma área na qual deseja buscar programas gratuitos :)");
    }

    public LiveData<String> getText() {
        return mText;
    }
}