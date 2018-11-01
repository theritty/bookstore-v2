package com.bookstore.dao;

import com.bookstore.model.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class ProductDaoTest {

    private EmbeddedDatabase db;

    @Before
    public void setUp() {
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.HSQL)
    		.addScript("db/sql/create-db.sql")
    		.addScript("db/sql/insert-data.sql")
    		.build();
    }

    @Test
    public void testGetProduct() {
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    	ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setNamedParameterJdbcTemplate(template);

		Product product = productDao.findById(1);
        Assert.assertNotNull(product);
        Assert.assertEquals(1, product.getItemId().intValue());
        Assert.assertEquals(1, product.getSpec_id().intValue());
        Assert.assertEquals(1, product.getCategoryNode());
        Assert.assertEquals(9, product.getRemaining_quantity().intValue());
        Assert.assertEquals(300.0, product.getSalePrice().doubleValue(), 0.1);
        Assert.assertEquals(0.25, product.getDiscount().doubleValue(), 0.1);
        Assert.assertEquals(false, product.isIs_best_seller());
        Assert.assertEquals(false, product.isIs_new());
        Assert.assertEquals("/resources/images/4.jpg", product.getThumbnailImage());
        Assert.assertEquals("The Medical Examiner", product.getName());

    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}