package ru.blinov.control.inventory.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="inventory_cards")
public class InventoryCard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="identifier")
	private String identifier;
	
	@Column(name="class")
	private String className;
	
	@Column(name="created_at",
	    	columnDefinition="TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt = new Date();
	
	@Column(name="status")
	private String status;
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
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
	
	@Column(name="product_length")
	private String productLength;
	
	@Column(name="product_width")
	private String productWidth;
	
	@Column(name="product_height")
	private String productHeight;
	
	@Column(name="product_weight")
	private String productWeight;
	
	@Column(name="product_description")
	private String productDescription;
	
	public InventoryCard() {
		
	}

	public InventoryCard(int id, String identifier, String className, Date createdAt, String status, User user,
						 String productId, String productName, String productType, String productImage, String productManufacturer,
						 String productCountry, String productLength, String productWidth, String productHeight,
						 String productWeight, String productDescription) {
		this.id = id;
		this.identifier = identifier;
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
		this.productLength = productLength;
		this.productWidth = productWidth;
		this.productHeight = productHeight;
		this.productWeight = productWeight;
		this.productDescription = productDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
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

	public String getProductLength() {
		return productLength;
	}

	public void setProductLength(String productLength) {
		this.productLength = productLength;
	}

	public String getProductWidth() {
		return productWidth;
	}

	public void setProductWidth(String productWidth) {
		this.productWidth = productWidth;
	}

	public String getProductHeight() {
		return productHeight;
	}

	public void setProductHeight(String productHeight) {
		this.productHeight = productHeight;
	}

	public String getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	@Transient
	public String getProductImagePath() {
		
		if(productImage == null || identifier == null) {
			return null;
		}
		
		return "/src/main/resources/static/images/products/" + identifier + "/" + productImage;	
	}
	
	public String generateIdentifier() {
		
		String part1 = RandomStringUtils.randomNumeric(2);
		String part2 = RandomStringUtils.randomNumeric(5);
		String part3 = RandomStringUtils.randomNumeric(3);
		
		String identifier = part1.concat("h").concat(part2).concat("e").concat(part3);
		
		return identifier;
	}

	@Override
	public String toString() {
		return "InventoryCard [id=" + id + ", identifier=" + identifier + ", className=" + className + ", createdAt="
				+ createdAt + ", status=" + status + ", user=" + user + ", productId=" + productId + ", productName="
				+ productName + ", productType=" + productType + ", productImage=" + productImage
				+ ", productManufacturer=" + productManufacturer + ", productCountry=" + productCountry
				+ ", productLength=" + productLength + ", productWidth=" + productWidth + ", productHeight="
				+ productHeight + ", productWeight=" + productWeight + ", productDescription=" + productDescription
				+ "]";
	}

	
	

}
















