package com.example.mangaspt;

public class Utilizadores {

    int Id, telem;
    String user, password, email;

    public Utilizadores(int id, int telem, String user, String password, String email) {
        this.Id = id;
        this.telem = telem;
        this.user = user;
        this.password = password;
        this.email = email;
    }

    public Utilizadores() {

    }


    public int getId() {
        return Id;
    }

    public void setId(int id){this.Id = id;}

    public int getTelem() {
        return telem;
    }

    public void setTelem(int telem) {
        this.telem = telem;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
