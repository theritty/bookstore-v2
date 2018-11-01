package com.bookstore.model;

public class Product {
	private Integer itemId;

	private String name;

	private String shortDescription;

	private String categoryNode;

	private Integer spec_id;

	private Double salePrice;

	private Double discount;

	private boolean is_best_seller;

	private boolean is_new;

	private String thumbnailImage;

	private Integer remaining_quantity;


	@Override
	public String toString() {
		return "Product [itemId=" + itemId + ", name=" + name + ", shortDescription=" + shortDescription +
				", categoryNode=" + categoryNode + ", salePrice=" + salePrice + ", discount=" + discount + ", is_best_seller=" + is_best_seller +
				", is_new=" + is_new +", thumbnailImage=" + thumbnailImage +", remaining_quantity=" + remaining_quantity +"]";
	}

	public Product() {
	}

	public Product(Product product) {
		this.itemId = product.itemId;
		this.name = product.name;
		this.shortDescription = product.shortDescription;
		this.categoryNode = product.categoryNode;
		this.salePrice = product.salePrice;
		this.discount = product.discount;
		this.is_best_seller = product.is_best_seller;
		this.is_new = product.is_new;
		this.thumbnailImage = product.thumbnailImage;
		this.remaining_quantity = product.remaining_quantity;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getCategoryNode() {
		return categoryNode;
	}

	public void setCategoryNode(String categoryNode) {
		this.categoryNode = categoryNode;
	}

	public Integer getSpec_id() {
		return spec_id;
	}

	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public boolean isIs_best_seller() {
		return is_best_seller;
	}

	public void setIs_best_seller(boolean is_best_seller) {
		this.is_best_seller = is_best_seller;
	}

	public boolean isIs_new() {
		return is_new;
	}

	public void setIs_new(boolean is_new) {
		this.is_new = is_new;
	}

	public String getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public Integer getRemaining_quantity() {
		return remaining_quantity;
	}

	public void setRemaining_quantity(Integer remaining_quantity) {
		this.remaining_quantity = remaining_quantity;
	}


}