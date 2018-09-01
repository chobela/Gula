package com.appexpress.gula.models;

public class Post {

    private String post_id;
    private String user_id;
    private String image;
    private String title;
    private String description;
    private String price;
    private String city;
    private String contact_email;
    private String phone;

    public Post(String post_id, String user_id, String image, String title, String description,
                String price, String city, String contact_email, String phone) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.price = price;
        this.city = city;
        this.contact_email = contact_email;
        this.phone = phone;
    }

    public Post() {

    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String price) {
        this.price = phone;
    }


    @Override
    public String toString() {
        return "Post{" +
                "post_id='" + post_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", contact_email='" + contact_email + '\'' +
                ", phone='" + phone + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
