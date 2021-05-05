package com.example.demo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.Assert.*;
//import static org.junit.Assert.assertThat;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
//spring data jpa by default takes the default embedded data base i.e. h2 database
//so we have add the dependency for h2 database in pom.xml
//in order to use the postgres database we have to use the following annotation
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//here NONE means we have to use the database specified in application.properties
//after this the test methods will use the postgresql database and not the default h2 embedded db
public class ProductTest
{
    @Autowired
    private ProductRepository repo;

    //CREATE PRODUCT TEST METHOD
    @Test
    @Rollback(false)
    @Order(1)
    public void testCreateProduct()
    {
        Product product = new Product("MacBookPro",2345);
        product.setName("MacBookPro");
        product.setPrice(2345);
        Product savedProduct = repo.save(product);

        assertNotNull(savedProduct);
    }
    //TEST CASE TO CHECK IF A PERTICULAR PRODUCT EXIST
    @Test
    @Order(2)
    public void testFindProductByNameExist()
    {
        String name = "MacBookPro";
        Product product = repo.findByName(name);

        assertThat(product.getName()).isEqualTo(name);
    }
    @Test
    @Order(3)
    public void testFindProductByNameNotExist()
    {
        String name = "MacBookAir";
        Product product = repo.findByName(name);

        assertNull(product);
    }
    //TEST CASE TO UPDATE PRODUCT
    @Test
    @Rollback(false)
    @Order(4)
    public void testUpdateProduct()
    {
        String productName = "kindle";
        Product product = new Product(productName,2000);
        product.setId(2);
        repo.save(product);
        Product updatedProduct = repo.findByName(productName);
        assertThat(updatedProduct.getName()).isEqualTo(productName);
    }
    //TEST CASE TO RETRIEVE LIST OF ALL PRODUCTS
    @Test
    @Order(5)
    public void testListProducts()
    {
        List<Product> products = (List<Product>) repo.findAll();
        for(Product product : products)
        {
            System.out.println(product);
        }
        assertThat(products).size().isGreaterThan(0);
    }
    //TEST CASE TO DELETE A PRODUCT
    @Test
    @Rollback(false)
    @Order(6)
    public void testDeleteProduct()
    {
        Integer id = 2;
        boolean isExistBeforeDelete = repo.findById(id).isPresent();
        repo.deleteById(id);
        boolean notExistAfterDelete = repo.findById(id).isPresent();
        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }
}
