package models.bean;

public class Image {
    private int id;
    private String name;
    private String path;
    private String pathThumbnail;
    private int userId;
    private int albumId;

    public Image() {
        albumId = 0;
    }

    public Image(int id, String name, String path, String pathThumbnail, int userId, int albumId) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.pathThumbnail = pathThumbnail;
        this.userId = userId;
        this.albumId = albumId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
