package com.example.retrofitjsonarraytest;

public class ProfileItem {

    public ProfileItem(String id, String account, String nickname, String image, String thumb) {
        this.id = id;
        this.account = account;
        this.nickname = nickname;
        this.image = image;
        this.thumb = thumb;
    }

    String id;
    String account;
    String nickname;
    String image;
    String thumb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
