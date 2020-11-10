package models.bean;

import java.util.List;

public class Album {
    private int id;
    private String name;
    private int userId;
    private List<Image> images;

    public Album() {
    }

    public Album(int id, String name, int userId, List<Image> images) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
