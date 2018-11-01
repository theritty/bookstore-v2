package com.bookstore.helper;

import com.bookstore.model.Product;
import com.bookstore.model.ProductSpecification;
import com.bookstore.model.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mappers  {

	public enum MapType{
		PRODUCT_MAPPER,
		SIMPLIFIED_PRODUCT_MAPPER,
		REVIEW_MAPPER,
		PRODUCT_SPECIFICATION_MAPPER
	}

	public static RowMapper getMapper(MapType type) {
		switch (type) {
			case SIMPLIFIED_PRODUCT_MAPPER: 	return new SimplifiedProductMapper();
			case PRODUCT_MAPPER: 				return new ProductMapper();
			case REVIEW_MAPPER: 				return new ReviewMapper();
			case PRODUCT_SPECIFICATION_MAPPER: 	return new ProductSpecificationMapper();
			default: 							return null;
		}
	}

	private static final class ProductMapper implements RowMapper<Product> {

		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setItemId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setCategoryNode(rs.getString("category_id"));
			product.setShortDescription(rs.getString("description"));
			product.setDiscount(rs.getDouble("discount"));
			product.setIs_best_seller(rs.getBoolean("is_best_seller"));
			product.setIs_new(rs.getBoolean("is_new"));
			product.setThumbnailImage(rs.getString("picture_url"));
			product.setSalePrice(rs.getDouble("price"));
			product.setRemaining_quantity(rs.getInt("remaining_quantity"));
			product.setSpec_id(rs.getInt("spec_id"));

			return product;
		}
	}

	private static final class SimplifiedProductMapper implements RowMapper<Product> {

		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setItemId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setDiscount(rs.getDouble("discount"));
			product.setSalePrice(rs.getDouble("price"));
			product.setThumbnailImage(rs.getString("picture_url"));

			return product;
		}
	}
	private static final class ReviewMapper implements RowMapper<Review> {

		public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
			Review review = new Review();
			review.setId(rs.getInt("id"));
			review.setText(rs.getString("text"));
			review.setCustomer_name(rs.getString("name"));

			return review;
		}
	}

	private static final class ProductSpecificationMapper implements RowMapper<ProductSpecification> {

		public ProductSpecification mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductSpecification spec = new ProductSpecification();
			spec.setId(rs.getInt("id"));
			spec.setSeries_title(rs.getString("series_title"));
			spec.setPublisher(rs.getString("publisher"));
			spec.setAuthor(rs.getString("author"));
			spec.setBook_format(rs.getString("book_format"));
			spec.setOriginal_languages(rs.getString("original_languages"));
			spec.setIsbn_13(rs.getString("isbn_13"));
			spec.setIsbn_10(rs.getString("isbn_10"));
			spec.setDimensions(rs.getString("dimensions"));
			spec.setPublication_date(rs.getDate("publication_date"));
			spec.setNumber_of_pages(rs.getInt("number_of_pages"));

			return spec;
		}
	}

}