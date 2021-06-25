package com.gauravdalvi.aaag.models;

public class Upload {

    String itemName, price, category, description, url;
    String email;

    public Upload(String itemName, String price, String category, String description, String url) {
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.description = description;
        this.url = url;
    }

    public Upload(String itemName, String price, String category, String description, String url, String email) {
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.description = description;
        this.url = url;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Upload() {}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
