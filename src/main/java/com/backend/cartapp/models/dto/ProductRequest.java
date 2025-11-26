package com.backend.cartapp.models.dto;

public class ProductRequest {
    private String name;
    private String description;
    private String category;
    private Long price;
    private String brand;
    private String quantity;
    private String image;

    public String getName(){return name;}
    public void setName(String n){this.name=n;}

    public String getDescription(){return description;}
    public void setDescription(String d){this.description=d;}

    public String getCategory(){return category;}
    public void setCategory(String n){this.category=n;}

    public Long getPrice(){return price;}
    public void setPrice(Long p){this.price=p;}

    public String getBrand(){return brand;}
    public void setBrand(String n){this.brand=n;}

    public String getQuantity(){return quantity;}
    public void setQuantity(String q){this.quantity=q;}

    public String getImage() {return image;}
    public void setImage(String n) {this.image = n;}
}
