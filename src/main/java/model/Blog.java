package model;

public class Blog {
    private int id;
    private String userId;
    private String title;
    private String content;
    private String createdAt;
    private String imageUrl;

    public Blog() {}

    public Blog(String userId, String title, String content, String imageUrl) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }
    public Blog(int id, String userId, String title, String content, String createdAt, String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
