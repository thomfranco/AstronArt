package com.teste.tcc.ui;

import com.google.firebase.database.DatabaseReference;
import com.teste.tcc.ui.config.FirebaseConfig;

public class Recomendacao {

    private String id;
    private String nome;
    private String areaArte;
    private String plataformas;
    private String website;
    private String desc;
    private String image;
    private String appScreenshot;
    private String appScreenshot2;

    public Recomendacao(){
    }

    public Recomendacao(String id, String nome, String areaArte, String plataformas, String website, String desc, String image, String appScreenshot, String appScreenshot2) {
        this.id = id;
        this.nome = nome;
        this.areaArte = areaArte;
        this.plataformas = plataformas;
        this.website = website;
        this.desc = desc;
        this.image = image;
        this.appScreenshot = appScreenshot;
        this.appScreenshot2 = appScreenshot2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAreaArte() {
        return areaArte;
    }

    public void setAreaArte(String areaArte) {
        this.areaArte = areaArte;
    }

    public String getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(String plataformas) {
        this.plataformas = plataformas;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAppScreenshot() {
        return appScreenshot;
    }

    public void setAppScreenshot(String appScreenshot) {
        this.appScreenshot = appScreenshot;
    }

    public String getAppScreenshot2() {
        return appScreenshot2;
    }

    public void setAppScreenshot2(String appScreenshot2) {
        this.appScreenshot2 = appScreenshot2;
    }

    public void salvarDados() {
        DatabaseReference firebase = FirebaseConfig.getFirebaseDatabase();
        firebase.child("RecomendacaoVisual").child(this.id).setValue(this);
    }
}
