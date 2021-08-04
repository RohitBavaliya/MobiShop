package com.example.mycart;

public class Model_item_data {
    String header,image,price;

    public Model_item_data(String header, String image, String price) {
        this.header = header;
        this.image = image;
        this.price = price;
    }

    public Model_item_data() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
