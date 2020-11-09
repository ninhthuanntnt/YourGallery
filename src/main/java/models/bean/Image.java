package models.bean;

public class Image {
    private int id;
    private String name;
    private String path;
    private String pathThumbnail;

    public Image() {
    }

    public Image(int id, String name, String path, String pathThumbnail) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.pathThumbnail = pathThumbnail;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathThumbnail() {
        return pathThumbnail;
    }

    public void setPathThumbnail(String pathThumbnail) {
        this.pathThumbnail = pathThumbnail;
    }
}
