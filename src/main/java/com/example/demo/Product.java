package com.example.demo;

import javax.persistence.*;

//entity class
@Entity
@Table(name = "Product_Test")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //this annotation indicates that the value of id will be automatically generated in the database
    @Column(name = "id")
    private Integer id;
    @Column(name = "name",length = 100)
    //@Column(length = 100)
    private String name;
    @Column(name = "price")
    private float price;

    public Product(String name, float price)
    {
        this.name = name;
        this.price = price;
    }

    public Product()
    {
        //no-argument constructor
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
