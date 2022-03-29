package ru.blinov.control.inventory.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inventory_cards")
public class InventoryCard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="class")
	private String className;
	
	@Column(name="created_at")
	private String createdAt;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne(cascade= {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="product_id")
	private String productId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_type")
	private String productType;
	
	@Column(name="product_image")
	private String productImage;
	
	@Column(name="product_manufacturer")
	private String productManufacturer;
	
	@Column(name="product_country")
	private String productCountry;
	
	@Column(name="product_description")
	private String productDescription;
	
	public InventoryCard() {
		
	}

	public InventoryCard(String className, String createdAt, String status, User user, String productId,
			String productName, String productType, String productImage, String productManufacturer,
			String productCountry, String productDescription) {
		this.className = className;
		this.createdAt = createdAt;
		this.status = status;
		this.user = user;
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.productImage = productImage;
		this.productManufacturer = productManufacturer;
		this.productCountry = productCountry;
		this.productDescription = productDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductManufacturer() {
		return productManufacturer;
	}

	public void setProductManufacturer(String productManufacturer) {
		this.productManufacturer = productManufacturer;
	}

	public String getProductCountry() {
		return productCountry;
	}

	public void setProductCountry(String productCountry) {
		this.productCountry = productCountry;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
















