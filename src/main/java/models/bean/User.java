package models.bean;

import java.util.List;

public class User{
    private int id;
    private String username;
    private String password;
    private String name;
    private List<Album> albums;

    public User() {
    }

    public User(int id, String username, String password, String name, List<Album> albums) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.albums = albums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
